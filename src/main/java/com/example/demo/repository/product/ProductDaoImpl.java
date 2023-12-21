package com.example.demo.repository.product;

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
import com.example.demo.exception.HandlingException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {
	
	private final JdbcTemplate jdbcTemplate;
	int check;

	@Override
	public List<Product> productList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM product INNER JOIN entryenterprise ");
		buf.append(" ON product.enterprise_id = entryenterprise.enterprise_id ");
		buf.append(" INNER JOIN producttype ");
		buf.append(" ON product.producttype_id = producttype.producttype_id ");
		buf.append(" INNER JOIN breed ");
		buf.append(" ON product.breed_id = breed.breed_id ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Product> productList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Enterprise entryenterprise = new Enterprise();
			entryenterprise.setEnterprisename((String)result.get("enterprisename"));
			
			Producttype producttype = new Producttype();
			producttype.setProducttype((String)result.get("producttype"));
			
			Breed breed = new Breed();
			breed.setBreed((String)result.get("breed"));
			
			Product product = new Product();
			product.setProduct_id((int)result.get("product_id"));
			product.setProducttype_id((int)result.get("producttype_id"));
			product.setEnterprise_id((int)result.get("enterprise_id"));
			if(result.get("breed_id") != null) {
				product.setBreed_id((int)result.get("breed_id"));
			}
			product.setSawingmethod((String)result.get("sawingmethod"));
			product.setMaterialtype((String)result.get("materialtype"));
			if(result.get("length") != null) {
				product.setLength((int)result.get("length"));
			}
			if(result.get("diameter_bottom") != null) {
				product.setDiameter_bottom((int)result.get("diameter_bottom"));
			}
			if(result.get("diameter_top") != null) {
				product.setDiameter_top((int)result.get("diameter_top"));
			}
			if(result.get("length_short") != null) {
				product.setLength_short((int)result.get("length_short"));
			}
			if(result.get("length_long") != null) {
				product.setLength_long((int)result.get("length_long"));
			}
			product.setJas((String)result.get("jas"));
			product.setProductdetail((String)result.get("productdetail"));
			product.setEntryenterprise(entryenterprise);
			product.setProducttype(producttype);
			product.setBreed(breed);
			
			productList.add(product);
			
		}
		
		return productList;
	}

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
	public List<Product> filteredProductList(int entryenterprise_id, int producttype_id, int breed_id) {
		StringBuffer buf = new StringBuffer();
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM product INNER JOIN entryenterprise ");
		buf.append(" ON product.enterprise_id = entryenterprise.enterprise_id ");
		buf.append(" INNER JOIN producttype ");
		buf.append(" ON product.producttype_id = producttype.producttype_id ");
		buf.append(" INNER JOIN breed ");
		buf.append(" ON product.breed_id = breed.breed_id ");
		
		if(entryenterprise_id != 0 && producttype_id != 0 && breed_id != 0) {
			buf.append(" WHERE product.enterprise_id = ? AND ");
			buf.append(" product.producttype_id = ? AND ");
			buf.append(" product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(),entryenterprise_id,producttype_id,breed_id);
		}else if(entryenterprise_id != 0 && producttype_id != 0 && breed_id == 0) {
			buf.append(" WHERE product.enterprise_id = ? AND ");
			buf.append(" product.producttype_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(),entryenterprise_id,producttype_id);
		}else if(entryenterprise_id != 0 && producttype_id == 0 && breed_id != 0) {
			buf.append(" WHERE product.enterprise_id = ? AND ");
			buf.append(" product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(),entryenterprise_id,breed_id);
		}else if(entryenterprise_id == 0 && producttype_id != 0 && breed_id != 0) {
			buf.append(" WHERE product.producttype_id = ? AND ");
			buf.append(" product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(),producttype_id,breed_id);
		}else if(entryenterprise_id != 0 && producttype_id == 0 && breed_id == 0) {
			buf.append(" WHERE product.enterprise_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(),entryenterprise_id);
		}else if(entryenterprise_id == 0 && producttype_id != 0 && breed_id == 0) {
			buf.append(" WHERE product.producttype_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(),producttype_id);
		}else if(entryenterprise_id == 0 && producttype_id == 0 && breed_id != 0) {
			buf.append(" WHERE product.breed_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(),breed_id);
		}else {
			resultList = jdbcTemplate.queryForList(buf.toString());
		}
		
		List<Product> productList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Enterprise entryenterprise = new Enterprise();
			entryenterprise.setEnterprisename((String)result.get("enterprisename"));
			
			Producttype producttype = new Producttype();
			producttype.setProducttype((String)result.get("producttype"));
			
			Breed breed = new Breed();
			breed.setBreed((String)result.get("breed"));
			
			Product product = new Product();
			product.setProduct_id((int)result.get("product_id"));
			product.setProducttype_id((int)result.get("producttype_id"));
			product.setEnterprise_id((int)result.get("enterprise_id"));
			if(result.get("breed_id") != null) {
				product.setBreed_id((int)result.get("breed_id"));
			}
			product.setSawingmethod((String)result.get("sawingmethod"));
			product.setMaterialtype((String)result.get("materialtype"));
			if(result.get("length") != null) {
				product.setLength((int)result.get("length"));
			}
			if(result.get("diameter_bottom") != null) {
				product.setDiameter_bottom((int)result.get("diameter_bottom"));
			}
			if(result.get("diameter_top") != null) {
				product.setDiameter_top((int)result.get("diameter_top"));
			}
			if(result.get("length_short") != null) {
				product.setLength_short((int)result.get("length_short"));
			}
			if(result.get("length_long") != null) {
				product.setLength_long((int)result.get("length_long"));
			}
			product.setJas((String)result.get("jas"));
			product.setProductdetail((String)result.get("productdetail"));
			product.setEntryenterprise(entryenterprise);
			product.setProducttype(producttype);
			product.setBreed(breed);
			
			productList.add(product);
			
		}
		
		return productList;
	}

	@Override
	public Optional<Product> productDetail(int product_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM product INNER JOIN entryenterprise ");
		buf.append(" ON product.enterprise_id = entryenterprise.enterprise_id ");
		buf.append(" INNER JOIN producttype ");
		buf.append(" ON product.producttype_id = producttype.producttype_id ");
		buf.append(" INNER JOIN breed ");
		buf.append(" ON product.breed_id = breed.breed_id ");
		buf.append(" WHERE product.product_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), product_id);
		
		Enterprise entryenterprise = new Enterprise();
		entryenterprise.setEnterprisename((String)result.get("enterprisename"));

		Producttype producttype = new Producttype();
		producttype.setProducttype((String)result.get("producttype"));

		Breed breed = new Breed();
		breed.setBreed((String)result.get("breed"));

		Product product = new Product();
		product.setProduct_id((int)result.get("product_id"));
		product.setProducttype_id((int)result.get("producttype_id"));
		product.setEnterprise_id((int)result.get("enterprise_id"));
		if(result.get("breed_id") != null) {
			product.setBreed_id((int)result.get("breed_id"));
		}
		product.setSawingmethod((String)result.get("sawingmethod"));
		product.setMaterialtype((String)result.get("materialtype"));
		if(result.get("length") != null) {
			product.setLength((int)result.get("length"));
		}
		if(result.get("diameter_bottom") != null) {
			product.setDiameter_bottom((int)result.get("diameter_bottom"));
		}
		if(result.get("diameter_top") != null) {
			product.setDiameter_top((int)result.get("diameter_top"));
		}
		if(result.get("length_short") != null) {
			product.setLength_short((int)result.get("length_short"));
		}
		if(result.get("length_long") != null) {
			product.setLength_long((int)result.get("length_long"));
		}
		product.setJas((String)result.get("jas"));
		product.setProductdetail((String)result.get("productdetail"));
		product.setEntryenterprise(entryenterprise);
		product.setProducttype(producttype);
		product.setBreed(breed);
		
		Optional<Product> optProduct = Optional.ofNullable(product);
		return optProduct;
	}

	@Override
	public int productInsert(Product product) {
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO product(producttype_id, enterprise_id, breed_id, sawingmethod, materialtype, length, diameter_bottom, ");
		buf.append(" diameter_top, length_short, length_long, jas, productdetail) ");
		buf.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		
		check = jdbcTemplate.update(buf.toString(), product.getProducttype_id(), product.getEnterprise_id(), product.getBreed_id(),
				product.getSawingmethod(), product.getMaterialtype(), product.getLength(), product.getDiameter_bottom(),
				product.getDiameter_top(), product.getLength_short(), product.getLength_long(), product.getJas(), product.getProductdetail());
		return check;
	}

	@Override
	public int productUpdate(Product product) {
		StringBuffer buf = new StringBuffer();
		buf.append(" UPDATE product SET producttype_id = ?, enterprise_id = ?, breed_id = ?, sawingmethod = ?, materialtype = ?, length = ?, diameter_bottom = ?, ");
		buf.append(" diameter_top = ?, length_short = ?, length_long = ?, jas = ?, productdetail = ? ");
		buf.append(" WHERE product_id = ? ");
		
		check = jdbcTemplate.update(buf.toString(), product.getProducttype_id(), product.getEnterprise_id(), product.getBreed_id(),
				product.getSawingmethod(), product.getMaterialtype(), product.getLength(), product.getDiameter_bottom(),
				product.getDiameter_top(), product.getLength_short(), product.getLength_long(), product.getJas(), 
				product.getProductdetail(), product.getProduct_id());
		
		return check;
	}

	@Override
	public int productDelete(int product_id) {
		StringBuffer buf1 = new StringBuffer();
		buf1.append(" SELECT * FROM stock ");
		buf1.append(" WHERE product_id = ? ");
		List<Map<String, Object>> resultList1 = jdbcTemplate.queryForList(buf1.toString(), product_id);
		
		StringBuffer buf2 = new StringBuffer();
		buf2.append(" SELECT * FROM entrytrading ");
		buf2.append(" WHERE product_id = ? ");
		List<Map<String, Object>> resultList2 = jdbcTemplate.queryForList(buf2.toString(), product_id);
		
		StringBuffer buf3 = new StringBuffer();
		buf3.append(" SELECT * FROM purchasetrading ");
		buf3.append(" WHERE product_id = ? ");
		List<Map<String, Object>> resultList3 = jdbcTemplate.queryForList(buf3.toString(), product_id);
		
		StringBuffer buf4 = new StringBuffer();
		buf4.append(" SELECT * FROM salestrading ");
		buf4.append(" WHERE product_id = ? ");
		List<Map<String, Object>> resultList4 = jdbcTemplate.queryForList(buf4.toString(), product_id);
		
		if(resultList1.size() == 0 && resultList2.size() == 0 && resultList3.size() == 0 && resultList4.size() == 0) {
			StringBuffer buf5 = new StringBuffer();
			buf5.append(" DELETE FROM product ");
			buf5.append(" WHERE product_id = ? ");
			
			check = jdbcTemplate.update(buf5.toString(), product_id);
			
			return check;
		}else {
			throw new HandlingException("削除しようとしているレコードは別レコードに参照されているため削除できません");
		}
	}

}
