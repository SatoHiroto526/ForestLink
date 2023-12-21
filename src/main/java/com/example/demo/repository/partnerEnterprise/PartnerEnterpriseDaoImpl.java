package com.example.demo.repository.partnerEnterprise;

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
import com.example.demo.entity.Partnerenterpriserank;
import com.example.demo.exception.HandlingException;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class PartnerEnterpriseDaoImpl implements PartnerEnterpriseDao {
	
	private final JdbcTemplate jdbcTemplate;
	private final ChangeDataType changeDataType;
	private int check;

	@Override
	public List<Enterprise> partnerEnterpriseList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM partnerenterprise INNER JOIN businesstype ");
		buf.append(" ON partnerenterprise.partnerbusinesstype_id = businesstype.businesstype_id ");
		buf.append(" INNER JOIN partnerenterpriserank ");
		buf.append(" ON partnerenterprise.partnerenterpriserank_id = partnerenterpriserank.partnerenterpriserank_id ");
		buf.append(" INNER JOIN entryenterprise ");
		buf.append(" ON partnerenterprise.mainenterprise_id = entryenterprise.enterprise_id ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Enterprise> enterpriseList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			
			BusinessType businessType = new BusinessType();
			businessType.setBusinesstype((String)result.get("businesstype"));
			
			Partnerenterpriserank partnerenterpriserank = new Partnerenterpriserank();
			partnerenterpriserank.setPartnerenterpriserankdetail((String)result.get("partnerenterpriserankdetail"));
			
			Enterprise mainenterprise = new Enterprise();
			mainenterprise.setEnterprisename((String)result.get("enterprisename"));
			
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprise_id((int)result.get("partnerenterprise_id"));
			enterprise.setEnterprisename((String)result.get("partnerenterprisename"));
			enterprise.setPostalcode((String)result.get("partnerpostalcode"));
			enterprise.setAddress((String)result.get("partneraddress"));
			
			Date dataFoundnationdate = ((Date)result.get("partnerfoundnationdate"));
			enterprise.setFoundnationdate(changeDataType.dateToString(dataFoundnationdate));
			
			enterprise.setChiefname((String)result.get("partnerchiefname"));
			enterprise.setCapital((String)result.get("partnercapital"));
			enterprise.setCorporatenumber((String)result.get("partnercorporatenumber"));
			if(result.get("partneremployees") != null) {
				enterprise.setEmployees((int)result.get("partneremployees"));
			}
			enterprise.setBusinesstype_id((int)result.get("partnerbusinesstype_id"));
			enterprise.setBusinesstype(businessType);
			enterprise.setBusinessdetail((String)result.get("partnerbusinessdetail"));
			enterprise.setEnterprisenumber((String)result.get("partnerenterprisenumber"));
			enterprise.setEnterpriseemail((String)result.get("partnerenterpriseemail"));
			enterprise.setHomepage((String)result.get("partnerhomepage"));
			enterprise.setPartnerenterpriserank_id((int)result.get("partnerenterpriserank_id"));
			enterprise.setMainenterprise_id((int)result.get("mainenterprise_id"));
			enterprise.setPartnerenterpriserank(partnerenterpriserank);
			enterprise.setMainenterprise(mainenterprise);
			
			enterpriseList.add(enterprise);
			
		}
		
		return enterpriseList;
	}

	@Override
	public List<Enterprise> filteredPartnerEnterpriseList(int businesstype_id, int partnerenterpriserank_id,
			int mainenterprise_id) {
		
		StringBuffer buf = new StringBuffer();
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM partnerenterprise INNER JOIN businesstype ");
		buf.append(" ON partnerenterprise.partnerbusinesstype_id = businesstype.businesstype_id ");
		buf.append(" INNER JOIN partnerenterpriserank ");
		buf.append(" ON partnerenterprise.partnerenterpriserank_id = partnerenterpriserank.partnerenterpriserank_id ");
		buf.append(" INNER JOIN entryenterprise ");
		buf.append(" ON partnerenterprise.mainenterprise_id = entryenterprise.enterprise_id ");
		
		if(businesstype_id != 0 && partnerenterpriserank_id != 0 && mainenterprise_id != 0) {
			buf.append(" WHERE partnerenterprise.partnerbusinesstype_id = ? AND  ");
			buf.append(" partnerenterprise.partnerenterpriserank_id = ? AND  ");
			buf.append(" partnerenterprise.mainenterprise_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), businesstype_id, partnerenterpriserank_id, mainenterprise_id);
		}else if(businesstype_id != 0 && partnerenterpriserank_id != 0 && mainenterprise_id == 0) {
			buf.append(" WHERE partnerenterprise.partnerbusinesstype_id = ? AND  ");
			buf.append(" partnerenterprise.partnerenterpriserank_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), businesstype_id, partnerenterpriserank_id);
		}else if(businesstype_id != 0 && partnerenterpriserank_id == 0 && mainenterprise_id != 0) {
			buf.append(" WHERE partnerenterprise.partnerbusinesstype_id = ? AND  ");
			buf.append(" partnerenterprise.mainenterprise_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), businesstype_id, mainenterprise_id);
		}else if(businesstype_id == 0 && partnerenterpriserank_id != 0 && mainenterprise_id != 0) {
			buf.append(" WHERE partnerenterprise.partnerenterpriserank_id = ? AND  ");
			buf.append(" partnerenterprise.mainenterprise_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), partnerenterpriserank_id, mainenterprise_id);
		}else if(businesstype_id == 0 && partnerenterpriserank_id == 0 && mainenterprise_id != 0) {
			buf.append(" WHERE partnerenterprise.mainenterprise_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), mainenterprise_id);
		}else if(businesstype_id == 0 && partnerenterpriserank_id != 0 && mainenterprise_id == 0) {
			buf.append(" WHERE partnerenterprise.partnerenterpriserank_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), partnerenterpriserank_id);
		}else if(businesstype_id != 0 && partnerenterpriserank_id == 0 && mainenterprise_id == 0) {
			buf.append(" WHERE partnerenterprise.partnerbusinesstype_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), businesstype_id);
		}else {
			resultList = jdbcTemplate.queryForList(buf.toString());
		}
		
		List<Enterprise> enterpriseList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			
			BusinessType businessType = new BusinessType();
			businessType.setBusinesstype((String)result.get("businesstype"));
			
			Partnerenterpriserank partnerenterpriserank = new Partnerenterpriserank();
			partnerenterpriserank.setPartnerenterpriserankdetail((String)result.get("partnerenterpriserankdetail"));
			
			Enterprise mainenterprise = new Enterprise();
			mainenterprise.setEnterprisename((String)result.get("enterprisename"));
			
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprise_id((int)result.get("partnerenterprise_id"));
			enterprise.setEnterprisename((String)result.get("partnerenterprisename"));
			enterprise.setPostalcode((String)result.get("partnerpostalcode"));
			enterprise.setAddress((String)result.get("partneraddress"));
			
			Date dataFoundnationdate = ((Date)result.get("partnerfoundnationdate"));
			enterprise.setFoundnationdate(changeDataType.dateToString(dataFoundnationdate));
			
			enterprise.setChiefname((String)result.get("partnerchiefname"));
			enterprise.setCapital((String)result.get("partnercapital"));
			enterprise.setCorporatenumber((String)result.get("partnercorporatenumber"));
			if(result.get("partneremployees") != null) {
				enterprise.setEmployees((int)result.get("partneremployees"));
			}
			enterprise.setBusinesstype_id((int)result.get("partnerbusinesstype_id"));
			enterprise.setBusinesstype(businessType);
			enterprise.setBusinessdetail((String)result.get("partnerbusinessdetail"));
			enterprise.setEnterprisenumber((String)result.get("partnerenterprisenumber"));
			enterprise.setEnterpriseemail((String)result.get("partnerenterpriseemail"));
			enterprise.setHomepage((String)result.get("partnerhomepage"));
			enterprise.setPartnerenterpriserank_id((int)result.get("partnerenterpriserank_id"));
			enterprise.setMainenterprise_id((int)result.get("mainenterprise_id"));
			enterprise.setPartnerenterpriserank(partnerenterpriserank);
			enterprise.setMainenterprise(mainenterprise);
			
			enterpriseList.add(enterprise);
			
		}
		
		return enterpriseList;
		
	}

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
	public List<Partnerenterpriserank> partnerEnterpriseRankList() {
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
	public List<Enterprise> mainEnterpriseList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM entryenterprise ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Enterprise> enterpriseList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprise_id((int)result.get("enterprise_id"));
			enterprise.setEnterprisename((String)result.get("enterprisename"));
			enterpriseList.add(enterprise);
		}
		return enterpriseList;
	}

	@Override
	public Optional<Enterprise> partnerEnterpriseDetail(int enterprise_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM partnerenterprise INNER JOIN businesstype ");
		buf.append(" ON partnerenterprise.partnerbusinesstype_id = businesstype.businesstype_id ");
		buf.append(" INNER JOIN partnerenterpriserank ");
		buf.append(" ON partnerenterprise.partnerenterpriserank_id = partnerenterpriserank.partnerenterpriserank_id ");
		buf.append(" INNER JOIN entryenterprise ");
		buf.append(" ON partnerenterprise.mainenterprise_id = entryenterprise.enterprise_id ");
		buf.append(" WHERE partnerenterprise.partnerenterprise_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), enterprise_id);
			
		BusinessType businessType = new BusinessType();
		businessType.setBusinesstype((String)result.get("businesstype"));

		Partnerenterpriserank partnerenterpriserank = new Partnerenterpriserank();
		partnerenterpriserank.setPartnerenterpriserankdetail((String)result.get("partnerenterpriserankdetail"));

		Enterprise mainenterprise = new Enterprise();
		mainenterprise.setEnterprisename((String)result.get("enterprisename"));

		Enterprise enterprise = new Enterprise();
		enterprise.setEnterprise_id((int)result.get("partnerenterprise_id"));
		enterprise.setEnterprisename((String)result.get("partnerenterprisename"));
		enterprise.setPostalcode((String)result.get("partnerpostalcode"));
		enterprise.setAddress((String)result.get("partneraddress"));

		Date dataFoundnationdate = ((Date)result.get("partnerfoundnationdate"));
		enterprise.setFoundnationdate(changeDataType.dateToString(dataFoundnationdate));

		enterprise.setChiefname((String)result.get("partnerchiefname"));
		enterprise.setCapital((String)result.get("partnercapital"));
		enterprise.setCorporatenumber((String)result.get("partnercorporatenumber"));
		if(result.get("partneremployees") != null) {
			enterprise.setEmployees((int)result.get("partneremployees"));
		}
		enterprise.setBusinesstype_id((int)result.get("partnerbusinesstype_id"));
		enterprise.setBusinesstype(businessType);
		enterprise.setBusinessdetail((String)result.get("partnerbusinessdetail"));
		enterprise.setEnterprisenumber((String)result.get("partnerenterprisenumber"));
		enterprise.setEnterpriseemail((String)result.get("partnerenterpriseemail"));
		enterprise.setHomepage((String)result.get("partnerhomepage"));
		enterprise.setPartnerenterpriserank_id((int)result.get("partnerenterpriserank_id"));
		enterprise.setMainenterprise_id((int)result.get("mainenterprise_id"));
		enterprise.setPartnerenterpriserank(partnerenterpriserank);
		enterprise.setMainenterprise(mainenterprise);

		Optional<Enterprise> optPartnerEnterprise = Optional.ofNullable(enterprise);
		
		return optPartnerEnterprise;
	}

	@Override
	public int partnerEnterpriseInsert(Enterprise enterprise) {
		
		try {
			StringBuffer buf = new StringBuffer();
			buf.append(" INSERT INTO partnerenterprise(partnerenterprisename, partnerpostalcode, partneraddress, partnerfoundnationdate, partnerchiefname, partnercapital, partnercorporatenumber, ");
			buf.append(" partnerbusinesstype_id, partnerbusinessdetail, partnerenterprisenumber, partnerenterpriseemail, partnerhomepage, partnerenterpriserank_id, partneremployees, mainenterprise_id) ");
			buf.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			
			check = jdbcTemplate.update(buf.toString(), enterprise.getEnterprisename(), enterprise.getPostalcode(), enterprise.getAddress(), enterprise.getFoundnationdate(), 
					enterprise.getChiefname(), enterprise.getCapital(), enterprise.getCorporatenumber(), enterprise.getBusinesstype_id(), 
					enterprise.getBusinessdetail(), enterprise.getEnterprisenumber(), enterprise.getEnterpriseemail(),
					enterprise.getHomepage(), enterprise.getPartnerenterpriserank_id() , enterprise.getEmployees(), enterprise.getMainenterprise_id());
			
			return check;
		}catch(DataIntegrityViolationException e) {
			throw new HandlingException("外部取引先企業データの追加処理が失敗しました");
		}
		
	}

	@Override
	public int partnerEnterpriseUpdate(Enterprise enterprise) {
		
		try {
			StringBuffer buf = new StringBuffer();
			buf.append(" UPDATE partnerenterprise SET partnerenterprisename = ?, partnerpostalcode = ?, partneraddress = ?, partnerfoundnationdate = ?, partnerchiefname = ?, partnercapital = ?, partnercorporatenumber = ?, ");
			buf.append(" partnerbusinesstype_id = ?, partnerbusinessdetail = ?, partnerenterprisenumber = ?, partnerenterpriseemail = ?, partnerhomepage = ?, partnerenterpriserank_id = ? , partneremployees = ?, mainenterprise_id = ? ");
			buf.append(" WHERE partnerenterprise_id = ? ");
			
			check = jdbcTemplate.update(buf.toString(), enterprise.getEnterprisename(), enterprise.getPostalcode(), enterprise.getAddress(), enterprise.getFoundnationdate(), 
					enterprise.getChiefname(), enterprise.getCapital(), enterprise.getCorporatenumber(), enterprise.getBusinesstype_id(), 
					enterprise.getBusinessdetail(), enterprise.getEnterprisenumber(), enterprise.getEnterpriseemail(),
					enterprise.getHomepage(), enterprise.getPartnerenterpriserank_id() , enterprise.getEmployees(), enterprise.getMainenterprise_id(), enterprise.getEnterprise_id());
			
			return check;
		}catch(DataIntegrityViolationException e) {
			throw new HandlingException("外部取引先企業データの更新処理が失敗しました");
		}
	}

	@Override
	public int partnerEnterpriseDelete(int enterprise_id) {
		StringBuffer buf1 = new StringBuffer();
		buf1.append(" SELECT * FROM purchasetrading ");
		buf1.append(" WHERE salesenterprise_id = ? ");
		List<Map<String, Object>> resultList1 = jdbcTemplate.queryForList(buf1.toString(), enterprise_id);
		
		StringBuffer buf2 = new StringBuffer();
		buf2.append(" SELECT * FROM salestrading ");
		buf2.append(" WHERE purchaseenterprise_id = ? ");
		List<Map<String, Object>> resultList2 = jdbcTemplate.queryForList(buf2.toString(), enterprise_id);
		
		StringBuffer buf3 = new StringBuffer();
		buf3.append(" SELECT * FROM partneruser ");
		buf3.append(" WHERE partnerenterprise_id = ? ");
		List<Map<String, Object>> resultList3 = jdbcTemplate.queryForList(buf3.toString(), enterprise_id);
		
		StringBuffer buf4 = new StringBuffer();
		buf4.append(" SELECT * FROM workrecord ");
		buf4.append(" WHERE partnerenterprise_id = ? ");
		List<Map<String, Object>> resultList4 = jdbcTemplate.queryForList(buf4.toString(), enterprise_id);

		
		if(resultList1.size() == 0 && resultList2.size() == 0 && resultList3.size() == 0 && resultList4.size() == 0) {
			StringBuffer buf5 = new StringBuffer();
			buf5.append(" DELETE FROM partnerenterprise ");
			buf5.append(" WHERE partnerenterprise_id = ? ");
			
			check = jdbcTemplate.update(buf5.toString(), enterprise_id);
			
			return check;
		}else {
			throw new HandlingException("削除しようとしているレコードは別レコードに参照されているため削除できません");
		}
	}

}
