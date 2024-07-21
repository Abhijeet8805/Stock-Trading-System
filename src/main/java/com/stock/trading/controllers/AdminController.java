package com.stock.trading.controllers;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stock.trading.dtos.StockDTO;
import com.stock.trading.models.MarketSettings;
import com.stock.trading.services.MarketSettingService;
import com.stock.trading.services.StockService;
import com.stock.trading.services.UserService;

@Controller
public class AdminController {

	@Autowired private StockService ssrv;
	@Autowired private MarketSettingService msrv;
	@Autowired private UserService usrv;
	
	@GetMapping("/addstock")
	public String addStock() {
		return "addstock";
	}
	
	@PostMapping("/addstock")
	public String saveStock(StockDTO dto,RedirectAttributes ra) {
		ssrv.saveStock(dto);
		ra.addFlashAttribute("msg", "Stock added successfully");
		return "redirect:/stocks";
	}
	
	@GetMapping("/updateprice/{id}")
	public String updatePrice(@PathVariable("id") int id) {
		ssrv.saveStockPrice(id);
		return "redirect:/details/"+id;
	}
	
	@GetMapping("/settings")
	public String settings(Model model) {
		model.addAttribute("ms", msrv.getSettings());
		return "marketsettings";
	}
	
	@PostMapping("/settings")
	public String updateSettings(String starttime,String endtime,RedirectAttributes ra) {
		MarketSettings ms=new MarketSettings();
		ms.setStarttime(LocalTime.parse(starttime));
		ms.setEndtime(LocalTime.parse(endtime));
		msrv.update(ms);
		ra.addFlashAttribute("msg", "Market time updated successfully");
		return "redirect:/settings";
	}
	
	@GetMapping("/users")
	public String users(Model model) {
		model.addAttribute("list", usrv.listAll());
		return "stockholders";
	}
	
}
