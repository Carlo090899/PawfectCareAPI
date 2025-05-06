package PawfectCareApi.pawfectcareapi.controller;

import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;
import PawfectCareApi.pawfectcareapi.serviceImpl.DogServiceImpl;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/pawfectcare/dogs/")
public class DogProfileController {

    @Autowired
    DogServiceImpl dogServiceImpl;
    String fileUploadPath = "C:\\PawfectCareProj\\gallery\\images";

    @PostMapping("save_dog_details")
    public ApiResponseModel saveDogDetails(@RequestParam("file") MultipartFile file,
                                         @RequestParam("dog_name") String dogName,
                                         @RequestParam("gender") String gender,
                                         @RequestParam("birthdate") String birthdate,
                                         @RequestParam("notes") String notes) {
        return dogServiceImpl.saveDogDetails(file, dogName, gender, birthdate, notes);
    }

    @GetMapping("get_dog_list")
    public ApiResponseModel getDogList() {
        return dogServiceImpl.getDogDetails();
    }

    @GetMapping("get_image_list")
    public ApiResponseModel getAlbumImages(@RequestParam("dog_album_id") int dog_album_id) {
        return dogServiceImpl.getAlbumImages(dog_album_id);
    }

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("C:", "PawfectCareProj", "gallery", "images", filename).normalize();
            System.out.println("Looking for file at: " + filePath.toAbsolutePath());

            if (!Files.exists(filePath)) {
                System.out.println("File not found.");
                return ResponseEntity.notFound().build();
            }

            Resource resource = new UrlResource(filePath.toUri());

            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("save_album")
    public ApiResponseModel saveAlbum(@RequestParam("dog_id") int dog_id ,@RequestParam("album_name") String album_name,
                                      @RequestParam("user_id") int user_id){
        return dogServiceImpl.saveAlbum(dog_id, album_name,user_id);
    }

    @GetMapping("get_album")
    public ApiResponseModel getAlbum(@RequestParam("dog_id") int dog_id) {
        return dogServiceImpl.getAlbum(dog_id);
    }

    @PostMapping("save_album_image")
    public ApiResponseModel saveAlbumImage(@RequestParam("file") MultipartFile file,
                                           @RequestParam("dog_album_id") int dog_album_id,
                                           @RequestParam("user_id") int user_id) {
        return dogServiceImpl.saveAlbumImage(file, dog_album_id, user_id);
    }

}
