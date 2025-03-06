package com.example.lab1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long number = scanner.nextLong();
        int sum = 0;


//        System.out.println(isPerfect(number));
        System.out.println(sum(number));
        System.out.println(finalSum(number));

    }
    public static boolean isPerfect(long num) {
        long sum = 0;
        return isPerfect(num, num/2 +1, sum);
    }

    public static boolean isPerfect(long num, long index, long sum) {
        if (index == 0) {
            return sum == num;
        } else {
            if (num % index == 0) {
                sum += index;
            }
            return isPerfect(num, index - 1, sum);
        }
    }
    public static long sum(long n){
        if (n <10 ){
            return n;
        }else
            return sum(n/10) + sum(n%10);
    }
    public static long finalSum(long n){
        long sum = sum(n);
        if (sum >= 10){
            return finalSum(sum);
        }
        return sum;
    }
}
