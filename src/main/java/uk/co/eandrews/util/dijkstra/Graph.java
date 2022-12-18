package uk.co.eandrews.util.dijkstra;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Graph<T> {
    private Set<Node<T>> nodes = new HashSet<>();

    public void addNode(Node<T> nodeA) {
        nodes.add(nodeA);
    }

    // getters and setters
}