package com.example.qrcode;

public enum Version {
    _1L(1, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(1, 26, 19)}),
    _1M(1, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(1, 26, 16)}),
    _1Q(1, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(1, 26, 13)}),
    _1H(1, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(1, 26, 9)}),

    _2L(2, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(1, 44, 34)}),
    _2M(2, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(1, 44, 28)}),
    _2Q(2, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(1, 44, 22)}),
    _2H(2, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(1, 44, 16)}),

    _3L(3, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(1, 70, 55)}),
    _3M(3, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(1, 70, 44)}),
    _3Q(3, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(2, 35, 17)}),
    _3H(3, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(2, 35, 13)}),

    _4L(4, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(1, 100, 80)}),
    _4M(4, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(2, 50, 32)}),
    _4Q(4, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(2, 50, 24)}),
    _4H(4, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(4,25, 9)}),

    _5L(5, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(1, 134, 108)}),
    _5M(5, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(2, 67, 43)}),
    _5Q(5, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(2, 33, 15), new BlockChain(2, 34, 16)}),
    _5H(5, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(2, 33, 11), new BlockChain(2, 34, 12)}),

    _6L(6, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(2, 86, 68)}),
    _6M(6, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(4, 43, 27)}),
    _6Q(6, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(4, 43, 19)}),
    _6H(6, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(4, 43, 15)}),

    _7L(7, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(2, 98, 78)}),
    _7M(7, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(4, 49, 31)}),
    _7Q(7, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(2, 32, 14), new BlockChain(4, 33, 15)}),
    _7H(7, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(4, 39, 13), new BlockChain(1, 40, 14)}),

    _8L(8, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(2, 121, 97)}),
    _8M(8, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(2, 60, 38), new BlockChain(2, 61, 39)}),
    _8Q(8, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(4, 40, 18), new BlockChain(2, 41, 19)}),
    _8H(8, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(4, 40, 14), new BlockChain(2, 41, 15)}),

    _9L(9, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(2, 146, 116)}),
    _9M(9, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(3, 58, 36), new BlockChain(2, 59, 37)}),
    _9Q(9, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(4, 36, 16), new BlockChain(4, 37, 17)}),
    _9H(9, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(4, 36, 12), new BlockChain(4, 37, 13)}),

    _10L(10, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(2, 86, 68), new BlockChain(2, 87, 69)}),
    _10M(10, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(4, 69, 43), new BlockChain(1, 70, 44)}),
    _10Q(10, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(6, 43, 19), new BlockChain(2, 44, 20)}),
    _10H(10, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(6, 43, 15), new BlockChain(2, 44, 16)}),

    _11L(11, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(4, 101, 81)}),
    _11M(11, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(1, 80, 50), new BlockChain(4, 81, 51)}),
    _11Q(11, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(4, 50, 22), new BlockChain(4, 51, 23)}),
    _11H(11, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(3, 36, 12), new BlockChain(8, 37, 13)}),
    
    _12L(12, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(2, 116, 92), new BlockChain(2, 117, 93)}),
    _12M(12, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(6, 58, 36), new BlockChain(2, 59, 37)}),
    _12Q(12, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(4, 46, 20), new BlockChain(6, 47, 21)}),
    _12H(12, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(7, 42, 14), new BlockChain(4, 43, 15)}),

    _13L(13, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(4, 133, 107)}),
    _13M(13, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(8, 59, 37), new BlockChain(1, 60, 38)}),
    _13Q(13, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(8, 44, 20), new BlockChain(4, 45, 21)}),
    _13H(13, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(12, 33, 11), new BlockChain(4, 34, 12)}),

    _14L(14, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(3, 145, 115), new BlockChain(1, 146, 116)}),
    _14M(14, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(4, 64, 40), new BlockChain(5, 65, 41)}),
    _14Q(14, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(11, 36, 16), new BlockChain(5, 37, 17)}),
    _14H(14, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(11, 36, 12), new BlockChain(5, 37, 13)}),

    _15L(15, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(5, 109, 87), new BlockChain(1, 110, 88)}),
    _15M(15, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(5, 65, 41), new BlockChain(5, 66, 42)}),
    _15Q(15, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(5, 54, 24), new BlockChain(7, 55, 25)}),
    _15H(15, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(11, 36, 12), new BlockChain(7, 37, 13)}),

    _16L(16, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(5, 122, 98), new BlockChain(1, 123, 99)}),
    _16M(16, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(7, 73, 45), new BlockChain(3, 74, 46)}),
    _16Q(16, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(15, 43, 19), new BlockChain(2, 44, 20)}),
    _16H(16, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(3, 45, 15), new BlockChain(13, 46, 16)}),
    
    _17L(17, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(1, 135, 107), new BlockChain(4, 136, 108)}),
    _17M(17, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(10, 74, 46), new BlockChain(1, 75, 47)}),
    _17Q(17, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(2, 43, 19), new BlockChain(14, 44, 20)}),
    _17H(17, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(2, 45, 15), new BlockChain(14, 46, 16)}),

    _18L(18, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(5, 150, 120), new BlockChain(1, 151, 121)}),
    _18M(18, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(9, 69, 43), new BlockChain(4, 70, 44)}),
    _18Q(18, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(4, 50, 22), new BlockChain(13, 51, 23)}),
    _18H(18, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(2, 45, 15), new BlockChain(16, 46, 16)}),

    _19L(19, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(3, 141, 113), new BlockChain(4, 142, 114)}),
    _19M(19, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(3, 70, 44), new BlockChain(11, 71, 45)}),
    _19Q(19, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(17, 47, 21), new BlockChain(1, 48, 22)}),
    _19H(19, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(9, 45, 15), new BlockChain(10, 46, 16)}),

    _20L(20, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(3, 145, 115), new BlockChain(4, 146, 116)}),
    _20M(20, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(4, 70, 44), new BlockChain(11, 71, 45)}),
    _20Q(20, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(17, 47, 21), new BlockChain(2, 48, 22)}),
    _20H(20, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(13, 45, 15), new BlockChain(7, 46, 16)}),

    _21L(21, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(4, 152, 122), new BlockChain(4, 153, 123)}),
    _21M(21, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(8, 72, 45), new BlockChain(8, 73, 46)}),
    _21Q(21, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(12, 53, 24), new BlockChain(8, 54, 25)}),
    _21H(21, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(12, 45, 15), new BlockChain(10, 46, 16)}),

    _22L(22, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(2, 147, 117), new BlockChain(6, 148, 118)}),
    _22M(22, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(10, 73, 45), new BlockChain(7, 74, 46)}),
    _22Q(22, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(3, 54, 24), new BlockChain(19, 55, 25)}),
    _22H(22, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(3, 45, 15), new BlockChain(20, 46, 16)}),

    _23L(23, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(4, 158, 126), new BlockChain(4, 159, 127)}),
    _23M(23, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(8, 74, 46), new BlockChain(10, 75, 47)}),
    _23Q(23, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(7, 54, 24), new BlockChain(16, 55, 25)}),
    _23H(23, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(3, 45, 15), new BlockChain(21, 46, 16)}),

    _24L(24, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(6, 151, 121), new BlockChain(2, 152, 122)}),
    _24M(24, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(6, 75, 47), new BlockChain(14, 76, 48)}),
    _24Q(24, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(5, 54, 24), new BlockChain(19, 55, 25)}),
    _24H(24, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(7, 45, 15), new BlockChain(18, 46, 16)}),

    _25L(25, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(8, 152, 122), new BlockChain(1, 153, 123)}),
    _25M(25, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(8, 76, 48), new BlockChain(14, 77, 49)}),
    _25Q(25, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(13, 54, 24), new BlockChain(12, 55, 25)}),
    _25H(25, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(5, 45, 15), new BlockChain(21, 46, 16)}),

    _26L(26, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(4, 154, 124), new BlockChain(6, 155, 125)}),
    _26M(26, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(10, 74, 46), new BlockChain(14, 75, 47)}),
    _26Q(26, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(9, 54, 24), new BlockChain(17, 55, 25)}),
    _26H(26, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(1, 45, 15), new BlockChain(26, 46, 16)}),

    _27L(27, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(3, 157, 125), new BlockChain(8, 158, 126)}),
    _27M(27, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(9, 75, 47), new BlockChain(16, 76, 48)}),
    _27Q(27, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(4, 54, 24), new BlockChain(24, 55, 25)}),
    _27H(27, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(4, 45, 15), new BlockChain(24, 46, 16)}),

    _28L(28, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(7, 146, 116), new BlockChain(7, 147, 117)}),
    _28M(28, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(7, 73, 45), new BlockChain(20, 74, 46)}),
    _28Q(28, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(1, 53, 23), new BlockChain(28, 54, 24)}),
    _28H(28, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(8, 45, 15), new BlockChain(21, 46, 16)}),

    _29L(29, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(5, 145, 115), new BlockChain(10, 146, 116)}),
    _29M(29, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(12, 75, 47), new BlockChain(16, 76, 48)}),
    _29Q(29, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(6, 53, 23), new BlockChain(24, 54, 24)}),
    _29H(29, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(10, 45, 15), new BlockChain(20, 46, 16)}),

    _30L(30, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(5, 145, 115), new BlockChain(11, 146, 116)}),
    _30M(30, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(11, 75, 47), new BlockChain(18, 76, 48)}),
    _30Q(30, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(8, 54, 24), new BlockChain(23, 55, 25)}),
    _30H(30, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(14, 45, 15), new BlockChain(17, 46, 16)}),

    _31L(31, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(13, 145, 115), new BlockChain(3, 146, 116)}),
    _31M(31, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(2, 74, 46), new BlockChain(29, 75, 47)}),
    _31Q(31, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(10, 54, 24), new BlockChain(23, 55, 25)}),
    _31H(31, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(14, 45, 15), new BlockChain(19, 46, 16)}),

    _32L(32, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(17, 145, 115)}),
    _32M(32, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(10, 74, 46), new BlockChain(23, 75, 47)}),
    _32Q(32, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(14, 54, 24), new BlockChain(21, 55, 25)}),
    _32H(32, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(12, 45, 15), new BlockChain(23, 46, 16)}),

    _33L(33, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(17, 145, 115), new BlockChain(1, 146, 116)}),
    _33M(33, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(14, 74, 46), new BlockChain(21, 75, 47)}),
    _33Q(33, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(16, 54, 24), new BlockChain(21, 55, 25)}),
    _33H(33, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(11, 45, 15), new BlockChain(26, 46, 16)}),

    _34L(34, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(13, 145, 115), new BlockChain(6, 146, 116)}),
    _34M(34, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(14, 74, 46), new BlockChain(23, 75, 47)}),
    _34Q(34, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(30, 54, 24), new BlockChain(8, 55, 25)}),
    _34H(34, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(7, 45, 15), new BlockChain(32, 46, 16)}),

    _35L(35, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(12, 151, 121), new BlockChain(7, 152, 122)}),
    _35M(35, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(12, 75, 47), new BlockChain(26, 76, 48)}),
    _35Q(35, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(39, 54, 24), new BlockChain(1, 55, 25)}),
    _35H(35, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(7, 45, 15), new BlockChain(34, 46, 16)}),

    _36L(36, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(6, 151, 121), new BlockChain(14, 152, 122)}),
    _36M(36, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(6, 75, 47), new BlockChain(34, 76, 48)}),
    _36Q(36, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(46, 54, 24), new BlockChain(1, 55, 25)}),
    _36H(36, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(2, 45, 15), new BlockChain(42, 46, 16)}),

    _37L(37, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(8, 152, 122), new BlockChain(13, 153, 123)}),
    _37M(37, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(8, 76, 48), new BlockChain(34, 77, 49)}),
    _37Q(37, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(49, 54, 24), new BlockChain(1, 55, 25)}),
    _37H(37, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(10, 45, 15), new BlockChain(36, 46, 16)}),

    _38L(38, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(10, 152, 122), new BlockChain(13, 153, 123)}),
    _38M(38, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(3, 74, 46), new BlockChain(40, 75, 47)}),
    _38Q(38, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(48, 54, 24), new BlockChain(4, 55, 25)}),
    _38H(38, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(8, 45, 15), new BlockChain(40, 46, 16)}),

    _39L(39, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(7, 146, 116), new BlockChain(18, 147, 117)}),
    _39M(39, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(5, 75, 47), new BlockChain(40, 76, 48)}),
    _39Q(39, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(47, 54, 24), new BlockChain(7, 55, 25)}),
    _39H(39, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(10, 45, 15), new BlockChain(40, 46, 16)}),

    _40L(40, ErrorCorrectionLevel.L, new BlockChain[]{new BlockChain(5, 147, 117), new BlockChain(21, 148, 118)}),
    _40M(40, ErrorCorrectionLevel.M, new BlockChain[]{new BlockChain(1, 75, 47), new BlockChain(46, 76, 48)}),
    _40Q(40, ErrorCorrectionLevel.Q, new BlockChain[]{new BlockChain(45, 54, 24), new BlockChain(10, 55, 25)}),
    _40H(40, ErrorCorrectionLevel.H, new BlockChain[]{new BlockChain(19, 45, 15), new BlockChain(34, 46, 16)});

    private final int version;
    private final ErrorCorrectionLevel level;
    private final BlockChain[] blockChain;

    Version(int version, ErrorCorrectionLevel level, BlockChain[] blockChain) {
        this.version = version;
        this.level = level;
        this.blockChain = blockChain;
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

    public Block[] blocks() {
        int totalBlock = 0;
        for (BlockChain bc : blockChain) {
            totalBlock += bc.numBlock();
        }

        Block[] blocks = new Block[totalBlock];
        int i = 0;
        for (BlockChain bc: blockChain) {
            for (int j = 0; j < bc.numBlock(); j++) {
                blocks[i] = new Block(bc.totalCodeword(), bc.numDataCodeword());
                i++;
            }
        }
        return blocks;
    }

    public int totalDataBytes() {
        int sum = 0;
        for (BlockChain bc : blockChain) {
            sum += bc.numBlock() * bc.numDataCodeword();
        }
        return sum;
    }

    public int maxDataBytes() {
        int max = blockChain[0].numDataCodeword();
        for (BlockChain bc : blockChain) {
            max = bc.numDataCodeword() > max ? bc.numDataCodeword() : max;
        }
        return max;
    }

    public int maxErrorBytes() {
        int max = blockChain[0].numErrorCodeword();
        for (BlockChain bc : blockChain) {
            max = bc.numErrorCodeword() > max ? bc.numErrorCodeword() : max;
        }
        return max;
    }
}
