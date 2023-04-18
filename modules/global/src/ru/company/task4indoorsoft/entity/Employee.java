package ru.company.task4indoorsoft.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.Set;

@Table(name = "EMPLOYEE")
@Entity(name = "Employee")
@NamePattern("{0} {1.substring(0,1)}.{2?.substring(0,1)}|lastName,name,patronymic")
public class Employee extends StandardEntity {
    private static final long serialVersionUID = -2723083574940746946L;

    @Column(name = "LAST_NAME", nullable = false)
    protected String lastName;
    @Column(name = "NAME", nullable = false)
    protected String name;
    @Column(name = "PATRONYMIC")
    protected String patronymic;
    @ManyToMany(mappedBy = "employees")
    protected Set<Project> projects;

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

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}