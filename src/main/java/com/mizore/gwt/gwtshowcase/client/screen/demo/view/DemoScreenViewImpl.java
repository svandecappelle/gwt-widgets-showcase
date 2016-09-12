package com.mizore.gwt.gwtshowcase.client.screen.demo.view;

import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.mizore.gwt.gwtshowcase.client.screen.demo.interfaces.DemoScreenDisplay;
import com.mizore.gwt.widgets.client.dropdown.DropDownWidget;
import com.mizore.gwt.widgets.client.dropdown.ListItemWidget;
import com.mizore.gwt.widgets.client.dropdown.events.ValueChangedEvent;
import com.mizore.gwt.widgets.client.dropdown.events.ValueChangedHandler;

public class DemoScreenViewImpl extends FlowPanel implements DemoScreenDisplay {

	private TextArea widget;
	private DropDownWidget<Integer> list;

	public DemoScreenViewImpl() {
		this.widget = new TextArea();
		this.list = new DropDownWidget<Integer>("DropDown");
		this.list.addItem(new ListItemWidget<Integer>("a", 1));

		this.list.addValueChangedHandler(new ValueChangedHandler<Integer>() {
			@Override
			public void onValueChanged(ValueChangedEvent<Integer> event) {
				Window.alert("Selected : " + event.getValue());
			}

		});
		this.widget.setWidth("100%");

		this.add(list);
		this.add(widget);
	}

	@Override
	public HasChangeHandlers getChangedHandler() {
		return widget;
	}

}
