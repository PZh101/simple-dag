package github.simple.dag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * handle path
 *
 * @author zhoup
 */
public class PathUtil {
    private final static Logger log = LoggerFactory.getLogger(PathUtil.class);

    public static RunPath emptyPath() {
        return new RunPath();
    }

    public static RunPath clonePath(Set<Node> path) {
        return new RunPath(path);
    }

    public static void addPathToContext(Node node, RunPath runPath) {
        node.getContext().addNewPath(runPath);
    }

    public static void addToRunPath(Node node, RunPath runPath) {
        runPath.add(node);
    }

    public static void showPath(GlobalContext context) {
        log.debug("运行路径:");
        List<RunPath> paths = context.getPaths();
        for (RunPath path : paths) {
            boolean first = true;
            for (Node node : path) {
                if (first) {
                    System.out.print("path: " + node);
                    first = false;
                } else {
                    System.out.print("--->" + node);
                }
            }
            System.out.println();
        }
        log.debug("show over");
    }
}
