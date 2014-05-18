package org.antran.event.api;

public interface EventHandlerRegistrar
{
    void register(Object eventHandler);
    
    void unregister(Object eventHandler);
    
    void unregisterAll();
}
