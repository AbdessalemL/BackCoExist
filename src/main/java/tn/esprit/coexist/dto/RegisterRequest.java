package tn.esprit.coexist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.coexist.entity.RoleName;
import tn.esprit.coexist.entity.User;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
    private Long phoneNumber;
    private String address;
    private RoleName roleName;
    private String imageUrl;


}
