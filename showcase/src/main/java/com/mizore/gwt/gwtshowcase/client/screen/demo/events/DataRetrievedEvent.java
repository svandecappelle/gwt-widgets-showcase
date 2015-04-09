package com.mizore.gwt.gwtshowcase.client.screen.demo.events;

import com.google.gwt.event.shared.GwtEvent;

public class DataRetrievedEvent extends GwtEvent<DataRetrievedHandler> {

	private static Type<DataRetrievedHandler> type;
	private String result;

	public DataRetrievedEvent(String result) {
		this.result = result;
	}

	public static Type<DataRetrievedHandler> getType() {
		if (type == null) {
			type = new Type<DataRetrievedHandler>();
		}
		return type;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DataRetrievedHandler> getAssociatedType() {
		return getType();
	}

	@Override
	protected void dispatch(DataRetrievedHandler handler) {
		handler.onDataRetrieved(this);
	}

	public String getResult() {
		return result;
	}

}
