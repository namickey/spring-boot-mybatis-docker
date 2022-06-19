package com.example.taskletbatch;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ItemMapper {

    @Select("select nextval('abc')")
    public Integer select();
}
