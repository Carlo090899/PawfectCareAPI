package PawfectCareApi.pawfectcareapi.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "dog_profile")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DogProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "dog_name")
    String dogName;

    String birthdate;
    String gender;
    String notes;
    String filename;
    String filepath;

}
