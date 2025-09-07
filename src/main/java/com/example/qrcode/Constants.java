package com.example.qrcode;

public class Constants {
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

    final static char FILLED_SQUARE = '\u25A0';
    final static int BYTE_MODE = 0b0100;
}

