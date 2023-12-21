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
import com.example.demo.form.enterprise.EnterpriseFilter;
import com.example.demo.form.enterprise.EnterpriseForm;
import com.example.demo.service.entryEnterprise.EntryEnterpriseService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class EntryEnterpriseController {
	
	private final EntryEnterpriseService entryEnterpriseService;
	private final DataRefill dataRefill;
	
	@GetMapping("/entryEnterprise")
	public String entryEnterpriseAll(EnterpriseFilter enterpriseFilter,
			Model model,
			@ModelAttribute("complete") String complete) {
		List<Enterprise> enterpriseList = entryEnterpriseService.entryEnterpriseList();
		model.addAttribute("enterpriseFilter", enterpriseFilter);
		List<BusinessType> businesstypeList = entryEnterpriseService.businessTypeList();
		model.addAttribute("businesstypeList",businesstypeList);
		model.addAttribute("enterpriseList", enterpriseList);
		model.addAttribute("enterpriseCount",enterpriseList.size() + "件のレコード");
		model.addAttribute("complete", complete);
		return "entryEnterprise/entryEnterpriseAll";
	}
	
	@GetMapping("/filteredEntryEnterprise")
	public String filteredEntryEnterpriseAll(EnterpriseFilter enterpriseFilter,
			Model model) {
		int businesstype_Id = enterpriseFilter.getBusinesstype_id();
		List<Enterprise> enterpriseList = entryEnterpriseService.filteredEntryEnterpriseList(businesstype_Id);
		model.addAttribute("enterpriseFilter", enterpriseFilter);
		List<BusinessType> businesstypeList = entryEnterpriseService.businessTypeList();
		model.addAttribute("businesstypeList", businesstypeList);
		model.addAttribute("enterpriseList", enterpriseList);
		model.addAttribute("enterpriseCount",enterpriseList.size() + "件のレコード");
		return "entryEnterprise/entryEnterpriseAll";
	}
	
	
	@GetMapping("/entryEnterprise/detail/{enterprise_id}")
	public String entryEnterpriseDetail(@PathVariable int enterprise_id,
			Model model) {
		
		Enterprise entryEnterprise = entryEnterpriseService.entryEnterpriseDetail(enterprise_id);
		model.addAttribute("entryEnterprise", entryEnterprise);
		
		return "entryEnterprise/entryEnterpriseDetail";
	}
	
	@GetMapping("/entryEnterprise/insert")
	public String entryEnterpriseInsert(EnterpriseForm enterpriseForm,
			Model model) {
		enterpriseForm.setNewEnterprise(true);
		model.addAttribute("title", "新規参加企業登録");
		model.addAttribute("enterpriseForm",enterpriseForm);
		List<BusinessType> businesstypeList = entryEnterpriseService.businessTypeList();
		model.addAttribute("businesstypeList",businesstypeList);
		
		return "entryEnterprise/entryEnterpriseInsert";
	}
	
	
	@PostMapping("/entryEnterprise/insert")
	public String executeEntryEnterpriseInsert(@Validated EnterpriseForm enterpriseForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			enterpriseForm.setNewEnterprise(true);
			model.addAttribute("title", "新規参加企業登録");
			model.addAttribute("enterpriseForm",enterpriseForm);
			List<BusinessType> businesstypeList = entryEnterpriseService.businessTypeList();
			model.addAttribute("businesstypeList",businesstypeList);
			
			return "entryEnterprise/entryEnterpriseInsert";
			
		}else {
			Enterprise enterprise = dataRefill.makeEnterprise(enterpriseForm, 0);
			entryEnterpriseService.enterpriseInsert(enterprise);
			redirectAttributes.addFlashAttribute("complete","新規参加企業が1件追加されました");
			return "redirect:/entryEnterprise";
		}
	}
	
	
	@GetMapping("/entryEnterprise/update/{enterprise_id}")
	public String entryEnterpriseUpdate(EnterpriseForm enterpriseForm,
			@PathVariable int enterprise_id,
			Model model) {
		enterpriseForm.setNewEnterprise(false);
		model.addAttribute("title", "参加企業更新");
		Enterprise entryEnterprise = entryEnterpriseService.entryEnterpriseDetail(enterprise_id);
		enterpriseForm = dataRefill.makeEntryEnterpriseForm(entryEnterprise);
		model.addAttribute("enterpriseForm",enterpriseForm);
		model.addAttribute("enterprise_id",enterprise_id);
		List<BusinessType> businesstypeList = entryEnterpriseService.businessTypeList();
		model.addAttribute("businesstypeList",businesstypeList);
		
		return "entryEnterprise/entryEnterpriseInsert";
	}
	
	@PostMapping("/entryEnterprise/update")
	public String executeEntryEnterpriseUpdate(@Validated EnterpriseForm enterpriseForm,
			BindingResult result,
			@RequestParam("enterprise_id") int enterprise_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			enterpriseForm.setNewEnterprise(false);
			model.addAttribute("title", "参加企業更新");
			model.addAttribute("enterprise_id",enterprise_id);
			List<BusinessType> businesstypeList = entryEnterpriseService.businessTypeList();
			model.addAttribute("businesstypeList",businesstypeList);
			
			return "entryEnterprise/entryEnterpriseInsert";
			
		}else {
			Enterprise enterprise = dataRefill.makeEnterprise(enterpriseForm, enterprise_id);
			entryEnterpriseService.enterpriseUpdate(enterprise);
			redirectAttributes.addFlashAttribute("complete","参加企業が1件更新されました");
			return "redirect:/entryEnterprise";
		}
		
	}
	
	@PostMapping("/entryEnterprise/delete")
	public String enterpriseDelete(@RequestParam("enterprise_id") int enterprise_id,
			RedirectAttributes redirectAttributes) {
		entryEnterpriseService.enterpriseDelete(enterprise_id);
		redirectAttributes.addFlashAttribute("complete","参加企業が1件削除されました");
		return "redirect:/entryEnterprise";
	}
	


}
