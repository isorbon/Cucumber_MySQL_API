package steps;

import pages.AddNewEmployeePage;
import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.LoginPage;

public class PageInitializer {

    public static LoginPage loginPage;
    public static DashboardPage dash;
    public  static AddNewEmployeePage addNewEmployeePage;
    public static EmployeeListPage employeeListPage;


    public static void initializePageObjects(){
        loginPage = new LoginPage();
        dash = new DashboardPage();
        addNewEmployeePage = new AddNewEmployeePage();
        employeeListPage = new EmployeeListPage();
    }

}
