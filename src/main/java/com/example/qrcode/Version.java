package com.example.qrcode;

public enum Version {
    _1L(1, ErrorCorrectionLevel.L, new Block[]{new Block(26, 19)}),
    _1M(1, ErrorCorrectionLevel.M, new Block[]{new Block(26, 16)}),
    _1Q(1, ErrorCorrectionLevel.Q, new Block[]{new Block(26, 13)}),
    _1H(1, ErrorCorrectionLevel.H, new Block[]{new Block(26, 9)}),
    _2L(2, ErrorCorrectionLevel.L, new Block[]{new Block(44, 34)}),
    _2M(2, ErrorCorrectionLevel.M, new Block[]{new Block(44, 28)}),
    _2Q(2, ErrorCorrectionLevel.Q, new Block[]{new Block(44, 22)}),
    _2H(2, ErrorCorrectionLevel.H, new Block[]{new Block(44, 16)}),
    _3L(3, ErrorCorrectionLevel.L, new Block[]{new Block(70, 55)}),
    _3M(3, ErrorCorrectionLevel.M, new Block[]{new Block(70, 44)}),
    _3Q(3, ErrorCorrectionLevel.Q, new Block[]{new Block(35, 17), new Block(35, 17)}),
    _3H(3, ErrorCorrectionLevel.H, new Block[]{new Block(35, 13), new Block(35, 13)}),
    _4L(4, ErrorCorrectionLevel.L, new Block[]{new Block(100, 80)}),
    _4M(4, ErrorCorrectionLevel.M, new Block[]{new Block(50, 32), new Block(50, 32)}),
    _4Q(4, ErrorCorrectionLevel.Q, new Block[]{new Block(50, 24), new Block(50, 24)}),
    _4H(4, ErrorCorrectionLevel.H, new Block[]{new Block(25, 9), new Block(25, 9), new Block(25, 9), new Block(25, 9)}),
    _5L(5, ErrorCorrectionLevel.L, new Block[]{new Block(134, 108)}),
    _5M(5, ErrorCorrectionLevel.M, new Block[]{new Block(67, 43), new Block(67, 43)}),
    _5Q(5, ErrorCorrectionLevel.Q, new Block[]{new Block(33, 15), new Block(33, 15), new Block(34, 16), new Block(34, 16)}),
    _5H(5, ErrorCorrectionLevel.H, new Block[]{new Block(33, 11), new Block(33, 11), new Block(34, 12), new Block(34, 12)}),
    _6L(6, ErrorCorrectionLevel.L, new Block[]{new Block(86, 68), new Block(86, 68)}),
    _6M(6, ErrorCorrectionLevel.M, new Block[]{new Block(43, 27), new Block(43, 27), new Block(43, 27), new Block(43, 27)}),
    _6Q(6, ErrorCorrectionLevel.Q, new Block[]{new Block(43, 19), new Block(43, 19), new Block(43, 19), new Block(43, 19)}),
    _6H(6, ErrorCorrectionLevel.H, new Block[]{new Block(43, 15), new Block(43, 15), new Block(43, 15), new Block(43, 15)}),
    _7L(7, ErrorCorrectionLevel.L, new Block[]{new Block(98, 78), new Block(98, 78)});

    private final int version;
    private final ErrorCorrectionLevel level;
    private final Block[] blocks;

    Version(int version, ErrorCorrectionLevel level, Block[] blocks) {
        this.version = version;
        this.level = level;
        this.blocks = blocks;
    }

    enum ErrorCorrectionLevel {
        L(1),
        M(0),
        Q(3),
        H(2);

        private final int code;

        ErrorCorrectionLevel(int code) {
            this.code = code;
        }

        public String binaryString() {
            return String.format("%2s", Integer.toBinaryString(code)).replace(" ", "0");
        }
    }

    public int toInt() {
        return version;
    }

    public ErrorCorrectionLevel ecLevel() {
        return level;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public int getTotalDataBytes() {
        int sum = 0;
        for (Block b : blocks) {
            sum += b.numDataCodewords();
        }
        return sum;
    }

    public int getMaxDataBytes() {
        int max = blocks[0].numDataCodewords();
        for (Block b : blocks) {
            max = b.numDataCodewords() > max ? b.numDataCodewords() : max;
        }
        return max;
    }

    public int getMaxErrorBytes() {
        int max = blocks[0].numErrorCodewords();
        for (Block b : blocks) {
            max = b.numErrorCodewords() > max ? b.numErrorCodewords() : max;
        }
        return max;
    }

    public int getVersionInformation(int version) {
        int versionInfo = 0;
        return versionInfo;
    }
}
