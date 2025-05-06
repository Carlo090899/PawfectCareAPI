package PawfectCareApi.pawfectcareapi.serviceImpl;

import PawfectCareApi.pawfectcareapi.entity.UserEntity;
import PawfectCareApi.pawfectcareapi.error.ErrorException;
import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;
import PawfectCareApi.pawfectcareapi.model.EmployeeModel;
import PawfectCareApi.pawfectcareapi.repository.UserRepository;
import PawfectCareApi.pawfectcareapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    UserRepository uRepo;

    @Override
    public ApiResponseModel getTeamList(int user_id) {
        ApiResponseModel resp = new ApiResponseModel();
        List<UserEntity> team = new ArrayList<>();

        try {
            team = uRepo.findAllExceptId(user_id);
            if (team.size() == 0) {
                resp.setMessage("No Data to Show");
                resp.setStatus(false);
                resp.setStatusCode(404);
            } else {
            }
            resp.setTeamList(team);
            resp.setMessage("Team List Found");
            resp.setStatus(true);
            resp.setStatusCode(200);
        } catch (ErrorException e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    public ApiResponseModel updateStatus(int id, boolean isActive) {
        ApiResponseModel resp = new ApiResponseModel();
        UserEntity team = new UserEntity();

        try {
            team = uRepo.findById(id);
            if (team.getId() == 0) {
                resp.setMessage("No Data to Show");
                resp.setStatus(false);
                resp.setStatusCode(404);
            } else {
                team.setActive(isActive);
                uRepo.save(team);
                resp.setMessage("Successfully update the status");
                resp.setStatus(true);
                resp.setStatusCode(200);
            }

        } catch (ErrorException e) {
            e.printStackTrace();
        }
        return resp;
    }

}
