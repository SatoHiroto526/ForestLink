package com.example.demo.service.masterData;

import java.util.List;

import com.example.demo.entity.Breed;
import com.example.demo.entity.BusinessType;
import com.example.demo.entity.Contactmethod;
import com.example.demo.entity.Partnerenterpriserank;
import com.example.demo.entity.Producttype;
import com.example.demo.entity.Stockstatus;
import com.example.demo.entity.Worktype;

public interface MasterDataService {
	
	List<BusinessType> businessTypeList();
	
	void businessTypeInsert(BusinessType businessType);
	
	void businessTypeUpdate(BusinessType businessType);
	
	BusinessType businessTypeDetail(int businesstype_id);
	
	List<Partnerenterpriserank> partnerenterpriserankList();
	
	void partnerenterpriserankInsert(Partnerenterpriserank partnerenterpriserank);
	
	void partnerenterpriserankUpdate(Partnerenterpriserank partnerenterpriserank);
	
	Partnerenterpriserank partnerenterpriserankDetail(int partnerenterpriserank_id); 
	
	List<Worktype> worktypeList();
	
	void worktypeInsert(Worktype worktype);
	
	void worktypeUpdate(Worktype worktype);
	
	Worktype worktypeDetail(int worktype_id); 
	
	List<Contactmethod> contactmethodList();
	
	void contactmethodInsert(Contactmethod contactmethod);
	
	void contactmethodUpdate(Contactmethod contactmethod);
	
	Contactmethod contactmethodDetail(int contactmethod_id); 
	
	void businesstypeDelete(int businesstype_id);
	
	void partnerenterpriserankDelete(int partnerenterpriserank_id);
	
	void worktypeDelete(int worktype_id);
	
	void contactmethodDelete(int contactmethod_id);
	
	List<Producttype> producttypeList();
	
	List<Breed> breedList();
	
	Producttype producttypeDetail(int producttype_id); 
	
	Breed breedDetail(int breed_id); 
	
	void producttypeInsert(Producttype producttype);
	
	void breedInsert(Breed breed);
	
	void producttypeUpdate(Producttype producttype);
	
	void breedUpdate(Breed breed);
	
	void producttypeDelete(int producttype_id);
	
	void breedDelete(int breed_id);
	
	List<Stockstatus> stockstatusList();
	
	Stockstatus stockstatusDetail(int stockstatus_id);
	
	void stockstatusInsert(Stockstatus stockstatus);
	
	void stockstatusUpdate(Stockstatus stockstatus);
	
	void stockstatusDelete(int stockstatus_id);

}
