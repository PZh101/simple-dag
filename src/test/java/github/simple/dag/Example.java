package github.simple.dag;


import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * 使用案例,或者说叫测试
 *
 * @author zhoup
 */
public class Example {
    static Random random = new Random();

    public static void main(String[] args) {
        GlobalContext globalContext = GlobalContextProvider.getGlobalContext();
        Configuration conf = new Configuration();
        conf.setUseMultiThread(true);
        globalContext.setConfiguration(conf);
//        DAG dag = mockdag();
        DAG dag = mockdag1();
        Node node1 = (Node) dag.getNodes().toArray()[0];
        DAGEngine engine = new DAGEngine(dag);

        Process process = new Process(engine);
        process.startRunNode(node1);
        PathUtil.showPath(node1.getContext());
        GlobalContextProvider.cleanContext();
    }

    static DAG mockdag1() {
        Node node1 = new MyNode("node1").proxy();
        Node node2 = new MyNode("node2").proxy();
        Node node3 = new MyNode("node3").proxy();
        Node node4 = new MyNode("node4").proxy();
        Node node5 = new MyNode("node5").proxy();
        Node node6 = new MyNode("node6").proxy();
        Node node7 = new MyNode("node7").proxy();

        Edge edge1 = new DirectedEdge(node1, node2);
        Edge edge2 = new DirectedEdge(node1, node3);
        Edge edge3 = new DirectedEdge(node2, node4);
        Edge edge4 = new DirectedEdge(node2, node5);
        Edge edge5 = new DirectedEdge(node4, node6);
        Edge edge6 = new DirectedEdge(node1, node7);
        Edge edge7 = new DirectedEdge(node6, node1);
        DAG dag = new DAG();
        Set<Node> nodes = new LinkedHashSet<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);
        nodes.add(node6);
        nodes.add(node7);
        dag.setNodes(nodes);

        Set<Edge> edges = new LinkedHashSet<>();
        edges.add(edge1);
        edges.add(edge2);
        edges.add(edge3);
        edges.add(edge4);
        edges.add(edge5);
        edges.add(edge6);
//        edges.add(edge7);
        dag.setEdges(edges);
        return dag;
    }


    static DAG mockdag() {
        Node node1 = new MyNode("node1").proxy();
        Node node2 = new MyNode("node2").proxy();
        Node node3 = new MyNode("node3").proxy();
        Node node4 = new MyNode("node4").proxy();
        Edge edge2 = new ConditionEdge(node1, node2, node3, (bundle -> bundle.<Double>getValue() < 0.5));
        Edge edge3 = new DirectedEdge(node2, node4);
        DAG dag = new DAG();
        Set<Node> nodes = new LinkedHashSet<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        dag.setNodes(nodes);

        Set<Edge> edges = new LinkedHashSet<>();
        edges.add(edge2);
        edges.add(edge3);
        dag.setEdges(edges);
        return dag;
    }
}

class MyNode extends AbstractNode {

    public MyNode(String name) {
        super(name);
    }

    @Override
    public Bundle run() {
        double v = Example.random.nextGaussian();
        System.out.println(id() + " run result is " + v);
        return Bundle.of(v, Double.class);
    }
}