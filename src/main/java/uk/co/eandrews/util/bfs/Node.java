package uk.co.eandrews.util.bfs;

import java.util.*;

public class Node<T> {
    private final T value;
    private final Set<Node<T>> neighbours;

    public Node(T value) {
        this.value = value;
        this.neighbours = new HashSet<>();
    }

    public void connect(Node<T> node) {
        if (this == node) throw new IllegalArgumentException("Can't connect node to itself");
        this.neighbours.add(node);
        node.neighbours.add(this);
    }

    public T getValue() {
        return value;
    }

    public Set<Node<T>> getNeighbours() {
        return neighbours;
    }
}
