/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


/**
 *
 * @author ASCENT SOLUTIONS INC
 */
@Data
@Entity
@Table(name = "ref_roles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RolesEntity {

  @Id
  int id;

  String description;

}
