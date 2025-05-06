package PawfectCareApi.pawfectcareapi.repository;

import PawfectCareApi.pawfectcareapi.entity.DogAlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogAlbumRepository extends JpaRepository<DogAlbumEntity, Integer> {

    List<DogAlbumEntity> findByDogId(int dog_id);

}
