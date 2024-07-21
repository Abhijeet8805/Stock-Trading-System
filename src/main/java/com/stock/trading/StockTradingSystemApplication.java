package com.stock.trading;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.stock.trading.models.MarketSettings;
import com.stock.trading.models.User;
import com.stock.trading.services.MarketSettingService;
import com.stock.trading.services.UserService;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class StockTradingSystemApplication {
	
	private static final Logger log = LoggerFactory.getLogger(StockTradingSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StockTradingSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserService srv,MarketSettingService msrv) {
	    return (args) -> {
	    	if(srv.countUser()==0) {
	    		User user=new User();
	    		user.setUserid("admin");
	    		user.setPwd("admin");
	    		user.setRole("Admin");
	    		user.setUsername("Administrator");
	    		srv.saveUser(user);
	    		log.info("Admin user created successfully");
	    		
	    		
	    	}
	    	
	    	if(msrv.count()==0) {
	    		MarketSettings ms=new MarketSettings();
	    		ms.setStarttime(LocalTime.of(10, 0));
	    		ms.setEndtime(LocalTime.of(16, 00));
	    		msrv.save(ms);
	    	}
	    };
	}
	
}
