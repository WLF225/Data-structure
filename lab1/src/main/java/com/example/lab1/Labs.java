package com.example.lab1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Labs extends Application {
    @Override
    public void start(Stage stage){
        TabPane tP = new TabPane();
        Tab tP1 = new Tab("Lab1");
        tP1.setClosable(Boolean.FALSE);
        Tab tP2 = new Tab("Lab2");
        tP2.setClosable(Boolean.FALSE);
        Tab tP3 = new Tab("Lab3");
        tP3.setClosable(Boolean.FALSE);
        Tab tP4 = new Tab("Lab4");
        tP4.setClosable(Boolean.FALSE);
        Tab tP5 = new Tab("Lab5");
        tP5.setClosable(Boolean.FALSE);
        Tab tP6 = new Tab("Lab6");
        tP6.setClosable(Boolean.FALSE);
        Tab tP7 = new Tab("Lab7");
        tP7.setClosable(Boolean.FALSE);
        Tab tP8 = new Tab("Lab8");
        tP8.setClosable(Boolean.FALSE);
        Tab tP9 = new Tab("Lab9");
        tP9.setClosable(Boolean.FALSE);
        Tab tP10 = new Tab("Lab10");
        tP10.setClosable(Boolean.FALSE);
        Tab tP11 = new Tab("Lab11");
        tP11.setClosable(Boolean.FALSE);
        Tab tP12 = new Tab("Lab12");
        tP12.setClosable(Boolean.FALSE);
        tP.getTabs().addAll(tP1, tP2, tP3, tP4, tP5, tP6, tP7, tP8, tP9, tP10, tP11, tP12);

        tP1.setContent(new Activity1());

        Scene scene = new Scene(tP);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Labs");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}