package com.youxu.pattern.observer.publish;

import com.youxu.pattern.observer.event.AbstractEvent;
import com.youxu.pattern.observer.listener.IListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventCenter implements IEventPublisher,ApplicationListener<ContextRefreshedEvent> {

    private Map<String,List<IListener>> map = new HashMap<>();

    @Override
    public void publishEvent(AbstractEvent abstractEvent) {
        String name = abstractEvent.getClass().getName();
        List<IListener> listeners = map.get(name);
        listeners.forEach(l -> l.onEvent(abstractEvent));
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext ctx = contextRefreshedEvent.getApplicationContext();
        Map<String, IListener> listeners = ctx.getBeansOfType(IListener.class);
        for (IListener l : listeners.values()){
            //获取接口的通用属性
            Type[] genericInterfaces = l.getClass().getGenericInterfaces();
            for (Type interfaceType : genericInterfaces){
                ParameterizedType parameterizedType = (ParameterizedType) interfaceType;
                //获取接口的泛型类型
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if(parameterizedType.getRawType().equals(IListener.class) && actualTypeArguments.length == 1){
                    Class typeArgument = (Class) actualTypeArguments[0];
                    if (typeArgument.getSuperclass().equals(AbstractEvent.class)) {
                        List<IListener> listenerList = map.get(typeArgument.getName());
                        if(null == listenerList){
                            listenerList = new ArrayList<>();
                            listenerList.add(l);
                            map.put(typeArgument.getName(),listenerList);
                        } else {
                            listenerList.add(l);
                        }
                    }
                }
            }
        }
    }
}
