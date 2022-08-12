package github.simple.dag;

/**
 * @author zhoup
 */
public class ClassDescUtil {
    public static String desc(Object obj) {
        return obj.getClass().getName() + "@" + Integer.toHexString(obj.hashCode());
    }
}
