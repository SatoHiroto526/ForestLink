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
import com.example.demo.entity.Stocktreasurer;
import com.example.demo.entity.Treasure;
import com.example.demo.entity.User;
import com.example.demo.form.stocktreasurer.StocktreasurerForm;
import com.example.demo.service.stocktreasurer.StocktreasurerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/stock/stocktreasurer")
@RequiredArgsConstructor
public class StocktreasurerController {
	
	private final StocktreasurerService stocktreasurerService;
	private final DataRefill dataRefill;
	
	@GetMapping("/{stock_id}")
	public String stocktreasurer(@PathVariable int stock_id,
			Model model) {
		model.addAttribute("title", "在庫ID：" + stock_id + "の在庫出納履歴一覧");
		List<Stocktreasurer> stocktreasurerList = stocktreasurerService.stocktreasurerList(stock_id);
		model.addAttribute("stocktreasurerList", stocktreasurerList);
		model.addAttribute("stocktreasurerCount",stocktreasurerList.size() + "件のレコード");
		model.addAttribute("stock_id", stock_id);
		return "stocktreasurer/stocktreasurerAll";
	}
	
	@GetMapping("/insert/{stock_id}")
	public String stocktreasurerInsert(StocktreasurerForm stocktreasurerForm,
			@PathVariable int stock_id,
			Model model,
			@ModelAttribute("complete") String complete) {
		model.addAttribute("title", "在庫ID：" + stock_id + "の新規在庫出納");
		model.addAttribute("stocktreasurerForm", stocktreasurerForm);
		List<User> userList = stocktreasurerService.userList(stock_id);
		model.addAttribute("userList", userList);
		List<Treasure> treasureList = stocktreasurerService.treasureList();
		model.addAttribute("treasureList", treasureList);
		model.addAttribute("stock_id",stock_id);
		return "stocktreasurer/stocktreasurerInsert";
	}
	
	@PostMapping("/insert")
	public String executeStockInsert(@Validated StocktreasurerForm stocktreasurerForm,
			BindingResult result,
			@RequestParam("stock_id") int stock_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			model.addAttribute("title", "在庫ID：" + stock_id + "の新規在庫出納");
			model.addAttribute("stocktreasurerForm", stocktreasurerForm);
			List<User> userList = stocktreasurerService.userList(stock_id);
			model.addAttribute("userList", userList);
			List<Treasure> treasureList = stocktreasurerService.treasureList();
			model.addAttribute("treasureList", treasureList);
			model.addAttribute("stock_id",stock_id);
			return "stocktreasurer/stocktreasurerInsert";
			
		}else {
			Stocktreasurer stocktreasurer = dataRefill.makeStocktreasurer(stocktreasurerForm);
			stocktreasurerService.treasureInsert(stocktreasurer);
			redirectAttributes.addFlashAttribute("complete","新規在庫出納が1件追加されました");
			return "redirect:/stock/stocktreasurer/" + stock_id;
		}
	}

}
