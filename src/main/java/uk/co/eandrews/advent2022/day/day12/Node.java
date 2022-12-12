package uk.co.eandrews.advent2022.day.day12;

import lombok.Getter;
import lombok.Setter;
import uk.co.eandrews.util.Vector2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Node {
    private Vector2 vector;

    private List<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(Vector2 vector) {
        this.vector = vector;
    }
}
