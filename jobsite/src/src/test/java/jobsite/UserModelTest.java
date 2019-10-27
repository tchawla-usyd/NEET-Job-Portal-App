package jobsite;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.neet.jobsite.model.User;

public class UserModelTest {

	@Test
	public void PropertySetTest() {
		User user = new User(); // MyClass is tested

		user.setFirstName("Paul");
		user.setLastName("John");
		user.setEmail("paul.john@gmail.com");
		user.setPassword("paul@1234");
		user.setUserTypeID(4);

		// assert statements

		assertEquals(user.getFirstName(), "Paul");
		assertEquals(user.getLastName(), "John");
		assertEquals(user.getEmail(), "paul.john@gmail.com");
		assertEquals(user.getPassword(), "paul@1234");
		assertEquals(user.getUserTypeID(), Integer.valueOf(4));

	}
}
