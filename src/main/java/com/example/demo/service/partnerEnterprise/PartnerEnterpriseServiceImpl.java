package com.example.demo.service.partnerEnterprise;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BusinessType;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Partnerenterpriserank;
import com.example.demo.exception.HandlingException;
import com.example.demo.repository.partnerEnterprise.PartnerEnterpriseDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartnerEnterpriseServiceImpl implements PartnerEnterpriseService {
	
	private final PartnerEnterpriseDao partnerEnterpriseDao;

	@Override
	public List<Enterprise> partnerEnterpriseList() {
		return partnerEnterpriseDao.partnerEnterpriseList();
	}

	@Override
	public List<Enterprise> filteredPartnerEnterpriseList(int businesstype_id, int partnerenterpriserank,
			int mainenterprise_id) {
		return partnerEnterpriseDao.filteredPartnerEnterpriseList(businesstype_id, partnerenterpriserank, mainenterprise_id);
	}

	@Override
	public List<BusinessType> businessTypeList() {
		return partnerEnterpriseDao.businessTypeList();
	}

	@Override
	public List<Partnerenterpriserank> partnerEnterpriseRankList() {
		return partnerEnterpriseDao.partnerEnterpriseRankList();
	}

	@Override
	public List<Enterprise> mainEnterpriseList() {
		return partnerEnterpriseDao.mainEnterpriseList();
	}

	@Override
	public Enterprise partnerEnterpriseDetail(int enterprise_id) {
		try {
			
			Enterprise enterprise = new Enterprise();
			Optional<Enterprise> optPartnerEnterprise = partnerEnterpriseDao.partnerEnterpriseDetail(enterprise_id); 
			
			if(optPartnerEnterprise.isPresent()) {
				enterprise = optPartnerEnterprise.get();
			}
			
			return enterprise;
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("お探しの企業が見つかりません");
		}
	}

	@Override
	public void partnerEnterpriseInsert(Enterprise enterprise) {
		if(partnerEnterpriseDao.partnerEnterpriseInsert(enterprise) == 0) {
			throw new HandlingException("外部取引先企業データの追加処理が失敗しました");
		}	
	}

	@Override
	public void partnerEnterpriseUpdate(Enterprise enterprise) {
		if(partnerEnterpriseDao.partnerEnterpriseUpdate(enterprise) == 0) {
			throw new HandlingException("外部取引先企業データの更新処理が失敗しました");
		}	
	}

	@Override
	public void partnerEnterpriseDelete(int enterprise_id) {
		if(partnerEnterpriseDao.partnerEnterpriseDelete(enterprise_id) == 0) {
			throw new HandlingException("外部取引先企業データの削除処理が失敗しました");
		}	
	}

}
