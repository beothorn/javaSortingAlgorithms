package com.github.beothorn.tests;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interceptor {

    static List<Object> objs = new ArrayList<>();

    public static void interceptMethod(
        Object self,
        Executable method,
        Object[] allArguments,
        Object returnValueFromMethod
    ){
        System.out.println(">>>>");
        System.out.println("self: "+self);
        System.out.println("method: "+method.getName());
        System.out.println("allArguments: "+ Arrays.toString(allArguments));
        System.out.println("returnValueFromMethod: "+returnValueFromMethod);
        System.out.println("<<<<");
    }

}
