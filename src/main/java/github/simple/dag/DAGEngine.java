package github.simple.dag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoup
 */
public class DAGEngine {

    private final static Logger log = LoggerFactory.getLogger(DAGEngine.class);

    private final DAG dag;


    public DAGEngine(DAG dag) {
        this.dag = dag;
    }


    public List<Node> runCurrent(Node node) {
        Bundle ret = node.run();
        return nextNodes(node);
    }

    public List<Node> nextNodes(Node startNode) {
        Configuration conf = startNode.getContext().getConfiguration();
        List<Node> todoList = new ArrayList<>();
        if (!dag.contains(startNode)) {
            throw new IllegalArgumentException(startNode.desc() + "没有添加到DAG中");
        }
        for (Edge edge : dag.getEdges()) {
            if (isOrigin(edge, startNode)) {
                Node terminus = edge.terminus();
                terminus.setPrev(startNode);
                log.debug("{} 的下一个节点为 {} ;{} 的返回值 {}", startNode, terminus, startNode, startNode.getRunResult().getValue());
                if (terminus.executed() && conf.isFiltrateExecuted()) {
                    continue;
                }
                todoList.add(terminus);
            }
        }
        return todoList;
    }

    private boolean isOrigin(Edge edge, Node node) {
        return edge.origin().id().equals(node.id());
    }

    private void checkStartNode(Node node) {
        boolean found = dag.getEdges().stream().map(Edge::origin).anyMatch(node1 -> node1.equals(node));
        if (!found) {
            throw new IllegalArgumentException(node.desc() + "没有作为任何边的起点");
        }
    }
}
