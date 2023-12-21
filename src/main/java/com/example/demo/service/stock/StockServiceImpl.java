package com.example.demo.service.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Archive;
import com.example.demo.entity.Breed;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Product;
import com.example.demo.entity.Producttype;
import com.example.demo.entity.Stock;
import com.example.demo.entity.Stockstatus;
import com.example.demo.entity.Stocktype;
import com.example.demo.entity.User;
import com.example.demo.exception.HandlingException;
import com.example.demo.repository.stock.StockDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
	
	private final StockDao stockDao;

	@Override
	public List<Enterprise> enterpriseList() {
		return stockDao.enterpriseList();
	}

	@Override
	public List<Stockstatus> stockstatusList() {
		return stockDao.stockstatusList();
	}

	@Override
	public List<Producttype> producttypeList() {
		return stockDao.producttypeList();
	}

	@Override
	public List<Breed> breedList() {
		return stockDao.breedList();
	}

	@Override
	public List<Stock> stockList(String archive) {
		return stockDao.stockList(archive);
	}

	@Override
	public List<Stocktype> stocktypeList() {
		List<Stocktype> stocktypeList = new ArrayList<>();
		Stocktype product = new Stocktype();
		product.setStocktype("製品在庫");
		stocktypeList.add(product);
		Stocktype material = new Stocktype();
		material.setStocktype("原料在庫");
		stocktypeList.add(material);
		Stocktype semiproduct = new Stocktype();
		semiproduct.setStocktype("半製品在庫");
		stocktypeList.add(semiproduct);
		Stocktype production = new Stocktype();
		production.setStocktype("製造中在庫");
		stocktypeList.add(production);
		return stocktypeList;
	}

	@Override
	public List<Stock> filteredStockList(String archive, int enterprise_id, String stocktype, int stockstatus_id,
			int producttype_id, int breed_id) {
		return stockDao.filteredStockList(archive, enterprise_id, stocktype, stockstatus_id, producttype_id, breed_id);
	}

	@Override
	public Stock stockDetail(int stock_id) {
		try {
			
			Stock stock = new Stock();
			Optional<Stock> optStock = stockDao.StockDetail(stock_id);
			
			if(optStock.isPresent()) {
				stock = optStock.get();
			}
			
			return stock; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("在庫が見つかりません");
		}
	}

	@Override
	public List<Archive> archiveList() {
		List<Archive> archiveList = new ArrayList<>();
		Archive no = new Archive();
		no.setArchive("NO");
		archiveList.add(no);
		Archive yes = new Archive();
		yes.setArchive("YES");
		archiveList.add(yes);
		return archiveList;
	}

	@Override
	public List<Product> productList() {
		return stockDao.productList();
	}

	@Override
	public List<User> userList() {
		return stockDao.userList();
	}

	@Override
	public void stockInsert(Stock stock) {
		if(stockDao.stockInsert(stock) == 0) {
			throw new HandlingException("在庫データの追加処理が失敗しました");
		}
	}

	@Override
	public void stockUpdate(Stock stock) {
		if(stockDao.stockUpdate(stock) == 0) {
			throw new HandlingException("在庫データの更新処理が失敗しました");
		}
	}

}
