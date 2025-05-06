/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.service;

import PawfectCareApi.pawfectcareapi.entity.UserEntity;
import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author ASCENT SOLUTIONS INC
 */
public interface UserService {
  
    ApiResponseModel userLogin (String username, String password, String token);
    ApiResponseModel signup(String fullname, String password, String gender, String email, String contactNumber, String role);
    ResponseEntity verify(String email, int otpCode);
}
