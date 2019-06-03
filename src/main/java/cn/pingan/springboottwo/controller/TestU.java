package cn.pingan.springboottwo.controller;


import cn.pingan.springboottwo.utils.CronUtils;

public class TestU {

    public static void main(String[] args) {
        boolean b=CronUtils.checkCron("* 0/4 * * * ?");
        System.out.println(b);
    }
}
