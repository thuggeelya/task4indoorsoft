package ru.company.task4indoorsoft.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.util.Set;

@Table(name = "EMPLOYEE")
@Entity(name = "Employee")
@NamePattern("%s %s|name,lastName")
public class Employee extends StandardEntity {
    private static final long serialVersionUID = -2723083574940746946L;

    @Column(name = "LAST_NAME", length = 100, nullable = false)
    protected String lastName;
    @Column(name = "NAME", length = 50, nullable = false)
    protected String name;
    @Column(name = "PATRONYMIC")
    protected String patronymic;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "employee")
    protected Set<ProjectEmployee> projectsSet;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Set<ProjectEmployee> getProjectsSet() {
        return projectsSet;
    }

    public void setProjectsSet(Set<ProjectEmployee> projectsSet) {
        this.projectsSet = projectsSet;
    }
}