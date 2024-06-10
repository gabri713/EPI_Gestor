/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author vitor
 */
public class conecxao {


public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/nome_do_banco"; 
    private static final String USER = "seu_usuario"; 
    private static final String PASSWORD = "sua_senha"; 

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    
}
}
}

