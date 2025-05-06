/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.serviceImpl;

import PawfectCareApi.pawfectcareapi.entity.TaskAssignmentEntity;
import PawfectCareApi.pawfectcareapi.entity.UserEntity;
import PawfectCareApi.pawfectcareapi.error.ErrorException;
import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;
import PawfectCareApi.pawfectcareapi.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import PawfectCareApi.pawfectcareapi.repository.TaskAssignmentRepository;
import PawfectCareApi.pawfectcareapi.repository.UserRepository;
import PawfectCareApi.pawfectcareapi.service.TaskService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ASCENT SOLUTIONS INC
 */
@Service
public class TaskServiceImpl implements TaskService {

  @Autowired
  TaskAssignmentRepository tRepo;

  @Autowired
  UserRepository uRepo;

  @Override
  public ApiResponseModel getTasks(String fullname, int role_id) {
    ApiResponseModel resp = new ApiResponseModel();
    List<TaskAssignmentEntity> tasks = new ArrayList<>();

    try {
      if(role_id == 1){
        tasks = tRepo.findByStatusIn(Arrays.asList("COMPLETED", "PENDING"));
      }else{
        tasks = tRepo.findByAssignedToAndStatus(fullname,"PENDING");
      }


      if (tasks != null && !tasks.isEmpty()) {
        resp.setTasks(tasks);
        resp.setMessage("Data found");
        resp.setStatus(true);
        resp.setStatusCode(200);
      } else {
        resp.setMessage("No data found");
        resp.setStatus(false);
        resp.setStatusCode(404);
      }
    } catch (Exception e) {  // Change from ErrorException to Exception
      e.printStackTrace();
      resp.setMessage("Error occurred: " + e.getMessage());
      resp.setStatus(false);
      resp.setStatusCode(500);
    }

    return resp;
  }

  @Override
  public ApiResponseModel assignTask(String task_title, String task_description, String assigned_to, String assigned_from, int user_id) {
    ApiResponseModel resp = new ApiResponseModel();
    TaskAssignmentEntity task = new TaskAssignmentEntity();
    LocalDateTime date = LocalDateTime.now();

    try {

      task.setTaskTitle(task_title);
      task.setTaskDescription(task_description);
      task.setAssignedTo(assigned_to);
      task.setAssignedFrom(assigned_from);
      task.setTaskDate(String.valueOf(date));
      task.setStatus("PENDING");
      task.setCreatedById(user_id);
      task.setCreatedAt(Timestamp.valueOf(date));
      tRepo.save(task);

      resp.setMessage("Task Successfully Assigned to [ " + assigned_to + " ] ");
      resp.setStatus(true);
      resp.setStatusCode(200);

    } catch (Exception e) {  // Change from ErrorException to Exception
      e.printStackTrace();
      resp.setMessage("Error occurred: " + e.getMessage());
      resp.setStatus(false);
      resp.setStatusCode(500);
    }

    return resp;

  }

  @Override
  public ApiResponseModel getEmployeeList() {
    ApiResponseModel resp = new ApiResponseModel();
    EmployeeModel employeeModel = new EmployeeModel();

    List<UserEntity> employees = new ArrayList<>();

    try {
      employees = uRepo.findByRoleIdAndIsActive(2, true);
      if (employees.isEmpty()) {
        resp.setMessage("No Data to Show");
        resp.setStatus(false);
        resp.setStatusCode(404);
      } else {
        employeeModel.setEmployeeList(employees);
      }
      resp.setEmployeeList(employees);
      resp.setMessage("Employee List Found");
      resp.setStatus(true);
      resp.setStatusCode(200);
    } catch (ErrorException e) {
      e.printStackTrace();
    }
    return resp;
  }

  @Override
  public ApiResponseModel deleteTask(int task_id) {
    ApiResponseModel resp = new ApiResponseModel();
    TaskAssignmentEntity task = new TaskAssignmentEntity();

    try {
      task = tRepo.findById(task_id);
      if (task == null) {
        resp.setMessage("No task Found");
        resp.setStatus(false);
        resp.setStatusCode(404);
      } else {
        tRepo.delete(task);
      }
      resp.setMessage("Task Successfully Deleted!");
      resp.setStatus(true);
      resp.setStatusCode(200);
    } catch (ErrorException e) {
      e.printStackTrace();
    }
    return resp;
  }

  @Override
  public ApiResponseModel updateTaskStatus(int task_id, String status) {
    ApiResponseModel resp = new ApiResponseModel();
    TaskAssignmentEntity task = new TaskAssignmentEntity();

    try {
      task = tRepo.findById(task_id);
      if (task == null) {
        resp.setMessage("No task Found");
        resp.setStatus(false);
        resp.setStatusCode(404);
      } else {
        task.setStatus(status);
        tRepo.save(task);
      }
      resp.setMessage("Task Successfully Tagged as COMPLETED!");
      resp.setStatus(true);
      resp.setStatusCode(200);
    } catch (ErrorException e) {
      e.printStackTrace();
    }
    return resp;
  }

}
