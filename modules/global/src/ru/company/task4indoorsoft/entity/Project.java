package ru.company.task4indoorsoft.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.util.Set;

@Table(name = "PROJECT")
@Entity(name = "project")
@NamePattern("%s|name")
public class Project extends StandardEntity {
    private static final long serialVersionUID = 1217656651438539840L;

    @Column(name = "NAME", length = 100, nullable = false)
    protected String name;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "project")
    protected Set<ProjectEmployee> employeeSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProjectEmployee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<ProjectEmployee> employeeSet) {
        this.employeeSet = employeeSet;
    }
}