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
    private final String URL = "jdbc:mysql://localhost:/sistema";
        private final String USER = "root";
        private final String PASSWORD = "";
        
        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }
  
    // Outros métodos e lógica aqui
}

