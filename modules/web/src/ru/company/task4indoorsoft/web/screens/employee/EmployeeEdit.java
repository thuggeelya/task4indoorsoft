package ru.company.task4indoorsoft.web.screens.employee;

import com.haulmont.cuba.gui.screen.*;
import ru.company.task4indoorsoft.entity.Employee;

@UiController("Employee.edit")
@UiDescriptor("employee-edit.xml")
@EditedEntityContainer("employeeDc")
@LoadDataBeforeShow
public class EmployeeEdit extends StandardEditor<Employee> {
}