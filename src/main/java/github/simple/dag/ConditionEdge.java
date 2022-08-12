package github.simple.dag;

import java.util.function.Predicate;

/**
 * @author zhoup
 */
public class ConditionEdge implements Edge {
    private final Node origin;
    private final Node trueNode;
    private final Node falseNode;
    private final Predicate<Bundle> predicate;

    public ConditionEdge(Node origin, Node trueNode, Node falseNode, Predicate<Bundle> predicate) {
        this.origin = origin;
        this.trueNode = trueNode;
        this.falseNode = falseNode;
        this.predicate = predicate;
    }

    @Override
    public Node origin() {
        return origin;
    }

    @Override
    public Node terminus() {
        return selectNode();
    }

    private Node selectNode() {
        Bundle bundle = origin.getContext().getResultMap().get(origin.id());
        if (predicate.test(bundle)) {
            return trueNode;
        }
        return falseNode;
    }
}
