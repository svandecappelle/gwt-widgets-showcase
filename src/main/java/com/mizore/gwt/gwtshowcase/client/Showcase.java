package com.mizore.gwt.gwtshowcase.client;

import com.google.gwt.core.client.EntryPoint;
import com.mizore.gwt.gwtshowcase.client.application.ApplicationInjector;
import com.mizore.gwt.gwtshowcase.client.application.presenter.ApplicationPresenter;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Showcase implements EntryPoint {
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	  ApplicationPresenter presenter = ApplicationInjector.Util.getInstance().getPresenter();
	  presenter.bind();
	  presenter.revealDisplay();
  }
}
