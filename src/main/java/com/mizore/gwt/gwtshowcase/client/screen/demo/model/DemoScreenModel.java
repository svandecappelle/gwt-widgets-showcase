package com.mizore.gwt.gwtshowcase.client.screen.demo.model;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.mizore.gwt.gwtshowcase.client.GreetingServiceAsync;
import com.mizore.gwt.gwtshowcase.client.screen.demo.events.DataRetrievedEvent;

import net.customware.gwt.presenter.client.EventBus;

public class DemoScreenModel {
	
	@Inject
	private EventBus eventBus;

	@Inject
	private GreetingServiceAsync service;
	
	public DemoScreenModel() {

	}
	
	public void getData() {
		service.greetServer("steeve", new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				eventBus.fireEvent(new DataRetrievedEvent(result));
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
