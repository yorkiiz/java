package com.gupao.spi.mysql;

import com.gupao.spi.DataBaseDriver;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public class MysqlDriver implements DataBaseDriver {
    @Override
    public String buildCOnnect(String s) {
        return "Mysql的驱动实现:"+s;
    }
}
