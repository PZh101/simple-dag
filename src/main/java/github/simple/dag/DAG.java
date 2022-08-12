package github.simple.dag;

import java.util.Set;

/**
 * 有向无环图
 *
 * @author zhoup
 */
public class DAG implements Graph {

    private Set<Node> nodes;
    private Set<Edge> edges;

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }

    public boolean contains(Node node) {
        return nodes.contains(node);
    }
}
