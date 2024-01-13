/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;
import CapaDatos.CD_Usuario;
import java.io.InputStream;


/**
 *
 * @author Ena
 */
public class CN_Usuario {
    private CD_Usuario cdUsuario = new CD_Usuario();

    // Atributos
    private int id;
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private String mail;
    private char estado;
    private String intereses;
    private byte[] foto;

    // Constructor
    
    public CN_Usuario(){};
    public CN_Usuario(String cedula, String nombre, String apellido, String username, String password, String mail, char estado, String intereses, byte[] foto) {
      
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.estado = estado;
        this.intereses = intereses;
        this.foto = foto;
    }

    // Métodos getter y setter para los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNombres() {
        return nombre;
    }

    public void setNombres(String nombres) {
        this.nombre = nombres;
    }

    public String getApellidos() {
        return apellido;
    }

    public void setApellidos(String apellidos) {
        this.apellido = apellidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public char isEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public String getIntereses() {
        return intereses;
    }

    public void setIntereses(String intereses) {
        this.intereses = intereses;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

 
    
    //Llamado del metodo RegistrarUsuario
    
    public void crearUsuario(String nombre, String apellido, String password, String email, String username, String intereses, byte[] foto){
        cdUsuario.crearUsuario(nombre, apellido, username, email, password, intereses, foto);
    }
    
    public boolean loginUsuario(String username, String password){
       return cdUsuario.loginUsuario(username, password);
    }
    
    public String[] obtenerDatosUsuario(String inputUsername) {
        return cdUsuario.obtenerDatosUsuario(inputUsername);
    }
    
    public boolean enviarSolicitudAmistad(int senderId, int receiverId){
        return cdUsuario.enviarSolicitudAmistad(senderId, receiverId);
    }
    
    public int obtenerUserIdPorUsername(String username){
        return cdUsuario.obtenerUserIdPorUsername(username);
    }
    
    public void publicarMensaje(int user_id, String content){
        cdUsuario.publicarMensaje(user_id, content);
    }
     
     
}
       
