package com.vmware.broth.quickstart;

import com.wavefront.integrations.metrics.WavefrontReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
	 
    @SuppressWarnings("unused")
	public static void main( String[] args )
    {
    	int x;
    	GaugeThread gt;
    	CounterThread ct;
    	
        System.out.println( "Hello World!" );
        
        //
        // Instantiate a register. Its where all the metrics go
        //
        MetricRegistry metrics = new MetricRegistry();
        //
        //Create a counter. Easiest type of metric.
        // Put it in a name space numbers1123.
        //
    	Counter counter = metrics.counter("numbers1123");
    	// add one
        counter.inc();
        //Create a gauge
        WFIntegerGauge g = new WFIntegerGauge();
        // Register the gauge
        metrics.register("numbers1123.gauge", g);
        
        // Reporter-level point tags
        // Set up all point tags.
        WavefrontReporter reporter = WavefrontReporter.forRegistry(metrics)
            .withSource("broths-home")
            .withPointTag("dc", "san-jose")
            .withPointTag("service", "query")
            .withJvmMetrics()
            .build("192.168.0.160", 2878);

        // run it to report every 2 seconds/
        reporter.start(2, TimeUnit.SECONDS);
       
        gt = new GaugeThread(g,2000);
        gt.start();
        ct = new CounterThread(counter,1500);
        ct.start();
        try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("main sleep interrupted");
		}
        
        gt.interrupt();
        ct.interrupt();
        System.exit(0);
    }
}
