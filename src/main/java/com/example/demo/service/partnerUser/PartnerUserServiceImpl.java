package com.example.demo.service.partnerUser;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Enterprise;
import com.example.demo.entity.User;
import com.example.demo.exception.HandlingException;
import com.example.demo.repository.partnerUser.PartnerUserDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartnerUserServiceImpl implements PartnerUserService {
	
	private final PartnerUserDao partnerUserDao;

	@Override
	public User userDetail(int user_Id) {
		try {
			
			User user = new User();
			Optional<User> optPartnerUser = partnerUserDao.userDetail(user_Id);
			
			if(optPartnerUser.isPresent()) {
				user = optPartnerUser.get();
			}
			
			return user; 
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("外部取引先人材が見つかりません");
		}
	}

	@Override
	public List<User> userAll() {
		return partnerUserDao.userAll();
	}

	@Override
	public List<Enterprise> partnerEnterpriseList() {
		return partnerUserDao.partnerEnterpriseList();
	}

	@Override
	public List<User> filteredUserList(int enterprise_Id) {
		return partnerUserDao.filteredUserList(enterprise_Id);
	}

	@Override
	public void userInsert(User user) {
		if(partnerUserDao.userInsert(user) == 0) {
			throw new HandlingException("取引先人材データの追加処理が失敗しました");
		}
	}

	@Override
	public void userUpdate(User user) {
		if(partnerUserDao.userUpdate(user) == 0) {
			throw new HandlingException("取引先人材データの更新処理が失敗しました");
		}
	}

	@Override
	public void userDelete(int user_id) {
		if(partnerUserDao.userDelete(user_id) == 0) {
			throw new HandlingException("取引先人材データの削除処理が失敗しました");
		}
	}

}
