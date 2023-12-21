package com.example.demo.repository.entryEnterprise;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.BusinessType;
import com.example.demo.entity.Enterprise;

public interface EntryEnterpriseDao {
	
	List<Enterprise> entryEnterpriseList(); 
	
	List<Enterprise> filteredEntryEnterpriseList(int businesstype_id); 
	
	List<BusinessType> businessTypeList();
	
	Optional<Enterprise> entryEnterpriseDetail(int enterprise_id); 
	
	int enterpriseInsert(Enterprise enterprise);
	
	int enterpriseUpdate(Enterprise enterprise);
	
	int enterpriseDelete(int enterprise_id);

}
