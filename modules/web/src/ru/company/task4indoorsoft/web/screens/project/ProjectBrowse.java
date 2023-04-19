package ru.company.task4indoorsoft.web.screens.project;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.FluentLoader;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.CheckBoxGroup;
import com.haulmont.cuba.gui.components.EntityCombinedScreen;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.Subscribe;
import ru.company.task4indoorsoft.entity.Employee;
import ru.company.task4indoorsoft.entity.Project;
import ru.company.task4indoorsoft.entity.ProjectEmployee;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProjectBrowse extends EntityCombinedScreen {

    @Inject
    private DataManager dataManager;

    @Inject
    private CheckBoxGroup<Employee> checkBoxGroup;

    @Inject
    private GroupTable<Project> table;

    private List<Employee> allEmployees;
    private List<Employee> cachedParticipants;
    private Project currentProject;
    private final Logger logger = Logger.getLogger("ProjectBrowseLogger");

    @Subscribe
    protected void onInit(InitEvent event) {
        logger.info("onInit() start");
        allEmployees = getAllEmployees();
        checkBoxGroup.setOptionsList(allEmployees);
        checkBoxGroup.setEditable(false);
        checkBoxGroup.setVisible(false);
        table.addSelectionListener(listener -> onSelect());
        logger.info("onInit() end");
    }

    private List<Employee> getAllEmployees() {
        return dataManager.load(Employee.class).list();
    }

    @Subscribe("refreshBtn")
    protected void onRefreshButtonClick(Button.ClickEvent event) {
        allEmployees = getAllEmployees();
        checkBoxGroup.setOptionsList(allEmployees);
        logger.info("data has been updated");
    }

    @Subscribe("editBtn")
    protected void onEditButtonClick(Button.ClickEvent event) {
        checkBoxGroup.setEditable(true);

        if (cachedParticipants == null) {
            cachedParticipants = getParticipantsInCurrentProject();
        }

        logger.info("editing project: " + currentProject);
    }

    @Subscribe("cancelBtn")
    protected void onCancelButtonClick(Button.ClickEvent event) {
        checkBoxGroup.setValue(cachedParticipants);
        checkBoxGroup.setEditable(false);
    }

    @Subscribe("saveBtn")
    protected synchronized void onSaveButtonClick(Button.ClickEvent event) {
        checkBoxGroup.setEditable(false);
        Collection<Employee> editedParticipants = checkBoxGroup.getValue();

        if (editedParticipants == null) {
            cachedParticipants.stream()
                    .map(this::findCurrentProjectEmployeeByEmployee)
//                    .flatMap(List::stream)
                    .forEach(pe -> dataManager.remove(pe));
        } else if (!(editedParticipants.containsAll(cachedParticipants) && cachedParticipants.containsAll(editedParticipants))) {
            // adding
            editedParticipants.stream().filter(e -> !cachedParticipants.contains(e)).collect(Collectors.toList())
                    .forEach(e -> {
                        ProjectEmployee projectEmployee = dataManager.create(ProjectEmployee.class);
                        projectEmployee.setProject(currentProject);
                        projectEmployee.setEmployee(e);
                        dataManager.commit(projectEmployee);
                        logger.info("participation added: " + projectEmployee);
                    });
            // removal
            cachedParticipants.stream().filter(e -> !editedParticipants.contains(e)).collect(Collectors.toList())
                    .forEach(e -> {
                        ProjectEmployee pe = findCurrentProjectEmployeeByEmployee(e);
                        dataManager.remove(pe);
                        logger.info("participation deleted: " + pe);
                    });
        } else {
            logger.info("nothing has been changed");
        }
    }

    private void onSelect() {
        Project project = table.getSingleSelected();

        if (project != null) {
            logger.info("selected project " + project.getName());
            currentProject = project;
            List<Employee> participants = getParticipantsInCurrentProject();
            checkBoxGroup.setValue(participants);
            cacheSelectedProjectParticipantsBeforeEditing(participants.stream().filter(Objects::nonNull).collect(Collectors.toList()));
            checkBoxGroup.setVisible(true);
        }
    }

    private List<Employee> getParticipantsInCurrentProject() {
        return dataManager.load(ProjectEmployee.class)
                .query("select e from project_employee e where e.project.id = :id")
                .parameter("id", currentProject.getId())
                .list()
                .stream().map(this::findEmployeeByProjectEmployee)
                .collect(Collectors.toList());
    }

    private Employee findEmployeeByProjectEmployee(ProjectEmployee pe) {
        return allEmployees.stream().filter(e -> pe.getEmployee().equals(e)).findFirst().orElse(null);
    }

    // for physical deletion from db
    private ProjectEmployee findCurrentProjectEmployeeByEmployee(Employee e) {
        return getProjectEmployeeLoader(e).one();
    }

    private FluentLoader.ByQuery<ProjectEmployee, UUID> getProjectEmployeeLoader(Employee e) {
        return dataManager.load(ProjectEmployee.class)
                .query("select e from project_employee e where e.employee.id = :eid and e.project.id = :pid")
                .parameter("eid", e.getId())
                .parameter("pid", currentProject.getId());
    }

    // for soft deletion from db
    @SuppressWarnings("unused")
    private List<ProjectEmployee> findCurrentProjectEmployeesByEmployee(Employee e) {
        return getProjectEmployeeLoader(e).list();
    }

    private void cacheSelectedProjectParticipantsBeforeEditing(List<Employee> participants) {
        cachedParticipants = participants;
    }
}