package uk.co.eandrews.advent2022.day.day16;

import lombok.Builder;
import lombok.Getter;
import uk.co.eandrews.util.dijkstra.Node;

@Builder
@Getter
public class Distance<T> {
    private Node<T> startNode;
    private Node<T> endNode;
    private int distance;
}
