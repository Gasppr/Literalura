package br.com.gaspp.literalura.principal;

import br.com.gaspp.literalura.model.Author;
import br.com.gaspp.literalura.model.Book;
import br.com.gaspp.literalura.model.BooksResult;
import br.com.gaspp.literalura.repository.AuthorRepository;
import br.com.gaspp.literalura.repository.BookRepository;
import br.com.gaspp.literalura.service.DataConvert;
import br.com.gaspp.literalura.service.GetData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private DataConvert convert = new DataConvert();

    private Scanner sc = new Scanner(System.in);
    final String URL = "https://gutendex.com/books/";

    private Optional<Book> bookFound ;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    List<Book> bookList = new ArrayList<>();

    public Main(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    public void run(){

        int op = -1  ;

        String mainText = """
                    1 - Buscar Livro pelo titulo
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                   
                    0 - Sair
                """;


            while (op != 0){

                System.out.println(mainText);
                op = sc.nextInt();
                sc.nextLine();

                switch (op){
                    case 1:
                        getDataWeb();
                        break;
                    case 2:
                        getAllBooks();
                        break;
                    case 3 :
                        getAuthorBook();
                        break;
                    case 4 :
                        getAuthorAlive();
                        break;
                    case 5:
                        getBookByLanguage();
                        break;


                    case 0:
                        System.out.println("Saindo...");
                        break;

                }
            }


    }

    private void getBookByLanguage() {
        System.out.print("Escolha o idioma para procurar " +
                "\n- en," +
                "\n- pt," +
                "\n- br" +
                " \n");
        var language = sc.nextLine();

    }

    private void getAuthorAlive() {

        System.out.print("Escolha qual ano deseja buscar autores vivos: \n");
        var year = sc.nextInt();
        List<Author> authors = authorRepository.findByAuthorsAlive(year);

        if(!authors.isEmpty()) {
            authors.forEach(System.out::println);
        }else{
            System.out.printf("Nao existem livros com autores vivos.");
        }
    }

    private void getAuthorBook() {

        List<Author> authors = authorRepository.findAll();

        authors.forEach(System.out::println);
    }


    private void getAllBooks() {
        bookList = bookRepository.findAll();
        bookList.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);

    }


    private void getDataWeb() {
      BooksResult data = SearchBook();
      Book book = new Book(data);


      System.out.println(data);
    }

    private BooksResult SearchBook() {
        System.out.println("Digite o nome do livro:");
        var bookname = sc.nextLine();
        BooksResult booklistdata = null;
        BooksResult booksFoundResult = null;

        var verify = bookRepository.findByTitleContainingIgnoreCase(bookname);

        if(verify.isPresent()){
            System.out.println("\nLivro ja cadastrada no banco!");
        } else {
            var listJsonBooks = GetData.getData(URL + "?search=" + bookname.replace(" ", "+"));
            booklistdata = convert.getData(listJsonBooks, BooksResult.class);

            if(booklistdata.bookQtd() > 0){

                booklistdata.bookDataList().forEach(System.out::println);
                System.out.println("Digite o codigo do livro para busca");
                var bookCod = sc.nextLine();

                var jsonLivroEscolhido = GetData.getData(URL + "?ids=" + bookCod.trim());
                booksFoundResult = convert.getData(jsonLivroEscolhido, BooksResult.class);

                List<Author> authors = booksFoundResult.bookDataList().get(0).authors().stream()
                        .map(a-> new Author( a.name(), a.birth_year(), a.death_year()))
                        .collect(Collectors.toList());

                var b = booksFoundResult;
                b.bookDataList().get(0).authors().remove(0);

                var book = new Book(b);
                book.setAuthors(authors);
                bookRepository.save(book);

                System.out.println(booklistdata);
            }
        }
        return booksFoundResult;

    }



}
