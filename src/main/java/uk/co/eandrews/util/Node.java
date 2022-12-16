package uk.co.eandrews.util;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Node<T> {
    private T id;

    private List<Node<T>> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<Node<T>, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node<T> destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(T id) {
        this.id = id;
    }
}
