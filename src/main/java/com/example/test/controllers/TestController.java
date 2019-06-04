package com.example.test.controllers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.model.Employee;

@Controller
public class TestController {

	/**
	 * http://localhost:8080 will invoke this method
	 * By default, spring expects all methods of controller class to return some view(JSP/html) name.
	 * So to return string, we need to use @ResponseBody. This will tell spring that the returned value is not a viewName but actually it is body of response (e.g. string) 
	 */
	@RequestMapping("/")
	@ResponseBody
	public String test() {
		return "This is a test to return text as response rather than a view(jsp) page...!";
	}
	
	/**
	 * @ResponseBodyshould not be used when returning view(jsp/html) page
	 * By default, SpringBoot does not support JSP. So to use JSP, add dependency tomcat-jasper
	 */
	@RequestMapping("/page")
	public String testPage() {
//		return "test.html";
//		return "/pages/testViewPage.jsp";
		return "testViewPage";//This can be used when prefix/suffix are configured in application.properties
	}
	
	/**
	 * Accepting parameter as query string
	 * http://localhost:8080/data?name=John
	 */
	@RequestMapping("/data")
	public String testData(HttpServletRequest req) {
		String name = req.getParameter("name");
		System.out.println("name: "+ name);
//		req.setAttribute("name", name);//working
		//OR
		req.getSession().setAttribute("name", name);//working
		return "dataPage";//prefix/suffix are configured in application.properties
	}
	
		//http://localhost:8080/data1?name=John
		@RequestMapping("/data1")
//		public String testData1(String name, HttpSession session) {
		public String testData1(@RequestParam("name") String myName, HttpSession session) {
			System.out.println("data1 name: "+ myName);
			session.setAttribute("name", myName);//working
			return "dataPage";//prefix/suffix are configured in application.properties
		}
	
		//http://localhost:8080/mvtest?name=Fred
		@RequestMapping("/mvtest")
		public ModelAndView testModelView(@RequestParam("name") String myName) {
	//		ModelAndView mv = new ModelAndView("datePage");
			ModelAndView mv = new ModelAndView();
			mv.addObject("name", myName);
			mv.addObject("dateTime", LocalDateTime.now());
			mv.setViewName("dataPage");//prefix/suffix are configured in application.properties
	//		mv.setViewName("datePage");
			return mv;
		}
		
		/**
		 * Accepting parameter in path rather than as query string
		 * //http://localhost:8080/mvtest/Fred
		 */
		@RequestMapping("/mvtest1/{name}")
		public ModelAndView testModelView1(@PathVariable("name") String myName) {
	//		ModelAndView mv = new ModelAndView("datePage");
			ModelAndView mv = new ModelAndView();
			mv.addObject("name", myName);
			mv.addObject("dateTime", LocalDateTime.now());
			mv.setViewName("dataPage");//prefix/suffix are configured in application.properties
	//		mv.setViewName("datePage");
			return mv;
		}
		
		//http://localhost:8080/empdata?id=123&name=Fred&age=25&salary=25000
		@RequestMapping("empdata")
		public ModelAndView testModelView2(Employee employee) {
	//		ModelAndView mv = new ModelAndView("datePage");
			System.out.println(employee);
			
			ModelAndView mv = new ModelAndView();
			mv.addObject("emp", employee);
			mv.setViewName("empPage");//prefix/suffix are configured in application.properties
			return mv;
		}
}
