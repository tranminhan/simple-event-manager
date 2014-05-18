package org.antran.event.api;

public interface EventPublisher extends EventHandlerRegistrar
{
    
    void publish(Object event);
}
