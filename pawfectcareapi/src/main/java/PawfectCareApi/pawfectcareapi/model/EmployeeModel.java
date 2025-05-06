/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.model;

import PawfectCareApi.pawfectcareapi.entity.UserEntity;
import lombok.Data;

import java.util.List;

/**
 *
 * @author ASCENT SOLUTIONS INC
 */
@Data
public class EmployeeModel {
  
  List<UserEntity> employeeList;

  public List<UserEntity> getEmployeeList() {
    return employeeList;
  }

  public void setEmployeeList(List<UserEntity> employeeList) {
    this.employeeList = employeeList;
  }
}
