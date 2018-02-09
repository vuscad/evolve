package com.objective.evolve.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Document(collection = "path")
@TypeAlias("path")
public class Path implements Serializable {

    private static final long serialVersionUID = 8860980640517255440L;

    @Id
    private String id;
    @NotNull
    private String name;

    protected Path() {
    }

    public Path(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
