package com.gabriel.tela.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private  static  final  String URL = "jdbc:mysql://localhost:3306/estudante";
    private static final  String USUARIO = "root";
    private static final  String SENHA = "140827gdJ@";
    public static void fecharConexao(Connection connection){
        try {
            if (connection != null && !connection.isClosed());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection connection () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL,USUARIO,SENHA);
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("ERRO NO SQL");
        }

    }
}
