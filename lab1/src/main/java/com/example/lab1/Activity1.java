package com.example.lab1;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Activity1 extends BorderPane {
    //search is not done
    Activity1() {
        TextField[] textFields = {new TextField(), new TextField()};

        Label[] labels = {new Label("Number1:"), new Label("Number2:")};
        Button[] buttons = {new Button("Print numbers"), new Button("Print number in reverse"),
                new Button("Fibonacci"), new Button("Find"), new Button("Reverse"),
                new Button("IsPerfect"), new Button("Sum")};
        TextArea tA = new TextArea();

        HBox hb = new HBox(132, labels);
        HBox hb2 = new HBox(30, textFields);
        VBox vBox = new VBox(30, hb, hb2);
        vBox.getChildren().addAll(buttons);


        setLeft(vBox);

        setRight(tA);
        BorderPane.setAlignment(tA, Pos.CENTER);


        buttons[0].setOnAction(e -> {
            tA.clear();
            printNum(Integer.parseInt(textFields[0].getText()), tA);
        });
        buttons[1].setOnAction(e -> {
            tA.clear();
            reversePrintNum(Integer.parseInt(textFields[0].getText()), tA);
        });

        buttons[2].setOnAction(e -> {
            tA.clear();
            long[] fibonacci = new long[Integer.parseInt(textFields[0].getText()) + 1];
            fib(Integer.parseInt(textFields[0].getText()), fibonacci);
            for (int i = 0; i < fibonacci.length; i++) {
                tA.appendText(i + ": " + fibonacci[i] + "\n");
            }
        });
//        find.setOnAction(e -> {
//
//        });
//
        buttons[4].setOnAction(e -> {
            tA.setText(reveseString(textFields[0].getText()));
        });

        buttons[5].setOnAction(e -> {
            tA.setText(isPerfect(Integer.parseInt(textFields[0].getText()))+"");
        });
        buttons[6].setOnAction(e -> {
            tA.setText(finalSum(Integer.parseInt(textFields[0].getText()))+"");
        });

    }


    public void printNum(int num, TextArea tA) {
        tA.appendText(num + "");
        if (num > 0) {
            printNum(num - 1, tA);
        }

    }

    public void reversePrintNum(int num, TextArea tA) {
        if (num > 0) {
            reversePrintNum(num - 1, tA);
        }
        tA.appendText(num + "");
    }


    public long fib(int n, long[] fib) {
        fib[n]++;
        if (n == 0 || n == 1) {
            return 1;
        }
        return fib(n - 1, fib) + fib(n - 2, fib);
    }
//    public boolean find(int[] num,int target,int index){
//        if(index == num.length){
//            return false;
//        }
//        if(num[index] == target){
//            return true;
//        }else{
//
//        }
//    }

    public String reveseString(String s) {
        return reveseString(s, s.length() - 1);
    }

    public String reveseString(String str, int n) {
        if (n == 0) {
            return str.charAt(0) + "";
        }
        return str.charAt(n) + reveseString(str, n - 1);
    }
//    public String reverseEveryWord(String s,int n){
//        if(s.charAt(n) != " "){
//        }
//    }
//    public String reverseWords(String s, int n){
//        String str = reveseString(s);
//    }

    public boolean isPerfect(int num) {
        int sum = 0;
        return isPerfect(num, num / 2 + 1, sum);
    }

    public boolean isPerfect(int num, int index, int sum) {
        if (index == 0) {
            return sum == num;
        } else {
            if (num % index == 0) {
                sum += index;
            }
            return isPerfect(num, index - 1, sum);
        }
    }

    public long sum(long n) {
        if (n < 10) {
            return n;
        } else
            return sum(n / 10) + sum(n % 10);
    }

    public long finalSum(long n) {
        long sum = sum(n);
        if (sum >= 10) {
            return finalSum(sum);
        }
        return sum;
    }
}
