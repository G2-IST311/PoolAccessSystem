
package Controller;

import Model.Employee;
import Model.EmployeeList;
import View.LoginUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Ortiz
 */
public class LoginController {
    
    private LoginUI loginUI;
    private MainMenuController mainMenuController;
    private String empID;
    private String password;
    private EmployeeList employeeList;
    
    public LoginController() throws FileNotFoundException{
        loginUI = new LoginUI();
        employeeList = new EmployeeList();
        
        loginUI.addLoginButtonListener(new LoginButtonListener());
        loginUI.addQuitButtonListener(new QuitButtonListener());
        
        loginUI.addPasswordFieldKeyPressed(keyListener);

    }
    
    public LoginController getLoginController(){
        return this;
    }

    private KeyListener keyListener = new KeyListener() {
        public void keyPressed(KeyEvent keyEvent) {
        }
        public void keyTyped(KeyEvent keyEvent) {   
        }
        public void keyReleased(KeyEvent e) {
            if(loginUI.getEmpIDField().equals("") || loginUI.getPasswordField().equals("")){
                loginUI.toggleLoginButton(false);
            }else{
                loginUI.toggleLoginButton(true);
            }
        }
        
    }; //end keyListener
    
    public EmployeeList getEmployeeList(){
        return this.employeeList;
    }
    
    private class QuitButtonListener implements ActionListener 
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
        
    }
    
    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            empID = loginUI.getEmpIDField();
            password = loginUI.getPasswordField();
            
            if(empID.equals("") || password.equals("")){
                System.out.println("One of the fields are empty");
            } 
            else 
            {
                boolean isEmpId = employeeList.doesEmpIdExist(empID);
        
                if(isEmpId){
                    Employee tempEmp = employeeList.findEmployee(empID);

                    if(tempEmp.authenticate(empID, password)) { 
                        
                        loginUI.setStatusLabel("");
                        
                        try {
                            
                            mainMenuController = new MainMenuController(getLoginController(), tempEmp);
                            resetScreen();
                            loginUI.setVisible(false);
                            
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                    else 
                    {
                        loginUI.setStatusLabel("Login failed, incorrect password.");
                    } 

                } else {
                    loginUI.setStatusLabel("Login failed, user does not exist."); 
                }
            }
            
            loginUI.resetPasswordField();
            
        }

    } // end LoginButtonListener
    
    public void resetScreen() {
        loginUI.resetPasswordField();
        loginUI.setEmpIDLabel("");
        loginUI.toggleLoginButton(false);
    }
    
    public void toggleLoginUiVisible(boolean state) throws FileNotFoundException{
        if(state){
            this.employeeList.refreshEmployeeList();
        }
        loginUI.setVisible(state);
    }
    
}
