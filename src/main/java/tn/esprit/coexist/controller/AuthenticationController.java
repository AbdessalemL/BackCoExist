package tn.esprit.coexist.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.coexist.config.JwtService;
import tn.esprit.coexist.dto.AuthenticationRequest;
import tn.esprit.coexist.repository.UserRepository;
import tn.esprit.coexist.service.AuthenticationService;
import tn.esprit.coexist.dto.RegisterRequest;
import tn.esprit.coexist.service.UserService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    @Autowired
    UserRepository userRepository;



    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request
    )throws IOException {
        System.out.println("user added ! : ");
        return ResponseEntity.ok(service.register(request));
    }


    /*@PostMapping("/register")
    public ResponseEntity<String> register(@ModelAttribute RegisterRequest request, @RequestParam("image") MultipartFile imageFile) {
        try {
            service.register(request, imageFile);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user.");
        }
    }*/

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println("Welcome : "+request.getEmail());

        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}
