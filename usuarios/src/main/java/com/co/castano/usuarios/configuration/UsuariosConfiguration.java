package com.co.castano.usuarios.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("usuarios")// mismo nombre de su archivo properties
public class UsuariosConfiguration {

    private String value;  // Esta propiedad se mapea a usuarios.value

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
