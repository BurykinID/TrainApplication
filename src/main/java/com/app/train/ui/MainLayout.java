package com.app.train.ui;

import com.app.train.ui.list.TrainList;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout () {

        createDrawer();

    }

    private void createDrawer () {

        RouterLink trainLink = new RouterLink("Train List", TrainList.class);
        trainLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(trainLink);

    }
}
