package com.app.train.ui;

import com.app.train.ui.mainInfo.TrainList;
import com.app.train.ui.uploadData.UploadDataWithFile;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout () {

        H5 menu = new H5("Меню");
        menu.addClassName("menu");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), menu);
        header.expand(menu);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");

        addToNavbar(header);
        createDrawer();

    }

    private void createDrawer () {

        RouterLink trainLink = new RouterLink("Список тренировок", TrainList.class);
        RouterLink uploadTrainDataLink = new RouterLink("Загрузить информацию о тренировках", UploadDataWithFile.class);

        trainLink.setHighlightCondition(HighlightConditions.sameLocation());
        uploadTrainDataLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(trainLink, uploadTrainDataLink));

    }
}
