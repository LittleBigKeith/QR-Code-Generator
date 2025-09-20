package com.example.qrcode;

public class BlockChain {
    private int numBlock;
    private int numtotalCodeword;
    private int numdataCodeword;

    public BlockChain (int numBlock, int totalCodeword, int dataCodeword) {
        this.numBlock = numBlock;
        this.numtotalCodeword = totalCodeword;
        this.numdataCodeword = dataCodeword;
    }

    public int numBlock() {
        return numBlock;
    }

    public int totalCodeword() {
        return numtotalCodeword;
    }

    public int numDataCodeword() {
        return numdataCodeword;
    }

    public int numErrorCodeword() {
        return numtotalCodeword - numdataCodeword;
    }
}
