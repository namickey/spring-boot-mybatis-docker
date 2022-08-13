package com.example.demo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SeqMapper {

    @Select("select nextval(#{name})")
    int nextval(String name);

    @Select("select currval(#{name})")
    int currval(String name);
}
