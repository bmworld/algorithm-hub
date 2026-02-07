package no15596;

import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Test t = new Test();
    int[] a = new int[1_000_000];
    Arrays.fill(a, 3_000_000);
    System.out.println("sum =" + t.sum(a));
  }
}
