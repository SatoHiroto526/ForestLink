package com.example.demo.component.dataRefill;

import com.example.demo.entity.Breed;
import com.example.demo.entity.BusinessType;
import com.example.demo.entity.Contactmethod;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Partnerenterpriserank;
import com.example.demo.entity.Product;
import com.example.demo.entity.Producttype;
import com.example.demo.entity.Stock;
import com.example.demo.entity.Stockstatus;
import com.example.demo.entity.Stocktreasurer;
import com.example.demo.entity.Trading;
import com.example.demo.entity.User;
import com.example.demo.entity.Workrecord;
import com.example.demo.entity.Worktype;
import com.example.demo.form.enterprise.EnterpriseForm;
import com.example.demo.form.enterprise.WorkrecordForm;
import com.example.demo.form.masterData.BreedForm;
import com.example.demo.form.masterData.BusinesstypeForm;
import com.example.demo.form.masterData.ContactmethodForm;
import com.example.demo.form.masterData.PartnerenterpriserankForm;
import com.example.demo.form.masterData.ProducttypeForm;
import com.example.demo.form.masterData.StockstatusForm;
import com.example.demo.form.masterData.WorktypeForm;
import com.example.demo.form.product.ProductForm;
import com.example.demo.form.stock.StockForm;
import com.example.demo.form.stocktreasurer.StocktreasurerForm;
import com.example.demo.form.trading.TradingForm;
import com.example.demo.form.user.PartnerUserForm;
import com.example.demo.form.user.UserForm;

public interface DataRefill {
	
	UserForm makeUserForm(User user);
	
	User makeUser(UserForm userForm, int user_id);
	
	PartnerUserForm makePartnerUserForm(User user);
	
	User makeUser(PartnerUserForm partnerUserForm, int user_id);
	
	EnterpriseForm makeEntryEnterpriseForm(Enterprise enterprise);
	
	Enterprise makeEnterprise(EnterpriseForm enterpriseForm, int enterprise_id);
	
	BusinesstypeForm makeBusinesstypeForm(BusinessType businesstype);
	
	BusinessType makeBusinesstype(BusinesstypeForm businesstypeForm, int businesstype_id);
	
	PartnerenterpriserankForm makepartnerenterpriserankForm(Partnerenterpriserank partnerenterpriserank);
	
	Partnerenterpriserank makePartnerenterpriserank(PartnerenterpriserankForm partnerenterpriserankForm, int partnerenterpriserank_id);
	
	WorktypeForm makeWorktypeForm(Worktype worktype);
	
	Worktype makeWorktype(WorktypeForm worktypeForm, int worktype_id);
	
	ContactmethodForm makeContactmethodForm(Contactmethod contactmethod);
	
	Contactmethod makeContactmethod(ContactmethodForm contactmethodForm, int contactmethod_id);
	
	WorkrecordForm makeWorkrecordForm(Workrecord workrecord, int partnerenterprise_id);
	
	Workrecord makeWorkrecord(WorkrecordForm workrecordForm, int workrecord_id , int partnerenterprise_id);
	
	ProducttypeForm makeProducttypeForm(Producttype producttype); 
	
	Producttype makeProducttype(ProducttypeForm producttypeForm, int producttype_id); 
	
	BreedForm makeBreedForm(Breed breed);
	
	Breed makeBreed(BreedForm breedForm, int breed_id);
	
	ProductForm makeProductForm(Product product);
	
	Product makeProduct(ProductForm productForm, int product_id);
	
	StockstatusForm makeStockstatusForm(Stockstatus stockstatus);
	
	Stockstatus makeStockstatus(StockstatusForm stockstatusForm, int stockstatus_id);
	
	StockForm makeStockForm(Stock stock);
	
	Stock makeStock(StockForm stockForm, int stock_id);
	
	Stocktreasurer makeStocktreasurer(StocktreasurerForm stocktreasurerForm);
	
	TradingForm makeTradingForm(Trading trading);
	
	Trading makeTrading(TradingForm tradingForm, int trading_id);

}
