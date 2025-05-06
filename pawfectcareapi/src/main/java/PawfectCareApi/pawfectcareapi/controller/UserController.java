/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.controller;

import PawfectCareApi.pawfectcareapi.entity.UserEntity;
import PawfectCareApi.pawfectcareapi.model.RegistrationModel;
import PawfectCareApi.pawfectcareapi.serviceImpl.UserServiceImpl;
import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 *
 *
 */
@RestController
@RequestMapping("/pawfectcare/user/")
public class UserController {

  @Autowired
  UserServiceImpl userServiceImpl;

  @GetMapping("user_login")
  public ApiResponseModel getUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("token") String token) {
      System.out.println("Received login request for username: " + username);
    return userServiceImpl.userLogin(username, password, token);
  }

  @PostMapping("user_registration")
  public ApiResponseModel signup(@RequestBody RegistrationModel model) {
    return userServiceImpl.signup(model.getFullname(), model.getPassword(), model.getGender(), model.getEmail(), model.getContactNumber(), model.getRole());

  }
  
     @PostMapping("verify-email")
   public ResponseEntity verifyEmail(@RequestParam("email") String email, @RequestParam("otpCode") int otpCode) throws IOException {
      return userServiceImpl.verify(email, otpCode);
   }

}
