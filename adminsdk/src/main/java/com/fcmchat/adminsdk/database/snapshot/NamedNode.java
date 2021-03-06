/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fcmchat.adminsdk.database.snapshot;

public class NamedNode {

  private static final NamedNode MIN_NODE = new NamedNode(ChildKey.getMinName(), EmptyNode.Empty());
  private static final NamedNode MAX_NODE = new NamedNode(ChildKey.getMaxName(), Node.MAX_NODE);
  private final ChildKey name;
  private final Node node;

  public NamedNode(ChildKey name, Node node) {
    this.name = name;
    this.node = node;
  }

  public static NamedNode getMinNode() {
    return MIN_NODE;
  }

  public static NamedNode getMaxNode() {
    return MAX_NODE;
  }

  public ChildKey getName() {
    return this.name;
  }

  public Node getNode() {
    return this.node;
  }

  @Override
  public String toString() {
    return "NamedNode{" + "name=" + name + ", node=" + node + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    NamedNode namedNode = (NamedNode) o;

    if (!name.equals(namedNode.name)) {
      return false;
    }
    if (!node.equals(namedNode.node)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + node.hashCode();
    return result;
  }
}
