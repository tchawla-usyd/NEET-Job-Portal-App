package jobsite;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.neet.jobsite.UserAuthenticationController;
import com.neet.jobsite.bal.IUserService;
import com.neet.jobsite.filter.CORSFilter;
import com.neet.jobsite.model.User;

import junit.framework.TestCase;

public class UserAuthenticationControllerTest extends TestCase {
	
	@Mock
	private UserAuthenticationController userController;
	
	@Mock
	private IUserService userService;
	
	private MockMvc mockMvc;
	
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .addFilters(new CORSFilter())
                .build();
    }
	 
	public void testHandlerRequest() throws Exception {
		
		User user = new User();
		user.setFirstName("slha");
		user.setLastName("slha");
		user.setEmail("sf@gmail.com");
		user.setPassword("sjafa");
		user.setUserTypeID(4);
		
		
		
		HttpServletRequest request = mock(HttpServletRequest.class);
	     HttpServletResponse response = mock(HttpServletResponse.class);
	     
	     mockMvc.perform(post("/authenticate/registerProcess"))
         .andExpect(status().isOk())
         .andReturn();
		
	}

}
