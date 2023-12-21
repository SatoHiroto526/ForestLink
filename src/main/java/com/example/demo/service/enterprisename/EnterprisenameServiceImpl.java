package com.example.demo.service.enterprisename;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Enterprise;
import com.example.demo.exception.HandlingException;
import com.example.demo.repository.entryEnterprise.EntryEnterpriseDao;
import com.example.demo.repository.partnerEnterprise.PartnerEnterpriseDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnterprisenameServiceImpl implements EnterprisenameService {
	
	private final EntryEnterpriseDao entryEnterpriseDao;
	private final PartnerEnterpriseDao partnerEnterpriseDao;

	@Override
	public String getEntryenterprisename(int entryenterprise_id) {
		try {
			
			Enterprise enterprise = new Enterprise();
			Optional<Enterprise> optEntryEnterprise = entryEnterpriseDao.entryEnterpriseDetail(entryenterprise_id); 
			
			if(optEntryEnterprise.isPresent()) {
				enterprise = optEntryEnterprise.get();
			}
			
			String enterpriseName = enterprise.getEnterprisename();
			
			return enterpriseName;
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("エラーが発生しました");
		}
	}

	@Override
	public String getPartnerenterprisename(int partnerenterprise_id) {
		try {
			
			Enterprise enterprise = new Enterprise();
			Optional<Enterprise> optPartnerEnterprise = partnerEnterpriseDao.partnerEnterpriseDetail(partnerenterprise_id); 
			
			if(optPartnerEnterprise.isPresent()) {
				enterprise = optPartnerEnterprise.get();
			}
			
			String enterpriseName = enterprise.getEnterprisename();
			
			return enterpriseName;
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("エラーが発生しました");
		}
	}

}
