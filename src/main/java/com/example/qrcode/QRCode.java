package com.example.qrcode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QRCode {
    static final int version = 1;
    static final int size = version * 4 + 17;
    static final int numberOfDataECB = 7;
    static final int numberOfFormatECB = 10;
    static final int ecLevels = 1;
    static final int maskPatterns = 1;
    static final String data = "www.wikipedia.org";
    static int[][] qrcode = new int[size][size];

    DataCalculation dataCalculation = new DataCalculation();
    ErrorCalculation dataErrorCalculation = new ErrorCalculation(numberOfDataECB, 256, 285);
    ErrorCalculation formatErrorCalculation = new ErrorCalculation(10, 16,19);

    private static void fillAlignmentBlocks() {
        fillTopLeft();
        fillTopRight();
        fillBottomLeft();
    }

    private static void fillTopLeft() {
        for (int i = 0; i < Constants.ALIGN_BLOCK_L.length; i++) {
            for (int j = 0; j < Constants.ALIGN_BLOCK_L.length; j++) {
                qrcode[i][j] = Constants.ALIGN_BLOCK_L[i][j];
            }
        }
    }

    private static void fillTopRight() {
        for (int i = 0; i < Constants.ALIGN_BLOCK_L.length; i++) {
            for (int j = 0; j < Constants.ALIGN_BLOCK_L.length; j++) {
                qrcode[i][j + size - Constants.ALIGN_BLOCK_L.length] = Constants.ALIGN_BLOCK_L[i][j];
            }
        }
    }

    private static void fillBottomLeft() {
        for (int i = 0; i < Constants.ALIGN_BLOCK_L.length; i++) {
            for (int j = 0; j < Constants.ALIGN_BLOCK_L.length; j++) {
                qrcode[i + size - Constants.ALIGN_BLOCK_L.length][j] = Constants.ALIGN_BLOCK_L[i][j];
            }
        }
    }

    private static void fillTimingPatterns() {
        fillHorizontalLine();
        fillVerticalLine();
    }

    private static void fillHorizontalLine() {
        for (int j = Constants.ALIGN_BLOCK_L.length; j < size - Constants.ALIGN_BLOCK_L.length; j++) {
            qrcode[Constants.ALIGN_BLOCK_L.length - 1][j] = (j + 1) % 2;
        }
    }

    private static void fillVerticalLine() {
        int index = Constants.ALIGN_BLOCK_L.length - 1;
        for (int i = Constants.ALIGN_BLOCK_L.length; i < size - Constants.ALIGN_BLOCK_L.length; i++) {
            qrcode[i][index] = (i + 1) % 2;
        }
    }

    private static void fillFixedDots() {
        qrcode[size - Constants.ALIGN_BLOCK_L.length - 1][Constants.ALIGN_BLOCK_L.length + 1] = 1;
    }
    
    private static void fillAlignmentPatterns() {
        if (version == 1) {
            return;
        }
        int minCentre = Constants.ALIGN_BLOCK_L.length - 1;
        int maxCentre = size - Constants.ALIGN_BLOCK_L.length;
        int numPatterns = version / 7 + 2;
        int[] centres = new int[numPatterns];
        for (int i = 0; i < numPatterns; i++) {
            centres[i] = minCentre + i * (maxCentre - minCentre) / (numPatterns - 1);
        }
        for (int c1 : centres) {
            for (int c2: centres) {
                if (qrcode[c1][c2] == 1) {
                    continue;
                }
                for (int i = 0; i < 5; i++) {
                    for (int j = 0 ; j < 5 ; j++) {
                        qrcode[c1 + i - 2][c2 + j - 2] = Constants.ALIGN_BLOCK_S[i][j];
                    }
                }
            }
        }
    }

    private void fillFormatInfo() {
        String formatString = String.join("", String.format("%2s", Integer.toBinaryString(ecLevels)), String.format("%3s", Integer.toBinaryString(maskPatterns))).replace(" ", "0");
        List<Integer> formatBytes = formatString.chars().boxed().map(i -> i - '0').collect(Collectors.toList());
        List<Integer> GF = List.of(1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1);
        List<Integer> ECB = formatErrorCalculation.calculateECB(formatBytes, GF);
        formatBytes.addAll(ECB);
        int formatInt = Integer.parseInt(formatBytes.stream().map(String::valueOf).collect(Collectors.joining()), 2);
        formatInt = formatInt ^ 0b101010000010010;
        formatString = String.format("%15s", Integer.toBinaryString(formatInt));
        formatBytes = formatString.chars().boxed().map(i -> i - '0').collect(Collectors.toList());
        System.out.println(formatBytes);
        for (int i = 0; i < 6; i++) {
            qrcode[Constants.ALIGN_BLOCK_L.length + 1][i] = formatBytes.get(i);
        }
        qrcode[Constants.ALIGN_BLOCK_L.length + 1][Constants.ALIGN_BLOCK_L.length] = formatBytes.get(6);
        qrcode[Constants.ALIGN_BLOCK_L.length + 1][Constants.ALIGN_BLOCK_L.length + 1] = formatBytes.get(7);
        qrcode[Constants.ALIGN_BLOCK_L.length][Constants.ALIGN_BLOCK_L.length + 1] = formatBytes.get(8);
        for (int i = 0; i < 6; i++) {
            qrcode[Constants.ALIGN_BLOCK_L.length - i - 2][Constants.ALIGN_BLOCK_L.length + 1] = formatBytes.get(i + 9);
        }
        for (int i = 0; i < 7; i++) {
            qrcode[size - i - 1][Constants.ALIGN_BLOCK_L.length + 1] = formatBytes.get(i);
        }
        for (int i = 0; i < 8; i++) {
            qrcode[Constants.ALIGN_BLOCK_L.length + 1][size - Constants.ALIGN_BLOCK_L.length + i - 1] = formatBytes.get(i + 7);
        }
    }

    public void print() {
        fillAlignmentBlocks();
        fillAlignmentPatterns();
        fillTimingPatterns();
        fillFormatInfo();
        fillFixedDots();

        for (int[] row : qrcode) {
            for (int grid : row) {
                System.out.print(grid == 1 ? Constants.FILLED_SQUARE : " ");
            }
            System.out.println();
        }

        List<Integer> dataBytes = dataCalculation.getDataBytes(data);
        List<Integer> GF = IntStream.rangeClosed(0, numberOfDataECB).map(i -> dataErrorCalculation.getGFCoefficient(numberOfDataECB, i)).boxed().collect(Collectors.toList()).reversed();
        List<Integer> ECB = dataErrorCalculation.calculateECB(dataBytes, GF);
        dataBytes.addAll(ECB);
        System.out.println(dataBytes);

        String dataBinaryString = String.join("", dataBytes.stream().map(i -> String.format("%8s", Integer.toBinaryString(i)).replace(" ", "0")).collect(Collectors.toList()));
        System.out.println(dataBinaryString);

        Coords coords = new Coords(size);
        System.out.println(coords.toString());
        while (coords.getX() >= 0 && coords.getX() < size && coords.getY() >= 0 && coords.getY() < size) {

            coords.update();
            System.out.println(coords.toString());
        }
    }
}
