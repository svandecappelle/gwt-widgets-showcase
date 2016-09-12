package com.mizore.gwt.gwtshowcase.client.screen.tree.presenter;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mizore.gwt.gwtshowcase.client.screen.tree.interfaces.TreeWidgetDisplay;
import com.mizore.gwt.gwtshowcase.client.screen.tree.model.TreeWidgetModel;

@Singleton
public class TreeWidgetPresenter extends WidgetPresenter<TreeWidgetDisplay> {

	@Inject
	private TreeWidgetModel model;

	@Inject
	public TreeWidgetPresenter(TreeWidgetDisplay display, EventBus eventBus) {
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
