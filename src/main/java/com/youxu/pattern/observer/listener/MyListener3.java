package com.youxu.pattern.observer.listener;

import com.youxu.pattern.observer.event.SunnyEvent;
import org.springframework.stereotype.Component;

@Component
public class MyListener3 implements IListener<SunnyEvent> {
    @Override
    public void onEvent(SunnyEvent sunnyEvent) {
        System.out.println("出太阳了1");
        System.out.println(sunnyEvent.getData());

    }
}
