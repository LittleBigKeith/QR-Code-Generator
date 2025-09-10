package com.example.qrcode;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QRCode {
    private Version version;
    private int size;
    private Constants.ENCODING_MODE encMode;
    // private Constants.ERROR_CORRECTION_LEVEL ecLevel;
    private int numberOfDataECB;
    private String data;
    private int[][] qrcode;
    private boolean[][] drawn;

    DataCalculation dataCalculation;
    ErrorCalculation dataErrorCalculation;
    ErrorCalculation formatErrorCalculation;
    MaskingPattern maskingPattern;

    public QRCode(String data, Version version, Constants.ENCODING_MODE encMode, MaskingPattern maskingPattern) {
        this.data = data;
        this.version = version;
        this.size = version.toInt() * 4 + 17;
        this.encMode = encMode;
        this.numberOfDataECB = 7;
        this.maskingPattern = maskingPattern;
        this.qrcode = new int[size][size];
        this.drawn = new boolean[size][size];

        this.dataCalculation = new DataCalculation();
        this.dataErrorCalculation = new ErrorCalculation(numberOfDataECB, Constants.DATA_NUM_ELE, Constants.DATA_POLY_NUM);
        this.formatErrorCalculation = new ErrorCalculation(Constants.NUMBER_OF_FORMAT_ECB, Constants.FORMAT_NUM_ELE, Constants.FORMAT_POLY_NUM);
    }

    private void fillAlignmentBlocks() {
        fillTopLeft();
        fillTopRight();
        fillBottomLeft();
    }

    private void fillTopLeft() {
        for (int i = 0; i < Constants.ALIGN_BLOCK_L.length; i++) {
            for (int j = 0; j < Constants.ALIGN_BLOCK_L.length; j++) {
                draw(i, j, Constants.ALIGN_BLOCK_L[i][j]);
            }
        }
        
        for (int i = 0; i <= Constants.ALIGN_BLOCK_L.length; i++) {
            draw(i, Constants.ALIGN_BLOCK_L.length, 0);
        }

        for (int j = 0; j <= Constants.ALIGN_BLOCK_L.length; j++) {
            draw(Constants.ALIGN_BLOCK_L.length, j, 0);
        }
    }

    private void fillBottomLeft() {
        for (int i = 0; i < Constants.ALIGN_BLOCK_L.length; i++) {
            for (int j = 0; j < Constants.ALIGN_BLOCK_L.length; j++) {
                draw(i + size - Constants.ALIGN_BLOCK_L.length, j, Constants.ALIGN_BLOCK_L[i][j]);
            }
        }
        
        for (int i = 0; i <= Constants.ALIGN_BLOCK_L.length; i++) {
            draw(size - Constants.ALIGN_BLOCK_L.length + i - 1, Constants.ALIGN_BLOCK_L.length, 0);
        }

        for (int j = 0; j <= Constants.ALIGN_BLOCK_L.length; j++) {
            draw(size - Constants.ALIGN_BLOCK_L.length - 1, j, 0);
        }
    }

    private void fillTopRight() {
        for (int i = 0; i < Constants.ALIGN_BLOCK_L.length; i++) {
            for (int j = 0; j < Constants.ALIGN_BLOCK_L.length; j++) {
                draw(i, j + size - Constants.ALIGN_BLOCK_L.length, Constants.ALIGN_BLOCK_L[i][j]);
            }
        }
        
        for (int i = 0; i <= Constants.ALIGN_BLOCK_L.length; i++) {
            draw(i, size - Constants.ALIGN_BLOCK_L.length - 1, 0);
        }

        for (int j = 0; j <= Constants.ALIGN_BLOCK_L.length; j++) {
            draw(Constants.ALIGN_BLOCK_L.length, size - Constants.ALIGN_BLOCK_L.length + j - 1, 0);
        }
    }

    private void fillTimingPatterns() {
        fillHorizontalLine();
        fillVerticalLine();
    }

    private void fillHorizontalLine() {
        for (int j = Constants.ALIGN_BLOCK_L.length; j < size - Constants.ALIGN_BLOCK_L.length; j++) {
            draw(Constants.ALIGN_BLOCK_L.length - 1, j, (j + 1) % 2);
        }
    }

    private void fillVerticalLine() {
        int index = Constants.ALIGN_BLOCK_L.length - 1;
        for (int i = Constants.ALIGN_BLOCK_L.length; i < size - Constants.ALIGN_BLOCK_L.length; i++) {
            draw(i, index, (i + 1) % 2);
        }
    }

    private void fillFixedDots() {
        draw(size - Constants.ALIGN_BLOCK_L.length - 1, Constants.ALIGN_BLOCK_L.length + 1, 1);
    }
    
    private void fillAlignmentPatterns() {
        if (version.toInt() == 1) {
            return;
        }
        int minCentre = Constants.ALIGN_BLOCK_L.length - 1;
        int maxCentre = size - Constants.ALIGN_BLOCK_L.length;
        int numPatterns = version.toInt() / 7 + 2;
        int[] centres = new int[numPatterns];
        for (int i = 0; i < numPatterns; i++) {
            centres[i] = minCentre + i * (maxCentre - minCentre) / (numPatterns - 1);
        }
        for (int c1 : centres) {
            for (int c2: centres) {
                if (qrcode(c1, c2) == 1) {
                    continue;
                }
                for (int i = 0; i < 5; i++) {
                    for (int j = 0 ; j < 5 ; j++) {
                        draw(c1 + i - 2, c2 + j - 2, Constants.ALIGN_BLOCK_S[i][j]);
                    }
                }
            }
        }
    }

    private void fillFormatInfo() {
        String formatBinaryString = String.join("", version.ecLevel().binaryString(), maskingPattern.binaryString());
        System.out.println(formatBinaryString);
        List<Integer> formatBytes = formatBinaryString.chars().boxed().map(i -> i - '0').collect(Collectors.toList());
        List<Integer> GF = Constants.FORMAT_INFO_GF;
        List<Integer> ECB = formatErrorCalculation.calculateECB(formatBytes, GF);
        formatBytes.addAll(ECB);
        int formatInt = Integer.parseInt(formatBytes.stream().map(String::valueOf).collect(Collectors.joining()), 2);
        formatInt = formatErrorCalculation.applyFilter(formatInt, Constants.FORMAT_INFO_MASK_PATTERN);
        formatBinaryString = String.format("%15s", Integer.toBinaryString(formatInt));
        formatBytes = formatBinaryString.chars().boxed().map(i -> i - '0').collect(Collectors.toList());
 
        for (int j = 0; j < 6; j++) {
            draw(Constants.ALIGN_BLOCK_L.length + 1, j, formatBytes.get(j));
        }
        draw(Constants.ALIGN_BLOCK_L.length + 1, Constants.ALIGN_BLOCK_L.length, formatBytes.get(6));
        draw(Constants.ALIGN_BLOCK_L.length + 1, Constants.ALIGN_BLOCK_L.length + 1, formatBytes.get(7));
        draw(Constants.ALIGN_BLOCK_L.length, Constants.ALIGN_BLOCK_L.length + 1, formatBytes.get(8));
        for (int i = 0; i < 6; i++) {
            draw(Constants.ALIGN_BLOCK_L.length - i - 2, Constants.ALIGN_BLOCK_L.length + 1, formatBytes.get(i + 9));
        }
        for (int i = 0; i < 7; i++) {
            draw(size - i - 1, Constants.ALIGN_BLOCK_L.length + 1, formatBytes.get(i));
        }
        for (int j = 0; j < 8; j++) {
            draw(Constants.ALIGN_BLOCK_L.length + 1, size - Constants.ALIGN_BLOCK_L.length + j - 1, formatBytes.get(j + 7));
        }
    }

    private void draw(int i, int j, int value) {
        qrcode[i][j] = value;
        drawn[i][j] = true;
    }

    private int qrcode(int i, int j) {
        return qrcode[i][j];
    }

    private boolean drawn(int i, int j) {
        return drawn[i][j];
    }

    public void generate() {
        fillAlignmentBlocks();
        fillAlignmentPatterns();
        fillTimingPatterns();
        fillFormatInfo();
        fillFixedDots();

        List<Integer> dataBytes = dataCalculation.getDataBytes(data, encMode);
        List<Integer> GF = IntStream.rangeClosed(0, numberOfDataECB).map(i -> dataErrorCalculation.getGFCoefficient(numberOfDataECB, i)).boxed().collect(Collectors.toList()).reversed();
        System.out.println(GF.toString());
        List<Integer> ECB = dataErrorCalculation.calculateECB(dataBytes, GF);
        System.out.println(ECB.toString());
        dataBytes.addAll(ECB);

        String dataBinaryString = String.join("", dataBytes.stream().map(i -> String.format("%8s", Integer.toBinaryString(i)).replace(" ", "0")).collect(Collectors.toList()));

        Coords coords = new Coords(size);

        Iterator<Integer> dataBytesIter = dataBinaryString.chars().map(i -> i - '0').iterator();
        Iterator<Integer> paddingIter = Constants.PADDING_PATTERN.iterator();
        while (true) {
            if (coords.getX() < 0 || coords.getX() >= size || coords.getY() < 0 || coords.getY() >=  size) {
                break;
            }
            if (!drawn(coords.getY(), coords.getX())) {
                try {
                    draw(coords.getY(), coords.getX(), dataCalculation.applyFilter(dataBytesIter.next(), coords, maskingPattern));
                } catch (NoSuchElementException e) {
                    try {
                        draw(coords.getY(), coords.getX(), dataCalculation.applyFilter(paddingIter.next(), coords, maskingPattern));
                    } catch (NoSuchElementException e2) {
                        paddingIter = Constants.PADDING_PATTERN.iterator();
                        draw(coords.getY(), coords.getX(), dataCalculation.applyFilter(paddingIter.next(), coords, maskingPattern));
                    }
                }
            }

            coords.update();
        }

        for (int i = 0; i < 4; i++) {
            System.out.println();
        }
        for (int[] row : qrcode) {
            System.out.print("        ");
            for (int grid : row) {
                System.out.print(grid == 1 ? String.format("%c%c", Constants.FILLED_SQUARE, Constants.FILLED_SQUARE) : "  ");
            }
            System.out.print("        ");
            System.out.println();
        }
        for (int i = 0; i < 4; i++) {
            System.out.println();
        }
    }
}
