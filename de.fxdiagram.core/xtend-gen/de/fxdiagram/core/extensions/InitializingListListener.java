package de.fxdiagram.core.extensions;

import com.google.common.base.Objects;
import java.util.List;
import javafx.collections.ListChangeListener;
import org.eclipse.xtend.lib.Property;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class InitializingListListener<T extends Object> implements ListChangeListener<T> {
  @Property
  private Procedure1<? super ListChangeListener.Change<? extends T>> _change;
  
  @Property
  private Procedure1<? super T> _add;
  
  @Property
  private Procedure1<? super T> _remove;
  
  public void onChanged(final ListChangeListener.Change<? extends T> c) {
    Procedure1<? super ListChangeListener.Change<? extends T>> _change = this.getChange();
    boolean _notEquals = (!Objects.equal(_change, null));
    if (_notEquals) {
      Procedure1<? super ListChangeListener.Change<? extends T>> _change_1 = this.getChange();
      _change_1.apply(c);
    }
    while (c.next()) {
      boolean _and = false;
      Procedure1<? super T> _add = this.getAdd();
      boolean _notEquals_1 = (!Objects.equal(_add, null));
      if (!_notEquals_1) {
        _and = false;
      } else {
        boolean _wasAdded = c.wasAdded();
        _and = _wasAdded;
      }
      if (_and) {
        List<? extends T> _addedSubList = c.getAddedSubList();
        final Procedure1<T> _function = new Procedure1<T>() {
          public void apply(final T it) {
            Procedure1<? super T> _add = InitializingListListener.this.getAdd();
            _add.apply(it);
          }
        };
        IterableExtensions.forEach(_addedSubList, _function);
      }
    }
    boolean _and = false;
    Procedure1<? super T> _remove = this.getRemove();
    boolean _notEquals_1 = (!Objects.equal(_remove, null));
    if (!_notEquals_1) {
      _and = false;
    } else {
      boolean _wasRemoved = c.wasRemoved();
      _and = _wasRemoved;
    }
    if (_and) {
      List<? extends T> _removed = c.getRemoved();
      final Procedure1<T> _function = new Procedure1<T>() {
        public void apply(final T it) {
          Procedure1<? super T> _remove = InitializingListListener.this.getRemove();
          _remove.apply(it);
        }
      };
      IterableExtensions.forEach(_removed, _function);
    }
  }
  
  @Pure
  public Procedure1<? super ListChangeListener.Change<? extends T>> getChange() {
    return this._change;
  }
  
  public void setChange(final Procedure1<? super ListChangeListener.Change<? extends T>> change) {
    this._change = change;
  }
  
  @Pure
  public Procedure1<? super T> getAdd() {
    return this._add;
  }
  
  public void setAdd(final Procedure1<? super T> add) {
    this._add = add;
  }
  
  @Pure
  public Procedure1<? super T> getRemove() {
    return this._remove;
  }
  
  public void setRemove(final Procedure1<? super T> remove) {
    this._remove = remove;
  }
}
