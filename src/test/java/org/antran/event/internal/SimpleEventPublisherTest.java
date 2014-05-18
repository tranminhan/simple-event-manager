package org.antran.event.internal;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class SimpleEventPublisherTest
{
    
    SimpleEventPublisher eventPublisher = new SimpleEventPublisher(new EventHandlerMethodAnnotationInvoker());
    SingleMethodTestEventHandler eventHandler = new SingleMethodTestEventHandler();
    
    @Test
    public void shouldInvokeEventHandler()
    {
        // given
        
        // when
        eventPublisher.register(eventHandler);
        eventPublisher.publish(new String("Hello World"));
        
        // then
        assertEquals("Hello World", eventHandler.getLastEvent());
        
        // when another
        eventPublisher.publish(new String("Hello Jack"));
        
        // then another
        assertEquals("Hello Jack", eventHandler.getLastEvent());
    }
    
    @Test
    public void shouldStoreRegisteredEventHandler()
    {
        // given
        assertEquals(0, eventPublisher.countOfRegisteredEventHandler());
        
        // when
        eventPublisher.register(eventHandler);
        
        // then
        assertEquals(1, eventPublisher.countOfRegisteredEventHandler());
        
        // again
        eventPublisher.register(eventHandler);
        assertEquals(2, eventPublisher.countOfRegisteredEventHandler());
    }
}
