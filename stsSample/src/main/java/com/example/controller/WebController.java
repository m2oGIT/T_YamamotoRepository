package com.example.controller;

import org.springframework.stereotype.Controller;

//@RestControllerから@Controllerに書き換え、レスポンスをviewの名称とする。
@Controller
public class WebController {

//  @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
//  public String index(Model model) {
//
//  model.addAttribute("hello", "Hello Thymeleaf!!");
//  return "sample";
//  }

//  @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
//  public String home(Model model) {
//      model.addAttribute("hello", "Hello Thymeleaf!!");
//      return "sample";
//  }


//同一アプリ内でマッピングが重複するとビルド時にエラーとなる。

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String index(Model model) {
//	  model.addAttribute("now", new Date().toString());
//	  model.addAttribute("user", "me");
//	  return "index";
//	}



}