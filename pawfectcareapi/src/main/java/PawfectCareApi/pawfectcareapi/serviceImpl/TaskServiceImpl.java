/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.serviceImpl;

import PawfectCareApi.pawfectcareapi.PushNotificationServices.PushNotificationService;
import PawfectCareApi.pawfectcareapi.entity.TaskAssignmentEntity;
import PawfectCareApi.pawfectcareapi.entity.UserEntity;
import PawfectCareApi.pawfectcareapi.error.ErrorException;
import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;
import PawfectCareApi.pawfectcareapi.model.EmployeeModel;
import PawfectCareApi.pawfectcareapi.model.PushNotificationRequest;
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
 * @author ASCENT SOLUTIONS INC
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskAssignmentRepository tRepo;

    @Autowired
    UserRepository uRepo;

    @Autowired
    private PushNotificationService pushNotifService;

    @Override
    public ApiResponseModel getTasks(String fullname, int role_id) {
        ApiResponseModel resp = new ApiResponseModel();
        List<TaskAssignmentEntity> tasks = new ArrayList<>();

        try {
            if (role_id == 1) {
                tasks = tRepo.findByStatusInOrderByFollowUpDesc(Arrays.asList("COMPLETED", "PENDING"));
            } else {
                tasks = tRepo.findByAssignedToInAndStatus(Arrays.asList(fullname, "EVERYONE"), "PENDING");
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
        List<UserEntity> listOfEmployee = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();

        try {

            if (assigned_to.contains("EVERYONE")) {
                listOfEmployee = uRepo.findByRoleIdAndIsActive(2, true);

                for (UserEntity u : listOfEmployee) {
                    List<String> list = new ArrayList<>();
                    list = uRepo.getNotificationTokenForTaskAssign(u.getFullname());
                    for (String s : list) {
                        getNotification(new PushNotificationRequest("PAWFECT CARE PH", "TASK [" + task_title + "] was ASSIGNED to [" + assigned_to + "]", "", s));
                    }
                }

            } else {
                List<String> list = uRepo.getNotificationTokenForTaskAssign(assigned_to);
                for (String s : list) {
                    getNotification(new PushNotificationRequest("PAWFECT CARE PH", "TASK [" + task_title + "] was ASSIGNED to [" + assigned_to + "]", "", s));

                }
            }

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

            List<String> list = uRepo.getNotificationTokenForTaskComplete(task.getAssignedFrom());
            for (String s : list) {
                getNotification(new PushNotificationRequest("PAWFECT CARE PH", "TASK [" + task.getTaskTitle() + "] was tagged as COMPLETED by [" + task.getAssignedTo() + "]", "", s));
            }

            resp.setMessage("Task Successfully Tagged as COMPLETED!");
            resp.setStatus(true);
            resp.setStatusCode(200);
        } catch (ErrorException e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    public ApiResponseModel followUpTask(int task_id) {
        ApiResponseModel resp = new ApiResponseModel();
        TaskAssignmentEntity task = new TaskAssignmentEntity();

        try {
            task = tRepo.findById(task_id);
            if (task == null) {
                resp.setMessage("No task Found");
                resp.setStatus(false);
                resp.setStatusCode(404);
            } else {
                task.setFollowUp(true);
                tRepo.save(task);
            }
            resp.setMessage("Task Successfully Followed Up!");
            resp.setStatus(true);
            resp.setStatusCode(200);
        } catch (ErrorException e) {
            e.printStackTrace();
        }
        return resp;
    }

    public void getNotification(PushNotificationRequest request) {
        pushNotifService.sendPushNotificationToToken(request);
        System.out.println("princr");
    }

}
