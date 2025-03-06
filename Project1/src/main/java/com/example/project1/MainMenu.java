package com.example.project1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainMenu extends BorderPane {

    public MainMenu(Stage primaryStage) {

                Button[] buttons = {new Button("Read file"),
                        new Button("Save to file"),
                        new Button("Add book"),
                        new Button("View books")};

                styling(buttons,60);

                buttons[1].setOnAction(e-> {
                    FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showOpenDialog(primaryStage);
                    try (PrintWriter writer = new PrintWriter(file)) {
                        for (int i = 0; i < Main.booksList.size(); i++)
                            writer.println(Main.booksList.get(i).toString());

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Save File");
                        alert.setHeaderText(null);
                        alert.setContentText("The data has been saved successfully.");
                        alert.showAndWait();
                    }catch (Exception ex) {
                    }
                });

                buttons[0].setOnAction(e-> {
                    FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showOpenDialog(primaryStage);
                    try (Scanner scan = new Scanner(file)) {
                        int num = 0;
                        while (scan.hasNextLine()) {
                            num++;
                            try {
                                String[] line = scan.nextLine().split(",");

                                Main.booksList.add(new Book(Integer.parseInt(line[0]),line[1],line[2],line[3],
                                        Integer.parseInt(line[4]),line[5]));
                            }catch (AlertException ex){
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Read File");
                                alert.setHeaderText(null);
                                alert.setContentText("The dublicate in line " + num + ".");
                                alert.showAndWait();
                            }catch (Exception ex1) {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Read File");
                                alert.setHeaderText(null);
                                alert.setContentText("Wrong format in line " + num + ".");
                                alert.showAndWait();
                            }

                        }
                        Main.booksList.sort();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Read File");
                        alert.setHeaderText(null);
                        alert.setContentText("The data has been read successfully.");
                        alert.showAndWait();
                    }catch (Exception ex) {
                    }

                });

                buttons[3].setOnAction(e-> {
                    primaryStage.setTitle("View tab");
                    primaryStage.setScene(new Scene(new ViewTab(primaryStage)));
                });

                buttons[2].setOnAction(e-> {
                    primaryStage.setTitle("Add tab");
                    primaryStage.setScene(new Scene(new AddTab(primaryStage)));
                });

                ImageView imageView = new ImageView(new Image("book.png"));

                setTop(buttons[2]);
                setLeft(buttons[0]);
                setRight(buttons[1]);
                setBottom(buttons[3]);
                setCenter(imageView);

                for (int i = 0; i < 4; i++)
                    BorderPane.setAlignment(buttons[i], Pos.CENTER);

            }

            public static void styling(Button[] buttons,int textSize) {
                //To style the buttons
                for (Button button : buttons) {
                    button.setStyle(
                            "-fx-background-color: #3498db; " + // Blue background
                                    "-fx-text-fill: white; " +          // White text
                                    "-fx-font-size: "+textSize+"px; " +           // Font size
                                    "-fx-font-weight: bold; " +        // Bold text
                                    "-fx-padding: 10px 20px; " +       // Padding
                                    "-fx-border-radius: 0; " +         // Sharp corners
                                    "-fx-background-radius: 0; " +     // Sharp background
                                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 2, 0, 0, 1);" // Subtle shadow
                    );

                    button.setOnMouseEntered(e -> button.setStyle(
                            "-fx-background-color: #2980b9; " + // Darker blue on hover
                                    "-fx-text-fill: white; " +
                                    "-fx-font-size: "+textSize+"px; " +
                                    "-fx-font-weight: bold; " +
                                    "-fx-padding: 10px 20px; " +
                                    "-fx-border-radius: 0; " +
                                    "-fx-background-radius: 0; " +
                                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 2, 0, 0, 1);"
                    ));

                    // Revert to original style on exit
                    button.setOnMouseExited(e -> button.setStyle(
                            "-fx-background-color: #3498db; " +
                                    "-fx-text-fill: white; " +
                                    "-fx-font-size: "+textSize+"px; " +
                                    "-fx-font-weight: bold; " +
                                    "-fx-padding: 10px 20px; " +
                                    "-fx-border-radius: 0; " +
                                    "-fx-background-radius: 0; " +
                                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 2, 0, 0, 1);"
                    ));

                    // Pressed effect
                    button.setOnMousePressed(e -> button.setStyle(
                            "-fx-background-color: #1c5980; " + // Even darker blue when pressed
                                    "-fx-text-fill: white; " +
                                    "-fx-font-size: "+textSize+"px; " +
                                    "-fx-font-weight: bold; " +
                                    "-fx-padding: 10px 20px; " +
                                    "-fx-border-radius: 0; " +
                                    "-fx-background-radius: 0; " +
                                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 2, 0, 0, 1);"
                    ));

                    // Revert to hover style on release
                    button.setOnMouseReleased(e -> button.setStyle(
                            "-fx-background-color: #2980b9; " +
                                    "-fx-text-fill: white; " +
                                    "-fx-font-size: "+textSize+"px; " +
                                    "-fx-font-weight: bold; " +
                                    "-fx-padding: 10px 20px; " +
                                    "-fx-border-radius: 0; " +
                                    "-fx-background-radius: 0; " +
                                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 2, 0, 0, 1);"
                    ));
                }
            }

    }


