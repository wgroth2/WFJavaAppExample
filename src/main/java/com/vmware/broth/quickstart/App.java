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
	
	 static void wait5Seconds() {
	      try {
	          Thread.sleep(5*1000);
	      }
	      catch(InterruptedException e) {}
	 }
	 
    @SuppressWarnings("unused")
	public static void main( String[] args )
    {
    	int x;
    	x=1;
        System.out.println( "Hello World!" );
        x=1;
        
        MetricRegistry metrics = new MetricRegistry();
    	Counter counter = metrics.counter("numbers1123");
        counter.inc();
        //
        WFIntegerGauge g = new WFIntegerGauge();
        
        metrics.register("numbers1123.gauge", g);
        
        // Reporter-level point tags
        WavefrontReporter reporter = WavefrontReporter.forRegistry(metrics)
            .withSource("broths-home")
            .withPointTag("dc", "san-jose")
            .withPointTag("service", "query")
            .withJvmMetrics()
            .build("192.168.0.160", 2878);

        reporter.start(2, TimeUnit.SECONDS);
       
        g.setValue((int)Math.random()*100);
        wait5Seconds();
        g.setValue((int)Math.random()*100);
        counter.inc();
        wait5Seconds();
        counter.inc();
        g.setValue((int)Math.random()*100);
        wait5Seconds();
        g.setValue((int)Math.random()*100);
        counter.inc();
        wait5Seconds();
        x=2;
        
    }
}
