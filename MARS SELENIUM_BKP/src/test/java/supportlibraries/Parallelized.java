package supportlibraries;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.runners.Parameterized;
import org.junit.runners.model.RunnerScheduler;

import com.util.frameworkutils.Settings;

public class Parallelized extends Parameterized{
	    
	    private static class ThreadPoolScheduler implements RunnerScheduler
	    {
	        private ExecutorService executor; 
	        private static Properties properties;
	        
	        public ThreadPoolScheduler()
	        {
	        	String strNumberOfThreads = System.getenv("THREADS_PER_MARKET");
                if(strNumberOfThreads == null){
                	properties = Settings.getInstance();
                	strNumberOfThreads = properties.getProperty("NumberOfThreads");	//From Properties file
//                    strNumberOfThreads = "2";
                }

                
	            String threads = System.getProperty("junit.parallel.threads", strNumberOfThreads);
	            int numThreads = Integer.parseInt(threads);
	            executor = Executors.newFixedThreadPool(numThreads);
	        }
	        
	        @Override
	        public void finished()
	        {
	            executor.shutdown();
	            try
	            {
	                executor.awaitTermination(5, TimeUnit.MINUTES);
	            }
	            catch (InterruptedException exc)
	            {
	                System.out.println("Await Termination Issued an Interrupted exception");
	            	//throw new RuntimeException(exc);
	            }
	            
	            while(!executor.isTerminated()) {
	    			try {
	    				Thread.sleep(3000);
	    			} catch (Exception e) {
	    				System.out.println("Interrupted in Parallelized class - failing "+e.getMessage());
	    				//e.printStackTrace();
	    			}
	            }
	        
	        }

	        @Override
	        public void schedule(Runnable childStatement)
	        {
	            executor.submit(childStatement);
	        }
	    }

	    public Parallelized(Class klass) throws Throwable
	    {
	        super(klass);
	        setScheduler(new ThreadPoolScheduler());
	    }
	
}
