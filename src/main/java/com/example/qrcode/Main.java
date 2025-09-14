package com.example.qrcode;

public class Main {
    
    public static void main(String[] args) {

        QRCode qrCode = new QRCode("https://www.wikipedia.org/5H", Version._5H, Constants.ENCODING_MODE.BINARY, MaskingPattern._1);
        qrCode.generate();
    }

}