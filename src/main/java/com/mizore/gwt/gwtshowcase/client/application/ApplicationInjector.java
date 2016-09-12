package com.mizore.gwt.gwtshowcase.client.application;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.mizore.gwt.logging.client.gin.LoggingModule;
import com.mizore.gwt.gwtshowcase.client.application.presenter.ApplicationPresenter;

@GinModules({ApplicationModule.class, LoggingModule.class})
public interface ApplicationInjector extends Ginjector {

	final class Util {
		private static ApplicationInjector injector;

		public static ApplicationInjector getInstance() {
			if (injector == null) {
				injector = GWT.create(ApplicationInjector.class);
			}
			return injector;
		}
	}
	
	ApplicationPresenter getPresenter();
}
