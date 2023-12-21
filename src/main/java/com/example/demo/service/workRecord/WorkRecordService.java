package com.example.demo.service.workRecord;

import java.util.List;

import com.example.demo.entity.Contactmethod;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Salesworktype;
import com.example.demo.entity.User;
import com.example.demo.entity.Workrecord;
import com.example.demo.entity.Worktype;

public interface WorkRecordService {
	
	List<Workrecord> workrecordAll(int partnerenterprise_id);
	
	Workrecord workrecordDetail(int workrecord_id); 
	
	void workrecordInsert(Workrecord workrecord);
	
	List<Worktype> worktypeList();
	
	List<Enterprise> entryenterpriseList();
	
	List<User> userList();
	
	List<User> partneruserList();
	
	List<Contactmethod> contactmethodList();
	
	List<Salesworktype> salesworktypeList();
	
	void workrecordUpdate(Workrecord workrecord);
	
	void workrecordDelete(int workrecord_id);

}
