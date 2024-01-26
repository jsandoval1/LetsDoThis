// * Notes:
//------------------------
// - File exists only to validate the login form input
// - After form input passes or doesn't pass validation and authentication, that LoginUser instance disappears.
// - Similarly to @transient, we do not want this to be stored in the database.
// - Not marked as @Entity because we do not want a LoginCheck object to be stored in the database

package sandoval.john.server.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginCheck {

    @NotEmpty(message = "Email is required!")
    @Email(message = "Please enter a valid email!")
    private String email;

    @NotEmpty(message = "Password is required!")
    @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
    private String password;

    // * Empty Constructor
    public LoginCheck() {
    }

    // * Getter and Setters
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
