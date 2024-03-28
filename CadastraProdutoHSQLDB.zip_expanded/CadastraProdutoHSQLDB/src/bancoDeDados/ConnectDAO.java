package bancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe para Implementar a conexão com o Banco de Dados.
 * 
 * Aplica o Single Responsibility Principle (SRP) ao
 * se concentrar exclusivamente em estabelecer conexões
 * com o banco de dados.
 */

public class ConnectDAO {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/Solid";
        String user = "postgres";
        String pwd = "postgres";
        Class.forName(driver);
        return DriverManager.getConnection(url, user, pwd);
    }
}