package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String URL = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void inicializarBancoDeDados() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            String sqlUsuario = "CREATE TABLE IF NOT EXISTS Usuario (" +
                    "email VARCHAR(255) PRIMARY KEY, " +
                    "senha VARCHAR(255) NOT NULL, " +
                    "nome VARCHAR(255), " +
                    "tipoUsuario VARCHAR(50))";
            stmt.execute(sqlUsuario);

            String sqlPost = "CREATE TABLE IF NOT EXISTS Post (" +
                    "titulo VARCHAR(255), " +
                    "conteudo TEXT, " +
                    "dataPublicacao DATE, " +
                    "usuarioEmail VARCHAR(255), " +
                    "FOREIGN KEY (usuarioEmail) REFERENCES Usuario(email))";
            stmt.execute(sqlPost);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
