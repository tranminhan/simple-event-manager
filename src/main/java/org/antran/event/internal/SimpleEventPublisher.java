package org.antran.event.internal;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.antran.event.api.EventPublisher;
import org.springframework.util.Assert;

public class SimpleEventPublisher implements EventPublisher
{
    private final List<Object> allEventHandlers = new ArrayList<Object>();
    private final AnnotatedMethodInvoker methodInvoker;
    
    public SimpleEventPublisher(final AnnotatedMethodInvoker methodInvoker)
    {
        Assert.notNull(methodInvoker);
        
        this.methodInvoker = methodInvoker;
    }
    
    public void register(Object eventHandler)
    {
        allEventHandlers.add(eventHandler);
    }
    
    public void unregister(Object eventHandler)
    {
        // TODO Auto-generated method stub
        
    }
    
    public void unregisterAll()
    {
        // TODO Auto-generated method stub
        
    }
    
    public void publish(Object event)
    {
        Assert.notNull(event);
        
        for (Object eventHandler : allEventHandlers)
        {
            Method[] annotatedMethods = methodInvoker.getEventHandlerMethods(eventHandler);
            Assert.isTrue(annotatedMethods.length == 1);
            
            // whens
            methodInvoker.invoke(annotatedMethods[0], eventHandler, event);
        }
    }
    
    public int countOfRegisteredEventHandler()
    {
        return allEventHandlers.size();
    }
    
}
