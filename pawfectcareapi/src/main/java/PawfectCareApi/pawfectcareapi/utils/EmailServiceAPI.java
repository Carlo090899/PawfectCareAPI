/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.utils;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 *
 */
public class EmailServiceAPI {

    static final String FROMNAME = Config.getPropertyValue("hibernate.FROMNAME");
    static final String FROM = Config.getPropertyValue("hibernate.FROM");
    static final String API_KEY = Config.getPropertyValue("hibernate.API_KEY");
    static final String API_SECRET_KEY = Config.getPropertyValue("hibernate.API_SECRET_KEY");

    public static MailjetResponse sendMail(String TO, String SUBJECT, String BODY, String name) {
        MailjetResponse response = null;
        try {

            MailjetClient client;
            MailjetRequest request;
            client = new MailjetClient(API_KEY, API_SECRET_KEY);
            request = new MailjetRequest(Emailv31.resource)
                    .property(Emailv31.MESSAGES, new JSONArray()
                            .put(new JSONObject()
                                    .put(Emailv31.Message.FROM, new JSONObject()
                                            .put("Email", FROM)
                                            .put("Name", FROMNAME))
                                    .put(Emailv31.Message.TO, new JSONArray()
                                            .put(new JSONObject()
                                                    .put("Email", TO)
                                                    .put("Name", name)))
                                    .put(Emailv31.Message.SUBJECT, SUBJECT)
                                    .put(Emailv31.Message.TEXTPART, "")
                                    .put(Emailv31.Message.HTMLPART, BODY)
                            ));
            response = client.post(request);
//         System.out.println(response.getStatus());
//         System.out.println(response.getData());

        } catch (MailjetException | JSONException ex) {
            ex.printStackTrace();
            System.out.println("Error message: " + ex.getMessage());
        }
        return response;
    }

    /**
     * Replace placeholders in email template
     *
     * @param email_template_path Filename of the email template to be used
     * @param data Array of placeholders, value
     * @return string Updated email template
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String formatEmailBody(String email_template_path, HashMap data)
            throws FileNotFoundException, IOException {
//        System.out.println("===== " + new Throwable().getStackTrace()[0].getMethodName());
//        System.out.println("===== Template: " + email_template_path);

        FileReader fr = new FileReader(email_template_path);

        String str = "";
        int i;

        while ((i = fr.read()) != -1) {
//            System.out.println("str" + (char) i);
            str += (char) i;
        }

        fr.close();

        if (data != null) {
            //set value to parameter in html
            Set<Map.Entry<String, String>> entries = data.entrySet();
            for (Map.Entry<String, String> entry : entries) {
//                System.out.println(entry.getKey() + " => " + entry.getValue());

                // Put the HTML content using HTML markup
                str = str.replace(entry.getKey(), entry.getValue() == null ? "" : entry.getValue());
            }
        }

        return str;
    }

}
