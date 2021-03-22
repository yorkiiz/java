package com.service.iml;

import com.service.Bird;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Component
@Data
public class Duck implements Bird {


    private String name;
    private String speed;
    public void fly() {
        System.out.println("my speed is"+speed);
    }
}
