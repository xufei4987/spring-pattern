package com.youxu.pattern.statemachine;

import java.util.Map;

public class StateMachineFactory {
    private StateMachineFactory(){}
    private static StateMachineFactory instance = new StateMachineFactory();
    private Map<StateType, Map<MessageType, Object[]>> stateMap;

    public static StateMachineFactory getInstance() {
        return instance;
    }

    public Map<StateType, Map<MessageType, Object[]>> getStateMap() {
        return stateMap;
    }

    public void setStateMap(Map<StateType, Map<MessageType, Object[]>> stateMap) {
        this.stateMap = stateMap;
    }

    public StateMachine newStateMachine(){
        return new StateMachine(StateType.STOPPED,this.stateMap);
    }
}
