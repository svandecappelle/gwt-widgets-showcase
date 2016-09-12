package com.mizore.gwt.gwtshowcase.client.application.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.mizore.gwt.gwtshowcase.client.application.interfaces.ApplicationDisplay;
import com.mizore.gwt.gwtshowcase.client.authentication.events.AuthenticatedEvent;
import com.mizore.gwt.gwtshowcase.client.authentication.events.AuthenticatedHandler;
import com.mizore.gwt.gwtshowcase.client.authentication.presenter.AuthenticationPresenter;
import com.mizore.gwt.gwtshowcase.client.screen.demo.presenter.DemoScreenPresenter;
import com.mizore.gwt.gwtshowcase.client.screen.dnd.datagrid.presenter.DnDataGridPresenter;
import com.mizore.gwt.gwtshowcase.client.screen.graph.presenter.GraphScreenPresenter;
import com.mizore.gwt.gwtshowcase.client.screen.menu.presenter.MenuPresenter;
import com.mizore.gwt.gwtshowcase.client.screen.tree.presenter.TreeWidgetPresenter;
import com.mizore.gwt.logging.client.control.presenter.LoggingPresenter;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

@Singleton
public class ApplicationPresenter extends WidgetPresenter<ApplicationDisplay> {

    private HasClickHandlers demo;
    private HasClickHandlers graph;
    private HasClickHandlers menu;
    private HasClickHandlers dnd;
    private HasClickHandlers tree;

    @Inject
    private Provider<DemoScreenPresenter> presenterDemoGenerator;
    private DemoScreenPresenter presenterDemo;

    @Inject
    private GraphScreenPresenter presenterGraph;

    @Inject
    private DnDataGridPresenter dragNdropDataGridPresenter;

    @Inject
    private TreeWidgetPresenter treeWidget;

    @Inject
    private LoggingPresenter presenterLogging;

    @Inject
    private AuthenticationPresenter authenticationPresenter;

    @Inject
    private MenuPresenter presenterMenu;

    @Inject
    public ApplicationPresenter(ApplicationDisplay display, EventBus eventBus) {
        super(display, eventBus);

        this.demo = display.addMenuItem("D", "Demo");
        this.graph = display.addMenuItem("G", "Graph");
        this.menu = display.addMenuItem("M", "Menu");
        this.dnd = display.addMenuItem("N", "Drag/Drop datagrid");
        this.tree = display.addMenuItem("T", "TreeWidget");

    }

    @Override
    protected void onBind() {
        presenterLogging.bind();
        authenticationPresenter.bind();

        registerHandler(demo.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                presenterGraph.unbind();
                presenterDemo = presenterDemoGenerator.get();

                display.setCenterWidget(presenterDemo.getDisplay());
                presenterDemo.bind();
                presenterDemo.revealDisplay();
            }
        }));

        registerHandler(graph.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (presenterDemo != null) {
                    presenterDemo.unbind();
                }

                display.setCenterWidget(presenterGraph.getDisplay());
                presenterGraph.bind();
                presenterGraph.revealDisplay();
            }
        }));

        registerHandler(menu.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (presenterDemo != null) {
                    presenterDemo.unbind();
                }

                display.setCenterWidget(presenterMenu.getDisplay());
                presenterMenu.bind();
                presenterMenu.revealDisplay();
            }
        }));

        registerHandler(dnd.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                presenterGraph.unbind();

                display.setCenterWidget(dragNdropDataGridPresenter.getDisplay());
                dragNdropDataGridPresenter.bind();
                dragNdropDataGridPresenter.revealDisplay();
            }
        }));

        registerHandler(tree.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                presenterGraph.unbind();

                display.setCenterWidget(treeWidget.getDisplay());
                treeWidget.bind();
                treeWidget.revealDisplay();
            }
        }));

        registerHandler(eventBus.addHandler(AuthenticatedEvent.getType(), new AuthenticatedHandler() {

            @Override
            public void onAuthenticated(AuthenticatedEvent event) {
                if (event.isAuthenticated()) {
                    RootLayoutPanel.get().add(display.asWidget());
                }
            }
        }));
    }

    @Override
    protected void onUnbind() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onRevealDisplay() {
        presenterLogging.revealDisplay();
        authenticationPresenter.authenticate();
    }

}
