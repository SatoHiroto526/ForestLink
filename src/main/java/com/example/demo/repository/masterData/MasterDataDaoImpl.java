package com.example.demo.repository.masterData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Breed;
import com.example.demo.entity.BusinessType;
import com.example.demo.entity.Contactmethod;
import com.example.demo.entity.Partnerenterpriserank;
import com.example.demo.entity.Producttype;
import com.example.demo.entity.Stockstatus;
import com.example.demo.entity.Worktype;
import com.example.demo.exception.HandlingException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MasterDataDaoImpl implements MasterDataDao {
	
	private final JdbcTemplate jdbcTemplate;
	int check;

	@Override
	public List<BusinessType> businessTypeList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM businesstype ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<BusinessType> businessTypeList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			BusinessType businessType = new BusinessType();
			businessType.setBusinesstype_id((int)result.get("businesstype_id"));
			businessType.setBusinesstype((String)result.get("businesstype"));
			businessTypeList.add(businessType);
		}
		return businessTypeList;
	}
	
	
	@Override
	public int businessTypeInsert(BusinessType businessType) {
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO businesstype(businesstype) ");
		buf.append(" VALUES(?) ");
		
		check = jdbcTemplate.update(buf.toString(), businessType.getBusinesstype());
		
		return check;
	}
	
	@Override
	public int businessTypeUpdate(BusinessType businessType) {
		StringBuffer buf = new StringBuffer();
		buf.append(" UPDATE businesstype SET businesstype = ? ");
		buf.append(" WHERE businesstype_id = ? ");
		
		check = jdbcTemplate.update(buf.toString(), businessType.getBusinesstype(), businessType.getBusinesstype_id());
		
		return check;
	}


	@Override
	public Optional<BusinessType> businesstypeDetail(int businesstype_Id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM businesstype ");
		buf.append(" WHERE businesstype_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), businesstype_Id);
		
		BusinessType businessType = new BusinessType();
		businessType.setBusinesstype_id((int)result.get("businesstype_id"));
		businessType.setBusinesstype((String)result.get("businesstype"));
		
		Optional<BusinessType> optBusinesType = Optional.ofNullable(businessType);
		
		return optBusinesType;
	}


	@Override
	public List<Partnerenterpriserank> partnerenterpriserankList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM partnerenterpriserank ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Partnerenterpriserank> partnerenterpriserankList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Partnerenterpriserank partnerenterpriserank = new Partnerenterpriserank();
			partnerenterpriserank.setPartnerenterpriserank_id((int)result.get("partnerenterpriserank_id"));
			partnerenterpriserank.setPartnerenterpriserankdetail((String)result.get("partnerenterpriserankdetail"));
			partnerenterpriserankList.add(partnerenterpriserank);
		}
		return partnerenterpriserankList;
	}


	@Override
	public int partnerenterpriserankInsert(Partnerenterpriserank partnerenterpriserank) {
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO partnerenterpriserank(partnerenterpriserankdetail) ");
		buf.append(" VALUES(?) ");
		
		check = jdbcTemplate.update(buf.toString(), partnerenterpriserank.getPartnerenterpriserankdetail());
		
		return check;
	}


	@Override
	public int partnerenterpriserankUpdate(Partnerenterpriserank partnerenterpriserank) {
		StringBuffer buf = new StringBuffer();
		buf.append(" UPDATE partnerenterpriserank SET partnerenterpriserankdetail = ? ");
		buf.append(" WHERE partnerenterpriserank_id = ? ");
		
		check = jdbcTemplate.update(buf.toString(),partnerenterpriserank.getPartnerenterpriserankdetail(), 
				partnerenterpriserank.getPartnerenterpriserank_id());
		
		return check;
	}


	@Override
	public Optional<Partnerenterpriserank> partnerenterpriserankDetail(int partnerenterpriserank_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM partnerenterpriserank ");
		buf.append(" WHERE partnerenterpriserank_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), partnerenterpriserank_id);
		
		Partnerenterpriserank partnerenterpriserank = new Partnerenterpriserank();
		partnerenterpriserank.setPartnerenterpriserank_id((int)result.get("partnerenterpriserank_id"));
		partnerenterpriserank.setPartnerenterpriserankdetail((String)result.get("partnerenterpriserankdetail"));
		
		Optional<Partnerenterpriserank> optPartnerenterpriserank = Optional.ofNullable(partnerenterpriserank);
		
		return optPartnerenterpriserank;
	}


	@Override
	public List<Worktype> worktypeList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM worktype ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Worktype> worktypeList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Worktype worktype = new Worktype();
			worktype.setWorktype_id((int)result.get("worktype_id"));
			worktype.setWorktype((String)result.get("worktype"));
			worktypeList.add(worktype);
		}
		return worktypeList;
	}


	@Override
	public int worktypeInsert(Worktype worktype) {
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO worktype(worktype) ");
		buf.append(" VALUES(?) ");
		
		check = jdbcTemplate.update(buf.toString(), worktype.getWorktype());
		
		return check;
	}


	@Override
	public int worktypeUpdate(Worktype worktype) {
		StringBuffer buf = new StringBuffer();
		buf.append(" UPDATE worktype SET worktype = ? ");
		buf.append(" WHERE worktype_id = ? ");
		
		check = jdbcTemplate.update(buf.toString(), worktype.getWorktype(), worktype.getWorktype_id());
		
		return check;
	}


	@Override
	public Optional<Worktype> worktypeDetail(int worktype_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM worktype ");
		buf.append(" WHERE worktype_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), worktype_id);
		
		Worktype worktype = new Worktype();
		worktype.setWorktype_id((int)result.get("worktype_id"));
		worktype.setWorktype((String)result.get("worktype"));
		
		Optional<Worktype> optWorktype = Optional.ofNullable(worktype);
		
		return optWorktype;
	}


	@Override
	public List<Contactmethod> contactmethodList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM contactmethod ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Contactmethod> contactmethodList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Contactmethod contactmethod = new Contactmethod();
			contactmethod.setContactmethod_id((int)result.get("contactmethod_id"));
			contactmethod.setContactmethod((String)result.get("contactmethod"));
			contactmethodList.add(contactmethod);
		}
		return contactmethodList;
	}


	@Override
	public int contactmethodInsert(Contactmethod contactmethod) {
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO contactmethod(contactmethod) ");
		buf.append(" VALUES(?) ");
		
		check = jdbcTemplate.update(buf.toString(), contactmethod.getContactmethod());
		
		return check;
	}


	@Override
	public int contactmethodUpdate(Contactmethod contactmethod) {
		StringBuffer buf = new StringBuffer();
		buf.append(" UPDATE contactmethod SET contactmethod = ? ");
		buf.append(" WHERE contactmethod_id = ? ");
		
		check = jdbcTemplate.update(buf.toString(), contactmethod.getContactmethod(), contactmethod.getContactmethod_id());
		
		return check;
	}


	@Override
	public Optional<Contactmethod> contactmethodDetail(int contactmethod_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM contactmethod ");
		buf.append(" WHERE contactmethod_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), contactmethod_id);
		
		Contactmethod contactmethod = new Contactmethod();
		contactmethod.setContactmethod_id((int)result.get("contactmethod_id"));
		contactmethod.setContactmethod((String)result.get("contactmethod"));
		
		Optional<Contactmethod> optContactmethod = Optional.ofNullable(contactmethod);
		
		return optContactmethod;
	}


	@Override
	public int businesstypeDelete(int businesstype_id) {
		
		StringBuffer buf1 = new StringBuffer();
		buf1.append(" SELECT * FROM entryenterprise ");
		buf1.append(" WHERE businesstype_id = ? ");
		List<Map<String, Object>> resultList1 = jdbcTemplate.queryForList(buf1.toString(), businesstype_id);
		
		StringBuffer buf2 = new StringBuffer();
		buf2.append(" SELECT * FROM partnerenterprise ");
		buf2.append(" WHERE partnerbusinesstype_id = ? ");
		List<Map<String, Object>> resultList2 = jdbcTemplate.queryForList(buf2.toString(), businesstype_id);
		
		if(resultList1.size() == 0 && resultList2.size() == 0) {
			StringBuffer buf3 = new StringBuffer();
			buf3.append(" DELETE FROM businesstype ");
			buf3.append(" WHERE businesstype_id = ? ");
			
			check = jdbcTemplate.update(buf3.toString(), businesstype_id);
			
			return check;
		}else {
			throw new HandlingException("削除しようとしているレコードは別レコードに参照されているため削除できません");
		}
		
	}


	@Override
	public int partnerenterpriserankDelete(int partnerenterpriserank_id) {
		StringBuffer buf1 = new StringBuffer();
		buf1.append(" SELECT * FROM partnerenterprise ");
		buf1.append(" WHERE partnerenterpriserank_id = ? ");
		List<Map<String, Object>> resultList1 = jdbcTemplate.queryForList(buf1.toString(), partnerenterpriserank_id);
		
		if(resultList1.size() == 0) {
			StringBuffer buf2 = new StringBuffer();
			buf2.append(" DELETE FROM partnerenterpriserank ");
			buf2.append(" WHERE partnerenterpriserank_id = ? ");
			
			check = jdbcTemplate.update(buf2.toString(), partnerenterpriserank_id);
			
			return check;
		}else {
			throw new HandlingException("削除しようとしているレコードは別レコードに参照されているため削除できません");
		}
	}


	@Override
	public int worktypeDelete(int worktype_id) {
		StringBuffer buf1 = new StringBuffer();
		buf1.append(" SELECT * FROM workrecord ");
		buf1.append(" WHERE worktype_id = ? ");
		List<Map<String, Object>> resultList1 = jdbcTemplate.queryForList(buf1.toString(), worktype_id);
		
		if(resultList1.size() == 0) {
			StringBuffer buf2 = new StringBuffer();
			buf2.append(" DELETE FROM worktype ");
			buf2.append(" WHERE worktype_id = ? ");
			
			check = jdbcTemplate.update(buf2.toString(), worktype_id);
			
			return check;
		}else {
			throw new HandlingException("削除しようとしているレコードは別レコードに参照されているため削除できません");
		}
	}


	@Override
	public int contactmethodDelete(int contactmethod_id) {
		StringBuffer buf1 = new StringBuffer();
		buf1.append(" SELECT * FROM workrecord ");
		buf1.append(" WHERE contactmethod_id = ? ");
		List<Map<String, Object>> resultList1 = jdbcTemplate.queryForList(buf1.toString(), contactmethod_id);
		
		if(resultList1.size() == 0) {
			StringBuffer buf2 = new StringBuffer();
			buf2.append(" DELETE FROM contactmethod ");
			buf2.append(" WHERE contactmethod_id = ? ");
			
			check = jdbcTemplate.update(buf2.toString(), contactmethod_id);
			
			return check;
		}else {
			throw new HandlingException("削除しようとしているレコードは別レコードに参照されているため削除できません");
		}
	}


	@Override
	public List<Producttype> producttypeList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM producttype ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Producttype> producttypeList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Producttype producttype = new Producttype();
			producttype.setProducttype_id((int)result.get("producttype_id"));
			producttype.setProducttype((String)result.get("producttype"));
			producttypeList.add(producttype);
		}
		return producttypeList;
	}


	@Override
	public List<Breed> breedList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM breed ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Breed> breedList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Breed breed = new Breed();
			breed.setBreed_id((int)result.get("breed_id"));
			breed.setBreed((String)result.get("breed"));
			breedList.add(breed);
		}
		return breedList;
	}


	@Override
	public Optional<Producttype> producttypeDetail(int producttype_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM producttype ");
		buf.append(" WHERE producttype_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), producttype_id);
		
		Producttype producttype = new Producttype();
		producttype.setProducttype_id((int)result.get("producttype_id"));
		producttype.setProducttype((String)result.get("producttype"));
		
		Optional<Producttype> optProducttype = Optional.ofNullable(producttype);
		
		return optProducttype;
	}


	@Override
	public Optional<Breed> breedDetail(int breed_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM breed ");
		buf.append(" WHERE breed_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), breed_id);
		
		Breed breed = new Breed();
		breed.setBreed_id((int)result.get("breed_id"));
		breed.setBreed((String)result.get("breed"));
		
		Optional<Breed> optBreed = Optional.ofNullable(breed);
		
		return optBreed;
	}


	@Override
	public int producttypeInsert(Producttype producttype) {
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO producttype(producttype) ");
		buf.append(" VALUES(?) ");
		
		check = jdbcTemplate.update(buf.toString(), producttype.getProducttype());
		
		return check;
	}


	@Override
	public int breedInsert(Breed breed) {
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO breed(breed) ");
		buf.append(" VALUES(?) ");
		
		check = jdbcTemplate.update(buf.toString(), breed.getBreed());
		
		return check;
	}


	@Override
	public int producttypeUpdate(Producttype producttype) {
		StringBuffer buf = new StringBuffer();
		buf.append(" UPDATE producttype SET producttype = ? ");
		buf.append(" WHERE producttype_id = ? ");
		
		check = jdbcTemplate.update(buf.toString(), producttype.getProducttype(), producttype.getProducttype_id());
		
		return check;
	}


	@Override
	public int breedUpdate(Breed breed) {
		StringBuffer buf = new StringBuffer();
		buf.append(" UPDATE breed SET breed = ? ");
		buf.append(" WHERE breed_id = ? ");
		
		check = jdbcTemplate.update(buf.toString(), breed.getBreed(), breed.getBreed_id());
		
		return check;
	}


	@Override
	public int producttypeDelete(int producttype_id) {
		StringBuffer buf1 = new StringBuffer();
		buf1.append(" SELECT * FROM product ");
		buf1.append(" WHERE producttype_id = ? ");
		List<Map<String, Object>> resultList1 = jdbcTemplate.queryForList(buf1.toString(), producttype_id);
		
		if(resultList1.size() == 0) {
			StringBuffer buf2 = new StringBuffer();
			buf2.append(" DELETE FROM producttype ");
			buf2.append(" WHERE producttype_id = ? ");
			
			check = jdbcTemplate.update(buf2.toString(), producttype_id);
			
			return check;
		}else {
			throw new HandlingException("削除しようとしているレコードは別レコードに参照されているため削除できません");
		}
	}


	@Override
	public int breedDelete(int breed_id) {
		StringBuffer buf1 = new StringBuffer();
		buf1.append(" SELECT * FROM product ");
		buf1.append(" WHERE breed_id = ? ");
		List<Map<String, Object>> resultList1 = jdbcTemplate.queryForList(buf1.toString(), breed_id);
		
		if(resultList1.size() == 0) {
			StringBuffer buf2 = new StringBuffer();
			buf2.append(" DELETE FROM breed ");
			buf2.append(" WHERE breed_id = ? ");
			
			check = jdbcTemplate.update(buf2.toString(), breed_id);
			
			return check;
		}else {
			throw new HandlingException("削除しようとしているレコードは別レコードに参照されているため削除できません");
		}
	}


	@Override
	public List<Stockstatus> stockstatusList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM stockstatus ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Stockstatus> stockstatusList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Stockstatus stockstatus = new Stockstatus();
			stockstatus.setStockstatus_id((int)result.get("stockstatus_id"));
			stockstatus.setStockstatus((String)result.get("stockstatus"));
			stockstatusList.add(stockstatus);
		}
		return stockstatusList;
	}


	@Override
	public Optional<Stockstatus> stockstatusDetail(int stockstatus_id) {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM stockstatus ");
		buf.append(" WHERE stockstatus_id = ? ");
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), stockstatus_id);
		Stockstatus stockstatus = new Stockstatus();
		stockstatus.setStockstatus_id((int)result.get("stockstatus_id"));
		stockstatus.setStockstatus((String)result.get("stockstatus"));
		Optional<Stockstatus> optStockstatus = Optional.ofNullable(stockstatus);
		return optStockstatus;
	}


	@Override
	public int stockstatusInsert(Stockstatus stockstatus) {
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO stockstatus(stockstatus) ");
		buf.append(" VALUES(?) ");
		
		check = jdbcTemplate.update(buf.toString(), stockstatus.getStockstatus());
		
		return check;
	}


	@Override
	public int stockstatusUpdate(Stockstatus stockstatus) {
		StringBuffer buf = new StringBuffer();
		buf.append(" UPDATE stockstatus SET stockstatus = ? ");
		buf.append(" WHERE stockstatus_id = ? ");
		
		check = jdbcTemplate.update(buf.toString(), stockstatus.getStockstatus(), stockstatus.getStockstatus_id());
		
		return check;
	}


	@Override
	public int stockstatusDelete(int stockstatus_id) {
		StringBuffer buf1 = new StringBuffer();
		buf1.append(" SELECT * FROM stock ");
		buf1.append(" WHERE stockstatus_id = ? ");
		List<Map<String, Object>> resultList1 = jdbcTemplate.queryForList(buf1.toString(), stockstatus_id);
		
		if(resultList1.size() == 0) {
			StringBuffer buf2 = new StringBuffer();
			buf2.append(" DELETE FROM stockstatus ");
			buf2.append(" WHERE stockstatus_id = ? ");
			
			check = jdbcTemplate.update(buf2.toString(), stockstatus_id);
			
			return check;
		}else {
			throw new HandlingException("削除しようとしているレコードは別レコードに参照されているため削除できません");
		}
	}

}
