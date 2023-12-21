package com.example.demo.repository.workrecord;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.component.changeDataType.ChangeDataType;
import com.example.demo.entity.Contactmethod;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.User;
import com.example.demo.entity.Workrecord;
import com.example.demo.entity.Worktype;
import com.example.demo.exception.HandlingException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WorkrecordDaoImpl implements WorkrecordDao {
	
	private final JdbcTemplate jdbcTemplate;
	private final ChangeDataType changeDataType;
	int check;

	@Override
	public List<Workrecord> workrecordAll(int partnerenterprise_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM workrecord INNER JOIN worktype ");
		buf.append(" ON workrecord.worktype_id = worktype.worktype_id ");
		buf.append(" INNER JOIN entryenterprise ");
		buf.append(" ON workrecord.enterprise_id = entryenterprise.enterprise_id ");
		buf.append(" INNER JOIN partnerenterprise ");
		buf.append(" ON workrecord.partnerenterprise_id = partnerenterprise.partnerenterprise_id ");
		buf.append(" INNER JOIN user ");
		buf.append(" ON workrecord.user_id = user.user_id ");
		buf.append(" INNER JOIN partneruser ");
		buf.append(" ON workrecord.partneruser_id = partneruser.partneruser_id ");
		buf.append(" INNER JOIN contactmethod ");
		buf.append(" ON workrecord.contactmethod_id = contactmethod.contactmethod_id ");
		buf.append(" WHERE workrecord.partnerenterprise_id = ? ");
		buf.append(" ORDER BY workrecordday DESC ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString(), partnerenterprise_id);
		
		List<Workrecord> workrecordList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			Worktype worktype = new Worktype();
			worktype.setWorktype((String)result.get("worktype"));
			
			Enterprise entryEnterprise = new Enterprise();
			entryEnterprise.setEnterprisename((String)result.get("enterprisename"));
			
			Enterprise partnerEnterprsie = new Enterprise();
			partnerEnterprsie.setEnterprisename((String)result.get("partnerenterprisename"));
			
			User user = new User();
			user.setUsername((String)result.get("username"));
			
			User partnerUser = new User();
			partnerUser.setUsername((String)result.get("partnerusername"));
			
			Contactmethod contactmethod = new Contactmethod();
			contactmethod.setContactmethod((String)result.get("contactmethod"));
			
			Workrecord workrecord = new Workrecord();
			workrecord.setWorktype(worktype);
			workrecord.setEntryEnterprise(entryEnterprise);
			workrecord.setPartnerEnterprise(partnerEnterprsie);
			workrecord.setUser(user);
			workrecord.setPartnerUser(partnerUser);
			workrecord.setContactmethod(contactmethod);
			
			workrecord.setWorkrecord_id((int)result.get("workrecord_id"));
			workrecord.setWorktype_id((int)result.get("worktype_id"));
			workrecord.setSalesworktype((String)result.get("salesworktype"));
			workrecord.setEnterprise_id((int)result.get("enterprise_id"));
			workrecord.setPartnerenterprise_id((int)result.get("partnerenterprise_id"));
			workrecord.setUser_id((int)result.get("user_id"));
			workrecord.setPartneruser_id((int)result.get("partneruser_id"));
			workrecord.setContactmethod_id((int)result.get("contactmethod_id"));
			workrecord.setWorkrecorddetail((String)result.get("workrecorddetail"));
			Date dataWorkrecordday = ((Date)result.get("workrecordday"));
			workrecord.setWorkrecordday(changeDataType.dateToString(dataWorkrecordday));
			
			workrecordList.add(workrecord);
			
		}
		return workrecordList;
	}

	@Override
	public Optional<Workrecord> workrecordDetail(int workrecord_id) {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM workrecord INNER JOIN worktype ");
		buf.append(" ON workrecord.worktype_id = worktype.worktype_id ");
		buf.append(" INNER JOIN entryenterprise ");
		buf.append(" ON workrecord.enterprise_id = entryenterprise.enterprise_id ");
		buf.append(" INNER JOIN partnerenterprise ");
		buf.append(" ON workrecord.partnerenterprise_id = partnerenterprise.partnerenterprise_id ");
		buf.append(" INNER JOIN user ");
		buf.append(" ON workrecord.user_id = user.user_id ");
		buf.append(" INNER JOIN partneruser ");
		buf.append(" ON workrecord.partneruser_id = partneruser.partneruser_id ");
		buf.append(" INNER JOIN contactmethod ");
		buf.append(" ON workrecord.contactmethod_id = contactmethod.contactmethod_id ");
		buf.append(" WHERE workrecord.workrecord_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), workrecord_id);
		
		Worktype worktype = new Worktype();
		worktype.setWorktype((String)result.get("worktype"));
		
		Enterprise entryEnterprise = new Enterprise();
		entryEnterprise.setEnterprisename((String)result.get("enterprisename"));
		
		Enterprise partnerEnterprsie = new Enterprise();
		partnerEnterprsie.setEnterprisename((String)result.get("partnerenterprisename"));
		
		User user = new User();
		user.setUsername((String)result.get("username"));
		
		User partnerUser = new User();
		partnerUser.setUsername((String)result.get("partnerusername"));
		
		Contactmethod contactmethod = new Contactmethod();
		contactmethod.setContactmethod((String)result.get("contactmethod"));
		
		Workrecord workrecord = new Workrecord();
		workrecord.setWorktype(worktype);
		workrecord.setEntryEnterprise(entryEnterprise);
		workrecord.setPartnerEnterprise(partnerEnterprsie);
		workrecord.setUser(user);
		workrecord.setPartnerUser(partnerUser);
		workrecord.setContactmethod(contactmethod);
		
		workrecord.setWorkrecord_id((int)result.get("workrecord_id"));
		workrecord.setWorktype_id((int)result.get("worktype_id"));
		workrecord.setSalesworktype((String)result.get("salesworktype"));
		workrecord.setEnterprise_id((int)result.get("enterprise_id"));
		workrecord.setPartnerenterprise_id((int)result.get("partnerenterprise_id"));
		workrecord.setUser_id((int)result.get("user_id"));
		workrecord.setPartneruser_id((int)result.get("partneruser_id"));
		workrecord.setContactmethod_id((int)result.get("contactmethod_id"));
		workrecord.setWorkrecorddetail((String)result.get("workrecorddetail"));
		Date dataWorkrecordday = ((Date)result.get("workrecordday"));
		workrecord.setWorkrecordday(changeDataType.dateToString(dataWorkrecordday));
		
		Optional<Workrecord> optWorkrecord = Optional.ofNullable(workrecord);
		
		return optWorkrecord;
	}

	@Override
	public int workrecordInsert(Workrecord workrecord) {
		
		try {
			StringBuffer buf = new StringBuffer();
			buf.append(" INSERT INTO workrecord( ");
			buf.append(" worktype_id, salesworktype, enterprise_id, partnerenterprise_id, user_id, partneruser_id, ");
			buf.append(" contactmethod_id, workrecorddetail, workrecordday) ");
			buf.append(" VALUES(?,?,?,?,?,?,?,?,?) ");
			
			check = jdbcTemplate.update(buf.toString(), workrecord.getWorktype_id(), workrecord.getSalesworktype(), workrecord.getEnterprise_id(),
					workrecord.getPartnerenterprise_id(), workrecord.getUser_id(), workrecord.getPartneruser_id(),
					workrecord.getContactmethod_id(), workrecord.getWorkrecorddetail(), workrecord.getWorkrecordday());
			return check;
		}catch(DataIntegrityViolationException e) {
			throw new HandlingException("活動記録データの追加処理が失敗しました");
		}
		
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
	public List<Enterprise> entryenterpriseList() {
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
	public List<User> userList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM user ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<User> userList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			
			User user = new User();
			user.setUser_id((int)result.get("user_id"));
			user.setUsername((String)result.get("username"));;
			
			userList.add(user);
			
		}
		return userList;
	}

	@Override
	public List<User> partneruserList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM partneruser ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<User> userList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			User user = new User();
			user.setUser_id((int)result.get("partneruser_id"));
			user.setUsername((String)result.get("partnerusername"));
			
			userList.add(user);
			
		}
		return userList;
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
	public int workrecordUpdate(Workrecord workrecord) {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append(" UPDATE workrecord SET ");
			buf.append(" worktype_id = ?, salesworktype = ?, enterprise_id = ?, partnerenterprise_id = ?, user_id = ?, partneruser_id = ?, ");
			buf.append(" contactmethod_id = ?, workrecorddetail = ?, workrecordday = ? ");
			buf.append(" WHERE workrecord_id = ? ");
			
			check = jdbcTemplate.update(buf.toString(), workrecord.getWorktype_id(), workrecord.getSalesworktype(), workrecord.getEnterprise_id(),
					workrecord.getPartnerenterprise_id(), workrecord.getUser_id(), workrecord.getPartneruser_id(),
					workrecord.getContactmethod_id(), workrecord.getWorkrecorddetail(), workrecord.getWorkrecordday(), workrecord.getWorkrecord_id());
			return check;
		}catch(DataIntegrityViolationException e) {
			throw new HandlingException("活動記録データの更新処理が失敗しました");
		}
	}

	@Override
	public int workrecordDelete(int workrecord_id) {
		StringBuffer buf = new StringBuffer();
		buf.append(" DELETE FROM workrecord ");
		buf.append(" WHERE workrecord_id = ? ");
		
		check = jdbcTemplate.update(buf.toString(), workrecord_id);
		
		return check;
	}

}
