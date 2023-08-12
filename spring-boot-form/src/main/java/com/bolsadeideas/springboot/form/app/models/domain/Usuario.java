package com.bolsadeideas.springboot.form.app.models.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Usuario {
    private String identificador;

    @NotEmpty(message = "El nombre no puede ser vacío")
    private String nombre;
    @NotEmpty(message = "El apellido no puede ser vacío")
    private String apellido;
    @NotEmpty(message = "El nombre de usuario no puede ser vacío")
    @Size(min = 3, max = 8, message = "El nombre de usuario debe tener una extensión de entre 3 y 8 caracteres")
    private String username;
    @NotEmpty (message = "La contraseña no puede ser vacía")
    private String password;
    @NotEmpty(message = "El email no puede ser vacío")
    @Email(message = "Digite una dirección de correo válida")
    private String email;

    public Usuario() {
    }

    public Usuario(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
}