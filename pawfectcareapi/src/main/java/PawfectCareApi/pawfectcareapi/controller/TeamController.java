package PawfectCareApi.pawfectcareapi.controller;

import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;
import PawfectCareApi.pawfectcareapi.serviceImpl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pawfectcare/team/")
public class TeamController {

    @Autowired
    TeamServiceImpl teamService;

    @GetMapping("get_team")
    public ApiResponseModel getTeamList(@RequestParam("user_id") int user_id) {
        return teamService.getTeamList(user_id);
    }

    @PostMapping("update_status")
    public ApiResponseModel updateStatus(@RequestParam("id") int id, @RequestParam("is_active") boolean isActive){
        return teamService.updateStatus(id, isActive);
    }
}
