package br.com;

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
        var opcaoSelecionada = exibirMenu();
        while(opcaoSelecionada != 0){
            switch(opcaoSelecionada){
                case 1:
                    cadastro();
                    break;
                case 3:
                    listagem();
                    break;
            }
            opcaoSelecionada = exibirMenu();
        }
    }

    private static int exibirMenu(){

        System.out.println("""
                    Bem-vindo ao serviço de usuarios Julia.
                    Escolha uma opção abaixo:
                    0 - Finalizar o programa
                    1 - Cadastrar um novo usuário
                    2 - Logar em uma conta
                    3 - Visualizar os usuários cadastrados
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
        System.out.println("Digite enter para continuar na aplicação: ");
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
        System.out.println("Digite enter para continuar na aplicação: ");
        scanner.nextLine();
    }

}
