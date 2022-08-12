package github.simple.dag;

import java.io.Serializable;

/**
 * 节点
 *
 * @author zhoup
 */
public interface Node {
    /**
     * 运行当前节点
     *
     * @return 具体类型
     */
    Bundle run();

    /**
     * 获取全局上下文
     *
     * @return 上下文
     */
    GlobalContext getContext();

    /**
     * 当前是否被运行过
     *
     * @return true/false
     */
    boolean executed();

    /**
     * 修改运行状态
     *
     * @param executed 是否被执行
     */
    void setExecuted(boolean executed);

    /**
     * 节点的运行结果
     *
     * @return 节点的运行结果
     */
    Bundle getRunResult();

    /**
     * 记录上一个节点
     *
     * @param node 上一个节点
     */
    default void setPrev(Node node) {

    }

    /**
     * 获取上一个节点
     *
     * @return 上一个节点
     */
    default Node getPrev() {
        return null;
    }

    /**
     * 节点的说明
     *
     * @return xx
     */
    default String desc() {
        return ClassDescUtil.desc(this);
    }

    /**
     * 节点的id
     *
     * @return 当前节点的id
     */
    default Serializable id() {
        return ClassDescUtil.desc(this);
    }

    /**
     * 当前节点的名称
     *
     * @return 节点名称
     */
    default String getName() {
        return ClassDescUtil.desc(this);
    }
}
