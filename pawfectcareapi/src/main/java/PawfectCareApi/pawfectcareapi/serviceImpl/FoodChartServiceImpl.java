package PawfectCareApi.pawfectcareapi.serviceImpl;

import PawfectCareApi.pawfectcareapi.entity.DogAlbumEntity;
import PawfectCareApi.pawfectcareapi.entity.FoodChartEntity;
import PawfectCareApi.pawfectcareapi.entity.TaskAssignmentEntity;
import PawfectCareApi.pawfectcareapi.error.ErrorException;
import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;
import PawfectCareApi.pawfectcareapi.repository.FoodChartRepository;
import PawfectCareApi.pawfectcareapi.service.FoodChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodChartServiceImpl implements FoodChartService {

    @Autowired
    FoodChartRepository foodRepo;

    @Override
    public ApiResponseModel saveFood(int dog_id, String dog_food, String food_desc, String quantity, int user_id) {
        ApiResponseModel resp = new ApiResponseModel();
        FoodChartEntity foodEntity = new FoodChartEntity();
        LocalDateTime date = LocalDateTime.now();
        try {

            foodEntity.setDogFood(dog_food);
            foodEntity.setDogId(dog_id);
            foodEntity.setFoodDesc(food_desc);
            foodEntity.setQuantity(quantity);
            foodEntity.setCreatedAt(Timestamp.valueOf(date));
            foodEntity.setCreatedById(user_id);

            foodRepo.save(foodEntity);

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

    @Override
    public ApiResponseModel getFoods(int dog_id) {
        ApiResponseModel resp = new ApiResponseModel();
        List<FoodChartEntity> foodList = new ArrayList<>();

        try {
            foodList = foodRepo.findByDogId(dog_id);
            if (!foodList.isEmpty()) {
                resp.setFoodList(foodList);
                resp.setMessage("List of foods found");
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
    public ApiResponseModel deleteFood(int food_id) {
        ApiResponseModel resp = new ApiResponseModel();
        FoodChartEntity food = new FoodChartEntity();

        try {
            food = foodRepo.findById(food_id);
            if (food == null) {
                resp.setMessage("No food Found");
                resp.setStatus(false);
                resp.setStatusCode(404);
            } else {
                foodRepo.delete(food);
            }
            resp.setMessage("Food Successfully Deleted!");
            resp.setStatus(true);
            resp.setStatusCode(200);
        } catch (ErrorException e) {
            e.printStackTrace();
        }
        return resp;
    }
}
