/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.entity;

/**
 *
 * @author ASCENT SOLUTIONS INC
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  String fullname;
  String email;
  String password;
  String gender;

  @Column(name = "role_id")
  int roleId;

  @Column(name = "contact_number")
  String contactNumber;

  @Column(name = "created_at")
  Timestamp createdAt;

  @Column(name = "is_active")
  boolean isActive;

  @Column(name = "is_verified")
  boolean isVerified;

  @Column(name = "otp_code")
  private int otpCode;

  @Column(name = "code_expired")
  private Timestamp codeExpired;

  @Column(name = "device_token")
  String deviceToken;

}
