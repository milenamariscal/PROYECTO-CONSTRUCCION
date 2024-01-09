/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;
import CapaDatos.CD_Usuario;

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

    // Constructor
    
    public CN_Usuario(){};
    public CN_Usuario(String cedula, String nombre, String apellido, String username, String password, String mail, char estado) {
      
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.estado = estado;
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
    
    //Llamado del metodo RegistrarUsuario
    
    public void crearUsuario(String nombre, String apellido, String password, String email, String username){
        cdUsuario.crearUsuario(nombre, apellido, username, email, password);
    }
    
    public void loginUsuario(String username, String password){
        cdUsuario.loginUsuario(username, password);
        
    }
}
       
