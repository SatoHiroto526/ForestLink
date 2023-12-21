package com.example.demo.service.home;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Home;
import com.example.demo.entity.Stock;
import com.example.demo.entity.Trading;
import com.example.demo.entity.User;
import com.example.demo.exception.HandlingException;
import com.example.demo.repository.home.HomeDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
	
	private final HomeDao homeDao;
	
	@Override
	public User userDetail(String useremail) {
		try {
			
			User user = new User();
			Optional<User> optUser = homeDao.userDetail(useremail);
			
			if(optUser.isPresent()) {
				user = optUser.get();
			}
			
			return user; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("ユーザーが見つかりません");
		}
	}

	@Override
	public Home purchase(int enterprise_id) {
        List<Trading> entryTradingPurchase = homeDao.entryTradingPurchase(enterprise_id);
        List<Trading> purchaseTrading = homeDao.purchaseTrading(enterprise_id);
        Home home = new Home();
        String lavel[] = new String[12];
        int data[] = new int[12];
        for(int i = 0; i < 12; i++) {
        	Trading etp = entryTradingPurchase.get(i);
        	Trading pt = purchaseTrading.get(i);
        	lavel[i] = etp.getPurchaseday();
        	data[i] = etp.getPrice() + pt.getPrice();
        }
        home.setLavel(lavel);
        home.setData(data);
		return home;
	}

	@Override
	public Home sales(int enterprise_id) {
		List<Trading> entryTradingSales = homeDao.entryTradingSales(enterprise_id);
        List<Trading> salesTrading = homeDao.salesTrading(enterprise_id);
        Home home = new Home();
        String lavel[] = new String[12];
        int data[] = new int[12];
        for(int i = 0; i < 12; i++) {
        	Trading ets = entryTradingSales.get(i);
        	Trading st = salesTrading.get(i);
        	lavel[i] = ets.getPurchaseday();
        	data[i] = ets.getPrice() + st.getPrice();
        }
        home.setLavel(lavel);
        home.setData(data);
		return home;
	}

	@Override
	public Home stockType(int enterprise_id) {
		List<Stock> stockList = homeDao.stockType(enterprise_id);
		Home home = new Home();
		String lavel[] = new String[stockList.size()];
		int data[] = new int[stockList.size()];
		for(int i = 0; i < stockList.size(); i++) {
			Stock stock = stockList.get(i);
			lavel[i] = stock.getStocktype();
			data[i] = stock.getStockcount();
		}
		home.setLavel(lavel);
        home.setData(data);
		return home;
	}

	@Override
	public Home breed(int enterprise_id) {
		List<Stock> stockList = homeDao.breed(enterprise_id);
		Home home = new Home();
		String lavel[] = new String[stockList.size()];
		int data[] = new int[stockList.size()];
		for(int i = 0; i < stockList.size(); i++) {
			Stock stock = stockList.get(i);
			lavel[i] = stock.getBreed().getBreed();
			data[i] = stock.getStockcount();
		}
		home.setLavel(lavel);
        home.setData(data);
		return home;
	}

}
