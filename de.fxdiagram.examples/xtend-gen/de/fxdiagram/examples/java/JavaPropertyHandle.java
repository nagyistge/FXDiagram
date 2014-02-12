package de.fxdiagram.examples.java;

import de.fxdiagram.annotations.properties.ModelNode;
import de.fxdiagram.core.model.DomainObjectHandleImpl;
import de.fxdiagram.core.model.DomainObjectProvider;
import de.fxdiagram.core.model.ModelElementImpl;
import de.fxdiagram.examples.java.JavaModelProvider;
import de.fxdiagram.examples.java.JavaProperty;

@ModelNode({ "id", "key", "provider" })
@SuppressWarnings("all")
public class JavaPropertyHandle extends DomainObjectHandleImpl {
  public JavaPropertyHandle(final JavaProperty it, final JavaModelProvider provider) {
    super(((it.getType().getCanonicalName() + " ") + it.getName()), ((it.getType().getCanonicalName() + " ") + it.getName()), provider);
  }
  
  public JavaProperty getDomainObject() {
    Object _domainObject = super.getDomainObject();
    return ((JavaProperty) _domainObject);
  }
  
  /**
   * Automatically generated by @ModelNode. Needed for deserialization.
   */
  public JavaPropertyHandle() {
  }
  
  public void populate(final ModelElementImpl modelElement) {
    modelElement.addProperty(idProperty(), String.class);
    modelElement.addProperty(keyProperty(), String.class);
    modelElement.addProperty(providerProperty(), DomainObjectProvider.class);
  }
}
