package github.simple.dag;

/**
 * @author zhoup
 */
public interface Edge {
    /**
     * 获取边的起点
     *
     * @return 开始节点
     */
    Node origin();

    /**
     * 获取边的终点
     *
     * @return 终点
     */
    Node terminus();

}
