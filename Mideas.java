package projeto_spotify;

import java.util.ArrayList;


public class Mideas {
    public static enum GeneroOuTipo {
        ROCK,
        POP,
        MPB,
        FORRO,
        PODCAST,
        AUDIOBOOK
    }

    public static class Midea{
        String titulo;
        GeneroOuTipo generoOuTipo;
        String artista;
        int duracaoSegundos;

        public Midea (String titulo, GeneroOuTipo generoOuTipo, String artista,  int duracaoSegundos) {
        this.titulo = titulo;
        this.generoOuTipo = generoOuTipo;
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
    }
    }

}


