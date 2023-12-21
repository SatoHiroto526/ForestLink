package com.example.demo.repository.entryTrading;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.component.changeDataType.ChangeDataType;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Trading;
import com.example.demo.entity.User;
import com.example.demo.exception.HandlingException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EntryTradingDaoImpl implements EntryTradingDao {
	
	private final JdbcTemplate jdbcTemplate;
	private final ChangeDataType changeDataType;
	int check;

	@Override
	public List<Trading> tradingList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM entrytrading ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Trading> tradingList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Trading trading = new Trading();
			trading.setTrading_id((int)result.get("trading_id"));
			trading.setProduct_id((int)result.get("product_id"));
			Date dataOrderday = ((Date)result.get("orderday"));
			trading.setOrderday(changeDataType.dateToString(dataOrderday));
			Date dataPurchaseday = ((Date)result.get("purchaseday"));
			trading.setPurchaseday(changeDataType.dateToString(dataPurchaseday));
			trading.setPurchaseenterprise_id((int)result.get("purchaseenterprise_id"));
			trading.setSalesenterprise_id((int)result.get("salesenterprise_id"));
			trading.setPurchaseuser_id((int)result.get("purchaseuser_id"));
			trading.setSalesuser_id((int)result.get("salesuser_id"));
			if(result.get("count") != null) {
				trading.setCount((int)result.get("count"));
			}
			if(result.get("price") != null) {
				trading.setPrice((int)result.get("price"));
			}
			tradingList.add(trading);
		}
		return tradingList;
	}

	@Override
	public List<Trading> filteredTradingList(int purchaseenterprise_id, int salesenterprise_id) {
		StringBuffer buf = new StringBuffer();
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM entrytrading ");
		
		if(purchaseenterprise_id != 0 && salesenterprise_id != 0) {
			buf.append(" WHERE purchaseenterprise_id = ? AND salesenterprise_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), purchaseenterprise_id, salesenterprise_id);
		}else if(purchaseenterprise_id != 0 && salesenterprise_id == 0) {
			buf.append(" WHERE purchaseenterprise_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), purchaseenterprise_id);
		}else if(purchaseenterprise_id == 0 && salesenterprise_id != 0) {
			buf.append(" WHERE salesenterprise_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), salesenterprise_id);
		}else {
			resultList = jdbcTemplate.queryForList(buf.toString());
		}
		
		List<Trading> tradingList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Trading trading = new Trading();
			trading.setTrading_id((int)result.get("trading_id"));
			trading.setProduct_id((int)result.get("product_id"));
			Date dataOrderday = ((Date)result.get("orderday"));
			trading.setOrderday(changeDataType.dateToString(dataOrderday));
			Date dataPurchaseday = ((Date)result.get("purchaseday"));
			trading.setPurchaseday(changeDataType.dateToString(dataPurchaseday));
			trading.setPurchaseenterprise_id((int)result.get("purchaseenterprise_id"));
			trading.setSalesenterprise_id((int)result.get("salesenterprise_id"));
			trading.setPurchaseuser_id((int)result.get("purchaseuser_id"));
			trading.setSalesuser_id((int)result.get("salesuser_id"));
			if(result.get("count") != null) {
				trading.setCount((int)result.get("count"));
			}
			if(result.get("price") != null) {
				trading.setPrice((int)result.get("price"));
			}
			tradingList.add(trading);
		}
		return tradingList;
	}

	@Override
	public Optional<Trading> purchaseTradingDetail(int trading_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM entrytrading ");
		buf.append(" INNER JOIN entryenterprise ");
		buf.append(" ON entrytrading.purchaseenterprise_id = entryenterprise.enterprise_id ");
		buf.append(" INNER JOIN user ");
		buf.append(" ON entrytrading.purchaseuser_id = user.user_id ");
		buf.append(" WHERE trading_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), trading_id);
		
		Enterprise enterprise = new Enterprise();
		enterprise.setEnterprisename((String)result.get("enterprisename"));
		
		User user = new User();
		user.setUsername((String)result.get("username"));

		Trading trading = new Trading();
		trading.setTrading_id((int)result.get("trading_id"));
		trading.setProduct_id((int)result.get("product_id"));
		Date dataOrderday = ((Date)result.get("orderday"));
		trading.setOrderday(changeDataType.dateToString(dataOrderday));
		Date dataPurchaseday = ((Date)result.get("purchaseday"));
		trading.setPurchaseday(changeDataType.dateToString(dataPurchaseday));
		trading.setPurchaseenterprise_id((int)result.get("purchaseenterprise_id"));
		trading.setSalesenterprise_id((int)result.get("salesenterprise_id"));
		trading.setPurchaseuser_id((int)result.get("purchaseuser_id"));
		trading.setSalesuser_id((int)result.get("salesuser_id"));
		if(result.get("count") != null) {
			trading.setCount((int)result.get("count"));
		}
		if(result.get("price") != null) {
			trading.setPrice((int)result.get("price"));
		}
		trading.setEnterprise(enterprise);
		trading.setUser(user);
		
		Optional<Trading> optTrading = Optional.ofNullable(trading);

		return optTrading;
	}

	@Override
	public Optional<Trading> salesTradingDetail(int trading_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM entrytrading ");
		buf.append(" INNER JOIN entryenterprise ");
		buf.append(" ON entrytrading.salesenterprise_id = entryenterprise.enterprise_id ");
		buf.append(" INNER JOIN user ");
		buf.append(" ON entrytrading.salesuser_id = user.user_id ");
		buf.append(" WHERE trading_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), trading_id);
		
		Enterprise enterprise = new Enterprise();
		enterprise.setEnterprisename((String)result.get("enterprisename"));
		
		User user = new User();
		user.setUsername((String)result.get("username"));

		Trading trading = new Trading();
		trading.setTrading_id((int)result.get("trading_id"));
		trading.setProduct_id((int)result.get("product_id"));
		Date dataOrderday = ((Date)result.get("orderday"));
		trading.setOrderday(changeDataType.dateToString(dataOrderday));
		Date dataPurchaseday = ((Date)result.get("purchaseday"));
		trading.setPurchaseday(changeDataType.dateToString(dataPurchaseday));
		trading.setPurchaseenterprise_id((int)result.get("purchaseenterprise_id"));
		trading.setSalesenterprise_id((int)result.get("salesenterprise_id"));
		trading.setPurchaseuser_id((int)result.get("purchaseuser_id"));
		trading.setSalesuser_id((int)result.get("salesuser_id"));
		if(result.get("count") != null) {
			trading.setCount((int)result.get("count"));
		}
		if(result.get("price") != null) {
			trading.setPrice((int)result.get("price"));
		}
		trading.setEnterprise(enterprise);
		trading.setUser(user);
		
		Optional<Trading> optTrading = Optional.ofNullable(trading);

		return optTrading;
	}

	@Override
	public int tradingInsert(Trading trading) {
		try {
			StringBuffer buf = new StringBuffer();
			
			buf.append(" INSERT INTO entrytrading(product_id, orderday, purchaseday, ");
			buf.append(" purchaseenterprise_id, salesenterprise_id, purchaseuser_id, salesuser_id, count, price) ");
			buf.append(" VALUES(?,?,?,?,?,?,?,?,?) ");
			check = jdbcTemplate.update(buf.toString(), trading.getProduct_id(), trading.getOrderday(), trading.getPurchaseday(), 
					trading.getPurchaseenterprise_id(), trading.getSalesenterprise_id(), trading.getPurchaseuser_id(), trading.getSalesuser_id(),
					trading.getCount(), trading.getPrice()); 
			return check;
		}catch(DataIntegrityViolationException e) {
			throw new HandlingException("参加企業内取引データの追加処理が失敗しました");
		}
	}

	@Override
	public int tradingUpdate(Trading trading) {
		try {
			StringBuffer buf = new StringBuffer();
			
			buf.append(" UPDATE entrytrading SET product_id = ?, orderday = ?, purchaseday = ?, ");
			buf.append(" purchaseenterprise_id = ?, salesenterprise_id = ?, purchaseuser_id = ?, salesuser_id = ?, count = ?, price = ? ");
			buf.append(" WHERE trading_id = ? ");
			check = jdbcTemplate.update(buf.toString(), trading.getProduct_id(), trading.getOrderday(), trading.getPurchaseday(), 
					trading.getPurchaseenterprise_id(), trading.getSalesenterprise_id(), trading.getPurchaseuser_id(),
					trading.getSalesuser_id(), trading.getCount(), trading.getPrice(), trading.getTrading_id()); 
			return check;
		}catch(DataIntegrityViolationException e) {
			throw new HandlingException("参加企業内取引データの更新処理が失敗しました");
		}
	}

	@Override
	public int tradingDelete(int trading_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" DELETE FROM entrytrading ");
		buf.append(" WHERE trading_id = ? ");
		check = jdbcTemplate.update(buf.toString(), trading_id);
		return check;
	}

}
