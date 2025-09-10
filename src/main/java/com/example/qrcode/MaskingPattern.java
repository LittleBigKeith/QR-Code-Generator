package com.example.qrcode;

public enum MaskingPattern {

    _1(1),
    _2(2),
    _3(3),
    _4(4),
    _5(5),
    _6(6),
    _7(7),
    _8(8);

    private final int index;

    MaskingPattern(int index) {
        this.index = index;
    }

    public int pattern_0(int j, int i) { return (i + j) % 2 == 0 ? 1 : 0; }
    public int pattern_1(int j, int i) { return i % 2 == 0 ? 1 : 0; }
    public int pattern_2(int j, int i) { return j % 3 == 0 ? 1 : 0; }
    public int pattern_3(int j, int i) { return (i + j) % 3 == 0 ? 1 : 0; }
    public int pattern_4(int j, int i) { return (i / 2 + j / 3) % 2 == 0 ? 1 : 0; }
    public int pattern_5(int j, int i) { return (i * j) % 2 + (i * j) % 3 == 0 ? 1 : 0; }
    public int pattern_6(int j, int i) { return ((i * j) % 3 + i * j) % 2 == 0 ? 1 : 0; }
    public int pattern_7(int j, int i) { return ((i * j) % 3 + i + j) % 2 == 0 ? 1 : 0; }

    interface Mask {
        int mask(int j, int i);
    }
    private Mask[] moveActions = new Mask[] {
        new Mask() { public int mask(int j, int i) { return pattern_0(i, j); } },
        new Mask() { public int mask(int j, int i) { return pattern_1(i, j); } },
        new Mask() { public int mask(int j, int i) { return pattern_2(i, j); } },
        new Mask() { public int mask(int j, int i) { return pattern_3(i, j); } },
        new Mask() { public int mask(int j, int i) { return pattern_4(i, j); } },
        new Mask() { public int mask(int j, int i) { return pattern_5(i, j); } },
        new Mask() { public int mask(int j, int i) { return pattern_6(i, j); } },
        new Mask() { public int mask(int j, int i) { return pattern_7(i, j); } },
    };

    public int of(int j, int i) {
        return moveActions[index].mask(i, j);
    }

    public String binaryString() {
        return String.format("%3s", Integer.toBinaryString(index)).replace(" ", "0");
    }

}
