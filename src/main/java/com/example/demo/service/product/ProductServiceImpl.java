package com.example.demo.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Breed;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Jas;
import com.example.demo.entity.Materialtype;
import com.example.demo.entity.Product;
import com.example.demo.entity.Producttype;
import com.example.demo.entity.Sawingmethod;
import com.example.demo.exception.HandlingException;
import com.example.demo.repository.product.ProductDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private final ProductDao productDao;

	@Override
	public List<Product> productList() {
		return productDao.productList();
	}

	@Override
	public List<Enterprise> enterpriseList() {
		return productDao.enterpriseList();
	}

	@Override
	public List<Producttype> producttypeList() {
		return productDao.producttypeList();
	}

	@Override
	public List<Breed> breedList() {
		return productDao.breedList();
	}

	@Override
	public List<Product> filteredProductList(int entryenterprise_id, int producttype_id, int breed_id) {
		return productDao.filteredProductList(entryenterprise_id, producttype_id, breed_id);
	}

	@Override
	public Product productDetail(int product_id) {
		try {
			
			Product product = new Product();
			Optional<Product> optProduct = productDao.productDetail(product_id);	
			if(optProduct.isPresent()) {
				product = optProduct.get();
			}
			
			return product; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("製品が見つかりません");
		}
	}

	@Override
	public void productInsert(Product product) {
		if(productDao.productInsert(product) == 0) {
			throw new HandlingException("製品データの追加処理が失敗しました");
		}	
	}

	@Override
	public void productUpdate(Product product) {
		if(productDao.productUpdate(product) == 0) {
			throw new HandlingException("製品データの更新処理が失敗しました");
		}	
	}

	@Override
	public List<Sawingmethod> sawingmethodList() {
		List<Sawingmethod> sawingmethodList = new ArrayList<>();
		Sawingmethod solid = new Sawingmethod(); 
		solid.setSawingmethod("無垢材");
		sawingmethodList.add(solid);
		Sawingmethod laminated = new Sawingmethod(); 
		laminated.setSawingmethod("集成材");
		sawingmethodList.add(laminated);
		return sawingmethodList;
	}

	@Override
	public List<Materialtype> materialtypeList() {
		List<Materialtype> materialtypeList = new ArrayList<>();
		Materialtype round = new Materialtype();
		round.setMaterialtype("丸材");
		materialtypeList.add(round);
		Materialtype square = new Materialtype();
		square.setMaterialtype("角材");
		materialtypeList.add(square);
		return materialtypeList;
	}

	@Override
	public List<Jas> jasList() {
		List<Jas> jasList = new ArrayList<>();
		Jas yes = new Jas();
		yes.setJas("YES");
		jasList.add(yes);
		Jas no = new Jas();
		no.setJas("NO");
		jasList.add(no);
		return jasList;
	}

	@Override
	public void productDelete(int product_id) {
		if(productDao.productDelete(product_id) == 0) {
			throw new HandlingException("製品データの削除処理が失敗しました");
		}
	}

}
