package com.example.demo.repository.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Enterprise;
import com.example.demo.entity.User;
import com.example.demo.exception.HandlingException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
	
	private final JdbcTemplate jdbcTemplate;
	int check;

	@Override
	public Optional<User> userDetail(String email) {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM user INNER JOIN entryenterprise ");
		buf.append(" ON user.enterprise_id = entryenterprise.enterprise_id ");
		buf.append(" WHERE user.useremail = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), email);
		
		Enterprise enterprise = new Enterprise();
		enterprise.setEnterprisename((String)result.get("enterprisename"));
		
		User user = new User();
		user.setUser_id((int)result.get("user_id"));
		user.setAuthority((String)result.get("authority"));
		user.setEnterprise_id((int)result.get("enterprise_id"));
		user.setEnterprise(enterprise);
		user.setUsername((String)result.get("username"));
		user.setUseremail((String)result.get("useremail"));
		user.setUsernumber((String)result.get("usernumber"));
		
		Optional<User> optUser = Optional.ofNullable(user);
		
		return optUser;
	}
	
	@Override
	public Optional<User> userDetail(int user_Id){
		
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM user INNER JOIN entryenterprise ");
		buf.append(" ON user.enterprise_id = entryenterprise.enterprise_id ");
		buf.append(" WHERE user.user_id = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), user_Id);
		
		Enterprise enterprise = new Enterprise();
		enterprise.setEnterprisename((String)result.get("enterprisename"));
		
		User user = new User();
		user.setUser_id((int)result.get("user_id"));
		user.setAuthority((String)result.get("authority"));
		user.setEnterprise_id((int)result.get("enterprise_id"));
		user.setEnterprise(enterprise);
		user.setUsername((String)result.get("username"));
		user.setUseremail((String)result.get("useremail"));
		user.setUsernumber((String)result.get("usernumber"));
		
		Optional<User> optUser = Optional.ofNullable(user);
		
		return optUser;
	}
	
	@Override
	public List<User> userAll(){
		
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM user INNER JOIN entryenterprise ");
		buf.append(" ON user.enterprise_id = entryenterprise.enterprise_id ");
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(buf.toString());
		
		List<User> userList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprisename((String)result.get("enterprisename"));
			
			User user = new User();
			user.setUser_id((int)result.get("user_id"));
			user.setAuthority((String)result.get("authority"));
			user.setEnterprise_id((int)result.get("enterprise_id"));
			user.setEnterprise(enterprise);
			user.setUsername((String)result.get("username"));
			user.setUseremail((String)result.get("useremail"));
			user.setUsernumber((String)result.get("usernumber"));
			
			userList.add(user);
			
		}
		return userList;
	}
	
	
	@Override
	public List<Enterprise> entryEnterpriseList() {
		
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
	public List<User> filteredUserList(int enterprise_Id){
		
		StringBuffer buf = new StringBuffer();
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM user INNER JOIN entryenterprise ");
		buf.append(" ON user.enterprise_id = entryenterprise.enterprise_id ");
		
		if(enterprise_Id != 0) {
			buf.append(" WHERE ");
			buf.append(" user.enterprise_id = ? ");
			resultList = jdbcTemplate.queryForList(buf.toString(), enterprise_Id);
		}else {
			resultList = jdbcTemplate.queryForList(buf.toString());
		}
		
		List<User> userList = new ArrayList<>();
		
		for(Map<String, Object> result : resultList) {
			
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprisename((String)result.get("enterprisename"));
			
			User user = new User();
			user.setUser_id((int)result.get("user_id"));
			user.setAuthority((String)result.get("authority"));
			user.setEnterprise_id((int)result.get("enterprise_id"));
			user.setEnterprise(enterprise);
			user.setUsername((String)result.get("username"));
			user.setUseremail((String)result.get("useremail"));
			user.setUsernumber((String)result.get("usernumber"));
			
			userList.add(user);
			
		}
		return userList;
		
	}
	
	@Override
	public int userInsert(User user) {
		
		try {
			StringBuffer buf = new StringBuffer();
			buf.append(" INSERT INTO user(authority, enterprise_id, username, password, useremail, usernumber) ");
			buf.append(" VALUES(?, ?, ?, ?, ?, ?) ");
			
			check = jdbcTemplate.update(buf.toString(), user.getAuthority(), user.getEnterprise_id(), user.getUsername(),
					user.getPassword(), user.getUseremail(), user.getUsernumber());
		}catch(DuplicateKeyException e) {
			throw new HandlingException("既に登録されているユーザーメールアドレスは登録することができません");
		}
			
			return check;
		
	}
	
	@Override
	public int userUpdate(User user) {
		
		try {
			StringBuffer buf = new StringBuffer();
			buf.append(" UPDATE user SET authority = ?, enterprise_id = ?, username = ?, ");
			buf.append(" password = ?, useremail = ?, usernumber = ? ");
			buf.append(" WHERE user_id = ? ");
			
			check = jdbcTemplate.update(buf.toString(), user.getAuthority(), user.getEnterprise_id(), user.getUsername(),
					user.getPassword(), user.getUseremail(), user.getUsernumber(), user.getUser_id());
		}catch(DuplicateKeyException e) {
			throw new HandlingException("既に登録されているユーザーメールアドレスは登録することができません");
		}
			
			return check;
		
	}

	@Override
	public int userDelete(int user_id) {
		StringBuffer buf1 = new StringBuffer();
		buf1.append(" SELECT * FROM stock ");
		buf1.append(" WHERE liabilityuser_id = ? ");
		List<Map<String, Object>> resultList1 = jdbcTemplate.queryForList(buf1.toString(), user_id);
		
		StringBuffer buf2 = new StringBuffer();
		buf2.append(" SELECT * FROM entrytrading ");
		buf2.append(" WHERE purchaseuser_id = ? ");
		List<Map<String, Object>> resultList2 = jdbcTemplate.queryForList(buf2.toString(), user_id);
		
		StringBuffer buf3 = new StringBuffer();
		buf3.append(" SELECT * FROM purchasetrading ");
		buf3.append(" WHERE purchaseuser_id = ? ");
		List<Map<String, Object>> resultList3 = jdbcTemplate.queryForList(buf3.toString(), user_id);
		
		StringBuffer buf4 = new StringBuffer();
		buf4.append(" SELECT * FROM salestrading ");
		buf4.append(" WHERE salesuser_id = ? ");
		List<Map<String, Object>> resultList4 = jdbcTemplate.queryForList(buf4.toString(), user_id);
		
		StringBuffer buf5 = new StringBuffer();
		buf5.append(" SELECT * FROM stocktreasurer ");
		buf5.append(" WHERE repuser_id = ? ");
		List<Map<String, Object>> resultList5 = jdbcTemplate.queryForList(buf5.toString(), user_id);
		
		StringBuffer buf6 = new StringBuffer();
		buf6.append(" SELECT * FROM workrecord ");
		buf6.append(" WHERE user_id = ? ");
		List<Map<String, Object>> resultList6 = jdbcTemplate.queryForList(buf6.toString(), user_id);
		
		StringBuffer buf7 = new StringBuffer();
		buf7.append(" SELECT * FROM entrytrading ");
		buf7.append(" WHERE salesuser_id = ? ");
		List<Map<String, Object>> resultList7 = jdbcTemplate.queryForList(buf7.toString(), user_id);
		
		if(resultList1.size() == 0 && resultList2.size() == 0 && resultList3.size() == 0 && resultList4.size() == 0 &&
				resultList5.size() == 0 && resultList6.size() == 0  && resultList7.size() == 0) {
			StringBuffer buf8 = new StringBuffer();
			buf8.append(" DELETE FROM user ");
			buf8.append(" WHERE user_id = ? ");
			
			check = jdbcTemplate.update(buf8.toString(), user_id);
			
			return check;
		}else {
			throw new HandlingException("削除しようとしているレコードは別レコードに参照されているため削除できません");
		}
	}
	


}
