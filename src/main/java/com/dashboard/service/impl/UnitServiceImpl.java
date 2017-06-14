package com.dashboard.service.impl;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dashboard.beans.UnitDetailsBean;
import com.dashboard.service.UnitService;

@Component
public class UnitServiceImpl implements UnitService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UnitServiceImpl.class);

	@Override
	public UnitDetailsBean fetchUnitDetails(Integer employeeId) {
		LOGGER.info("Fetching unit details");
		simulateOneSecondDelay();
		return new UnitDetailsBean();
	}

	private void simulateOneSecondDelay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
