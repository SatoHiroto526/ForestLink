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
import com.example.demo.service.purchaseTrading.PurchaseTradingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class PurchaseTradingController {
	
	private final PurchaseTradingService purchaseTradingService;
	private final EnterprisenameService enterprisenameService;
	private final DataRefill dataRefill;
	
	@GetMapping("/purchaseTrading")
	public String purchaseTrading(TradingFilter tradingFilter,
			Model model,
			@ModelAttribute("complete") String complete) {
		List<Trading> tradingList = purchaseTradingService.tradingList();
		model.addAttribute("tradingList", tradingList);
		model.addAttribute("tradingCount",tradingList.size() + "件のレコード");
		model.addAttribute("tradingFilter", tradingFilter);
		List<Enterprise> enterpriseList = purchaseTradingService.enterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		List<Enterprise> partnerEnterpriseList = purchaseTradingService.partnerenterpriseList();
		model.addAttribute("partnerEnterpriseList",partnerEnterpriseList);
		model.addAttribute("complete", complete);
		return "purchaseTrading/purchaseTradingAll";
	}
	
	@GetMapping("/filteredPurchaseTrading")
	public String filteredPurchaseTrading(TradingFilter tradingFilter,
			Model model,
			@ModelAttribute("complete") String complete) {
		int purchaseenterprise_id = tradingFilter.getPurchaseenterprise_id();
		int salesenterprise_id = tradingFilter.getSalesenterprise_id();
		List<Trading> tradingList = purchaseTradingService.filteredTradingList(purchaseenterprise_id, salesenterprise_id);
		model.addAttribute("tradingList", tradingList);
		model.addAttribute("tradingCount",tradingList.size() + "件のレコード");
		model.addAttribute("tradingFilter", tradingFilter);
		List<Enterprise> enterpriseList = purchaseTradingService.enterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		List<Enterprise> partnerEnterpriseList = purchaseTradingService.partnerenterpriseList();
		model.addAttribute("partnerEnterpriseList",partnerEnterpriseList);
		model.addAttribute("complete", complete);
		return "purchaseTrading/purchaseTradingAll";
	}
	
	@GetMapping("/purchaseTrading/detail/{trading_id}")
	public String purchaseTradingDetail(@PathVariable int trading_id,
			Model model) {
		Trading purchaseTrading = purchaseTradingService.purchaseTradingDetail(trading_id);
		model.addAttribute("purchaseTrading", purchaseTrading);
		Trading salesTrading = purchaseTradingService.salesTradingDetail(trading_id);
		model.addAttribute("salesTrading", salesTrading);
		return "purchaseTrading/purchaseTradingDetail";
	}
	
	@GetMapping("/partnerEnterprisePurchase/entryEnterprise/{enterprise_id}")
	public String entryEnterprisePurchase(@PathVariable int enterprise_id,
			Model model) {
		String enterprisename = enterprisenameService.getEntryenterprisename(enterprise_id);
		model.addAttribute("title", enterprisename + "の外部取引先企業購買一覧");
		List<Trading> tradingList = purchaseTradingService.filteredTradingList(enterprise_id, 0);
		model.addAttribute("tradingList", tradingList);
		model.addAttribute("tradingCount",tradingList.size() + "件のレコード");
		return "purchaseTrading/enterprisePurchaseTrading";
	}
	
	@GetMapping("/partnerEnterprisePurchase/partnerEnterprise/{enterprise_id}")
	public String partnerEnterprisePurchase(@PathVariable int enterprise_id,
			Model model) {
		String enterprisename = enterprisenameService.getPartnerenterprisename(enterprise_id);
		model.addAttribute("title", enterprisename + "の外部取引先企業購買一覧");
		List<Trading> tradingList = purchaseTradingService.filteredTradingList(0, enterprise_id);
		model.addAttribute("tradingList", tradingList);
		model.addAttribute("tradingCount",tradingList.size() + "件のレコード");
		return "purchaseTrading/enterprisePurchaseTrading";
	}
	
	@GetMapping("/purchaseTrading/insert")
	public String purchaseTradingInsert(TradingForm tradingForm,
			Model model) {
		tradingForm.setNewTrading(true);
		model.addAttribute("title", "新規外部取引先企業購買登録");
		model.addAttribute("tradingForm", tradingForm);
		List<Enterprise> enterpriseList = purchaseTradingService.enterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		List<Enterprise> partnerenterpriseList = purchaseTradingService.partnerenterpriseList();
		model.addAttribute("partnerenterpriseList",partnerenterpriseList);
		List<User> userList = purchaseTradingService.userList();
		model.addAttribute("userList",userList);
		List<User> partneruserList = purchaseTradingService.partneruserList();
		model.addAttribute("partneruserList",partneruserList);
		List<Product> productList = purchaseTradingService.productList();
		model.addAttribute("productList",productList);
		List<Salesworktype> salesworktypeList = purchaseTradingService.salesworktypeList();
		model.addAttribute("salesworktypeList",salesworktypeList);
		
		return "purchaseTrading/purchaseTradingInsert";
	}
	
	@PostMapping("/purchaseTrading/insert")
	public String executePurchaseTradingInsert(@Validated TradingForm tradingForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			tradingForm.setNewTrading(true);
			model.addAttribute("title", "新規外部取引先企業購買登録");
			model.addAttribute("tradingForm", tradingForm);
			List<Enterprise> enterpriseList = purchaseTradingService.enterpriseList();
			model.addAttribute("enterpriseList",enterpriseList);
			List<Enterprise> partnerenterpriseList = purchaseTradingService.partnerenterpriseList();
			model.addAttribute("partnerenterpriseList",partnerenterpriseList);
			List<User> userList = purchaseTradingService.userList();
			model.addAttribute("userList",userList);
			List<User> partneruserList = purchaseTradingService.partneruserList();
			model.addAttribute("partneruserList",partneruserList);
			List<Product> productList = purchaseTradingService.productList();
			model.addAttribute("productList",productList);
			List<Salesworktype> salesworktypeList = purchaseTradingService.salesworktypeList();
			model.addAttribute("salesworktypeList",salesworktypeList);
			
			return "purchaseTrading/purchaseTradingInsert";
			
		}else {
			Trading trading = dataRefill.makeTrading(tradingForm, 0);
			purchaseTradingService.tradingInsert(trading);
			redirectAttributes.addFlashAttribute("complete","新規外部取引先企業購買が1件追加されました");
			return "redirect:/purchaseTrading";
		}
	}
	
	@GetMapping("/purchaseTrading/update/{trading_id}")
	public String purchaseTradingUpdate(TradingForm tradingForm,
			@PathVariable int trading_id,
			Model model) {
		tradingForm.setNewTrading(false);
		model.addAttribute("title", "外部取引先企業購買更新");
		Trading trading = purchaseTradingService.purchaseTradingDetail(trading_id);
		tradingForm = dataRefill.makeTradingForm(trading);
		model.addAttribute("tradingForm",tradingForm);
		model.addAttribute("trading_id",trading_id);
		List<Enterprise> enterpriseList = purchaseTradingService.enterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		List<Enterprise> partnerenterpriseList = purchaseTradingService.partnerenterpriseList();
		model.addAttribute("partnerenterpriseList",partnerenterpriseList);
		List<User> userList = purchaseTradingService.userList();
		model.addAttribute("userList",userList);
		List<User> partneruserList = purchaseTradingService.partneruserList();
		model.addAttribute("partneruserList",partneruserList);
		List<Product> productList = purchaseTradingService.productList();
		model.addAttribute("productList",productList);
		List<Salesworktype> salesworktypeList = purchaseTradingService.salesworktypeList();
		model.addAttribute("salesworktypeList",salesworktypeList);
		
		return "purchaseTrading/purchaseTradingInsert";
	}
	
	@PostMapping("/purchaseTrading/update")
	public String executePurchaseTradingUpdate(@Validated TradingForm tradingForm,
			BindingResult result,
			@RequestParam("trading_id") int trading_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			tradingForm.setNewTrading(false);
			model.addAttribute("title", "外部取引先企業購買更新");
			model.addAttribute("trading_id",trading_id);
			List<Enterprise> enterpriseList = purchaseTradingService.enterpriseList();
			model.addAttribute("enterpriseList",enterpriseList);
			List<Enterprise> partnerenterpriseList = purchaseTradingService.partnerenterpriseList();
			model.addAttribute("partnerenterpriseList",partnerenterpriseList);
			List<User> userList = purchaseTradingService.userList();
			model.addAttribute("userList",userList);
			List<User> partneruserList = purchaseTradingService.partneruserList();
			model.addAttribute("partneruserList",partneruserList);
			List<Product> productList = purchaseTradingService.productList();
			model.addAttribute("productList",productList);
			List<Salesworktype> salesworktypeList = purchaseTradingService.salesworktypeList();
			model.addAttribute("salesworktypeList",salesworktypeList);
			
			return "purchaseTrading/purchaseTradingInsert";
			
		}else {
			Trading trading = dataRefill.makeTrading(tradingForm, trading_id);
			purchaseTradingService.tradingUpdate(trading);
			redirectAttributes.addFlashAttribute("complete","外部取引先企業購買が1件更新されました");
			return "redirect:/purchaseTrading";
		}
		
	}
	
	@PostMapping("/purchaseTrading/delete")
	public String purchaseTradingDelete(@RequestParam("trading_id") int trading_id,
			RedirectAttributes redirectAttributes) {
		purchaseTradingService.tradingDelete(trading_id);
		redirectAttributes.addFlashAttribute("complete","外部取引先企業購買が1件削除されました");
		return "redirect:/purchaseTrading";
	}
	
	
}
