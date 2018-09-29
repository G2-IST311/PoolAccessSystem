package Model;

import Model.Reports.Report;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author d.mikhaylov, David Ortiz
 */
public class Pool {

    private Report report;
    private ArrayList<Swimmer> swimmers;
    private ArrayList<Visit> visits;
    private ArrayList<Employee> employees;

    public Pool() throws FileNotFoundException {
        swimmers = new ArrayList<>();
        visits = new ArrayList<>();
        employees = fetchEmployeesFromFile();
        report = new Report(this);
    }

    /**
     * @return the report
     */
    public Report getReport() {
        return report;
    }

    /**
     * @return the swimmers
     */
    public ArrayList<Swimmer> getSwimmers() {
        return swimmers;
    }

    /**
     * @return the visits
     */
    public ArrayList<Visit> getVisits() {
        return visits;
    }
    
    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }
    
    public void addSwimmer(Swimmer swimmer){
        swimmers.add(swimmer);
    }
    
    public void addVisit(Swimmer swimmer){
        visits.add(new Visit(swimmer));
    }
    
    public void addEmployee(Employee _employee){
        employees.add(_employee);
    }
    
    public boolean doesEmpIdExist(String _empID){
        for (Employee emp : this.employees) 
        { 

            if(emp.getCredential().getEmpID().toLowerCase().equals(_empID)){
                return true;
            }
        }
        return false;
    }
    public Employee findEmployee(String _empID){
        for (Employee emp : this.employees) 
        { 
            if(emp.getCredential().getEmpID().toLowerCase().equals(_empID)){
                return emp;
            }
        }
        return null;
    }

    
    
    private static ArrayList<Employee> fetchEmployeesFromFile() throws FileNotFoundException{
        String fileName = "employees.txt";
        
        
        ArrayList<Employee> tempEmployees = new ArrayList<>();
         
        String line = null;
         
        int index = 0;
        
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
                
                if(line != ""){
                    String[] type = line.split("~");
                    
                    if(type[0].equals("admin")){
                        
                        String[] words = type[1].split(";");
                        String fname = words[0];
                        String lname = words[1];
                        String pass = words[2];
                        
                        Admin newA = new Admin(fname, lname);
                        newA.setCredential(pass);
                        tempEmployees.add(newA);
                        
                    } else if(type[0].equals("operator")){
                        
                        String[] words = type[1].split(";");
                        String fname = words[0];
                        String lname = words[1];
                        String pass = words[2];
                        
                        Operator newO = new Operator(fname, lname);
                        newO.setCredential(pass);
                        tempEmployees.add(newO);
                        
                    } else {
                        
                        System.out.println("Invalid employee");
                    }
                    
                    index++;
                } 
                
            }
            
            bufferedReader.close(); 
             
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println( "Error reading file '" + fileName + "'");   
        }
        return tempEmployees;
         
    } // end fetchEmployeesFromFile()
}
