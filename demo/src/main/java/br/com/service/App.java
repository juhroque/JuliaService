package br.com.service;

import java.util.Scanner;
import java.util.Set;

import br.com.service.usuario.DadosCadastroUsuario;
import br.com.service.usuario.Usuario;
import br.com.service.usuario.UsuarioService;

public class App 
{

    private static UsuarioService service = new UsuarioService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main( String[] args )
    {
        primeiraSelecao();
    }

    public static void primeiraSelecao(){
        System.out.println("Bem-vindo ao sistema de usuários Júlia, o JuliaService!");
        var opcaoSelecionada = exibirMenu();
        while(opcaoSelecionada != 0){
            switch(opcaoSelecionada){
                case 1:
                    cadastro();
                    break;
                case 2:
                    var autenticou = login();
                    if (autenticou){
                        segundaSelecao();
                    } else {
                        System.out.println("Tente novamente!");
                    }
                    break;
                case 3:
                    listagem();
                    break;
            }
            opcaoSelecionada = exibirMenu();
        }
    }

    public static void segundaSelecao(){
        var segundaOpcaoSelecionada = exibirOpcoesLogado();
        while (segundaOpcaoSelecionada != 0){
            switch(segundaOpcaoSelecionada){
                case 1:
                    System.out.println("Você saiu da sua conta.");
                    primeiraSelecao();
                    break;
                case 2:
                    alterarSenha();
                    break;
                case 3:
                    alterarPlano();
                    break;
                case 5:
                    visualizarDados();
                    break;
            }
            if (segundaOpcaoSelecionada != 1 && segundaOpcaoSelecionada != 0){
                segundaOpcaoSelecionada = exibirOpcoesLogado(); 
            }
        }
    }

    private static int exibirMenu(){
        System.out.println("""
                    Escolha uma opção abaixo:
                    0 - Finalizar o programa
                    1 - Cadastrar um novo usuário
                    2 - Logar em uma conta
                    3 - Visualizar os usuários cadastrados
                    """);
        var retorno = scanner.nextLine();
        return Integer.parseInt(retorno);
    }

    private static int exibirOpcoesLogado(){
        System.out.println("""
                    Escolha uma opção abaixo: 
                    0 - Finalizar o programa
                    1 - Sair da sua conta
                    2 - Alterar sua senha
                    3 - Alterar seu plano
                    4 - Excluir sua conta
                    5 - Visualizar seus dados
                    6 - Acessar o app
                            """);
        var retorno = scanner.nextLine();
        return Integer.parseInt(retorno);
    }

    private static void cadastro(){
        System.out.println("Digite o seu primeiro nome: ");
        var primeiroNome = scanner.nextLine();

        System.out.println("Digite seu email: ");
        var email = scanner.nextLine();

        System.out.println("Digite o seu nome de usuário: ");
        var username = scanner.nextLine();

        System.out.println("Digite uma senha: ");
        var senha = scanner.nextLine();

        System.out.println("Selecione um plano [N - Normal | G - Gold | P - Premium ]");
        var plano = scanner.nextLine();

        service.cadastrarNovoUsuario(new DadosCadastroUsuario(primeiroNome, username, email, senha, plano));
        System.out.println("Usuário cadastrado com sucesso no banco de dados.");
        System.out.println("Digite qualquer tecla e dê enter para continuar na aplicação: ");
        scanner.nextLine();
    }

    private static void listagem(){
        System.out.println("Usuarios cadastrados: ");
        Set<Usuario> usuarios = service.listarUsuarios();
        for(Usuario usuario : usuarios){
            System.out.println("User: " + usuario.getUsername());
            System.out.println("Plano: " + usuario.getPlano().toUpperCase());
            System.out.println();
        }
        System.out.println("Digite qualquer tecla e dê enter para continuar na aplicação: ");
        scanner.nextLine();
    }

    private static boolean login(){
        System.out.println("Digite o usuário: ");
        var username = scanner.nextLine();

        System.out.println("Digite a senha: ");
        var senha = scanner.nextLine();
        return service.autenticarUsuario(username, senha);
    }


    private static void alterarSenha(){
        System.out.println("Digite a nova senha:");
        var novaSenha = scanner.nextLine();

        System.out.println("Confirme usando seu nome de usuário: ");
        var usernameLogado = scanner.nextLine();

        service.alterarSenhaDoUsuario(usernameLogado, novaSenha);
        System.out.println("Digite qualquer tecla e dê enter para continuar na aplicação: ");
        scanner.nextLine();
    }

    private static void alterarPlano(){
        System.out.println("Digite o novo plano: [N - Normal | G - Gold | P - Premium ]");
        var novoPlano = scanner.nextLine();

        System.out.println("Confirme usando seu nome de usuário: ");
        var usernameLogado = scanner.nextLine();

        service.alterarPlanoDoUsuario(usernameLogado, novoPlano);
        System.out.println("Digite qualquer tecla e dê enter para continuar na aplicação: ");
        scanner.nextLine();
    }

    private static void visualizarDados(){
        System.out.println("Confirme usando seu nome de usuário: ");
        var usernameLogado = scanner.nextLine();

        System.out.println(service.encontrarUsuario(usernameLogado));
        System.out.println("Digite qualquer tecla e dê enter para continuar na aplicação: ");
        scanner.nextLine();
    }
}