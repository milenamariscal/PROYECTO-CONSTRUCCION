/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CapaDatos;

/**
 *
 * @author Ena
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear una instancia de la clase Proyecto
        CD_Usuario usuario = new CD_Usuario();
        
        // Llamar al método insertarUsuario con valores de ejemplo
        //usuario.crearUsuario("john_doe", "john.doe@example.com", "password123",1);
        
        usuario.loginUsuario("john_doe", "password123");
         
    }
    
}
