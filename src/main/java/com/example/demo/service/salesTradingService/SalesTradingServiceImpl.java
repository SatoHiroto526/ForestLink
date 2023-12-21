package com.example.demo.service.salesTradingService;

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
import com.example.demo.repository.salesTradingDao.SalesTradingDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesTradingServiceImpl implements SalesTradingService {
	
	private final SalesTradingDao salesTradingDao;
	private final CommonTradingDao commonTradingDao;

	@Override
	public List<Trading> tradingList() {
		return salesTradingDao.tradingList();
	}

	@Override
	public List<Trading> filteredTradingList(int purchaseenterprise_id, int salesenterprise_id) {
		return salesTradingDao.filteredTradingList(purchaseenterprise_id, salesenterprise_id);
	}

	@Override
	public Trading purchaseTradingDetail(int trading_id) {
		try {
			
			Trading trading = new Trading();
			Optional<Trading> optTrading = salesTradingDao.purchaseTradingDetail(trading_id);
			
			if(optTrading.isPresent()) {
				trading = optTrading.get();
			}
			
			return trading; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("外部取引先企業販売が見つかりません");
		}
	}

	@Override
	public Trading salesTradingDetail(int trading_id) {
		try {
			
			Trading trading = new Trading();
			Optional<Trading> optTrading = salesTradingDao.salesTradingDetail(trading_id);
			
			if(optTrading.isPresent()) {
				trading = optTrading.get();
			}
			
			return trading; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("外部取引先企業販売が見つかりません");
		}
	}

	@Override
	public void tradingInsert(Trading trading) {
		if(salesTradingDao.tradingInsert(trading) == 0) {
			throw new HandlingException("外部取引先企業販売データの追加処理が失敗しました");
		}else {
			
		}
	}

	@Override
	public void tradingUpdate(Trading trading) {
		if(salesTradingDao.tradingUpdate(trading) == 0) {
			throw new HandlingException("外部取引先企業販売データの更新処理が失敗しました");
		}else {
			
		}
	}

	@Override
	public void tradingDelete(int trading_id) {
		if(salesTradingDao.tradingDelete(trading_id) == 0) {
			throw new HandlingException("外部取引先企業販売データの削除処理が失敗しました");
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
