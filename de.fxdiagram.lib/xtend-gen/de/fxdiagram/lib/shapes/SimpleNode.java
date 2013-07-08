package de.fxdiagram.lib.shapes;

import de.fxdiagram.core.XNode;
import de.fxdiagram.lib.shapes.AddRapidButtonBehavior;
import de.fxdiagram.lib.shapes.RectangleBorderPane;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class SimpleNode extends XNode {
  private String name;
  
  public SimpleNode(final String name) {
    this.name = name;
    RectangleBorderPane _rectangleBorderPane = new RectangleBorderPane();
    final Procedure1<RectangleBorderPane> _function = new Procedure1<RectangleBorderPane>() {
        public void apply(final RectangleBorderPane it) {
          Text _text = new Text();
          final Text label = _text;
          ObservableList<Node> _children = it.getChildren();
          final Procedure1<Text> _function = new Procedure1<Text>() {
              public void apply(final Text it) {
                it.setText(name);
                it.setTextOrigin(VPos.TOP);
              }
            };
          Text _doubleArrow = ObjectExtensions.<Text>operator_doubleArrow(label, _function);
          _children.add(_doubleArrow);
          Insets _insets = new Insets(10, 20, 10, 20);
          StackPane.setMargin(label, _insets);
        }
      };
    RectangleBorderPane _doubleArrow = ObjectExtensions.<RectangleBorderPane>operator_doubleArrow(_rectangleBorderPane, _function);
    this.setNode(_doubleArrow);
    Node _node = this.getNode();
    InnerShadow _innerShadow = new InnerShadow();
    final Procedure1<InnerShadow> _function_1 = new Procedure1<InnerShadow>() {
        public void apply(final InnerShadow it) {
          it.setRadius(7);
        }
      };
    InnerShadow _doubleArrow_1 = ObjectExtensions.<InnerShadow>operator_doubleArrow(_innerShadow, _function_1);
    _node.setEffect(_doubleArrow_1);
    this.setKey(name);
  }
  
  public void doActivate() {
    super.doActivate();
    AddRapidButtonBehavior _addRapidButtonBehavior = new AddRapidButtonBehavior(this);
    final AddRapidButtonBehavior rapidButtonBehavior = _addRapidButtonBehavior;
    rapidButtonBehavior.activate();
  }
  
  public String toString() {
    return this.name;
  }
}