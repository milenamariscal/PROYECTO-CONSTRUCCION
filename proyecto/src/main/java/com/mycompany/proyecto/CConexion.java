/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class CConexion {
    Connection conectar = null;
    String usuario = "root";
    String contrasenia = "123456789";
    String bd = "proyecto_construccion";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;
    
    public Connection estableceConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver"); // Corrección aquí
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            JOptionPane.showMessageDialog(null, "Se conectó a la base de datos correctamente");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se conectó a la base de datos. Error: " + e.toString());
        }
        return conectar;
    }
}