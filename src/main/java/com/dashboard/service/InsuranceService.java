package com.dashboard.service;

import com.dashboard.beans.InsuranceDetailsBean;

public interface InsuranceService {

	InsuranceDetailsBean fetchInsuranceDetails(Integer employeeId);
}
