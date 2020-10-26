package com.recaptcha.controller;

import com.recaptcha.model.ReCaptchaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    RestTemplate restTemplate;


    @GetMapping(value = "/")
    public String home () {
        return "/home";
    }



    @PostMapping(value = "/user")
    public String loginUser (@RequestParam("g-recaptcha-response") String keyResponse, HttpServletRequest request) {

        // Адрес запроса Request
        String url = "https://www.google.com/recaptcha/api/siteverify";

        // Параметры в запросе:

        // Секретный ключ
        String secretKey = "?secret=6Lfmg8gZAAAAAO1KxvqOCl_chP-BjPm34HOD7wIu";

        // Response от Google. Параметр "g-recaptcha-response" который приходит
        // со страницы где находится reCaptcha
        String response = "&response="+keyResponse;

        // IP адрес клиента (Опционально)
        String remoteIp = "&remoteip="+request.getRemoteAddr();

        //Параметры в запросе
        String paramsRequest = url + secretKey + response + remoteIp;

        // Делаем запрос
        ReCaptchaModel reCaptchaModel = restTemplate.getForObject(paramsRequest, ReCaptchaModel.class);


        assert reCaptchaModel != null;
        if (reCaptchaModel.getError_codes() == null){
            System.out.println("Будиан == " + reCaptchaModel.isSuccess());
            System.out.println("Дата == " + reCaptchaModel.getChallenge_ts());
            System.out.println("Hostname == " + reCaptchaModel.getHostname());
        } else {
            System.out.println("Будиан == " + reCaptchaModel.isSuccess());
            System.out.println("Дата == " + reCaptchaModel.getChallenge_ts());
            System.out.println("Hostname == " + reCaptchaModel.getHostname());
            for (String error : reCaptchaModel.getError_codes()) {
                System.out.println("Ошики == " + error);

            }
        }

            return "redirect:/user";
        }




    @GetMapping(value = "/user")
    public String user () {
        return "/user";
    }


}
