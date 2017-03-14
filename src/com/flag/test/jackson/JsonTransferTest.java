package com.flag.test.jackson;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jackson tranfer object test
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-03-14-10:41
 */
public class JsonTransferTest {
    public static void main(String[] args) {
        print();
    }

    private static void print(){
        JsonEntity entity = new JsonEntity();
        entity.setName("fff");
        entity.setAge(18);
        entity.setHobbiesStr("[{\"hobby\":\"hobby0\",\"level\":0},{\"hobby\":\"hobby1\",\"level\":1},{\"hobby\":\"hobby2\",\"level\":2},{\"hobby\":\"hobby3\",\"level\":3}]");
        List<TestEntity> hobbies = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            TestEntity hobby = new TestEntity();
            hobby.setHobby("hobby" + i);
            hobby.setLevel(i);
            hobbies.add(hobby);
        }
        entity.setHobbies(hobbies);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(entity);
            System.out.println(json);
            Map map = mapper.readValue(json, HashMap.class);
            System.out.println(mapper.readValue(entity.getHobbiesStr(), ArrayList.class));
            System.out.println(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
