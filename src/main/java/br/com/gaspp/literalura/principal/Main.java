package br.com.gaspp.literalura.principal;

import br.com.gaspp.literalura.service.GetData;

import java.lang.ref.SoftReference;
import java.util.Scanner;

public class Main {

    public void run(){
        Scanner sc = new Scanner(System.in);
        int op = -1  ;
        String url = "https://gutendex.com/books/?";
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

                switch (op){
                    case 1:
                        System.out.println("Cadastrar Livro");
                        break;
                    case 2:
                        System.out.println("Listar livros");
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;

                }
            }


    }
}
