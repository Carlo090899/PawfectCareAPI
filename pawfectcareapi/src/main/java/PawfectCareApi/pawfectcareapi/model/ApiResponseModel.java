/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.model;

import PawfectCareApi.pawfectcareapi.entity.TaskAssignmentEntity;
import lombok.Data;

import java.util.List;


/**
 *
 * @author ASCENT SOLUTIONS INC
 */
@Data
public class ApiResponseModel {

  String message;
  boolean status;
  int statusCode;
  Object data;
  List<?> employeeList;
  List<?> tasks;
  List<?> dogs;
  List<?> albums;
  List<?> images;
  List<?> foodList;
  List<?> teamList;

  public List<?> getEmployeeList() {
    return employeeList;
  }

  public void setEmployeeList(List<?> employeeList) {
    this.employeeList = employeeList;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public List<?> getTasks() {
    return tasks;
  }

  public void setTasks(List<?> tasks) {
    this.tasks = tasks;
  }

  public List<?> getDogs() {
    return dogs;
  }

  public void setDogs(List<?> dogs) {
    this.dogs = dogs;
  }

  public List<?> getAlbums() {
    return albums;
  }

  public void setAlbums(List<?> albums) {
    this.albums = albums;
  }

  public List<?> getImages() {
    return images;
  }

  public void setImages(List<?> images) {
    this.images = images;
  }

  public List<?> getFoodList() {
    return foodList;
  }

  public void setFoodList(List<?> foodList) {
    this.foodList = foodList;
  }

  public List<?> getTeamList() {
    return teamList;
  }

  public void setTeamList(List<?> teamList) {
    this.teamList = teamList;
  }
}
