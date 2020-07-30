package com.youxu.pattern.observer.listener;

import com.youxu.pattern.observer.event.RainEvent;
import org.springframework.stereotype.Component;

@Component
public class MyListener1 implements IListener<RainEvent>{
    @Override
    public void onEvent(RainEvent rainEvent) {
        System.out.println("下雨了1");
        System.out.println(rainEvent.getData());
    }
}
