package edu.comillas.icai.gitt.pat.spring.p5.service;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileRequest;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileResponse;
import edu.comillas.icai.gitt.pat.spring.p5.model.RegisterRequest;
import edu.comillas.icai.gitt.pat.spring.p5.repository.TokenRepository;
import edu.comillas.icai.gitt.pat.spring.p5.repository.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.p5.util.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * TODO#6
 * Completa los métodos del servicio para que cumplan con el contrato
 * especificado en el interface UserServiceInterface, utilizando
 * los repositorios y entidades creados anteriormente
 */
// EL TODO#14 ESTA INCLUIDO
//MIRAR INTERNFACE!!!!!!!!!!!!!
@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private Hashing hashing;

    public Token login(String email, String password) {

        AppUser appUser = appUserRepository.findByEmail(email).orElse(null); //Sin el orElse me da error
        if (appUser == null ) return null; //No hay nadie con ese mail
        //CON TODO#14
        if (!hashing.compare(appUser.password, password)) return null; //La contraseña no era correcta

        //Si llega hasta aqui es que esta todo bien


        Token token = new Token();
        token.appUser=appUser; //Asignar el token al usuario
        return tokenRepository.save(token); //Guardar el token en el repositorio y devolver el token
    }

    public AppUser authentication(String tokenId) {
        Token token = tokenRepository.findById(tokenId).orElse(null);
        if (token == null) return null; //No existe
        return token.appUser;
    }

    public ProfileResponse profile(AppUser appUser) {
        return new ProfileResponse(appUser.name,appUser.email,appUser.role);

    }
    public ProfileResponse profile(AppUser appUser, ProfileRequest profile) {
        //Guardar los cambios
        appUser.name= profile.name();
        appUser.role= profile.role();
        //TODO#14
        //Si se cambia la contraseña cuidado
        if (profile.password() != null && !profile.password().isEmpty()) {
            appUser.password = hashing.hash(profile.password());
        }

        AppUser actualizado=appUserRepository.save(appUser);
        return new ProfileResponse(actualizado.name,actualizado.email,actualizado.role);
    }
    public ProfileResponse profile(RegisterRequest register) {
        AppUser appUser = new AppUser();
        appUser.name= register.name();
        appUser.email= register.email();
        appUser.role= register.role();
        appUser.password= hashing.hash(register.password()); // TODO#14

        AppUser guardado=appUserRepository.save(appUser);
        return profile(guardado); //LLamo a la funcion
    }

    public void logout(String tokenId) {
        tokenRepository.deleteById(tokenId);
    }

    public void delete(AppUser appUser) {
        appUserRepository.delete(appUser);
    }

}
