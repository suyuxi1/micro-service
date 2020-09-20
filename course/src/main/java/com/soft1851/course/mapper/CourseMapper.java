package com.soft1851.course.mapper;

import com.soft1851.course.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author su
 * @Date 2020/9/17
 * @Version 1.0
 */
@Mapper
public interface CourseMapper {


    /**
     * //
     * @param id
     * @return Course
     */
    @Select("SELECT * FROM t_course WHERE user_id = #{id}")
    List<Course> queryList(@Param("id") String id);



    /**
     *
     * @param id
     * @return Course
     */
    @Select("SELECT * FROM t_course WHERE id = #{id}")
    Course queryById(@Param("id") String id);


}
