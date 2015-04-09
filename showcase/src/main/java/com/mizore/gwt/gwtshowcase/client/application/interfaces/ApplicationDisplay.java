package com.mizore.gwt.gwtshowcase.client.application.interfaces;

import com.google.gwt.event.dom.client.HasClickHandlers;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

public interface ApplicationDisplay extends WidgetDisplay{

	HasClickHandlers addMenuItem(String itemTitle, String itemText);

	void setCenterWidget(WidgetDisplay display);

}
