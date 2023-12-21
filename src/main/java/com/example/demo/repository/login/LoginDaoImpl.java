package com.example.demo.repository.login;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LoginDaoImpl implements LoginDao {
	
	private final JdbcTemplate jdbcTemplate; 

	@Override
	public User loginUser(String useremail) {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" * ");
		buf.append(" FROM user ");
		buf.append(" WHERE useremail = ? ");
		
		Map<String, Object> result = jdbcTemplate.queryForMap(buf.toString(), useremail);
		
		User user = new User();
		user.setAuthority((String)result.get("authority"));
		user.setPassword((String)result.get("password"));
		user.setUseremail((String)result.get("useremail"));
		
		return user;
	}

}
