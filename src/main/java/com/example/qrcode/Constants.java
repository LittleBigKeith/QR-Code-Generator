package com.example.qrcode;

import java.util.List;

public class Constants {

    public enum ENCODING_MODE {
        NUMERIC(1),
        ALPHANUMERIC(2),
        BINARY(4),
        KANJI(8);

        private final int code;

        ENCODING_MODE(int code) {
            this.code = code;   
        }

        public String binaryString() {
            return String.format("%4s", Integer.toBinaryString(code)).replace(" ", "0");
        }
    }

    final static int[][] ALIGN_BLOCK_L = {{1, 1, 1, 1, 1, 1, 1},
                                          {1, 0, 0, 0, 0, 0, 1},
                                          {1, 0, 1, 1, 1, 0, 1},
                                          {1, 0, 1, 1, 1, 0, 1},
                                          {1, 0, 1, 1, 1, 0, 1},
                                          {1, 0, 0, 0, 0, 0, 1},
                                          {1, 1, 1, 1, 1, 1, 1},};

    final static int[][] ALIGN_BLOCK_S = {{1, 1, 1, 1, 1},
                                          {1, 0, 0, 0, 1},
                                          {1, 0, 1, 0, 1},
                                          {1, 0, 0, 0, 1},
                                          {1, 1, 1, 1, 1},};

    final static char FILLED_SQUARE = '\u2588';

    final static int DATA_NUM_ELE = 256;
    final static int DATA_POLY_NUM = 285;

    final static List<Integer> FORMAT_INFO_GF = List.of(1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1);
    final static int FORMAT_INFO_MASK_PATTERN = 0b101010000010010;
    final static int NUMBER_OF_FORMAT_ECB = 10;
    final static int FORMAT_NUM_ELE = 2;
    final static int FORMAT_POLY_NUM = 2;

    final static List<Integer> VERSION_INFO_GF = List.of(1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1);
    final static int NUMBER_OF_VERSION_ECB = 12;
    final static int VERSION_NUM_ELE = 2;
    final static int VERSION_POLY_NUM = 2;

    final static int PADDING_PATTERN = 0b1110110000010001;
}

