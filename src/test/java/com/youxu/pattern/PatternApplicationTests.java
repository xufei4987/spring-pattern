package com.youxu.pattern;

import com.youxu.pattern.observer.event.RainEvent;
import com.youxu.pattern.observer.event.SunnyEvent;
import com.youxu.pattern.observer.publish.EventCenter;
import com.youxu.pattern.statemachine.Message;
import com.youxu.pattern.statemachine.MessageType;
import com.youxu.pattern.statemachine.entity.Lift;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PatternApplicationTests {

    @Autowired
    EventCenter eventCenter;

    @Test
    void contextLoads() {
        //observer test
//        eventCenter.publishEvent(new SunnyEvent("sunny"));
//        eventCenter.publishEvent(new RainEvent("rain"));

        //state machine test
        Lift lift = new Lift("No.1电梯");
        System.out.println(lift.getCurrentState());
        Message openMsg = new Message(MessageType.OPEN, "电梯开门");
        lift.process(openMsg);
        System.out.println(lift.getCurrentState());
        Message openCompleteMsg = new Message(MessageType.OPEN_COMPLETE, "电梯完成开门");
        lift.process(openCompleteMsg);
        System.out.println(lift.getCurrentState());
        Message closeMsg = new Message(MessageType.CLOSE, "电梯关门");
        lift.process(closeMsg);
        System.out.println(lift.getCurrentState());
        Message closeCompleteMsg = new Message(MessageType.CLOSE_COMPLETE, "电梯完成关门");
        lift.process(closeCompleteMsg);
        System.out.println(lift.getCurrentState());

    }

}
