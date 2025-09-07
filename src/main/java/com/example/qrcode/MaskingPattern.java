package com.example.qrcode;

public class MaskingPattern {

    int index;

    public MaskingPattern(int index) {
        this.index = index;
    }

    public void pattern_1() { }
    public void pattern_2() { }
    public void pattern_3() { }
    public void pattern_4() { }
    public void pattern_5() { }
    public void pattern_6() { }
    public void pattern_7() { }
    public void pattern_8() { }

    interface Mask {
        void mask();
    }

    private Mask[] moveActions = new Mask[] {
        new Mask() { public void mask() { pattern_1(); } },
        new Mask() { public void mask() { pattern_2(); } },
        new Mask() { public void mask() { pattern_3(); } },
        new Mask() { public void mask() { pattern_4(); } },
        new Mask() { public void mask() { pattern_5(); } },
        new Mask() { public void mask() { pattern_6(); } },
        new Mask() { public void mask() { pattern_7(); } },
        new Mask() { public void mask() { pattern_8(); } },
    };

    public void of() {
        moveActions[index].mask();
    }
}
