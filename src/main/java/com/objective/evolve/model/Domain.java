package com.objective.evolve.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Document(collection = "domain")
@TypeAlias("domain")
public class Domain implements Serializable {

    private static final long serialVersionUID = 8860980640517255440L;

    @Id
    private String id;
    @NotNull
    private String name;
    @DBRef
    private List<Task> tasks = new ArrayList<>();

    protected Domain() {}

    public Domain(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
