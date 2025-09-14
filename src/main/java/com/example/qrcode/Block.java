package com.example.qrcode;

public class Block {

    private int totalCodeword;
    private int dataCodeword;

    public Block(int totalCodeword, int dataCodeword) {
        this.totalCodeword = totalCodeword;
        this.dataCodeword = dataCodeword;
    }

    public int numDataCodewords() {
        return dataCodeword;
    }

    public int numErrorCodewords() {
        return totalCodeword - dataCodeword;
    }
}
