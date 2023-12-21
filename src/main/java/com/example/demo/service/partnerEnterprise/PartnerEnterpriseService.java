package com.example.demo.service.partnerEnterprise;

import java.util.List;

import com.example.demo.entity.BusinessType;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Partnerenterpriserank;

public interface PartnerEnterpriseService {
	
	List<Enterprise> partnerEnterpriseList(); 
	
	List<Enterprise> filteredPartnerEnterpriseList(int businesstype_id, int partnerenterpriserank, int mainenterprise_id); 
	
	List<BusinessType> businessTypeList();
	
	List<Partnerenterpriserank> partnerEnterpriseRankList();
	
	List<Enterprise> mainEnterpriseList();
	
	Enterprise partnerEnterpriseDetail(int enterprise_id); 
	
	void partnerEnterpriseInsert(Enterprise enterprise);
	
	void partnerEnterpriseUpdate(Enterprise enterprise);
	
	void partnerEnterpriseDelete(int enterprise_id);

}
