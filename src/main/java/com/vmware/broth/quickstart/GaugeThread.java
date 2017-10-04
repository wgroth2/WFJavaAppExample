package com.vmware.broth.quickstart;

public class GaugeThread extends Thread {
    private WFIntegerGauge cGauge;
    private int cWaitMillis;
    
    GaugeThread(WFIntegerGauge g, int millis) {
    	cGauge = g;
    	cWaitMillis = millis;
    }
    public void run(){
    	// get info for gauge
    	int x;
    	while(true) {
    		Double d = Math.random() *100;
    		x = d.intValue();
    		System.out.println("setting guage to " + x);
    		cGauge.setValue(x);
        	try {
    			sleep(cWaitMillis);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			System.out.println("Gauge update interrupted");
    		}
    	}
    }
}
