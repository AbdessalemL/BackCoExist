package tn.esprit.coexist.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.coexist.dto.AuthenticationRequest;
import tn.esprit.coexist.dto.AuthenticationResponse;
import tn.esprit.coexist.dto.RegisterRequest;
import tn.esprit.coexist.config.JwtService;
import tn.esprit.coexist.entity.User;
import tn.esprit.coexist.repository.UserRepository;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final FileUploadService fileUploadService;
    private EmailSenderService emailSenderService;
    private UserService userService;
    OTPInterface otpInterface;
    public static final String uploadDirectory = "C:/xampp/htdocs/images/";

    public AuthenticationResponse register(RegisterRequest request) throws IOException {
        var  user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode((request.getPassword())))
                .phoneNumber(request.getPhoneNumber())
                .Address(request.getAddress())
                .roleName(request.getRoleName())
                // .imageUrl(request.getImageUrl(userService.Addimage(User, imageFile)))
                .build();
        String url = "http://localhost:4200/#/verification" ;
        String newLine = "<br/>"; // HTML line break
        String htmlMessage = "<div style='border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;'>"
                + "Soyez le bienvenue dans notre plateforme" + newLine
                + "Veuillez utiliser ce lien pour vous authentifier : " + newLine
                + "<a href='" + url + "'>" + url + "</a>" + newLine

                + "</div>";
        userRepository.save(user);
        /*String Newligne = System.getProperty("line.separator");
        String url = "http://localhost:4200/auth/verification/" + suser.getToken();
        String body = "Soyez le bienvenue dans notre platforme COEXIST  \n  veuillez utuliser ce lien là pour s'authentifier :" + Newligne + url + Newligne + "verification" +
                "Voici votre code de verfication  TN1122" ;
        try {
            emailSenderService.send(user.getEmail(), "Welcome", body);
            return new ResponseEntity<>(request HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
         */
        try {
            emailSenderService.send(user.getEmail(), "Welcome " + user.getUsername(), htmlMessage);
        } catch (Exception e) {
            System.out.println("Failed to send welcome email to " + user.getEmail() + ": " + e.getMessage());
        }
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        System.out.println("welcome : " + user.getUsername());


        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        System.out.println("Role of logged user : " + user.getRoleName());
        System.out.println("token of logged user : " + jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                var authResponse = AuthenticationResponse.builder()
                        .token(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }


    /*  public User Addimage(User user, MultipartFile imageFile) {
        try {
            // Créer le chemin du répertoire si nécessaire
            Path directoryPath = Paths.get(uploadDirectory);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // Générer un nom de fichier unique pour l'image
            String originalFilename = imageFile.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + originalFilename;

            // Chemin du fichier d'image
            Path filePath = Paths.get(uploadDirectory, fileName);

            // Écrire les données de l'image sur le disque
            Files.write(filePath, imageFile.getBytes());

            // Mettre à jour l'URL de l'image de l'utilisateur
            user.setImageUrl(fileName);

            // Enregistrer les modifications dans la base de données et retourner l'utilisateur mis à jour
            return userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload image");
        }
    }
   */
}
