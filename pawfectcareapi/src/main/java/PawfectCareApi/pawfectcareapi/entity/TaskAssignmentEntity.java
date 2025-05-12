/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author ASCENT SOLUTIONS INC
 */
@Data
@Entity
@Table(name = "task_assignment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TaskAssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "task_title")
    String taskTitle;

    @Column(name = "task_description")
    String taskDescription;

    @Column(name = "assigned_to")
    String assignedTo;

    @Column(name = "assigned_from")
    String assignedFrom;

    @Column(name = "task_date")
    String taskDate;

    String status;

    @Column(name = "created_by_id")
    int createdById;

    @Column(name = "created_at")
    Timestamp createdAt;

    @Column(name = "follow_up")
    boolean followUp;

}
