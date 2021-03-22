package com.yorkiiz.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Data
public class Account implements Serializable {

    private String id;

    private String name;

    private double money;


}
