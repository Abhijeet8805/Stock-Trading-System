package com.stock.trading.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.trading.dtos.PurchaseDTO;
import com.stock.trading.dtos.SaleOrderDTO;
import com.stock.trading.dtos.StockDTO;
import com.stock.trading.dtos.StockResponse;
import com.stock.trading.models.AccountTransaction;
import com.stock.trading.models.SaleOrder;
import com.stock.trading.models.Stock;
import com.stock.trading.models.StockPrice;
import com.stock.trading.models.User;
import com.stock.trading.models.UserStock;
import com.stock.trading.repository.SaleOrderRepository;
import com.stock.trading.repository.StockPriceRepository;
import com.stock.trading.repository.StockRepository;
import com.stock.trading.repository.UserStockRepository;

@Service
public class StockService {

	@Autowired private StockRepository repo;
	@Autowired private StockPriceRepository sprepo;
	@Autowired private UserService usrv;
	@Autowired private ServletContext ctx;
	@Autowired private AccountTransactionService atsrv;
	@Autowired private UserStockRepository usrepo;
	@Autowired private SaleOrderRepository sorepo;
	
	public void buyStock(PurchaseDTO dto) {
		System.out.println("I am here");
		User user=usrv.findById(dto.getUserid());
		Stock stk=repo.getById(dto.getStockid());
		
		UserStock us=usrepo.findByUserAndStock(user, stk);
		if(us==null) {
			us=new UserStock();
			us.setStock(stk);
			us.setUser(user);
			us.setQty(dto.getQty());
		}
		else {
			us.setQty(us.getQty()+dto.getQty());
		}
		usrepo.save(us);
		
		stk.setVolume(stk.getVolume()-dto.getQty());
		repo.save(stk);
		
		AccountTransaction at=new AccountTransaction();
		at.setDebit(dto.getAmount());
		at.setUser(user);
		at.setDescription("Stock purchased from market");
		atsrv.saveTran(at, dto.getUserid());
		
		user.setBalance(user.getBalance()-dto.getAmount());
		usrv.saveUser(user);
	}
	
	public void saleStock(PurchaseDTO dto) {
		User user=usrv.findById(dto.getUserid());
		Stock stk=repo.getById(dto.getStockid());
		
		UserStock us=usrepo.findByUserAndStock(user, stk);
		if(us.getQty()==dto.getQty()) {
			usrepo.delete(us);
		}else {
			us.setQty(us.getQty()-dto.getQty());
			usrepo.save(us);
		}
		stk.setVolume(stk.getVolume()+dto.getQty());
		repo.save(stk);
		double amount=dto.getQty()*stk.getPrice();
		
		AccountTransaction at=new AccountTransaction();
		at.setCredit((int)amount);
		at.setUser(user);
		at.setDescription("Stock sold to market");
		atsrv.saveTran(at, dto.getUserid());
		
		
		System.out.println("amount =="+amount);
		user.setBalance(user.getBalance()+(int)amount);
		usrv.saveUser(user);
	}
	
	public void buySaleOrder(PurchaseDTO dto) {
		User user=usrv.findById(dto.getUserid());
		SaleOrder so=sorepo.getById(dto.getSaleorderid());
		UserStock uss=so.getStock();
		
		Stock stk=uss.getStock();
		
		UserStock us=usrepo.findByUserAndStock(user, stk);
		if(us==null) {
			us=new UserStock();
			us.setStock(stk);
			us.setUser(user);
			us.setQty(dto.getQty());
		}
		else {
			us.setQty(us.getQty()+dto.getQty());
		}
		usrepo.save(us);
		uss.setQty(uss.getQty()-dto.getQty());
		usrepo.save(uss);
		
		AccountTransaction at=new AccountTransaction();
		at.setDebit(dto.getAmount());
		at.setUser(user);
		at.setDescription("Stock purchased from saleorder");
		atsrv.saveTran(at, dto.getUserid());
		
		AccountTransaction at2=new AccountTransaction();
		at2.setCredit(dto.getAmount());
		at2.setUser(uss.getUser());
		at2.setDescription("Stock sold from saleorder");
		atsrv.saveTran(at2, uss.getUser().getUserid());
		
		user.setBalance(user.getBalance()-dto.getAmount());
		usrv.saveUser(user);
		
		User us2= uss.getUser();
		us2.setBalance(us2.getBalance()+dto.getAmount());
		usrv.saveUser(us2);
		if(so.getQty()==dto.getQty()) {
			sorepo.delete(so);
		}else {
			so.setQty(so.getQty()-dto.getQty());
			sorepo.save(so);
		}
		
	}
	
	public void createSaleorder(SaleOrderDTO dto) {
		SaleOrder so=new SaleOrder();
		so.setUser(usrv.findById(dto.getUserid()));
		so.setDesiredprice(dto.getDesiredprice());
		so.setQty(dto.getQty());
		so.setLastdate(LocalDate.parse(dto.getLastdate()));
		so.setStock(usrepo.getById(dto.getUserstockid()));
		sorepo.save(so);
	}
	
	public UserStock findById(int id) {
		return usrepo.getById(id);
	}
	
	public void cancelOrder(int id) {
		sorepo.delete(sorepo.getById(id));
	}
	
	public SaleOrder findsaleorder(int id) {
		return sorepo.getById(id);
	}
	
	public List<SaleOrder> listall(){
		return sorepo.findAll();
	}
	
	public List<SaleOrder> userSaleorders(String userid){
		return sorepo.findByUser(usrv.findById(userid));
	}
	
	public List<UserStock> userStock(String userid){
		User user=usrv.findById(userid);
		return usrepo.findByUser(user);
	}
	
	public void saveStockPrice(int id) {
		Stock stk=repo.getById(id);
		StockPrice sp=new StockPrice();
		sp.setStock(stk);
		Random random=new Random();
		double delta=(stk.getPrice()*0.05)*(random.nextBoolean()?1:-1);
		System.out.println(delta);
		double nprice=stk.getPrice()+delta;
		System.out.println(nprice);
		sp.setPrice(nprice);
		stk.setDelta(delta);
		sprepo.save(sp);
		stk.setPrice(nprice);
		repo.save(stk);
	}
	
	public StockResponse findStockInfo(int id) {
		return repo.findStockById(id);
	}
	
	public long minStockPrice(int id) {
		return sprepo.min(id);
	}
	
	public long maxStockPrice(int id) {
		return sprepo.max(id);
	}
	
	public void saveStock(StockDTO dto) {
		Stock stock=StockDTO.toEntity(dto);
		Stock stk=repo.save(stock);
//		try {
//			Files.copy(dto.getPhoto().getInputStream(), Paths.get(ctx.getRealPath("/logo/"), stk.getId()+".jpg"),StandardCopyOption.REPLACE_EXISTING);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		stk.setLogo("/logo/"+ stk.getId()+".jpg");
		repo.save(stk);
		
		StockPrice sp=new StockPrice();
		sp.setStock(stk);
		sp.setPrice(stk.getPrice());
		sprepo.save(sp);
	}
	
	public List<StockResponse> listAll(){
		return repo.allRecords();
	}
	
	public List<Stock> listAllStocks(){
		return repo.findAll();
	}
	
	public List<StockPrice> getPriceDetails(int stkid){
		return sprepo.findByStock(stkid);
	}

}
