package ru.company.task4indoorsoft.web.screens.employee;

import com.haulmont.cuba.gui.screen.*;
import ru.company.task4indoorsoft.entity.Employee;

@UiController("Employee.browse")
@UiDescriptor("employee-browse.xml")
@LookupComponent("employeesTable")
@LoadDataBeforeShow
public class EmployeeBrowse extends StandardLookup<Employee> {
}