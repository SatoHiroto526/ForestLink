package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Product;
import com.example.demo.entity.Stock;
import com.example.demo.entity.Stocktreasurer;
import com.example.demo.entity.Trading;
import com.example.demo.entity.User;
import com.example.demo.entity.Workrecord;
import com.example.demo.service.entryEnterprise.EntryEnterpriseService;
import com.example.demo.service.entryTrading.EntryTradingService;
import com.example.demo.service.partnerEnterprise.PartnerEnterpriseService;
import com.example.demo.service.partnerUser.PartnerUserService;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.purchaseTrading.PurchaseTradingService;
import com.example.demo.service.salesTradingService.SalesTradingService;
import com.example.demo.service.stock.StockService;
import com.example.demo.service.stocktreasurer.StocktreasurerService;
import com.example.demo.service.user.UserService;
import com.example.demo.service.workRecord.WorkRecordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestfullAPIController {
	
	private final EntryEnterpriseService entryEnterpriseService;
	private final UserService userService;
	private final PartnerEnterpriseService partnerEnterpriseService;
	private final PartnerUserService partnerUserService;
	private final WorkRecordService workRecordService;
	private final ProductService productService;
	private final StockService stockService;
	private final StocktreasurerService stocktreasurerService;
	private final EntryTradingService entryTradingService;
	private final PurchaseTradingService purchaseTradingService;
	private final SalesTradingService salesTradingService;
	
	@GetMapping("/entryEnterprise")
	public List<Enterprise> entryEnterprise() {
		return entryEnterpriseService.entryEnterpriseList();
	}
	
	@GetMapping("/user")
	public List<User> user() {
		return userService.userAll();
	}
	
	@GetMapping("/partnerEnterprise")
	public List<Enterprise> partnerEnterprise() {
		return partnerEnterpriseService.partnerEnterpriseList();
	}
	
	@GetMapping("/partnerUser")
	public List<User> partnerUser() {
		return partnerUserService.userAll();
	}
	
	@GetMapping("/workRecord/{enterprise_id}")
	public List<Workrecord> workRecord(@PathVariable int enterprise_id){
		return workRecordService.workrecordAll(enterprise_id);
	}
	
	@GetMapping("/product")
	public List<Product> product(){
		return productService.productList();
	}
	
	@GetMapping("/stock/{archive}")
	public List<Stock> stockService(@PathVariable String archive){
		return stockService.stockList(archive);
	}
	
	@GetMapping("/stocktreasurer/{stock_id}")
	public List<Stocktreasurer> stocktreasurerService(@PathVariable int stock_id){
		return stocktreasurerService.stocktreasurerList(stock_id);
	}
	
	@GetMapping("/entryTrading")
	public List<Trading> entryTradingService(){
		return entryTradingService.tradingList();
	}
	
	@GetMapping("/purchaseTrading")
	public List<Trading> purchaseTradingService(){
		return purchaseTradingService.tradingList();
	}
	
	@GetMapping("/salesTrading")
	public List<Trading> salesTradingService(){
		return salesTradingService.tradingList();
	}

}
