package com.matrix.cim;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class StartWebMainTest {

    @Test
    public void testMain() throws IOException {
        System.out.println("Module PPT System Started successful .............");
        System.out.println("按任意键退出");
        System.in.read();
    }
}
