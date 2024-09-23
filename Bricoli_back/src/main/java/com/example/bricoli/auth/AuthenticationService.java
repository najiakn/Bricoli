package com.example.bricoli.auth;

import com.example.bricoli.config.JwtService;
import com.example.bricoli.enums.Role;
import com.example.bricoli.models.Admin;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Prestataire;
import com.example.bricoli.repository.PersonneRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final PersonneRepository personneRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    //RegisterClient:
    public AuthenticationResponse registerClient(RegisterRequest request) {
        var user = new Client();

        user.setNom(request.getNom());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CLIENT);

        user = personneRepository.save(user);
        var jwtToken = jwtService.generateToken(user,user.getId());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();


    }

    //RegisterPrestataire
    public AuthenticationResponse registerPres(RegisterRequest request) {
        var user =new Prestataire();

        user.setNom(request.getNom());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.PRESTATAIRE);

        user = personneRepository.save(user);

        var jwtToken = jwtService.generateToken(user,user.getId());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();


    }

    //Register Admin ;
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        var user =new Admin();

        user.setNom(request.getNom());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ADMIN);

        user = personneRepository.save(user);

        var jwtToken = jwtService.generateToken(user,user.getId());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();


    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getMail(),
                        request.getPassword()
                )
        );
        var user = personneRepository.findByEmail(request.getMail())
                .orElseThrow();
        System.out.print(user);
        var jwtToken = jwtService.generateToken(user,user.getId());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();


    }



}
