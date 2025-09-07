package com.example.qrcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorCalculation {

    int numberOfECB;
    List<Integer> alphas;
    int numElements;
    int polynomialNumber;

    public ErrorCalculation(int numberOfECB, int numElements, int polynomialNumber) {
        this.numberOfECB = numberOfECB;
        this.numElements = numElements;
        this.polynomialNumber = polynomialNumber;
        alphas = calculateAlphas();
    }


    public List<Integer> calculateAlphas() {
        int[] coefficients = new int[numElements - 1];
        coefficients[0] = 1;
        for (int i = 1; i < coefficients.length; i++) {
            coefficients[i] = coefficients[i - 1] << 1 > (numElements - 1) ? (coefficients[i - 1] << 1) ^ polynomialNumber : coefficients[i - 1] << 1;
        }
        return Arrays.stream(coefficients).boxed().toList();
    }

    public int getGFCoefficient(int polyDegree, int xDegree) {
        List<List<Integer>> comboList = new ArrayList<>();
        getCombos(comboList, new ArrayList<>(), polyDegree - xDegree, 0);
        return getAlphaValue(comboList);
    }

    private void getCombos(List<List<Integer>> comboList, List<Integer> combo, int len, int index) {
        if (combo.size() == len) {
            comboList.add(combo);
            return;
        }
        if (index >= numberOfECB) {
            return;
        }
        List<Integer> newCombo = new ArrayList<>(combo);
        newCombo.add(index);
        getCombos(comboList, newCombo, len, index + 1);
        getCombos(comboList, combo, len, index + 1);
    }

    private int getAlphaValue(List<List<Integer>> comboList) {
        List<Integer> comboSumList = comboList.stream().map(combo -> combo.stream().mapToInt(Integer::intValue).sum() % (numElements - 1)).collect(Collectors.toList());
        int alphaDegree = 0;
        for (int c : comboSumList) {
            alphaDegree ^= alphas.get(c);
        }
        return alphaDegree;
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
}
