package com.mizore.gwt.gwtshowcase.client.screen.demo.presenter;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.mizore.gwt.gwtshowcase.client.screen.demo.events.DataRetrievedEvent;
import com.mizore.gwt.gwtshowcase.client.screen.demo.events.DataRetrievedHandler;
import com.mizore.gwt.gwtshowcase.client.screen.demo.interfaces.DemoScreenDisplay;
import com.mizore.gwt.gwtshowcase.client.screen.demo.model.DemoScreenModel;

public class DemoScreenPresenter extends WidgetPresenter<DemoScreenDisplay> {

	@Inject
	private DemoScreenModel model;
	
	@Inject
	public DemoScreenPresenter(DemoScreenDisplay display, EventBus eventBus) {
		super(display, eventBus);
	}

	@Override
	protected void onBind() {
		registerHandler(eventBus.addHandler(DataRetrievedEvent.getType(), new DataRetrievedHandler() {
			
			@Override
			public void onDataRetrieved(DataRetrievedEvent event) {
				Window.alert(event.getResult());
			}
		}));
		
		registerHandler(display.getChangedHandler().addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				model.getData();
			}
		}));
		
	}

	@Override
	protected void onUnbind() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onRevealDisplay() {
		// TODO Auto-generated method stub

	}

}
