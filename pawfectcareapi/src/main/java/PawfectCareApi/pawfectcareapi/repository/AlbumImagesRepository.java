package PawfectCareApi.pawfectcareapi.repository;

import PawfectCareApi.pawfectcareapi.entity.AlbumImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumImagesRepository extends JpaRepository<AlbumImagesEntity, Integer> {

    List<AlbumImagesEntity> findByDogAlbumId(int dog_album_id);

}
