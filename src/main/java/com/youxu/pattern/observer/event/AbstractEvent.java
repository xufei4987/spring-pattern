package com.youxu.pattern.observer.event;

/**
 * 事件抽象类
 */
public abstract class AbstractEvent {
    //事件需要传递的数据
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public AbstractEvent(Object data){
        this.data = data;
    }
}
