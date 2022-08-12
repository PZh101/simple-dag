package github.simple.dag;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @author zhoup
 */
public class RunPath extends LinkedHashSet<Node> {
    public RunPath() {
        super();
    }

    public RunPath(Collection<? extends Node> c) {
        super(c);
    }
}
