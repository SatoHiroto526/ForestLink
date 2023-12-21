package com.example.demo.service.entryTrading;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Product;
import com.example.demo.entity.Trading;
import com.example.demo.entity.User;
import com.example.demo.exception.HandlingException;
import com.example.demo.repository.commonTrading.CommonTradingDao;
import com.example.demo.repository.entryTrading.EntryTradingDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntryTradingServiceImpl implements EntryTradingService {
	
	private final EntryTradingDao entryTradingDao;
	private final CommonTradingDao commonTradingDao;

	@Override
	public List<Trading> tradingList() {
		return entryTradingDao.tradingList();
	}

	@Override
	public List<Trading> filteredTradingList(int purchaseenterprise_id, int salesenterprise_id) {
		return entryTradingDao.filteredTradingList(purchaseenterprise_id, salesenterprise_id);
	}

	@Override
	public Trading purchaseTradingDetail(int trading_id) {
		try {
			
			Trading trading = new Trading();
			Optional<Trading> optTrading = entryTradingDao.purchaseTradingDetail(trading_id);
			
			if(optTrading.isPresent()) {
				trading = optTrading.get();
			}
			
			return trading; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("参加企業内取引が見つかりません");
		}
	}

	@Override
	public Trading salesTradingDetail(int trading_id) {
		try {
			
			Trading trading = new Trading();
			Optional<Trading> optTrading = entryTradingDao.salesTradingDetail(trading_id);
			
			if(optTrading.isPresent()) {
				trading = optTrading.get();
			}
			
			return trading; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("参加企業内取引が見つかりません");
		}
	}

	@Override
	public void tradingInsert(Trading trading) {
		if(entryTradingDao.tradingInsert(trading) == 0) {
			throw new HandlingException("参加企業内取引データの追加処理が失敗しました");
		}else {
			
		}
	}

	@Override
	public void tradingUpdate(Trading trading) {
		if(entryTradingDao.tradingUpdate(trading) == 0) {
			throw new HandlingException("参加企業内取引データの更新処理が失敗しました");
		}else {
			
		}
	}

	@Override
	public void tradingDelete(int trading_id) {
		if(entryTradingDao.tradingDelete(trading_id) == 0) {
			throw new HandlingException("参加企業内取引データの削除処理が失敗しました");
		}else {
			
		}
	}

	@Override
	public List<Enterprise> enterpriseList() {
		return commonTradingDao.enterpriseList();
	}

	@Override
	public List<User> userList() {
		return commonTradingDao.userList();
	}

	@Override
	public List<Product> productList() {
		return commonTradingDao.productList();
	}

}
