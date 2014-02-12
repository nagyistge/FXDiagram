package de.fxdiagram.examples.java;

import de.fxdiagram.annotations.properties.ModelNode;
import de.fxdiagram.core.model.DomainObjectHandleImpl;
import de.fxdiagram.core.model.DomainObjectProvider;
import de.fxdiagram.core.model.ModelElementImpl;
import de.fxdiagram.examples.java.JavaModelProvider;

@ModelNode({ "id", "key", "provider" })
@SuppressWarnings("all")
public class JavaTypeHandle extends DomainObjectHandleImpl {
  public JavaTypeHandle(final Class<?> javaClass, final JavaModelProvider provider) {
    super(javaClass.getCanonicalName(), javaClass.getCanonicalName(), provider);
  }
  
  public Class<?> getDomainObject() {
    Object _domainObject = super.getDomainObject();
    return ((Class<?>) _domainObject);
  }
  
  /**
   * Automatically generated by @ModelNode. Needed for deserialization.
   */
  public JavaTypeHandle() {
  }
  
  public void populate(final ModelElementImpl modelElement) {
    modelElement.addProperty(idProperty(), String.class);
    modelElement.addProperty(keyProperty(), String.class);
    modelElement.addProperty(providerProperty(), DomainObjectProvider.class);
  }
}
