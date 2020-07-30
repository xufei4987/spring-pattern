package com.youxu.pattern.observer.listener;

import com.youxu.pattern.observer.event.RainEvent;
import org.springframework.stereotype.Component;

@Component
public class MyListener2 implements IListener<RainEvent>{
    @Override
    public void onEvent(RainEvent rainEvent) {
        System.out.println("下雨了2");
        System.out.println(rainEvent.getData());

    }
}
