package jobsite;

import com.neet.jobsite.UserAuthenticationController;

import junit.framework.TestCase;

public class UserAuthenticationControllerTest extends TestCase {
	
	public void testHandlerRequest() throws Exception {
		UserAuthenticationController controller = new UserAuthenticationController();
		controller.authenticateByToken(null, null);
		
	}

}
