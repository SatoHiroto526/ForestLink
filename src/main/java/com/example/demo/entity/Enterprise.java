package com.example.demo.entity;

import lombok.Data;

@Data
public class Enterprise {
	
	private int enterprise_id;
	private String enterprisename;
	private String postalcode;
	private String address;
	private String foundnationdate; 
	private String chiefname;
	private String capital;
	private String corporatenumber;
	private int employees;
	private int businesstype_id;
	private String businessdetail;
	private String enterprisenumber;
	private String enterpriseemail;
	private String homepage;
	private int partnerenterpriserank_id;
	private BusinessType businesstype;
	private Partnerenterpriserank partnerenterpriserank;
	Enterprise mainenterprise;
	private int mainenterprise_id;

}
