package github.simple.dag;

/**
 * @author zhoup
 */
public class DirectedEdge implements Edge {
    private final Node origin;
    private final Node terminus;

    public DirectedEdge(Node origin, Node terminus) {
        this.origin = origin;
        this.terminus = terminus;
    }

    @Override
    public Node origin() {
        return origin;
    }

    @Override
    public Node terminus() {
        return terminus;
    }
}
