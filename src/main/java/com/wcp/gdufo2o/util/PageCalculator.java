package com.wcp.gdufo2o.util;

public class PageCalculator {
    public static int calculateRowIndex(int pageIndex, int pageSize){
        return (pageIndex>0) ? (pageIndex-1)*pageSize : 0;
    }
}
