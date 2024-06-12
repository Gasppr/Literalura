package br.com.gaspp.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BooksResult(
        @JsonAlias("count") int bookQtd,
        @JsonAlias("results") List<BookData> bookDataList) {

}