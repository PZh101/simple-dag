package github.simple.dag;

import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @author zhoup
 */
public class Bundle {
    private final static Logger log = LoggerFactory.getLogger(Bundle.class);
    private final Type type;
    private Object value;

    public Bundle(Object value, Type type) {
        Objects.requireNonNull(type);
        validateType(value, type);
        this.type = type;
        this.value = value;
    }


    public static <T> Bundle of(T value, Class<T> type) {
        return new Bundle(value, type);
    }

    @SuppressWarnings(value = "unchecked")
    public <T> T getValue() {
        try {
            T ret = (T) this.value;
            if (ret != null) {
                validateType(ret, this.type);
            }
            return ret;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public <T> void setValue(T value) {
        validateType(value, type);
        this.value = value;
    }

    private void validateType(@NotNull Object val, @NotNull Type type) {
        if (val == null) {
            return;
        }
        if (!val.getClass().getTypeName().equals(type.getTypeName())) {
            throw new TypeNotPresentException(val.getClass().getTypeName(), null);
        }
    }

    public Type getType() {
        return type;
    }
}
