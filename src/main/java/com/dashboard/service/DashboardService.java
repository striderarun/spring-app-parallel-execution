package com.dashboard.service;

import com.dashboard.beans.EmployeeDashboardBean;

public interface DashboardService {
	 	
	 EmployeeDashboardBean fetchDashboardDetails(Integer employeeId);
	 
	 <T> EmployeeDashboardBean fetchDashboardDetailsParallel(Integer employeeId);
	
}
