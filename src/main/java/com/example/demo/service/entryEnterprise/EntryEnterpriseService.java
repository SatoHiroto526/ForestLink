package com.example.demo.service.entryEnterprise;

import java.util.List;

import com.example.demo.entity.BusinessType;
import com.example.demo.entity.Enterprise;

public interface EntryEnterpriseService {
	
	List<Enterprise> entryEnterpriseList(); 
	
	List<Enterprise> filteredEntryEnterpriseList(int businesstype_Id); 
	
	List<BusinessType> businessTypeList();
	
	Enterprise entryEnterpriseDetail(int enterprise_id);
	
	void enterpriseInsert(Enterprise enterprise);
	
	void enterpriseUpdate(Enterprise enterprise);
	
	void enterpriseDelete(int enterprise_id);
	

}
