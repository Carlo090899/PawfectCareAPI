/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.controller;

import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;
import PawfectCareApi.pawfectcareapi.serviceImpl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author ASCENT SOLUTIONS INC
 */
@RestController
@RequestMapping("/pawfectcare/task/")
public class TaskController {

  @Autowired
  TaskServiceImpl taskServiceImpl;

  @GetMapping("get_tasks")
  public ApiResponseModel getTasks(@RequestParam("fullname") String fullname, @RequestParam("role_id") int role_id) {
    return taskServiceImpl.getTasks(fullname, role_id);
  }

  @PostMapping("assign_task")
  public ApiResponseModel assignTask(@RequestParam("task_title") String task_title, @RequestParam("task_desc") String task_desc,
          @RequestParam("assigned_to") String assigned_to, @RequestParam("assigned_from") String assigned_from, @RequestParam("user_id") int user_id) {
    return taskServiceImpl.assignTask(task_title, task_desc, assigned_to, assigned_from, user_id);
  }

  @GetMapping("get_employees")
  public ApiResponseModel getEmployeeList() {
    return taskServiceImpl.getEmployeeList();
  }

  @PostMapping("tag_task_as_completed")
  public ApiResponseModel tagtaskAsCompleted(@RequestParam("task_id") int task_id, @RequestParam("status") String status) {
    return taskServiceImpl.updateTaskStatus(task_id, status);
  }

  @PostMapping("delete_task")
  public ApiResponseModel deletetask(@RequestParam("task_id") int task_id) {
    return taskServiceImpl.deleteTask(task_id);
  }

}
