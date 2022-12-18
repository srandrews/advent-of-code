package uk.co.eandrews.advent2022.day.day16;

import org.springframework.stereotype.Component;
import uk.co.eandrews.util.dijkstra.Graph;
import uk.co.eandrews.util.dijkstra.Node;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ValveInputParser implements InputParser<Graph<Valve>> {

    private static final String VALVE_DETAILS_REGEX = "Valve (.+) has flow rate=(\\d+);.+";
    private static final String TUNNEL_DETAILS_REGEX = "Valve (.+) has flow rate=\\d+; (?:tunnel|tunnels) (?:lead|leads) to (?:valve|valves) (.+)";
    private static final Pattern VALVE_DETAILS_PATTERN = Pattern.compile(VALVE_DETAILS_REGEX);
    private static final Pattern TUNNEL_DETAILS_PATTERN = Pattern.compile(TUNNEL_DETAILS_REGEX);

    @Override
    public Graph<Valve> parse(Stream<String> input) {
        List<String> inputList = input.toList();

        Map<String, Node<Valve>> valves = inputList.stream()
             .map(s -> {
                 Matcher matcher = VALVE_DETAILS_PATTERN.matcher(s);
                 if (matcher.matches()) {
                     return  new Valve(matcher.group(1), Integer.parseInt(matcher.group(2)));
                 } else {
                     throw new RuntimeException("Can't match string");
                 }
             })
             .map(Node::new)
             .collect(Collectors.toMap(valveNode -> valveNode.getId().name(), valveNode -> valveNode));

        inputList.forEach(s -> {
                Matcher matcher = TUNNEL_DETAILS_PATTERN.matcher(s);
                if (matcher.matches()) {
                    Node<Valve> node = valves.get(matcher.group(1));
                    Arrays.stream(matcher.group(2).split(","))
                        .forEach(valve -> {
                            Node<Valve> adjacentNode = valves.get(valve.trim());
                            node.addDestination(adjacentNode, 1);
                        });
                } else {
                    throw new RuntimeException("Can't match string");
                }
            });

        Graph<Valve> graph = new Graph<>();
        valves.values().forEach(graph::addNode);

        return graph;

    }
}