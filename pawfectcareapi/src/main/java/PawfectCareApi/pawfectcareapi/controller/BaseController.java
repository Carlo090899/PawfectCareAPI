/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import PawfectCareApi.pawfectcareapi.utils.EmailServiceAPI;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;

import static PawfectCareApi.pawfectcareapi.utils.EmailServiceAPI.sendMail;

/**
 *
 * 
 */
@RestController
public class BaseController {

   public BaseController() {
   }

   public static HashMap<String, String> data = new HashMap<>();
   public static HashMap<String, Object> req_response = new HashMap<>();
   public static LocalDateTime date = LocalDateTime.now();
   public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   public static SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyyMMddHHmm");

   public static final String contextPath = "pawfectcare/";

   public final static String EMAIL_TEMPLATE_PATH = "C:\\PawfectCareProj\\templates\\email\\";
   public final static String INVENTORY_IMAGES_PATH = "C:\\PawfectCareProj\\gallery\\images\\";


   protected Boolean has_session = false;
   protected int current_user_id = 0;
   protected String current_role_code;
   protected String current_user_email;
   protected String current_token;

   /**
    * Set Session
    *
    * @param session
    */
   protected void getSessionObject(HttpSession session) {
      this.has_session = true;
      if ((session != null && session.getAttribute("sess_user_pid") != null)) {
         this.current_user_id = (int) session.getAttribute("sess_user_pid");
         this.current_role_code = String.valueOf(session.getAttribute("sess_user_role_code"));
         this.current_user_email = String.valueOf(session.getAttribute("sess_email"));
         this.current_token = String.valueOf(session.getAttribute("sess_token"));
      }
   }


   /**
    *
    * @param
    * @return
    */
   public static String getRandomNumber(int n) {
      String AlphaNumericString = "0123456789";

      StringBuilder sb = new StringBuilder(n);
      for (int i = 0; i < n; i++) {
         int index = (int) (AlphaNumericString.length() * Math.random());
         sb.append(AlphaNumericString.charAt(index));
      }
      return sb.toString();
   }

   /**
    *
    * @param n
    * @return
    */
   public static String getRandomString(int n) {
      String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz0123456789";

      StringBuilder sb = new StringBuilder(n);
      for (int i = 0; i < n; i++) {
         int index = (int) (AlphaNumericString.length() * Math.random());
         sb.append(AlphaNumericString.charAt(index));
      }
      return sb.toString();
   }

   public void formatAndSendMail(String to, String subject, String filename, HashMap<String, Object> data1, String name) throws Exception {

      String body = EmailServiceAPI.formatEmailBody((EMAIL_TEMPLATE_PATH + filename), data1);
      sendMail(to, subject, body, name);
   }

   public static boolean transfer_file(MultipartFile file, String abs_file_name) throws Exception {
      try {
         file.transferTo(new File(abs_file_name));
      } catch (IOException | IllegalStateException e) {
         throw e;
      }

      return true;
   }

   /**
    * Delete files
    *
    * @param file
    * @param dir
    */
   public static void deleteFiles(File file, String dir) {
      try {
         file.delete();
      } catch (Exception e) {
         throw e;
      }

      try {
         File file2 = new File(dir);
         file2.delete();
      } catch (Exception e) {
         throw e;
      }
   }
}
