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
import com.example.demo.entity.Breed;
import com.example.demo.entity.BusinessType;
import com.example.demo.entity.Contactmethod;
import com.example.demo.entity.Partnerenterpriserank;
import com.example.demo.entity.Producttype;
import com.example.demo.entity.Stockstatus;
import com.example.demo.entity.Worktype;
import com.example.demo.form.masterData.BreedForm;
import com.example.demo.form.masterData.BusinesstypeForm;
import com.example.demo.form.masterData.ContactmethodForm;
import com.example.demo.form.masterData.PartnerenterpriserankForm;
import com.example.demo.form.masterData.ProducttypeForm;
import com.example.demo.form.masterData.StockstatusForm;
import com.example.demo.form.masterData.WorktypeForm;
import com.example.demo.service.masterData.MasterDataService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/masterData")
@RequiredArgsConstructor
public class MastarDataController {
	
	private final  MasterDataService  masterDataService;
	private final DataRefill dataRefill;
	
	@GetMapping
	public String mastarData() {
		return "masterData/masterDataAll";
	}
	
	@GetMapping("/businessType")
	public String businessType(Model model,
			@ModelAttribute("complete") String complete) {
		List<BusinessType> businesstypeList = masterDataService.businessTypeList();
		model.addAttribute("businesstypeCount" ,businesstypeList.size() + "件のレコード");
		model.addAttribute("businesstypeList" ,businesstypeList);
		model.addAttribute("complete" ,complete);
		return "masterData/businesstype";
	}
	
	@GetMapping("/businessType/insert")
	public String businesstypeInsert(BusinesstypeForm businesstypeForm,
			Model model) {
		
		businesstypeForm.setNewBusinesstype(true);
		model.addAttribute("title", "新規事業区分登録");
		model.addAttribute("businesstypeForm",businesstypeForm);
		
		return "masterData/businesstypeInsert";
	}
	
	@PostMapping("/businessType/insert")
	public String executeBusinesstypeInsert(@Validated BusinesstypeForm businesstypeForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			businesstypeForm.setNewBusinesstype(true);
			model.addAttribute("title", "新規事業区分登録");
			model.addAttribute("businesstypeForm",businesstypeForm);
			
			return "masterData/businesstypeInsert";
			
		}else {
			BusinessType businesstype = dataRefill.makeBusinesstype(businesstypeForm, 0);
			masterDataService.businessTypeInsert(businesstype);
			redirectAttributes.addFlashAttribute("complete","新規事業区分が1件追加されました");
			return "redirect:/masterData/businessType";
		}
		
	}
	
	
	@GetMapping("/businessType/update/{businesstype_id}")
	public String businesstypeUpdate(BusinesstypeForm businesstypeForm,
			@PathVariable int businesstype_id,
			Model model) {
		businesstypeForm.setNewBusinesstype(false);
		model.addAttribute("title", "事業区分更新");
		BusinessType businessType = masterDataService.businessTypeDetail(businesstype_id);
		businesstypeForm = dataRefill.makeBusinesstypeForm(businessType);
		model.addAttribute("businesstypeForm",businesstypeForm);
		model.addAttribute("businesstype_id",businesstype_id);
		
		return "masterData/businesstypeInsert";
	}
	
	@PostMapping("/businessType/update")
	public String executeBusinesstypeUpdate(@Validated BusinesstypeForm businesstypeForm,
			BindingResult result,
			@RequestParam("businesstype_id") int businesstype_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			businesstypeForm.setNewBusinesstype(false);
			model.addAttribute("title", "事業区分更新");
			model.addAttribute("businesstype_id",businesstype_id);
			
			return "masterData/businesstypeInsert";
			
		}else {
			BusinessType businesstype = dataRefill.makeBusinesstype(businesstypeForm, businesstype_id);
			masterDataService.businessTypeUpdate(businesstype);
			redirectAttributes.addFlashAttribute("complete","事業区分が1件更新されました");
			return "redirect:/masterData/businessType";
		}
		
	}
	
	@GetMapping("/partnerenterpriserank")
	public String partnerenterpriserank(Model model,
			@ModelAttribute("complete") String complete) {
		List<Partnerenterpriserank> partnerenterpriserankList = masterDataService.partnerenterpriserankList();
		model.addAttribute("partnerenterpriserankCount", partnerenterpriserankList.size() + "件のレコード");
		model.addAttribute("partnerenterpriserankList", partnerenterpriserankList);
		model.addAttribute("complete" ,complete);
		return "masterData/partnerenterpriserank";
	}
	
	
	@GetMapping("/partnerenterpriserank/insert")
	public String partnerenterpriserankInsert(PartnerenterpriserankForm partnerenterpriserankForm,
			Model model) {
		
		partnerenterpriserankForm.setNewPartnerenterpriserank(true);
		model.addAttribute("title", "新規取引先ランク登録");
		model.addAttribute("partnerenterpriserankForm", partnerenterpriserankForm);
		
		return "masterData/partnerenterpriserankInsert";
	}
	
	
	@PostMapping("/partnerenterpriserank/insert")
	public String executePartnerenterpriserankInsert(@Validated PartnerenterpriserankForm partnerenterpriserankForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			partnerenterpriserankForm.setNewPartnerenterpriserank(true);
			model.addAttribute("title", "新規取引先ランク登録");
			model.addAttribute("partnerenterpriserankForm", partnerenterpriserankForm);
			
			return "masterData/partnerenterpriserankInsert";
			
		}else {
			Partnerenterpriserank partnerenterpriserank = dataRefill.makePartnerenterpriserank(partnerenterpriserankForm, 0);
			masterDataService.partnerenterpriserankInsert(partnerenterpriserank);
			redirectAttributes.addFlashAttribute("complete","新規取引先ランクが1件追加されました");
			return "redirect:/masterData/partnerenterpriserank";
		}
		
	}
	
	
	@GetMapping("/partnerenterpriserank/update/{partnerenterpriserank_id}")
	public String partnerenterpriserankUpdate(PartnerenterpriserankForm partnerenterpriserankForm,
			@PathVariable int partnerenterpriserank_id,
			Model model) {
		partnerenterpriserankForm.setNewPartnerenterpriserank(false);
		model.addAttribute("title", "取引先ランク更新");
		Partnerenterpriserank partnerenterpriserank = masterDataService.partnerenterpriserankDetail(partnerenterpriserank_id);
		partnerenterpriserankForm = dataRefill.makepartnerenterpriserankForm(partnerenterpriserank);
		model.addAttribute("partnerenterpriserankForm", partnerenterpriserankForm);
		model.addAttribute("partnerenterpriserank_id",partnerenterpriserank_id);
		
		return "masterData/partnerenterpriserankInsert";
	}
	
	@PostMapping("/partnerenterpriserank/update")
	public String executePartnerenterpriserankUpdate(@Validated PartnerenterpriserankForm partnerenterpriserankForm,
			BindingResult result,
			@RequestParam("partnerenterpriserank_id") int partnerenterpriserank_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			partnerenterpriserankForm.setNewPartnerenterpriserank(false);
			model.addAttribute("title", "取引先ランク更新");
			model.addAttribute("partnerenterpriserank_id",partnerenterpriserank_id);
			
			return "masterData/partnerenterpriserankInsert";
			
		}else {
			Partnerenterpriserank partnerenterpriserank = dataRefill.makePartnerenterpriserank(partnerenterpriserankForm, partnerenterpriserank_id);
			masterDataService.partnerenterpriserankUpdate(partnerenterpriserank);
			redirectAttributes.addFlashAttribute("complete","取引先ランクが1件更新されました");
			return "redirect:/masterData/partnerenterpriserank";
		}
		
	}
	
	@GetMapping("/worktype")
	public String worktype(Model model,
			@ModelAttribute("complete") String complete) {
		List<Worktype> worktypeList = masterDataService.worktypeList();
		model.addAttribute("worktypeCount", worktypeList.size() + "件のレコード");
		model.addAttribute("worktypeList", worktypeList);
		model.addAttribute("complete" ,complete);
		return "masterData/worktype";
	}
	
	
	@GetMapping("/worktype/insert")
	public String worktypeInsert(WorktypeForm worktypeForm,
			Model model) {
		
		worktypeForm.setNewWorktype(true);
		model.addAttribute("title", "新規活動種別登録");
		model.addAttribute("worktypeForm", worktypeForm);
		
		return "masterData/worktypeInsert";
	}
	
	@PostMapping("/worktype/insert")
	public String executeWorktypeInsert(@Validated WorktypeForm worktypeForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			worktypeForm.setNewWorktype(true);
			model.addAttribute("title", "新規活動種別登録");
			model.addAttribute("worktypeForm", worktypeForm);
			
			return "masterData/worktypeInsert";
			
		}else {
			Worktype worktype = dataRefill.makeWorktype(worktypeForm, 0);
			masterDataService.worktypeInsert(worktype);
			redirectAttributes.addFlashAttribute("complete","新規活動種別が1件追加されました");
			return "redirect:/masterData/worktype";
		}
		
	}
	
	@GetMapping("/worktype/update/{worktype_id}")
	public String worktypeUpdate(WorktypeForm worktypeForm,
			@PathVariable int worktype_id,
			Model model) {
		worktypeForm.setNewWorktype(false);
		model.addAttribute("title", "活動種別更新");
		Worktype worktype = masterDataService.worktypeDetail(worktype_id);
		worktypeForm = dataRefill.makeWorktypeForm(worktype);
		model.addAttribute("worktypeForm", worktypeForm);
		model.addAttribute("worktype_id",worktype_id);
		
		return "masterData/worktypeInsert";
	}
	
	
	@PostMapping("/worktype/update")
	public String executeWorktypeUpdate(@Validated WorktypeForm worktypeForm,
			BindingResult result,
			@RequestParam("worktype_id") int worktype_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			worktypeForm.setNewWorktype(false);
			model.addAttribute("title", "活動種別更新");
			model.addAttribute("worktype_id",worktype_id);
			
			return "masterData/worktypeInsert";
			
		}else {
			Worktype worktype = dataRefill.makeWorktype(worktypeForm, worktype_id);
			masterDataService.worktypeUpdate(worktype);
			redirectAttributes.addFlashAttribute("complete","活動種別が1件更新されました");
			return "redirect:/masterData/worktype";
		}
		
	}
	
	
	@GetMapping("/contactmethod")
	public String contactmethod(Model model,
			@ModelAttribute("complete") String complete) {
		List<Contactmethod> contactmethodList = masterDataService.contactmethodList();
		model.addAttribute("contactmethodCount", contactmethodList.size() + "件のレコード");
		model.addAttribute("contactmethodList", contactmethodList);
		model.addAttribute("complete" ,complete);
		return "masterData/contactmethod";
	}
	
	
	@GetMapping("/contactmethod/insert")
	public String contactmethodInsert(ContactmethodForm contactmethodForm,
			Model model) {
		
		contactmethodForm.setNewContactmethod(true);
		model.addAttribute("title", "新規連絡手段登録");
		model.addAttribute("contactmethodForm", contactmethodForm);
		
		return "masterData/contactmethodInsert";
	}
	
	
	@PostMapping("/contactmethod/insert")
	public String executeContactmethodInsert(@Validated ContactmethodForm contactmethodForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			contactmethodForm.setNewContactmethod(true);
			model.addAttribute("title", "新規連絡手段登録");
			model.addAttribute("contactmethodForm", contactmethodForm);
			
			return "masterData/contactmethodInsert";
			
		}else {
			Contactmethod contactmethod = dataRefill.makeContactmethod(contactmethodForm, 0);
			masterDataService.contactmethodInsert(contactmethod);
			redirectAttributes.addFlashAttribute("complete","新規連絡手段が1件追加されました");
			return "redirect:/masterData/contactmethod";
		}
		
	}
	
	
	@GetMapping("/contactmethod/update/{contactmethod_id}")
	public String worktypeUpdate(ContactmethodForm contactmethodForm,
			@PathVariable int contactmethod_id,
			Model model) {
		contactmethodForm.setNewContactmethod(false);
		model.addAttribute("title", "連絡手段更新");
		Contactmethod contactmethod = masterDataService.contactmethodDetail(contactmethod_id);
		contactmethodForm = dataRefill.makeContactmethodForm(contactmethod);
		model.addAttribute("contactmethodForm", contactmethodForm);
		model.addAttribute("contactmethod_id",contactmethod_id);
		
		return "masterData/contactmethodInsert";
	}
	
	
	@PostMapping("/contactmethod/update")
	public String executeContactmethodUpdate(@Validated ContactmethodForm contactmethodForm,
			BindingResult result,
			@RequestParam("contactmethod_id") int contactmethod_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			contactmethodForm.setNewContactmethod(false);
			model.addAttribute("title", "連絡手段更新");
			model.addAttribute("contactmethod_id",contactmethod_id);
			
			return "masterData/contactmethodInsert";
			
		}else {
			Contactmethod contactmethod = dataRefill.makeContactmethod(contactmethodForm, contactmethod_id);
			masterDataService.contactmethodUpdate(contactmethod);
			redirectAttributes.addFlashAttribute("complete","連絡手段が1件更新されました");
			return "redirect:/masterData/contactmethod";
		}
		
	}
	
	@PostMapping("/businessType/delete")
	public String businesstypeDelete(@RequestParam("businesstype_id") int businesstype_id,
			RedirectAttributes redirectAttributes) {
		masterDataService.businesstypeDelete(businesstype_id);
		redirectAttributes.addFlashAttribute("complete","事業区分が1件削除されました");
		return "redirect:/masterData/businessType";
	}
	
	@PostMapping("/partnerenterpriserank/delete")
	public String partnerenterpriserankDelete(@RequestParam("partnerenterpriserank_id") int partnerenterpriserank_id,
			RedirectAttributes redirectAttributes) {
		masterDataService.partnerenterpriserankDelete(partnerenterpriserank_id);
		redirectAttributes.addFlashAttribute("complete","取引先ランクが1件削除されました");
		return "redirect:/masterData/partnerenterpriserank";
	}
	
	
	@PostMapping("/worktype/delete")
	public String worktypeDelete(@RequestParam("worktype_id") int worktype_id,
			RedirectAttributes redirectAttributes) {
		masterDataService.worktypeDelete(worktype_id);
		redirectAttributes.addFlashAttribute("complete","活動種別が1件削除されました");
		return "redirect:/masterData/worktype";
	}
	
	@PostMapping("/contactmethod/delete")
	public String contactmethodDelete(@RequestParam("contactmethod_id") int contactmethod_id,
			RedirectAttributes redirectAttributes) {
		masterDataService.contactmethodDelete(contactmethod_id);
		redirectAttributes.addFlashAttribute("complete","連絡手段が1件削除されました");
		return "redirect:/masterData/contactmethod";
	}
	
	
	@GetMapping("/producttype")
	public String producttype(Model model,
			@ModelAttribute("complete") String complete) {
		List<Producttype> producttypeList = masterDataService.producttypeList();
		model.addAttribute("producttypeCount", producttypeList.size() + "件のレコード");
		model.addAttribute("producttypeList", producttypeList);
		model.addAttribute("complete" ,complete);
		return "masterData/producttype";
	}
	
	@GetMapping("/producttype/insert")
	public String producttypeInsert(ProducttypeForm producttypeForm,
			Model model) {
		
		producttypeForm.setNewProducttype(true);
		model.addAttribute("title", "新規製品種別登録");
		model.addAttribute("producttypeForm", producttypeForm);
		
		return "masterData/producttypeInsert";
	}
	
	@PostMapping("/producttype/insert")
	public String executeProducttypeInsert(@Validated ProducttypeForm producttypeForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			producttypeForm.setNewProducttype(true);
			model.addAttribute("title", "新規製品種別登録");
			model.addAttribute("producttypeForm", producttypeForm);
			
			return "masterData/producttypeInsert";
			
		}else {
			Producttype producttype = dataRefill.makeProducttype(producttypeForm, 0);
			masterDataService.producttypeInsert(producttype);
			redirectAttributes.addFlashAttribute("complete","新規製品種別が1件追加されました");
			return "redirect:/masterData/producttype";
		}
		
	}
	
	@GetMapping("/producttype/update/{producttype_id}")
	public String worktypeUpdate(ProducttypeForm producttypeForm,
			@PathVariable int producttype_id,
			Model model) {
		producttypeForm.setNewProducttype(false);
		model.addAttribute("title", "製品種別更新");
		Producttype producttype = masterDataService.producttypeDetail(producttype_id);
		producttypeForm = dataRefill.makeProducttypeForm(producttype);
		model.addAttribute("producttypeForm", producttypeForm);
		model.addAttribute("producttype_id",producttype_id);
		
		return "masterData/producttypeInsert";
	}
	
	@PostMapping("/producttype/update")
	public String executeProducttypeUpdate(@Validated ProducttypeForm producttypeForm,
			BindingResult result,
			@RequestParam("producttype_id") int producttype_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			producttypeForm.setNewProducttype(false);
			model.addAttribute("title", "製品種別更新");
			model.addAttribute("producttype_id",producttype_id);
			
			return "masterData/producttypeInsert";
			
		}else {
			Producttype producttype = dataRefill.makeProducttype(producttypeForm, producttype_id);
			masterDataService.producttypeUpdate(producttype);
			redirectAttributes.addFlashAttribute("complete","製品種別が1件更新されました");
			return "redirect:/masterData/producttype";
		}
		
	}
	
	@GetMapping("/breed")
	public String breed(Model model,
			@ModelAttribute("complete") String complete) {
		List<Breed> breedList = masterDataService.breedList();
		model.addAttribute("breedCount", breedList.size() + "件のレコード");
		model.addAttribute("breedList", breedList);
		model.addAttribute("complete" ,complete);
		return "masterData/breed";
	}
	
	@GetMapping("/breed/insert")
	public String breedInsert(BreedForm breedForm,
			Model model) {
		
		breedForm.setNewBreed(true);
		model.addAttribute("title", "新規品種登録");
		model.addAttribute("breedForm", breedForm);
		
		return "masterData/breedInsert";
	}
	
	@PostMapping("/breed/insert")
	public String executeBreedInsert(@Validated BreedForm breedForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			breedForm.setNewBreed(true);
			model.addAttribute("title", "新規品種登録");
			model.addAttribute("breedForm", breedForm);
			
			return "masterData/breedInsert";
			
		}else {
			Breed breed = dataRefill.makeBreed(breedForm, 0);
			masterDataService.breedInsert(breed);
			redirectAttributes.addFlashAttribute("complete","新規品種が1件追加されました");
			return "redirect:/masterData/breed";
		}
		
	}
	
	@GetMapping("/breed/update/{breed_id}")
	public String breedUpdate(BreedForm breedForm,
			@PathVariable int breed_id,
			Model model) {
		breedForm.setNewBreed(false);
		model.addAttribute("title", "品種更新");
		Breed breed = masterDataService.breedDetail(breed_id);
		breedForm = dataRefill.makeBreedForm(breed);
		model.addAttribute("breedForm", breedForm);
		model.addAttribute("breed_id",breed_id);
		
		return "masterData/breedInsert";
	}
	
	@PostMapping("/breed/update")
	public String executeBreedUpdate(@Validated BreedForm breedForm,
			BindingResult result,
			@RequestParam("breed_id") int breed_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			breedForm.setNewBreed(false);
			model.addAttribute("title", "品種更新");
			model.addAttribute("breed_id",breed_id);
			
			return "masterData/breedInsert";
			
		}else {
			Breed breed = dataRefill.makeBreed(breedForm, breed_id);
			masterDataService.breedUpdate(breed);
			redirectAttributes.addFlashAttribute("complete","品種が1件更新されました");
			return "redirect:/masterData/breed";
		}
		
	}
	
	@PostMapping("/producttype/delete")
	public String producttypeDelete(@RequestParam("producttype_id") int producttype_id,
			RedirectAttributes redirectAttributes) {
		masterDataService.producttypeDelete(producttype_id);
		redirectAttributes.addFlashAttribute("complete","製品種別が1件削除されました");
		return "redirect:/masterData/producttype";
	}
	
	@PostMapping("/breed/delete")
	public String breedDelete(@RequestParam("breed_id") int breed_id,
			RedirectAttributes redirectAttributes) {
		masterDataService.breedDelete(breed_id);
		redirectAttributes.addFlashAttribute("complete","品種が1件削除されました");
		return "redirect:/masterData/breed";
	}
	
	@GetMapping("/stockstatus")
	public String stockstatus(Model model,
			@ModelAttribute("complete") String complete) {
		List<Stockstatus> stockstatusList = masterDataService.stockstatusList();
		model.addAttribute("stockstatusCount" ,stockstatusList.size() + "件のレコード");
		model.addAttribute("stockstatusList" ,stockstatusList);
		model.addAttribute("complete" ,complete);
		return "masterData/stockstatus";
	}
	
	@GetMapping("/stockstatus/insert")
	public String stockstatusInsert(StockstatusForm stockstatusForm,
			Model model) {
		
		stockstatusForm.setNewStockstatus(true);
		model.addAttribute("title", "新規在庫状態登録");
		model.addAttribute("stockstatusForm",stockstatusForm);
		
		return "masterData/stockstatusInsert";
	}
	
	@PostMapping("/stockstatus/insert")
	public String executeStockstatusInsert(@Validated StockstatusForm stockstatusForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			stockstatusForm.setNewStockstatus(true);
			model.addAttribute("title", "新規在庫状態登録");
			model.addAttribute("stockstatusForm",stockstatusForm);
			
			return "masterData/stockstatusInsert";
			
		}else {
			Stockstatus stockstatus = dataRefill.makeStockstatus(stockstatusForm, 0);
			masterDataService.stockstatusInsert(stockstatus);
			redirectAttributes.addFlashAttribute("complete","新規在庫状態が1件追加されました");
			return "redirect:/masterData/stockstatus";
		}
		
	}
	
	@GetMapping("/stockstatus/update/{stockstatus_id}")
	public String stockstatusUpdate(StockstatusForm stockstatusForm,
			@PathVariable int stockstatus_id,
			Model model) {
		stockstatusForm.setNewStockstatus(false);
		model.addAttribute("title", "在庫状態更新");
		Stockstatus stockstatus = masterDataService.stockstatusDetail(stockstatus_id);
		stockstatusForm = dataRefill.makeStockstatusForm(stockstatus);
		model.addAttribute("stockstatusForm",stockstatusForm);
		model.addAttribute("stockstatus_id",stockstatus_id);
		
		return "masterData/stockstatusInsert";
	}
	
	@PostMapping("/stockstatus/update")
	public String executeStockstatusUpdate(@Validated StockstatusForm stockstatusForm,
			BindingResult result,
			@RequestParam("stockstatus_id") int stockstatus_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			stockstatusForm.setNewStockstatus(false);
			model.addAttribute("title", "在庫状態更新");
			model.addAttribute("stockstatus_id",stockstatus_id);
			return "masterData/stockstatusInsert";
			
		}else {
			Stockstatus stockstatus = dataRefill.makeStockstatus(stockstatusForm, stockstatus_id);
			masterDataService.stockstatusUpdate(stockstatus);
			redirectAttributes.addFlashAttribute("complete","在庫状態が1件更新されました");
			return "redirect:/masterData/stockstatus";
		}
		
	}
	
	@PostMapping("/stockstatus/delete")
	public String stockstatusDelete(@RequestParam("stockstatus_id") int stockstatus_id,
			RedirectAttributes redirectAttributes) {
		masterDataService.stockstatusDelete(stockstatus_id);
		redirectAttributes.addFlashAttribute("complete","在庫状態が1件削除されました");
		return "redirect:/masterData/stockstatus";
	}
	
}


