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
}
