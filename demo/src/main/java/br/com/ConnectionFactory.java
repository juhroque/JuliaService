package br.com;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    /**
     * O método conecta com a database MySQL, e
     * @return conexão com o banco de dados juliaservice. 
     */

    public Connection recuperarConexao() {
        try {
            
            //String de conexão:
            Properties props = new Properties();
            props.load(new FileInputStream("C://Users//julia//OneDrive//Área de Trabalho//workspace//java//projetinhos//JuliaService//demo//src//main//java//br//com//config.properties"));
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/juliaservice", user, password);

        } catch(SQLException e){
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    
}
