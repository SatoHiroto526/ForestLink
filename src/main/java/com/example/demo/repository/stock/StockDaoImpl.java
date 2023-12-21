package com.example.demo.repository.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Breed;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Product;
import com.example.demo.entity.Producttype;
import com.example.demo.entity.Stock;
import com.example.demo.entity.Stockstatus;
import com.example.demo.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StockDaoImpl implements StockDao {
	
	private final JdbcTemplate jdbcTemplate;
	int check;

	@Override
	public List<Enterprise> enterpriseList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM entryenterprise ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Enterprise> entryenterpriseList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Enterprise enterprise = new Enterprise(); 
			enterprise.setEnterprise_id((int)result.get("enterprise_id"));
			enterprise.setEnterprisename((String)result.get("enterprisename"));
			entryenterpriseList.add(enterprise);
		}
		return entryenterpriseList;
	}

	@Override
	public List<Stockstatus> stockstatusList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM stockstatus ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Stockstatus> stockstatusList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Stockstatus stockstatus = new Stockstatus();
			stockstatus.setStockstatus_id((int)result.get("stockstatus_id"));
			stockstatus.setStockstatus((String)result.get("stockstatus"));
			stockstatusList.add(stockstatus);
		}
		return stockstatusList;
	}

	@Override
	public List<Producttype> producttypeList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM producttype ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Producttype> producttypeList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Producttype producttype = new Producttype();
			producttype.setProducttype_id((int)result.get("producttype_id"));
			producttype.setProducttype((String)result.get("producttype"));
			producttypeList.add(producttype);
		}
		return producttypeList;
	}

	@Override
	public List<Breed> breedList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM breed ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Breed> breedList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Breed breed = new Breed();
			breed.setBreed_id((int)result.get("breed_id"));
			breed.setBreed((String)result.get("breed"));
			breedList.add(breed);
		}
		return breedList;
	}

	@Override
	public List<Stock> stockList(String archive) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM stock INNER JOIN stockstatus ");
		buf.append(" ON stock.stockstatus_id = stockstatus.stockstatus_id ");
		buf.append(" INNER JOIN entryenterprise ");
		buf.append(" ON stock.enterprise_id = entryenterprise.enterprise_id ");
		buf.append(" INNER JOIN user ");
		buf.append(" ON stock.liabilityuser_id = user.user_id ");
		buf.append(" INNER JOIN product ");
		buf.append(" ON stock.product_id = product.product_id ");
		buf.append(" INNER JOIN producttype ");
		buf.append(" ON product.producttype_id = producttype.producttype_id ");
		buf.append(" INNER JOIN breed ");
		buf.append(" ON product.breed_id = breed.breed_id ");
		buf.append(" WHERE archive = ? ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString(), archive);
		
		List<Stock> stockList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Stockstatus stockstatus = new Stockstatus();
			stockstatus.setStockstatus((String)result.get("stockstatus"));
			
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprisename((String)result.get("enterprisename"));
			
			User user = new User();
			user.setUsername((String)result.get("username"));
			
			Producttype producttype = new Producttype();
			producttype.setProducttype((String)result.get("producttype"));
			
			Breed breed = new Breed();
			breed.setBreed((String)result.get("breed"));
			
			Stock stock = new Stock();
			stock.setStock_id((int)result.get("stock_id"));
			stock.setEnterprise_id((int)result.get("enterprise_id"));
			stock.setProduct_id((int)result.get("product_id"));
			stock.setStocktype((String)result.get("stocktype"));
			stock.setStockstatus_id((int)result.get("stockstatus_id"));
			stock.setLiabilityuser_id((int)result.get("liabilityuser_id"));
			if(result.get("stockcount") != null) {
				stock.setStockcount((int)result.get("stockcount"));
			}
			stock.setArchive((String)result.get("archive"));
			stock.setStockstatus(stockstatus);
			stock.setEnterprise(enterprise);
			stock.setUser(user);
			stock.setProducttype(producttype);
			stock.setBreed(breed);
		
			stockList.add(stock);
		}
		return stockList;
	}

	@Override
	public List<Stock> filteredStockList(String archive, int enterprise_id, String stocktype, int stockstatus_id,
			int producttype_id, int breed_id) {
		StringBuffer buf = new StringBuffer();
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM stock INNER JOIN stockstatus ");
		buf.append(" ON stock.stockstatus_id = stockstatus.stockstatus_id ");
		buf.append(" INNER JOIN entryenterprise ");
		buf.append(" ON stock.enterprise_id = entryenterprise.enterprise_id ");
		buf.append(" INNER JOIN user ");
		buf.append(" ON stock.liabilityuser_id = user.user_id ");
		buf.append(" INNER JOIN product ");
		buf.append(" ON stock.product_id = product.product_id ");
		buf.append(" INNER JOIN producttype ");
		buf.append(" ON product.producttype_id = producttype.producttype_id ");
		buf.append(" INNER JOIN breed ");
		buf.append(" ON product.breed_id = breed.breed_id ");
		buf.append(" WHERE archive = ? ");
		
		if(enterprise_id != 0 && stocktype != null && stockstatus_id != 0 && producttype_id != 0 && breed_id != 0) {
			buf.append(" AND stock.enterprise_id = ? AND stock.stocktype = ? AND stock.stockstatus_id = ? ");
			buf.append(" AND product.producttype_id = ? AND product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id, stocktype, stockstatus_id,
					producttype_id, breed_id);
		}else if(enterprise_id != 0 && stocktype != null && stockstatus_id != 0 && producttype_id != 0 && breed_id == 0) {
			buf.append(" AND stock.enterprise_id = ? AND stock.stocktype = ? AND stock.stockstatus_id = ? ");
			buf.append(" AND product.producttype_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id, stocktype, stockstatus_id,
					producttype_id);
		}else if(enterprise_id != 0 && stocktype != null && stockstatus_id != 0 && producttype_id == 0 && breed_id != 0) {
			buf.append(" AND stock.enterprise_id = ? AND stock.stocktype = ? AND stock.stockstatus_id = ? ");
			buf.append(" AND product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id, stocktype, stockstatus_id,
					breed_id);
		}else if(enterprise_id != 0 && stocktype != null && stockstatus_id == 0 && producttype_id != 0 && breed_id != 0) {
			buf.append(" AND stock.enterprise_id = ? AND stock.stocktype = ? ");
			buf.append(" AND product.producttype_id = ? AND product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id, stocktype,
					producttype_id, breed_id);
		}else if(enterprise_id != 0 && stocktype == null && stockstatus_id != 0 && producttype_id != 0 && breed_id != 0) {
			buf.append(" AND stock.enterprise_id = ? AND stock.stockstatus_id = ? ");
			buf.append(" AND product.producttype_id = ? AND product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id, stockstatus_id,
					producttype_id, breed_id);
		}else if(enterprise_id == 0 && stocktype != null && stockstatus_id != 0 && producttype_id != 0 && breed_id != 0) {
			buf.append(" AND stock.stocktype = ? AND stock.stockstatus_id = ? ");
			buf.append(" AND product.producttype_id = ? AND product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, stocktype, stockstatus_id,
					producttype_id, breed_id);
		}else if(enterprise_id != 0 && stocktype != null && stockstatus_id != 0 && producttype_id == 0 && breed_id == 0) {
			buf.append(" AND stock.enterprise_id = ? AND stock.stocktype = ? AND stock.stockstatus_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id, stocktype, stockstatus_id);
		}else if(enterprise_id != 0 && stocktype != null && stockstatus_id == 0 && producttype_id != 0 && breed_id == 0) {
			buf.append(" AND stock.enterprise_id = ? AND stock.stocktype = ? ");
			buf.append(" AND product.producttype_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id, stocktype,
					producttype_id);
		}else if(enterprise_id != 0 && stocktype == null && stockstatus_id != 0 && producttype_id != 0 && breed_id == 0) {
			buf.append(" AND stock.enterprise_id = ? AND stock.stockstatus_id = ? ");
			buf.append(" AND product.producttype_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id, stockstatus_id,
					producttype_id);
		}else if(enterprise_id == 0 && stocktype != null && stockstatus_id != 0 && producttype_id != 0 && breed_id == 0) {
			buf.append(" AND stock.stocktype = ? AND stock.stockstatus_id = ? ");
			buf.append(" AND product.producttype_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, stocktype, stockstatus_id,
					producttype_id);
		}else if(enterprise_id != 0 && stocktype != null && stockstatus_id == 0 && producttype_id == 0 && breed_id != 0) {
			buf.append(" AND stock.enterprise_id = ? AND stock.stocktype = ? ");
			buf.append(" AND product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id, stockstatus_id,
					breed_id);
		}else if(enterprise_id != 0 && stocktype == null && stockstatus_id != 0 && producttype_id == 0 && breed_id != 0) {
			buf.append(" AND stock.enterprise_id = ? AND stock.stockstatus_id = ? ");
			buf.append(" AND product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id, stockstatus_id,
					breed_id);
		}else if(enterprise_id == 0 && stocktype != null && stockstatus_id != 0 && producttype_id == 0 && breed_id != 0) {
			buf.append(" AND stock.stocktype = ? AND stock.stockstatus_id = ? ");
			buf.append(" AND product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, stocktype, stockstatus_id,
					breed_id);
		}else if(enterprise_id != 0 && stocktype == null && stockstatus_id == 0 && producttype_id != 0 && breed_id != 0) {
			buf.append(" AND stock.enterprise_id = ? ");
			buf.append(" AND product.producttype_id = ? AND product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id,
					producttype_id, breed_id);
		}else if(enterprise_id == 0 && stocktype != null && stockstatus_id == 0 && producttype_id != 0 && breed_id != 0) {
			buf.append(" AND stock.stocktype = ? ");
			buf.append(" AND product.producttype_id = ? AND product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, stocktype,
					producttype_id, breed_id);
		}else if(enterprise_id == 0 && stocktype == null && stockstatus_id != 0 && producttype_id != 0 && breed_id != 0) {
			buf.append(" AND stock.stockstatus_id = ? ");
			buf.append(" AND product.producttype_id = ? AND product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, stockstatus_id,
					producttype_id, breed_id);
		}else if(enterprise_id == 0 && stocktype == null && stockstatus_id == 0 && producttype_id != 0 && breed_id != 0) {
			buf.append(" AND product.producttype_id = ? AND  product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive,
					producttype_id, breed_id);
		}else if(enterprise_id == 0 && stocktype == null && stockstatus_id != 0 && producttype_id == 0 && breed_id != 0) {
			buf.append(" AND stock.stockstatus_id = ? ");
			buf.append(" AND product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, stockstatus_id,
					breed_id);
		}else if(enterprise_id == 0 && stocktype != null && stockstatus_id == 0 && producttype_id == 0 && breed_id != 0) {
			buf.append(" AND stock.stocktype = ?");
			buf.append(" AND product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, stocktype,
					breed_id);
		}else if(enterprise_id != 0 && stocktype == null && stockstatus_id == 0 && producttype_id == 0 && breed_id != 0) {
			buf.append(" AND stock.enterprise_id = ? ");
			buf.append(" AND product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id,
					breed_id);
		}else if(enterprise_id == 0 && stocktype == null && stockstatus_id != 0 && producttype_id != 0 && breed_id == 0) {
			buf.append(" AND stock.stockstatus_id = ? ");
			buf.append(" AND product.producttype_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, stockstatus_id,
					producttype_id);
		}else if(enterprise_id == 0 && stocktype != null && stockstatus_id == 0 && producttype_id != 0 && breed_id == 0) {
			buf.append(" AND stock.stocktype = ? ");
			buf.append(" AND product.producttype_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, stocktype,
					producttype_id);
		}else if(enterprise_id != 0 && stocktype == null && stockstatus_id == 0 && producttype_id != 0 && breed_id == 0) {
			buf.append(" AND stock.enterprise_id = ? ");
			buf.append(" AND product.producttype_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id,
					producttype_id);
		}else if(enterprise_id == 0 && stocktype != null && stockstatus_id != 0 && producttype_id == 0 && breed_id == 0) {
			buf.append(" AND stock.stocktype = ? AND stock.stockstatus_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, stocktype, stockstatus_id);
		}else if(enterprise_id != 0 && stocktype == null && stockstatus_id != 0 && producttype_id == 0 && breed_id == 0) {
			buf.append(" AND stock.enterprise_id = ? AND stock.stockstatus_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id, stockstatus_id);
		}else if(enterprise_id != 0 && stocktype != null && stockstatus_id == 0 && producttype_id == 0 && breed_id == 0) {
			buf.append(" AND stock.enterprise_id = ? AND stock.stocktype = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id, stocktype);
		}else if(enterprise_id == 0 && stocktype == null && stockstatus_id == 0 && producttype_id == 0 && breed_id != 0) {
			buf.append(" AND product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, breed_id);
		}else if(enterprise_id == 0 && stocktype == null && stockstatus_id == 0 && producttype_id != 0 && breed_id == 0) {
			buf.append(" AND product.producttype_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, producttype_id);
		}else if(enterprise_id == 0 && stocktype == null && stockstatus_id != 0 && producttype_id == 0 && breed_id == 0) {
			buf.append(" AND stock.stockstatus_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, stockstatus_id);
		}else if(enterprise_id == 0 && stocktype != null && stockstatus_id == 0 && producttype_id == 0 && breed_id == 0) {
			buf.append(" AND stock.stocktype = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, stocktype);
		}else if(enterprise_id != 0 && stocktype == null && stockstatus_id == 0 && producttype_id == 0 && breed_id == 0) {
			buf.append(" AND stock.enterprise_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), archive, enterprise_id);
		}else{
			resultList = jdbcTemplate.queryForList(buf.toString(), archive);
		}
		
		List<Stock> stockList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Stockstatus stockstatus = new Stockstatus();
			stockstatus.setStockstatus((String)result.get("stockstatus"));
			
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprisename((String)result.get("enterprisename"));
			
			User user = new User();
			user.setUsername((String)result.get("username"));
			
			Producttype producttype = new Producttype();
			producttype.setProducttype((String)result.get("producttype"));
			
			Breed breed = new Breed();
			breed.setBreed((String)result.get("breed"));
			
			Stock stock = new Stock();
			stock.setStock_id((int)result.get("stock_id"));
			stock.setEnterprise_id((int)result.get("enterprise_id"));
			stock.setProduct_id((int)result.get("product_id"));
			stock.setStocktype((String)result.get("stocktype"));
			stock.setStockstatus_id((int)result.get("stockstatus_id"));
			stock.setLiabilityuser_id((int)result.get("liabilityuser_id"));
			if(result.get("stockcount") != null) {
				stock.setStockcount((int)result.get("stockcount"));
			}
			stock.setArchive((String)result.get("archive"));
			stock.setStockstatus(stockstatus);
			stock.setEnterprise(enterprise);
			stock.setUser(user);
			stock.setProducttype(producttype);
			stock.setBreed(breed);
		
			stockList.add(stock);
		}
		return stockList;
	}

	@Override
	public Optional<Stock> StockDetail(int stock_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM stock INNER JOIN stockstatus ");
		buf.append(" ON stock.stockstatus_id = stockstatus.stockstatus_id ");
		buf.append(" INNER JOIN entryenterprise ");
		buf.append(" ON stock.enterprise_id = entryenterprise.enterprise_id ");
		buf.append(" INNER JOIN user ");
		buf.append(" ON stock.liabilityuser_id = user.user_id ");
		buf.append(" INNER JOIN product ");
		buf.append(" ON stock.product_id = product.product_id ");
		buf.append(" INNER JOIN producttype ");
		buf.append(" ON product.producttype_id = producttype.producttype_id ");
		buf.append(" INNER JOIN breed ");
		buf.append(" ON product.breed_id = breed.breed_id ");
		buf.append(" WHERE stock_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), stock_id);

		Stockstatus stockstatus = new Stockstatus();
		stockstatus.setStockstatus((String)result.get("stockstatus"));

		Enterprise enterprise = new Enterprise();
		enterprise.setEnterprisename((String)result.get("enterprisename"));

		User user = new User();
		user.setUsername((String)result.get("username"));

		Producttype producttype = new Producttype();
		producttype.setProducttype((String)result.get("producttype"));

		Breed breed = new Breed();
		breed.setBreed((String)result.get("breed"));

		Stock stock = new Stock();
		stock.setStock_id((int)result.get("stock_id"));
		stock.setEnterprise_id((int)result.get("enterprise_id"));
		stock.setProduct_id((int)result.get("product_id"));
		stock.setStocktype((String)result.get("stocktype"));
		stock.setStockstatus_id((int)result.get("stockstatus_id"));
		stock.setLiabilityuser_id((int)result.get("liabilityuser_id"));
		if(result.get("stockcount") != null) {
			stock.setStockcount((int)result.get("stockcount"));
		}
		stock.setArchive((String)result.get("archive"));
		stock.setStockstatus(stockstatus);
		stock.setEnterprise(enterprise);
		stock.setUser(user);
		stock.setProducttype(producttype);
		stock.setBreed(breed);
		
		Optional<Stock> optStock = Optional.ofNullable(stock);
		
		return optStock;
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
	public int stockInsert(Stock stock) {
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO stock ");
		buf.append(" (enterprise_id, product_id, stocktype, stockstatus_id, liabilityuser_id, stockcount , archive) ");
		buf.append(" VALUES(?,?,?,?,?,?,?) ");
		check = jdbcTemplate.update(buf.toString(), stock.getEnterprise_id(), stock.getProduct_id(), stock.getStocktype(), 
				stock.getStockstatus_id(), stock.getLiabilityuser_id(), 0, stock.getArchive());
		return check;
	}

	@Override
	public int stockUpdate(Stock stock) {
		StringBuffer buf = new StringBuffer();
		buf.append(" UPDATE stock ");
		buf.append(" SET enterprise_id = ?, product_id = ?, stocktype = ?, stockstatus_id = ?, liabilityuser_id = ?, archive = ? ");
		buf.append(" WHERE stock_id = ? ");
		check = jdbcTemplate.update(buf.toString(), stock.getEnterprise_id(), stock.getProduct_id(), stock.getStocktype(), 
				stock.getStockstatus_id(), stock.getLiabilityuser_id(), stock.getArchive(), stock.getStock_id());
		return check;
	}

}
