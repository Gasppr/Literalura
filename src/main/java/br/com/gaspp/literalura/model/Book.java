package br.com.gaspp.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "book")
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    private List<String> languages;

    private Double downloadNumber;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Author> authors = new ArrayList<>();


    public Book(){}
    public Book(BookData data) {
        this.title = data.title();
        this.languages = data.languages();
        this.downloadNumber = data.downloadNumber();
    }


    public void setEpisodio(List<Author> authors) {
        authors.forEach(e -> e.setBook(this));
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
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
