package cz.lidinsky.tree;

/*
 *  Copyright 2016 Jiri Lidinsky
 *
 *  This file is part of java tools library.
 *
 *  java tools is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, version 3.
 *
 *  java tools library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with java tools library.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *  A node of the tree.
 */
public class Node<T> {

  protected Node<T> parent;

  protected ArrayList<Node<T>> children;

  /**
   * Create new root node.
   */
  public Node() {
    this.parent = null;
  }

  /**
   * Creates new node and adds it as a leaf to the parent node.
   */
  public Node(Node parent) {
    if (parent != null) {
      parent.addChild(this);
      this.parent = parent;
    } else {
      this.parent = null;
    }
  }

  public void addChild(Node node) {
    if (node != null) {
      if (node.parent != null) {
        parent.remove(node);
      }
      if (children == null) {
        children = new ArrayList<>();
      }
      children.add(node);
    }
  }

  public void remove(Node node) {
    if (node != null) {
      if (node.parent == this) {
        children.remove(node);
        node.parent = null;
      }
    }
  }

  public void remove(int index) {
    Node node = children.get(index);
    node.parent = null;
    children.remove(index);
  }

  /**
   * Returns parent of this node or null if this parent is the root node.
   */
  public Node<T> getParent() {
    return parent;
  }

  public List<Node<T>> getChildren() {
    return children == null
            ? Collections.emptyList()
            : Collections.unmodifiableList(children);
  }

  public List<T> getDecoratedChildren() {
    return new ChildList(this);
  }

  public Node<T> getChild(int index) {
    if (children != null) {
      return children.get(index);
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  public int getIndexOfChild(Node<T> node) {
    if (children != null) {
      return children.indexOf(node);
    } else {
      throw new NoSuchElementException();
    }
  }

  public boolean isRoot() {
    return parent == null;
  }

  public boolean isLeaf() {
    return children == null || children.size() == 0;
  }

  public boolean hasChildren() {
    return !isLeaf();
  }

  public boolean isSibling(Node<T> node) {
    //notNull(node);
    if (node == null) {
      throw new NullPointerException();
    }
    if (node == this) {
      return true; // ???
    } else if (parent == null) {
      return false;
    } else {
      return parent == node.getParent();
    }
  }

  public Node<T> getRoot() {
    Node<T> node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    return node;
  }

  private T decorated;

  public T getDecorated() {
    return decorated;
  }

  public void setDecorated(T object) {
    this.decorated = object;
  }

}
