package com.example.demo.repository.partnerUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Enterprise;
import com.example.demo.entity.User;
import com.example.demo.exception.HandlingException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PartnerUserDaoImpl implements PartnerUserDao {
	
	private final JdbcTemplate jdbcTemplate;
	int check;

	@Override
	public Optional<User> userDetail(int user_id) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM partneruser INNER JOIN partnerenterprise ");
		buf.append(" ON partneruser.partnerenterprise_id = partnerenterprise.partnerenterprise_id ");
		buf.append(" WHERE partneruser.partneruser_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), user_id);
			
		Enterprise enterprise = new Enterprise();
		enterprise.setEnterprisename((String)result.get("partnerenterprisename"));

		User user = new User();
		user.setUser_id((int)result.get("partneruser_id"));
		user.setEnterprise_id((int)result.get("partnerenterprise_id"));
		user.setEnterprise(enterprise);
		user.setUsername((String)result.get("partnerusername"));
		user.setUseremail((String)result.get("partneruseremail"));
		user.setUsernumber((String)result.get("partnerusernumber"));

		Optional<User> optPartnerUser = Optional.ofNullable(user);
		
		return optPartnerUser;
	}

	@Override
	public List<User> userAll() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM partneruser INNER JOIN partnerenterprise ");
		buf.append(" ON partneruser.partnerenterprise_id = partnerenterprise.partnerenterprise_id ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<User> userList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprisename((String)result.get("partnerenterprisename"));
			
			User user = new User();
			user.setUser_id((int)result.get("partneruser_id"));
			user.setEnterprise_id((int)result.get("partnerenterprise_id"));
			user.setEnterprise(enterprise);
			user.setUsername((String)result.get("partnerusername"));
			user.setUseremail((String)result.get("partneruseremail"));
			user.setUsernumber((String)result.get("partnerusernumber"));
			
			userList.add(user);
			
		}
		return userList;
	}

	@Override
	public List<Enterprise> partnerEnterpriseList() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM partnerenterprise ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<Enterprise> enterpriseList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprise_id((int)result.get("partnerenterprise_id"));
			enterprise.setEnterprisename((String)result.get("partnerenterprisename"));
			
			enterpriseList.add(enterprise);
			
		}
		
		return enterpriseList;
	}

	@Override
	public List<User> filteredUserList(int enterprise_Id) {
		StringBuffer buf = new StringBuffer();
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM partneruser INNER JOIN partnerenterprise ");
		buf.append(" ON partneruser.partnerenterprise_id = partnerenterprise.partnerenterprise_id ");
		
		if(enterprise_Id != 0) {
			buf.append(" WHERE partneruser.partnerenterprise_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), enterprise_Id);
		}else {
			resultList = jdbcTemplate.queryForList(buf.toString());
		}
		
		List<User> userList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprisename((String)result.get("partnerenterprisename"));
			
			User user = new User();
			user.setUser_id((int)result.get("partneruser_id"));
			user.setEnterprise_id((int)result.get("partnerenterprise_id"));
			user.setEnterprise(enterprise);
			user.setUsername((String)result.get("partnerusername"));
			user.setUseremail((String)result.get("partneruseremail"));
			user.setUsernumber((String)result.get("partnerusernumber"));
			
			userList.add(user);
			
		}
		return userList;
	}

	@Override
	public int userInsert(User user) {
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO partneruser(partnerenterprise_id, partnerusername, partneruseremail, partnerusernumber) ");
		buf.append(" VALUES(?, ?, ?, ?) ");

		check = jdbcTemplate.update(buf.toString(), user.getEnterprise_id(), user.getUsername(),
				user.getUseremail(), user.getUsernumber());

		return check;
	}

	@Override
	public int userUpdate(User user) {
		StringBuffer buf = new StringBuffer();
		buf.append(" UPDATE partneruser SET partnerenterprise_id = ?, partnerusername = ?, partneruseremail = ?, partnerusernumber = ? ");
		buf.append(" WHERE partneruser_id = ? ");

		check = jdbcTemplate.update(buf.toString(), user.getEnterprise_id(), user.getUsername(),
				user.getUseremail(), user.getUsernumber(), user.getUser_id());

		return check;
	}

	@Override
	public int userDelete(int user_id) {
		StringBuffer buf1 = new StringBuffer();
		buf1.append(" SELECT * FROM purchasetrading ");
		buf1.append(" WHERE salesuser_id = ? ");
		List<Map<String, Object>> resultList1 = jdbcTemplate.queryForList(buf1.toString(), user_id);
		
		StringBuffer buf2 = new StringBuffer();
		buf2.append(" SELECT * FROM salestrading ");
		buf2.append(" WHERE purchaseuser_id = ? ");
		List<Map<String, Object>> resultList2 = jdbcTemplate.queryForList(buf2.toString(), user_id);
				
		StringBuffer buf3 = new StringBuffer();
		buf3.append(" SELECT * FROM workrecord ");
		buf3.append(" WHERE partneruser_id = ? ");
		List<Map<String, Object>> resultList3 = jdbcTemplate.queryForList(buf3.toString(), user_id);
		
		if(resultList1.size() == 0 && resultList2.size() == 0 && resultList3.size() == 0) {
			StringBuffer buf4 = new StringBuffer();
			buf4.append(" DELETE FROM partneruser ");
			buf4.append(" WHERE partneruser_id = ? ");
			
			check = jdbcTemplate.update(buf4.toString(), user_id);
			
			return check;
		}else {
			throw new HandlingException("削除しようとしているレコードは別レコードに参照されているため削除できません");
		}
	}

}
