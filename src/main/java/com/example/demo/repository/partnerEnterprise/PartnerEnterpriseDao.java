package com.example.demo.repository.partnerEnterprise;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.BusinessType;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Partnerenterpriserank;

public interface PartnerEnterpriseDao {
	
	List<Enterprise> partnerEnterpriseList(); 
	
	List<Enterprise> filteredPartnerEnterpriseList(int businesstype_id, int partnerenterpriserank_id, int mainenterprise_id); 
	
	List<BusinessType> businessTypeList();
	
	List<Partnerenterpriserank> partnerEnterpriseRankList();
	
	List<Enterprise> mainEnterpriseList();
	
	Optional<Enterprise> partnerEnterpriseDetail(int enterprise_id); 
	
	int partnerEnterpriseInsert(Enterprise enterprise);
	
	int partnerEnterpriseUpdate(Enterprise enterprise);
	
	int partnerEnterpriseDelete(int enterprise_id);

}
