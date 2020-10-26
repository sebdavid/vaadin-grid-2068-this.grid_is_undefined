package com.example.application.views.helloworld;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Route(value = "hello", layout = MainView.class)
@PageTitle("Hello World")
@CssImport("./styles/views/helloworld/hello-world-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class HelloWorldView extends VerticalLayout {

    private Grid<Project> projects;
    private Grid<CalendarRow> calendar;

    private LinkedHashMap<Project, List<CalendarRow>> data;
    private List<LocalDate> days = Arrays.asList(
            LocalDate.of(2020, 10, 01),
            LocalDate.of(2020, 10, 02),
            LocalDate.of(2020, 10, 03)
    );


    public HelloWorldView() {
        setId("hello-world-view");


        data = new LinkedHashMap<>();
        data.put(new Project("Project 1"), Collections.emptyList());
        data.put(new Project("Project 2"), Arrays.asList(
                new CalendarRow("John Doe", new LinkedHashMap<LocalDate, Integer>(){{
                    put(LocalDate.of(2020, 10, 01), 5);
                    put(LocalDate.of(2020, 10, 02), 2);
                    put(LocalDate.of(2020, 10, 03), null);
                }}),

                new CalendarRow("Jane Doe", new LinkedHashMap<LocalDate, Integer>(){{
                    put(LocalDate.of(2020, 10, 01), null);
                    put(LocalDate.of(2020, 10, 02), 8);
                    put(LocalDate.of(2020, 10, 03), 8);
                }})
        ));

        // Master table: list of projects
        projects = new Grid<>();
        projects.setSelectionMode(Grid.SelectionMode.SINGLE);
        projects.addSelectionListener(this::selectItemFromMaster);
        projects.addColumn(Project::getName).setHeader("Project name");
        projects.setItems(data.keySet());

        // Detail table: timesheet for the selected project
        calendar = new Grid<>();
        calendar.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS);
        calendar.setSelectionMode(Grid.SelectionMode.NONE);


        // Select the first project item
        //projects.select(new Project("Project 1"));


        add(projects, calendar);
    }


    private void selectItemFromMaster(SelectionEvent<Grid<Project>, Project> event){

        calendar.removeAllColumns();

        // Get the data to display
        Project selectedProject = null;
        if(event.getFirstSelectedItem().isPresent()){
            selectedProject = event.getFirstSelectedItem().get();
        }

        List<CalendarRow> items = null;
        if(selectedProject != null){
            items = data.get(selectedProject);
        }

        if(items == null){
            items = Collections.emptyList();
        }

        //
        // ADD COLUMNS
        //

        // 1st column is static
        calendar
                .addColumn(CalendarRow::getName)
                .setHeader("Name")
                .setKey("name_col")
                .setFrozen(true);

        // other columns are dynamic
        if(!items.isEmpty()) {
            days.forEach(d -> {
                calendar
                        .addColumn(row -> row.getCells().get(d))
                        .setHeader(d.format(DateTimeFormatter.ISO_DATE));
            });
        }

        calendar.setItems(items);
    }


    private static String buildColumnKey(LocalDate day){
        return day.format(DateTimeFormatter.ISO_DATE);
    }
}
