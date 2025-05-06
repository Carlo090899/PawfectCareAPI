package PawfectCareApi.pawfectcareapi.service;

import PawfectCareApi.pawfectcareapi.model.ApiResponseModel;

public interface TeamService {

    ApiResponseModel getTeamList(int user_id);
    ApiResponseModel updateStatus(int id, boolean isActive);
}
