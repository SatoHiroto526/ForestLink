package com.example.demo.repository.home;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Breed;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Stock;
import com.example.demo.entity.Trading;
import com.example.demo.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HomeDaoImpl implements HomeDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public Optional<User> userDetail(String email) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM user INNER JOIN entryenterprise ");
		buf.append(" ON user.enterprise_id = entryenterprise.enterprise_id ");
		buf.append(" WHERE user.useremail = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), email);
		
		Enterprise enterprise = new Enterprise();
		enterprise.setEnterprise_id((int)result.get("enterprise_id"));
		enterprise.setEnterprisename((String)result.get("enterprisename"));
		
		User user = new User();
		user.setEnterprise(enterprise);
		
		Optional<User> optUser = Optional.ofNullable(user);
		
		return optUser;
	}

	@Override
	public List<Trading> entryTradingPurchase(int enterprise_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append("SELECT ");
		buf.append("    YEAR(date_range.date) AS YEAR, ");
		buf.append("    MONTH(date_range.date) AS MONTH, ");
		buf.append("    COALESCE(SUM(entrytrading.price), 0) AS SUM ");
		buf.append("FROM ( ");
		buf.append("    SELECT ");
		buf.append("        LAST_DAY(NOW() - INTERVAL (a.a + (10 * b.a) + (100 * c.a)) MONTH) AS date ");
		buf.append("    FROM ");
		buf.append("        (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a ");
		buf.append("        CROSS JOIN ");
		buf.append("        (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b ");
		buf.append("        CROSS JOIN ");
		buf.append("        (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c ");
		buf.append(") AS date_range ");
		buf.append("LEFT JOIN entrytrading ");
		buf.append("    ON YEAR(date_range.date) = YEAR(entrytrading.purchaseday) ");
		buf.append("    AND MONTH(date_range.date) = MONTH(entrytrading.purchaseday) ");
		buf.append("    AND entrytrading.purchaseenterprise_id = ? ");
		buf.append("WHERE ");
		buf.append("    date_range.date >= LAST_DAY(NOW() - INTERVAL 11 MONTH) ");
		buf.append("    AND date_range.date <= CURDATE() ");
		buf.append("    OR ( ");
		buf.append("        YEAR(date_range.date) = YEAR(CURDATE()) ");
		buf.append("        AND MONTH(date_range.date) = MONTH(CURDATE()) ");
		buf.append("    ) ");
		buf.append("GROUP BY ");
		buf.append("    YEAR(date_range.date), MONTH(date_range.date) ");
		buf.append("ORDER BY ");
		buf.append("    YEAR(date_range.date), MONTH(date_range.date);");

		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString(), enterprise_id);
		
		List<Trading> tradingList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Trading trading = new Trading();
			int year = ((int)result.get("YEAR"));
			int month = ((int)result.get("MONTH"));
			trading.setPurchaseday(year + "/" + month);
			trading.setPrice(((BigDecimal) result.get("SUM")).intValue());
			tradingList.add(trading);
		}
		
		return tradingList;
	}

	@Override
	public List<Trading> purchaseTrading(int enterprise_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append("SELECT ");
		buf.append("    YEAR(date_range.date) AS YEAR, ");
		buf.append("    MONTH(date_range.date) AS MONTH, ");
		buf.append("    COALESCE(SUM(purchasetrading.price), 0) AS SUM ");
		buf.append("FROM ( ");
		buf.append("    SELECT ");
		buf.append("        LAST_DAY(NOW() - INTERVAL (a.a + (10 * b.a) + (100 * c.a)) MONTH) AS date ");
		buf.append("    FROM ");
		buf.append("        (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a ");
		buf.append("        CROSS JOIN ");
		buf.append("        (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b ");
		buf.append("        CROSS JOIN ");
		buf.append("        (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c ");
		buf.append(") AS date_range ");
		buf.append("LEFT JOIN purchasetrading ");
		buf.append("    ON YEAR(date_range.date) = YEAR(purchasetrading.purchaseday) ");
		buf.append("    AND MONTH(date_range.date) = MONTH(purchasetrading.purchaseday) ");
		buf.append("    AND purchasetrading.purchaseenterprise_id = ? ");
		buf.append("WHERE ");
		buf.append("    date_range.date >= LAST_DAY(NOW() - INTERVAL 11 MONTH) ");
		buf.append("    AND date_range.date <= CURDATE() ");
		buf.append("    OR ( ");
		buf.append("        YEAR(date_range.date) = YEAR(CURDATE()) ");
		buf.append("        AND MONTH(date_range.date) = MONTH(CURDATE()) ");
		buf.append("    ) ");
		buf.append("GROUP BY ");
		buf.append("    YEAR(date_range.date), MONTH(date_range.date) ");
		buf.append("ORDER BY ");
		buf.append("    YEAR(date_range.date), MONTH(date_range.date);");

		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString(), enterprise_id);
		
		List<Trading> tradingList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Trading trading = new Trading();
			int year = ((int)result.get("YEAR"));
			int month = ((int)result.get("MONTH"));
			trading.setPurchaseday(year + "/" + month);
			trading.setPrice(((BigDecimal) result.get("SUM")).intValue());
			tradingList.add(trading);
		}
		
		return tradingList;
	}

	@Override
	public List<Trading> entryTradingSales(int enterprise_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append("SELECT ");
		buf.append("    YEAR(date_range.date) AS YEAR, ");
		buf.append("    MONTH(date_range.date) AS MONTH, ");
		buf.append("    COALESCE(SUM(entrytrading.price), 0) AS SUM ");
		buf.append("FROM ( ");
		buf.append("    SELECT ");
		buf.append("        LAST_DAY(NOW() - INTERVAL (a.a + (10 * b.a) + (100 * c.a)) MONTH) AS date ");
		buf.append("    FROM ");
		buf.append("        (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a ");
		buf.append("        CROSS JOIN ");
		buf.append("        (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b ");
		buf.append("        CROSS JOIN ");
		buf.append("        (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c ");
		buf.append(") AS date_range ");
		buf.append("LEFT JOIN entrytrading ");
		buf.append("    ON YEAR(date_range.date) = YEAR(entrytrading.purchaseday) ");
		buf.append("    AND MONTH(date_range.date) = MONTH(entrytrading.purchaseday) ");
		buf.append("    AND entrytrading.salesenterprise_id = ? ");
		buf.append("WHERE ");
		buf.append("    date_range.date >= LAST_DAY(NOW() - INTERVAL 11 MONTH) ");
		buf.append("    AND date_range.date <= CURDATE() ");
		buf.append("    OR ( ");
		buf.append("        YEAR(date_range.date) = YEAR(CURDATE()) ");
		buf.append("        AND MONTH(date_range.date) = MONTH(CURDATE()) ");
		buf.append("    ) ");
		buf.append("GROUP BY ");
		buf.append("    YEAR(date_range.date), MONTH(date_range.date) ");
		buf.append("ORDER BY ");
		buf.append("    YEAR(date_range.date), MONTH(date_range.date);");

		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString(), enterprise_id);
		
		List<Trading> tradingList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Trading trading = new Trading();
			int year = ((int)result.get("YEAR"));
			int month = ((int)result.get("MONTH"));
			trading.setPurchaseday(year + "/" + month);
			trading.setPrice(((BigDecimal) result.get("SUM")).intValue());
			tradingList.add(trading);
		}
		
		return tradingList;
	}

	@Override
	public List<Trading> salesTrading(int enterprise_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append("SELECT ");
		buf.append("    YEAR(date_range.date) AS YEAR, ");
		buf.append("    MONTH(date_range.date) AS MONTH, ");
		buf.append("    COALESCE(SUM(salestrading.price), 0) AS SUM ");
		buf.append("FROM ( ");
		buf.append("    SELECT ");
		buf.append("        LAST_DAY(NOW() - INTERVAL (a.a + (10 * b.a) + (100 * c.a)) MONTH) AS date ");
		buf.append("    FROM ");
		buf.append("        (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a ");
		buf.append("        CROSS JOIN ");
		buf.append("        (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b ");
		buf.append("        CROSS JOIN ");
		buf.append("        (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c ");
		buf.append(") AS date_range ");
		buf.append("LEFT JOIN salestrading ");
		buf.append("    ON YEAR(date_range.date) = YEAR(salestrading.purchaseday) ");
		buf.append("    AND MONTH(date_range.date) = MONTH(salestrading.purchaseday) ");
		buf.append("    AND salestrading.salesenterprise_id = ? ");
		buf.append("WHERE ");
		buf.append("    date_range.date >= LAST_DAY(NOW() - INTERVAL 11 MONTH) ");
		buf.append("    AND date_range.date <= CURDATE() ");
		buf.append("    OR ( ");
		buf.append("        YEAR(date_range.date) = YEAR(CURDATE()) ");
		buf.append("        AND MONTH(date_range.date) = MONTH(CURDATE()) ");
		buf.append("    ) ");
		buf.append("GROUP BY ");
		buf.append("    YEAR(date_range.date), MONTH(date_range.date) ");
		buf.append("ORDER BY ");
		buf.append("    YEAR(date_range.date), MONTH(date_range.date);");

		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString(), enterprise_id);
		
		List<Trading> tradingList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Trading trading = new Trading();
			int year = ((int)result.get("YEAR"));
			int month = ((int)result.get("MONTH"));
			trading.setPurchaseday(year + "/" + month);
			trading.setPrice(((BigDecimal) result.get("SUM")).intValue());
			tradingList.add(trading);
		}
		
		return tradingList;
	}

	@Override
	public List<Stock> stockType(int enterprise_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT SUM(stockcount) AS stockcount, stocktype ");
		buf.append(" FROM stock  ");
		buf.append(" WHERE archive = 'NO' AND enterprise_id = ? ");
		buf.append(" GROUP BY stocktype ");
		buf.append(" ORDER BY stockcount DESC ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString(), enterprise_id);
		
		List<Stock> stockList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Stock stock = new Stock();
			stock.setStocktype((String)result.get("stocktype"));
			stock.setStockcount(((BigDecimal)result.get("stockcount")).intValue());
			stockList.add(stock);
		}
		return stockList;
	}

	@Override
	public List<Stock> breed(int enterprise_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT product.breed_id, SUM(stock.stockcount) AS stockcount, breed.breed ");
		buf.append(" FROM product INNER JOIN stock ON product.product_id = stock.stock_id ");
		buf.append(" INNER JOIN breed ON product.breed_id = breed.breed_id ");
		buf.append(" WHERE stock.archive = 'NO' AND stock.enterprise_id = ? ");
		buf.append(" GROUP BY product.breed_id ");
		buf.append(" ORDER BY stockcount DESC ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString(), enterprise_id);
		
		List<Stock> stockList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Breed breed = new Breed();
			breed.setBreed((String)result.get("breed"));
			Stock stock = new Stock();
			stock.setStockcount(((BigDecimal)result.get("stockcount")).intValue());
			stock.setBreed(breed);
			stockList.add(stock);
		}
		return stockList;
	}

}
