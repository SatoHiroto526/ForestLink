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
import com.example.demo.entity.BusinessType;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Partnerenterpriserank;
import com.example.demo.form.enterprise.EnterpriseFilter;
import com.example.demo.form.enterprise.EnterpriseForm;
import com.example.demo.service.partnerEnterprise.PartnerEnterpriseService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class PartnerEnterpriseController {
	
	private final PartnerEnterpriseService partnerEnterpriseService;
	private final DataRefill dataRefill;
	
	@GetMapping("/partnerEnterprise")
	public String partnerEnterpriseAll(EnterpriseFilter enterpriseFilter,
			Model model,
			@ModelAttribute("complete") String complete) {
		List<Enterprise> partnerEnterpriseList = partnerEnterpriseService.partnerEnterpriseList();
		model.addAttribute("enterpriseFilter", enterpriseFilter);
		List<BusinessType> businesstypeList = partnerEnterpriseService.businessTypeList();
		model.addAttribute("businesstypeList",businesstypeList);
		List<Partnerenterpriserank> partnerenterpriserankList = partnerEnterpriseService.partnerEnterpriseRankList();
		model.addAttribute("partnerenterpriserankList",partnerenterpriserankList);
		List<Enterprise> mainenterpriseList = partnerEnterpriseService.mainEnterpriseList();
		model.addAttribute("mainenterpriseList",mainenterpriseList);
		model.addAttribute("partnerEnterpriseList", partnerEnterpriseList);
		model.addAttribute("partnerEnterpriseCount",partnerEnterpriseList.size() + "件のレコード");
		model.addAttribute("complete", complete);
		return "partnerEnterprise/partnerEnterpriseAll";
	}
	
	
	@GetMapping("/filteredPartnerEnterprise")
	public String filterdPartnerEnterpriseAll(EnterpriseFilter enterpriseFilter,
			Model model) {
		int businesstype_id = enterpriseFilter.getBusinesstype_id();
		int partnerenterpriserank_id = enterpriseFilter.getPartnerenterpriserank_id();
		int mainenterprise_id = enterpriseFilter.getMainenterprise_id();
		List<Enterprise> partnerEnterpriseList = partnerEnterpriseService.filteredPartnerEnterpriseList(businesstype_id, partnerenterpriserank_id, mainenterprise_id);
		model.addAttribute("enterpriseFilter", enterpriseFilter);
		List<BusinessType> businesstypeList = partnerEnterpriseService.businessTypeList();
		model.addAttribute("businesstypeList",businesstypeList);
		List<Partnerenterpriserank> partnerenterpriserankList = partnerEnterpriseService.partnerEnterpriseRankList();
		model.addAttribute("partnerenterpriserankList",partnerenterpriserankList);
		List<Enterprise> mainenterpriseList = partnerEnterpriseService.mainEnterpriseList();
		model.addAttribute("mainenterpriseList",mainenterpriseList);
		model.addAttribute("partnerEnterpriseList", partnerEnterpriseList);
		model.addAttribute("partnerEnterpriseCount",partnerEnterpriseList.size() + "件のレコード");
		return "partnerEnterprise/partnerEnterpriseAll";
	}
	
	
	@GetMapping("/partnerEnterprise/detail/{partnerenterprise_id}")
	public String partnerEnterpriseDetail(@PathVariable int partnerenterprise_id,
			Model model) {
		
		Enterprise partnerEnterprise = partnerEnterpriseService.partnerEnterpriseDetail(partnerenterprise_id);
		model.addAttribute("partnerEnterprise", partnerEnterprise);
		
		return "partnerEnterprise/partnerEnterpriseDetail";
	}
	
	@GetMapping("/partnerEnterprise/insert")
	public String partnerEnterpriseInsert(EnterpriseForm enterpriseForm,
			Model model) {
		enterpriseForm.setNewEnterprise(true);
		model.addAttribute("title", "新規外部取引先企業登録");
		model.addAttribute("enterpriseForm",enterpriseForm);
		List<BusinessType> businesstypeList = partnerEnterpriseService.businessTypeList();
		model.addAttribute("businesstypeList",businesstypeList);
		List<Partnerenterpriserank> partnerenterpriserankList = partnerEnterpriseService.partnerEnterpriseRankList();
		model.addAttribute("partnerenterpriserankList",partnerenterpriserankList);
		List<Enterprise> mainenterpriseList = partnerEnterpriseService.mainEnterpriseList();
		model.addAttribute("mainenterpriseList",mainenterpriseList);
		
		return "partnerEnterprise/partnerEnterpriseInsert";
	}
	
	
	@PostMapping("/partnerEnterprise/insert")
	public String executePartnerEnterpriseInsert(@Validated EnterpriseForm enterpriseForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			enterpriseForm.setNewEnterprise(true);
			model.addAttribute("title", "新規外部取引先企業登録");
			model.addAttribute("enterpriseForm",enterpriseForm);
			List<BusinessType> businesstypeList = partnerEnterpriseService.businessTypeList();
			model.addAttribute("businesstypeList",businesstypeList);
			List<Partnerenterpriserank> partnerenterpriserankList = partnerEnterpriseService.partnerEnterpriseRankList();
			model.addAttribute("partnerenterpriserankList",partnerenterpriserankList);
			List<Enterprise> mainenterpriseList = partnerEnterpriseService.mainEnterpriseList();
			model.addAttribute("mainenterpriseList",mainenterpriseList);
			
			return "partnerEnterprise/partnerEnterpriseInsert";
			
		}else {
			Enterprise enterprise = dataRefill.makeEnterprise(enterpriseForm, 0);
			partnerEnterpriseService.partnerEnterpriseInsert(enterprise);
			redirectAttributes.addFlashAttribute("complete","新規外部取引先参加企業が1件追加されました");
			return "redirect:/partnerEnterprise";
		}
	}
	
	
	@GetMapping("/partnerEnterprise/update/{partnerenterprise_id}")
	public String partnerEnterpriseUpdate(EnterpriseForm enterpriseForm,
			@PathVariable int partnerenterprise_id,
			Model model) {
		enterpriseForm.setNewEnterprise(false);
		model.addAttribute("title", "外部取引先企業更新");
		Enterprise entryEnterprise = partnerEnterpriseService.partnerEnterpriseDetail(partnerenterprise_id);
		enterpriseForm = dataRefill.makeEntryEnterpriseForm(entryEnterprise);
		model.addAttribute("enterpriseForm",enterpriseForm);
		model.addAttribute("enterprise_id",partnerenterprise_id);
		List<BusinessType> businesstypeList = partnerEnterpriseService.businessTypeList();
		model.addAttribute("businesstypeList",businesstypeList);
		List<Partnerenterpriserank> partnerenterpriserankList = partnerEnterpriseService.partnerEnterpriseRankList();
		model.addAttribute("partnerenterpriserankList",partnerenterpriserankList);
		List<Enterprise> mainenterpriseList = partnerEnterpriseService.mainEnterpriseList();
		model.addAttribute("mainenterpriseList",mainenterpriseList);
		
		return "partnerEnterprise/partnerEnterpriseInsert";
	}
	
	
	@PostMapping("/partnerEnterprise/update")
	public String executePartnerEnterpriseUpdate(@Validated EnterpriseForm enterpriseForm,
			BindingResult result,
			@RequestParam("enterprise_id") int enterprise_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			enterpriseForm.setNewEnterprise(false);
			model.addAttribute("title", "外部取引先企業更新");
			model.addAttribute("enterprise_id",enterprise_id);
			List<BusinessType> businesstypeList = partnerEnterpriseService.businessTypeList();
			model.addAttribute("businesstypeList",businesstypeList);
			List<Partnerenterpriserank> partnerenterpriserankList = partnerEnterpriseService.partnerEnterpriseRankList();
			model.addAttribute("partnerenterpriserankList",partnerenterpriserankList);
			List<Enterprise> mainenterpriseList = partnerEnterpriseService.mainEnterpriseList();
			model.addAttribute("mainenterpriseList",mainenterpriseList);
			
			return "partnerEnterprise/partnerEnterpriseInsert";
			
		}else {
			Enterprise enterprise = dataRefill.makeEnterprise(enterpriseForm, enterprise_id);
			partnerEnterpriseService.partnerEnterpriseUpdate(enterprise);
			redirectAttributes.addFlashAttribute("complete","外部取引先企業が1件更新されました");
			return "redirect:/partnerEnterprise";
		}
		
	}
	
	@PostMapping("/partnerEnterprise/delete")
	public String partnerEnterpriseDelete(@RequestParam("enterprise_id") int enterprise_id,
			RedirectAttributes redirectAttributes) {
		partnerEnterpriseService.partnerEnterpriseDelete(enterprise_id);
		redirectAttributes.addFlashAttribute("complete","外部取引先企業が1件削除されました");
		return "redirect:/partnerEnterprise";
	}

}
