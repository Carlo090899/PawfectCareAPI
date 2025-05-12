package PawfectCareApi.pawfectcareapi.repository;

import PawfectCareApi.pawfectcareapi.entity.DogProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogProfileRepository extends JpaRepository<DogProfileEntity, Integer> {

    DogProfileEntity findById(int id);

}
