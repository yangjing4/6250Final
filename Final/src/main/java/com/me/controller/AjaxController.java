package com.me.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ajax/*")
public class AjaxController {
	ArrayList<String> categoryList;
	public AjaxController(){
		categoryList = new ArrayList<String>();
		categoryList.add("Music");
		categoryList.add("Sports");
		categoryList.add("Art");
		categoryList.add("AED");
		categoryList.add("Web tools");
		categoryList.add("Web Design");
		categoryList.add("Cloud computing");
		categoryList.add("Data Science");
	}
	
	@RequestMapping(value = "/ajax/ajaxservice.htm", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxService(HttpServletRequest request)
	{
		String queryString = request.getParameter("category");
		String result = "";
		for(int i =0;i<categoryList.size();i++){
			if(categoryList.get(i).toLowerCase().contains(queryString.toLowerCase())){
				result +=categoryList.get(i)+",";
			}
		}
		
		return result;
	}
	
	@RequestMapping(value = "/ajax/injectionError",method=RequestMethod.GET)
	public ModelAndView error() throws Exception {
		return new ModelAndView("injectionError");	
	}

}
