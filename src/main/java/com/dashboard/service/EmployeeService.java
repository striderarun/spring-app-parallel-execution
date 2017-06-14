package com.dashboard.service;

import com.dashboard.beans.CompensationDetailsBean;
import com.dashboard.beans.PersonalDetailsBean;
import com.dashboard.beans.RoleDetailsBean;
import com.dashboard.beans.SkillDetailsBean;

public interface EmployeeService {
	 	
	 CompensationDetailsBean fetchCompensationDetails(Integer employeeId);
	 
	 PersonalDetailsBean fetchPersonalDetails(Integer employeeId);
	 
	 RoleDetailsBean fetchRoleDetails(Integer employeeId);
	 
	 SkillDetailsBean fetchSkillDetails(Integer employeeId);
	
}
