import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrawGraph {
    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing");

        String input = "[ (I, 2), (A, 5), (E, 4), (F, 2), (T, 2), (S, 3) ]";
        List<VertexData> vertices = parseInput(input);
        createAndDisplayGraph(vertices);
    }

    private static List<VertexData> parseInput(String input) {
        List<VertexData> vertices = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\(([A-Z]),\\s*(\\d+)\\)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String letter = matcher.group(1);
            int x = Integer.parseInt(matcher.group(2));
            vertices.add(new VertexData(letter, x));
        }

        return vertices;
    }

    private static void createAndDisplayGraph(List<VertexData> vertices) {
        Graph graph = new SingleGraph("Circular Graph");
        graph.setAttribute("ui.stylesheet",
                "node { size: 30px; fill-color: #3d8bff; text-size: 20; }" +
                        "edge { fill-color: #666; size: 2px; }");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

        int n = vertices.size();
        if (n == 0) return;

        for (VertexData vd : vertices) {
            Node node = graph.addNode(vd.letter);
            node.setAttribute("ui.label", vd.letter);
            double angle = 2 * Math.PI * vertices.indexOf(vd) / n;
            node.setAttribute("x", Math.cos(angle));
            node.setAttribute("y", Math.sin(angle));
        }

        for (int i = 0; i < n; i++) {
            String source = vertices.get(i).letter;
            int x = vertices.get(i).x;

            int rightPos = (i + x) % n;
            int leftPos = (i - x) % n;
            if (leftPos < 0) leftPos += n;


            String rightTarget = vertices.get(rightPos).letter;
            graph.addEdge(source + "→" + rightTarget, source, rightTarget, true)
                    .setAttribute("ui.label", "x=" + x);

            if (rightPos != leftPos) {
                String leftTarget = vertices.get(leftPos).letter;
                graph.addEdge(source + "←" + leftTarget, source, leftTarget, true)
                        .setAttribute("ui.label", "x=" + x);
            }
        }


        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
        viewer.enableAutoLayout();
    }

    static class VertexData {
        String letter;
        int x;

        VertexData(String letter, int x) {
            this.letter = letter;
            this.x = x;
        }
    }
}