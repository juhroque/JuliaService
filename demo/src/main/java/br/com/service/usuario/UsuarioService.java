package br.com.service.usuario;

import java.sql.Connection;
import java.util.Set;

import br.com.service.ConnectionFactory;

public class UsuarioService {
    
    private ConnectionFactory connection;
    
    public UsuarioService(){
        this.connection = new ConnectionFactory();
    }

    public void cadastrarNovoUsuario(DadosCadastroUsuario dadosCadastroUsuario){
        //Abre a conexão usando o factory de atributo:
        Connection conn = connection.recuperarConexao();

        //Um usuarioDAO é criado com a conexão, e um usuário é criado passando os dados de parâmetro
        new UsuarioDAO(conn).salvarNovoUsuario(dadosCadastroUsuario);
    }

    public Set<Usuario> listarUsuarios(){
        Connection conn = connection.recuperarConexao();

        //Um usuarioDAO é criado com a conexão, e o método que retorna um set de usuários encontrados é chamado
        return new UsuarioDAO(conn).listarUsuariosSalvos();
    }

    public boolean autenticarUsuario(String username, String senha){
        Connection conn = connection.recuperarConexao();

        //Um usuarioDAO é criado com a conexão, e o método que autentica o usuário é chamado
        return new UsuarioDAO(conn).autenticacao(username, senha);
    }

    public Usuario encontrarUsuario(String username){
        Connection conn = connection.recuperarConexao();

        //Um usuarioDAO é criado com a conexão, e o método que retorna um usuário é chamado
        return new UsuarioDAO(conn).encontrarUsuario(username);
    }

    public void alterarSenhaDoUsuario(String username, String novaSenha){
        Connection conn = connection.recuperarConexao();

        //Um usuarioDAO é criado com a conexão, e o método que altera a senha do usuário é chamado
        new UsuarioDAO(conn).alterarSenha(username, novaSenha);
    }

    public void alterarPlanoDoUsuario(String username, String novoPlano){
        Connection conn = connection.recuperarConexao();

        //Um usuarioDAO é criado com a conexão, e o método que altera o plano do usuário é chamado
        new UsuarioDAO(conn).alterarPlano(username, novoPlano);
    }




}
