package com.vmware.broth.quickstart;

import com.codahale.metrics.Gauge;

public class WFDoubleGauge implements Gauge<Double> {
	private Double cValue;
	
	WFDoubleGauge() {
		cValue = 0.0;
	}
	
	public Double getValue() {
		return cValue;
	}
	public void setValue(Double i ) {
		cValue = i;
	}

}
