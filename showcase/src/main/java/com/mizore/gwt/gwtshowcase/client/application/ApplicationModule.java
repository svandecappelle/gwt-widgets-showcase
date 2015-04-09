package com.mizore.gwt.gwtshowcase.client.application;

import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.inject.client.AbstractGinModule;
import com.mizore.gwt.gwtshowcase.client.application.interfaces.ApplicationDisplay;
import com.mizore.gwt.gwtshowcase.client.application.view.ApplicationViewImpl;
import com.mizore.gwt.gwtshowcase.client.authentication.interfaces.AuthenticationDisplay;
import com.mizore.gwt.gwtshowcase.client.authentication.view.AuthenticationViewImpl;
import com.mizore.gwt.gwtshowcase.client.screen.demo.interfaces.DemoScreenDisplay;
import com.mizore.gwt.gwtshowcase.client.screen.demo.view.DemoScreenViewImpl;
import com.mizore.gwt.gwtshowcase.client.screen.dnd.datagrid.interfaces.DnDataGridDisplay;
import com.mizore.gwt.gwtshowcase.client.screen.dnd.datagrid.view.DnDataGridViewImpl;
import com.mizore.gwt.gwtshowcase.client.screen.graph.interfaces.GraphDisplay;
import com.mizore.gwt.gwtshowcase.client.screen.graph.view.GraphViewImpl;
import com.mizore.gwt.gwtshowcase.client.screen.menu.interfaces.MenuDisplay;
import com.mizore.gwt.gwtshowcase.client.screen.menu.view.MenuViewImpl;

public class ApplicationModule extends AbstractGinModule{

	@Override
	protected void configure() {
		bind(EventBus.class).to(DefaultEventBus.class).asEagerSingleton();
		bind(ApplicationDisplay.class).to(ApplicationViewImpl.class);

		// authentication
		bind(AuthenticationDisplay.class).to(AuthenticationViewImpl.class);
		
		// demo
		bind(DemoScreenDisplay.class).to(DemoScreenViewImpl.class);
		bind(GraphDisplay.class).to(GraphViewImpl.class);
		bind(MenuDisplay.class).to(MenuViewImpl.class);
		bind(DnDataGridDisplay.class).to(DnDataGridViewImpl.class);
	}

}
