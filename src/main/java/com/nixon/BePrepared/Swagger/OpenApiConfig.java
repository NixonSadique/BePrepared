package com.nixon.BePrepared.Swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "BePrepared",
                description = "App que permite que os usuarios se mantenham informados sobre os desastres naturais",
                version = "1.0",
                contact = @Contact(
                        name = "Nixon Sadique",
                        email = "nixonsadique2005@gmail.com",
                        url = "https://github.com/NixonSadique"
                ),
                license = @License(
                        name = "",
                        url = ""
                )
        ),
        security = {
                @SecurityRequirement(
                        name = "Bearer Authentication"
                )
        }
)
@SecurityScheme(
        name = "Bearer Authentication",
        description = "Faca o login na API, para poder usar a aplicacao BePrepared, coloque o seu token de autorizacao abaixo!!",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
