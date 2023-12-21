package com.example.demo.repository.entryEnterprise;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.component.changeDataType.ChangeDataType;
import com.example.demo.entity.BusinessType;
import com.example.demo.entity.Enterprise;
import com.example.demo.exception.HandlingException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EntryEnterpriseDaoImpl implements EntryEnterpriseDao {
	
	private final JdbcTemplate jdbcTemplate;
	private final ChangeDataType changeDataType;
	private int check;
	
	
	@Override
	public List<Enterprise> entryEnterpriseList() {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM entryenterprise INNER JOIN businesstype ");
		buf.append(" ON entryenterprise.businesstype_id = businesstype.businesstype_id ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Enterprise> enterpriseList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			
			BusinessType businessType = new BusinessType();
			businessType.setBusinesstype((String)result.get("businesstype"));
			
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprise_id((int)result.get("enterprise_id"));
			enterprise.setEnterprisename((String)result.get("enterprisename"));
			enterprise.setPostalcode((String)result.get("postalcode"));
			enterprise.setAddress((String)result.get("address"));
			
			Date dataFoundnationdate = ((Date)result.get("foundnationdate"));
			enterprise.setFoundnationdate(changeDataType.dateToString(dataFoundnationdate));
			
			enterprise.setChiefname((String)result.get("chiefname"));
			enterprise.setCapital((String)result.get("capital"));
			enterprise.setCorporatenumber((String)result.get("corporatenumber"));
			if(result.get("employees") != null) {
				enterprise.setEmployees((int)result.get("employees"));
			}
			enterprise.setBusinesstype_id((int)result.get("businesstype_id"));
			enterprise.setBusinesstype(businessType);
			enterprise.setBusinessdetail((String)result.get("businessdetail"));
			enterprise.setEnterprisenumber((String)result.get("enterprisenumber"));
			enterprise.setEnterpriseemail((String)result.get("enterpriseemail"));
			enterprise.setHomepage((String)result.get("homepage"));
			
			enterpriseList.add(enterprise);
			
		}
		
		return enterpriseList;
	}
	
	
	@Override
	public List<Enterprise> filteredEntryEnterpriseList(int businesstype_id) {
		
		StringBuffer buf = new StringBuffer();
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM entryenterprise INNER JOIN businesstype ");
		buf.append(" ON entryenterprise.businesstype_id = businesstype.businesstype_id ");
		
		if(businesstype_id != 0) {
			buf.append(" WHERE ");
			buf.append(" entryenterprise.businesstype_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), businesstype_id);
		}else {
			resultList = jdbcTemplate.queryForList(buf.toString());
		}
		
		List<Enterprise> enterpriseList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			
			BusinessType businessType = new BusinessType();
			businessType.setBusinesstype((String)result.get("businesstype"));
			
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprise_id((int)result.get("enterprise_id"));
			enterprise.setEnterprisename((String)result.get("enterprisename"));
			enterprise.setPostalcode((String)result.get("postalcode"));
			enterprise.setAddress((String)result.get("address"));
			
			Date dataFoundnationdate = ((Date)result.get("foundnationdate"));
			enterprise.setFoundnationdate(changeDataType.dateToString(dataFoundnationdate));
			
			enterprise.setChiefname((String)result.get("chiefname"));
			enterprise.setCapital((String)result.get("capital"));
			enterprise.setCorporatenumber((String)result.get("corporatenumber"));
			if(result.get("employees") != null) {
				enterprise.setEmployees((int)result.get("employees"));
			}
			enterprise.setBusinesstype_id((int)result.get("businesstype_id"));
			enterprise.setBusinesstype(businessType);
			enterprise.setBusinessdetail((String)result.get("businessdetail"));
			enterprise.setEnterprisenumber((String)result.get("enterprisenumber"));
			enterprise.setEnterpriseemail((String)result.get("enterpriseemail"));
			enterprise.setHomepage((String)result.get("homepage"));
			
			enterpriseList.add(enterprise);
			
		}
		
		return enterpriseList;
	}
	
	@Override
	public List<BusinessType> businessTypeList(){
		
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
	public Optional<Enterprise> entryEnterpriseDetail(int enterprise_id) {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM entryenterprise INNER JOIN businesstype ");
		buf.append(" ON entryenterprise.businesstype_id = businesstype.businesstype_id ");
		buf.append(" WHERE entryenterprise.enterprise_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), enterprise_id);
		
		BusinessType businessType = new BusinessType();
		businessType.setBusinesstype((String)result.get("businesstype"));
		
		Enterprise enterprise = new Enterprise();
		enterprise.setEnterprise_id((int)result.get("enterprise_id"));
		enterprise.setEnterprisename((String)result.get("enterprisename"));
		enterprise.setPostalcode((String)result.get("postalcode"));
		enterprise.setAddress((String)result.get("address"));
		
		Date dataFoundnationdate = ((Date)result.get("foundnationdate"));
		enterprise.setFoundnationdate(changeDataType.dateToString(dataFoundnationdate));
		
		enterprise.setChiefname((String)result.get("chiefname"));
		enterprise.setCapital((String)result.get("capital"));
		enterprise.setCorporatenumber((String)result.get("corporatenumber"));
		if(result.get("employees") != null) {
			enterprise.setEmployees((int)result.get("employees"));
		}
		enterprise.setBusinesstype_id((int)result.get("businesstype_id"));
		enterprise.setBusinesstype(businessType);
		enterprise.setBusinessdetail((String)result.get("businessdetail"));
		enterprise.setEnterprisenumber((String)result.get("enterprisenumber"));
		enterprise.setEnterpriseemail((String)result.get("enterpriseemail"));
		enterprise.setHomepage((String)result.get("homepage"));
		
		Optional<Enterprise> optEnterprise = Optional.ofNullable(enterprise);
		
		return optEnterprise;
	}
	
	@Override
	public int enterpriseInsert(Enterprise enterprise) {
		
		try {
			StringBuffer buf = new StringBuffer();
			buf.append(" INSERT INTO entryenterprise(enterprisename, postalcode, address, foundnationdate, chiefname, capital, corporatenumber, ");
			buf.append(" businesstype_id, businessdetail, enterprisenumber, enterpriseemail, homepage, employees) ");
			buf.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			
			check = jdbcTemplate.update(buf.toString(), enterprise.getEnterprisename(), enterprise.getPostalcode(), enterprise.getAddress(), enterprise.getFoundnationdate(), 
					enterprise.getChiefname(), enterprise.getCapital(), enterprise.getCorporatenumber(), enterprise.getBusinesstype_id(), 
					enterprise.getBusinessdetail(), enterprise.getEnterprisenumber(), enterprise.getEnterpriseemail(),
					enterprise.getHomepage(), enterprise.getEmployees());
			
			return check;
		}catch(DataIntegrityViolationException e) {
			throw new HandlingException("参加企業データの追加処理が失敗しました");
		}
	}
	
	
	@Override
	public int enterpriseUpdate(Enterprise enterprise) {
		
		try {
			StringBuffer buf = new StringBuffer();
			buf.append(" UPDATE entryenterprise SET enterprisename = ?, postalcode = ?, address = ?, ");
			buf.append(" foundnationdate = ?, chiefname = ?, capital = ?, corporatenumber = ?, ");
			buf.append(" businesstype_id = ?, businessdetail = ?, enterprisenumber = ?, enterpriseemail = ?, homepage = ?, employees= ? ");
			buf.append(" WHERE enterprise_id = ? ");
			
			check = jdbcTemplate.update(buf.toString(), enterprise.getEnterprisename(), enterprise.getPostalcode(), enterprise.getAddress(), enterprise.getFoundnationdate(), 
					enterprise.getChiefname(), enterprise.getCapital(), enterprise.getCorporatenumber(), enterprise.getBusinesstype_id(), 
					enterprise.getBusinessdetail(), enterprise.getEnterprisenumber(), enterprise.getEnterpriseemail(),
					enterprise.getHomepage(), enterprise.getEmployees(), enterprise.getEnterprise_id());
			
			return check;
		}catch(DataIntegrityViolationException e) {
			throw new HandlingException("参加企業データの更新処理が失敗しました");
		}
		
	}


	@Override
	public int enterpriseDelete(int enterprise_id) {
		StringBuffer buf1 = new StringBuffer();
		buf1.append(" SELECT * FROM stock ");
		buf1.append(" WHERE enterprise_id = ? ");
		List<Map<String, Object>> resultList1 = jdbcTemplate.queryForList(buf1.toString(), enterprise_id);
		
		StringBuffer buf2 = new StringBuffer();
		buf2.append(" SELECT * FROM entrytrading ");
		buf2.append(" WHERE purchaseenterprise_id = ? ");
		List<Map<String, Object>> resultList2 = jdbcTemplate.queryForList(buf2.toString(), enterprise_id);
		
		StringBuffer buf3 = new StringBuffer();
		buf3.append(" SELECT * FROM purchasetrading ");
		buf3.append(" WHERE purchaseenterprise_id = ? ");
		List<Map<String, Object>> resultList3 = jdbcTemplate.queryForList(buf3.toString(), enterprise_id);
		
		StringBuffer buf4 = new StringBuffer();
		buf4.append(" SELECT * FROM salestrading ");
		buf4.append(" WHERE salesenterprise_id = ? ");
		List<Map<String, Object>> resultList4 = jdbcTemplate.queryForList(buf4.toString(), enterprise_id);
		
		StringBuffer buf5 = new StringBuffer();
		buf5.append(" SELECT * FROM user ");
		buf5.append(" WHERE enterprise_id = ? ");
		List<Map<String, Object>> resultList5 = jdbcTemplate.queryForList(buf5.toString(), enterprise_id);
		
		StringBuffer buf6 = new StringBuffer();
		buf6.append(" SELECT * FROM workrecord ");
		buf6.append(" WHERE enterprise_id = ? ");
		List<Map<String, Object>> resultList6 = jdbcTemplate.queryForList(buf6.toString(), enterprise_id);
		
		StringBuffer buf7 = new StringBuffer();
		buf7.append(" SELECT * FROM entrytrading ");
		buf7.append(" WHERE salesenterprise_id = ? ");
		List<Map<String, Object>> resultList7 = jdbcTemplate.queryForList(buf7.toString(), enterprise_id);
		
		StringBuffer buf8 = new StringBuffer();
		buf8.append(" SELECT * FROM product ");
		buf8.append(" WHERE enterprise_id = ? ");
		List<Map<String, Object>> resultList8 = jdbcTemplate.queryForList(buf8.toString(), enterprise_id);
		
		if(resultList1.size() == 0 && resultList2.size() == 0 && resultList3.size() == 0 && resultList4.size() == 0 &&
				resultList5.size() == 0 && resultList6.size() == 0  && resultList7.size() == 0 && resultList8.size() == 0) {
			StringBuffer buf9 = new StringBuffer();
			buf9.append(" DELETE FROM entryenterprise ");
			buf9.append(" WHERE enterprise_id = ? ");
			
			check = jdbcTemplate.update(buf9.toString(), enterprise_id);
			
			return check;
		}else {
			throw new HandlingException("削除しようとしているレコードは別レコードに参照されているため削除できません");
		}
	}
	

}
