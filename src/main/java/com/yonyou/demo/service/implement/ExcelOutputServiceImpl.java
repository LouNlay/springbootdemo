package com.yonyou.demo.service.implement;

import com.yonyou.demo.entity.SystemParam;
import com.yonyou.demo.entity.page.Page;
import com.yonyou.demo.service.ExcelOutputService;
import com.yonyou.demo.service.SystemParamService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ExcelOutputServiceImpl implements ExcelOutputService {

    private static final Logger logger = LoggerFactory.getLogger(ExcelOutputServiceImpl.class);

    @Autowired
    private SystemParamService systemParamService;

    @Override
    public void excelOutput(Page page,HttpServletResponse response) {
        page.setPageIndex(1);
        page.setPageSize(Integer.valueOf(String.valueOf(systemParamService.getSysParamNumber())));
        Page<SystemParam> systemParamPage = systemParamService.pageQuery(page);
        List<SystemParam> data = systemParamPage.getData();

        try {
            HSSFWorkbook excel = new HSSFWorkbook();
            //style
            HSSFCellStyle cellStyle = excel.createCellStyle();
            initStyle(cellStyle);
            HSSFCellStyle dateStyle = excel.createCellStyle();
            dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
            HSSFSheet sheet = excel.createSheet("张俊龙要求的报表导出");
            HSSFRow row0 = sheet.createRow(0);
            HSSFCell cell0 = row0.createCell(0);
            cell0.setCellValue("张俊龙要的报表导出");
            cell0.setCellStyle(cellStyle);
            //表头标题行
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));

            //head
            initHead(sheet);

            //日期格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

            if (!CollectionUtils.isEmpty(data)) {
                int rowNumber = 1;
                for (SystemParam systemParam : data) {
                    HSSFRow row = sheet.createRow(rowNumber+1);
                    row.createCell(0).setCellValue(rowNumber);
                    row.createCell(1).setCellValue(systemParam.getId());
                    row.createCell(2).setCellValue(systemParam.getCode());
                    row.createCell(3).setCellValue(systemParam.getValue());
                    row.createCell(4).setCellValue(systemParam.getDescription());
                    row.createCell(5).setCellValue(sdf.format(systemParam.getCreateTime()));
//                    row.createCell(5).setCellStyle(dateStyle);
//                    row.createCell(5).setCellStyle(new HSSFCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm")));
                    rowNumber++;
                }
            }
            OutputStream output = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=report.xls");
//            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msexcel");
            excel.write(output);
            output.close();
        } catch (IOException e) {
            logger.error("导出excel时发生IOException:", e);
            throw new RuntimeException("导出excel时发生错误");
        } catch (Exception e) {
            logger.error("导出excel时发生Exception,错误信息如下:", e);
            throw new RuntimeException("导出excel时发生错误");
        }

    }

    private void initStyle(HSSFCellStyle cellStyle) {
        // 设置单元格的横向和纵向对齐方式，具体参数就不列了，参考HSSFCellStyle

        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);

  /* 设置单元格的填充方式，以及前景颜色和背景颜色

   三点注意：

   1.如果需要前景颜色或背景颜色，一定要指定填充方式，两者顺序无所谓；

   2.如果同时存在前景颜色和背景颜色，前景颜色的设置要写在前面；

   3.前景颜色不是字体颜色。

  */

        //设置填充方式(填充图案)

//        cellStyle.setFillPattern(FillPatternType.BIG_SPOTS);

        //设置前景色
        short sho = 111;
        cellStyle.setFillForegroundColor(sho);

        //设置背景颜色

        cellStyle.setFillBackgroundColor(sho);

        // 设置单元格底部的边框及其样式和颜色

        // 这里仅设置了底边边框，左边框、右边框和顶边框同理可设

//        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_SLANTED_DASH_DOT);

//        cellStyle.setBottomBorderColor(HSSFColor.DARK_RED.index);

        //设置日期型数据的显示样式

        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
    }

    private void initHead(HSSFSheet sheet) {

        HSSFRow row = sheet.createRow(1);
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("id");
        row.createCell(2).setCellValue("code");
        row.createCell(3).setCellValue("name");
        row.createCell(4).setCellValue("description");
        row.createCell(5).setCellValue("create_time");
    }
}
