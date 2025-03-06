package com.example.project1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EditTab extends BorderPane {

    EditTab(Stage primaryStage, Book book) {

        Label[] labels = {new Label("ID: "),new Label("Title: "),new Label("Author: "),
                          new Label("Category: "),new Label("Published Year: "),
                          new Label("ISBN: ")};

        for (Label label : labels) {
            label.setFont(Font.font(50));
        }

        TextField[] textFields = {new TextField(),new TextField(),new TextField(),
                                  new TextField(),new TextField(),new TextField()};
        for (TextField textField : textFields) {
            textField.setFont(Font.font(34));
        }

        Button[] buttons = {new Button("Back"),new Button("Refill"),new Button("Edit")};

        MainMenu.styling(buttons,60);

        //To fill the text fields with the book data
        buttons[1].setOnAction(event -> {
            textFields[0].setText(book.getId()+"");
            textFields[1].setText(book.getTitle());
            textFields[2].setText(book.getAuthor());
            textFields[3].setText(book.getCategory());
            textFields[4].setText(book.getPublishedYear()+"");
            textFields[5].setText(book.getiSBN());
        });

        buttons[1].fire();

        //To return to the view tab
        buttons[0].setOnAction(event -> {
            primaryStage.setTitle("View tab");
            primaryStage.setScene(new Scene(new ViewTab(primaryStage)));
        });

        buttons[2].setOnAction(event -> {
            //To make sure it wont do duplicate errors
            Main.booksList.remove(book);
            try {
                Main.booksList.add(new Book(Integer.parseInt(textFields[0].getText()),
                        textFields[1].getText(), textFields[2].getText(),
                        textFields[3].getText(),Integer.parseInt(textFields[4].getText()),
                        textFields[5].getText()));
                Main.booksList.sort();
                buttons[0].fire();
            }catch (AlertException ex){
                //To return the book if the edit was wrong
                Main.booksList.add(book);
            }catch (Exception ex){
                //To return the book if the edit was wrong
                Main.booksList.add(book);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("ID and Published year should be integers");
                alert.showAndWait();
            }
        });

        HBox buttonsHB = new HBox(100,buttons);
        buttonsHB.setAlignment(Pos.CENTER);
        setBottom(buttonsHB);

        ImageView iV = new ImageView(new Image("edit.png"));
        setRight(iV);
        BorderPane.setAlignment(iV, Pos.CENTER);

        VBox vbox = new VBox(30,labels);
        vbox.setAlignment(Pos.CENTER);
        VBox vbox2 = new VBox(30,textFields);
        vbox2.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(30,vbox,vbox2);
        hbox.setAlignment(Pos.CENTER);
        setLeft(hbox);

    }

}
