package com.example.demo.service.entryEnterprise;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BusinessType;
import com.example.demo.entity.Enterprise;
import com.example.demo.exception.HandlingException;
import com.example.demo.repository.entryEnterprise.EntryEnterpriseDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntryEnterpriseServiceImpl implements EntryEnterpriseService {
	
	private final EntryEnterpriseDao entryEnterpriseDao;
	
	@Override
	public List<Enterprise> entryEnterpriseList() {
		return entryEnterpriseDao.entryEnterpriseList();
	}
	
	@Override
	public List<Enterprise> filteredEntryEnterpriseList(int businesstype_Id){
		return entryEnterpriseDao.filteredEntryEnterpriseList(businesstype_Id);
	}
	
	@Override
	public List<BusinessType> businessTypeList(){
		return entryEnterpriseDao.businessTypeList();
	}

	@Override
	public Enterprise entryEnterpriseDetail(int enterprise_id) {
		
		try {
			
			Enterprise enterprise = new Enterprise();
			Optional<Enterprise> optEntryEnterprise = entryEnterpriseDao.entryEnterpriseDetail(enterprise_id); 
			
			if(optEntryEnterprise.isPresent()) {
				enterprise = optEntryEnterprise.get();
			}
			
			return enterprise;
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("お探しの企業が見つかりません");
		}
		
	}
	
	
	@Override
	public void enterpriseInsert(Enterprise enterprise) {
		if(entryEnterpriseDao.enterpriseInsert(enterprise) == 0) {
			throw new HandlingException("参加企業データの追加処理が失敗しました");
		}	
	}
	
	@Override
	public void enterpriseUpdate(Enterprise enterprise) {
		if(entryEnterpriseDao.enterpriseUpdate(enterprise) == 0) {
			throw new HandlingException("参加企業データの更新処理が失敗しました");
		}	
	}

	@Override
	public void enterpriseDelete(int enterprise_id) {
		if(entryEnterpriseDao.enterpriseDelete(enterprise_id) == 0) {
			throw new HandlingException("参加企業データの削除処理が失敗しました");
		}
	}
	


}
