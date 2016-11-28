package cz.lidinsky.tree;

import java.util.AbstractList;

class ChildList<T> extends AbstractList {

  private final Node<T> node;

  ChildList(Node<T> node) {
    this.node = node;
  }

  @Override
  public T get(int index) {
    return node.getChild(index).getDecorated();
  }

  @Override
  public int size() {
    return node.getChildren().size();
  }

}
