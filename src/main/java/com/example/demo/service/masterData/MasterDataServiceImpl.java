package com.example.demo.service.masterData;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Breed;
import com.example.demo.entity.BusinessType;
import com.example.demo.entity.Contactmethod;
import com.example.demo.entity.Partnerenterpriserank;
import com.example.demo.entity.Producttype;
import com.example.demo.entity.Stockstatus;
import com.example.demo.entity.Worktype;
import com.example.demo.exception.HandlingException;
import com.example.demo.repository.masterData.MasterDataDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MasterDataServiceImpl implements MasterDataService {
	
	private final MasterDataDao masterDataDao;

	@Override
	public List<BusinessType> businessTypeList() {
		return masterDataDao.businessTypeList();
	}
	
	@Override
	public void businessTypeInsert(BusinessType businessType) {
		if(masterDataDao.businessTypeInsert(businessType) == 0) {
			throw new HandlingException("事業区分の追加処理が失敗しました");
		}	
	}

	@Override
	public void businessTypeUpdate(BusinessType businessType) {
		if(masterDataDao.businessTypeUpdate(businessType) == 0) {
			throw new HandlingException("事業区分の更新処理が失敗しました");
		}	
	}

	@Override
	public BusinessType businessTypeDetail(int businesstype_id) {
		try {
			
			BusinessType businesstype = new BusinessType();
			Optional<BusinessType> optBusinessType = masterDataDao.businesstypeDetail(businesstype_id);
			
			if(optBusinessType.isPresent()) {
				businesstype = optBusinessType.get();
			}
			
			return businesstype; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("事業区分が見つかりません");
		}
	}

	@Override
	public List<Partnerenterpriserank> partnerenterpriserankList() {
		return masterDataDao.partnerenterpriserankList();
	}

	@Override
	public void partnerenterpriserankInsert(Partnerenterpriserank partnerenterpriserank) {
		if(masterDataDao.partnerenterpriserankInsert(partnerenterpriserank) == 0) {
			throw new HandlingException("取引先ランクの追加処理が失敗しました");
		}
	}

	@Override
	public void partnerenterpriserankUpdate(Partnerenterpriserank partnerenterpriserank) {
		if(masterDataDao.partnerenterpriserankUpdate(partnerenterpriserank) == 0) {
			throw new HandlingException("取引先ランクの更新処理が失敗しました");
		}
	}

	@Override
	public Partnerenterpriserank partnerenterpriserankDetail(int partnerenterpriserank_id) {
		try {
			
			Partnerenterpriserank partnerenterpriserank = new Partnerenterpriserank();
			Optional<Partnerenterpriserank> optPartnerenterpriserank = masterDataDao.partnerenterpriserankDetail(partnerenterpriserank_id);
			
			if(optPartnerenterpriserank.isPresent()) {
				partnerenterpriserank = optPartnerenterpriserank.get();
			}
			
			return partnerenterpriserank; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("取引先ランクが見つかりません");
		}
	}

	@Override
	public List<Worktype> worktypeList() {
		return masterDataDao.worktypeList();
	}

	@Override
	public void worktypeInsert(Worktype worktype) {
		if(masterDataDao.worktypeInsert(worktype) == 0) {
			throw new HandlingException("活動種別の追加処理が失敗しました");
		}
	}

	@Override
	public void worktypeUpdate(Worktype worktype) {
		if(masterDataDao.worktypeUpdate(worktype) == 0) {
			throw new HandlingException("活動種別の更新処理が失敗しました");
		}
	}

	@Override
	public Worktype worktypeDetail(int worktype_id) {
		try {
			
			Worktype worktype = new Worktype();
			Optional<Worktype> optWorktype = masterDataDao.worktypeDetail(worktype_id);
			
			if(optWorktype.isPresent()) {
				worktype = optWorktype.get();
			}
			
			return worktype; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("活動種別が見つかりません");
		}
	}

	@Override
	public List<Contactmethod> contactmethodList() {
		return masterDataDao.contactmethodList();
	}

	@Override
	public void contactmethodInsert(Contactmethod contactmethod) {
		if(masterDataDao.contactmethodInsert(contactmethod) == 0) {
			throw new HandlingException("連絡手段の追加処理が失敗しました");
		}
	}

	@Override
	public void contactmethodUpdate(Contactmethod contactmethod) {
		if(masterDataDao.contactmethodUpdate(contactmethod) == 0) {
			throw new HandlingException("連絡手段の更新処理が失敗しました");
		}
	}

	@Override
	public Contactmethod contactmethodDetail(int contactmethod_id) {
		try {
			
			Contactmethod contactmethod = new Contactmethod();
			Optional<Contactmethod> optContactmethod = masterDataDao.contactmethodDetail(contactmethod_id);
			
			if(optContactmethod.isPresent()) {
				contactmethod = optContactmethod.get();
			}
			
			return contactmethod; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("連絡手段が見つかりません");
		}
	}

	@Override
	public void businesstypeDelete(int businesstype_id) {
		if(masterDataDao.businesstypeDelete(businesstype_id) == 0) {
			throw new HandlingException("事業区分の削除処理が失敗しました");
		}
	}

	@Override
	public void partnerenterpriserankDelete(int partnerenterpriserank_id) {
		if(masterDataDao.partnerenterpriserankDelete(partnerenterpriserank_id) == 0) {
			throw new HandlingException("取引先ランクの削除処理が失敗しました");
		}
	}

	@Override
	public void worktypeDelete(int worktype_id) {
		if(masterDataDao.worktypeDelete(worktype_id) == 0) {
			throw new HandlingException("活動種別の削除処理が失敗しました");
		}
	}

	@Override
	public void contactmethodDelete(int contactmethod_id) {
		if(masterDataDao.worktypeDelete(contactmethod_id) == 0) {
			throw new HandlingException("連絡手段の削除処理が失敗しました");
		}
	}

	@Override
	public List<Producttype> producttypeList() {
		return masterDataDao.producttypeList();
	}

	@Override
	public List<Breed> breedList() {
		return masterDataDao.breedList();
	}

	@Override
	public Producttype producttypeDetail(int producttype_id) {
		try {
			
			Producttype producttype = new Producttype();
			Optional<Producttype> optProducttype = masterDataDao.producttypeDetail(producttype_id);
			
			if(optProducttype.isPresent()) {
				producttype = optProducttype.get();
			}
			
			return producttype; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("製品種別が見つかりません");
		}
	}

	@Override
	public Breed breedDetail(int breed_id) {
		try {
			
			Breed breed = new Breed();
			Optional<Breed> optBreed = masterDataDao.breedDetail(breed_id);
			
			if(optBreed.isPresent()) {
				breed = optBreed.get();
			}
			
			return breed; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("品種が見つかりません");
		}
	}

	@Override
	public void producttypeInsert(Producttype producttype) {
		if(masterDataDao.producttypeInsert(producttype) == 0) {
			throw new HandlingException("製品種別の追加処理が失敗しました");
		}
	}

	@Override
	public void breedInsert(Breed breed) {
		if(masterDataDao.breedInsert(breed) == 0) {
			throw new HandlingException("品種の追加処理が失敗しました");
		}
	}

	@Override
	public void producttypeUpdate(Producttype producttype) {
		if(masterDataDao.producttypeUpdate(producttype) == 0) {
			throw new HandlingException("製品種別の更新処理が失敗しました");
		}
	}

	@Override
	public void breedUpdate(Breed breed) {
		if(masterDataDao.breedUpdate(breed) == 0) {
			throw new HandlingException("品種の更新処理が失敗しました");
		}
	}

	@Override
	public void producttypeDelete(int producttype_id) {
		if(masterDataDao.producttypeDelete(producttype_id) == 0) {
			throw new HandlingException("製品種別の削除処理が失敗しました");
		}
	}

	@Override
	public void breedDelete(int breed_id) {
		if(masterDataDao.breedDelete(breed_id) == 0) {
			throw new HandlingException("品種の削除処理が失敗しました");
		}
	}

	@Override
	public List<Stockstatus> stockstatusList() {
		return masterDataDao.stockstatusList();
	}

	@Override
	public Stockstatus stockstatusDetail(int stockstatus_id) {
		try {
			
			Stockstatus stockstatus = new Stockstatus();
			Optional<Stockstatus> optStockstatus = masterDataDao.stockstatusDetail(stockstatus_id);
			
			if(optStockstatus.isPresent()) {
				stockstatus = optStockstatus.get();
			}
			
			return stockstatus; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("在庫状態が見つかりません");
		}
	}

	@Override
	public void stockstatusInsert(Stockstatus stockstatus) {
		if(masterDataDao.stockstatusInsert(stockstatus) == 0) {
			throw new HandlingException("在庫状態の追加処理が失敗しました");
		}
	}

	@Override
	public void stockstatusUpdate(Stockstatus stockstatus) {
		if(masterDataDao.stockstatusUpdate(stockstatus) == 0) {
			throw new HandlingException("在庫状態の更新処理が失敗しました");
		}
	}

	@Override
	public void stockstatusDelete(int stockstatus_id) {
		if(masterDataDao.stockstatusDelete(stockstatus_id) == 0) {
			throw new HandlingException("在庫状態の削除処理が失敗しました");
		}
	}

}
