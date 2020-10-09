package com.soft1851.content.center.mapper;

import com.soft1851.content.center.domain.entity.Share;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Su
 * @className ShareMapper
 * @Description TODO
 * @Date 2020/9/29 15:52
 * @Version 1.0
 **/


public interface ShareMapper extends Mapper<Share> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Select("SELECT * FROM share WHERE id = #{id}")
    Share findById(@Param("id") Integer id);

    @Select("SELECT * FROM share WHERE user_id = #{id}")
    List<Share> queryById(@Param("id") Integer id);
}
