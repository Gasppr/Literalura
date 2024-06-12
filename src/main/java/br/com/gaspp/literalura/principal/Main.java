package br.com.gaspp.literalura.principal;

import br.com.gaspp.literalura.model.Book;
import br.com.gaspp.literalura.model.BookData;
import br.com.gaspp.literalura.repository.BookRepository;
import br.com.gaspp.literalura.service.DataConvert;
import br.com.gaspp.literalura.service.GetData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class Main {

    private DataConvert convert = new DataConvert();

    private Scanner sc = new Scanner(System.in);
    final String URL = "https://gutendex.com/books/";

    private Optional<Book> bookFound ;
    @Autowired
    private BookRepository repository;

    List<Book> bookList = new ArrayList<>();

    public Main(BookRepository repository){
        this.repository = repository;
    }


    public void run(){

        int op = -1  ;

        String mainText = """
                    1 - Buscar Livro pelo ID na Web
                    2 - Buscar Livro pelo titulo
                    3 - Listar livros registrados
                    4 - Listar autores registrados
                    5 - Listar autores vivos em um determinado ano
                    6 - Listar livros em um determinado idioma
                   
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
                        getBookByTitulo();
                        break;
                    case 3:
                        getAllBooks();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;

                }
            }


    }

    private void getAllBooks() {
        bookList = repository.findAll();
        bookList.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);

    }


    private void getDataWeb() {
      BookData data = SearchBook();
      Book book = new Book(data);

      repository.save(book);

      System.out.println(data);
    }

    private BookData SearchBook() {
        System.out.println("Digite o ID do livro:");
        var bookid = sc.nextInt();
        var json = GetData.getData(URL + bookid + "/");
        BookData data = convert.getData(json , BookData.class);

        return data;
    }

    private void getBookByTitulo() {

        getAllBooks();
        System.out.println("Digite o titulo do livro:");
        var title = sc.nextLine();
        bookFound = repository.findByTitleContainingIgnoreCase(title);

        if (bookFound.isPresent() ){
            System.out.println("Dados da série: " + bookFound.get());
        }else {
            System.out.println("Série não encontrada");
        }


    }

}
