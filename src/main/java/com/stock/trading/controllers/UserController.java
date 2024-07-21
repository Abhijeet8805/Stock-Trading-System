package com.stock.trading.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stock.trading.dtos.PurchaseDTO;
import com.stock.trading.dtos.SaleOrderDTO;
import com.stock.trading.models.AccountTransaction;
import com.stock.trading.models.MarketSettings;
import com.stock.trading.models.SaleOrder;
import com.stock.trading.models.StockPrice;
import com.stock.trading.models.UserStock;
import com.stock.trading.services.AccountTransactionService;
import com.stock.trading.services.MarketSettingService;
import com.stock.trading.services.StockService;
import com.stock.trading.services.UserService;

@Controller
public class UserController {
	
	@Autowired private StockService ssrv;
	@Autowired private UserService usrv;
	@Autowired private HttpSession session;
	@Autowired private AccountTransactionService atsrv;
	@Autowired private MarketSettingService msrv;
	
	@GetMapping("/details/{id}")
	public String stockDetails(@PathVariable("id") int id,Model model)
	{		
		//ssrv.saveStockPrice(id);
		List<StockPrice> plist= ssrv.getPriceDetails(id);
		if(session.getAttribute("userid")!=null) {
			String userid=session.getAttribute("userid").toString();
			model.addAttribute("uinfo", usrv.findById(userid));
		}
		MarketSettings ms=msrv.getSettings();
		//LocalDate.now().getDayOfWeek()!=DayOfWeek.SATURDAY && LocalDate.now().getDayOfWeek()!=DayOfWeek.SUNDAY &&  
		if(ms.getStarttime().isBefore(LocalTime.now()) && ms.getEndtime().isAfter(LocalTime.now())) {
			model.addAttribute("open", true);
		}else {
			model.addAttribute("open", false);
		}
		List<Double> prices=new ArrayList<Double>();
		List<String> dates=new ArrayList<String>();
		for(StockPrice sp :plist) {
			prices.add(sp.getPrice());
			dates.add(sp.getCreatedon().getHour()+":"+sp.getCreatedon().getMinute());
		}
		model.addAttribute("values", prices);
		model.addAttribute("dates", dates);
		model.addAttribute("stk", plist.get(0));
		model.addAttribute("prices", plist);
		model.addAttribute("info",ssrv.findStockInfo(id));
		model.addAttribute("min", ssrv.minStockPrice(id));
		model.addAttribute("max", ssrv.maxStockPrice(id));
		return "stockdetails";
	}
	
	@PostMapping("/details/{id}")
	public String savePurchase(@PathVariable("id") int id,PurchaseDTO dto) {
		dto.setUserid(session.getAttribute("userid").toString());
		dto.setStockid(id);
		ssrv.buyStock(dto);
		return "redirect:/mystocks";
	}
	
	@GetMapping("/mystocks")
	public String userStocks(Model model) {
		String userid=session.getAttribute("userid").toString();
		model.addAttribute("list", ssrv.userStock(userid));
		return "mystocks";
	}
	@GetMapping("/addmoney")
	public String addmoney(Model model) {
		String userid=session.getAttribute("userid").toString();
		model.addAttribute("uinfo", usrv.findById(userid));
		return "addmoney";
	}
	
	@PostMapping("/addmoney")
	public String savemoney(int amount,RedirectAttributes ra) {
		String userid=session.getAttribute("userid").toString();
		usrv.updateWallet(amount, userid);
		AccountTransaction at=new AccountTransaction();
		at.setCredit(amount);
		at.setDescription("Amount added to wallet");
		atsrv.saveTran(at, userid);
		ra.addFlashAttribute("msg", "Rs. "+amount+" has been added successfully to your wallet");
		return "redirect:/addmoney";
	}
	
	@GetMapping("/history")
	public String history(Model model) {
		model.addAttribute("list", atsrv.userTrans(session.getAttribute("userid").toString()));
		return "tranhistory";
	}
	
	@GetMapping("/createorder/{id}")
	public String createsaleorder(@PathVariable("id") int id,Model model) {
		UserStock us=ssrv.findById(id);
		model.addAttribute("us",us);
		return "createsaleorder";
	}
	
	@GetMapping("/marketsale/{id}")
	public String createmarketsale(@PathVariable("id") int id,Model model) {
		UserStock us=ssrv.findById(id);
		model.addAttribute("us",us);
		return "marketsale";
	}
	
	@PostMapping("/marketsale/{id}")
	public String savemarketsale(@PathVariable("id") int id,PurchaseDTO dto,RedirectAttributes ra) {
		UserStock us=ssrv.findById(id);
		dto.setStockid(us.getStock().getId());	
		dto.setUserid(session.getAttribute("userid").toString());
		ssrv.saleStock(dto);
		ra.addFlashAttribute("msg", "Sold successfully");
		return "redirect:/mystocks";
	}
	
	@PostMapping("/createorder/{id}")
	public String saveOrder(@PathVariable("id") int id,SaleOrderDTO dto ,RedirectAttributes ra) {
		dto.setUserstockid(id);	
		dto.setUserid(session.getAttribute("userid").toString());
		ssrv.createSaleorder(dto);
		ra.addFlashAttribute("msg", "Sale Order Created successfully");
		return "redirect:/saleorders";
	}
	
	@GetMapping("/saleorders")
	public String saleOrders(Model model) {
		model.addAttribute("list", ssrv.listall());		
		return "saleorders";
	}
	
	@GetMapping("/cancelorder/{id}")
	public String cancelorder(@PathVariable("id") int id,RedirectAttributes ra) {
		ra.addFlashAttribute("msg", "Sale order cancelled");
		ssrv.cancelOrder(id);
		return "redirect:/saleorders";
	}
	
	@GetMapping("/buynow/{id}")
	public String buysaleorder(@PathVariable("id") int id,Model model) {
		SaleOrder so=ssrv.findsaleorder(id);
		if(so.getLastdate().isBefore(LocalDate.now())) {
			model.addAttribute("expire", true);
		}
		model.addAttribute("so",so);
		model.addAttribute("user", usrv.findById(session.getAttribute("userid").toString()));
		return "buynow";
	}
	
	@PostMapping("/buynow/{id}")
	public String buyordersave(@PathVariable("id") int id,PurchaseDTO dto) {
		dto.setUserid(session.getAttribute("userid").toString());
		SaleOrder so= ssrv.findsaleorder(id);
		dto.setSaleorderid(id);
		ssrv.buySaleOrder(dto);
		return "redirect:/mystocks";
	}
}
