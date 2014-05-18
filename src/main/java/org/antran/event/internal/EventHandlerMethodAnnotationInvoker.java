package org.antran.event.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.antran.event.api.EventHandler;
import org.antran.event.api.HandleEventException;

public class EventHandlerMethodAnnotationInvoker implements AnnotatedMethodInvoker
{
    
    /* (non-Javadoc)
     * @see org.antran.event.internal.AnnotatedMethodInvoker#getEventHandlerMethods(java.lang.Object)
     */
    public Method[] getEventHandlerMethods(Object eventHandler)
    {
        List<Method> annotatedMethods = new ArrayList<Method>();
        
        Method[] allMethods = eventHandler.getClass().getMethods();
        for (Method method : allMethods)
        {
            if (method.isAnnotationPresent(EventHandler.class))
            {
                annotatedMethods.add(method);
            }
        }
        
        return annotatedMethods.toArray(new Method[0]);
    }
    
    /* (non-Javadoc)
     * @see org.antran.event.internal.AnnotatedMethodInvoker#invoke(java.lang.reflect.Method, java.lang.Object, java.lang.String)
     */
    public void invoke(Method method, Object eventHandler, Object event)
    {
        try
        {
            method.invoke(eventHandler, event);
        }
        catch (IllegalAccessException e)
        {
            throw new HandleEventException(e);
        }
        catch (IllegalArgumentException e)
        {
            throw new HandleEventException(e);
        }
        catch (InvocationTargetException e)
        {
            throw new HandleEventException(e);
        }
    }
    
}
