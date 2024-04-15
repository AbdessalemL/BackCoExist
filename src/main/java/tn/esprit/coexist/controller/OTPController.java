package tn.esprit.coexist.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.coexist.dto.OTP;
import tn.esprit.coexist.service.OTPInterface;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/OTP")
public class OTPController {
    @Autowired
    OTPInterface otpInterface;
    @PostMapping("/GenerateOTP")
    public OTP GenerateOTp() {
        return otpInterface.GenerateOTp();
    }
    @PostMapping("/VerifierOTP/{identification}")
    public Boolean VerifierOTP(@PathVariable("identification") String identification)  {
        return otpInterface.VerifierOTP(identification);
    }
    @PostMapping("/ResendOTP")
    public OTP ResendOTP(@RequestBody OTP existingOTP) {
        return otpInterface.ResendOTP(existingOTP);
    }
    @DeleteMapping("/DeleteOTP")
    public void DeleteOTP() {
        otpInterface.DeleteOTP();
    }
}
