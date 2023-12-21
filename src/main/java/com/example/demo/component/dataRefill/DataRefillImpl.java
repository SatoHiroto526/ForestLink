package com.example.demo.component.dataRefill;

import org.springframework.stereotype.Component;

import com.example.demo.component.changeDataType.ChangeDataType;
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

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataRefillImpl implements DataRefill {
	
	private final ChangeDataType changeDataType;

	@Override
	public UserForm makeUserForm(User user) {
		
		UserForm userForm = new UserForm();
		userForm.setUser_id(user.getUser_id());
		userForm.setAuthority(user.getAuthority());
		userForm.setEnterprise_id(user.getEnterprise_id());
		userForm.setUsername(user.getUsername());
		userForm.setUseremail(user.getUseremail());
		userForm.setUsernumber(user.getUsernumber());
		
		return userForm;
	}
	
	@Override
	public User makeUser(UserForm userForm, int user_id) {
		
		User user = new User();
		if(user_id != 0) {
			user.setUser_id(user_id);
		}
		user.setAuthority(userForm.getAuthority());
		user.setEnterprise_id(userForm.getEnterprise_id());
		user.setUsername(userForm.getUsername());
		user.setPassword(userForm.getPassword());
		user.setUseremail(userForm.getUseremail());
		user.setUsernumber(userForm.getUsernumber());
		
		return user;
	}
	
	
	@Override
	public PartnerUserForm makePartnerUserForm(User user) {
		PartnerUserForm userForm = new PartnerUserForm();
		userForm.setUser_id(user.getUser_id());
		userForm.setEnterprise_id(user.getEnterprise_id());
		userForm.setUsername(user.getUsername());
		userForm.setUseremail(user.getUseremail());
		userForm.setUsernumber(user.getUsernumber());
		
		return userForm;
	}

	@Override
	public User makeUser(PartnerUserForm partnerUserForm, int user_id) {
		User user = new User();
		if(user_id != 0) {
			user.setUser_id(user_id);
		}
		user.setEnterprise_id(partnerUserForm.getEnterprise_id());
		user.setUsername(partnerUserForm.getUsername());
		user.setUseremail(partnerUserForm.getUseremail());
		user.setUsernumber(partnerUserForm.getUsernumber());
		
		return user;
	}
	

	@Override
	public EnterpriseForm makeEntryEnterpriseForm(Enterprise enterprise) {
		
		EnterpriseForm enterpriseForm = new EnterpriseForm();
		enterpriseForm.setEnterprise_id(enterprise.getEnterprise_id());
		enterpriseForm.setEnterprisename(enterprise.getEnterprisename());
		enterpriseForm.setPostalcode(enterprise.getPostalcode());
		enterpriseForm.setAddress(enterprise.getAddress());
		if(enterprise.getFoundnationdate() != "") {
			enterpriseForm.setFoundnationdate(changeDataType.changeDataFormat(enterprise.getFoundnationdate()));
		}
		enterpriseForm.setChiefname(enterprise.getChiefname());
		enterpriseForm.setCapital(enterprise.getCapital());
		enterpriseForm.setCorporatenumber(enterprise.getCorporatenumber());
		enterpriseForm.setEmployees(enterprise.getEmployees());
		enterpriseForm.setBusinesstype_id(enterprise.getBusinesstype_id());
		enterpriseForm.setBusinessdetail(enterprise.getBusinessdetail());
		enterpriseForm.setEnterprisenumber(enterprise.getEnterprisenumber());
		enterpriseForm.setEnterpriseemail(enterprise.getEnterpriseemail());
		enterpriseForm.setHomepage(enterprise.getHomepage());
		enterpriseForm.setPartnerenterpriserank_id(enterprise.getPartnerenterpriserank_id());
		enterpriseForm.setMainenterprise_id(enterprise.getMainenterprise_id());
		
		return enterpriseForm;
	}
	
	@Override
	public Enterprise makeEnterprise(EnterpriseForm enterpriseForm, int enterprise_id) {
		Enterprise enterprise = new Enterprise();
		if(enterprise_id != 0) {
			enterprise.setEnterprise_id(enterprise_id);
		}
		enterprise.setEnterprisename(enterpriseForm.getEnterprisename());
		enterprise.setPostalcode(enterpriseForm.getPostalcode());
		enterprise.setAddress(enterpriseForm.getAddress());
		enterprise.setFoundnationdate(enterpriseForm.getFoundnationdate());
		enterprise.setChiefname(enterpriseForm.getChiefname());
		enterprise.setCapital(enterpriseForm.getCapital());
		enterprise.setCorporatenumber(enterpriseForm.getCorporatenumber());
		enterprise.setEmployees(enterpriseForm.getEmployees());
		enterprise.setBusinesstype_id(enterpriseForm.getBusinesstype_id());
		enterprise.setBusinessdetail(enterpriseForm.getBusinessdetail());
		enterprise.setEnterprisenumber(enterpriseForm.getEnterprisenumber());
		enterprise.setEnterpriseemail(enterpriseForm.getEnterpriseemail());
		enterprise.setHomepage(enterpriseForm.getHomepage());
		enterprise.setPartnerenterpriserank_id(enterpriseForm.getPartnerenterpriserank_id());
		enterprise.setMainenterprise_id(enterpriseForm.getMainenterprise_id());
		
		return enterprise;
	}

	@Override
	public BusinesstypeForm makeBusinesstypeForm(BusinessType businesstype) {
		BusinesstypeForm businesstypeForm = new BusinesstypeForm();
		businesstypeForm.setBusinesstype(businesstype.getBusinesstype());
		return businesstypeForm;
	}

	@Override
	public BusinessType makeBusinesstype(BusinesstypeForm businesstypeForm, int businesstype_id) {
		BusinessType businesstype = new BusinessType();
		if(businesstype_id != 0) {
			businesstype.setBusinesstype_id(businesstype_id);
		}
		
		businesstype.setBusinesstype(businesstypeForm.getBusinesstype());
		
		return businesstype;
	}

	@Override
	public PartnerenterpriserankForm makepartnerenterpriserankForm(Partnerenterpriserank partnerenterpriserank) {
		PartnerenterpriserankForm partnerenterpriserankForm = new PartnerenterpriserankForm();
		partnerenterpriserankForm.setPartnerenterpriserankdetail(partnerenterpriserank.getPartnerenterpriserankdetail());
		return partnerenterpriserankForm;
	}

	@Override
	public Partnerenterpriserank makePartnerenterpriserank(PartnerenterpriserankForm partnerenterpriserankForm,
		int partnerenterpriserank_id) {
		Partnerenterpriserank partnerenterpriserank = new Partnerenterpriserank();
		if(partnerenterpriserank_id != 0) {
			partnerenterpriserank.setPartnerenterpriserank_id(partnerenterpriserank_id);
		}
		partnerenterpriserank.setPartnerenterpriserankdetail(partnerenterpriserankForm.getPartnerenterpriserankdetail());
		return partnerenterpriserank;
	}

	@Override
	public WorktypeForm makeWorktypeForm(Worktype worktype) {
		WorktypeForm worktypeForm = new WorktypeForm();
		worktypeForm.setWorktype(worktype.getWorktype());
		return worktypeForm;
	}

	@Override
	public Worktype makeWorktype(WorktypeForm worktypeForm, int worktype_id) {
		Worktype worktype = new Worktype();
		if(worktype_id != 0) {
			worktype.setWorktype_id(worktype_id);
		}
		worktype.setWorktype(worktypeForm.getWorktype());
		return worktype;
	}

	@Override
	public ContactmethodForm makeContactmethodForm(Contactmethod contactmethod) {
		ContactmethodForm contactmethodForm = new ContactmethodForm();
		contactmethodForm.setContactmethod(contactmethod.getContactmethod());
		return contactmethodForm;
	}

	@Override
	public Contactmethod makeContactmethod(ContactmethodForm contactmethodForm, int contactmethod_id) {
		Contactmethod contactmethod = new Contactmethod();
		if(contactmethod_id != 0) {
			contactmethod.setContactmethod_id(contactmethod_id);
		}
		contactmethod.setContactmethod(contactmethodForm.getContactmethod());
		return contactmethod;
	}

	@Override
	public WorkrecordForm makeWorkrecordForm(Workrecord workrecord, int partnerenterprise_id) {
		WorkrecordForm workrecordForm = new WorkrecordForm();
		workrecordForm.setWorktype_id(workrecord.getWorktype_id());
		workrecordForm.setSalesworktype(workrecord.getSalesworktype());
		workrecordForm.setEnterprise_id(workrecord.getEnterprise_id());
		workrecordForm.setUser_id(workrecord.getUser_id());
		workrecordForm.setPartneruser_id(workrecord.getPartneruser_id());
		workrecordForm.setContactmethod_id(workrecord.getContactmethod_id());
		workrecordForm.setWorkrecorddetail(workrecord.getWorkrecorddetail());
		if(workrecord.getWorkrecordday() != "") {
			workrecordForm.setWorkrecordday(changeDataType.changeDataFormat(workrecord.getWorkrecordday()));
		}
		return workrecordForm;
	}

	@Override
	public Workrecord makeWorkrecord(WorkrecordForm workrecordForm, int workrecord_id, int partnerenterprise_id) {
		Workrecord workrecord = new Workrecord();
		if(workrecord_id != 0) {
			workrecord.setWorkrecord_id(workrecord_id);
		}
		workrecord.setWorktype_id(workrecordForm.getWorktype_id());
		workrecord.setSalesworktype(workrecordForm.getSalesworktype());
		workrecord.setEnterprise_id(workrecordForm.getEnterprise_id());
		workrecord.setPartnerenterprise_id(partnerenterprise_id);
		workrecord.setUser_id(workrecordForm.getUser_id());
		workrecord.setPartneruser_id(workrecordForm.getPartneruser_id());
		workrecord.setContactmethod_id(workrecordForm.getContactmethod_id());
		workrecord.setWorkrecorddetail(workrecordForm.getWorkrecorddetail());
		workrecord.setWorkrecordday(workrecordForm.getWorkrecordday());
		return workrecord;
	}

	@Override
	public ProducttypeForm makeProducttypeForm(Producttype producttype) {
		ProducttypeForm producttypeForm = new ProducttypeForm();
		producttypeForm.setProducttype(producttype.getProducttype());
		return producttypeForm;
	}

	@Override
	public Producttype makeProducttype(ProducttypeForm producttypeForm, int producttype_id) {
		Producttype producttype = new Producttype();
		if(producttype_id != 0) {
			producttype.setProducttype_id(producttype_id);
		}
		producttype.setProducttype(producttypeForm.getProducttype());
		return producttype;
	}

	@Override
	public BreedForm makeBreedForm(Breed breed) {
		BreedForm breedForm = new BreedForm();
		breedForm.setBreed(breed.getBreed());
		return breedForm;
	}

	@Override
	public Breed makeBreed(BreedForm breedForm, int breed_id) {
		Breed breed = new Breed();
		if(breed_id != 0) {
			breed.setBreed_id(breed_id);
		}
		breed.setBreed(breedForm.getBreed());
		return breed;
	}

	@Override
	public ProductForm makeProductForm(Product product) {
		ProductForm productForm = new ProductForm();
		productForm.setProducttype_id(product.getProducttype_id());
		productForm.setEnterprise_id(product.getEnterprise_id());
		productForm.setBreed_id(product.getBreed_id());
		productForm.setSawingmethod(product.getSawingmethod());
		productForm.setMaterialtype(product.getMaterialtype());		
		productForm.setLength(product.getLength());
		productForm.setDiameter_bottom(product.getDiameter_bottom());
		productForm.setDiameter_top(product.getDiameter_top());
		productForm.setLength_short(product.getLength_short());
		productForm.setLength_long(product.getLength_long());
		productForm.setJas(product.getJas());
		productForm.setProductdetail(product.getProductdetail());
		return productForm;
	}

	@Override
	public Product makeProduct(ProductForm productForm, int product_id) {
		Product product = new Product();
		if(product_id != 0) {
			product.setProduct_id(product_id);
		}
		product.setProducttype_id(productForm.getProducttype_id());
		product.setEnterprise_id(productForm.getEnterprise_id());
		product.setBreed_id(productForm.getBreed_id());
		product.setSawingmethod(productForm.getSawingmethod());
		product.setMaterialtype(productForm.getMaterialtype());
		product.setLength(productForm.getLength());
		product.setDiameter_bottom(productForm.getDiameter_bottom());
		product.setDiameter_top(productForm.getDiameter_top());
		product.setLength_short(productForm.getLength_short());
		product.setLength_long(productForm.getLength_long());
		product.setJas(productForm.getJas());
		product.setProductdetail(productForm.getProductdetail());
		return product;
	}

	@Override
	public StockstatusForm makeStockstatusForm(Stockstatus stockstatus) {
		StockstatusForm stockstatusForm = new StockstatusForm();
		stockstatusForm.setStockstatus(stockstatus.getStockstatus());
		return stockstatusForm;
	}

	@Override
	public Stockstatus makeStockstatus(StockstatusForm stockstatusForm, int stockstatus_id) {
		Stockstatus stockstatus = new Stockstatus();
		if(stockstatus_id != 0) {
			stockstatus.setStockstatus_id(stockstatus_id);
		}
		stockstatus.setStockstatus(stockstatusForm.getStockstatus());
		return stockstatus;
	}

	@Override
	public StockForm makeStockForm(Stock stock) {
		StockForm stockForm = new StockForm();
		stockForm.setEnterprise_id(stock.getEnterprise_id());
		stockForm.setProduct_id(stock.getProduct_id());
		stockForm.setStocktype(stock.getStocktype());
		stockForm.setStockstatus_id(stock.getStockstatus_id());
		stockForm.setLiabilityuser_id(stock.getLiabilityuser_id());
		stockForm.setArchive(stock.getArchive());
		return stockForm;
	}

	@Override
	public Stock makeStock(StockForm stockForm, int stock_id) {
		Stock stock = new Stock();
		if(stock_id != 0) {
			stock.setStock_id(stock_id);
		}
		stock.setEnterprise_id(stockForm.getEnterprise_id());
		stock.setProduct_id(stockForm.getProduct_id());
		stock.setStocktype(stockForm.getStocktype());
		stock.setStockstatus_id(stockForm.getStockstatus_id());
		stock.setLiabilityuser_id(stockForm.getLiabilityuser_id());
		stock.setArchive(stockForm.getArchive());
		return stock;
	}

	@Override
	public Stocktreasurer makeStocktreasurer(StocktreasurerForm stocktreasurerForm) {
		Stocktreasurer stocktreasurer = new Stocktreasurer();
		stocktreasurer.setStock_id(stocktreasurerForm.getStock_id());
		stocktreasurer.setRepuser_id(stocktreasurerForm.getRepuser_id());
		stocktreasurer.setTreasure(stocktreasurerForm.getTreasure());
		stocktreasurer.setTreasurercount(stocktreasurerForm.getTreasurercount());
		stocktreasurer.setTreasureday(stocktreasurerForm.getTreasureday());	
		return stocktreasurer;
	}

	@Override
	public TradingForm makeTradingForm(Trading trading) {
		TradingForm tradingForm = new TradingForm();
		tradingForm.setProduct_id(trading.getProduct_id());
		if(trading.getOrderday() != "") {
			tradingForm.setOrderday(changeDataType.changeDataFormat(trading.getOrderday()));
		}
		if(trading.getPurchaseday() != "") {
			tradingForm.setPurchaseday(changeDataType.changeDataFormat(trading.getPurchaseday()));
		}
		tradingForm.setPurchaseenterprise_id(trading.getPurchaseenterprise_id());
		tradingForm.setSalesenterprise_id(trading.getSalesenterprise_id());
		tradingForm.setPurchaseuser_id(trading.getPurchaseuser_id());
		tradingForm.setSalesuser_id(trading.getSalesuser_id());
		tradingForm.setSalesworktype(trading.getSalesworktype());
		tradingForm.setCount(trading.getCount());
		tradingForm.setPrice(trading.getPrice());
		return tradingForm;
	}

	@Override
	public Trading makeTrading(TradingForm tradingForm, int trading_id) {
		Trading trading = new Trading();
		if(trading_id != 0) {
			trading.setTrading_id(trading_id);
		}
		trading.setProduct_id(tradingForm.getProduct_id());
		trading.setOrderday(tradingForm.getOrderday());
		trading.setPurchaseday(tradingForm.getPurchaseday());
		trading.setPurchaseenterprise_id(tradingForm.getPurchaseenterprise_id());
		trading.setSalesenterprise_id(tradingForm.getSalesenterprise_id());
		trading.setPurchaseuser_id(tradingForm.getPurchaseuser_id());
		trading.setSalesuser_id(tradingForm.getSalesuser_id());
		trading.setSalesworktype(tradingForm.getSalesworktype());
		trading.setCount(tradingForm.getCount());
		trading.setPrice(tradingForm.getPrice());
		return trading;
	}

	

}
