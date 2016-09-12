package com.mizore.gwt.gwtshowcase.client.screen.tree.view;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.resources.client.ImageResource;

public interface MultiStateCheckBoxBundle extends ClientBundle {

    @NotStrict
    @Source("multistate-checkbox.css")
    MultiStateCheckBoxResourceCss css();
    
    @Source("checkboxes.gif")
    ImageResource checkboxImage();
}
