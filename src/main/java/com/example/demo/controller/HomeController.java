package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.auth.CustomUserDetails;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Home;
import com.example.demo.entity.User;
import com.example.demo.service.home.HomeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
	
	private final HomeService homeService;
	
	@GetMapping
	public String home(@AuthenticationPrincipal CustomUserDetails customUserDetails,
			Model model) {
		User userDetail = homeService.userDetail(customUserDetails.getUsername());
		Enterprise enterprise = userDetail.getEnterprise();
		int enterprise_id = enterprise.getEnterprise_id();
		String enterprisename = enterprise.getEnterprisename();
		model.addAttribute("enterprisename", enterprisename);
		Home purchase  = homeService.purchase(enterprise_id);
		model.addAttribute("PLlavel", purchase.getLavel()); 
		model.addAttribute("PLPurchas", purchase.getData());
		Home sales = homeService.sales(enterprise_id);
		model.addAttribute("PLSales", sales.getData());
		Home stockType = homeService.stockType(enterprise_id);
		model.addAttribute("stockTypeLavel", stockType.getLavel());
		model.addAttribute("stockType", stockType.getData());
		Home breed = homeService.breed(enterprise_id);
		model.addAttribute("breedLavel", breed.getLavel());
		model.addAttribute("breed", breed.getData());
		return "home/home";
	}

}
