package com.example.application.views.helloworld;

import java.util.Objects;

public class Project {

    private String name;

    public Project(String name){this.name = name;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Getter for name
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }
}
