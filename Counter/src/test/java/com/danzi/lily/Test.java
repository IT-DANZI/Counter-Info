package com.danzi.lily;

import com.danzi.pac.dao.AlterInfoMessageMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Describe ：测试
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/8/12.
 */
public class Test {
    private ApplicationContext ac;
    private AlterInfoMessageMapper infoMessageMapper;
    @org.junit.Test
    public void hash() {
        ac = new ClassPathXmlApplicationContext(new String[] { "spring.xml",
                "spring-mybatis.xml" });
        System.out.print(ac);
    }
}
