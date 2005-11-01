/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.intellij.util.xml;

/**
 * @author peter
 */
public abstract class DomEventListener implements DomEventVisitor{

  public void valueChanged(ValueChangeEvent event) {
  }

  public void elementDefined(ElementDefinedEvent event) {
  }

  public void elementUndefined(ElementUndefinedEvent event) {
  }

  public void elementChanged(ElementChangedEvent event) {
  }

  public void eventOccured(DomEvent event) {
    event.accept(this);
  }

  public final void visitValueChangeEvent(final ValueChangeEvent event) {
    valueChanged(event);
  }

  public final void visitElementDefined(final ElementDefinedEvent event) {
    elementDefined(event);
  }

  public final void visitElementUndefined(final ElementUndefinedEvent event) {
    elementUndefined(event);
  }

  public void visitElementChangedEvent(final ElementChangedEvent event) {
    elementChanged(event);
  }
}
