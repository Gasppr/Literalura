package br.com.gaspp.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Author(String name,
                     Integer birth_year,
                     Integer death_year,
                     List<Book> Books
) {
}
