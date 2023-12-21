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
import com.example.demo.entity.User;
import com.example.demo.form.user.PartnerUserForm;
import com.example.demo.form.user.UserFilter;
import com.example.demo.service.enterprisename.EnterprisenameService;
import com.example.demo.service.partnerUser.PartnerUserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class PartnerUserController {
	
	private final PartnerUserService partnerUserService;
	private final EnterprisenameService enterprisenameService;
	private final DataRefill dataRefill;
	
	@GetMapping("/partnerUser")
	public String userAll(UserFilter userFilter,
			Model model,
			@ModelAttribute("complete") String complete) {
		List<User> userList = partnerUserService.userAll();
		model.addAttribute("userFilter", userFilter);
		List<Enterprise> enterpriseList = partnerUserService.partnerEnterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		model.addAttribute("userList", userList);
		model.addAttribute("userCount",userList.size() + "件のレコード");
		model.addAttribute("complete", complete);
		return "partnerUser/partnerUserAll";
	}
	
	@GetMapping("/filteredPartnerUser")
	public String filteredPartnerUserAll(UserFilter userFilter,
			Model model) {
		int enterprise_Id = userFilter.getEnterprise_id();
		List<User> userList = partnerUserService.filteredUserList(enterprise_Id);
		model.addAttribute("userFilter", userFilter);
		List<Enterprise> enterpriseList = partnerUserService.partnerEnterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		model.addAttribute("userList", userList);
		model.addAttribute("userCount",userList.size() + "件のレコード");
		return "partnerUser/partnerUserAll";
	}
	
	
	@GetMapping("/partnerUser/detail/{user_id}")
	public String userDetail(@PathVariable int user_id,
			Model model) {
		
		User userDetail = partnerUserService.userDetail(user_id);
		model.addAttribute("userDetail", userDetail);
		model.addAttribute("title","外部取引先人材詳細");
		
		return "partnerUser/partnerUserDetail";
	}
	
	
	@GetMapping("/partnerUser/insert")
	public String userInsert(PartnerUserForm partnerUserForm,
			Model model) {
		partnerUserForm.setNewUser(true);
		model.addAttribute("title", "新規外部取引先人材登録");
		model.addAttribute("partnerUserForm",partnerUserForm);
		List<Enterprise> enterpriseList = partnerUserService.partnerEnterpriseList();
		model.addAttribute("enterpriseList", enterpriseList);
		
		return "partnerUser/partnerUserInsert";
	}
	
	
	@PostMapping("/partnerUser/insert")
	public String executePartnerUserInsert(@Validated PartnerUserForm partnerUserForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			partnerUserForm.setNewUser(true);
			model.addAttribute("title", "新規外部取引先人材登録");
			model.addAttribute("partnerUserForm",partnerUserForm);
			List<Enterprise> enterpriseList = partnerUserService.partnerEnterpriseList();
			model.addAttribute("enterpriseList", enterpriseList);
			
			return "partnerUser/partnerUserInsert";
			
		}else {
			User user = dataRefill.makeUser(partnerUserForm, 0);
			partnerUserService.userInsert(user);
			redirectAttributes.addFlashAttribute("complete","新規取引先人材が1件追加されました");
			return "redirect:/partnerUser";
		}
	}
	
	
	@GetMapping("/partnerUser/update/{user_id}")
	public String userUpdate(PartnerUserForm partnerUserForm,
			@PathVariable int user_id,
			Model model) {
		partnerUserForm.setNewUser(false);
		model.addAttribute("title", "外部取引先人材更新");
		User user = partnerUserService.userDetail(user_id);
		partnerUserForm = dataRefill.makePartnerUserForm(user);
		model.addAttribute("partnerUserForm",partnerUserForm);
		model.addAttribute("user_id",user_id);
		List<Enterprise> enterpriseList = partnerUserService.partnerEnterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		
		return "partnerUser/partnerUserInsert";
	}
	
	
	@PostMapping("/partnerUser/update")
	public String executePartnerUserUpdate(@Validated PartnerUserForm partnerUserForm,
			BindingResult result,
			@RequestParam("user_id") int user_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			partnerUserForm.setNewUser(false);
			model.addAttribute("title", "外部取引先人材更新");
			model.addAttribute("user_id",user_id);
			List<Enterprise> enterpriseList = partnerUserService.partnerEnterpriseList();
			model.addAttribute("enterpriseList",enterpriseList);
			
			return "partnerUser/partnerUserInsert";
			
		}else {
			User user = dataRefill.makeUser(partnerUserForm, user_id);
			partnerUserService.userUpdate(user);
			redirectAttributes.addFlashAttribute("complete","外部取引先人材が1件更新されました");
			return "redirect:/partnerUser";
		}
		
	}
	
	
	@GetMapping("/partnerUser/partnerEnterprise/{partnerenterprise_id}")
	public String enterpriseUser(@PathVariable int partnerenterprise_id,
			Model model) {
		List<User> userList = partnerUserService.filteredUserList(partnerenterprise_id);
		String enterprisename = enterprisenameService.getPartnerenterprisename(partnerenterprise_id);
		model.addAttribute("userList", userList);
		model.addAttribute("title", enterprisename + "の外部取引先人材一覧");
		model.addAttribute("userCount",userList.size() + "件のレコード");
		return "partnerUser/partnerEnterpriseUser";
	}
	
	@PostMapping("/partnerUser/delete")
	public String userDelete(@RequestParam("user_id") int user_id,
			RedirectAttributes redirectAttributes) {
		partnerUserService.userDelete(user_id);
		redirectAttributes.addFlashAttribute("complete","外部取引先人材が1件削除されました");
		return "redirect:/partnerUser";
	}

}
