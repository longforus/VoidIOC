package com.longforus.test.a10_voidioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Void Young on 8/22/2016.
 */
@Target(ElementType.FIELD)//目标类型只能是field
@Retention(RetentionPolicy.RUNTIME)//保留到什么时候?保留到JVM中runtime
public @interface VoidIocId {
    public int value();//方法名为value时调用可以不用加方法名
}
