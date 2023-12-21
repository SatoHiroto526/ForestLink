package com.example.demo.service.purchaseTrading;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Product;
import com.example.demo.entity.Salesworktype;
import com.example.demo.entity.Trading;
import com.example.demo.entity.User;
import com.example.demo.exception.HandlingException;
import com.example.demo.repository.commonTrading.CommonTradingDao;
import com.example.demo.repository.purchaseTrading.PurchaseTradingDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseTradingServiceImpl implements PurchaseTradingService {
	
	private final PurchaseTradingDao purchaseTradingDao;
	private final CommonTradingDao commonTradingDao;

	@Override
	public List<Trading> tradingList() {
		return purchaseTradingDao.tradingList();
	}

	@Override
	public List<Trading> filteredTradingList(int purchaseenterprise_id, int salesenterprise_id) {
		return purchaseTradingDao.filteredTradingList(purchaseenterprise_id, salesenterprise_id);
	}

	@Override
	public Trading purchaseTradingDetail(int trading_id) {
		try {
			
			Trading trading = new Trading();
			Optional<Trading> optTrading = purchaseTradingDao.purchaseTradingDetail(trading_id);
			
			if(optTrading.isPresent()) {
				trading = optTrading.get();
			}
			
			return trading; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("外部取引先企業購買が見つかりません");
		}
	}

	@Override
	public Trading salesTradingDetail(int trading_id) {
		try {
			
			Trading trading = new Trading();
			Optional<Trading> optTrading = purchaseTradingDao.salesTradingDetail(trading_id);
			
			if(optTrading.isPresent()) {
				trading = optTrading.get();
			}
			
			return trading; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("外部取引先企業購買が見つかりません");
		}
	}

	@Override
	public void tradingInsert(Trading trading) {
		if(purchaseTradingDao.tradingInsert(trading) == 0) {
			throw new HandlingException("外部取引先企業購買データの追加処理が失敗しました");
		}else {
			
		}
	}

	@Override
	public void tradingUpdate(Trading trading) {
		if(purchaseTradingDao.tradingUpdate(trading) == 0) {
			throw new HandlingException("外部取引先企業購買データの更新処理が失敗しました");
		}else {
			
		}
	}

	@Override
	public void tradingDelete(int trading_id) {
		if(purchaseTradingDao.tradingDelete(trading_id) == 0) {
			throw new HandlingException("外部取引先企業購買データの削除処理が失敗しました");
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

	@Override
	public List<Enterprise> partnerenterpriseList() {
		return commonTradingDao.partnerenterpriseList();
	}

	@Override
	public List<User> partneruserList() {
		return commonTradingDao.partneruserList();
	}

	@Override
	public List<Salesworktype> salesworktypeList() {
		List<Salesworktype> salesworktypeList = new ArrayList<>();
		Salesworktype inbound = new Salesworktype();
		inbound.setSalesworktype("インバウンド");
		salesworktypeList.add(inbound);
		Salesworktype outbound = new Salesworktype();
		outbound.setSalesworktype("アウトバウンド");
		salesworktypeList.add(outbound);
		return salesworktypeList;
	}

}
