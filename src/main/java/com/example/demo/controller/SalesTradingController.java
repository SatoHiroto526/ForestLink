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
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Product;
import com.example.demo.entity.Salesworktype;
import com.example.demo.entity.Trading;
import com.example.demo.entity.User;
import com.example.demo.form.trading.TradingFilter;
import com.example.demo.form.trading.TradingForm;
import com.example.demo.service.enterprisename.EnterprisenameService;
import com.example.demo.service.salesTradingService.SalesTradingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class SalesTradingController {
	
	private final SalesTradingService salesTradingService;
	private final EnterprisenameService enterprisenameService;
	private final DataRefill dataRefill;
	
	@GetMapping("/salesTrading")
	public String salesTrading(TradingFilter tradingFilter,
			Model model,
			@ModelAttribute("complete") String complete) {
		List<Trading> tradingList = salesTradingService.tradingList();
		model.addAttribute("tradingList", tradingList);
		model.addAttribute("tradingCount",tradingList.size() + "件のレコード");
		model.addAttribute("tradingFilter", tradingFilter);
		List<Enterprise> enterpriseList = salesTradingService.enterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		List<Enterprise> partnerEnterpriseList = salesTradingService.partnerenterpriseList();
		model.addAttribute("partnerEnterpriseList",partnerEnterpriseList);
		model.addAttribute("complete", complete);
		return "salesTrading/salesTradingAll";
	}
	
	@GetMapping("/filteredSalesTrading")
	public String filteredSalesTrading(TradingFilter tradingFilter,
			Model model,
			@ModelAttribute("complete") String complete) {
		int purchaseenterprise_id = tradingFilter.getPurchaseenterprise_id();
		int salesenterprise_id = tradingFilter.getSalesenterprise_id();
		List<Trading> tradingList = salesTradingService.filteredTradingList(purchaseenterprise_id, salesenterprise_id);
		model.addAttribute("tradingList", tradingList);
		model.addAttribute("tradingCount",tradingList.size() + "件のレコード");
		model.addAttribute("tradingFilter", tradingFilter);
		List<Enterprise> enterpriseList = salesTradingService.enterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		List<Enterprise> partnerEnterpriseList = salesTradingService.partnerenterpriseList();
		model.addAttribute("partnerEnterpriseList",partnerEnterpriseList);
		model.addAttribute("complete", complete);
		return "salesTrading/salesTradingAll";
	}
	
	@GetMapping("/salesTrading/detail/{trading_id}")
	public String salesTradingDetail(@PathVariable int trading_id,
			Model model) {
		Trading purchaseTrading = salesTradingService.purchaseTradingDetail(trading_id);
		model.addAttribute("purchaseTrading", purchaseTrading);
		Trading salesTrading = salesTradingService.salesTradingDetail(trading_id);
		model.addAttribute("salesTrading", salesTrading);
		return "salesTrading/salesTradingDetail";
	}
	
	@GetMapping("/partnerEnterpriseSales/entryEnterprise/{enterprise_id}")
	public String entryEnterpriseSales(@PathVariable int enterprise_id,
			Model model) {
		String enterprisename = enterprisenameService.getEntryenterprisename(enterprise_id);
		model.addAttribute("title", enterprisename + "の外部取引先企業販売一覧");
		List<Trading> tradingList = salesTradingService.filteredTradingList(0, enterprise_id);
		model.addAttribute("tradingList", tradingList);
		model.addAttribute("tradingCount",tradingList.size() + "件のレコード");
		return "salesTrading/enterpriseSalesTrading";
	}
	
	@GetMapping("/partnerEnterpriseSales/partnerEnterprise/{enterprise_id}")
	public String partnerEnterprisePurchase(@PathVariable int enterprise_id,
			Model model) {
		String enterprisename = enterprisenameService.getPartnerenterprisename(enterprise_id);
		model.addAttribute("title", enterprisename + "の外部取引先企業購買一覧");
		List<Trading> tradingList = salesTradingService.filteredTradingList(enterprise_id, 0);
		model.addAttribute("tradingList", tradingList);
		model.addAttribute("tradingCount",tradingList.size() + "件のレコード");
		return "salesTrading/enterpriseSalesTrading";
	}
	
	@GetMapping("/salesTrading/insert")
	public String salesTradingInsert(TradingForm tradingForm,
			Model model) {
		tradingForm.setNewTrading(true);
		model.addAttribute("title", "新規外部取引先企業販売登録");
		model.addAttribute("tradingForm", tradingForm);
		List<Enterprise> enterpriseList = salesTradingService.enterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		List<Enterprise> partnerenterpriseList = salesTradingService.partnerenterpriseList();
		model.addAttribute("partnerenterpriseList",partnerenterpriseList);
		List<User> userList = salesTradingService.userList();
		model.addAttribute("userList",userList);
		List<User> partneruserList = salesTradingService.partneruserList();
		model.addAttribute("partneruserList",partneruserList);
		List<Product> productList = salesTradingService.productList();
		model.addAttribute("productList",productList);
		List<Salesworktype> salesworktypeList = salesTradingService.salesworktypeList();
		model.addAttribute("salesworktypeList",salesworktypeList);
		
		return "salesTrading/salesTradingInsert";
	}
	
	@PostMapping("/salesTrading/insert")
	public String executeSalesTradingInsert(@Validated TradingForm tradingForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			tradingForm.setNewTrading(true);
			model.addAttribute("title", "新規外部取引先企業販売登録");
			model.addAttribute("tradingForm", tradingForm);
			List<Enterprise> enterpriseList = salesTradingService.enterpriseList();
			model.addAttribute("enterpriseList",enterpriseList);
			List<Enterprise> partnerenterpriseList = salesTradingService.partnerenterpriseList();
			model.addAttribute("partnerenterpriseList",partnerenterpriseList);
			List<User> userList = salesTradingService.userList();
			model.addAttribute("userList",userList);
			List<User> partneruserList = salesTradingService.partneruserList();
			model.addAttribute("partneruserList",partneruserList);
			List<Product> productList = salesTradingService.productList();
			model.addAttribute("productList",productList);
			List<Salesworktype> salesworktypeList = salesTradingService.salesworktypeList();
			model.addAttribute("salesworktypeList",salesworktypeList);
			
			return "salesTrading/salesTradingInsert";
			
		}else {
			Trading trading = dataRefill.makeTrading(tradingForm, 0);
			salesTradingService.tradingInsert(trading);
			redirectAttributes.addFlashAttribute("complete","新規外部取引先企業販売が1件追加されました");
			return "redirect:/salesTrading";
		}
	}
	
	@GetMapping("/salesTrading/update/{trading_id}")
	public String salesTradingUpdate(TradingForm tradingForm,
			@PathVariable int trading_id,
			Model model) {
		tradingForm.setNewTrading(false);
		model.addAttribute("title", "外部取引先企業販売更新");
		Trading trading = salesTradingService.purchaseTradingDetail(trading_id);
		tradingForm = dataRefill.makeTradingForm(trading);
		model.addAttribute("tradingForm",tradingForm);
		model.addAttribute("trading_id",trading_id);
		List<Enterprise> enterpriseList = salesTradingService.enterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		List<Enterprise> partnerenterpriseList = salesTradingService.partnerenterpriseList();
		model.addAttribute("partnerenterpriseList",partnerenterpriseList);
		List<User> userList = salesTradingService.userList();
		model.addAttribute("userList",userList);
		List<User> partneruserList = salesTradingService.partneruserList();
		model.addAttribute("partneruserList",partneruserList);
		List<Product> productList = salesTradingService.productList();
		model.addAttribute("productList",productList);
		List<Salesworktype> salesworktypeList = salesTradingService.salesworktypeList();
		model.addAttribute("salesworktypeList",salesworktypeList);
		
		return "salesTrading/salesTradingInsert";
	}
	
	@PostMapping("/salesTrading/update")
	public String executeSalesTradingUpdate(@Validated TradingForm tradingForm,
			BindingResult result,
			@RequestParam("trading_id") int trading_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			tradingForm.setNewTrading(false);
			model.addAttribute("title", "外部取引先企業販売更新");
			model.addAttribute("trading_id",trading_id);
			List<Enterprise> enterpriseList = salesTradingService.enterpriseList();
			model.addAttribute("enterpriseList",enterpriseList);
			List<Enterprise> partnerenterpriseList = salesTradingService.partnerenterpriseList();
			model.addAttribute("partnerenterpriseList",partnerenterpriseList);
			List<User> userList = salesTradingService.userList();
			model.addAttribute("userList",userList);
			List<User> partneruserList = salesTradingService.partneruserList();
			model.addAttribute("partneruserList",partneruserList);
			List<Product> productList = salesTradingService.productList();
			model.addAttribute("productList",productList);
			List<Salesworktype> salesworktypeList = salesTradingService.salesworktypeList();
			model.addAttribute("salesworktypeList",salesworktypeList);
			
			return "salesTrading/salesTradingInsert";
			
		}else {
			Trading trading = dataRefill.makeTrading(tradingForm, trading_id);
			salesTradingService.tradingUpdate(trading);
			redirectAttributes.addFlashAttribute("complete","外部取引先企業販売が1件更新されました");
			return "redirect:/salesTrading";
		}
		
	}
	
	@PostMapping("/salesTrading/delete")
	public String salesTradingDelete(@RequestParam("trading_id") int trading_id,
			RedirectAttributes redirectAttributes) {
		salesTradingService.tradingDelete(trading_id);
		redirectAttributes.addFlashAttribute("complete","外部取引先企業販売が1件削除されました");
		return "redirect:/salesTrading";
	}

}
