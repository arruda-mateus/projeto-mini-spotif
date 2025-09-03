package projeto_spotify;
import java.util.InputMismatchException;
import java.util.Scanner;

import projeto_spotify.Mideas.GeneroOuTipo;
import projeto_spotify.Mideas.Midea;
import projeto_spotify.Playlists.Playlist;


public class sistema {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcaoMenuInicial = -1;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Cadastrar usuário/Logar Usuário");
            System.out.println("2 - Listar todas as mideas do catálogo");
            System.out.println("3 - Buscar músicas por título, artista ou gênero");
            System.out.println("0 - Sair/Encerrar programa");

            while (true) {
                System.out.print("Escolha uma opção: ");
                try {
                    opcaoMenuInicial = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Digite um número.");
                    sc.nextLine();
                }
            }

            switch (opcaoMenuInicial) {
                case 1:
                    System.out.println("Digite seu nome:");
                    String nomeUsuario = sc.nextLine();

                    System.out.println("Digite seu email: ");
                    String emailUsuario = sc.nextLine();

                    Usuarios.Usuario usuarioLogado = Usuarios.cadastrarUsuario(nomeUsuario, emailUsuario);
                    System.out.println("\nBem-vindo!");

                    int opcaoMenuUsuario = -1;

                    do {
                        System.out.println("\nO que deseja fazer?");
                        System.out.println("1 - Criar uma playlist");
                        System.out.println("2 - Excluir uma playlist");
                        System.out.println("3 - Adicionar uma mídia em uma playlist");
                        System.out.println("4 - Remover uma mídia de uma playlist");
                        System.out.println("5 - Visualizar todas as suas playlists criadas");
                        System.out.println("6 - Visualizar suas mídias adicionadas a uma playlist sua");
                        System.out.println("0 - Deslogar e voltar ao menu inicial");

                        while (true) {
                            System.out.print("Escolha uma opção: ");
                            try {
                                opcaoMenuUsuario = sc.nextInt();
                                sc.nextLine();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Entrada inválida! Digite um número.");
                                sc.nextLine();
                            }
                        }

                        if (opcaoMenuUsuario == 1) {
                            System.out.print("Digite o título da sua playlist: ");
                            String titulo = sc.nextLine();
                            boolean playlistCriada = Playlists.criarPlaylist(titulo, usuarioLogado);
                            System.out.println(playlistCriada ? "Playlist criada com sucesso!" :
                                    "Não foi possível criar a playlist. Você já tem uma com esse título.");
                            continue;

                        } else if (opcaoMenuUsuario == 2) {
                            if (usuarioLogado.playlistsDoUsuario.size() == 0) {
                                System.out.println("Você não tem nenhuma playlist cadastrada no sistema ainda!");
                                continue;
                            }

                            System.out.print("Digite o título da sua playlist: ");
                            String titulo = sc.nextLine();
                            boolean playlistExcluida = Playlists.excluirPlaylist(titulo, usuarioLogado);
                            System.out.println(playlistExcluida ? "Playlist excluída com sucesso!" :
                                    "Não encontramos nenhuma playlist sua com esse título.");
                            continue;

                        } else if (opcaoMenuUsuario == 3) {
                            if (usuarioLogado.playlistsDoUsuario.size() == 0) {
                                System.out.println("Você não tem nenhuma playlist cadastrada no sistema ainda!");
                                continue;
                            }

                            System.out.println("Digite o título da playlist em que deseja adicionar uma mídia: ");
                            String titulo = sc.nextLine();
                            Playlist playlist = Playlists.pesquisarPlaylistNaLista(usuarioLogado, titulo);
                            if (playlist == null) {
                                System.out.println("Playlist não encontrada!");
                                continue;
                            }

                            System.out.println("Digite o título da mídia que deseja adicionar:");
                            String tituloMidea = sc.nextLine();
                            Midea mideaEncontrada = Catalogo.verificarSeMideaEstaNaLista(tituloMidea);
                            if (mideaEncontrada != null) {
                                boolean adicionou = Playlists.adicionarMideaNaPlaylist(mideaEncontrada, playlist);
                                System.out.println(adicionou ? "Mídia adicionada com sucesso!" :
                                        "Essa mídia já está na sua playlist.");
                                continue;
                            }

                            System.out.println("\nO que deseja adicionar?");
                            System.out.println("1 - Música");
                            System.out.println("2 - Podcast");
                            System.out.println("3 - Audiobook");

                            int opcao = -1;
                            while (true) {
                                System.out.print("Escolha uma opção: ");
                                try {
                                    opcao = sc.nextInt();
                                    sc.nextLine();
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Entrada inválida! Digite um número.");
                                    sc.nextLine();
                                }
                            }

                            GeneroOuTipo generoOuTipo = null;

                            if (opcao == 1) {
                                System.out.println("\nSelecione o gênero da sua música:");
                                System.out.println("1 - Forró");
                                System.out.println("2 - Rock");
                                System.out.println("3 - POP");
                                System.out.println("4 - MPB");

                                int opcaoGenero = -1;
                                while (true) {
                                    System.out.print("Escolha uma opção: ");
                                    try {
                                        opcaoGenero = sc.nextInt();
                                        sc.nextLine();
                                        break;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Entrada inválida! Digite um número.");
                                        sc.nextLine();
                                    }
                                }

                                switch (opcaoGenero) {
                                    case 1 -> generoOuTipo = GeneroOuTipo.FORRO;
                                    case 2 -> generoOuTipo = GeneroOuTipo.ROCK;
                                    case 3 -> generoOuTipo = GeneroOuTipo.POP;
                                    case 4 -> generoOuTipo = GeneroOuTipo.MPB;
                                    default -> {
                                        System.out.println("Opção inválida!");
                                        continue;
                                    }
                                }
                            } else if (opcao == 2) {
                                generoOuTipo = GeneroOuTipo.PODCAST;
                            } else if (opcao == 3) {
                                generoOuTipo = GeneroOuTipo.AUDIOBOOK;
                            }

                            System.out.println("Digite o nome do artista:");
                            String artista = sc.nextLine();

                            int duracao = 0;
                            while (true) {
                                System.out.print("Digite a duração da mídia em segundos: ");
                                try {
                                    duracao = sc.nextInt();
                                    sc.nextLine();
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Entrada inválida! Digite um número inteiro.");
                                    sc.nextLine();
                                }
                            }

                            Midea novaMidea = new Midea(tituloMidea, generoOuTipo, artista, duracao);
                            Catalogo.adicionarMideaNoCatalogo(novaMidea);
                            Playlists.adicionarMideaNaPlaylist(novaMidea, playlist);

                        } else if (opcaoMenuUsuario == 4) {
                            if (usuarioLogado.playlistsDoUsuario.size() == 0) {
                                System.out.println("Você não tem nenhuma playlist cadastrada no sistema ainda!");
                                continue;
                            }

                            System.out.println("Digite o título da playlist em que deseja excluir uma mídia: ");
                            String tituloPlaylist = sc.nextLine();
                            Playlist playlist = Playlists.pesquisarPlaylistNaLista(usuarioLogado, tituloPlaylist);
                            if (playlist == null) {
                                System.out.println("Playlist não encontrada!");
                                continue;
                            }

                            System.out.println("Digite o título da mídia que deseja excluir da sua playlist: ");
                            String tituloMidea = sc.nextLine();
                            Midea midiaASerExcluida = Playlists.verificarSeMideaEstaNaPlaylist(playlist, tituloMidea);
                            if (midiaASerExcluida != null) {
                                playlist.listaMideasPlaylist.remove(midiaASerExcluida);
                                System.out.println("Mídia excluída.");
                                continue;
                            }
                            System.out.println("Mídia não encontrada!");
                            continue;

                        } else if (opcaoMenuUsuario == 5) {
                            if (usuarioLogado.playlistsDoUsuario.size() == 0) {
                                System.out.println("Você não tem nenhuma playlist cadastrada no sistema ainda!");
                                continue;
                            }
                            System.out.println("===== SUAS PLAYLISTS =====");
                            for (Playlist p : usuarioLogado.playlistsDoUsuario) {
                                System.out.println("• " + p.titulo);
                            }
                            System.out.println("==========================");

                        } else if (opcaoMenuUsuario == 6) {
                            if (usuarioLogado.playlistsDoUsuario.size() == 0) {
                                System.out.println("Você não tem nenhuma playlist cadastrada no sistema ainda!");
                                continue;
                            }
                            System.out.println("Digite o título da playlist que deseja ver:");
                            String titulo = sc.nextLine();
                            Playlist playlist = Playlists.pesquisarPlaylistNaLista(usuarioLogado, titulo);
                            if (playlist != null){
                                Playlists.imprimirPlaylist(playlist);
                            }else{
                                System.out.println("Playlist nao encontrada");
                            }
                            continue;
                        }

                    } while (opcaoMenuUsuario != 0);

                    break;

                case 2:
                    Catalogo.listarTodasMideasDoCatalogo();
                    continue;

                case 3:
                    Catalogo.pesquisarMideaNoCatalogo(sc);
                    continue;

                case 0:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente!");
            }

        } while (opcaoMenuInicial != 0);

        sc.close();
    }
}
