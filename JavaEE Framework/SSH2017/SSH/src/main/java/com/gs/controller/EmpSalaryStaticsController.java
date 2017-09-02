package com.gs.controller;

import com.gs.common.HighchartsLine;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmpSalaryStaticsController extends ActionSupport {

    private List<HighchartsLine<Double>> series;

    public String execute() {
        series = new ArrayList<>();
        Random ran = new Random();
        for (int i = 0; i < 4; i++) {
            HighchartsLine<Double> emp = new HighchartsLine<>();
            emp.setName("emp" + (i + 1));
            List<Double> data = new ArrayList<>(12);
            for (int month = 0; month < 12; month++) {
                data.add(ran.nextDouble() * 5000);
            }
            emp.setData(data);
            series.add(emp);
        }
        return SUCCESS;
    }

    public List<HighchartsLine<Double>> getSeries() {
        return series;
    }
}
