package jobsite;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.neet.jobsite.model.Job;


public class JobModelTest {
	
	Job job = new Job(); // MyClass is tested

	@Test
	public void PropertySetTest() {
		
		Job job = new Job();
		
		job.setTitle("Developer");
		job.setJobDescription("work as a developer");
		job.setLocation("Sydney");
		job.setJobCategoryID(1);
		
		
		assertEquals(job.getTitle(),"Developer");
		assertEquals(job.getJobDescription(),"work as a developer");
		assertEquals(job.getLocation(),"Sydney" );
		assertEquals(job.getJobCategoryID(), Integer.valueOf(1));
		
	}

	// assert statements



}
