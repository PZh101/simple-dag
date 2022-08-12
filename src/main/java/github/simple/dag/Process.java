package github.simple.dag;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 树执行引擎
 *
 * @author zhoup
 */
public class Process {
    DAGEngine engine;

    public Process(DAGEngine engine) {
        this.engine = engine;
    }

    public void startRunNode(Node node) {
        RunPath runPath = PathUtil.emptyPath();
        PathUtil.addPathToContext(node, runPath);
        runNode(node, runPath);
    }

    public void runNode(Node node, RunPath runPath) {
        if (node.getContext().getConfiguration().isCheckLoop()) {
            checkLoop(runPath, node);
        }
        PathUtil.addToRunPath(node, runPath);
        List<Node> nodes = engine.runCurrent(node);
        boolean first = true;
        RunPath tempPath = PathUtil.clonePath(runPath);
        List<CompletableFuture<?>> asyncTask = new ArrayList<>();
        for (Node candidate : nodes) {
            RunPath newRunPath;
            if (first) {
                newRunPath = runPath;
                first = false;
            } else {
                newRunPath = PathUtil.clonePath(tempPath);
                PathUtil.addPathToContext(node, newRunPath);
            }
            if (node.getContext().getConfiguration().isUseMultiThread()) {
                asyncTask.add(CompletableFuture.runAsync(() -> runNode(candidate, newRunPath)));
            } else {
                runNode(candidate, newRunPath);
            }
        }
        if (node.getContext().getConfiguration().isUseMultiThread()) {
            CompletableFuture<?>[] tasks = new CompletableFuture<?>[asyncTask.size()];
            CompletableFuture.allOf(asyncTask.toArray(tasks)).join();
        }
    }

    public void checkLoop(RunPath runPath, Node node) {
        if (runPath.contains(node)) {
            throw new UnsupportedOperationException("暂不支持有向有环图运行");
        }
    }
}
