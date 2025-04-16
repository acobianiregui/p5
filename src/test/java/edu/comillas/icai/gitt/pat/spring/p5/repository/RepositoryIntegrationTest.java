package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RepositoryIntegrationTest {
    @Autowired TokenRepository tokenRepository;
    @Autowired AppUserRepository appUserRepository;

    /**
     * TODO#9
     * Completa este test de integración para que verifique
     * que los repositorios TokenRepository y AppUserRepository guardan
     * los datos correctamente, y las consultas por AppToken y por email
     * definidas respectivamente en ellos retornan el token y usuario guardados.
     */
    @Test void saveTest() {
        // Given ...
        AppUser user = new AppUser();
        Token token = new Token();


        user.name="Pepe";
        user.password="Test9";
        user.email="pepe@gmail.com";
        user.role=Role.USER;

        token.appUser=user;
        appUserRepository.save(user);
        tokenRepository.save(token);

        //Guardamos y buscamos para ver si coinciden

        // When ...
        AppUser usuario= appUserRepository.findByEmail("pepe@gmail.com").orElse(null);
        Token guardado= tokenRepository.findById(token.id).orElse(null);
        Token token_usario = tokenRepository.findByAppUser(usuario).orElse(null);
        // Then ...
        Assertions.assertNotNull(usuario);
        Assertions.assertEquals(user.email, usuario.email);

        Assertions.assertNotNull(guardado);
        Assertions.assertEquals(user.id, guardado.appUser.id);

        Assertions.assertNotNull(token_usario);
        Assertions.assertEquals(token.id, token_usario.id);

    }

    /**
     * TODO#10
     * Completa este test de integración para que verifique que
     * cuando se borra un usuario, automáticamente se borran sus tokens asociados.
     */
    @Test void deleteCascadeTest() {
        // Given ...

        AppUser user = new AppUser();
        user.name="Pepe";
        user.password="Test9";
        user.email="pepe@gmail.com";
        user.role=Role.USER;
        appUserRepository.save(user);
        //Crear 2 tokens para probar
        Token token1 = new Token();
        token1.appUser=user;
        tokenRepository.save(token1);



        //Deberia haber ahora mismo 1 usuario y 2tokens
        Assertions.assertEquals(1, appUserRepository.count());
        Assertions.assertEquals(1, tokenRepository.count());

        // When ...
        appUserRepository.delete(user); //Borramos y vemos que pasa
        // Then ...
        Assertions.assertEquals(0, appUserRepository.count());
        Assertions.assertEquals(0, tokenRepository.count());

    }
}