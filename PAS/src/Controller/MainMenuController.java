/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Admin;
import Model.Employee;
import Model.RoleEnum;
import View.MainMenuUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 *
 * @author Andrew Hopkins, David Ortiz
 */
public class MainMenuController  {
    
    private LoginController loginController;
    private Employee currentEmployee;
    private MainMenuUI mainMenuUi;
    
    public MainMenuController(LoginController _loginController, Employee _currentEmp) throws FileNotFoundException {
        loginController = _loginController;
        currentEmployee = _currentEmp;
        
        mainMenuUi = new MainMenuUI();
        
        mainMenuUi.addRegisterBtnListener(new RegisterBtnListener());
        mainMenuUi.addSearchBtnListener(new SearchBtnListener());
        mainMenuUi.addPoolBtnListener(new PoolBtnListener());
        mainMenuUi.addReportsBtnListener(new ReportsBtnListener());
        mainMenuUi.addLogOutBtnListener(new LogOutBtnListener());

        mainMenuUi.welcome(currentEmployee.getFirstName());

        mainMenuUi.toggleRegisterBtn(currentEmployee.isFunctionPermitted(RoleEnum.CREATE_PROFILE));
        mainMenuUi.toggleReportsBtn(currentEmployee.isFunctionPermitted(RoleEnum.VIEW_REPORTS));
        
        
                    
                    
    }
    
    //reset the log-in screen and make it visible again
    private class LogOutBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            loginController.resetScreen();
            loginController.toggleLoginUiVisible(true);
            mainMenuUi.setVisible(false);
        }
    }
    
    //navigate to the register new swimmer screen
    private class RegisterBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            //TODO: navigates to register new swimmer scree
            System.out.println("test - Register Screen");
        }
    }

    //navigate to the search screen
    private class SearchBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            //TODO: navigate to search screen
            System.out.println("test - Search Screen");
        }
    }

    //navigate to the pool view screen
    private class PoolBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            //TODO: navigate to pool screen
            System.out.println("test - Pool Screen");
        }
    }

    //navigate to the reports menu screen
    private class ReportsBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            //TODO: navigate to reports screen
            System.out.println("test - Reports Screen");
        }
    }
}

