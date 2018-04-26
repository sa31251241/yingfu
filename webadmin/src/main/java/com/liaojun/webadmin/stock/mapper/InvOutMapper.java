package com.liaojun.webadmin.stock.mapper;

import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.mybatis.mapper.IBaseMapper;
import com.liaojun.webadmin.stock.model.InvIn;
import com.liaojun.webadmin.stock.model.InvOut;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:31
 */
@Mapper
public interface InvOutMapper extends IBaseMapper<InvOut>{
    List<InvOut> findList(@Param("invOut") InvOut invOut, @Param("pageRequest") PageRequest pageRequest, @Param("sortRequest") SortRequest sortRequest);

    Integer findListCount(@Param("invOut") InvOut invOut);

    InvOut findById(String id);
}
