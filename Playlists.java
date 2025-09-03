package projeto_spotify;

import java.util.ArrayList;

import projeto_spotify.Mideas.Midea;
import projeto_spotify.Usuarios.Usuario;

public  class Playlists {
    
    public static class Playlist {
        String titulo;
        ArrayList<Midea> listaMideasPlaylist;


    public Playlist(String titulo) {
        this.titulo = titulo;
        this.listaMideasPlaylist = new ArrayList<>();
        }
    }

    public static boolean criarPlaylist(String titulo, Usuario usuario){
        
        for (Playlist playlist : usuario.playlistsDoUsuario){
            if (playlist.titulo.equals(titulo)){
                return false;

            }
        }
        Playlist novaPlaylist = new Playlist(titulo);
        usuario.playlistsDoUsuario.add(novaPlaylist);
        return true;
    }

    public static boolean excluirPlaylist(String titulo, Usuario usuario){
        for (Playlist playlist:usuario.playlistsDoUsuario){
            if(playlist.titulo.equals(titulo)){
                usuario.playlistsDoUsuario.remove(playlist);
                return true;
            }
        }
        return false;

    }

    public static Playlist pesquisarPlaylistNaLista(Usuario usuario, String titulo){
        for (Playlist playlist:usuario.playlistsDoUsuario){
            if(playlist.titulo.equals(titulo)){
                return playlist;
            }
        }
        return null;

    }

    
    public static boolean adicionarMideaNaPlaylist(Midea midea, Playlist playlist) {
        if (playlist.listaMideasPlaylist.contains(midea)){
            return false;
        }else{
            playlist.listaMideasPlaylist.add(midea);
            return true;
        }

    }

    public static Midea verificarSeMideaEstaNaPlaylist(Playlist playlist, String titulo){
        for (Midea midia: playlist.listaMideasPlaylist){
            if(midia.titulo.equals(titulo)){
                return midia;
            }
        }
        return null;

    }



    public static void imprimirPlaylist(Playlist playlist) {
    System.out.println("===== PLAYLIST: " + playlist.titulo + " =====");
    int duracaoTotal = 0;

    for (Midea m : playlist.listaMideasPlaylist) {
        System.out.println("• " + m.titulo + " - " + m.artista + " (" + m.duracaoSegundos + "s)");
        duracaoTotal += m.duracaoSegundos;
    }

    // Converte para minutos e segundos
    int minutos = duracaoTotal / 60;
    int segundos = duracaoTotal % 60;

    System.out.println("\nDuração total da playlist: " + minutos + "m " + segundos + "s");
    System.out.println("===============================");
}


}




