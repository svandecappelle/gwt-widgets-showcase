package com.mizore.gwt.gwtshowcase.client.screen.tree.view;

import java.util.logging.Logger;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IsTreeItem;
import com.google.gwt.user.client.ui.TreeItem;

public class TreeItemWidget extends TreeItem implements IsTreeItem {
    private ContentTreeItem widget;

    public TreeItemWidget(String text) {
        super();
        widget = new ContentTreeItem(text);
        setWidget(widget);

        widget.getSelection().addValueChangeHandler(new ValueChangeHandler<BooleanStateEnum>() {

            @Override
            public void onValueChange(ValueChangeEvent<BooleanStateEnum> event) {
                Logger.getLogger("tesst").info("select: " + event.getValue());
                selectChildren(event.getValue(), true);
                selectParents(event.getValue());
            }
        });
    }

    protected void selectParents(BooleanStateEnum selection) {
        if (getParentItem() != null) {
            for (int i = 0; i < getParentItem().getChildCount(); i++) {
                TreeItemWidget itemChild = (TreeItemWidget) getParentItem().getChild(i);
                if (itemChild.getHasValue().getValue() != selection) {
                    ((TreeItemWidget) getParentItem()).setSelectedItem(BooleanStateEnum.PARTIAL, false);
                    ((TreeItemWidget) getParentItem()).selectParents(BooleanStateEnum.PARTIAL);
                    return;
                }
            }

            ((TreeItemWidget) getParentItem()).setSelectedItem(selection, false);
            ((TreeItemWidget) getParentItem()).selectParents(selection);
        }
    }

    protected void selectChildren(BooleanStateEnum selection, boolean recursive) {
        if (TreeItemWidget.this.getChildCount() > 0) {
            for (int i = 0; i < getChildCount(); i++) {
                TreeItemWidget itemChild = (TreeItemWidget) getChild(i);
                itemChild.setSelectedItem(selection, recursive);
            }
        }
    }

    private void setSelectedItem(BooleanStateEnum selected, boolean recursive) {
        super.setSelected(selected == BooleanStateEnum.TRUE || selected == BooleanStateEnum.PARTIAL);
        widget.getSelection().setValue(selected);
        if (recursive) {
            selectChildren(selected, recursive);
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
    }

    HasValue<BooleanStateEnum> getHasValue() {
        return widget.getSelection();
    }
}
