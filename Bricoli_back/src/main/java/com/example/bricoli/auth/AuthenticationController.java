package com.example.bricoli.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/Admin/registerClient")
    public ResponseEntity<AuthenticationResponse>registerClient(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.registerClient(request));
    }


    @PostMapping("/Admin/registerPrestataire")
    public ResponseEntity<AuthenticationResponse>registerPres(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.registerPres(request));
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<AuthenticationResponse>registerAdmin(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.registerAdmin(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>authenticate(
            @RequestBody AuthenticationRequest  request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }


}
