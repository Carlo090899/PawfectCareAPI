/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.repository;

import PawfectCareApi.pawfectcareapi.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ASCENT SOLUTIONS INC
 */
public interface RolesRepository extends JpaRepository<RolesEntity, Integer>{
  RolesEntity findByDescription(String role);
}
