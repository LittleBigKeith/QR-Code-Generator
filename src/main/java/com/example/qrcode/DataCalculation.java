package com.example.qrcode;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataCalculation {

    public List<Integer> dataBytes(String data, Version version, Constants.ENCODING_MODE encMode) {
        String bitString = encMode.binaryString()
                                .concat(getLengthString(data, version, encMode))
                                .concat(getDataString(data, encMode))
                                .concat("0000")
                                .replace(" ", "0");
        if (bitString.length() > version.totalDataBytes() * 8) { 
            System.err.println(String.format("Error: Data is too long for version %s\nTotal data length in bits = %d\nmaximum allowed data length in bits = %d ", version.name(), bitString.length(), version.totalDataBytes() * 8));
            System.exit(-1);
        }

        bitString = bitString.concat(String.format("%16s", Integer.toBinaryString(Constants.PADDING_PATTERN))
                                            .repeat((version.totalDataBytes() * 8 - bitString.length()) / 16));                           

        bitString = bitString.concat(String.format("%16s", Integer.toBinaryString(Constants.PADDING_PATTERN))
                                        .substring(0, version.totalDataBytes() * 8 - bitString.length()));

        int[] rearranged = new int[bitString.length() / 8];
        for (int i = 0; i < bitString.length(); i+=8) {
            rearranged[i / 8] = Integer.parseInt(bitString.substring(i, i + 4), 2) * 16 + Integer.parseInt(bitString.substring(i + 4, i + 8), 2);
        }
        return Arrays.stream(rearranged).boxed().collect(Collectors.toList());
    }

    public int applyFilter(int dataByte, Coords coords, MaskingPattern maskingPattern) {
        return dataByte ^ maskingPattern.of(coords.x(), coords.y());
    }

    private String getLengthString(String data, Version version, Constants.ENCODING_MODE encMode) {
        int numBits = 0;
        switch (encMode) {
            case Constants.ENCODING_MODE.NUMERIC:
                if (version.toInt() <= 9) {
                    numBits = 10;
                } else if (version.toInt() <= 26) {
                    numBits = 12;
                } else {
                    numBits = 14;
                }
                break; 
            case Constants.ENCODING_MODE.ALPHANUMERIC:
                if (version.toInt() <= 9) {
                    numBits = 9;
                } else if (version.toInt() <= 26) {
                    numBits = 11;
                } else {
                    numBits = 13;
                }
                break;
            case Constants.ENCODING_MODE.BINARY:
                if (version.toInt() <= 9) {
                    numBits = 8;
                } else if (version.toInt() <= 26) {
                    numBits = 16;
                } else {
                    numBits = 16;
                }
                break;
            case Constants.ENCODING_MODE.KANJI:
                if (version.toInt() <= 9) {
                    numBits = 8;
                } else if (version.toInt() <= 26) {
                    numBits = 10;
                } else {
                    numBits = 12;
                }
                break;
            default:
                break;
        }
        String format = String.format("%%%ds", numBits);
        return String.format(format, Integer.toBinaryString(data.length()));
    }

    private String getDataString(String data, Constants.ENCODING_MODE encMode) {
        String outputStr = new String();
        switch (encMode) {
            case Constants.ENCODING_MODE.NUMERIC:
                checkDataString(data, encMode, "0123456789");
                for (int i = 0; i < data.length() / 3; i++) {
                    int fragment = Integer.parseInt(data.substring(i * 3, i * 3 + 3));
                    outputStr = outputStr.concat(String.format("%10s", Integer.toBinaryString(fragment)));
                }
                String lastNumeric = data.substring(data.length() / 3 * 3, data.length());
                if (!lastNumeric.isEmpty()) {
                    int fragment = Integer.parseInt(lastNumeric);
                    if (fragment < 10) {
                        outputStr = outputStr.concat(String.format("%4s", Integer.toBinaryString(fragment)));
                    } else {
                        outputStr = outputStr.concat(String.format("%7s", Integer.toBinaryString(fragment)));
                    }
                }
                break;
            case Constants.ENCODING_MODE.ALPHANUMERIC:
                String allowableChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:";
                checkDataString(data, encMode, allowableChars);
                for (int i = 0; i < data.length() / 2; i++) {
                    int fragment = allowableChars.indexOf(data.charAt(i * 2)) * 45 + allowableChars.indexOf(data.charAt(i * 2 + 1));
                    outputStr = outputStr.concat(String.format("%11s", Integer.toBinaryString(fragment)));
                }
                if (data.length() % 2 == 1) {
                    int fragment = allowableChars.indexOf(data.charAt(data.length() - 1));
                    outputStr = outputStr.concat(String.format("%6s", Integer.toBinaryString(fragment)));
                }
                break;
            case Constants.ENCODING_MODE.BINARY:
                outputStr = data.chars().mapToObj(c -> String.format("%8s", Integer.toBinaryString(c))).collect(Collectors.joining(""));
                break;
            case Constants.ENCODING_MODE.KANJI:
                for (char c : data.toCharArray()) {
                    String str = String.valueOf(c);
                    try {
                        byte[] shiftJISBytes = str.getBytes("Shift_JIS");
                        if (shiftJISBytes.length != 2) {
                            System.err.println(String.format("Data string contains illegal character %c in Kanji mode", c));
                        }
                        int shiftJISValue = ((shiftJISBytes[0] & 0xFF) << 8) | (shiftJISBytes[1] & 0xFF);
                        if (!((shiftJISValue >= 0x8140 && shiftJISValue <= 0x9FFC) || (shiftJISValue >= 0xE040 && shiftJISValue <= 0xEBBF))) {
                            System.err.println(String.format("Data string contains illegal character %c in Kanji mode", c));
                            System.exit(-1);
                        }
                        if (shiftJISValue >= 0x8140 && shiftJISValue <= 0x9FFC) {
                            shiftJISValue -= 0x8140;
                        } else {
                            shiftJISValue -= 0xC140;
                        }
                        shiftJISValue = ((shiftJISValue & 0xff00) >> 8) * 0xC0 + (shiftJISValue & 0x00ff);
                        outputStr = outputStr.concat(String.format("%13s", Integer.toBinaryString(shiftJISValue)));
                    } catch (UnsupportedEncodingException e) {
                        System.err.println(String.format("Unsupported Encoding Exception: %s", e));
                    }
                }
                break;
            default:
                break;
        }
        return outputStr;
    }

    private void checkDataString(String data, Constants.ENCODING_MODE encMode, String allowableChars) {
        for (char dc : data.toCharArray()) {
            if (!IntStream.range(0, allowableChars.length()).anyMatch(i -> dc == allowableChars.charAt(i))) {
                System.err.println(String.format( "Data string contains illegal character %c in %s mode", dc, encMode.name()));
                System.exit(-1);
            }
        }
    }
}
