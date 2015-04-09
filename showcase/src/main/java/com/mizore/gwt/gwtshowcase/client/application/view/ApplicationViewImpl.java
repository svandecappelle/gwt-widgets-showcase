package com.mizore.gwt.gwtshowcase.client.application.view;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.SimplePanel;
import com.mizore.gwt.gwtshowcase.client.application.interfaces.ApplicationDisplay;
import com.mizore.gwt.widgets.client.layout.LayoutVisiblePanel;
import com.mizore.gwt.widgets.client.menu.MenuItem;
import com.mizore.gwt.widgets.client.menu.MenuWidget;

public class ApplicationViewImpl extends SimplePanel implements
		ApplicationDisplay {

	private static final int MENU_WIDTH = 200;

	private static final int LOCKER_WIDTH = 58;

	private LayoutVisiblePanel containerApplication;
	private MenuWidget menu;

	private SimplePanel centeredWidget;

	public ApplicationViewImpl() {
		this.containerApplication = new LayoutVisiblePanel();
		this.menu = new MenuWidget();
		this.centeredWidget = new SimplePanel();

		this.containerApplication.add(menu);
		this.containerApplication.add(centeredWidget);

		this.containerApplication.setWidgetTopBottom(menu, 0, Unit.PX, 0,
				Unit.PX);
		this.containerApplication.setWidgetLeftWidth(menu, 0, Unit.PX,
				MENU_WIDTH, Unit.PX);

		this.containerApplication.setWidgetTopBottom(centeredWidget, 0,
				Unit.PX, 0, Unit.PX);
		this.containerApplication.setWidgetLeftRight(centeredWidget,
				MENU_WIDTH + LOCKER_WIDTH, Unit.PX, 0, Unit.PX);
		
		this.containerApplication.setSize("100%", "100%");
		this.setWidget(containerApplication);
		this.setSize("100%", "100%");
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
