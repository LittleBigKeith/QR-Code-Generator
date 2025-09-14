package com.example.qrcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataCalculation {

    public List<Integer> getDataBytes(String data, Version version, Constants.ENCODING_MODE encMode) {
        String bitString = encMode.binaryString()
                                .concat(String.format("%8s", Integer.toBinaryString(data.length())))
                                .concat(data.chars().mapToObj(c -> String.format("%8s", Integer.toBinaryString(c))).collect(Collectors.joining("")))
                                .concat("0000")
                                .replace(" ", "0");
        
        if (bitString.length() > version.getTotalDataBytes() * 8) { 
            System.err.println(String.format("Error: Data is too long for version %s\nTotal data length in bits = %d\nmaximum allowed data length in bits = %d ", version.name(), bitString.length(), version.getTotalDataBytes() * 8));
            System.exit(-1);
        }

        bitString = bitString.concat(String.format("%16s", Integer.toBinaryString(Constants.PADDING_PATTERN))
                                            .repeat((version.getTotalDataBytes() * 8 - bitString.length()) / 16));                           

        bitString = bitString.concat(String.format("%16s", Integer.toBinaryString(Constants.PADDING_PATTERN))
                                        .substring(0, version.getTotalDataBytes() * 8 - bitString.length()));

        int[] rearranged = new int[bitString.length() / 8];
        for (int i = 0; i < bitString.length(); i+=8) {
            rearranged[i / 8] = Integer.parseInt(bitString.substring(i, i + 4), 2) * 16 + Integer.parseInt(bitString.substring(i + 4, i + 8), 2);
        }
        return Arrays.stream(rearranged).boxed().collect(Collectors.toList());
    }


    public int applyFilter(int dataByte, Coords coords, MaskingPattern maskingPattern) {
        return dataByte ^ maskingPattern.of(coords.getX(), coords.getY());
    }
}
