package de.fxdiagram.core.model;

import com.google.common.base.Objects;
import de.fxdiagram.core.model.ModelElement;
import de.fxdiagram.core.model.ModelFactory;
import de.fxdiagram.core.model.XModelProvider;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;

@SuppressWarnings("all")
public class Model {
  private Map<Object, ModelElement> index = new IdentityHashMap<Object, ModelElement>();
  
  private ModelElement rootElement;
  
  private ModelFactory modelFactory;
  
  public Model(final Object rootNode) {
    ModelFactory _modelFactory = new ModelFactory();
    this.modelFactory = _modelFactory;
    ModelElement _addElement = this.addElement(rootNode);
    this.rootElement = _addElement;
  }
  
  Map<Object, ModelElement> getIndex() {
    return this.index;
  }
  
  public ModelElement getRootElement() {
    return this.rootElement;
  }
  
  public ModelElement getModelElement(final XModelProvider node) {
    return this.index.get(node);
  }
  
  ModelElement addElement(final Object node) {
    ModelElement _xblockexpression = null;
    {
      if ((node instanceof XModelProvider)) {
        boolean _isTransient = ((XModelProvider)node).isTransient();
        if (_isTransient) {
          return null;
        }
      }
      final ModelElement existingElement = this.index.get(node);
      boolean _notEquals = (!Objects.equal(existingElement, null));
      if (_notEquals) {
        return existingElement;
      }
      ModelElement _elvis = null;
      if (existingElement != null) {
        _elvis = existingElement;
      } else {
        ModelElement _createElement = this.modelFactory.createElement(node);
        _elvis = _createElement;
      }
      final ModelElement element = _elvis;
      this.index.put(node, element);
      List<? extends Property<?>> _properties = element.getProperties();
      final Consumer<Property<?>> _function = (Property<?> it) -> {
        boolean _isPrimitive = element.isPrimitive(it);
        boolean _not = (!_isPrimitive);
        if (_not) {
          Object _value = it.getValue();
          if (_value!=null) {
            this.addElement(_value);
          }
        }
      };
      _properties.forEach(_function);
      List<? extends ListProperty<?>> _listProperties = element.getListProperties();
      final Consumer<ListProperty<?>> _function_1 = (ListProperty<?> it) -> {
        boolean _isPrimitive = element.isPrimitive(it);
        boolean _not = (!_isPrimitive);
        if (_not) {
          ObservableList<?> _value = it.getValue();
          final Consumer<Object> _function_2 = (Object it_1) -> {
            if (it_1!=null) {
              this.addElement(it_1);
            }
          };
          _value.forEach(_function_2);
        }
      };
      _listProperties.forEach(_function_1);
      _xblockexpression = element;
    }
    return _xblockexpression;
  }
}
