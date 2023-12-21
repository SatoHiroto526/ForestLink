package com.example.demo.service.stocktreasurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.component.sendmail.SendMail;
import com.example.demo.entity.Stock;
import com.example.demo.entity.Stocktreasurer;
import com.example.demo.entity.Treasure;
import com.example.demo.entity.User;
import com.example.demo.exception.HandlingException;
import com.example.demo.repository.stock.StockDao;
import com.example.demo.repository.stocktreasurer.StocktreasurerDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StocktreasurerServiceImpl implements StocktreasurerService {
	
	private final StocktreasurerDao stocktreasurerDao;
	private final StockDao stockDao;
	private final SendMail sendmail;

	@Override
	public List<Stocktreasurer> stocktreasurerList(int stock_id) {
		return stocktreasurerDao.stocktreasurerList(stock_id);
	}

	@Override
	public List<User> userList(int stock_id) {
		return stocktreasurerDao.userList(stock_id);
	}

	@Override
	public List<Treasure> treasureList() {
		List<Treasure> treasureList = new ArrayList<>();
		Treasure receiving = new Treasure();
		receiving.setTreasure("入庫");
		treasureList.add(receiving);
		Treasure issuing = new Treasure();
		issuing.setTreasure("出庫");
		treasureList.add(issuing);
		return treasureList;
	}

	@Override
	public void treasureInsert(Stocktreasurer stocktreasurer) {
		try {
			
			Stock stock = new Stock();
			Optional<Stock> optStock = stockDao.StockDetail(stocktreasurer.getStock_id());	
			if(optStock.isPresent()) {
				stock = optStock.get();
				int stockCount = stock.getStockcount();
				if(stocktreasurer.getTreasure().equals("出庫") && stockCount < stocktreasurer.getTreasurercount()) {
					throw new HandlingException("出庫数は在庫数以下でなければいけません");
				}else {
					
					if(stocktreasurer.getTreasure().equals("出庫")) {
						int treasurercount = -stocktreasurer.getTreasurercount();
						stocktreasurer.setTreasurercount(treasurercount);
					}
					
					if(stocktreasurerDao.stocktreasurerInsert(stocktreasurer) == 0) {
						throw new HandlingException("在庫出納データの追加処理が失敗しました");
					}
					
					if(stocktreasurerDao.executeStocktreasurer(stocktreasurer.getStock_id(),stocktreasurer.getTreasurercount()) == 0) {
						throw new HandlingException("在庫データの更新処理が失敗しました");
					}
					
					try {
						
						User user = new User();
						Optional<User> optUser = stocktreasurerDao.userDetail(stocktreasurer.getStock_id());	
						if(optUser.isPresent()) {
							user = optUser.get();
						}
						
						String username = user.getUsername();
						String useremail = user.getUseremail();
						String subject = "在庫ID：" + stocktreasurer.getStock_id() + "の在庫出納が行われました";
						String content = username + "様" + "\n\nForestLink運営局です。\n在庫ID：" + stocktreasurer.getStock_id() +
								"の在庫出納が行われました。\n在庫出納情報と出納後在庫情報をご確認くださいませ。\n\n【在庫出納情報】"
								+ "\n---------------------------------------" + 
								"\n入庫or出庫：" + stocktreasurer.getTreasure() + "\n入出庫数：" + stocktreasurer.getTreasurercount() +
								"\n入出庫日：" + stocktreasurer.getTreasureday() + "\n---------------------------------------" + 
								"\n\n【出納後在庫情報】\n---------------------------------------\nURL：" + 
								"https://satohiroto.com/forestlink/stock/detail/" + stocktreasurer.getStock_id() +
								"\n---------------------------------------\n\n---------------------------------------"
								+ "\nForestLink運営局" +
								"\nEmail：forestlinkapp@gmail.com" + "\n---------------------------------------";
						
						sendmail.sendMail(useremail, subject, content);
						
					}catch(EmptyResultDataAccessException e) {
						throw new HandlingException("エラーが発生しました");
					}
					
				}
			}
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("エラーが発生しました");
		}
	}

}
