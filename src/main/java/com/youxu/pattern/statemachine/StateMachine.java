package com.youxu.pattern.statemachine;

import com.youxu.pattern.statemachine.handler.ILiftHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Slf4j
public class StateMachine {
    private StateType currentState;
    private Map<StateType, Map<MessageType, Object[]>> stateMap;

    public StateMachine(StateType currentState, Map<StateType, Map<MessageType, Object[]>> stateMap) {
        this.currentState = currentState;
        this.stateMap = stateMap;
    }

    public StateType getCurrentState() {
        return currentState;
    }

    public void stateMove(Message message){
        Map<MessageType, Object[]> messageTypeMap = stateMap.get(currentState);
        if(CollectionUtils.isEmpty(messageTypeMap)){
            log.info("当前状态为{}，状态机无法处理！", currentState.name());
            return;
        }
        MessageType mt = message.getMessageType();
        Object[] objects = messageTypeMap.get(mt);
        if(objects != null){
            if(objects.length == 1){
                currentState = (StateType) objects[0];
            } else if (objects.length == 2){
                ILiftHandler handler = (ILiftHandler) objects[1];
                handler.handle(message);
                currentState = (StateType) objects[0];
            } else {
                log.error("状态机异常！");
            }
        } else {
            log.error("当前状态为{}，状态机无法处理{}",currentState.name(),mt.name());
        }

    }
}
