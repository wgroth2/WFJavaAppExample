package com.vmware.broth.quickstart;

import com.codahale.metrics.Gauge;

public class WFIntegerGauge implements Gauge<Integer> {
	private Integer cInt;
	
	WFIntegerGauge() {
		cInt = 0;
	}
	
	public Integer getValue() {
		return cInt;
	}
	public void setValue(Integer i ) {
		cInt = i;
	}

}
