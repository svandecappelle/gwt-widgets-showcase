package com.mizore.gwt.gwtshowcase.client.screen.dnd.datagrid.view;

import gwtquery.plugins.draggable.client.events.DragStartEvent;
import gwtquery.plugins.draggable.client.events.DragStartEvent.DragStartEventHandler;
import gwtquery.plugins.droppable.client.events.DropEvent;
import gwtquery.plugins.droppable.client.events.DropEvent.DropEventHandler;
import gwtquery.plugins.droppable.client.events.OutDroppableEvent;
import gwtquery.plugins.droppable.client.events.OutDroppableEvent.OutDroppableEventHandler;
import gwtquery.plugins.droppable.client.events.OverDroppableEvent;
import gwtquery.plugins.droppable.client.events.OverDroppableEvent.OverDroppableEventHandler;

import java.util.logging.Logger;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.inject.Inject;
import com.mizore.gwt.gwtshowcase.client.screen.dnd.datagrid.interfaces.DnDataGridDisplay;

public class DnDataGridViewImpl extends LayoutPanel implements DnDataGridDisplay {

    protected static final Logger LOGGER = Logger.getLogger("ConcurentsEnseigneViewImpl");

    @Inject
    public DnDataGridViewImpl(final DraggableTable tableOne, final DraggableTable tableTwo) {
        setSize("100%", "100%");

        tableOne.setDraggable(true);

        this.add(tableOne);

        this.add(tableTwo);

        tableOne.setMetaData();
        tableTwo.setMetaData();

        this.setWidgetTopBottom(tableOne, 0, Unit.PX, 0, Unit.PX);
        this.setWidgetTopBottom(tableTwo, 0, Unit.PX, 0, Unit.PX);

        this.setWidgetLeftRight(tableOne, 0, Unit.PX, 50, Unit.PCT);
        this.setWidgetLeftRight(tableTwo, 50, Unit.PCT, 0, Unit.PX);

        // Drag from table 1 to table 2
        // Revert is disabled
        tableOne.addDragStartHandler(new DragStartEventHandler() {

            @Override
            public void onDragStart(DragStartEvent event) {
                LOGGER.info("DragStart: ");
                String contact = event.getDraggableData();
                // LOGGER.info("DataDragged: " + contact);

                Element helper = event.getHelper();
                SafeHtmlBuilder sb = new SafeHtmlBuilder();
                // reuse the contact cell to render the inner html of the drag
                // helper.
                // new DraggableTable.SheetDraggedCell().render(null, contact,
                // sb);

                sb.appendHtmlConstant("<div>");

                sb.appendHtmlConstant("<span>");
                sb.appendEscaped("Integer: ");
                sb.appendHtmlConstant("</span>");

                sb.appendHtmlConstant("<span>");
                sb.appendEscaped(contact);
                sb.appendHtmlConstant("</span>");

                sb.appendHtmlConstant("</div>");

                helper.setInnerHTML(sb.toSafeHtml().asString());
            }
        });

        tableTwo.addOverDroppableHandler(new OverDroppableEventHandler() {

            @Override
            public void onOverDroppable(OverDroppableEvent event) {
                event.getDroppable().getStyle().setBackgroundColor("red");
            }
        });

        tableTwo.addOutDroppableHandler(new OutDroppableEventHandler() {

            @Override
            public void onOutDroppable(OutDroppableEvent event) {
                event.getDroppable().getStyle().setBackgroundColor("transparent");
            }
        });

        tableTwo.addDropHandler(new DropEventHandler() {

            public void onDrop(DropEvent event) {
                // Integer draggedRow = event.getDraggableData();
                // LOGGER.info("dragged:: + " + draggedRow);

                // Integer droppedRow = event.getDroppableData();
                // event.getDroppable().getStyle().setBackgroundColor("red");
                // LOGGER.info("dropped::into position of " + droppedRow +
                // " -> " + tableTwo.getDisplayedItems().indexOf(droppedRow) +
                // " / " + (tableTwo.getDisplayedItems().size() - 1));

                event.getDroppable().getStyle().setBackgroundColor("transparent");
                // first remove the contact to the table
                // ContactDatabase.get().removeContact(contactToDelete);
                // avoid doublon
                // table2Test.getDisplayedItems().remove(contactToDelete);
                // add the contact to the delete list
                // getTable().getDisplayedItems().add(contactToDelete);

            }
        });

    }

}
