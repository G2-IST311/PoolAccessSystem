import Controller.LoginController;
import java.io.FileNotFoundException;


/**
 * 
 * @author d.mikhaylov, David Ortiz
 */
public class app {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        LoginController loginController = new LoginController();
        
        /*  
            Employee list (retrieved from "employees.txt" in root directory:
            empID is lastname + first 2 chars of firstname.
        
            empID = scottmi  
            password = mypass
        
            empID = shrutedw
            password = sheriff
        
            empID = squarepantssp
            password = pineapple
        
            empID = starpa
            password = rock
                
        */
        
    }
}
