package com.example.project1;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Book implements Comparable<Book> {

    private SimpleIntegerProperty id= new SimpleIntegerProperty();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleStringProperty author = new SimpleStringProperty();
    private SimpleStringProperty category= new SimpleStringProperty();
    private SimpleIntegerProperty publishedYear = new SimpleIntegerProperty();
    private SimpleStringProperty iSBN = new SimpleStringProperty();

    public Book(int id, String title, String author, String category, int publishedYear, String ISBN) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        setCategory(category);
        setPublishedYear(publishedYear);
        setiSBN(ISBN);
    }


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        if (id < 0)
            throw new AlertException("id cannot be negative");
        duplicatedID(id);
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public int getPublishedYear() {
        return publishedYear.get();
    }

    public SimpleIntegerProperty publishedYearProperty() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        Calendar c = new GregorianCalendar();
        if(publishedYear <= c.get(Calendar.YEAR))
             this.publishedYear.set(publishedYear);
        else
            throw new AlertException("publishedYear cannot be greater than current year");
    }

    public String getiSBN() {
        return iSBN.get();
    }

    public SimpleStringProperty iSBNProperty() {
        return iSBN;
    }

    public void setiSBN(String iSBN) {
//        to make sure it start with 3 digits then - then any number of digits\
        if(iSBN.matches("^\\d{3}-\\d+$")) {
            duplicatedISBN(iSBN);
            this.iSBN.set(iSBN);
        }else
            throw new AlertException("Invalid ISBN, the right format is: 123-12345.....");
    }

    //To check if there is another book with the same id
    public void duplicatedID(int id) {
        for (int i = Main.booksList.size()-1; i >= 0 ; i--) {
            if(Main.booksList.get(i).getId() < id) {
                break;
            }
            if (Main.booksList.get(i).getId() == id) {
                throw new AlertException("This ID already exists.");
            }
        }
    }

    //To check if there is another book with the same iSBN
    public void duplicatedISBN(String iSBN) {
        for (int i = Main.booksList.size()-1; i >= 0 ; i--) {
            if (Main.booksList.get(i).getiSBN().equalsIgnoreCase(iSBN)) {
                throw new AlertException("This ISBN already exists.");
            }
        }
    }

    public int compareTo(Book b) {
        return this.getId() - b.getId();
    }

    public String toString() {
        return id.get()+","+title.get()+","+author.get()+","+category.get()+","+publishedYear.get()+","+ iSBN.get();
    }
}
