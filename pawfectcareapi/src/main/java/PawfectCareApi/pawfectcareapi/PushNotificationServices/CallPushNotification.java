package PawfectCareApi.pawfectcareapi.PushNotificationServices;

import PawfectCareApi.pawfectcareapi.model.PushNotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class CallPushNotification {
    @Autowired
    PushNotificationService pushNotificationService;
    public void getNotification(PushNotificationRequest request){
        pushNotificationService.sendPushNotificationToToken(request);
        System.out.println("princr");
    }
}
