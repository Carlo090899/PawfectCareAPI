package PawfectCareApi.pawfectcareapi.service;

import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DogService {

    ApiResponseModel saveDogDetails(MultipartFile file, String dogName, String gender, String birthdate, String notes);
    ApiResponseModel editDogDetails(String dogName, int dog_id, String birthdate, String notes);
    ApiResponseModel getDogDetails();
    ApiResponseModel saveAlbum(int dog_id, String album_name, int user_id);
    ApiResponseModel getAlbum(int dog_id);
    ApiResponseModel saveAlbumImage(MultipartFile file, int dog_album_id, int user_id);
    ApiResponseModel getAlbumImages(int dog_album_id);
    ApiResponseModel deleteAlbum(int album_id);
    ApiResponseModel editAlbum(int album_id, String album_name);
}
