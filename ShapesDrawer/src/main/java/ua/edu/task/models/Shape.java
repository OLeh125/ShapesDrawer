package ua.edu.task.models;

import java.util.List;

public class Shape {
    private Integer id;
    private String name;
    private List<Position> positions;

    public Shape() {
    }

    public Shape(String name) {
        this.name = name;
    }

    public Shape(String name, List<Position> positions) {
        this.name = name;
        this.positions = positions;
    }

    public Shape(Integer id, String name, List<Position> positions) {
        this.id = id;
        this.name = name;
        this.positions = positions;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
