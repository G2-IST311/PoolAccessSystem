
package Controller;

import View.RegisterEmployeeUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Joseph Hackett
 */


public class RegisterEmployeeController {
    
    private MainMenuController MMC; 
    private RegisterEmployeeUI REUI;
    public RegisterEmployeeController(MainMenuController _MMC)
    {
      MMC = _MMC;  
      REUI = new RegisterEmployeeUI();
      REUI.addSubmitBtnListener(new SubmitButtonListener());
      REUI.addCnclBtnListener(new CancelButtonListener());
    }
    
  
    
    private class SubmitButtonListener implements ActionListener{ 

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            
            if(REUI.checkFields())
            {    
               REUI.setErrorMsgLabel("One or more fields are not completed");     
            }
            else
            {
               if (REUI.containsForbiddenCharacters())
                {
                    REUI.setErrorMsgLabel(" the characters ~ and ; cannot be contained in any field");
                    REUI.resetFields();
                }
            
                else                
                {    
                    
                    REUI.setGoodMessage("Submitted Successfully");
                    MMC.saveEmployee(REUI.getRole(), REUI.getfNameTxtField(), REUI.getlNameTxtField(), REUI.getEmpPWField());
                    MMC.toggleMainMenuUI(true);
                    REUI.setVisible(false);
                }     
            }    
            
        }
        
    }

    private class CancelButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            MMC.toggleMainMenuUI(true);
            REUI.setVisible(false);
        }
        
    }
   
}
