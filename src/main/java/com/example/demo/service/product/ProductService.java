package com.example.demo.service.product;

import java.util.List;

import com.example.demo.entity.Breed;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Jas;
import com.example.demo.entity.Materialtype;
import com.example.demo.entity.Product;
import com.example.demo.entity.Producttype;
import com.example.demo.entity.Sawingmethod;

public interface ProductService {
	
	List<Product> productList();
	
	List<Enterprise> enterpriseList();
	
	List<Producttype> producttypeList();
	
	List<Breed> breedList();
	
	List<Product> filteredProductList(int entryenterprise_id, int producttype_id, int breed_id);
	
	List<Sawingmethod> sawingmethodList();
	
	List<Materialtype> materialtypeList();
	
	List<Jas> jasList();
	
	Product productDetail(int product_id);
	
	void productInsert(Product product);
	
	void productUpdate(Product product);
	
	void productDelete(int product_id);

}
