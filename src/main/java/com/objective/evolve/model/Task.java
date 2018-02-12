package com.objective.evolve.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Document(collection = "task")
@TypeAlias("task")
public class Task implements Serializable {

    private static final long serialVersionUID = 8860980640517255440L;

    @Id
    private String id;
    @NotNull
    private String name;
    private List<String> assignments = new ArrayList<>();

    protected Task() {
    }

    public Task(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<String> getAssignments() {
        return Collections.unmodifiableList(assignments);
    }

    public void setAssignments(List<String> assignments) {
        this.assignments = assignments;
    }
}
