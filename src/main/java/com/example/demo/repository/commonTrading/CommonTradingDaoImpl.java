package com.example.demo.repository.commonTrading;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommonTradingDaoImpl implements CommonTradingDao {
	
	private final JdbcTemplate jdbcTemplate;

	@Override
	public List<Enterprise> enterpriseList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM entryenterprise ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		List<Enterprise> enterpriseList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprise_id((int)result.get("enterprise_id"));
			enterprise.setEnterprisename((String)result.get("enterprisename"));
			enterpriseList.add(enterprise);
		}
		return enterpriseList;
	}

	@Override
	public List<User> userList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM user ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
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
	public List<Product> productList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM product ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		List<Product> productList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Product product = new Product();
			product.setProduct_id((int)result.get("product_id"));
			productList.add(product);
		}
		return productList;
	}

	@Override
	public List<Enterprise> partnerenterpriseList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM partnerenterprise ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		List<Enterprise> enterpriseList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprise_id((int)result.get("partnerenterprise_id"));
			enterprise.setEnterprisename((String)result.get("partnerenterprisename"));
			enterpriseList.add(enterprise);
		}
		return enterpriseList;
	}

	@Override
	public List<User> partneruserList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM partneruser ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		List<User> userList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			User user = new User();
			user.setUser_id((int)result.get("partneruser_id"));
			user.setUsername((String)result.get("partnerusername"));
			userList.add(user);
		}
		return userList;
	}

}
