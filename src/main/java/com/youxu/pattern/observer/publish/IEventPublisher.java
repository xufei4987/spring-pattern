package com.youxu.pattern.observer.publish;

import com.youxu.pattern.observer.event.AbstractEvent;

public interface IEventPublisher<E extends AbstractEvent> {
    void publishEvent(E e);
}
