package br.com.service.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

public class UsuarioDAO {
    
    private Connection connection;

    public UsuarioDAO(Connection conn){
        this.connection = conn;
    }

    public void salvarNovoUsuario(DadosCadastroUsuario dadosUsuario){

        //Criar uma nova instância de usuário passando os dados recebidos como parâmetro:
        var usuario = new Usuario(dadosUsuario);

        //Prompt SQL:
        String sql = "INSERT INTO usuarios (primeiroNome, username, email, senha, plano)" +
                    "VALUES( ?, ?, ?, ?, ?)";

        try {
            //Usa o método da conexão de preparar a string para SQL, que retorna um objeto PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Preparando os valores para serem passados na query SQL:
            //Os valores são adicionados conforme (indexDoParâmetro, informaçãoASerAdicionada)
            preparedStatement.setString(1, usuario.getPrimeiroNome());
            preparedStatement.setString(2, usuario.getUsername());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getSenha());
            preparedStatement.setString(5, usuario.getPlano());

            //A query preparada é executada:
            preparedStatement.execute();

            //A conexão é fechada:
            connection.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Set<Usuario> listarUsuariosSalvos(){

        //Cria referências dos tipos PS e ResultSet
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        
        //Criação de set que vai armazenar os usuários salvos:
        Set<Usuario> usuarios = new HashSet<>();

        //Query:
        String sql = "SELECT * FROM usuarios";

        try {
            preparedStatement = connection.prepareStatement(sql);
            //ResultSet é o set retornado da execução da query
            resultSet = preparedStatement.executeQuery();

            //Enquanto o resultSet ainda for iterável (não ter chegado ao fim)
            while (resultSet.next()){
                String primeiroNome = resultSet.getString(1);
                String username = resultSet.getString(2);
                String email = resultSet.getString(3);
                String senha = resultSet.getString(4);
                String plano = resultSet.getString(5);
                
                //Cria o record com os dados e depois cria o usuário com os mesmos
                DadosCadastroUsuario dadosCadastroUsuario = new DadosCadastroUsuario(primeiroNome, username, email, senha, plano);
                Usuario usuario = new Usuario(dadosCadastroUsuario);

                //Adiciona o usuário ao set
                usuarios.add(usuario);
            }
            connection.close();
            resultSet.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return usuarios;
    }

    public Usuario encontrarUsuario(String username){
        String sql = "SELECT * FROM usuarios WHERE username = ?";

        ResultSet resultSet;
        Usuario usuario = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String primeiroNome = resultSet.getString(1);
                String usernameEncontrado = resultSet.getString(2);
                String email = resultSet.getString(3);
                String senha = resultSet.getString(4);
                String plano = resultSet.getString(5);

                DadosCadastroUsuario dadosCadastroUsuario = new DadosCadastroUsuario(primeiroNome, usernameEncontrado, email, senha, plano);

                usuario = new Usuario(dadosCadastroUsuario);
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return usuario;
    }

    public boolean autenticacao(String username, String senha){
        String sql = "SELECT * FROM usuarios WHERE username = ? AND senha = ?";

        ResultSet resultSet;
        Usuario usuario = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, senha);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String primeiroNome = resultSet.getString(1);
                String usernameEncontrado = resultSet.getString(2);
                String email = resultSet.getString(3);
                String senhaEncontrada = resultSet.getString(4);
                String plano = resultSet.getString(5);

                DadosCadastroUsuario dadosCadastroUsuario = new DadosCadastroUsuario(primeiroNome, usernameEncontrado, email, senhaEncontrada, plano);

                usuario = new Usuario(dadosCadastroUsuario);
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        if(usuario == null){
            System.out.println("Usuário ou senha incorreto(s).");
            return false;
        } else {
            System.out.println("Autenticado! Seja bem-vindo, " + usuario.getPrimeiroNome() + "!");
            System.out.println("Seu plano é: " + usuario.getPlano() + ".");
            return true;
        }
    }

    public void alterarSenha(String username, String novaSenha){
        String sql = "UPDATE usuarios SET senha = ? WHERE username = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, novaSenha);
            preparedStatement.setString(2, username);

            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        System.out.println("Senha alterada com sucesso!");
    }

    public void alterarPlano(String username, String novoPlano){
        String sql = "UPDATE usuarios SET plano = ? WHERE username = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, novoPlano);
            preparedStatement.setString(2, username);

            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        System.out.println("Plano alterado com sucesso!");
    }
}
