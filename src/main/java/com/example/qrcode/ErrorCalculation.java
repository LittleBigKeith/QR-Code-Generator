package com.example.qrcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ErrorCalculation {

    int numberOfECB;
    List<Integer> alphas;
    int numElements;
    int polynomialNumber;

    public ErrorCalculation(int numberOfECB, int numElements, int polynomialNumber) {
        this.numberOfECB = numberOfECB;
        this.numElements = numElements;
        this.polynomialNumber = polynomialNumber;
        this.alphas = calculateAlphas();
    }


    public List<Integer> calculateAlphas() {
        int[] coefficients = new int[numElements - 1];
        coefficients[0] = 1;
        for (int i = 1; i < coefficients.length; i++) {
            coefficients[i] = coefficients[i - 1] << 1 > (numElements - 1) ? (coefficients[i - 1] << 1) ^ polynomialNumber : coefficients[i - 1] << 1;
        }
        return Arrays.stream(coefficients).boxed().toList();
    }

    public int[][] calculateETable(int numECB) {
        int[][] eTable = new int[numECB + 1][numECB + 1];
        eTable[0][0] = 1;
        for (int m = 1; m <= numECB; m++) {
            eTable[0][m] = 0;
        }
        for (int n = 1; n <= numECB; n++) {
            eTable[n][0] = 1;
            for (int m = 1; m <= n; m++) {
                eTable[n][m] = eTable[n-1][m] ^ (alphas.get((n - 1 + alphas.indexOf(eTable[n-1][m-1])) % 255));
            }
        }
        return eTable;
    }

    public List<Integer> calculateECB(List<Integer> dataBytes, List<Integer> GF) {
        int i = 0;
        int[] ECB = new int[numberOfECB];
        List<Integer> data = new ArrayList<>(dataBytes);
        while (true) {
            if (data.get(i) > 0) {
                int dataAlpha = alphas.indexOf(data.get(i));
                for (int j = 0; j < GF.size(); j++) {
                    if (GF.get(j) > 0) {
                        int GFAlpha = alphas.indexOf(GF.get(j));
                        if (i + j < data.size()) {
                            data.set(i + j, data.get(i + j) ^ alphas.get((dataAlpha + GFAlpha) % (numElements - 1)));
                        } else {
                            ECB[i + j - data.size()] = ECB[i + j - data.size()] ^ alphas.get((dataAlpha + GFAlpha) % (numElements - 1));
                        }
                    }
                }
            }
            i++;
            if (i >= data.size()) {
                return Arrays.stream(ECB).boxed().toList();
            }
        }
    }

    public int applyFilter(int formatInt, int maskPattern) {
        return formatInt ^ maskPattern;
    }
}
