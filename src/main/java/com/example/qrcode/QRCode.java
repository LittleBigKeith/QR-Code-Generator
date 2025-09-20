package com.example.qrcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class QRCode {
    private Version version;
    private int size;
    private Constants.ENCODING_MODE encMode;
    private boolean mirror;
    private String data;
    private int[][] qrcode;
    private boolean[][] drawn;

    MaskingPattern maskingPattern;

    public QRCode(String data, Version version, Constants.ENCODING_MODE encMode, MaskingPattern maskingPattern, boolean mirror) {
        this.data = data;
        this.version = version;
        this.size = version.toInt() * 4 + 17;
        this.encMode = encMode;
        this.maskingPattern = maskingPattern;
        this.mirror = mirror;
        this.qrcode = new int[size][size];
        this.drawn = new boolean[size][size];
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
        ErrorCalculation formatErrorCalculation = new ErrorCalculation(Constants.NUMBER_OF_FORMAT_ECB, Constants.FORMAT_NUM_ELE, Constants.FORMAT_POLY_NUM);
        String formatBinaryString = String.join("", version.ecLevel().binaryString(), maskingPattern.binaryString());
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

    private void fillVersionInfo() {
        if (version.toInt() < 7) {
            return;
        }
        ErrorCalculation versionErrorCalculation = new ErrorCalculation(Constants.NUMBER_OF_VERSION_ECB, Constants.VERSION_NUM_ELE, Constants.VERSION_POLY_NUM);
        String versionBinaryString = String.format("%6s", Integer.toBinaryString(version.toInt())).replace(" ", "0");
        List<Integer> versionBytes = versionBinaryString.chars().boxed().map(i -> i - '0').collect(Collectors.toList());
        List<Integer> GF = Constants.VERSION_INFO_GF;
        List<Integer> ECB = versionErrorCalculation.calculateECB(versionBytes, GF);
        versionBytes.addAll(ECB);
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 3; i++) {
                draw(size - Constants.ALIGN_BLOCK_L.length - 4 + i, j, versionBytes.get(versionBytes.size() - j * 3 - i - 1));
            }
         }

         for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                draw(i, size - Constants.ALIGN_BLOCK_L.length - 4 + j, versionBytes.get(versionBytes.size() - i * 3 - j - 1));
            }
         }
    }

    private void draw(int i, int j, int value) {
        if (i < 0 || i > qrcode.length || j < 0 || j > qrcode[i].length) {
            return;
        }
        qrcode[i][j] = value;
        drawn[i][j] = true;
    }

    private int qrcode(int i, int j) {
        if (i < 0 || i > qrcode.length || j < 0 || j > qrcode[i].length) {
            return -1;
        }
        return qrcode[i][j];
    }

    private boolean drawn(int i, int j) {
       // if (i < 0 || i > qrcode.length || j < 0 || j > qrcode[i].length) {
       //     return true;
       // }
        return drawn[i][j];
    }

    public void generate() {

        fillAlignmentBlocks();
        fillAlignmentPatterns();
        fillTimingPatterns();
        fillFormatInfo();
        fillVersionInfo();
        fillFixedDots();

        DataCalculation dataCalculation = new DataCalculation();
        List<Integer> dataBytes = dataCalculation.dataBytes(data, version, encMode);
        List<Integer> errorBytes = new ArrayList<>();
        int startByte = 0;
        for (Block b : version.blocks()) {
            int numECB = b.numErrorCodewords();
            ErrorCalculation dataErrorCalculation = new ErrorCalculation(numECB, Constants.DATA_NUM_ELE, Constants.DATA_POLY_NUM);
            
            int[][] eTable = dataErrorCalculation.calculateETable(numECB);
            List<Integer> GF = Arrays.stream(eTable[numECB]).boxed().toList();
            List<Integer> ECB = dataErrorCalculation.calculateECB(dataBytes.subList(startByte, startByte + b.numDataCodewords()), GF);
            errorBytes.addAll(ECB);
            startByte += b.numDataCodewords();
        }

        Coords coords = new Coords(size);

        for (int i = 0; i < version.maxDataBytes(); i++) {
            int blockStartByte = 0;
            for (Block b : version.blocks()) {
                if (i >= b.numDataCodewords()) {
                    continue;
                }
                int blockByte = dataBytes.get(blockStartByte + i);
                String blockByteString = String.format("%8s", Integer.toBinaryString(blockByte)).replace(" ", "0");
                Iterator<Integer> blockByteIter = blockByteString.chars().map(j -> j - '0').iterator();
                while (blockByteIter.hasNext()) {
                    draw(coords.y(), coords.x(), dataCalculation.applyFilter(blockByteIter.next(), coords, maskingPattern));
                    while (drawn(coords.y(), coords.x())) {
                        coords.update();
                            if (coords.x() < 0 || coords.x() >= size || coords.y() < 0 || coords.y() >=  size) {
                            break;
                        }
                    }
                }
                blockStartByte += b.numDataCodewords();
            }
        }
        
        for (int i = 0; i < version.maxErrorBytes(); i++) {
            int blockStartByte = 0;
            for (Block b : version.blocks()) {
                int blockByte = errorBytes.get(blockStartByte + i);
                String blockByteString = String.format("%8s", Integer.toBinaryString(blockByte)).replace(" ", "0");
                Iterator<Integer> blockByteIter = blockByteString.chars().map(j -> j - '0').iterator();
                while (blockByteIter.hasNext()) {
                    if (coords.x() < 0 || coords.x() >= size || coords.y() < 0 || coords.y() >=  size) {
                        break;
                    }
                    draw(coords.y(), coords.x(), dataCalculation.applyFilter(blockByteIter.next(), coords, maskingPattern));
                    while (drawn(coords.y(), coords.x())) {
                        coords.update();
                            if (coords.x() < 0 || coords.x() >= size || coords.y() < 0 || coords.y() >=  size) {
                            break;
                        }
                    }
                }
                blockStartByte += b.numErrorCodewords();
            }
        }

        while (true) {
            if (coords.x() < 0 || coords.x() >= size || coords.y() < 0 || coords.y() >=  size) {
                break;
            }

            if (!drawn(coords.y(), coords.x())) {
                draw(coords.y(), coords.x(), dataCalculation.applyFilter(0, coords, maskingPattern));
            }

            coords.update();
        }
        
        for (int i = 0; i < 4; i++) {
            System.out.println();
        }
        for (int[] row : qrcode) {
            System.out.print("        ");
            for (int i = 0; i < row.length; i++) {
                if (mirror) {
                    System.out.print(row[row.length - i - 1] == 1 ? String.format("%c%c", Constants.FILLED_SQUARE, Constants.FILLED_SQUARE) : "  ");
                } else {
                    System.out.print(row[i] == 1 ? String.format("%c%c", Constants.FILLED_SQUARE, Constants.FILLED_SQUARE) : "  ");
                }
            }
            System.out.print("        ");
            System.out.println();
        }
        for (int i = 0; i < 4; i++) {
            System.out.println();
        }
    }
}
