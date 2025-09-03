package projeto_spotify;

import java.util.ArrayList;

import projeto_spotify.Playlists.Playlist;

public class Usuarios {
    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public static class Usuario {
        String nome;
        String email;
        ArrayList<Playlist> playlistsDoUsuario = new ArrayList<>();
    }

    //Logar ou cadastrar usuário irá retornar o indice do usuário na lista de usuários.


    public static Usuario cadastrarUsuario(String nome, String email){
        for (Usuario usuario: listaUsuarios){
            if (usuario.email.equals(email)){
                return usuario;
            }
        }

        Usuario usuario = new Usuario();
        usuario.nome = nome;
        usuario.email = email;
        listaUsuarios.add(usuario);
        return usuario;
    }

}
