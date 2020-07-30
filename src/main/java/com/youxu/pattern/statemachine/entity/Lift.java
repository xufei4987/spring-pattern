package com.youxu.pattern.statemachine.entity;

import com.youxu.pattern.statemachine.Message;
import com.youxu.pattern.statemachine.StateMachine;
import com.youxu.pattern.statemachine.StateMachineFactory;
import com.youxu.pattern.statemachine.StateType;

public class Lift {
    private StateMachine stateMachine;
    private String name;

    public Lift(String name){
        this.name = name;
        this.stateMachine = StateMachineFactory.getInstance().newStateMachine();
    }

    public StateType getCurrentState(){
        return this.stateMachine.getCurrentState();
    }

    public void process(Message message){
        this.stateMachine.stateMove(message);
    }
}
