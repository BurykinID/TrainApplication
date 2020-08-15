package com.app.train.ui.mainInfo;

import com.app.train.backend.entity.Exercise;
import com.app.train.backend.entity.LevelOfStress;
import com.app.train.backend.entity.Train;
import com.app.train.backend.entity.User;
import com.app.train.backend.service.TrainService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.shared.Registration;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TrainForm extends FormLayout {


    private final TrainService trainService;

    /*block of special value
     * These block contains Exercise and Level of Stress value and User id*/
    ComboBox<Exercise> exercise = new ComboBox<>("Упражнение");
    ComboBox<LevelOfStress> levelOfStress = new ComboBox<>("Уровень стресса");
    ComboBox<User> idUser = new ComboBox<>("Пользователь");
    // end special value block
    /* block of numeric value
     * These block contains info about numeric characteristic. Weight, Set, Repeat,
     * Pulse Start, Pulse Finish, Max Pulse, Time recreation, */
    NumberField set = new NumberField("Подход");
    NumberField repeats = new NumberField("Количество повторений");
    NumberField weight = new NumberField("Вес");
    NumberField timeRecreation = new NumberField("Время отдыха");
    NumberField pulseStart = new NumberField("Начальный пульс");
    NumberField pulseFinish = new NumberField("Пульс в конце выполнения упражнения");
    NumberField pulseMax = new NumberField("Максимальный пульс");
    // end numeric block
    /* block of string values
     * These block contains info about */
    TextField powerReserve = new TextField("Резерв");
    /* block of time
        These block contains time value. Time start, Time Finish*/
    TimePicker timeStart = new TimePicker("Время начала выполнения упражнения");
    TimePicker timeFinish = new TimePicker("Время окончания выполнения упражнения");
    // end time's block
    /*block of date
     * These block contains date Train*/
    DatePicker date = new DatePicker("День тренировки");
    // end date's block
    /*block of buttons
     * These block contains button for save, delete and close Train*/
    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Скрыть");
    Button duplicateExercise = new Button("Дублировать");
    /*Button duplicatePrevious*/
    // end buttons block
    Binder<Train> binder = new BeanValidationBinder<>(Train.class);

    public TrainForm (List<Exercise> exerciseList, List<LevelOfStress> levelOfStressList, List<User> userList, TrainService trainService) {

        this.trainService = trainService;

        addClassName("train-form");

        binder.forField(date)
                .withNullRepresentation(LocalDate.now());

        binder.forField(timeStart)
                .withNullRepresentation(LocalTime.now())
                .bind(Train::getTimeStart,
                Train::setTimeStart);

        binder.forField(timeFinish)
                .withNullRepresentation(LocalTime.now())
                .bind(Train::getTimeFinish,
                Train::setTimeFinish);

        binder.forField(set)
                .withNullRepresentation(0.0)
                .withConverter(new Converter<Double, Integer>() {
                    @Override
                    public Result<Integer> convertToModel (Double aDouble, ValueContext valueContext) {

                        int value;

                        if (aDouble == null) {
                            value = 0;
                        }
                        else {
                            value = aDouble.intValue();
                        }

                        return Result.ok(value);
                    }

                    @Override
                    public Double convertToPresentation (Integer i, ValueContext valueContext) {

                        double val;

                        if (i == null) {
                            val = 0.0;
                        }
                        else {
                            val = Double.parseDouble(String.valueOf(i));
                        }

                        return val;
                    }
                })
                .bind(Train::getSet,
                Train::setSet);

        binder.forField(repeats)
                .withNullRepresentation(0.0)
                .withConverter(new Converter<Double, Integer>() {
                    @Override
                    public Result<Integer> convertToModel (Double aDouble, ValueContext valueContext) {

                        int value;

                        if (aDouble == null) {
                            value = 0;
                        }
                        else {
                            value = aDouble.intValue();
                        }

                        return Result.ok(value);
                    }

                    @Override
                    public Double convertToPresentation (Integer i, ValueContext valueContext) {

                        double val;

                        if (i == null) {
                            val = 0.0;
                        }
                        else {
                            val = Double.parseDouble(String.valueOf(i));
                        }

                        return val;
                    }
                })
                .bind(Train::getRepeats,
                Train::setRepeats);

        binder.forField(pulseStart)
                .withNullRepresentation(0.0)
                .withConverter(new Converter<Double, Integer>() {
                    @Override
                    public Result<Integer> convertToModel (Double aDouble, ValueContext valueContext) {

                        int value;

                        if (aDouble == null) {
                            value = 0;
                        }
                        else {
                            value = aDouble.intValue();
                        }

                        return Result.ok(value);
                    }

                    @Override
                    public Double convertToPresentation (Integer i, ValueContext valueContext) {

                        double val;

                        if (i == null) {
                            val = 0.0;
                        }
                        else {
                            val = Double.parseDouble(String.valueOf(i));
                        }

                        return val;
                    }
                })
                .bind(Train::getPulseStart,
                        Train::setPulseStart);

        binder.forField(pulseFinish)
                .withNullRepresentation(0.0)
                .withConverter(new Converter<Double, Integer>() {
                    @Override
                    public Result<Integer> convertToModel (Double aDouble, ValueContext valueContext) {

                        int value;

                        if (aDouble == null) {
                            value = 0;
                        }
                        else {
                            value = aDouble.intValue();
                        }

                        return Result.ok(value);
                    }

                    @Override
                    public Double convertToPresentation (Integer i, ValueContext valueContext) {

                        double val;

                        if (i == null) {
                            val = 0.0;
                        }
                        else {
                            val = Double.parseDouble(String.valueOf(i));
                        }

                        return val;
                    }
                })
                .bind(Train::getPulseFinish,
                        Train::setPulseFinish);

        binder.forField(pulseMax)
                .withNullRepresentation(0.0)
                .withConverter(new Converter<Double, Integer>() {
                    @Override
                    public Result<Integer> convertToModel (Double aDouble, ValueContext valueContext) {

                        int value;

                        if (aDouble == null) {
                            value = 0;
                        }
                        else {
                            value = Integer.parseInt(String.valueOf(aDouble));
                        }

                        return Result.ok(value);
                    }

                    @Override
                    public Double convertToPresentation (Integer i, ValueContext valueContext) {

                        double val;

                        if (i == null) {
                            val = 0.0;
                        }
                        else {
                            val = Double.parseDouble(String.valueOf(i));
                        }

                        return val;
                    }
                })
                .bind(Train::getPulseMax,
                        Train::setPulseMax);

        binder.forField(exercise)
                .bind(Train::getExercise,
                        Train::setExercise);

        binder.forField(levelOfStress)
                .bind(Train::getLevelOfStress,
                        Train::setLevelOfStress);

        binder.forField(idUser)
                .bind(Train::getIdUser,
                        Train::setIdUser);

        binder.bindInstanceFields(this);


        exercise.setItems(exerciseList);
        exercise.setItemLabelGenerator(Exercise::getName);
        levelOfStress.setItems(levelOfStressList);
        levelOfStress.setItemLabelGenerator(LevelOfStress::getName);
        idUser.setItems(userList);
        idUser.setItemLabelGenerator(User::getUsername);

        configureTimePickers();
        configureNumberFields();

        add(new VerticalLayout(configureForm(),
                createButtonsLayout()));

    }

    public Component configureForm() {

        HorizontalLayout infoAboutWeight = new HorizontalLayout();
        infoAboutWeight.add(set, repeats, weight);

        HorizontalLayout infoAboutPulse = new HorizontalLayout();
        infoAboutPulse.add(pulseStart, pulseFinish, pulseMax);

        HorizontalLayout infoAboutTime = new HorizontalLayout();
        infoAboutTime.add(timeStart, timeFinish, timeRecreation);

        HorizontalLayout infoAboutUser = new HorizontalLayout();
        infoAboutUser.add(idUser, date);

        HorizontalLayout infoAboutStress = new HorizontalLayout();
        infoAboutStress.add(levelOfStress, powerReserve);

        return new VerticalLayout(infoAboutUser, exercise, infoAboutWeight, infoAboutTime, infoAboutPulse, infoAboutStress);

    }

    private void configureTimePickers () {
        timeStart.setStep(Duration.ofSeconds(1));
        timeFinish.setStep(Duration.ofSeconds(1));
    }

    private void configureNumberFields() {

        setNumericField(set);
        setNumericField(repeats);
        setNumericField(weight);
        setNumericField(timeRecreation);
        setNumericField(pulseStart);
        setNumericField(pulseFinish);
        setNumericField(pulseMax);

    }

    public void setNumericField(NumberField object) {
        object.setClearButtonVisible(true);
        object.setSizeFull();
        object.setStep(1);
    }

    public void setTrain(Train train) {
        binder.setBean(train);
    }

    private Component createButtonsLayout () {

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));
        duplicateExercise.addClickListener(click -> createDuplicate());

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, duplicateExercise, delete, close);
    }

    private void createDuplicate () {
        if (binder.isValid()) {
            Train train = binder.getBean();
            train.setId(null);
            train = getLastTrainValue(train);
            binder.setBean(train);
            clearPlaceholderField();
        }
    }

    private void clearPlaceholderField () {
        repeats.clear();
        weight.clear();
        timeRecreation.clear();
        /*pulseStart.clear();
        pulseFinish.clear();
        pulseMax.clear();*/
        levelOfStress.clear();
    }
    // this method will setup value where was at the last train in this set and this user in this exercise
    private Train getLastTrainValue (Train train) {

        /* completed    проставлять дату
        *  completed    проставлять уровень стресса
        *  completed    проставлять количество повторов которое было в прошлый раз в этом подходе (P.S. в виде плейсхолдера)
        *  completed    проставить вес, который был в прошлый раз на этом подходе у этого пользователя (P.S placeholder)*/

        train.setIdUser(idUser.getValue());

        User idUser = train.getIdUser();
        String nameExercise = train.getExercise().getName();

        Date newDate = new Date();

        date.setValue(newDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        Calendar today = Calendar.getInstance();
        today = setCalendar(today, newDate, "start");
        LocalDate todayLocal = today.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        set.setValue(trainService.findSet(idUser, nameExercise, todayLocal) + 1.0);

        today.add(Calendar.DATE, -1);

        LocalDate yesterday = today.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;

        int exerciseSet = train.getSet();

        repeats.setPlaceholder(String.valueOf(trainService.findRepeats(idUser, nameExercise, yesterday, exerciseSet)));
        weight.setPlaceholder(String.valueOf(trainService.findWeight(idUser, nameExercise, yesterday, exerciseSet)));
        levelOfStress.setPlaceholder(trainService.findLevelOfStress(idUser, nameExercise, yesterday, exerciseSet));
        timeRecreation.setPlaceholder(String.valueOf(trainService.findTimeRecreation(idUser, nameExercise, yesterday, exerciseSet)));

        train = setClearValueInBinder(train);

        return train;
    }
    // this method will setup start and finish day when user did exercises
    public Calendar setCalendar(Calendar calendar, Date date, String index) {

        calendar.setTime(date);

        if (index.equals("start")) {
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
        }

        else if (index.equals("end")) {
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
        }

        return calendar;
    }

    public Train setClearValueInBinder (Train train) {
        train.setSet(set.getValue().intValue());
        train.setRepeats(0);
        train.setWeight(0);
        train.setTimeRecreation(0);
        train.setPulseStart(0);
        train.setPulseFinish(0);
        train.setPulseMax(0);
        train.setLevelOfStress(null);
        return train;
    }

    private void validateAndSave () {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    public static abstract class TrainFormEvent extends ComponentEvent<TrainForm> {
        private Train train;

        protected TrainFormEvent(TrainForm source, Train train) {
            super(source, false);
            this.train = train;
        }

        public Train getTrain() {
            return train;
        }
    }

    public static class SaveEvent extends TrainFormEvent {
        SaveEvent(TrainForm source, Train train) {
            super(source, train);
        }
    }

    public static class DeleteEvent extends TrainFormEvent {
        DeleteEvent(TrainForm source, Train train) {
            super(source, train);
        }
    }

    public static class CloseEvent extends TrainFormEvent {
        CloseEvent(TrainForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
