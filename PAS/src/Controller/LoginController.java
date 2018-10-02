
package Controller;

import Model.Employee;
import Model.Pool;
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
    private Pool pool;
    
    public LoginController() throws FileNotFoundException{
        loginUI = new LoginUI();
        pool = new Pool();
        
        loginUI.addLoginButtonListener(new LoginButtonListener());
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
                boolean isEmpId = pool.doesEmpIdExist(empID);
        
                if(isEmpId){
                    Employee tempEmp = pool.findEmployee(empID);

                    if(tempEmp.authenticate(empID, password)) { 
                        
                        loginUI.setStatusLabel("");
                        
                        try {
                            
                            mainMenuController = new MainMenuController(getLoginController(), tempEmp);
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
    
    public void toggleLoginUiVisible(boolean state){
        loginUI.setVisible(state);
    }
    
}
