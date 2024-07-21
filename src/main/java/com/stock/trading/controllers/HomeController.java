package com.stock.trading.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stock.trading.models.Stock;
import com.stock.trading.models.User;
import com.stock.trading.services.StockService;
import com.stock.trading.services.UserService;

@Controller
public class HomeController {

	@Autowired HttpSession session;
	@Autowired UserService uservice;
	@Autowired StockService ssrv;
	
	private static final Logger logger=LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/")
	public String homepage() {
		return "index";
	}
	
	@GetMapping("/login")
	public String loginpage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String customerregister() {
		return "register";
	}
	
	@PostMapping("/register")
	public String saveUser(User user) {
		user.setRole("Customer");
		uservice.saveUser(user);
		return "redirect:/login";
	}
	
	@PostMapping("/login")
	public String validate(String userid,String pwd,RedirectAttributes ra) {
		User user=uservice.validate(userid, pwd);
		if(user==null) {
			ra.addFlashAttribute("error", "Invalid username or password");
		}else {
			session.setAttribute("userid", user.getUserid());
			session.setAttribute("role", user.getRole());
			session.setAttribute("uname", user.getUsername());
		}
		return "redirect:/";
	}
	
	@GetMapping("/stocks")
	public String stocks(Model model) {		
		model.addAttribute("prods", ssrv.listAll());
//		for(Stock sr: ssrv.listAllStocks() ) {
//			ssrv.saveStockPrice(sr.getId());
//		}
		return "stocks";
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@Scheduled(initialDelay = 10000,fixedDelay = 5000)
	public void UpdatePrices() {
		logger.info(" == Price update start == ");
		for(Stock sr: ssrv.listAllStocks() ) {
			ssrv.saveStockPrice(sr.getId());
		}
		logger.info(" == Price updated end == ");
	}
}
