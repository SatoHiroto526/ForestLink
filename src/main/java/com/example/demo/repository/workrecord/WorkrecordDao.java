package com.example.demo.repository.workrecord;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Contactmethod;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.User;
import com.example.demo.entity.Workrecord;
import com.example.demo.entity.Worktype;

public interface WorkrecordDao {
	
	List<Workrecord> workrecordAll(int partnerenterprise_id);
	
	Optional<Workrecord> workrecordDetail(int workrecord_id); 
	
	int workrecordInsert(Workrecord workrecord);
	
	List<Worktype> worktypeList();
	
	List<Enterprise> entryenterpriseList();
	
	List<User> userList();
	
	List<User> partneruserList();
	
	List<Contactmethod> contactmethodList();
	
	int workrecordUpdate(Workrecord workrecord);
	
	int workrecordDelete(int workrecord_id);

}
