package com.example.demo.service.workRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contactmethod;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Salesworktype;
import com.example.demo.entity.User;
import com.example.demo.entity.Workrecord;
import com.example.demo.entity.Worktype;
import com.example.demo.exception.HandlingException;
import com.example.demo.repository.workrecord.WorkrecordDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkRecordServiceImpl implements WorkRecordService {
	
	private final WorkrecordDao workrecordDao;

	@Override
	public List<Workrecord> workrecordAll(int partnerenterprise_id) {
		return workrecordDao.workrecordAll(partnerenterprise_id);
	}

	@Override
	public Workrecord workrecordDetail(int workrecord_id) {

		try {
			
			Workrecord workrecord = new Workrecord();
			Optional<Workrecord> optWorkrecord = workrecordDao.workrecordDetail(workrecord_id); 
			
			if(optWorkrecord.isPresent()) {
				workrecord = optWorkrecord.get();
			}
			
			return workrecord;
			
		}catch(EmptyResultDataAccessException e) {
			throw new HandlingException("お探しの活動記録が見つかりません");
		}
	}

	@Override
	public void workrecordInsert(Workrecord workrecord) {
		if(workrecordDao.workrecordInsert(workrecord) == 0) {
			throw new HandlingException("活動記録データの追加処理が失敗しました");
		}	
	}

	@Override
	public List<Worktype> worktypeList() {
		return workrecordDao.worktypeList();
	}

	@Override
	public List<Enterprise> entryenterpriseList() {
		return workrecordDao.entryenterpriseList();
	}

	@Override
	public List<User> userList() {
		return workrecordDao.userList();
	}

	@Override
	public List<User> partneruserList() {
		return workrecordDao.partneruserList();
	}

	@Override
	public List<Contactmethod> contactmethodList() {
		return workrecordDao.contactmethodList();
	}

	@Override
	public List<Salesworktype> salesworktypeList() {
		List<Salesworktype> salesworktypeList = new ArrayList<>();
		Salesworktype inbound = new Salesworktype();
		inbound.setSalesworktype("インバウンド");
		salesworktypeList.add(inbound);
		Salesworktype outbound = new Salesworktype();
		outbound.setSalesworktype("アウトバウンド");
		salesworktypeList.add(outbound);
		return salesworktypeList;
	}

	@Override
	public void workrecordUpdate(Workrecord workrecord) {
		if(workrecordDao.workrecordUpdate(workrecord) == 0) {
			throw new HandlingException("活動記録データの更新処理が失敗しました");
		}
	}

	@Override
	public void workrecordDelete(int workrecord_id) {
		if(workrecordDao.workrecordDelete(workrecord_id) == 0) {
			throw new HandlingException("活動記録データの削除処理が失敗しました");
		}
	}

}
