/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.service;

import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;

/**
 *
 * @author ASCENT SOLUTIONS INC
 */
public interface TaskService {
  
  ApiResponseModel getTasks(String fullname, int role_id);
  ApiResponseModel assignTask(String task_title, String task_description, String assigned_to, String assigned_from, int user_id);
  ApiResponseModel getEmployeeList();
  ApiResponseModel deleteTask(int task_id);
  ApiResponseModel updateTaskStatus(int task_id, String status);
  ApiResponseModel followUpTask(int task_id);
}
