package com.example.demo.repository.product;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Breed;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Product;
import com.example.demo.entity.Producttype;

public interface ProductDao {
	
	List<Product> productList();
	
	List<Enterprise> enterpriseList();
	
	List<Producttype> producttypeList();
	
	List<Breed> breedList();
	
	List<Product> filteredProductList(int entryenterprise_id, int producttype_id, int breed_id);
	
	Optional<Product> productDetail(int product_id);
	
	int productInsert(Product product);
	
	int productUpdate(Product product);
	
	int productDelete(int product_id);

}
