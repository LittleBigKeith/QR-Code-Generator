package com.example.qrcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataCalculation {

    public List<Integer> getDataBytes(String data) {
        String bitString = new String(String.format("%4s", Integer.toBinaryString(Constants.BYTE_MODE))
                                              .concat(String.format("%8s", Integer.toBinaryString(data.length())))
                                              .concat(data.chars().mapToObj(c -> String.format("%8s", Integer.toBinaryString(c))).collect(Collectors.joining(""))))
                                              .concat("0000")
                                              .replace(" ", "0");
        
        int[] rearranged = new int[bitString.length() / 8];
        for (int i = 0; i < bitString.length(); i+=8) {
            rearranged[i / 8] = Integer.parseInt(bitString.substring(i, i + 4), 2) * 16 + Integer.parseInt(bitString.substring(i + 4, i + 8), 2);
        }
        return Arrays.stream(rearranged).boxed().collect(Collectors.toList());
    }
}
