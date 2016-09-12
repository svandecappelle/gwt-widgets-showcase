package com.mizore.gwt.gwtshowcase.client.screen.menu.presenter;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mizore.gwt.gwtshowcase.client.screen.menu.interfaces.MenuDisplay;
import com.mizore.gwt.gwtshowcase.client.screen.menu.model.MenuModel;

@Singleton
public class MenuPresenter extends WidgetPresenter<MenuDisplay> {

	@Inject
	private MenuModel model;

	@Inject
	public MenuPresenter(MenuDisplay display, EventBus eventBus) {
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
