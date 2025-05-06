package PawfectCareApi.pawfectcareapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "food_chart")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FoodChartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="dog_food")
    String dogFood;

    @Column(name="food_desc")
    String foodDesc;

    String quantity;

    @Column(name="created_at")
    Timestamp createdAt;

    @Column(name="created_by_id")
    int createdById;

    @Column(name="dog_id")
    int dogId;



}
