package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.component.dataRefill.DataRefill;
import com.example.demo.entity.Archive;
import com.example.demo.entity.Breed;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Product;
import com.example.demo.entity.Producttype;
import com.example.demo.entity.Stock;
import com.example.demo.entity.Stockstatus;
import com.example.demo.entity.Stocktype;
import com.example.demo.entity.User;
import com.example.demo.form.stock.StockFilter;
import com.example.demo.form.stock.StockForm;
import com.example.demo.service.enterprisename.EnterprisenameService;
import com.example.demo.service.stock.StockService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class StockController {
	
	private final StockService stockService;
	private final EnterprisenameService enterprisenameService;
	private final DataRefill dataRefill;
	
	@GetMapping("/stock")
	public String stock(StockFilter stockFilter,
			Model model,
			@ModelAttribute("complete") String complete) {
		model.addAttribute("title", "在庫一覧");
		List<Stock> stockList = stockService.stockList("NO");
		model.addAttribute("stockList", stockList);
		model.addAttribute("stockCount",stockList.size() + "件のレコード");
		List<Enterprise> enterpriseList = stockService.enterpriseList();
		model.addAttribute("enterpriseList", enterpriseList);
		List<Stocktype> stocktypeList = stockService.stocktypeList();
		model.addAttribute("stocktypeList", stocktypeList);
		List<Stockstatus> stockstatusList = stockService.stockstatusList(); 
		model.addAttribute("stockstatusList", stockstatusList);
		List<Producttype> producttypeList = stockService.producttypeList();
		model.addAttribute("producttypeList", producttypeList);
		List<Breed> breedList = stockService.breedList();
		model.addAttribute("breedList", breedList);
		model.addAttribute("complete", complete);
		model.addAttribute("archiveStock", false);
		return "stock/stockAll";
	}
	
	@GetMapping("/archiveStock")
	public String archiveStock(StockFilter stockFilter,
			Model model,
			@ModelAttribute("complete") String complete) {
		model.addAttribute("title", "アーカイブ済み在庫一覧");
		List<Stock> stockList = stockService.stockList("YES");
		model.addAttribute("stockList", stockList);
		model.addAttribute("stockCount",stockList.size() + "件のレコード");
		List<Enterprise> enterpriseList = stockService.enterpriseList();
		model.addAttribute("enterpriseList", enterpriseList);
		List<Stocktype> stocktypeList = stockService.stocktypeList();
		model.addAttribute("stocktypeList", stocktypeList);
		List<Stockstatus> stockstatusList = stockService.stockstatusList(); 
		model.addAttribute("stockstatusList", stockstatusList);
		List<Producttype> producttypeList = stockService.producttypeList();
		model.addAttribute("producttypeList", producttypeList);
		List<Breed> breedList = stockService.breedList();
		model.addAttribute("breedList", breedList);
		model.addAttribute("complete", complete);
		model.addAttribute("archiveStock", true);
		return "stock/stockAll";
	}
	
	@GetMapping("/filteredStock")
	public String filteredStock(StockFilter stockFilter,
			Model model,
			@ModelAttribute("complete") String complete) {
		model.addAttribute("title", "在庫一覧");
		int enterprise_id = stockFilter.getEnterprise_id();
		String stocktype = stockFilter.getStocktype();
		int stockstatus_id = stockFilter.getStockstatus_id();
		int producttype_id = stockFilter.getProducttype_id();
		int breed_id = stockFilter.getBreed_id();
		List<Stock> stockList = stockService.filteredStockList("NO", enterprise_id, stocktype, stockstatus_id, producttype_id, breed_id);
		model.addAttribute("stockList", stockList);
		model.addAttribute("stockCount",stockList.size() + "件のレコード");
		List<Enterprise> enterpriseList = stockService.enterpriseList();
		model.addAttribute("enterpriseList", enterpriseList);
		List<Stocktype> stocktypeList = stockService.stocktypeList();
		model.addAttribute("stocktypeList", stocktypeList);
		List<Stockstatus> stockstatusList = stockService.stockstatusList(); 
		model.addAttribute("stockstatusList", stockstatusList);
		List<Producttype> producttypeList = stockService.producttypeList();
		model.addAttribute("producttypeList", producttypeList);
		List<Breed> breedList = stockService.breedList();
		model.addAttribute("breedList", breedList);
		model.addAttribute("archiveStock", false);
		return "stock/stockAll";
	}
	
	@GetMapping("/filteredArchiveStock")
	public String filteredArchiveStock(StockFilter stockFilter,
			Model model,
			@ModelAttribute("complete") String complete) {
		model.addAttribute("title", "アーカイブ済み在庫一覧");
		int enterprise_id = stockFilter.getEnterprise_id();
		String stocktype = stockFilter.getStocktype();
		int stockstatus_id = stockFilter.getStockstatus_id();
		int producttype_id = stockFilter.getProducttype_id();
		int breed_id = stockFilter.getBreed_id();
		List<Stock> stockList = stockService.filteredStockList("YES", enterprise_id, stocktype, stockstatus_id, producttype_id, breed_id);
		model.addAttribute("stockList", stockList);
		model.addAttribute("stockCount",stockList.size() + "件のレコード");
		List<Enterprise> enterpriseList = stockService.enterpriseList();
		model.addAttribute("enterpriseList", enterpriseList);
		List<Stocktype> stocktypeList = stockService.stocktypeList();
		model.addAttribute("stocktypeList", stocktypeList);
		List<Stockstatus> stockstatusList = stockService.stockstatusList(); 
		model.addAttribute("stockstatusList", stockstatusList);
		List<Producttype> producttypeList = stockService.producttypeList();
		model.addAttribute("producttypeList", producttypeList);
		List<Breed> breedList = stockService.breedList();
		model.addAttribute("breedList", breedList);
		model.addAttribute("archiveStock", true);
		return "stock/stockAll";
	}
	
	@GetMapping("/stock/detail/{stock_id}")
	public String stockDetail(@PathVariable int stock_id,
			Model model) {
		Stock stockDetail = stockService.stockDetail(stock_id);
		model.addAttribute("stockDetail", stockDetail);
		model.addAttribute("title", "在庫詳細");
		return "stock/stockDetail";
	}
	
	@GetMapping("/stock/entryEnterprise/{enterprise_id}")
	public String enterpriseStock(@PathVariable int enterprise_id,
			Model model) {
		String enterprisename = enterprisenameService.getEntryenterprisename(enterprise_id);
		model.addAttribute("title", enterprisename + "の在庫一覧");
		List<Stock> stockList = stockService.filteredStockList("NO", enterprise_id, null, 0, 0, 0);
		model.addAttribute("stockList", stockList);
		model.addAttribute("stockCount",stockList.size() + "件のレコード");
		return "stock/enterpriseStock";
	}
	
	@GetMapping("/archiveStock/entryEnterprise/{enterprise_id}")
	public String enterprisearchiveStock(@PathVariable int enterprise_id,
			Model model) {
		String enterprisename = enterprisenameService.getEntryenterprisename(enterprise_id);
		model.addAttribute("title", enterprisename + "のアーカイブ済み在庫一覧");
		List<Stock> stockList = stockService.filteredStockList("YES", enterprise_id, null, 0, 0, 0);
		model.addAttribute("stockList", stockList);
		model.addAttribute("stockCount",stockList.size() + "件のレコード");
		return "stock/enterpriseStock";
	}
	
	@GetMapping("/stock/insert")
	public String stockInsert(StockForm stockForm,
			Model model) {
		stockForm.setNewStock(true);
		model.addAttribute("title", "新規在庫登録");
		model.addAttribute("stockForm",stockForm);
		List<Enterprise> enterpriseList = stockService.enterpriseList();
		model.addAttribute("enterpriseList", enterpriseList);
		List<Product> productList = stockService.productList();
		model.addAttribute("productList", productList);
		List<Stocktype> stocktypeList = stockService.stocktypeList();
		model.addAttribute("stocktypeList", stocktypeList);
		List<Stockstatus> stockstatusList = stockService.stockstatusList(); 
		model.addAttribute("stockstatusList", stockstatusList);
		List<User> liabilityuserList = stockService.userList();
		model.addAttribute("liabilityuserList", liabilityuserList);
		List<Archive> archiveList = stockService.archiveList();
		model.addAttribute("archiveList", archiveList);
		
		return "stock/stockInsert";
	}
	
	@PostMapping("/stock/insert")
	public String executeStockInsert(@Validated StockForm stockForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			stockForm.setNewStock(true);
			model.addAttribute("title", "新規在庫登録");
			model.addAttribute("stockForm",stockForm);
			List<Enterprise> enterpriseList = stockService.enterpriseList();
			model.addAttribute("enterpriseList", enterpriseList);
			List<Product> productList = stockService.productList();
			model.addAttribute("productList", productList);
			List<Stocktype> stocktypeList = stockService.stocktypeList();
			model.addAttribute("stocktypeList", stocktypeList);
			List<Stockstatus> stockstatusList = stockService.stockstatusList(); 
			model.addAttribute("stockstatusList", stockstatusList);
			List<User> liabilityuserList = stockService.userList();
			model.addAttribute("liabilityuserList", liabilityuserList);
			List<Archive> archiveList = stockService.archiveList();
			model.addAttribute("archiveList", archiveList);
			
			return "stock/stockInsert";
			
		}else {
			Stock stock = dataRefill.makeStock(stockForm, 0);
			stockService.stockInsert(stock);
			redirectAttributes.addFlashAttribute("complete","新規在庫が1件追加されました");
			return "redirect:/stock";
		}
	}
	
	@GetMapping("/stock/update/{stock_id}")
	public String entryEnterpriseUpdate(StockForm stockForm,
			@PathVariable int stock_id,
			Model model) {
		stockForm.setNewStock(false);
		model.addAttribute("title", "在庫更新");
		Stock stock = stockService.stockDetail(stock_id);
		stockForm = dataRefill.makeStockForm(stock);
		model.addAttribute("stockForm",stockForm);
		model.addAttribute("stock_id",stock_id);
		List<Enterprise> enterpriseList = stockService.enterpriseList();
		model.addAttribute("enterpriseList", enterpriseList);
		List<Product> productList = stockService.productList();
		model.addAttribute("productList", productList);
		List<Stocktype> stocktypeList = stockService.stocktypeList();
		model.addAttribute("stocktypeList", stocktypeList);
		List<Stockstatus> stockstatusList = stockService.stockstatusList(); 
		model.addAttribute("stockstatusList", stockstatusList);
		List<User> liabilityuserList = stockService.userList();
		model.addAttribute("liabilityuserList", liabilityuserList);
		List<Archive> archiveList = stockService.archiveList();
		model.addAttribute("archiveList", archiveList);
		
		return "stock/stockInsert";
	}
	
	@PostMapping("/stock/update")
	public String executeStockUpdate(@Validated StockForm stockForm,
			BindingResult result,
			@RequestParam("stock_id") int stock_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			stockForm.setNewStock(false);
			model.addAttribute("title", "在庫更新");
			model.addAttribute("stock_id",stock_id);
			List<Enterprise> enterpriseList = stockService.enterpriseList();
			model.addAttribute("enterpriseList", enterpriseList);
			List<Product> productList = stockService.productList();
			model.addAttribute("productList", productList);
			List<Stocktype> stocktypeList = stockService.stocktypeList();
			model.addAttribute("stocktypeList", stocktypeList);
			List<Stockstatus> stockstatusList = stockService.stockstatusList(); 
			model.addAttribute("stockstatusList", stockstatusList);
			List<User> liabilityuserList = stockService.userList();
			model.addAttribute("liabilityuserList", liabilityuserList);
			List<Archive> archiveList = stockService.archiveList();
			model.addAttribute("archiveList", archiveList);
			
			return "stock/stockInsert";
			
		}else {
			Stock stock = dataRefill.makeStock(stockForm, stock_id);
			stockService.stockUpdate(stock);
			redirectAttributes.addFlashAttribute("complete","新規在庫が1件更新されました");
			return "redirect:/stock";
		}
		
	}

}
