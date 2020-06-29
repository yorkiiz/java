package com.gupaoedu.example.springbootdemo02;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public class CustomerHealthIndicator extends AbstractHealthIndicator{

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {

    }
}
