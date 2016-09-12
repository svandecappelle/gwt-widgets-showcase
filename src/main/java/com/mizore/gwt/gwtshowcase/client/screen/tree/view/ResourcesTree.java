package com.mizore.gwt.gwtshowcase.client.screen.tree.view;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Tree.Resources;

public interface ResourcesTree extends Resources {
    @Override
    ImageResource treeClosed();

    @Override
    ImageResource treeLeaf();

    @Override
    ImageResource treeOpen();
}
