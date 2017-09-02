package com.gs.controller;

import com.gs.common.HighchartsPie;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class BrowserStaticsController extends ActionSupport {

    private List<HighchartsPie> data;

    public String execute() {
        data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HighchartsPie pie = new HighchartsPie();
            pie.setName("browser" + (i + 1));
            pie.setY(20.0);
            if (i == 3) {
                pie.setSelected(true);
                pie.setSliced(true);
            }
            data.add(pie);
        }
        return SUCCESS;
    }

    public List<HighchartsPie> getData() {
        return data;
    }
}
