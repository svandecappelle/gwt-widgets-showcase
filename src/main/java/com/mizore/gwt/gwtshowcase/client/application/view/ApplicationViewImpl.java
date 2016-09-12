package com.mizore.gwt.gwtshowcase.client.application.view;

import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.mizore.gwt.gwtshowcase.client.application.interfaces.ApplicationDisplay;
import com.mizore.gwt.widgets.client.menu.MenuItem;
import com.mizore.gwt.widgets.client.menu.MenuWidget;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

public class ApplicationViewImpl extends SimplePanel implements ApplicationDisplay {

    private static final int MENU_WIDTH = 200;

    private FlowPanel containerApplication;
    private MenuWidget menu;

    private SimplePanel centeredWidget;

    public ApplicationViewImpl() {
        this.containerApplication = new FlowPanel();
        this.menu = new MenuWidget();
        this.menu.setLockerVisible(false);
        this.centeredWidget = new SimplePanel();

        menu.getElement().getStyle().setProperty("maxWidth", MENU_WIDTH, Unit.PX);
        menu.setWidth("65px");
        this.menu.getElement().getStyle().setPosition(Position.RELATIVE);
        menu.getElement().getStyle().setFloat(Float.LEFT);

        this.containerApplication.add(menu);
        this.containerApplication.add(centeredWidget);
        this.centeredWidget.getElement().getStyle().setOverflow(Overflow.AUTO);

        this.containerApplication.setSize("100%", "100%");
        this.setWidget(containerApplication);
        this.setSize("100%", "100%");
        this.menu.addMenuLockerClickHandler(new ClickHandler() {
            boolean open = false;

            @Override
            public void onClick(ClickEvent event) {
                open = !open;
                menu.setWidth(open ? "100%" : "65px");
            }
        });
    }

    @Override
    public HasClickHandlers addMenuItem(String itemTitle, String itemText) {
        MenuItem item = new MenuItem(itemTitle, itemText);
        menu.addItem(item);
        return item;
    }

    @Override
    public void setCenterWidget(WidgetDisplay display) {
        this.centeredWidget.setWidget(display.asWidget());
    }
}
