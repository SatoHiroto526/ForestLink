package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import com.example.demo.auth.CustomUserDetails;
import com.example.demo.component.dataRefill.DataRefill;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.User;
import com.example.demo.form.user.UserFilter;
import com.example.demo.form.user.UserForm;
import com.example.demo.service.enterprisename.EnterprisenameService;
import com.example.demo.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	private final EnterprisenameService enterprisenameService;
	private final DataRefill dataRefill;
	
	@GetMapping("/loginUserDetail")
	public String loginUserDetail(@AuthenticationPrincipal CustomUserDetails customUserDetails,
			Model model) {
		
		User userDetail = userService.userDetail(customUserDetails.getUsername());
		model.addAttribute("userDetail", userDetail);
		model.addAttribute("title","ログインユーザー詳細");
		model.addAttribute("logout", true);
		
		return "user/userDetail";
	}
	
	
	@GetMapping("/user/detail/{user_id}")
	public String userDetail(@PathVariable int user_id,
			Model model) {
		
		User userDetail = userService.userDetail(user_id);
		model.addAttribute("userDetail", userDetail);
		model.addAttribute("title","ユーザー詳細");
		model.addAttribute("logout", false);
		
		return "user/userDetail";
	}
	
	@GetMapping("/user")
	public String userAll(UserFilter userFilter,
			Model model,
			@ModelAttribute("complete") String complete) {
		List<User> userList = userService.userAll();
		model.addAttribute("userFilter", userFilter);
		List<Enterprise> enterpriseList = userService.entryEnterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		model.addAttribute("userList", userList);
		model.addAttribute("userCount",userList.size() + "件のレコード");
		model.addAttribute("complete", complete);
		return "user/userAll";
	}
	
	@GetMapping("/filteredUser")
	public String filteredUserAll(UserFilter userFilter,
			Model model) {
		int enterprise_Id = userFilter.getEnterprise_id();
		List<User> userList = userService.filteredUserList(enterprise_Id);
		model.addAttribute("userFilter", userFilter);
		List<Enterprise> enterpriseList = userService.entryEnterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		model.addAttribute("userList", userList);
		model.addAttribute("userCount",userList.size() + "件のレコード");
		return "user/userAll";
	}
	
	@GetMapping("/user/insert")
	public String userInsert(UserForm userForm,
			Model model) {
		userForm.setNewUser(true);
		model.addAttribute("title", "新規ユーザー登録");
		model.addAttribute("userForm",userForm);
		model.addAttribute("authorityList", userService.authortyList());
		List<Enterprise> enterpriseList = userService.entryEnterpriseList();
		model.addAttribute("enterpriseList", enterpriseList);
		
		return "user/userInsert";
	}
	
	
	@PostMapping("/user/insert")
	public String executeUserInsert(@Validated UserForm userForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			userForm.setNewUser(true);
			model.addAttribute("title", "新規ユーザー登録");
			model.addAttribute("userForm", userForm);
			model.addAttribute("authorityList", userService.authortyList());
			List<Enterprise> enterpriseList = userService.entryEnterpriseList();
			model.addAttribute("enterpriseList", enterpriseList);
			
			return "user/userInsert";
			
		}else {
			User user = dataRefill.makeUser(userForm, 0);
			userService.userInsert(user);
			redirectAttributes.addFlashAttribute("complete","新規ユーザーが1件追加されました");
			return "redirect:/user";
		}
	}
	
	@GetMapping("/user/entryEnterprise/{enterprise_id}")
	public String enterpriseUser(@PathVariable int enterprise_id,
			Model model) {
		List<User> userList = userService.filteredUserList(enterprise_id);
		String enterprisename = enterprisenameService.getEntryenterprisename(enterprise_id);
		model.addAttribute("userList", userList);
		model.addAttribute("title", enterprisename + "のユーザー一覧");
		model.addAttribute("userCount",userList.size() + "件のレコード");
		return "user/enterpriseUser";
	}
	
	
	@GetMapping("/user/update/{user_id}")
	public String userUpdate(UserForm userForm,
			@PathVariable int user_id,
			Model model) {
		userForm.setNewUser(false);
		model.addAttribute("title", "ユーザー更新");
		User user = userService.userDetail(user_id);
		userForm = dataRefill.makeUserForm(user);
		model.addAttribute("authorityList", userService.authortyList());
		model.addAttribute("userForm",userForm);
		model.addAttribute("user_id",user_id);
		List<Enterprise> enterpriseList = userService.entryEnterpriseList();
		model.addAttribute("enterpriseList",enterpriseList);
		
		return "user/userInsert";
	}
	
	@PostMapping("/user/update")
	public String executeUserUpdate(@Validated UserForm userForm,
			BindingResult result,
			@RequestParam("user_id") int user_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			userForm.setNewUser(false);
			model.addAttribute("title", "ユーザー更新");
			model.addAttribute("user_id",user_id);
			model.addAttribute("authorityList", userService.authortyList());
			List<Enterprise> enterpriseList = userService.entryEnterpriseList();
			model.addAttribute("enterpriseList",enterpriseList);
			
			return "user/userInsert";
			
		}else {
			User user = dataRefill.makeUser(userForm, user_id);
			userService.userUpdate(user);
			redirectAttributes.addFlashAttribute("complete","ユーザーが1件更新されました");
			return "redirect:/user";
		}
		
	}
	
	@PostMapping("/user/delete")
	public String userDelete(@RequestParam("user_id") int user_id,
			RedirectAttributes redirectAttributes) {
		userService.userDelete(user_id);
		redirectAttributes.addFlashAttribute("complete","ユーザーが1件削除されました");
		return "redirect:/user";
	}

}
