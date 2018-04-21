package com.noasking.snote.test;

import com.noasking.snote.persistence.PathProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProperties {

    @Autowired
    private PathProperties pathProperties;

    @Test
    public void testPathProperties(){
        System.out.println(pathProperties);
    }

}
