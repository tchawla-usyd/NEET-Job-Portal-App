package jobsite;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.neet.jobsite.model.Candidate;
import com.neet.jobsite.model.User;

public class CandidateModelTest {
	
	@Test
	public void PropertySetTest() {
		Candidate candidate = new Candidate(); // MyClass is tested

		candidate.setId(1);
		candidate.setEducation("University Of Sydney");
		candidate.setExperience("8 years");
		candidate.setResume("John_resume.pdf");

		// assert statements

		assertEquals(candidate.getId(),1);
		assertEquals(candidate.getEducation(), "University Of Sydney");
		assertEquals(candidate.getExperience(),"8 years");
		assertEquals(candidate.getResume(),"John_resume.pdf");

	}

}
