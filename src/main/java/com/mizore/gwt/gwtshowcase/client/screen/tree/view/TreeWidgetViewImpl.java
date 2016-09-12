package com.mizore.gwt.gwtshowcase.client.screen.tree.view;

import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.inject.Inject;
import com.mizore.gwt.gwtshowcase.client.screen.tree.interfaces.TreeWidgetDisplay;

public class TreeWidgetViewImpl extends SimplePanel implements TreeWidgetDisplay {

    private Tree tree;

    private int itemCount;

    @Inject
    public TreeWidgetViewImpl(ResourcesTree treeResources) {
        setWidth("50%");
        setHeight("100%");
        tree = new Tree(treeResources, true);
        tree.setAnimationEnabled(true);
        tree.setHeight("400px");
        tree.setWidth("50%");
        for (int i = 0; i < 1; i++) {
            itemCount += 1;
            TreeItemWidget item = new TreeItemWidget("Global " + itemCount);
            tree.addItem(item);
            addItems(1, item);
        }

        add(tree);
    }

    private void addItems(int level, TreeItem addTextItem) {
        if (level < 5) {
            for (int i = 0; i < 8; i++) {
                itemCount += 1;
                TreeItemWidget item = new TreeItemWidget(Character.toString((char) (65 + level)) + " -> " + itemCount);
                addTextItem.addItem(item);
                addItems(level + 1, item);
            }
        }
    }

}
