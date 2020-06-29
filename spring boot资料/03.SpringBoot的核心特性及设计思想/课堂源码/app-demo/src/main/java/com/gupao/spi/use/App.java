package com.gupao.spi.use;

import com.gupao.spi.DataBaseDriver;

import java.util.ServiceLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ServiceLoader<DataBaseDriver> serviceLoader=ServiceLoader.load(DataBaseDriver.class);
        for(DataBaseDriver dbd:serviceLoader){
            System.out.println(dbd.buildCOnnect("Test"));
        }
    }
}
