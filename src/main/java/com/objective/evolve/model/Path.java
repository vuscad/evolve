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

@Document(collection = "path")
@TypeAlias("path")
public class Path implements Serializable {

    private static final long serialVersionUID = 8860980640517255440L;

    @Id
    private String id;
    @NotNull
    private String name;
    private List<Domain> domains = new ArrayList<>();

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

    public List<Domain> getDomains() {
        return Collections.unmodifiableList(domains);
    }
}
