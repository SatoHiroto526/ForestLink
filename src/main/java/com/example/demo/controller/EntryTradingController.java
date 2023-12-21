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
import com.example.demo.entity.Trading;
import com.example.demo.entity.User;
import com.example.demo.form.trading.TradingFilter;
import com.example.demo.form.trading.TradingForm;
import com.example.demo.service.enterprisename.EnterprisenameService;
import com.example.demo.service.entryTrading.EntryTradingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class EntryTradingController {
	
	private final EntryTradingService entryTradingService;
	private final EnterprisenameService enterprisenameService;
	private final DataRefill dataRefill;
	
	@GetMapping("/entryTrading")
	public String entryTrading(TradingFilter tradingFilter,
			Model model,
			@ModelAttribute("complete") String complete) {
		List<Trading> tradingList = entryTradingService.tradingList();
		model.addAttribute("tradingList", tradingList);
		model.addAttribute("tradingCount",tradingList.size() + "件のレコード");
		model.addAttribute("tradingFilter", tradingFilter);
		List<Enterprise> enterpriseList = entryTradingService.enterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		model.addAttribute("complete", complete);
		return "entryTrading/entryTradingAll";
	}
	
	@GetMapping("/filteredEntryTrading")
	public String filteredEntryTrading(TradingFilter tradingFilter,
			Model model,
			@ModelAttribute("complete") String complete) {
		int purchaseenterprise_id = tradingFilter.getPurchaseenterprise_id();
		int salesenterprise_id = tradingFilter.getSalesenterprise_id();
		List<Trading> tradingList = entryTradingService.filteredTradingList(purchaseenterprise_id, salesenterprise_id);
		model.addAttribute("tradingList", tradingList);
		model.addAttribute("tradingCount",tradingList.size() + "件のレコード");
		model.addAttribute("tradingFilter", tradingFilter);
		List<Enterprise> enterpriseList = entryTradingService.enterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		model.addAttribute("complete", complete);
		return "entryTrading/entryTradingAll";
	}
	
	@GetMapping("/entryTrading/detail/{trading_id}")
	public String entryTradingDetail(@PathVariable int trading_id,
			Model model) {
		Trading purchaseTrading = entryTradingService.purchaseTradingDetail(trading_id);
		model.addAttribute("purchaseTrading", purchaseTrading);
		Trading salesTrading = entryTradingService.salesTradingDetail(trading_id);
		model.addAttribute("salesTrading", salesTrading);
		return "entryTrading/entryTradingDetail";
	}
	
	@GetMapping("/entryEnterprisePurchase/entryEnterprise/{enterprise_id}")
	public String entryEnterprisePurchase(@PathVariable int enterprise_id,
			Model model) {
		String enterprisename = enterprisenameService.getEntryenterprisename(enterprise_id);
		model.addAttribute("title", enterprisename + "の参加企業内購買一覧");
		List<Trading> tradingList = entryTradingService.filteredTradingList(enterprise_id, 0);
		model.addAttribute("tradingList", tradingList);
		model.addAttribute("tradingCount",tradingList.size() + "件のレコード");
		return "entryTrading/enterpriseEntryTrading";
	}
	
	@GetMapping("/entryEnterpriseSales/entryEnterprise/{enterprise_id}")
	public String entryEnterpriseSales(@PathVariable int enterprise_id,
			Model model) {
		String enterprisename = enterprisenameService.getEntryenterprisename(enterprise_id);
		model.addAttribute("title", enterprisename + "の参加企業内販売一覧");
		List<Trading> tradingList = entryTradingService.filteredTradingList(0, enterprise_id);
		model.addAttribute("tradingList", tradingList);
		model.addAttribute("tradingCount",tradingList.size() + "件のレコード");
		return "entryTrading/enterpriseEntryTrading";
	}
	
	@GetMapping("/entryTrading/insert")
	public String entryTradingInsert(TradingForm tradingForm,
			Model model) {
		tradingForm.setNewTrading(true);
		model.addAttribute("title", "新規参加企業内取引登録");
		model.addAttribute("tradingForm", tradingForm);
		List<Enterprise> enterpriseList = entryTradingService.enterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		List<User> userList = entryTradingService.userList();
		model.addAttribute("userList",userList);
		List<Product> productList = entryTradingService.productList();
		model.addAttribute("productList",productList);
		
		return "entryTrading/entryTradingInsert";
	}
	
	
	@PostMapping("/entryTrading/insert")
	public String executeEntryTradingInsert(@Validated TradingForm tradingForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			tradingForm.setNewTrading(true);
			model.addAttribute("title", "新規参加企業内取引登録");
			model.addAttribute("tradingForm", tradingForm);
			List<Enterprise> enterpriseList = entryTradingService.enterpriseList();
			model.addAttribute("enterpriseList",enterpriseList);
			List<User> userList = entryTradingService.userList();
			model.addAttribute("userList",userList);
			List<Product> productList = entryTradingService.productList();
			model.addAttribute("productList",productList);
			
			return "entryTrading/entryTradingInsert";
			
		}else {
			Trading trading = dataRefill.makeTrading(tradingForm, 0);
			entryTradingService.tradingInsert(trading);
			redirectAttributes.addFlashAttribute("complete","新規参加企業内取引が1件追加されました");
			return "redirect:/entryTrading";
		}
	}
	
	@GetMapping("/entryTrading/update/{trading_id}")
	public String entryTradingUpdate(TradingForm tradingForm,
			@PathVariable int trading_id,
			Model model) {
		tradingForm.setNewTrading(false);
		model.addAttribute("title", "参加企業内取引更新");
		Trading trading = entryTradingService.purchaseTradingDetail(trading_id);
		tradingForm = dataRefill.makeTradingForm(trading);
		model.addAttribute("tradingForm",tradingForm);
		model.addAttribute("trading_id",trading_id);
		List<Enterprise> enterpriseList = entryTradingService.enterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		List<User> userList = entryTradingService.userList();
		model.addAttribute("userList",userList);
		List<Product> productList = entryTradingService.productList();
		model.addAttribute("productList",productList);
		
		return "entryTrading/entryTradingInsert";
	}
	
	
	@PostMapping("/entryTrading/update")
	public String executeEntryTradingUpdate(@Validated TradingForm tradingForm,
			BindingResult result,
			@RequestParam("trading_id") int trading_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			tradingForm.setNewTrading(false);
			model.addAttribute("title", "参加企業内取引更新");
			model.addAttribute("trading_id",trading_id);
			List<Enterprise> enterpriseList = entryTradingService.enterpriseList();
			model.addAttribute("enterpriseList",enterpriseList);
			List<User> userList = entryTradingService.userList();
			model.addAttribute("userList",userList);
			List<Product> productList = entryTradingService.productList();
			model.addAttribute("productList",productList);
			
			return "entryTrading/entryTradingInsert";
			
		}else {
			Trading trading = dataRefill.makeTrading(tradingForm, trading_id);
			entryTradingService.tradingUpdate(trading);
			redirectAttributes.addFlashAttribute("complete","参加企業内取引が1件更新されました");
			return "redirect:/entryTrading";
		}
		
	}
	
	@PostMapping("/entryTrading/delete")
	public String entryTradingDelete(@RequestParam("trading_id") int trading_id,
			RedirectAttributes redirectAttributes) {
		entryTradingService.tradingDelete(trading_id);
		redirectAttributes.addFlashAttribute("complete","参加企業内取引が1件削除されました");
		return "redirect:/entryTrading";
	}

}
