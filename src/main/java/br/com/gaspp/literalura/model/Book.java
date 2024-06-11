package br.com.gaspp.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Book(@JsonAlias("title") String title,
                   @JsonAlias("authors") List<Author> authors,
                   @JsonAlias("Languages") List<String> languages,
                   @JsonAlias("download_count") Double downloadNumber
                    ) {

}
