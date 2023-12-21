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
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Product;
import com.example.demo.entity.Producttype;
import com.example.demo.form.product.ProductFilter;
import com.example.demo.form.product.ProductForm;
import com.example.demo.service.enterprisename.EnterprisenameService;
import com.example.demo.service.product.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	private final EnterprisenameService enterprisenameService;
	private final DataRefill dataRefill;
	
	@GetMapping("/product")
	public String product(ProductFilter productFilter,
			Model model,
			@ModelAttribute("complete") String complete) {
		model.addAttribute("productFilter", productFilter);
		List<Product> productList = productService.productList();
		model.addAttribute("productList", productList);
		model.addAttribute("productCount",productList.size() + "件のレコード");
		List<Enterprise> entryenterpriseList = productService.enterpriseList();
		model.addAttribute("entryenterpriseList", entryenterpriseList);
		List<Producttype> producttypeList = productService.producttypeList();
		model.addAttribute("producttypeList", producttypeList);
		List<Breed> breedList = productService.breedList();
		model.addAttribute("breedList", breedList);
		model.addAttribute("complete", complete);
		return "product/productAll";
	}
	
	@GetMapping("/filteredProduct")
	public String filteredProduct(ProductFilter productFilter,
			Model model) {
		int entryenterprise_id = productFilter.getEnterprise_id();
		int producttype_id = productFilter.getProducttype_id();
		int breed_id = productFilter.getBreed_id();
		List<Product> productList = productService.filteredProductList(entryenterprise_id, producttype_id, breed_id);
		model.addAttribute("productList",productList);
		model.addAttribute("productCount",productList.size() + "件のレコード");
		model.addAttribute("productFilter", productFilter);
		List<Enterprise> entryenterpriseList = productService.enterpriseList();
		model.addAttribute("entryenterpriseList", entryenterpriseList);
		List<Producttype> producttypeList = productService.producttypeList();
		model.addAttribute("producttypeList", producttypeList);
		List<Breed> breedList = productService.breedList();
		model.addAttribute("breedList", breedList);
		return "product/productAll";
	}
	
	@GetMapping("/product/detail/{product_id}")
	public String userDetail(@PathVariable int product_id,
			Model model) {
		
		Product productDetail = productService.productDetail(product_id);
		model.addAttribute("productDetail", productDetail);
		model.addAttribute("title","製品詳細");
		return "product/productDetail";
	}
	
	@GetMapping("/product/entryEnterprise/{enterprise_id}")
	public String enterpriseUser(@PathVariable int enterprise_id,
			Model model) {
		List<Product> productList = productService.filteredProductList(enterprise_id, 0, 0);
		String enterprisename = enterprisenameService.getEntryenterprisename(enterprise_id);
		model.addAttribute("productList", productList);
		model.addAttribute("title", enterprisename + "の取扱製品一覧");
		model.addAttribute("userCount",productList.size() + "件のレコード");
		return "product/enterpriseProduct";
	}
	
	@GetMapping("/product/insert")
	public String productInsert(ProductForm productForm,
			Model model) {
		productForm.setNewProduct(true);
		model.addAttribute("title", "新規製品登録");
		model.addAttribute("productForm",productForm);
		List<Enterprise> entryenterpriseList = productService.enterpriseList();
		model.addAttribute("entryenterpriseList", entryenterpriseList);
		List<Producttype> producttypeList = productService.producttypeList();
		model.addAttribute("producttypeList", producttypeList);
		List<Breed> breedList = productService.breedList();
		model.addAttribute("breedList", breedList);
		model.addAttribute("sawingmethodList", productService.sawingmethodList());
		model.addAttribute("materialtypeList", productService.materialtypeList());
		model.addAttribute("jasList", productService.jasList());
		return "product/productInsert";
	}
	
	@PostMapping("/product/insert")
	public String executeProductInsert(@Validated ProductForm productForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			productForm.setNewProduct(true);
			model.addAttribute("title", "新規製品登録");
			model.addAttribute("productForm",productForm);
			List<Enterprise> entryenterpriseList = productService.enterpriseList();
			model.addAttribute("entryenterpriseList", entryenterpriseList);
			List<Producttype> producttypeList = productService.producttypeList();
			model.addAttribute("producttypeList", producttypeList);
			List<Breed> breedList = productService.breedList();
			model.addAttribute("breedList", breedList);
			model.addAttribute("sawingmethodList", productService.sawingmethodList());
			model.addAttribute("materialtypeList", productService.materialtypeList());
			model.addAttribute("jasList", productService.jasList());
			return "product/productInsert";
			
		}else {
			Product product = dataRefill.makeProduct(productForm, 0);
			productService.productInsert(product);
			redirectAttributes.addFlashAttribute("complete","新規製品が1件追加されました");
			return "redirect:/product";
		}
	}
	
	@GetMapping("/product/update/{product_id}")
	public String productUpdate(ProductForm productForm,
			@PathVariable int product_id,
			Model model) {
		productForm.setNewProduct(false);
		model.addAttribute("title", "製品更新");
		Product product = productService.productDetail(product_id);
		productForm = dataRefill.makeProductForm(product);
		model.addAttribute("productForm",productForm);
		List<Enterprise> entryenterpriseList = productService.enterpriseList();
		model.addAttribute("entryenterpriseList", entryenterpriseList);
		List<Producttype> producttypeList = productService.producttypeList();
		model.addAttribute("producttypeList", producttypeList);
		List<Breed> breedList = productService.breedList();
		model.addAttribute("breedList", breedList);
		model.addAttribute("sawingmethodList", productService.sawingmethodList());
		model.addAttribute("materialtypeList", productService.materialtypeList());
		model.addAttribute("jasList", productService.jasList());
		return "product/productInsert";
	}
	
	@PostMapping("/product/update")
	public String executeProductUpdate(@Validated ProductForm productForm,
			BindingResult result,
			@RequestParam("product_id") int product_id,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			
			productForm.setNewProduct(false);
			model.addAttribute("title", "製品更新");
			List<Enterprise> entryenterpriseList = productService.enterpriseList();
			model.addAttribute("entryenterpriseList", entryenterpriseList);
			List<Producttype> producttypeList = productService.producttypeList();
			model.addAttribute("producttypeList", producttypeList);
			List<Breed> breedList = productService.breedList();
			model.addAttribute("breedList", breedList);
			model.addAttribute("sawingmethodList", productService.sawingmethodList());
			model.addAttribute("materialtypeList", productService.materialtypeList());
			model.addAttribute("jasList", productService.jasList());
			return "product/productInsert";
			
		}else {
			Product product = dataRefill.makeProduct(productForm, product_id);
			productService.productUpdate(product);
			redirectAttributes.addFlashAttribute("complete","製品が1件更新されました");
			return "redirect:/product";
		}
		
	}
	
	@PostMapping("/product/delete")
	public String productDelete(@RequestParam("product_id") int product_id,
			RedirectAttributes redirectAttributes) {
		productService.productDelete(product_id);
		redirectAttributes.addFlashAttribute("complete","製品が1件削除されました");
		return "redirect:/product";
	}

}
