import static org.junit.Assert.*;

import org.junit.Test;

import com.neet.jobsite.model.Company;

public class CompanyModelTest {

	@Test
	public void PropertySetTest() {
		Company user = new Company(); // MyClass is tested

        user.setCompanyName("Google");
        user.setBusinessPhone("0405552940");
        // assert statements
        
        assertEquals(user.setCompanyName(), "Google");
        assertEquals(user.setBusinessPhone(), "0405552940");

	}
	
	//todo: due to time constraint have not added all the tests


}
