import org.junit.Test;

import java.util.ArrayList;

import static javax.management.Query.times;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void PasswordMustBeSet()
    {
        User user = new User();
        App controller = new App(user);
        user=new User(controller) ;

        String expectedUsername = "John";
        String expectedPassword = "123";

        user.set_name_password(expectedUsername, expectedPassword);

        assertNotNull(expectedUsername, user.getUsername());
    }
}