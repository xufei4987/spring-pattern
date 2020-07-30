package com.youxu.pattern.observer.listener;

import com.youxu.pattern.observer.event.AbstractEvent;

public interface IListener<E extends AbstractEvent> {
    void onEvent(E e);
}
