package com.mizore.gwt.gwtshowcase.client.screen.tree.view;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.Style.WhiteSpace;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.i18n.shared.HasDirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.DirectionalTextHelper;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasDirectionalSafeHtml;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWordWrap;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

public class MultiStateCheckBox extends ButtonBase implements HasName, HasValue<BooleanStateEnum>, HasWordWrap, HasDirectionalSafeHtml, HasDirectionEstimator, IsEditor<LeafValueEditor<BooleanStateEnum>> {

    public static final DirectionEstimator DEFAULT_DIRECTION_ESTIMATOR = DirectionalTextHelper.DEFAULT_DIRECTION_ESTIMATOR;

    final DirectionalTextHelper directionalTextHelper;
    InputElement inputElem;
    LabelElement labelElem;
    private LeafValueEditor<BooleanStateEnum> editor;
    private boolean valueChangeHandlerInitialized;

    private static final MultiStateCheckBoxBundle bundle = GWT.create(MultiStateCheckBoxBundle.class);

    static {
        bundle.css().ensureInjected();
    }

    /**
     * Creates a check box with no label.
     */
    public MultiStateCheckBox() {
        this(DOM.createInputCheck());
        setStyleName(bundle.css().checkbox());
    }

    /**
     * Creates a check box with the specified text label.
     * 
     * @param label
     *            the check box's label
     */
    public MultiStateCheckBox(SafeHtml label) {
        this(label.asString(), true);
    }

    /**
     * Creates a check box with the specified text label.
     * 
     * @param label
     *            the check box's label
     * @param dir
     *            the text's direction. Note that {@code DEFAULT} means direction should be inherited from the widget's
     *            parent element.
     */
    public MultiStateCheckBox(SafeHtml label, Direction dir) {
        this();
        setHTML(label, dir);
    }

    /**
     * Creates a check box with the specified text label.
     * 
     * @param label
     *            the check box's label
     * @param directionEstimator
     *            A DirectionEstimator object used for automatic direction adjustment. For convenience,
     *            {@link #DEFAULT_DIRECTION_ESTIMATOR} can be used.
     */
    public MultiStateCheckBox(SafeHtml label, DirectionEstimator directionEstimator) {
        this();
        setDirectionEstimator(directionEstimator);
        setHTML(label.asString());
    }

    /**
     * Creates a check box with the specified text label.
     * 
     * @param label
     *            the check box's label
     */
    public MultiStateCheckBox(String label) {
        this();
        setText(label);
    }

    /**
     * Creates a check box with the specified text label.
     * 
     * @param label
     *            the check box's label
     * @param dir
     *            the text's direction. Note that {@code DEFAULT} means direction should be inherited from the widget's
     *            parent element.
     */
    public MultiStateCheckBox(String label, Direction dir) {
        this();
        setText(label, dir);
    }

    /**
     * Creates a label with the specified text and a default direction estimator.
     * 
     * @param label
     *            the check box's label
     * @param directionEstimator
     *            A DirectionEstimator object used for automatic direction adjustment. For convenience,
     *            {@link #DEFAULT_DIRECTION_ESTIMATOR} can be used.
     */
    public MultiStateCheckBox(String label, DirectionEstimator directionEstimator) {
        this();
        setDirectionEstimator(directionEstimator);
        setText(label);
    }

    /**
     * Creates a check box with the specified text label.
     * 
     * @param label
     *            the check box's label
     * @param asHTML
     *            <code>true</code> to treat the specified label as html
     */
    public MultiStateCheckBox(String label, boolean asHTML) {
        this();
        if (asHTML) {
            setHTML(label);
        } else {
            setText(label);
        }
    }

    protected MultiStateCheckBox(Element elem) {
        super(DOM.createSpan());

        inputElem = InputElement.as(elem);
        labelElem = Document.get().createLabelElement();

        getElement().appendChild(inputElem);
        getElement().appendChild(labelElem);

        String uid = DOM.createUniqueId();
        inputElem.setPropertyString("id", uid);
        inputElem.setValue(BooleanStateEnum.FALSE.name());
        labelElem.setHtmlFor(uid);

        directionalTextHelper = new DirectionalTextHelper(labelElem, true);

        // Accessibility: setting tab index to be 0 by default, ensuring element
        // appears in tab sequence. FocusWidget's setElement method already
        // calls setTabIndex, which is overridden below. However, at the time
        // that this call is made, inputElem has not been created. So, we have
        // to call setTabIndex again, once inputElem has been created.
        setTabIndex(0);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<BooleanStateEnum> handler) {
        // Is this the first value change handler? If so, time to add handlers
        if (!valueChangeHandlerInitialized) {
            ensureDomEventHandlers();
            valueChangeHandlerInitialized = true;
        }
        return addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    public LeafValueEditor<BooleanStateEnum> asEditor() {
        if (editor == null) {
            editor = TakesValueEditor.of(this);
        }
        return editor;
    }

    @Override
    public DirectionEstimator getDirectionEstimator() {
        return directionalTextHelper.getDirectionEstimator();
    }

    /**
     * Returns the value property of the input element that backs this widget. This is the value that will be associated
     * with the CheckBox name and submitted to the server if a {@link FormPanel} that holds it is submitted and the box
     * is checked.
     * <p>
     * Don't confuse this with {@link #getValue}, which returns true or false if the widget is checked.
     */
    public String getFormValue() {
        return inputElem.getValue();
    }

    @Override
    public String getHTML() {
        return directionalTextHelper.getTextOrHtml(true);
    }

    @Override
    public String getName() {
        return inputElem.getName();
    }

    @Override
    public int getTabIndex() {
        return inputElem.getTabIndex();
    }

    @Override
    public String getText() {
        return directionalTextHelper.getTextOrHtml(false);
    }

    @Override
    public Direction getTextDirection() {
        return directionalTextHelper.getTextDirection();
    }

    /**
     * Determines whether this check box is currently checked.
     * <p>
     * Note that this <em>does not</em> return the value property of the checkbox input element wrapped by this widget.
     * For access to that property, see {@link #getFormValue()}
     * 
     * @return <code>true</code> if the check box is checked, false otherwise. Will not return null
     */
    @Override
    public BooleanStateEnum getValue() {
        if (isAttached()) {
            return inputElem.getValue() != null ? BooleanStateEnum.valueOf(String.valueOf(inputElem.getValue()).toUpperCase()) : BooleanStateEnum.FALSE;
        } else {
            return inputElem.isDefaultChecked() ? BooleanStateEnum.TRUE : BooleanStateEnum.FALSE;
        }
    }

    @Override
    public boolean getWordWrap() {
        return !WhiteSpace.NOWRAP.getCssName().equals(getElement().getStyle().getWhiteSpace());
    }

    /**
     * Determines whether this check box is currently checked.
     * 
     * @return <code>true</code> if the check box is checked
     * @deprecated Use {@link #getValue} instead
     */
    @Deprecated
    public boolean isChecked() {
        // Funny comparison b/c getValue could in theory return null
        return getValue() == BooleanStateEnum.TRUE;
    }

    @Override
    public boolean isEnabled() {
        return !inputElem.isDisabled();
    }

    @Override
    public void setAccessKey(char key) {
        inputElem.setAccessKey("" + key);
    }

    /**
     * Checks or unchecks this check box. Does not fire {@link ValueChangeEvent}. (If you want the event to fire, use
     * {@link #setValue(Boolean, boolean)})
     * 
     * @param checked
     *            <code>true</code> to check the check box.
     * @deprecated Use {@link #setValue(Boolean)} instead
     */
    @Deprecated
    public void setChecked(boolean checked) {
        setValue(checked ? BooleanStateEnum.TRUE : BooleanStateEnum.FALSE);
    }

    /**
     * {@inheritDoc}
     * <p>
     * See note at {@link #setDirectionEstimator(DirectionEstimator)}.
     */
    @Override
    public void setDirectionEstimator(boolean enabled) {
        directionalTextHelper.setDirectionEstimator(enabled);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Note: DirectionEstimator should be set before the label has any content; it's highly recommended to set it using
     * a constructor. Reason: if the label already has non-empty content, this will update its direction according to
     * the new estimator's result. This may cause flicker, and thus should be avoided.
     */
    @Override
    public void setDirectionEstimator(DirectionEstimator directionEstimator) {
        directionalTextHelper.setDirectionEstimator(directionEstimator);
    }

    @Override
    public void setEnabled(boolean enabled) {
        inputElem.setDisabled(!enabled);
        if (enabled) {
            removeStyleDependentName("disabled");
        } else {
            addStyleDependentName("disabled");
        }
    }

    @Override
    public void setFocus(boolean focused) {
        if (focused) {
            inputElem.focus();
        } else {
            inputElem.blur();
        }
    }

    /**
     * Set the value property on the input element that backs this widget. This is the value that will be associated
     * with the CheckBox's name and submitted to the server if a {@link FormPanel} that holds it is submitted and the
     * box is checked.
     * <p>
     * Don't confuse this with {@link #setValue}, which actually checks and unchecks the box.
     * 
     * @param value
     */
    public void setFormValue(String value) {
        inputElem.setAttribute("value", value);
    }

    @Override
    public void setHTML(SafeHtml html, Direction dir) {
        directionalTextHelper.setTextOrHtml(html.asString(), dir, true);
    }

    @Override
    public void setHTML(String html) {
        directionalTextHelper.setTextOrHtml(html, true);
    }

    @Override
    public void setName(String name) {
        inputElem.setName(name);
    }

    @Override
    public void setTabIndex(int index) {
        // Need to guard against call to setTabIndex before inputElem is
        // initialized. This happens because FocusWidget's (a superclass of
        // CheckBox) setElement method calls setTabIndex before inputElem is
        // initialized. See CheckBox's protected constructor for more information.
        if (inputElem != null) {
            inputElem.setTabIndex(index);
        }
    }

    @Override
    public void setText(String text) {
        directionalTextHelper.setTextOrHtml(text, false);
    }

    @Override
    public void setText(String text, Direction dir) {
        directionalTextHelper.setTextOrHtml(text, dir, false);
    }

    /**
     * Checks or unchecks the check box.
     * <p>
     * Note that this <em>does not</em> set the value property of the checkbox input element wrapped by this widget. For
     * access to that property, see {@link #setFormValue(String)}
     * 
     * @param value
     *            true to check, false to uncheck; null value implies false
     */
    @Override
    public void setValue(BooleanStateEnum value) {
        setValue(value, false);
    }

    /**
     * Checks or unchecks the check box, firing {@link ValueChangeEvent} if appropriate.
     * <p>
     * Note that this <em>does not</em> set the value property of the checkbox input element wrapped by this widget. For
     * access to that property, see {@link #setFormValue(String)}
     * 
     * @param value
     *            true to check, false to uncheck; null value implies false
     * @param fireEvents
     *            If true, and value has changed, fire a {@link ValueChangeEvent}
     */
    @Override
    public void setValue(BooleanStateEnum value, boolean fireEvents) {
        if (value == null) {
            value = BooleanStateEnum.FALSE;
        }

        BooleanStateEnum oldValue = getValue();
        inputElem.setValue(value.name());
        setFormValue(value.name().toLowerCase());
        
        this.removeStyleName(oldValue.name().toLowerCase());
        this.addStyleName(getValue().name().toLowerCase());

        if (value.equals(oldValue)) {
            return;
        }
        if (fireEvents) {
            ValueChangeEvent.fire(this, value);
        }
    }

    @Override
    public void setWordWrap(boolean wrap) {
        getElement().getStyle().setWhiteSpace(wrap ? WhiteSpace.NORMAL : WhiteSpace.NOWRAP);
    }

    // Unlike other widgets the CheckBox sinks on its inputElement, not
    // its wrapper
    @Override
    public void sinkEvents(int eventBitsToAdd) {
        if (isOrWasAttached()) {
            Event.sinkEvents(inputElem, eventBitsToAdd | Event.getEventsSunk(inputElem));
        } else {
            super.sinkEvents(eventBitsToAdd);
        }
    }

    protected void ensureDomEventHandlers() {
        addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Checkboxes always toggle their value, no need to compare
                // with old value. Radio buttons are not so lucky, see
                // overrides in RadioButton
                BooleanStateEnum state;
                if (getValue() == BooleanStateEnum.TRUE) {
                    state = BooleanStateEnum.FALSE;
                } else {
                    state = BooleanStateEnum.TRUE;
                }
                setValue(state);
                Logger.getLogger("test2").info("toggle check: " + getValue().name());
                
                ValueChangeEvent.fire(MultiStateCheckBox.this, state);
            }
        });
    }

    /**
     * <b>Affected Elements:</b>
     * <ul>
     * <li>-label = label next to checkbox.</li>
     * </ul>
     * 
     * @see UIObject#onEnsureDebugId(String)
     */
    @Override
    protected void onEnsureDebugId(String baseID) {
        super.onEnsureDebugId(baseID);
        ensureDebugId(labelElem, baseID, "label");
        ensureDebugId(inputElem, baseID, "input");
        labelElem.setHtmlFor(inputElem.getId());
    }

    /**
     * This method is called when a widget is attached to the browser's document. onAttach needs special handling for
     * the CheckBox case. Must still call {@link Widget#onAttach()} to preserve the <code>onAttach</code> contract.
     */
    @Override
    protected void onLoad() {
        DOM.setEventListener(inputElem, this);
    }

    /**
     * This method is called when a widget is detached from the browser's document. Overridden because of IE bug that
     * throws away checked state and in order to clear the event listener off of the <code>inputElem</code>.
     */
    @Override
    protected void onUnload() {
        // Clear out the inputElem's event listener (breaking the circular
        // reference between it and the widget).
        DOM.setEventListener(inputElem, null);
        setValue(getValue());
    }

    /**
     * Replace the current input element with a new one. Preserves all state except for the name property, for nasty
     * reasons related to radio button grouping. (See implementation of {@link RadioButton#setName}.)
     * 
     * @param elem
     *            the new input element
     */
    protected void replaceInputElement(Element elem) {
        replaceInputElement(DOM.asOld(elem));
    }

    /**
     * @deprecated Call and use {@link replaceInputElement(Element)} instead.
     */
    @Deprecated
    protected void replaceInputElement(com.google.gwt.user.client.Element elem) {
        InputElement newInputElem = InputElement.as(elem);
        // Collect information we need to set
        int tabIndex = getTabIndex();
        BooleanStateEnum checked = getValue();
        boolean enabled = isEnabled();
        String formValue = getFormValue();
        String uid = inputElem.getId();
        String accessKey = inputElem.getAccessKey();
        int sunkEvents = Event.getEventsSunk(inputElem);

        // Clear out the old input element
        DOM.setEventListener(inputElem, null);

        getElement().replaceChild(newInputElem, inputElem);

        // Sink events on the new element
        Event.sinkEvents(elem, Event.getEventsSunk(inputElem));
        Event.sinkEvents(inputElem, 0);
        inputElem = newInputElem;

        // Setup the new element
        Event.sinkEvents(inputElem, sunkEvents);
        inputElem.setId(uid);
        if (!"".equals(accessKey)) {
            inputElem.setAccessKey(accessKey);
        }
        setTabIndex(tabIndex);
        setValue(checked);
        setEnabled(enabled);
        setFormValue(formValue);

        // Set the event listener
        if (isAttached()) {
            DOM.setEventListener(inputElem, this);
        }
    }
}
