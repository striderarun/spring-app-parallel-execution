package com.dashboard.service.impl;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dashboard.beans.InsuranceDetailsBean;
import com.dashboard.service.InsuranceService;


@Component
public class InsuranceServiceImpl implements InsuranceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceServiceImpl.class);

	@Override
	public InsuranceDetailsBean fetchInsuranceDetails(Integer employeeId) {
		LOGGER.info("Fetching insurance details");
		simulateOneSecondDelay();
		return new InsuranceDetailsBean();
	}
	
	private void simulateOneSecondDelay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	
	

}