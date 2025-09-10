package com.example.qrcode;

public class Main {
    
    public static void main(String[] args) {

        QRCode qrCode = new QRCode("www.wikipedia.org", Version._1L, Constants.ENCODING_MODE.BINARY, MaskingPattern._1);
        qrCode.generate();
    }

}