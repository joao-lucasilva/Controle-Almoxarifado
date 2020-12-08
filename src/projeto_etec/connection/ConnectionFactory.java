package projeto_etec.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection(){
        System.out.println("Conectando ao Banco de Dados");
        try {
            return
            DriverManager.getConnection
     ("jdbc:mysql://localhost/projetoEtec", "usuario", "etec123");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args){
        new ConnectionFactory().getConnection();
        System.out.println("Conexão criada com sucesso");
    }
}
