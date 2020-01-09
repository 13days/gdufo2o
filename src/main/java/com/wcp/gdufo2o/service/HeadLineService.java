package com.wcp.gdufo2o.service;

import com.wcp.gdufo2o.entity.HeadLine;

import java.io.IOException;
import java.util.List;

/**
 * 头条业务层
 */
public interface HeadLineService {

    /**
     * 根据传入的调教返回指定头条
     * @param headLineCondition
     * @return
     * @throws IOException
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
