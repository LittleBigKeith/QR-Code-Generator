package com.example.qrcode;

public enum Version {
    _1L(1, ERROR_CORRECTION_LEVEL.L, 1, 26, 19);

    private final int version;
    private final ERROR_CORRECTION_LEVEL level;
    private final int blocks;
    private final int c;
    private final int k;

    Version(int version, ERROR_CORRECTION_LEVEL level, int blocks, int c, int k) {
        this.version = version;
        this.level = level;
        this.blocks = blocks;
        this.c = c;
        this.k = k;
    }

    public int toInt() {
        return version;
    }

    public ERROR_CORRECTION_LEVEL ecLevel() {
        return level;
    }

    enum ERROR_CORRECTION_LEVEL {
        L(1),
        M(2),
        Q(3),
        H(4);

        private final int code;

        ERROR_CORRECTION_LEVEL(int code) {
            this.code = code;
        }

        public String binaryString() {
            return String.format("%2s", Integer.toBinaryString(code)).replace(" ", "0");
        }
    }
}
