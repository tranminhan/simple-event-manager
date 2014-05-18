package org.antran.event.internal;

import org.antran.event.api.EventHandler;

public class SingleMethodTestEventHandler
{
    private Object lastEvent = "Hello World";
    
    @EventHandler
    public void handleEvent(Object event)
    {
        this.lastEvent = event;
        System.out.println(event);
    }
    
    public Object getLastEvent()
    {
        return lastEvent;
    }
}
