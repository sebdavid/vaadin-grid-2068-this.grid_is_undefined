package com.example.application.views.helloworld;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class CalendarRow {

    private String name;

    private LinkedHashMap<LocalDate, Integer> cells;

    public CalendarRow() {
    }

    public CalendarRow(String name, LinkedHashMap<LocalDate, Integer> cells) {
        this.name = name;
        this.cells = cells;
    }

    /**
     * Getter for name
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     *
     * @param name the value to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for cells
     *
     * @return value of cells
     */
    public LinkedHashMap<LocalDate, Integer> getCells() {
        return cells;
    }

    /**
     * Setter for cells
     *
     * @param cells the value to set
     */
    public void setCells(LinkedHashMap<LocalDate, Integer> cells) {
        this.cells = cells;
    }
}
