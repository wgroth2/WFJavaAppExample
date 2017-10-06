package com.vmware.broth.quickstart;

public class GaugeThread extends Thread {
    private WFIntegerGauge cGauge;
    private int cWaitMillis;
    
    GaugeThread(WFIntegerGauge g, int millis) {
    	cGauge = g;
    	cWaitMillis = millis;
    }
    //
    // Run method for thread.
    //
    public void run() {
    	//
    	// get info for gauge
    	//
    	int x;
       	while(!this.isInterrupted()) {
    	    		Double d = Math.random() *100;
    		x = d.intValue();
    		System.out.println("setting guage to " + x);
    		cGauge.setValue(x);
        	try {
    			sleep(cWaitMillis);
    		} catch (InterruptedException e) {
    			System.out.println("Gauge update interrupted");
    		}
    	}
    }
}
