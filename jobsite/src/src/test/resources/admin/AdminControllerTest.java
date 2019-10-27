import static org.junit.Assert.*;

import org.junit.Test;

import com.neet.jobsite.admin.AdminDashboardController;

public class AdminControllerTest {

	@Test
	public void AdminControllerDefaultTest() {
		AdminDashboardController controller = new AdminDashboardController();
		String viewName = controller.login(null, null);
		assertEquals(viewName, "admin/login");
	}
	
	//todo: due to time constraint have not added all the tests

}
