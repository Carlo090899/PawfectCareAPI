package PawfectCareApi.pawfectcareapi.service;

import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;

public interface FoodChartService {

    ApiResponseModel saveFood(int dog_id, String dog_food, String food_desc, String quantity, int user_id);
    ApiResponseModel getFoods(int dog_id);
    ApiResponseModel deleteFood(int food_id);
}
