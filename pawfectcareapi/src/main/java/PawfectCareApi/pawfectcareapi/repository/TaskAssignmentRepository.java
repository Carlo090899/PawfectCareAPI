/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.repository;


import PawfectCareApi.pawfectcareapi.entity.TaskAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author ASCENT SOLUTIONS INC
 */
public interface TaskAssignmentRepository extends JpaRepository<TaskAssignmentEntity, Integer>{
  
  List<TaskAssignmentEntity> findByStatusInOrderByFollowUpDesc(List<String> status);
  List<TaskAssignmentEntity> findByAssignedToInAndStatus(List<String> assigned_to, String status);
  TaskAssignmentEntity findById(int id);

}
