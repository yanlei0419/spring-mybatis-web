package org.seeker.dozer;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.Date;

public class DozerTest {
    public static void main(String[] args) {
        NotSameAttributeA a=new NotSameAttributeA();
        a.setId(123);
        a.setName("测试");
        a.setDate(new Date());
        NotSameAttributeB b=new NotSameAttributeB();

        Mapper mapper = new DozerBeanMapper();
        b =mapper.map(a, NotSameAttributeB.class);
        System.out.println(b);

    }
}
