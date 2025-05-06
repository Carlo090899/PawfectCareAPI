package PawfectCareApi.pawfectcareapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "album_images")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AlbumImagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String filename;
    String filepath;

    @Column(name = "dog_album_id")
    int dogAlbumId;

    @Column(name = "created_at")
    Timestamp createdAt;

    @Column(name = "created_by_id")
    int createdBy;


}
