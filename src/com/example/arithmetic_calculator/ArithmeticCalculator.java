package com.example.arithmetic_calculator;

public final class ArithmeticCalculator {
  int add1to50() {
    int result = 0;
    for (int i = 1; i < 50; i++) {
      result = result + i;
    }
    return result;
  }

  int multiply5to20() {
    int result = 1;
    for (int i = 5; i < 20; i++) {
      result = result * i;
    }
    return result;
  }
}
