package com.app.train.ui.list;

import com.app.train.backend.entity.Exercise;
import com.app.train.backend.entity.Train;
import com.app.train.backend.entity.User;
import com.app.train.backend.service.ExerciseService;
import com.app.train.backend.service.LevelOfStressService;
import com.app.train.backend.service.TrainService;
import com.app.train.backend.service.UserService;
import com.app.train.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.time.LocalTime;


@Route(value = "", layout = MainLayout.class)
@CssImport("./styles/shared-styles.css")
public class TrainList extends VerticalLayout {

    private final TrainService trainService;
    private final ExerciseService exerciseService;
    private final UserService userService;

    private final TrainForm trainForm;

    Grid<Train> trainGrid = new Grid<>(Train.class);
    TextField filterText = new TextField();

    public TrainList (TrainService trainService, ExerciseService exerciseService, UserService userService, LevelOfStressService levelOfStressService) {

        this.trainService = trainService;
        this.exerciseService = exerciseService;
        this.userService = userService;

        addClassName("train-view");
        setSizeFull();
        configureGrid();

        trainForm = new TrainForm(exerciseService.findAll(), levelOfStressService.findAll(), userService.findAll());
        trainForm.addListener(TrainForm.SaveEvent.class, this::saveContact);
        trainForm.addListener(TrainForm.DeleteEvent.class, this::deleteContact);
        trainForm.addListener(TrainForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(trainGrid, trainForm);
        content.setSizeFull();
        content.addClassName("content");

        add(getToolBar(), content);

        updateList();
        closeEditor();

    }

    private void configureGrid () {

        trainGrid.addClassName("train-grid");
        trainGrid.setSizeFull();

        trainGrid.removeColumnByKey("timeRecreation");
        trainGrid.removeColumnByKey("pulseStart");
        trainGrid.removeColumnByKey("pulseFinish");
        trainGrid.removeColumnByKey("pulseMax");
        trainGrid.removeColumnByKey("timeStart");
        trainGrid.removeColumnByKey("timeFinish");
        trainGrid.removeColumnByKey("powerReserve");
        trainGrid.removeColumnByKey("levelOfStress");
        trainGrid.removeColumnByKey("exercise");
        trainGrid.removeColumnByKey("idUser");
        trainGrid.setColumns("id", "set", "repeats", "weight", "date");

        trainGrid.addColumn(train -> {
            User user = userService.findById(train.getIdUser());
            return user == null ? new User() : user.getUsername();
        }).setHeader("idUser");
        trainGrid.addColumn(train -> {
            Exercise exercise = exerciseService.findById(train.getExercise());
            return exercise == null ? new Exercise() : exercise.getName();
        }).setHeader("exercise");
        trainGrid.addColumn(train -> {
            LocalTime timeStart = train.getTimeStart();
            return timeStart == null ? "-" : timeStart;
        }).setHeader("timeStart");
        trainGrid.addColumn(train -> {
            LocalTime timeFinish = train.getTimeFinish();
            return timeFinish == null ? "-" : timeFinish;
        }).setHeader("timeFinish");
        trainGrid.addColumn(train -> {
            String levelOfStress = train.getLevelOfStress().getName();
            return levelOfStress.isEmpty() ? "-" : levelOfStress;
        }).setHeader("levelOfStress");
        trainGrid.addColumn(Train::getTimeRecreation).setHeader("timeRecreation");
        trainGrid.addColumn(train -> {
            String pulseStart = String.valueOf(train.getPulseStart());
            return pulseStart.isEmpty() ? "-" : pulseStart;
        }).setHeader("pulseStart");
        trainGrid.addColumn(train -> {
            String pulseFinish = String.valueOf(train.getPulseFinish());
            return pulseFinish.isEmpty() ? "-" : pulseFinish;
        }).setHeader("pulseFinish");
        trainGrid.addColumn(train -> {
            String pulseMax = String.valueOf(train.getPulseMax());
            return pulseMax.isEmpty() ? "-" : pulseMax;
        }).setHeader("pulseMax");
        trainGrid.addColumn(train -> {
            String powerReserve = train.getPowerReserve();
            return powerReserve.isEmpty() ? "-" : powerReserve;
        }).setHeader("timeRecreation");

        trainGrid.getColumns().forEach(columns -> columns.setAutoWidth(true));

        trainGrid.asSingleSelect().addValueChangeListener(evt -> editTrain(evt.getValue()));

    }

    private void addTrain () {
        trainGrid.asSingleSelect().clear();
        Train train = new Train();
        editTrain(train);
    }

    private void editTrain (Train train) {

        if (train == null) {
            closeEditor();
        }
        else {
            trainForm.setTrain(train);
            trainForm.setVisible(true);
        }

    }

    private HorizontalLayout getToolBar () {

        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add Train", click -> addTrain());

        HorizontalLayout horizontalLayout = new HorizontalLayout(filterText, addContactButton);
        horizontalLayout.addClassName("toolbar");
        return horizontalLayout;

    }

    private void closeEditor () {
        trainForm.setTrain(null);
        trainForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        trainGrid.setItems(trainService.findAll(filterText.getValue()));
    }

    private void deleteContact (TrainForm.DeleteEvent evt) {

        trainService.delete(evt.getTrain());
        updateList();
        closeEditor();

    }

    private void saveContact (TrainForm.SaveEvent evt) {
        trainService.save(evt.getTrain());
        updateList();
        closeEditor();
    }

}
