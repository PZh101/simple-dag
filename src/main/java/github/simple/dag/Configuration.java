package github.simple.dag;

/**
 * DAG运行配置
 *
 * @author zhoup
 */
public class Configuration {
    private boolean filtrateExecuted = true;
    private boolean checkLoop = false;
    private boolean useMultiThread = false;
    public boolean isFiltrateExecuted() {
        return filtrateExecuted;
    }

    public void setFiltrateExecuted(boolean filtrateExecuted) {
        this.filtrateExecuted = filtrateExecuted;
    }

    public boolean isCheckLoop() {
        return checkLoop;
    }

    public void setCheckLoop(boolean checkLoop) {
        this.checkLoop = checkLoop;
    }

    public boolean isUseMultiThread() {
        return useMultiThread;
    }

    public void setUseMultiThread(boolean useMultiThread) {
        this.useMultiThread = useMultiThread;
    }
}
