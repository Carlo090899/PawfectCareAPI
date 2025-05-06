/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.serviceImpl;

import PawfectCareApi.pawfectcareapi.PushNotificationServices.PushNotificationService;
import PawfectCareApi.pawfectcareapi.entity.UserEntity;
import PawfectCareApi.pawfectcareapi.model.PushNotificationRequest;
import PawfectCareApi.pawfectcareapi.repository.UserRepository;
import PawfectCareApi.pawfectcareapi.service.UserService;
import PawfectCareApi.pawfectcareapi.controller.BaseController;
import PawfectCareApi.pawfectcareapi.error.ErrorException;
import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;
import PawfectCareApi.pawfectcareapi.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static PawfectCareApi.pawfectcareapi.controller.BaseController.getRandomNumber;

/**
 * @author ASCENT SOLUTIONS INC
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository uRepo;

//    @Autowired
//    PushNotificationService pushNotificationService;

    BaseController baseController = new BaseController();
    public final String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz0123456789";

    @Override
    public ApiResponseModel userLogin(String username, String password, String token) {
        ApiResponseModel resp = new ApiResponseModel();
        LocalDateTime date = LocalDateTime.now();
        boolean isActive = true;
        UserEntity u = new UserEntity();
        UserEntity checkIfActive = new UserEntity();
        try {
            u = uRepo.findByEmail(username);
            if (u == null) {
                resp.setMessage("User not found");
                resp.setStatusCode(404);
                resp.setStatus(false);
            } else {

                checkIfActive = uRepo.findByEmailAndIsActive(username, isActive);
                if (checkIfActive != null) {
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    if (passwordEncoder.matches(password, u.getPassword())) {
                        u.setDeviceToken(token);
                        uRepo.save(u);
                        resp.setData(u);
                        resp.setMessage("Login Success!");
                        resp.setStatusCode(200);
                        resp.setStatus(true);
                    } else {
                        resp.setMessage("Wrong password");
                        resp.setStatusCode(404);
                        resp.setStatus(false);
                    }
                } else {
                    resp.setMessage("This Account is no longer Active.");
                    resp.setStatusCode(404);
                    resp.setStatus(false);
                }

            }
        } catch (ErrorException e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    public ApiResponseModel signup(String fullname, String password, String gender, String email, String contactNumber, String role) {

        LocalDateTime date = LocalDateTime.now();
        String message = "";
        String code = "";
        UserEntity u = new UserEntity();
        ApiResponseModel resp = new ApiResponseModel();
        HashMap<String, Object> data = new HashMap<>();

        try {
            UserEntity user = uRepo.findByEmail(email);
            if (user == null) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashed_password = passwordEncoder.encode(password);
                code = getRandomNumber(4);
                u.setFullname(fullname);
                u.setEmail(email);
                u.setContactNumber(contactNumber);
                u.setGender(gender);
                u.setPassword(hashed_password);
                u.setActive(true);
                u.setCreatedAt(Timestamp.valueOf(date));
                u.setOtpCode(Integer.parseInt(code));
                u.setRoleId(2);

                uRepo.save(u);

                message = "Your account has been created successfully.";
                resp.setMessage(message);
                resp.setStatus(true);
                data.put("%%CODE%%", code);
                data.put("%%email%%", u.getEmail());

                baseController.formatAndSendMail(u.getEmail(), "User Verification", "VerifyUser.html", data, u.getFullname());

            } else {
                message = "Email is already exist.";
                resp.setMessage(message);
                resp.setStatus(false);
                resp.setStatusCode(404);

            }

        } catch (Exception ex) {
            message = ex.getMessage();
            resp.setMessage(message);
            resp.setStatus(false);
            resp.setStatusCode(404);

        }
        return resp;
    }

    @Override
    public ResponseEntity verify(String email, int otpCode) {
        ResponseEntity res = null;
        HashMap<String, Object> req_response = new HashMap<>();
        String message = "";
        UserEntity u = new UserEntity();


        try {

            u = uRepo.findByEmail(email);
            if (uRepo.existsById(u.getId())) {

                if (uRepo.existsByOtpCode(otpCode)) {

                        uRepo.updateIsVerifiedById(true, (long) u.getId());
                        message = "Your account has been verified.";
                        req_response.put("message", message);
                        req_response.put("status", true);
                        res = new ResponseEntity(req_response, HttpStatus.OK);

                } else {
                    message = "Please enter valid activation code.";
                    req_response.put("message", message);
                    req_response.put("status", false);
                    res = new ResponseEntity(req_response, HttpStatus.OK);
                }
            } else {
                message = "Your account is not existing. Please create an account.";
                req_response.put("message", message);
                res = new ResponseEntity(req_response, HttpStatus.NOT_FOUND);

            }
        } catch (Exception ex) {
            Logger.getLogger(UserServiceImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
            message = ex.getMessage();
            req_response.put("message", message);
            req_response.put("status", false);
            res = new ResponseEntity(req_response, HttpStatus.BAD_REQUEST);
        }
        return res;
    }

//    public void getNotification(PushNotificationRequest request) {
//        pushNotificationService.sendPushNotificationToToken(request);
//        System.out.println("princr");
//    }
}
