package com.mizore.gwt.gwtshowcase.client.screen.dnd.datagrid.presenter;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mizore.gwt.gwtshowcase.client.screen.dnd.datagrid.interfaces.DnDataGridDisplay;
import com.mizore.gwt.gwtshowcase.client.screen.dnd.datagrid.model.DnDataGridModel;

@Singleton
public class DnDataGridPresenter extends WidgetPresenter<DnDataGridDisplay> {

	@Inject
	private DnDataGridModel model;

	@Inject
	public DnDataGridPresenter(DnDataGridDisplay display, EventBus eventBus) {
		super(display, eventBus);

	}

	@Override
	protected void onBind() {
		// TODO Insert here your events. 
	}

	@Override
	protected void onUnbind() {
		// TODO Unbind here your events.

	}

	@Override
	protected void onRevealDisplay() {
		// TODO Reveal your display & datas
	}

}
