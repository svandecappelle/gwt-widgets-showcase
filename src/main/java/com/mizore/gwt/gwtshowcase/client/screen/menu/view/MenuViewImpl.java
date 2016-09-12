package com.mizore.gwt.gwtshowcase.client.screen.menu.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;
import com.mizore.gwt.gwtshowcase.client.screen.menu.interfaces.MenuDisplay;
import com.mizore.gwt.widgets.client.WidgetsBundle;
import com.mizore.gwt.widgets.client.menu.DisclosureMenu;

public class MenuViewImpl extends DisclosureMenu implements MenuDisplay {

    @Inject
    public MenuViewImpl(WidgetsBundle resourcesWidgets) {
        setWidth("100px");
        setHeight("100%");

        resourcesWidgets.menu().ensureInjected();
        addStyleName(resourcesWidgets.menu().disclosureMenu());

        getLockMenu().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (getLockMenu().isActive()) {
                    setWidth("100px");
                }
                else {
                    setWidth("58px");
                }
            }
        });

        SimplePanel panel = new SimplePanel();
        panel.setSize("100%", "100px");
        panel.getElement().getStyle().setBackgroundColor("blue");

        addToContainer(panel);
    }
}
