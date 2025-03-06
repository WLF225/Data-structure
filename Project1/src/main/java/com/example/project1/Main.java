package com.example.project1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static MyList<Book> booksList = new MyList<>(30);

    public void start(Stage primaryStage) {

        primaryStage.setTitle("Main menu");
        primaryStage.setScene(new Scene(new MainMenu(primaryStage)));
        primaryStage.setMaximized(true);
        primaryStage.show();    
    }
    public static void main(String[] args) {
    launch(args);
    }
}
