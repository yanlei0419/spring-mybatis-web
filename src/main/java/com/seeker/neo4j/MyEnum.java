package com.seeker.neo4j;

public enum MyEnum implements IMyEnum {
    A("测试1"),B("测试2"),C("测试3");

    MyEnum(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String getName() {
        return this.name;
    }


}
