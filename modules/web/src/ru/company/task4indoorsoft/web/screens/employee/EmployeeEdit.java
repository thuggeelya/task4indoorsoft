package ru.company.task4indoorsoft.web.screens.employee;

import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import ru.company.task4indoorsoft.entity.Employee;

import javax.inject.Inject;

@UiController("task4indoorsoft_EmployeeEdit")
@UiDescriptor("employee-edit.xml")
@EditedEntityContainer("employeeDc")
@LoadDataBeforeShow
public class EmployeeEdit extends StandardEditor<Employee> {

    @Inject
    protected ScreenBuilders screenBuilders;

    @Inject
    protected Metadata metadata;

    @Inject
    protected CollectionContainer<Employee> employeeDc;
}