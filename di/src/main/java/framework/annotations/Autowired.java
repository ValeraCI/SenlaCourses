package framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Autowired {
}
