package com.youxu.pattern.statemachine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youxu.pattern.statemachine.handler.ILiftHandler;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StateMachineInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private static final String STATE_CONFIG_PATH = "classpath:statemachine/state.json";
    /**
     * 组织数据的方式：一个状态可以输入多种消息类型，一种消息对应一种处理方法和一种下一步的状态
     */
    private Map<StateType, Map<MessageType, Object[]>> stateMap = new HashMap<>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> list = null;
        try {
            list = mapper.readValue(ResourceUtils.getFile(STATE_CONFIG_PATH), List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        list.forEach(map -> {
            String currentState = map.get("currentState");
            String[] currentStates = currentState.split(",");
            for (String cs : currentStates) {
                StateType st = Enum.valueOf(StateType.class, cs);
                Map<MessageType, Object[]> messageTypeMap = stateMap.computeIfAbsent(st, (key)->new HashMap<>());
                MessageType mt = Enum.valueOf(MessageType.class, map.get("input"));
                StateType nst = Enum.valueOf(StateType.class, map.get("nextState"));
                ILiftHandler handler = (ILiftHandler) contextRefreshedEvent.getApplicationContext().getBean(map.get("handler"));
                messageTypeMap.put(mt, new Object[]{nst, handler});
            }
        });
        StateMachineFactory.getInstance().setStateMap(stateMap);
    }
}
