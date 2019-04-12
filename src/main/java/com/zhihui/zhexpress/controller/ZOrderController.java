package com.zhihui.zhexpress.controller;

import com.zhihui.zhexpress.base.controller.ZHBaseController;
import com.zhihui.zhexpress.interfaces.Permission;
import com.zhihui.zhexpress.mapper.OrderMapper;
import com.zhihui.zhexpress.model.Order;
import com.zhihui.zhexpress.service.ZOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class ZOrderController extends ZHBaseController {

    @Autowired
    private ZOrderService mOrderService;
    @Autowired
    private OrderMapper mOrderMapper;

    @GetMapping("/list")
    @ResponseBody
    public Map getOrderList(@RequestParam(value = "pageNum") Integer pageNum,
                            @RequestParam(value = "pageSize") Integer pageSize,
                            @RequestParam(value = "orderBy", required = false, defaultValue = "o.createtime_desc") String orderBy,
                            @RequestParam(value = "startTime", required = false) String startTime,
                            @RequestParam(value = "endTime", required = false) String endTime,
                            @RequestParam(value = "stype", required = false) String stype,     //类型
                            @RequestParam(value = "num", required = false) String num,          //扫描单编号
                            @RequestParam(value = "repoNum", required = false) String repoNum,  //仓库编号
                            @RequestParam(value = "userNum", required = false) String userNum,  //扫描人编号
                            @RequestParam(value = "picNum", required = false) String picNum,        //图片编号
                            @RequestParam(value = "status", required = false) Integer status        //状态
    ) {
        return mOrderService.getOrderList(pageNum, pageSize, orderBy, startTime, endTime, stype, num, repoNum, userNum, picNum, status);
    }

    @GetMapping("/scanTypes")
    @ResponseBody
    @Permission(needToken = false)
    public Map getScanTypes() {
        return mOrderService.getScanTypes();
    }


    @GetMapping("/exportOrders")
    @ResponseBody
    public Map exportOrders(@RequestParam(value = "orderBy", required = false, defaultValue = "o.createtime_desc") String orderBy,
                            @RequestParam(value = "startTime", required = false) String startTime,
                            @RequestParam(value = "endTime", required = false) String endTime,
                            @RequestParam(value = "stype", required = false) String stype,     //类型
                            @RequestParam(value = "num", required = false) String num,          //扫描单编号
                            @RequestParam(value = "repoNum", required = false) String repoNum,  //仓库编号
                            @RequestParam(value = "userNum", required = false) String userNum,  //扫描人编号
                            @RequestParam(value = "picNum", required = false) String picNum,        //图片编号
                            @RequestParam(value = "status", required = false) Integer status        //状态
    ) {

        return mOrderService.exportOrders(orderBy, startTime, endTime, stype, num, repoNum, userNum, picNum, status);
    }


}
