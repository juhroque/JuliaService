package br.com.service.usuario;

import java.sql.Connection;
import java.util.Set;

import br.com.ConnectionFactory;

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
        return new UsuarioDAO(conn).listarUsuariosSalvos();
    }




}
