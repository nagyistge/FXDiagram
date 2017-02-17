package de.fxdiagram.eclipse.xtext.ids;

import com.google.common.base.Objects;
import de.fxdiagram.annotations.properties.ModelNode;
import de.fxdiagram.core.model.ModelElementImpl;
import de.fxdiagram.core.model.ToString;
import de.fxdiagram.core.model.XModelProvider;
import de.fxdiagram.eclipse.xtext.ids.XtextEObjectID;
import java.util.NoSuchElementException;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IResourceServiceProvider;

@ModelNode({ "eClassURIAsString", "uriAsString" })
@SuppressWarnings("all")
public abstract class AbstractXtextEObjectID implements XtextEObjectID, XModelProvider {
  private EClass eClass;
  
  private URI uri;
  
  public AbstractXtextEObjectID(final EClass eClass, final URI elementURI) {
    this.uri = elementURI;
    String _string = elementURI.toString();
    this.uriAsStringProperty.set(_string);
    this.eClass = eClass;
    URI _uRI = EcoreUtil.getURI(eClass);
    String _string_1 = _uRI.toString();
    this.eClassURIAsStringProperty.set(_string_1);
  }
  
  @Override
  public URI getURI() {
    URI _elvis = null;
    if (this.uri != null) {
      _elvis = this.uri;
    } else {
      String _uriAsString = this.getUriAsString();
      URI _createURI = URI.createURI(_uriAsString);
      _elvis = this.uri = _createURI;
    }
    return _elvis;
  }
  
  @Override
  public EClass getEClass() {
    EClass _elvis = null;
    if (this.eClass != null) {
      _elvis = this.eClass;
    } else {
      String _eClassURIAsString = this.getEClassURIAsString();
      EClass _eClass = this.getEClass(_eClassURIAsString);
      EClass _eClass_1 = (this.eClass = _eClass);
      _elvis = _eClass_1;
    }
    return _elvis;
  }
  
  protected EClass getEClass(final String uriAsString) {
    EClass _xblockexpression = null;
    {
      boolean _equals = Objects.equal(uriAsString, null);
      if (_equals) {
        return null;
      }
      final URI eClassURI = URI.createURI(uriAsString);
      URI _trimFragment = eClassURI.trimFragment();
      String _string = _trimFragment.toString();
      final EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(_string);
      boolean _equals_1 = Objects.equal(ePackage, null);
      if (_equals_1) {
        URI _trimFragment_1 = eClassURI.trimFragment();
        String _plus = ("Cannot find EPackage " + _trimFragment_1);
        throw new NoSuchElementException(_plus);
      }
      Resource _eResource = ePackage.eResource();
      String _fragment = eClassURI.fragment();
      EObject _eObject = _eResource.getEObject(_fragment);
      this.eClass = ((EClass) _eObject);
      boolean _equals_2 = Objects.equal(this.eClass, null);
      if (_equals_2) {
        throw new NoSuchElementException(("Cannot find EClass " + eClassURI));
      }
      _xblockexpression = this.eClass;
    }
    return _xblockexpression;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if ((obj instanceof AbstractXtextEObjectID)) {
      return (Objects.equal(((AbstractXtextEObjectID)obj).getURI().trimFragment(), this.getURI().trimFragment()) && Objects.equal(((AbstractXtextEObjectID)obj).getEClass(), this.getEClass()));
    } else {
      return false;
    }
  }
  
  @Override
  public int hashCode() {
    URI _uRI = this.getURI();
    URI _trimFragment = _uRI.trimFragment();
    int _hashCode = _trimFragment.hashCode();
    int _multiply = (_hashCode * 29);
    EClass _eClass = this.getEClass();
    int _hashCode_1 = _eClass.hashCode();
    int _multiply_1 = (_hashCode_1 * 173);
    return (_multiply + _multiply_1);
  }
  
  @Override
  public IResourceServiceProvider getResourceServiceProvider() {
    URI _uRI = this.getURI();
    return IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(_uRI);
  }
  
  /**
   * Automatically generated by @ModelNode. Needed for deserialization.
   */
  public AbstractXtextEObjectID() {
  }
  
  public void populate(final ModelElementImpl modelElement) {
    modelElement.addProperty(eClassURIAsStringProperty, String.class);
    modelElement.addProperty(uriAsStringProperty, String.class);
  }
  
  public void postLoad() {
    
  }
  
  public String toString() {
    return ToString.toString(this);
  }
  
  private ReadOnlyStringWrapper eClassURIAsStringProperty = new ReadOnlyStringWrapper(this, "eClassURIAsString");
  
  public String getEClassURIAsString() {
    return this.eClassURIAsStringProperty.get();
  }
  
  public ReadOnlyStringProperty eClassURIAsStringProperty() {
    return this.eClassURIAsStringProperty.getReadOnlyProperty();
  }
  
  private ReadOnlyStringWrapper uriAsStringProperty = new ReadOnlyStringWrapper(this, "uriAsString");
  
  public String getUriAsString() {
    return this.uriAsStringProperty.get();
  }
  
  public ReadOnlyStringProperty uriAsStringProperty() {
    return this.uriAsStringProperty.getReadOnlyProperty();
  }
}
