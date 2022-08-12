package github.simple.dag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhoup
 */
public abstract class AbstractNode implements Node, InvocationHandler {
    private final static Logger log = LoggerFactory.getLogger(AbstractNode.class);
    private boolean executed = false;
    final String proxyMethodName = "run";
    private final String name;

    public AbstractNode(String name) {
        this.name = name;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (proxyMethodName.equals(method.getName())) {
            if (method.getReturnType().equals(Bundle.class) && method.getParameterCount() == 0) {
                Bundle bundle = (Bundle) method.invoke(this, args);
                getContext().getResultMap().put(id(), bundle);
                setExecuted(true);
                log.debug("{} 节点已经被运行.", this);
                return bundle;
            }
        }
        return method.invoke(this, args);
    }

    public Node proxy() {
        return (Node) Proxy.newProxyInstance(getClass().getClassLoader(), new Class<?>[]{Node.class}, this);
    }

    @Override
    public GlobalContext getContext() {
        return GlobalContextProvider.getGlobalContext();
    }

    @Override
    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    @Override
    public Bundle getRunResult() {
        return getContext().getNodeReturn(this);
    }

    @Override
    public boolean executed() {
        return executed;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            return id().equals(((Node) obj).id());
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getName() {
        return name;
    }
}
