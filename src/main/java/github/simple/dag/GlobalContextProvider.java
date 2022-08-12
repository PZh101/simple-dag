package github.simple.dag;

/**
 * @author zhoup
 */
public class GlobalContextProvider {
    private volatile static GlobalContext globalContext = new GlobalContext();

    public synchronized static GlobalContext getGlobalContext() {
        if (globalContext != null) {
            return globalContext;
        }
        return new GlobalContext();
    }

    public synchronized static void cleanContext() {
        globalContext = null;
    }
}
