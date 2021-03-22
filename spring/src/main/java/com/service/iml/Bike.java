package com.service.iml;

import com.service.Icar;
import lombok.Data;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Data
public class Bike implements Icar {


    private String speed;
    public void drvie() {
        System.out.println("bike is comming");
    }
}
