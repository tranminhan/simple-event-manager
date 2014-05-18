package org.antran.event.internal;

import java.lang.reflect.Method;

public interface AnnotatedMethodInvoker
{
    
    Method[] getEventHandlerMethods(Object eventHandler);
    
    void invoke(Method method, Object eventHandler, Object event);
    
}