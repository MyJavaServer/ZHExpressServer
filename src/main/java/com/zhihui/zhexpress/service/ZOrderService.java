package com.zhihui.zhexpress.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zhihui.zhexpress.ExpressApplication;
import com.zhihui.zhexpress.base.ZHResponse;
import com.zhihui.zhexpress.config.ZHConfig;
import com.zhihui.zhexpress.mapper.OrderMapper;
import com.zhihui.zhexpress.mapper.SysConfigMapper;
import com.zhihui.zhexpress.model.ExcelData;
import com.zhihui.zhexpress.model.Order;
import com.zhihui.zhexpress.utils.ExportExcelUtils;
import com.zhihui.zhexpress.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ZOrderService {

    @Autowired
    private OrderMapper mOrderMapper;
    @Autowired
    private SysConfigMapper mSysConfigMapper;


    /**
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @param startTime
     * @param endTime
     * @param stype
     * @param num
     * @param repoNum
     * @param userNum
     * @param picNum
     * @return
     */
    public Map getOrderList(Integer pageNum, Integer pageSize, String orderBy, String startTime, String endTime, String stype, String num, String repoNum, String userNum, String picNum, Integer status) {
        StringUtil.startPage(pageNum, pageSize, orderBy);
        List<Map> ls = mOrderMapper.getOrderList(stype, num, repoNum, userNum, picNum, status, startTime, endTime, null);
        return ZHResponse.success(new PageInfo<>(ls));

    }


    public Map getScanTypes() {
        List<Map> types = mSysConfigMapper.getScanTypes();
        return ZHResponse.success(types);
    }

    public Map exportOrders(String orderBy, String startTime, String endTime, String stype, String num, String repoNum, String userNum, String picNum, Integer status) {
        ExcelData excelData = new ExcelData();
        String fileName = "扫描单明细" + new Date().getTime()+".xlsx";
        excelData.setName(fileName);
        List<String> titles = new ArrayList<>();
        List<List<Object>> rows = new ArrayList<>();
        //标题
        titles.add("序号");
        titles.add("扫描单编号");
        titles.add("扫描人编号");
        titles.add("图片编号");
        titles.add("图片地址");
        titles.add("仓库编号");
        titles.add("类型");
        titles.add("备注");
        titles.add("录入时间");
        titles.add("更新时间");

        List<Map> ls = mOrderMapper.getOrderList(stype, num, repoNum, userNum, picNum, status, startTime, endTime, null);
        if (ls == null) {
            return ZHResponse.failed("数据为空，无法导出！");
        }

        //每一列的收据
        for (Map map : ls) {
            List<Object> row = new ArrayList<>();
            Order o = JSONObject.parseObject(JSONObject.toJSON(map).toString(), Order.class);
            row.add(o.getId());
            row.add(o.getNum());
            row.add(o.getUserNum());
            row.add(o.getPicNum());
            row.add(o.getPicAddr());
            row.add(o.getRepoNum());
            row.add(o.getStype());
            row.add(o.getRemarks());
            row.add(o.getCreatetime());
            row.add(o.getUpdatetime());
            rows.add(row);
        }
        excelData.setTitles(titles);
        excelData.setRows(rows);
        try {
            // TODO: 2019/4/12 注意替换到对应服务器的文件保存地址
            String homeDir = "src/main/";
            ExportExcelUtils.exportExcel2(excelData, homeDir + fileName);
            String appMode = ExpressApplication.getGlobalAppMode();
            String fileUrl = "";
            if (appMode.equals(ZHConfig.GLOBAL_APP_MODE_LIVE)) {
                fileUrl = ZHConfig.SERVER_URL_LIVE + homeDir;
            } else if (appMode.equals(ZHConfig.GLOBAL_APP_MODE_DEV)) {
                fileUrl = ZHConfig.SERVER_URL_DEV + homeDir;
            } else if (appMode.equals(ZHConfig.GLOBAL_APP_MODE_TEST)) {
                fileUrl = ZHConfig.SERVER_URL_TEST + homeDir;
            }
            fileUrl += homeDir + fileName;
            return ZHResponse.success(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ZHResponse.failed("订单导出异常，" + e.getMessage());
        }
    }
}
