package com.example.qrcode;

public class Main {
    
    public static void main(String[] args) {
        QRCode qrCode = new QRCode("点茗", Version._10H, Constants.ENCODING_MODE.KANJI, MaskingPattern._6, false);
        qrCode.generate();
    }

}