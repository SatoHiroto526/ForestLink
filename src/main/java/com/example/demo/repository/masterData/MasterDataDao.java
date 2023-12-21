package com.example.demo.repository.masterData;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Breed;
import com.example.demo.entity.BusinessType;
import com.example.demo.entity.Contactmethod;
import com.example.demo.entity.Partnerenterpriserank;
import com.example.demo.entity.Producttype;
import com.example.demo.entity.Stockstatus;
import com.example.demo.entity.Worktype;

public interface MasterDataDao {
	
	List<BusinessType> businessTypeList();
	
	int businessTypeInsert(BusinessType businessType);
	
	int businessTypeUpdate(BusinessType businessType);
	
	Optional<BusinessType> businesstypeDetail(int businesstype_Id); 
	
	List<Partnerenterpriserank> partnerenterpriserankList();
	
	int partnerenterpriserankInsert(Partnerenterpriserank partnerenterpriserank);
	
	int partnerenterpriserankUpdate(Partnerenterpriserank partnerenterpriserank);
	
	Optional<Partnerenterpriserank> partnerenterpriserankDetail(int partnerenterpriserank_id); 
	
	List<Worktype> worktypeList();
	
	int worktypeInsert(Worktype worktype);
	
	int worktypeUpdate(Worktype worktype);
	
	Optional<Worktype> worktypeDetail(int worktype_id); 
	
	List<Contactmethod> contactmethodList();
	
	int contactmethodInsert(Contactmethod contactmethod);
	
	int contactmethodUpdate(Contactmethod contactmethod);
	
	Optional<Contactmethod> contactmethodDetail(int contactmethod_id); 
	
	int businesstypeDelete(int businesstype_id);
	
	int partnerenterpriserankDelete(int partnerenterpriserank_id);
	
	int worktypeDelete(int worktype_id);
	
	int contactmethodDelete(int contactmethod_id);
	
	List<Producttype> producttypeList();
	
	List<Breed> breedList();
	
	Optional<Producttype> producttypeDetail(int producttype_id); 
	
	Optional<Breed> breedDetail(int breed_id); 
	
	int producttypeInsert(Producttype producttype);
	
	int breedInsert(Breed breed);
	
	int producttypeUpdate(Producttype producttype);
	
	int breedUpdate(Breed breed);
	
	int producttypeDelete(int producttype_id);
	
	int breedDelete(int breed_id);
	
	List<Stockstatus> stockstatusList();
	
	Optional<Stockstatus> stockstatusDetail(int stockstatus_id);
	
	int stockstatusInsert(Stockstatus stockstatus);
	
	int stockstatusUpdate(Stockstatus stockstatus);
	
	int stockstatusDelete(int stockstatus_id);

}
