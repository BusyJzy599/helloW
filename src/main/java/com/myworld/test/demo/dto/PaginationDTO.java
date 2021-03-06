package com.myworld.test.demo.dto;

import com.myworld.test.demo.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
@Data
public class PaginationDTO <T>{
    private List<T> data;               //当前页的数据
    private boolean showPrevious;       //是否显示前一页
    private boolean showFirstPage=false;//是否显示首页
    private boolean showNext;           //是否显示后一页
    private boolean showLastPage=false; //是否显示最后一页

    private Integer page;               //当前页数
    private List<Integer>pages=new LinkedList<>();//显示的页码数

    private Integer countPage;          //总页数

    //设置相关属性
    public void setPagination(Integer totalCount, Integer page, Integer size) {
        Integer totalPage=0;
        //判断有多少页并赋值
        totalPage=totalCount%size==0?totalCount/size:totalCount/size+1;
        countPage=totalPage;

        //设置显示的页数
        if(totalPage>3){
            if(page>1) {
                if(page!=totalPage) {
                    pages.add(page - 1);
                    pages.add(page);
                    if (page + 1 <= totalPage)
                        pages.add(page + 1);
                }else {
                    pages.add(page-2);
                    pages.add(page-1);
                    pages.add(page);
                }
            }else{
                for(int i=0;i<3;i++){
                    pages.add(i+1);
                }
            }
        }else{
            for(int i=0;i<totalPage;i++) {
                pages.add(i + 1);
            }
        }


        //设置显示前一页
        showPrevious=page==1?false:true;
        //设置显示后一页
        showNext=page==totalPage?false:true;
        //设置显示到达第一页
        showFirstPage=page<=2?false:true;
        //设置显示到达尾页
        showLastPage=page+2>totalPage?false:true;



    }
}
