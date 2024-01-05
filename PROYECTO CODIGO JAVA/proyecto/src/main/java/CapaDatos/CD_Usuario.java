/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Ena
 */
public class CD_Usuario {
    
    public void crearUsuario(String username, String email, String password, int rol) {
        // Obtener una conexión a la base de datos
        Connection conexion = ConexionBD.obtenerConexion();
        
        // Declarar un objeto PreparedStatement para ejecutar consultas preparadas
        PreparedStatement pst = null;

        try {
            // Definir la consulta SQL con un procedimiento almacenado llamado "InsertarUsuario"
            String sqlQuery = "CALL CrearUsuario(?, ?, ?, ?)";
            
            // Preparar la consulta con la conexión establecida
            pst = conexion.prepareStatement(sqlQuery);

            // Asignarle los parámetros a la consulta
            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setInt(4, rol);



            // Ejecutar la llamada al procedimiento almacenado
            pst.executeUpdate();

            // Mostrar un mensaje de éxito
            JOptionPane.showMessageDialog(null, "Usuario creado correctamente");

        } catch (SQLException e) {
            // En caso de excepción, mostrar un mensaje de error
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                // Cerrar el PreparedStatement para liberar recursos
                if (pst != null) {
                    pst.close();
                }
                
                // Cerrar la conexión a la base de datos
                ConexionBD.cerrarConexion();

            } catch (SQLException e) {
                // En caso de excepción al cerrar, mostrar un mensaje de error
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

 

    public boolean loginUsuario(String username, String password) {
        Connection conexion = ConexionBD.obtenerConexion();
        CallableStatement cst = null;

        try {
            String sqlQuery = "{CALL LoginUsuario(?, ?, ?)}";
            cst = conexion.prepareCall(sqlQuery);

            // Asignarle los parámetros
            cst.setString(1, username);
            cst.setString(2, password);
            cst.registerOutParameter(3, java.sql.Types.BIT);

            // Ejecutar la llamada al procedimiento almacenado
            cst.execute();

            // Obtener el resultado del procedimiento almacenado
            boolean loginExitoso = cst.getBoolean(3);

            if (loginExitoso) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales inválidas");
            }

            return loginExitoso;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false; // Manejo de error
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                }
                ConexionBD.cerrarConexion();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public boolean enviarSolicitudAmistad(int senderId, int receiverId) {
        Connection conexion = ConexionBD.obtenerConexion();
        CallableStatement cst = null;

        try {
            String sqlQuery = "{CALL EnviarSolicitudAmistad(?, ?)}";
            cst = conexion.prepareCall(sqlQuery);

            // Asignarle los parámetros
            cst.setInt(1, senderId);
            cst.setInt(2, receiverId);

            // Ejecutar la llamada al procedimiento almacenado
            cst.execute();

            JOptionPane.showMessageDialog(null, "Solicitud de amistad enviada con éxito");

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al enviar la solicitud de amistad: " + e.getMessage());
            return false;
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                }
                ConexionBD.cerrarConexion();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public boolean aceptarSolicitudAmistad(int requestId) {
        Connection conexion = ConexionBD.obtenerConexion();
        CallableStatement cst = null;

        try {
            String sqlQuery = "{CALL AceptarSolicitudAmistad(?)}";
            cst = conexion.prepareCall(sqlQuery);

            // Asignarle los parámetros
            cst.setInt(1, requestId);

            // Ejecutar la llamada al procedimiento almacenado
            cst.execute();

            JOptionPane.showMessageDialog(null, "Solicitud de amistad aceptada con éxito");

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al aceptar la solicitud de amistad: " + e.getMessage());
            return false;
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                }
                ConexionBD.cerrarConexion();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    public void publicarMensaje(int user_id, String content) {
        Connection conexion = ConexionBD.obtenerConexion();
        CallableStatement cst = null;

        try {
            String sqlQuery = "{CALL PublicarMensaje(?, ?)}";
            cst = conexion.prepareCall(sqlQuery);

            // Asignarle los parámetros
            cst.setInt(1, user_id);
            cst.setString(2, content);

            // Ejecutar la llamada al procedimiento almacenado
            cst.execute();

            // Mostrar un mensaje de éxito
            JOptionPane.showMessageDialog(null, "Mensaje publicado correctamente");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                }
                ConexionBD.cerrarConexion();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public void obtenerMensajesForo() {
        Connection conexion = ConexionBD.obtenerConexion();
        CallableStatement cst = null;
        ResultSet rs = null;

        try {
            String sqlQuery = "{CALL ObtenerMensajesForo()}";
            cst = conexion.prepareCall(sqlQuery);

            // Ejecutar la llamada al procedimiento almacenado
            rs = cst.executeQuery();

            // Imprimir los mensajes directamente desde ResultSet
            while (rs.next()) {
                int messageId = rs.getInt("message_id");
                int userId = rs.getInt("user_id");
                String content = rs.getString("content");
                // ... (obtener otros campos según la estructura de tu tabla)

                // Imprimir el mensaje
                System.out.println("Message ID: " + messageId + ", User ID: " + userId + ", Content: " + content);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener mensajes del foro: " + e.getMessage());
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                }
                if (rs != null) {
                    rs.close();
                }
                ConexionBD.cerrarConexion();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
}


