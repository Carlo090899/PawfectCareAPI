package PawfectCareApi.pawfectcareapi.repository;

import PawfectCareApi.pawfectcareapi.entity.FoodChartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodChartRepository extends JpaRepository<FoodChartEntity, Integer> {

    List<FoodChartEntity> findByDogId(int dogId);
    FoodChartEntity findById(int id);
}
