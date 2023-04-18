package ru.company.task4indoorsoft.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.Set;

@Table(name = "PROJECT")
@Entity(name = "project")
@NamePattern("%s|name")
public class Project extends StandardEntity {
    private static final long serialVersionUID = 1217656651438539840L;

    @Column(name = "NAME", nullable = false)
    protected String name;
    @JoinTable(name = "EMPLOYEE",
            joinColumns = @JoinColumn(name = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID"))
    @ManyToMany
    protected Set<Employee> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}