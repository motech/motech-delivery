package org.motechproject.deliverytools.seed;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Seed {
    int priority();
    String version();
}
