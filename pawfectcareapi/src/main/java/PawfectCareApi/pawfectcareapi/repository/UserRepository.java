/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.repository;

import PawfectCareApi.pawfectcareapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author ASCENT SOLUTIONS INC
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

  UserEntity findByEmail(String email);
  UserEntity findById(int id);

  UserEntity findByEmailAndIsActive(String email, boolean is_active);

  List<UserEntity> findByRoleIdAndIsActive(int role_id, boolean is_active);

  boolean existsByIdAndIsVerified(Long id, boolean isVerified);

  boolean existsByOtpCode(int otp_code);

  @Query(value = "SELECT * FROM users WHERE id <> :id", nativeQuery = true)
  List<UserEntity> findAllExceptId(@Param("id") int id);

  @Modifying
  @Transactional
  @Query("update UserEntity u set u.isVerified = ?1 where u.id = ?2")
  int updateIsVerifiedById(boolean isVerified, Long id);
}
