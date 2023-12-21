package com.example.demo.repository.stocktreasurer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.component.changeDataType.ChangeDataType;
import com.example.demo.entity.Stocktreasurer;
import com.example.demo.entity.User;
import com.example.demo.exception.HandlingException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StocktreasurerDaoImpl implements StocktreasurerDao {
	
	private final JdbcTemplate jdbcTemplate;
	private final ChangeDataType changeDataType;
	int check;

	@Override
	public List<Stocktreasurer> stocktreasurerList(int stock_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM stocktreasurer ");
		buf.append(" INNER JOIN user ");
		buf.append(" on stocktreasurer.repuser_id = user.user_id ");
		buf.append(" WHERE stocktreasurer.stock_id = ? ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString(), stock_id);
		
		List<Stocktreasurer> stocktreasurerList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			User user = new User();
			user.setUsername((String)result.get("username"));
			
			Stocktreasurer stocktreasurer = new Stocktreasurer();
			stocktreasurer.setStocktreasurer_id((int)result.get("stocktreasurer_id"));
			stocktreasurer.setStock_id((int)result.get("stock_id"));
			stocktreasurer.setRepuser_id((int)result.get("repuser_id"));
			stocktreasurer.setTreasure((String)result.get("treasure"));
			if(result.get("treasurercount") != null) {
				stocktreasurer.setTreasurercount((int)result.get("treasurercount"));
			}
			Date dataTreasureday = ((Date)result.get("treasureday"));
			stocktreasurer.setTreasureday(changeDataType.dateToString(dataTreasureday));
			stocktreasurer.setUser(user);
			
			stocktreasurerList.add(stocktreasurer);
		}
		return stocktreasurerList;
	}

	@Override
	public int stocktreasurerInsert(Stocktreasurer stocktreasurer) {
		try {
			StringBuffer buf = new StringBuffer();
			
			buf.append(" INSERT INTO stocktreasurer(stock_id, repuser_id, treasure, treasurercount, treasureday) ");
			buf.append(" VALUES(?,?,?,?,?) ");
			check = jdbcTemplate.update(buf.toString(), stocktreasurer.getStock_id(), stocktreasurer.getRepuser_id(), 
					stocktreasurer.getTreasure(), stocktreasurer.getTreasurercount(), stocktreasurer.getTreasureday());
			return check;
		}catch(DataIntegrityViolationException e) {
			throw new HandlingException("在庫出納データの追加処理が失敗しました");
		}
	}

	@Override
	public int executeStocktreasurer(int stock_id, int treasurercount) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" UPDATE stock set stockcount = stockcount + ? ");
		buf.append(" WHERE stock_id = ? ");
		check = jdbcTemplate.update(buf.toString(), treasurercount, stock_id);
		return check;
	}

	@Override
	public List<User> userList(int stock_id) {
		StringBuffer buf1 = new StringBuffer();
		buf1.append(" SELECT * FROM stock ");
		buf1.append(" WHERE stock_id = ? ");
		Map<String, Object> stockResult = jdbcTemplate.queryForMap(buf1.toString(), stock_id);
		int enterprise_id = (int)stockResult.get("enterprise_id");
		
		StringBuffer buf2 = new StringBuffer();
		buf2.append(" SELECT * FROM user ");
		buf2.append(" WHERE enterprise_id = ? ");
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf2.toString(), enterprise_id);
		
		List<User> userList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			User user = new User();
			user.setUser_id((int)result.get("user_id"));
			user.setUsername((String)result.get("username"));
			userList.add(user);
		}
		
		return userList;
	}

	@Override
	public Optional<User> userDetail(int stock_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT * FROM stock ");
		buf.append(" INNER JOIN user ");
		buf.append(" ON stock.liabilityuser_id = user.user_id ");
		buf.append(" WHERE stock.stock_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), stock_id);
		User user = new User();
		user.setUsername((String)result.get("username"));
		user.setUseremail((String)result.get("useremail"));
		Optional<User> optUser = Optional.ofNullable(user);
		return optUser;
	}

}
