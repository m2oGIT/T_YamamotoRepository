package com.example;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@Controller
public class StsSampleApplication extends SpringBootServletInitializer {

    // public static void main(String[] args) {
    // SpringApplication.run(StsSampleApplication.class, args);

    public static void main(String[] args) {
        SpringApplication.run(StsSampleApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(StsSampleApplication.class);
    }

    // @RestControllerなら有効。
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String getSomething() {
        return "文字列テスト";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        model.addAttribute("now", sdf.format(new Date()));
        //model.addAttribute("user", "me");
        model.addAttribute("severInfo", this.getSeverInfo());
        return "index";
    }

    private HashMap<String, String> getSeverInfo() {

        HashMap<String, String> severInfo = new HashMap<String, String>();

        try {
            InetAddress addr = InetAddress.getLocalHost();
            severInfo.put("hostname", addr.getHostName());
            severInfo.put("hostadress", addr.getHostAddress());
            // System.out.println("Local Host Name: " + addr.getHostName());
            // System.out.println("IP Address : " + addr.getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return severInfo;

    }
}
