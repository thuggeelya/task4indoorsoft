package ru.company.task4indoorsoft.web.screens.employee;

import com.haulmont.cuba.gui.screen.*;
import ru.company.task4indoorsoft.entity.Employee;

@UiController("task4indoorsoft_Employees")
@UiDescriptor("employees.xml")
@LookupComponent("employeesTable")
@LoadDataBeforeShow
public class Employees extends StandardLookup<Employee> {
//    @Inject
//    protected ScreenBuilders screenBuilders;
//
//    @Subscribe("employeesTable.create")
//    protected void onCreateEmployee(Action.ActionPerformedEvent event) {
//
//    }
}