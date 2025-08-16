package com.autentication.oauth2.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {

    @GetMapping("/public")
    String publicRouter() {
        return "<h1> Rota publica, sinta-se livre para olhar </h1>";
    }

    @GetMapping("/private")
    String privateRouter(@AuthenticationPrincipal OidcUser principal ) {return String.format("<h1>Rota Privada, acesso negado!</h1>" +
                    "<h3>Principal:  %s</h3>" +
                    "<h3>Email: %s</h3>" +
                    "<h3>Authorities: %s </h3>" +
                    "<h3>JWT: %s </h3>",
                principal,
                principal.getAttribute("email"),
                principal.getAuthorities(),
                principal.getIdToken().getTokenValue()
        );
    }

    //Rota para trazer o JWT que foi gerado no Google
    @GetMapping("/jwt")
    String jwtRouter(@AuthenticationPrincipal Jwt jwt) {
        return String.format("<h1>JWT</h1>" +
                        "<h3>Principal:  %s</h3>" +
                        "<h3>Email: %s</h3>" +
                        "<h3>JWT: %s </h3>",
                jwt.getClaims(),
                jwt.getClaim("email"),
                jwt.getTokenValue());
    }
}

