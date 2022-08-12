package github.simple.dag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhoup
 */
public class GlobalContext {
    /**
     * 记录每个节点的运行结果
     */
    private final Map<Serializable, Bundle> resultMap = new ConcurrentHashMap<>();
    /**
     * 记录运行路径
     */
    private final List<RunPath> paths = new ArrayList<>();
    /**
     * 配置
     */
    private Configuration configuration;

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration == null ? new Configuration() : configuration;
    }

    public boolean canEnd() {
        return false;
    }

    public Map<Serializable, Bundle> getResultMap() {
        return resultMap;
    }

    public Bundle getNodeReturn(Node node) {
        return resultMap.get(node.id());
    }

    public List<RunPath> getPaths() {
        return paths;
    }

    public void addNewPath(RunPath nodes) {
        paths.add(nodes);
    }
}
