package org.seeker.common.util;

import org.dozer.Mapper;

import javax.annotation.Resource;
import java.util.*;

public class DozerUtil {

    @Resource private Mapper mapper;

    protected <T> List<T> doListMap(List<T> objs,Class<T> c){
        if(objs==null){
            return null;
        }else{
            List<T> list=new ArrayList<T>();
            Iterator i=objs.iterator();
            while (i.hasNext()){
                Object obj=i.next();
                list.add(this.mapper.map(obj,c));
            }
            return list;
        }
    }
    protected <T> T doObjectMap(Object obj,Class<T> c){
        return obj==null?null:this.mapper.map(obj,c);
    }


    protected <T> Set<T> doListMap(Set<T> objs, Class<T> c){
        if(objs==null){
            return null;
        }else{
            Set<T> set=new HashSet<T>();
            Iterator i=objs.iterator();
            while (i.hasNext()){
                Object obj=i.next();
                set.add(this.mapper.map(obj,c));
            }
            return set;
        }
    }

}
