package com.longforus.test.a10_voidioc;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Void Young on 8/22/2016.
 */
public class VoidIoc {

    private static Class clazz;

    public static void inject(Activity activity) {//注入注册方法需要首先被使用类在onCreate()或onStare()调用
        clazz = activity.getClass();//反射得到activity的字节码文件
        bindViewId(activity);
        bindClickEvent(activity);
    }

    private static void bindClickEvent(final Activity activity) {//绑定点击事件
        Method[] methods = clazz.getDeclaredMethods();//暴力反射得到所有的方法
        for (int i = 0; i < methods.length; i++) {
            final Method m = methods[i];//遍历所有方法
            VoidIocClick iocClick = m.getAnnotation(VoidIocClick.class);
            if (iocClick != null) {//如果该方法Annotation不为null
                int id = iocClick.value();
                final View view = activity.findViewById(id);//获取Annotation的value就是要绑定监听器的View ID 在activity中找到它
                view.setOnClickListener(new View.OnClickListener() {//为它绑定点击事件 如果它被点击就调用 反射到的方法
                    @Override
                    public void onClick(View v) {
                        try {
                            m.setAccessible(true);//设置可访问性
                            m.invoke(activity, view);//参数1:调用哪个对象的这个方法 参数2:这个方法的实参
                        }  catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    private static void bindViewId(Activity activity) {
        Field[] fields = clazz.getDeclaredFields();//获取所有的field
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            VoidIocId iocId = f.getAnnotation(VoidIocId.class);
            if (iocId != null) {//如果这个field的Annotation不为null
                int id = iocId.value();
                View view = activity.findViewById(id);//找到这个ID代表的view
                f.setAccessible(true);
                try {
                    f.set(activity,view);//把找到的view设为这个field的值
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
