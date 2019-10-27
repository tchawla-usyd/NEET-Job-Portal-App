import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.neet.jobsite.model.User;

public class UserModelTest {

    @Test
    public void PropertySetTest() {
        User user = new User(); // MyClass is tested

        user.setFirstName("Masudur");
        user.setLastName("Rahman");
        // assert statements
        
        assertEquals(user.getFirstName(), "Masudur");
        assertEquals(user.getLastName(), "Rahman");
        assertEquals(user.isIsActive(), false);
        assertEquals(user.isIsLocked()(), false);
    }
    
	//todo: due to time constraint have not added all the tests

}