package com.co.castano.usuarios.controller;

import com.co.castano.usuarios.configuration.UsuariosConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuariosController {

    @Autowired
    private UsuariosConfiguration usuariosConfiguration;  // Inyectamos la clase de configuración

    // Endpoint para acceder a la propiedad 'value' mapeada desde el Config Server
    @GetMapping("/endpoint")
    public String retrieveLimits() {
        return "Value: " + usuariosConfiguration.getValue();  // Devuelve la propiedad configurada
    }
}

