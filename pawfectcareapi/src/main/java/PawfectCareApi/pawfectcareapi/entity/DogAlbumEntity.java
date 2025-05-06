package PawfectCareApi.pawfectcareapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "dog_album")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DogAlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="album_name")
    String albumName;

    @Column(name="dog_profile_id")
    int dogId;

    @Column(name="created_at")
    Timestamp createdAt;

    @Column(name="created_by_id")
    int createdById;

}
