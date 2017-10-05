package com.vmware.broth.quickstart;

import com.codahale.metrics.Counter;

public class CounterThread extends Thread {
	private Counter gCounter;
	private int gMillis;

	CounterThread(Counter c, int millis ){
		gCounter = c;
		gMillis = millis;
	}
	
	public void run() {
		gCounter.inc();
		try {
			sleep(gMillis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Counter Sleep Interrupted");
		}
		
	}
}
