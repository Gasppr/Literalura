package br.com.gaspp.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class Book {
     String title;
    List<AuthorData> authors;
    List<String> languages;
    Double downloadNumber;

    public Book(BookData data) {
        this.title = data.title();
        this.authors = data.authors();
        this.languages = data.languages();
        this.downloadNumber = data.downloadNumber();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorData> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorData> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Double getDownloadNumber() {
        return downloadNumber;
    }

    public void setDownloadNumber(Double downloadNumber) {
        this.downloadNumber = downloadNumber;
    }
}
