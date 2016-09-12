package com.mizore.gwt.gwtshowcase.client.screen.dnd.datagrid.view;

import static com.google.gwt.query.client.GQuery.$;
import gwtquery.plugins.draggable.client.DraggableOptions;
import gwtquery.plugins.draggable.client.DraggableOptions.RevertOption;
import gwtquery.plugins.droppable.client.gwt.DragAndDropColumn;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.github.gwtbootstrap.client.ui.CellTable.TableType;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SingleSelectionModel;
import com.mizore.gwt.widgets.client.cellview.DragAndDropDataGridBootstrap;
import com.mizore.gwt.widgets.client.loader.DataGridLoaderWidget;

/**
 * The concurents' store datagrid implementation.
 * 
 * @author svandecappelle
 * @since 2.0.0
 */
public class DraggableTable extends DragAndDropDataGridBootstrap<String> {

    protected static final Logger LOGGER = Logger.getLogger("DraggableTable");

    static final class MockValues {
        private static List<String> get() {
            List<String> mocks = new ArrayList<String>();

            for (int i = 0; i < 50; i++) {
                mocks.add(Integer.toString(Random.nextInt()));
            }

            return mocks;
        }
    }

    private AsyncDataProvider<String> dataProvider;
    private boolean isDraggable;

    /**
     * The Cell used to render a {@link ContactInfo}.
     *
     * Code coming from the GWT showcase
     *
     */
    public static class SheetDraggedCell extends AbstractCell<String> {

        public SheetDraggedCell() {
        }

        @Override
        public void render(Context context, String value, SafeHtmlBuilder sb) {
            // Value can be null, so do a null check..
            if (value == null) {
                return;
            }
            LOGGER.info("drag");
            sb.appendHtmlConstant("<div>");

            sb.appendHtmlConstant("<span>");
            sb.appendEscaped("String: ");
            sb.appendHtmlConstant("</span>");

            sb.appendHtmlConstant("<span>");
            sb.appendEscaped(value.toString());
            sb.appendHtmlConstant("</span>");

            sb.appendHtmlConstant("</div>");

        }
    }

    /**
     * Injected constructor.
     */
    public DraggableTable() {
        this.setLoadingIndicator(new DataGridLoaderWidget());
    }

    /**
     * Create all cells in the header.
     * 
     * @param head
     */
    private void createHeader() {

        Column<String, String> column = null;

        if (isDraggable()) {
            column = new DragAndDropColumn<String, String>(new TextCell()) {
                @Override
                public String getValue(String object) {
                    return object;
                }
            };

            // retrieve draggableOptions on the column
            DraggableOptions draggableOptions = ((DragAndDropColumn<String, String>) column).getDraggableOptions();
            // use template to construct the helper. The content of the div will
            // be
            // set
            // after
            draggableOptions.setHelper($(Templates.INSTANCE.outerHelper().asString()));
            // opacity of the helper
            draggableOptions.setOpacity((float) 0.8);
            // cursor to use during the drag operation
            draggableOptions.setCursor(Cursor.MOVE);
            // set the revert option
            draggableOptions.setRevert(RevertOption.ON_INVALID_DROP);
            // prevents dragging when user click on the category drop-down list
            draggableOptions.setCancel("select");
            // attach the helper to the document because datagrid is scrollable
            draggableOptions.setAppendTo("body");

        } else {
            column = new TextColumn<String>() {
                @Override
                public String getValue(String object) {
                    return object;
                }
            };
        }

        this.addColumn(column, new Header<String>(new TextCell()) {
            @Override
            public String getValue() {
                return "String";
            }
        });

        this.addStyle(TableType.HOVER);
        this.addStyleName("table");

        this.dataProvider = new AsyncDataProvider<String>() {

            @Override
            protected void onRangeChanged(HasData<String> display) {
                // fireEventReload
                Timer t = new Timer() {

                    @Override
                    public void run() {
                        setData(new Range(0, 49), MockValues.get(), 50);
                    }
                };
                t.schedule(3000);

            }
        };
        dataProvider.addDataDisplay(this);
        this.setSelectionModel(new SingleSelectionModel<String>());
        super.setSize("100%", "100%");
    }

    public boolean isDraggable() {
        return this.isDraggable;
    }

    public void setDraggable(boolean isDraggable) {
        this.isDraggable = isDraggable;
    }

    /**
     * Template for the helper
     *
     * @author Julien Dramaix (julien.dramaix@gmail.com)
     *
     */
    static interface Templates extends SafeHtmlTemplates {
        Templates INSTANCE = GWT.create(Templates.class);

        @Template("<div id='dragHelper' style='border:1px solid black; background-color:#ffffff; color:black; width:150px;'></div>")
        SafeHtml outerHelper();
    }

    /**
     * Set the table data.
     * 
     * @param range
     *            the range of values.
     * @param values
     *            the data values.
     * @param nbValues
     *            the total number of values.
     */
    public void setData(Range range, List<String> values, int nbValues) {
        this.dataProvider.updateRowCount(nbValues, true);
        this.dataProvider.updateRowData(range.getStart(), values);
    }

    /**
     * Set the table meta-data.
     * 
     * @param metadata
     *            the table meta-data.
     */
    public void setMetaData() {
        this.createHeader();
    }

}
