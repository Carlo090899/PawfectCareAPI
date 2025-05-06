package PawfectCareApi.pawfectcareapi.controller;

import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;
import PawfectCareApi.pawfectcareapi.serviceImpl.FoodChartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pawfectcare/foods/")
public class FoodChartController {

    @Autowired
    FoodChartServiceImpl foodChartService;

    @PostMapping("save_food")
    public ApiResponseModel saveFood(@RequestParam("dog_id") int dog_id , @RequestParam("dog_food") String dog_food,
                                     @RequestParam("food_desc") String food_desc, @RequestParam("quantity") String quantity,
                                     @RequestParam("user_id") int user_id){
        return foodChartService.saveFood(dog_id, dog_food,food_desc, quantity, user_id);
    }

    @GetMapping("get_food")
    public ApiResponseModel getFood(@RequestParam("dog_id") int dog_id) {
        return foodChartService.getFoods(dog_id);
    }

    @PostMapping("delete_food")
    public ApiResponseModel deleteFood(@RequestParam("food_id") int food_id) {
        return foodChartService.deleteFood(food_id);
    }

}
