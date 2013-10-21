package de.fxdiagram.core.behavior;

import com.google.common.base.Objects;
import de.fxdiagram.core.XDiagram;
import de.fxdiagram.core.XNode;
import de.fxdiagram.core.XRoot;
import de.fxdiagram.core.XShape;
import de.fxdiagram.core.behavior.AbstractHostBehavior;
import de.fxdiagram.core.behavior.Behavior;
import de.fxdiagram.core.behavior.NavigationBehavior;
import de.fxdiagram.core.extensions.BoundsExtensions;
import de.fxdiagram.core.extensions.CoreExtensions;
import de.fxdiagram.core.tools.actions.ScrollToAndScaleTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class DiagramNavigationBehavior extends AbstractHostBehavior<XDiagram> implements NavigationBehavior {
  public DiagramNavigationBehavior(final XDiagram host) {
    super(host);
  }
  
  protected void doActivate() {
  }
  
  public Class<? extends Behavior> getBehaviorKey() {
    return NavigationBehavior.class;
  }
  
  public boolean next() {
    XDiagram _host = this.getHost();
    final ObservableList<XNode> nodes = _host.getNodes();
    final Function1<XNode,Boolean> _function = new Function1<XNode,Boolean>() {
      public Boolean apply(final XNode it) {
        boolean _selected = it.getSelected();
        return Boolean.valueOf(_selected);
      }
    };
    final XNode current = IterableExtensions.<XNode>findFirst(nodes, _function);
    XNode _xifexpression = null;
    boolean _notEquals = (!Objects.equal(current, null));
    if (_notEquals) {
      int _indexOf = nodes.indexOf(current);
      int _plus = (_indexOf + 1);
      int _size = nodes.size();
      int _modulo = (_plus % _size);
      XNode _get = nodes.get(_modulo);
      _xifexpression = _get;
    } else {
      XNode _head = IterableExtensions.<XNode>head(nodes);
      _xifexpression = _head;
    }
    final XNode next = _xifexpression;
    if (next!=null) {
      this.reveal(next);
    }
    return (!Objects.equal(next, null));
  }
  
  public boolean previous() {
    XDiagram _host = this.getHost();
    final ObservableList<XNode> nodes = _host.getNodes();
    final Function1<XNode,Boolean> _function = new Function1<XNode,Boolean>() {
      public Boolean apply(final XNode it) {
        boolean _selected = it.getSelected();
        return Boolean.valueOf(_selected);
      }
    };
    final XNode current = IterableExtensions.<XNode>findLast(nodes, _function);
    XNode _xifexpression = null;
    boolean _notEquals = (!Objects.equal(current, null));
    if (_notEquals) {
      int _indexOf = nodes.indexOf(current);
      int _size = nodes.size();
      int _plus = (_indexOf + _size);
      int _minus = (_plus - 1);
      int _size_1 = nodes.size();
      int _modulo = (_minus % _size_1);
      XNode _get = nodes.get(_modulo);
      _xifexpression = _get;
    } else {
      XNode _last = IterableExtensions.<XNode>last(nodes);
      _xifexpression = _last;
    }
    final XNode previous = _xifexpression;
    if (previous!=null) {
      this.reveal(previous);
    }
    return (!Objects.equal(previous, null));
  }
  
  protected ScrollToAndScaleTransition reveal(final XShape node) {
    XDiagram _host = this.getHost();
    XRoot _root = CoreExtensions.getRoot(_host);
    Bounds _boundsInLocal = node.getBoundsInLocal();
    Point2D _center = BoundsExtensions.center(_boundsInLocal);
    Point2D _localToDiagram = CoreExtensions.localToDiagram(node, _center);
    ScrollToAndScaleTransition _scrollToAndScaleTransition = new ScrollToAndScaleTransition(_root, _localToDiagram, 1);
    final Procedure1<ScrollToAndScaleTransition> _function = new Procedure1<ScrollToAndScaleTransition>() {
      public void apply(final ScrollToAndScaleTransition it) {
        final EventHandler<ActionEvent> _function = new EventHandler<ActionEvent>() {
          public void handle(final ActionEvent it) {
            XDiagram _host = DiagramNavigationBehavior.this.getHost();
            XRoot _root = CoreExtensions.getRoot(_host);
            Iterable<XShape> _currentSelection = _root.getCurrentSelection();
            final Procedure1<XShape> _function = new Procedure1<XShape>() {
              public void apply(final XShape it) {
                it.setSelected(false);
              }
            };
            IterableExtensions.<XShape>forEach(_currentSelection, _function);
            node.setSelected(true);
          }
        };
        it.setOnFinished(_function);
        it.play();
      }
    };
    ScrollToAndScaleTransition _doubleArrow = ObjectExtensions.<ScrollToAndScaleTransition>operator_doubleArrow(_scrollToAndScaleTransition, _function);
    return _doubleArrow;
  }
}