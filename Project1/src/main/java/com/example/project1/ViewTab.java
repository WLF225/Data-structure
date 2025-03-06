package com.example.project1;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.*;

public class ViewTab extends BorderPane {

    TableView<Book> bookTable;
    private ObservableList<Book> books = FXCollections.observableArrayList();

    public ViewTab(Stage primaryStage) {

        for (int i = 0; i < Main.booksList.size(); i++) {
            books.add(Main.booksList.get(i));
        }

        TextField tF = new TextField();
        tF.setFont(Font.font(50));
        tF.setPromptText("Search");

        Button[] buttons = {
                new Button("Search by ID"), new Button("Search by title"),
                new Button("Search by author"), new Button("Delete"),
                new Button("Edit Book"), new Button("Back"), new Button("Sort by ID"),
                new Button("Sort by title"), new Button("Sort by author"),
                new Button("Sort by Published Date"), new Button("Number of books by category"),
                new Button("Number of books by author"), new Button("Number of books in a year"),
                new Button("The year with most books"), new Button("The year with least books"),
                new Button("The author with most books"), new Button("The author with least books"),
                new Button("Is the author still active")};

        if (books.size() != 1) {
            buttons[3].setDisable(true);
            buttons[4].setDisable(true);
        }

        MainMenu.styling(buttons, 30);
        //to back for the Main menu
        buttons[5].setOnAction(e -> {
            primaryStage.setTitle("Main menu");
            primaryStage.setScene(new Scene(new MainMenu(primaryStage)));
        });

        HBox buttonHB = new HBox(50, buttons[0], buttons[1], buttons[2], buttons[3], buttons[4], buttons[5]);
        HBox buttonHB2 = new HBox(50, buttons[6], buttons[7], buttons[8], buttons[9]);
        VBox vBox = new VBox(50, tF, buttonHB, buttonHB2);
        buttonHB.setAlignment(Pos.CENTER);
        buttonHB2.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);
        setTop(vBox);

        bookTable = table(books);
        //To search the book according to the ID
        buttons[0].setOnAction(event -> {
            if (tF.getText().isEmpty()) {
                bookTable.setItems(books);
                buttons[3].setDisable(true);
                buttons[4].setDisable(true);
            } else {
                boolean cond = true;
                Book b = new Book(0, "", "", "", 0, "000-000000000");
                try {
                    //To search for the book with that title
                    for (Book book : books) {
                        if (book.getId() == Integer.parseInt(tF.getText())) {
                            b = book;
                            cond = false;
                            break;
                        }
                    }
                    if (cond) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Book Title");
                        alert.setHeaderText(null);
                        alert.setContentText("This book ID does not exist");
                        alert.showAndWait();
                    } else {
                        ObservableList<Book> books = FXCollections.observableArrayList(b);
                        buttons[3].setDisable(false);
                        buttons[4].setDisable(false);
                        bookTable.setItems(books);
                    }
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Book Title");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a valid ID");
                    alert.showAndWait();
                }
            }
        });
        //To search the book according to the title
        buttons[1].setOnAction(e -> {
            if (tF.getText().isEmpty()) {
                bookTable.setItems(books);
                buttons[3].setDisable(true);
                buttons[4].setDisable(true);
            } else {
                boolean cond = true;
                Book b = new Book(0, "", "", "", 0, "000-000000000");
                //To search for the book with that title
                for (Book book : books) {
                    if (book.getTitle().equalsIgnoreCase(tF.getText())) {
                        b = book;
                        cond = false;
                        break;
                    }
                }
                if (cond) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Book Title");
                    alert.setHeaderText(null);
                    alert.setContentText("This book title does not exist");
                    alert.showAndWait();
                } else {
                    ObservableList<Book> books = FXCollections.observableArrayList(b);
                    buttons[3].setDisable(false);
                    buttons[4].setDisable(false);
                    bookTable.setItems(books);
                }
            }
        });
        //To search the book according to the author
        buttons[2].setOnAction(e -> {
            if (tF.getText().isEmpty()) {
                bookTable.setItems(books);
                buttons[3].setDisable(true);
                buttons[4].setDisable(true);
            } else {
                boolean cond = true;
                Book b = new Book(0, "", "", "", 0, "000-000000000");
                //To search for the book with that title
                for (Book book : books) {
                    if (book.getAuthor().equalsIgnoreCase(tF.getText())) {
                        b = book;
                        cond = false;
                        break;
                    }
                }
                if (cond) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Book Title");
                    alert.setHeaderText(null);
                    alert.setContentText("This book author does not exist");
                    alert.showAndWait();
                } else {
                    ObservableList<Book> books = FXCollections.observableArrayList(b);
                    buttons[3].setDisable(false);
                    buttons[4].setDisable(false);
                    bookTable.setItems(books);
                }
            }
        });
        //To delete the book you searched for
        buttons[3].setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Book");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the book with id "
                    + bookTable.getItems().getFirst().getId() + " ?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Main.booksList.remove(bookTable.getItems().getFirst());
                books.remove(bookTable.getItems().getFirst());
                tF.setText("");
                bookTable.setItems(books);
                if (books.size() != 1) {
                    buttons[3].setDisable(true);
                    buttons[4].setDisable(true);
                }
            }
        });
        //To go for the edit tab for the book you searched for
        buttons[4].setOnAction(e -> {
            primaryStage.setTitle("Edit tab");
            primaryStage.setScene(new Scene(new EditTab(primaryStage, bookTable.getItems().getFirst())));
        });
        //To sort by the id
        buttons[6].setOnAction(e -> bookTable.setItems(books));
        //To sort by the title
        buttons[7].setOnAction(e -> {
            ObservableList<Book> sortedByTitle = FXCollections.observableArrayList(books);

            Collections.sort(sortedByTitle, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
            });
            bookTable.setItems(sortedByTitle);
        });
        //To sort by the author
        buttons[8].setOnAction(e -> {
            ObservableList<Book> sortedByAuthor = FXCollections.observableArrayList(books);
            Collections.sort(sortedByAuthor, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getAuthor().compareTo(o2.getAuthor());
                }
            });
            bookTable.setItems(sortedByAuthor);
        });
        //To sort by the year
        buttons[9].setOnAction(e -> {
            ObservableList<Book> sortedByDate = FXCollections.observableArrayList(books);
            Collections.sort(sortedByDate, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getPublishedYear() - o2.getPublishedYear();
                }
            });
            bookTable.setItems(sortedByDate);
        });
        setCenter(bookTable);

        //To count the number of books with the same category in the text field
        buttons[10].setOnAction(e -> {
            if (tF.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Text field is empty");
                alert.showAndWait();
                return;
            }
            int number = 0;
            for (int i = 0; i < Main.booksList.size(); i++) {
                if (Main.booksList.get(i).getCategory().equalsIgnoreCase(tF.getText()))
                    number++;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Category");
            alert.setHeaderText(null);
            alert.setContentText("The number of " + tF.getText() + " books is " + number);
            alert.showAndWait();
        });
        //To count the number of books written by the author in the text field
        buttons[11].setOnAction(e -> {
            if (tF.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Text field is empty");
                alert.showAndWait();
                return;
            }
            int number = 0;
            for (int i = 0; i < Main.booksList.size(); i++) {
                if (Main.booksList.get(i).getAuthor().equalsIgnoreCase(tF.getText()))
                    number++;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Author");
            alert.setHeaderText(null);
            alert.setContentText("The number of books written by " + tF.getText() + " is " + number);
            alert.showAndWait();
        });
        //To count the number of books published in the year in the text field
        buttons[12].setOnAction(e -> {
            if (tF.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Text field is empty");
                alert.showAndWait();
                return;
            }
            int number = 0;
            for (int i = 0; i < Main.booksList.size(); i++) {
                try {
                    if (Main.booksList.get(i).getPublishedYear() == Integer.parseInt(tF.getText()))
                        number++;
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("you should enter a valid year");
                    alert.showAndWait();
                    return;
                }

            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Year");
            alert.setHeaderText(null);
            alert.setContentText("The number of books published in " + tF.getText() + " is " + number);
            alert.showAndWait();

        });
        //To search for the year with the most books
        buttons[13].setOnAction(e -> {
            //To make sure it does not give an error
            if (Main.booksList.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No books found.");
                alert.showAndWait();
                return;
            }
            MyList<Book> sortedByDate = new MyList<>(Main.booksList);
            sortedByDate.sort(new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getPublishedYear() - o2.getPublishedYear();
                }
            });
            int year = sortedByDate.get(0).getPublishedYear();
            int number = 0;
            int maxYear = year;
            int max = 0;
            for (int i = 0; i < sortedByDate.size(); i++) {
                if (sortedByDate.get(i).getPublishedYear() == year) {
                    number++;
                } else {
                    if (number > max) {
                        max = number;
                        maxYear = year;
                    }
                    number = 1;
                    year = sortedByDate.get(i).getPublishedYear();
                }
            }
            if (number > max) {
                max = number;
                maxYear = year;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Year");
            alert.setHeaderText(null);
            alert.setContentText("The year with most books is " + maxYear + " with " + max + " books.");
            alert.showAndWait();
        });
        //To search for the year with the least books
        buttons[14].setOnAction(e -> {
            if (Main.booksList.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No books found.");
                alert.showAndWait();
                return;
            }
            MyList<Book> sortedByDate = new MyList<>(Main.booksList);
            sortedByDate.sort(new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getPublishedYear() - o2.getPublishedYear();
                }
            });
            int year = sortedByDate.get(0).getPublishedYear();
            int number = 0;
            int minYear = year;
            int min = 999999999;
            for (int i = 0; i < sortedByDate.size(); i++) {
                if (sortedByDate.get(i).getPublishedYear() == year) {
                    number++;
                } else {
                    if (number < min) {
                        min = number;
                        minYear = year;
                    }
                    number = 1;
                    year = sortedByDate.get(i).getPublishedYear();
                }
            }
            if (number < min) {
                min = number;
                minYear = year;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Year");
            alert.setHeaderText(null);
            alert.setContentText("The year with least books is " + minYear + " with " + min + " books.");
            alert.showAndWait();
        });
        //To search for the author with the most books
        buttons[15].setOnAction(e -> {
            if (Main.booksList.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No books found.");
                alert.showAndWait();
                return;
            }
            MyList<Book> sortedByAuthor = new MyList<>(Main.booksList);
            sortedByAuthor.sort(new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getAuthor().compareTo(o2.getAuthor());
                }
            });
            String author = sortedByAuthor.get(0).getAuthor();
            int number = 0;
            String maxAuthor = author;
            int max = 0;
            for (int i = 0; i < sortedByAuthor.size(); i++) {
                if (sortedByAuthor.get(i).getAuthor().equalsIgnoreCase(author)) {
                    number++;
                } else {
                    if (number > max) {
                        max = number;
                        maxAuthor = author;
                    }
                    number = 1;
                    author = sortedByAuthor.get(i).getAuthor();
                }
            }
            if (number > max) {
                max = number;
                maxAuthor = author;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Author");
            alert.setHeaderText(null);
            alert.setContentText("The author with most books is " + maxAuthor + " with " + max + " books.");
            alert.showAndWait();
        });
        //To search for the author with the least books
        buttons[16].setOnAction(e -> {
            if (Main.booksList.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No books found.");
                alert.showAndWait();
                return;
            }
            MyList<Book> sortedByAuthor = new MyList<>(Main.booksList);
            sortedByAuthor.sort(new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getAuthor().compareTo(o2.getAuthor());
                }
            });
            String author = sortedByAuthor.get(0).getAuthor();
            int number = 0;
            String minAuthor = author;
            int min = 999999999;
            for (int i = 0; i < sortedByAuthor.size(); i++) {
                if (sortedByAuthor.get(i).getAuthor().equalsIgnoreCase(author)) {
                    number++;
                } else {
                    if (number < min) {
                        min = number;
                        minAuthor = author;
                    }
                    number = 1;
                    author = sortedByAuthor.get(i).getAuthor();
                }
            }
            if (number < min) {
                min = number;
                minAuthor = author;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Author");
            alert.setHeaderText(null);
            alert.setContentText("The author with least books is " + minAuthor + " with " + min + " books.");
            alert.showAndWait();
        });

        buttons[17].setOnAction(e -> {
            if (tF.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Text field is empty");
                alert.showAndWait();
                return;
            }
            Calendar calendar = new GregorianCalendar();
            boolean cond = false;
            //To get if the author is active or not
            for (int i = 0; i < Main.booksList.size(); i++) {
                if (Main.booksList.get(i).getAuthor().equalsIgnoreCase(tF.getText()) && calendar.get(Calendar.YEAR) - Main.booksList.get(i).getPublishedYear() <= 5) {
                    cond = true;
                    break;
                }
            }
            //To convert the condition into string
            String s = (cond) ? "active" : "not active";
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Author");
            alert.setHeaderText(null);
            alert.setContentText(tF.getText() + " is " + s);
            alert.showAndWait();
        });

        HBox bottomHB = new HBox(50, buttons[10], buttons[11], buttons[12]);
        bottomHB.setAlignment(Pos.CENTER);
        HBox bottomHB2 = new HBox(50, buttons[13], buttons[14], buttons[15]);
        bottomHB2.setAlignment(Pos.CENTER);
        HBox bottomHB3 = new HBox(50, buttons[16], buttons[17]);
        bottomHB3.setAlignment(Pos.CENTER);
        VBox bottomVB = new VBox(50, bottomHB, bottomHB2, bottomHB3);
        bottomVB.setAlignment(Pos.CENTER);

        setBottom(bottomVB);
    }

    public TableView<Book> table(ObservableList<Book> list) {
        TableView<Book> tableView = new TableView<>(books);

        TableColumn<Book, SimpleIntegerProperty> idTC = new TableColumn<>("ID");
        idTC.setCellValueFactory(new PropertyValueFactory<>("id"));
        idTC.setStyle("-fx-font-size: 34px; " +
                "    -fx-alignment: CENTER;");
        idTC.setPrefWidth(200);

        TableColumn<Book, SimpleIntegerProperty> titleTC = new TableColumn<>("Title");
        titleTC.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleTC.setStyle("-fx-font-size: 34px; " +
                "    -fx-alignment: CENTER;");
        titleTC.setPrefWidth(450);

        TableColumn<Book, SimpleStringProperty> authorTC = new TableColumn<>("Author");
        authorTC.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorTC.setStyle("-fx-font-size: 34px; " +
                "    -fx-alignment: CENTER;");
        authorTC.setPrefWidth(300);

        TableColumn<Book, SimpleStringProperty> categoryTC = new TableColumn<>("Category");
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryTC.setStyle("-fx-font-size: 34px; " +
                "    -fx-alignment: CENTER;");
        categoryTC.setPrefWidth(300);

        TableColumn<Book, SimpleIntegerProperty> publishedYearTC = new TableColumn<>("Published Year");
        publishedYearTC.setCellValueFactory(new PropertyValueFactory<>("publishedYear"));
        publishedYearTC.setStyle("-fx-font-size: 34px; " +
                "    -fx-alignment: CENTER;");
        publishedYearTC.setPrefWidth(300);

        TableColumn<Book, SimpleStringProperty> iSBNTC = new TableColumn<>("ISBN");
        iSBNTC.setCellValueFactory(new PropertyValueFactory<>("iSBN"));
        iSBNTC.setStyle("-fx-font-size: 34px; " +
                "    -fx-alignment: CENTER;");
        iSBNTC.setPrefWidth(400);

        tableView.getColumns().addAll(idTC, titleTC, authorTC, categoryTC, publishedYearTC, iSBNTC);

        return tableView;
    }

}
