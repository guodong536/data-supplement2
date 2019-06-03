package cn.pingan.springboottwo.controller;

import javax.servlet.http.HttpServletResponse;

public class BaseController {

    public void exportDeal(HttpServletResponse response, String fileName){
        response.setHeader("Content-disposition",fileName);

        response.setContentType("aplication/vnd.ms-excel");

        response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");

        response.setHeader("Pragma","No-cache");

        response.setContentType("application/json;charset=UTF-8");

    }
}
