package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.component.dataRefill.DataRefill;
import com.example.demo.entity.Contactmethod;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.User;
import com.example.demo.entity.Workrecord;
import com.example.demo.entity.Worktype;
import com.example.demo.form.enterprise.WorkrecordForm;
import com.example.demo.service.enterprisename.EnterprisenameService;
import com.example.demo.service.workRecord.WorkRecordService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/workrecord/partnerEnterprise")
@RequiredArgsConstructor
public class WorkRecordController {
	
	private final WorkRecordService workRecordService;
	private final EnterprisenameService enterprisenameService;
	private final DataRefill dataRefill;
	
	@GetMapping("{partnerenterprise_id}")
	public String workRecordAll(@PathVariable int partnerenterprise_id,
			Model model) {
		
		model.addAttribute("partnerenterprise_id", partnerenterprise_id);
		List<Workrecord> workrecordList = workRecordService.workrecordAll(partnerenterprise_id);
		model.addAttribute("workrecordList", workrecordList);
		model.addAttribute("workrecordCount", workrecordList.size() + "件のレコード");
		String partnerEnterpriseName = enterprisenameService.getPartnerenterprisename(partnerenterprise_id);
		model.addAttribute("title", partnerEnterpriseName + "の活動記録");
		
		return "workrecord/workrecordAll";
	}
	
	@GetMapping("/detail/{workrecord_id}")
	public String workRecordDetail(@PathVariable int workrecord_id,
			Model model) {
		Workrecord workrecord = workRecordService.workrecordDetail(workrecord_id);
		model.addAttribute("workrecord",workrecord);
		return "workrecord/workrecordDetail";
	}
	
	
	@GetMapping("/insert/{partnerenterprise_id}")
	public String workRecordInsert(WorkrecordForm workrecordForm,
			@PathVariable int partnerenterprise_id,
			Model model) {
		workrecordForm.setNewWorkrecord(true);
		model.addAttribute("title", enterprisenameService.getPartnerenterprisename(partnerenterprise_id) + "の新規活動記録登録");
		model.addAttribute("workrecordForm",workrecordForm);
		List<Worktype> worktypeList = workRecordService.worktypeList();
		model.addAttribute("worktypeList",worktypeList);
		List<Enterprise> entryenterpriseList = workRecordService.entryenterpriseList();
		model.addAttribute("entryenterpriseList",entryenterpriseList);
		List<User> userList = workRecordService.userList();
		model.addAttribute("userList",userList);
		List<User> partneruserList = workRecordService.partneruserList();
		model.addAttribute("partneruserList",partneruserList);
		List<Contactmethod> contactmethodList = workRecordService.contactmethodList();
		model.addAttribute("contactmethodList",contactmethodList);
		model.addAttribute("salesworktypeList",workRecordService.salesworktypeList());
		model.addAttribute("partnerenterprise_id", partnerenterprise_id);
		
		return "workrecord/workrecordInsert";
	}
	
	@PostMapping("/insert")
	public String executeWorkRecordInsert(@Validated WorkrecordForm workrecordForm,
			BindingResult result,
			@RequestParam("partnerenterprise_id") int partnerenterprise_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			workrecordForm.setNewWorkrecord(true);
			model.addAttribute("title", enterprisenameService.getPartnerenterprisename(partnerenterprise_id) + "の新規活動記録登録");
			model.addAttribute("workrecordForm",workrecordForm);
			List<Worktype> worktypeList = workRecordService.worktypeList();
			model.addAttribute("worktypeList",worktypeList);
			List<Enterprise> entryenterpriseList = workRecordService.entryenterpriseList();
			model.addAttribute("entryenterpriseList",entryenterpriseList);
			List<User> userList = workRecordService.userList();
			model.addAttribute("userList",userList);
			List<User> partneruserList = workRecordService.partneruserList();
			model.addAttribute("partneruserList",partneruserList);
			List<Contactmethod> contactmethodList = workRecordService.contactmethodList();
			model.addAttribute("contactmethodList",contactmethodList);
			model.addAttribute("salesworktypeList",workRecordService.salesworktypeList());
			model.addAttribute("partnerenterprise_id", partnerenterprise_id);
			
			return "workrecord/workrecordInsert";
			
		}else {
			Workrecord workrecord = dataRefill.makeWorkrecord(workrecordForm, 0, partnerenterprise_id);
			workRecordService.workrecordInsert(workrecord);
			redirectAttributes.addFlashAttribute("complete","新規活動記録が1件追加されました");
			return "redirect:/workrecord/partnerEnterprise/" + partnerenterprise_id;
		}
	}
	
	
	@GetMapping("/update")
	public String worktypeUpdate(WorkrecordForm workrecordForm,
			@RequestParam("workrecord_id") int workrecord_id,
			@RequestParam("partnerenterprise_id") int partnerenterprise_id,
			Model model) {
		workrecordForm.setNewWorkrecord(false);
		model.addAttribute("title", enterprisenameService.getPartnerenterprisename(partnerenterprise_id) + "の活動記録更新");
		Workrecord workrecord = workRecordService.workrecordDetail(workrecord_id);
		workrecordForm = dataRefill.makeWorkrecordForm(workrecord, partnerenterprise_id);
		model.addAttribute("workrecordForm",workrecordForm);
		model.addAttribute("workrecord_id",workrecord_id);
		model.addAttribute("partnerenterprise_id", partnerenterprise_id);
		List<Worktype> worktypeList = workRecordService.worktypeList();
		model.addAttribute("worktypeList",worktypeList);
		List<Enterprise> entryenterpriseList = workRecordService.entryenterpriseList();
		model.addAttribute("entryenterpriseList",entryenterpriseList);
		List<User> userList = workRecordService.userList();
		model.addAttribute("userList",userList);
		List<User> partneruserList = workRecordService.partneruserList();
		model.addAttribute("partneruserList",partneruserList);
		List<Contactmethod> contactmethodList = workRecordService.contactmethodList();
		model.addAttribute("contactmethodList",contactmethodList);
		model.addAttribute("salesworktypeList",workRecordService.salesworktypeList());
		
		return "workrecord/workrecordInsert";
	}
	
	
	@PostMapping("/update")
	public String executeWorktypeUpdate(WorkrecordForm workrecordForm,
			BindingResult result,
			@RequestParam("workrecord_id") int workrecord_id,
			@RequestParam("partnerenterprise_id") int partnerenterprise_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			workrecordForm.setNewWorkrecord(false);
			model.addAttribute("title", enterprisenameService.getPartnerenterprisename(partnerenterprise_id) + "の活動記録更新");
			model.addAttribute("workrecord_id",workrecord_id);
			model.addAttribute("partnerenterprise_id", partnerenterprise_id);
			List<Worktype> worktypeList = workRecordService.worktypeList();
			model.addAttribute("worktypeList",worktypeList);
			List<Enterprise> entryenterpriseList = workRecordService.entryenterpriseList();
			model.addAttribute("entryenterpriseList",entryenterpriseList);
			List<User> userList = workRecordService.userList();
			model.addAttribute("userList",userList);
			List<User> partneruserList = workRecordService.partneruserList();
			model.addAttribute("partneruserList",partneruserList);
			List<Contactmethod> contactmethodList = workRecordService.contactmethodList();
			model.addAttribute("contactmethodList",contactmethodList);
			model.addAttribute("salesworktypeList",workRecordService.salesworktypeList());
			
			return "workrecord/workrecordInsert";
			
		}else {
			Workrecord workrecord = dataRefill.makeWorkrecord(workrecordForm, workrecord_id, partnerenterprise_id);
			workRecordService.workrecordUpdate(workrecord);
			redirectAttributes.addFlashAttribute("complete","活動記録が1件更新されました");
			return "redirect:/workrecord/partnerEnterprise/" + partnerenterprise_id;
		}
		
	}
	
	
	@PostMapping("/delete")
	public String workRecordDelete(@RequestParam("workrecord_id") int workrecord_id,
			@RequestParam("partnerenterprise_id") int partnerenterprise_id,
			RedirectAttributes redirectAttributes) {
		workRecordService.workrecordDelete(workrecord_id);
		redirectAttributes.addFlashAttribute("complete","活動記録が1件削除されました");
		return "redirect:/workrecord/partnerEnterprise/" + partnerenterprise_id;
	}
	
	

}
