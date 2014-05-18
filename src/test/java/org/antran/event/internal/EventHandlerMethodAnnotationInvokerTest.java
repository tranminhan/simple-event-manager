package org.antran.event.internal;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.antran.event.api.EventHandler;
import org.junit.Test;

public class EventHandlerMethodAnnotationInvokerTest
{
    AnnotatedMethodInvoker methodAnnotationInvoker = new EventHandlerMethodAnnotationInvoker();
    
    @Test
    public void shouldCheckOneAnnotatedMethod()
    {
        // given
        SingleMethodTestEventHandler eventHandler = new SingleMethodTestEventHandler();
        
        // when
        Method[] annotatedMethods = methodAnnotationInvoker.getEventHandlerMethods(eventHandler);
        
        // then
        assertNotNull(annotatedMethods);
        assertEquals(1, annotatedMethods.length);
    }
    
    @Test
    public void shouldCheckZeroAnnotatedMethod()
    {
        // given
        Object eventHandler = new Object();
        
        // when
        Method[] annotatedMethods = methodAnnotationInvoker.getEventHandlerMethods(eventHandler);
        
        // then
        assertNotNull(annotatedMethods);
        assertEquals(0, annotatedMethods.length);
    }
    
    @Test
    public void shouldCheckTwoAnnotatedMethods()
    {
        // given
        Object eventHandler = new Object()
        {
            @EventHandler
            public void handleEvent1(Object event)
            {
                System.out.println("handle event1");
            }
            
            @EventHandler
            public void handleEvent2(Object event)
            {
                System.out.println("handle event1");
            }
        };
        
        // when
        Method[] annotatedMethods = methodAnnotationInvoker.getEventHandlerMethods(eventHandler);
        
        // then
        assertNotNull(annotatedMethods);
        assertEquals(2, annotatedMethods.length);
    }
    
    @Test
    public void shouldInvokeAnnotatedMethod()
    {
        // given
        SingleMethodTestEventHandler eventHandler = new SingleMethodTestEventHandler();
        Method[] annotatedMethods = methodAnnotationInvoker.getEventHandlerMethods(eventHandler);
        assertEquals(1, annotatedMethods.length);
        
        // when
        methodAnnotationInvoker.invoke(annotatedMethods[0], eventHandler, new String("Test method invoker"));
         
        // then
        assertEquals("Test method invoker", eventHandler.getLastEvent());
    }
    
}
