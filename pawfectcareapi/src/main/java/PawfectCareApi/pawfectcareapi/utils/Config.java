/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PawfectCareApi.pawfectcareapi.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author andrea.hermogenes<ashermoegenes03@gmail.com>
 */
public class Config {

  public static String getPropertyValue(String propName) {
    Properties prop = new Properties();
    String returnValue = "";
    try {
      prop.load(new FileInputStream("C:\\PawfectCareProj\\conf\\config.properties"));
      returnValue = prop.getProperty(propName);
//          System.out.println("returnValue: " + returnValue);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return returnValue;
  }

}
