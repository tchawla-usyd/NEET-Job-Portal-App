package jobsite;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.User;

public class CompanyModelTest {

	@Test
	public void PropertySetTest() {
		Company company = new Company(); // MyClass is tested

		company.setId((long) 4);
		company.setCompanyName("Amazon");
		company.setCompanyImage("Amazon.png");
		company.setBusinessPhone("02876187161");
		company.setUserID(5);
		company.setWebSite("www.amazon.com.au");
		// assert statements

		assertEquals(company.getId(), 4);
		assertEquals(company.getCompanyName(), "Amazon");
		assertEquals(company.getCompanyImage(), "Amazon.png");
		assertEquals(company.getBusinessPhone(), "02876187161");
		assertEquals(company.getWebSite(), "www.amazon.com.au");

	}

}
