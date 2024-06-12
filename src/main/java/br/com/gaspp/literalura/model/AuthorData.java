package br.com.gaspp.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorData(@JsonAlias("name") String name,
                         @JsonAlias("birth_year") Integer birth_year,
                         @JsonAlias("death_year") Integer death_year

) {
}
