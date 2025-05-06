package PawfectCareApi.pawfectcareapi.serviceImpl;

import PawfectCareApi.pawfectcareapi.entity.AlbumImagesEntity;
import PawfectCareApi.pawfectcareapi.entity.DogAlbumEntity;
import PawfectCareApi.pawfectcareapi.entity.DogProfileEntity;
import PawfectCareApi.pawfectcareapi.entity.TaskAssignmentEntity;
import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;
import PawfectCareApi.pawfectcareapi.repository.AlbumImagesRepository;
import PawfectCareApi.pawfectcareapi.repository.DogAlbumRepository;
import PawfectCareApi.pawfectcareapi.repository.DogProfileRepository;
import PawfectCareApi.pawfectcareapi.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DogServiceImpl implements DogService {

    String fileUploadPath = "C:\\PawfectCareProj\\gallery\\images";

    @Autowired
    DogProfileRepository dogRepo;

    @Autowired
    DogAlbumRepository albumRepo;

    @Autowired
    AlbumImagesRepository albumImgRepo;

    @Override
    public ApiResponseModel getDogDetails() {
        ApiResponseModel resp = new ApiResponseModel();
        List<DogProfileEntity> dogs = new ArrayList<>();

        try {
            dogs = dogRepo.findAll();
            if (!dogs.isEmpty()) {
                resp.setDogs(dogs);
                resp.setMessage("List of dogs found");
                resp.setStatus(true);
                resp.setStatusCode(200);
            } else {
                resp.setMessage("No data found");
                resp.setStatus(false);
                resp.setStatusCode(404);
            }


        } catch (Exception e) {  // Change from ErrorException to Exception
            e.printStackTrace();
            resp.setMessage("Error occurred: " + e.getMessage());
            resp.setStatus(false);
            resp.setStatusCode(500);
        }

        return resp;

    }

    @Override
    public ApiResponseModel getAlbumImages(int dog_album_id) {
        ApiResponseModel resp = new ApiResponseModel();
        List<AlbumImagesEntity> images = new ArrayList<>();

        try {
            images = albumImgRepo.findByDogAlbumId(dog_album_id);
            if (!images.isEmpty()) {
                resp.setImages(images);
                resp.setMessage("List of images found");
                resp.setStatus(true);
                resp.setStatusCode(200);
            } else {
                resp.setMessage("No data found");
                resp.setStatus(false);
                resp.setStatusCode(404);
            }


        } catch (Exception e) {  // Change from ErrorException to Exception
            e.printStackTrace();
            resp.setMessage("Error occurred: " + e.getMessage());
            resp.setStatus(false);
            resp.setStatusCode(500);
        }

        return resp;

    }

    @Override
    public ApiResponseModel saveDogDetails(MultipartFile file, String dogName, String gender, String birthdate, String notes) {
        ApiResponseModel resp = new ApiResponseModel();
        DogProfileEntity dogProfileEntity = new DogProfileEntity();

        try {
            dogProfileEntity.setDogName(dogName);
            dogProfileEntity.setGender(gender);
            dogProfileEntity.setBirthdate(birthdate);
            dogProfileEntity.setNotes(notes);
            String filename = file.getOriginalFilename();
            dogProfileEntity.setFilename(filename);
            dogProfileEntity.setFilepath(fileUploadPath + filename);

            dogRepo.save(dogProfileEntity);
            saveImage(file);

            resp.setMessage("Dog Successfully Registered");
            resp.setStatus(true);
            resp.setStatusCode(200);

        } catch (Exception e) {  // Change from ErrorException to Exception
            e.printStackTrace();
            resp.setMessage("Error occurred: " + e.getMessage());
            resp.setStatus(false);
            resp.setStatusCode(500);
        }


        return resp;
    }

    private void saveImage(MultipartFile file) {

        try {
            byte[] data = file.getBytes();
            Path path = Paths.get(fileUploadPath + "/" + file.getOriginalFilename());
            Files.write(path, data);
        } catch (IOException ex) {
            Logger.getLogger(DogServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ApiResponseModel saveAlbum(int dog_id, String album_name, int user_id) {
        ApiResponseModel resp = new ApiResponseModel();
        DogAlbumEntity albumEntity = new DogAlbumEntity();
        LocalDateTime date = LocalDateTime.now();
        try {

            albumEntity.setAlbumName(album_name);
            albumEntity.setDogId(dog_id);
            albumEntity.setCreatedAt(Timestamp.valueOf(date));
            albumEntity.setCreatedById(user_id);

            albumRepo.save(albumEntity);

            resp.setMessage("Album Successfully Save");
            resp.setStatus(true);
            resp.setStatusCode(200);

        } catch (Exception e) {  // Change from ErrorException to Exception
            e.printStackTrace();
            resp.setMessage("Error occurred: " + e.getMessage());
            resp.setStatus(false);
            resp.setStatusCode(500);
        }


        return resp;
    }

    public ApiResponseModel getAlbum(int dog_id) {
        ApiResponseModel resp = new ApiResponseModel();
        List<DogAlbumEntity> albums = new ArrayList<>();

        try {
            albums = albumRepo.findByDogId(dog_id);
            if (!albums.isEmpty()) {
                resp.setAlbums(albums);
                resp.setMessage("List of dogs found");
                resp.setStatus(true);
                resp.setStatusCode(200);
            } else {
                resp.setMessage("No data found");
                resp.setStatus(false);
                resp.setStatusCode(404);
            }


        } catch (Exception e) {  // Change from ErrorException to Exception
            e.printStackTrace();
            resp.setMessage("Error occurred: " + e.getMessage());
            resp.setStatus(false);
            resp.setStatusCode(500);
        }

        return resp;

    }

    @Override
    public ApiResponseModel saveAlbumImage(MultipartFile file, int dog_album_id, int user_id) {
        ApiResponseModel resp = new ApiResponseModel();
        AlbumImagesEntity albumImagesEntity = new AlbumImagesEntity();
        LocalDateTime date = LocalDateTime.now();

        try {

            String filename = file.getOriginalFilename();
            albumImagesEntity.setFilename(filename);
            albumImagesEntity.setFilepath(fileUploadPath + filename);
            albumImagesEntity.setDogAlbumId(dog_album_id);
            albumImagesEntity.setCreatedBy(user_id);
            albumImagesEntity.setCreatedAt(Timestamp.valueOf(date));

            albumImgRepo.save(albumImagesEntity);
            saveImage(file);

            resp.setMessage("Image Successfully Saved");
            resp.setStatus(true);
            resp.setStatusCode(200);

        } catch (Exception e) {  // Change from ErrorException to Exception
            e.printStackTrace();
            resp.setMessage("Error occurred: " + e.getMessage());
            resp.setStatus(false);
            resp.setStatusCode(500);
        }


        return resp;
    }

}
