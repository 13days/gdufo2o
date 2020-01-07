package com.wcp.gdufo2o.dao;


import com.wcp.gdufo2o.BaseTest;
import com.wcp.gdufo2o.entity.Area;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;


public class AreaDaoTest extends BaseTest {

    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        for(Area area : areaList) {
        	System.out.println(area);
        }
        assertEquals(4,areaList.size());
    }
}
