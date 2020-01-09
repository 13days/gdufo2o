package com.wcp.gdufo2o.service;

import com.wcp.gdufo2o.BaseTest;
import com.wcp.gdufo2o.dao.HeadLineDao;
import com.wcp.gdufo2o.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testQueryHeadLine(){
        HeadLine headLine = new HeadLine();
        headLine.setEnableStatus(1);
        List<HeadLine> headLines = headLineDao.queryHeadLine(headLine);
        for(HeadLine headLine1 : headLines){
            System.out.println(headLine1.getLineName());
        }
    }
}
