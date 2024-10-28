/* package test;

import config.ConnectionFactory;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
            connection.close();
        } catch (Exception e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }
    }
}
 */