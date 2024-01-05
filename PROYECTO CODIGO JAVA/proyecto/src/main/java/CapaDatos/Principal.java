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
        //usuario.crearUsuario("milena_mariscal", "mileena@example.com", "password321",1);
        
        //Llamar al metodo LoginUsuario con valores de ejemplo
        //usuario.loginUsuario("john_doe", "password123");
        
        
         //Llamar al metodo EnviarSolicitud yAceptarSolicitud 
         // Enviar solicitud de amistad
        //usuario.enviarSolicitudAmistad(2, 3);

        // Aceptar solicitud de amistad (suponiendo que ya hay una solicitud pendiente)
        //usuario.aceptarSolicitudAmistad(1);

         
        //Llamar al metodo PublicarMensaje con valores de ejemplo
        //usuario.publicarMensaje(3, "Este es un mensaje de prueba");
        
        //Llamar al metodo ComentarMensaje con valores de ejemplo
        //usuario.comentarMensaje(1, 3, "Contestacion del mensaje de prueba");
        
        //Llamar al metodo ObtenerMensajesForo con valores de ejemplo
        usuario.obtenerMensajesForo();
    }
    
}
