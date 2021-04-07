package com.gl.procamp;

import com.gl.procamp.service.SumCalculator;

public class App {
    private static final String FILE_NAME = "number.txt"; //Filename can be passed with arguments

    public static void main(String[] args) {
        new SumCalculator(FILE_NAME).calculate();
    }
}
