package com.example.demo.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Authority;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.User;
import com.example.demo.exception.HandlingException;
import com.example.demo.repository.user.UserDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User userDetail(String useremail) {
		try {
			
			User user = new User();
			Optional<User> optUser = userDao.userDetail(useremail);
			
			if(optUser.isPresent()) {
				user = optUser.get();
			}
			
			return user; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("ユーザーが見つかりません");
		}
	}
	
	@Override
	public User userDetail(int user_Id) {
		try {
			
			User user = new User();
			Optional<User> optUser = userDao.userDetail(user_Id);
			
			if(optUser.isPresent()) {
				user = optUser.get();
			}
			
			return user; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("ユーザーが見つかりません");
		}
	}
	
	@Override
	public List<User> userAll(){
		return userDao.userAll();
	}
	
	@Override
	public List<Enterprise> entryEnterpriseList(){
		return userDao.entryEnterpriseList();
	}
	
	@Override
	public List<User> filteredUserList(int enterprise_Id){
		return userDao.filteredUserList(enterprise_Id);
	}
	
	@Override
	public void userInsert(User user) {
		var encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		if(userDao.userInsert(user) == 0) {
			throw new HandlingException("ユーザーデータの追加処理が失敗しました");
		}	
	}
	
	@Override
	public List<Authority> authortyList(){
		List<Authority> authorityList = new ArrayList<>();
		Authority adminAuthority = new Authority();
		adminAuthority.setAuthority("admin");
		authorityList.add(adminAuthority);
		Authority userAuthority = new Authority();
		userAuthority .setAuthority("user");
		authorityList.add(userAuthority);
		return authorityList;
	}
	
	@Override
	public void userUpdate(User user) {
		var encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		if(userDao.userUpdate(user) == 0) {
			throw new HandlingException("ユーザーデータの更新処理が失敗しました");
		}	
	}

	@Override
	public void userDelete(int user_id) {
		if(userDao.userDelete(user_id) == 0) {
			throw new HandlingException("ユーザーデータの削除処理が失敗しました");
		}
	}

}
