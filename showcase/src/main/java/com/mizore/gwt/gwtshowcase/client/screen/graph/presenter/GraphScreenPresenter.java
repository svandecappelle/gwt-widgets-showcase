package com.mizore.gwt.gwtshowcase.client.screen.graph.presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mizore.gwt.gwtshowcase.client.screen.graph.interfaces.GraphDisplay;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

//TODO montrer la difference entre singleton et non-singleton
@Singleton
public class GraphScreenPresenter extends WidgetPresenter<GraphDisplay> {

	@Inject
	public GraphScreenPresenter(GraphDisplay display, EventBus eventBus) {
		super(display, eventBus);
	}

	@Override
	protected void onBind() {
		// TODO Auto-generated method stub
		
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
