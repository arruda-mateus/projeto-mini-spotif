package projeto_spotify;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import projeto_spotify.Mideas.Midea;

public class Catalogo {
    public static ArrayList<Midea> catalogoMideas = new ArrayList<>();

    public static void adicionarMideaNoCatalogo(Midea midea){
        catalogoMideas.add(midea);
    }

    public static Midea verificarSeMideaEstaNaLista(String tituloMidea){
        for (Midea midea: catalogoMideas){
            if (midea.titulo.equals(tituloMidea)){
                return midea;
            }
        }
        return null;
    }

    public static void listarTodasMideasDoCatalogo() {
        if (catalogoMideas.isEmpty()) {
            System.out.println("A playlist está vazia!");
        } else {
            for (int i = 0; i < catalogoMideas.size(); i++) {
                Midea m = catalogoMideas.get(i);
                System.out.println((i + 1) + " - " + m.titulo 
                                   + " | Artista: " + m.artista 
                                   + " | Gênero/Tipo: " + m.generoOuTipo 
                                   + " | Duração: " + m.duracaoSegundos + "s");
            }
        }
    }

    public static void pesquisarMideaNoCatalogo(Scanner sc) {
        if (catalogoMideas.isEmpty()) {
            System.out.println("A playlist está vazia!");
        } else {
            System.out.println("Pesquisar por: 1-Título | 2-Genero/Tipo | 3-Artista");

            int opcao = 0;
            try {
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Use apenas números.");
                sc.nextLine();
                return;
            }

            System.out.println("Digite o termo de pesquisa:");
            String termo = sc.nextLine().toLowerCase();

            boolean encontrou = false;

            for (Midea m : catalogoMideas) {
                switch (opcao) {
                    case 1 -> {
                        if (m.titulo.toLowerCase().contains(termo)) {
                            System.out.println("• " + m.titulo + " - " + m.artista + " (" + m.duracaoSegundos + "s) [" + m.generoOuTipo + "]");
                            encontrou = true;
                        }
                    }
                    case 2 -> {
                        if (m.generoOuTipo.toString().toLowerCase().contains(termo)) {
                            System.out.println("• " + m.titulo + " - " + m.artista + " (" + m.duracaoSegundos + "s) [" + m.generoOuTipo + "]");
                            encontrou = true;
                        }
                    }
                    case 3 -> {
                        if (m.artista.toLowerCase().contains(termo)) {
                            System.out.println("• " + m.titulo + " - " + m.artista + " (" + m.duracaoSegundos + "s) [" + m.generoOuTipo + "]");
                            encontrou = true;
                        }
                    }
                    default -> System.out.println("Opção inválida!");
                }
            }

            if (!encontrou) {
                System.out.println("Nenhuma mídia encontrada para esse critério.");
            }
        }
    }
}
