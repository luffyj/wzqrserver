package org.luffy.wzqr.wzqrserver.web;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.luffy.wzqr.wzqrserver.beans.BeanHelper;
import org.luffy.wzqr.wzqrserver.beans.bean.Datadto;
import org.luffy.wzqr.wzqrserver.beans.bean.NoNullLabel;
import org.luffy.wzqr.wzqrserver.beans.bean.NoNullMap;
import org.luffy.wzqr.wzqrserver.entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

@Service
public class DocumentHandler implements ServletContextAware {

    @Autowired
    private BeanHelper beanHelper;
    private Configuration configuration = null;
    private ServletContext servletContext;

    private final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private final SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    private final SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
    private final SimpleDateFormat fullFormat = new SimpleDateFormat("yyyyMMdd");

    public DocumentHandler() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");

    }

    public void exportExcel(Query query, OutputStream out) throws WriteException, IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        /**
         * 合并单元格 第一个参数：第一个单元格的行数（从0开始） 第二个参数：第二个单元格的行数（从0开始）
         * 第三个参数：第一个单元格的列数（从0开始） 第四个参数：第二个单元格的列数（从0开始）
         */
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 20));
//        sheet.addMergedRegion(new CellRangeAddress(2, 2, 16, 17));
//        sheet.addMergedRegion(new CellRangeAddress(2, 2, 18, 19));

        // 设置字体
        HSSFFont headfont = workbook.createFont();
        headfont.setFontName("方正小标宋简体");
        headfont.setFontHeightInPoints((short) 16);// 字体大小
        //headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        HSSFCellStyle headstyle = workbook.createCellStyle();
        headstyle.setFont(headfont);
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        //headstyle.setLocked(true);
        //headstyle.setWrapText(true);// 自动换行
        int currentRow = 0;
        HSSFRow row1 = sheet.createRow(currentRow++);
        row1.setHeight((short) (45.75 * 20));  //设置行高
        HSSFCell cell0 = row1.createCell(0);
        cell0.setCellValue(new HSSFRichTextString("温州市\"580海外精英引进计划\"申报人选情况汇总表"));
        cell0.setCellStyle(headstyle);

        // 设置字体
        HSSFFont headfont2 = workbook.createFont();
        headfont2.setFontName("宋体");
        headfont2.setFontHeightInPoints((short) 12);// 字体大小
        headfont2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
        HSSFCellStyle headstyle2 = workbook.createCellStyle();
        headstyle2.setFont(headfont2);
        headstyle2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 左右居右
        headstyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

        HSSFRow row2 = sheet.createRow(currentRow++);
        row2.setHeight((short) (21.75 * 20));  //设置行高
        HSSFCell cell16 = row2.createCell(16);
        cell16.setCellValue(new HSSFRichTextString("填表时间："));
        cell16.setCellStyle(headstyle2);

        // 设置字体
        HSSFFont headfont21 = workbook.createFont();
        headfont21.setFontName("宋体");
        headfont21.setFontHeightInPoints((short) 12);// 字体大小
        HSSFCellStyle headstyle21 = workbook.createCellStyle();
        headstyle21.setFont(headfont21);
        headstyle21.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 左右居右
        headstyle21.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        HSSFCell cell18 = row2.createCell(18);
        cell18.setCellValue(new HSSFRichTextString(fullFormat.format(new Date())));
        cell18.setCellStyle(headstyle21);

        // 设置字体
        HSSFFont headfont3 = workbook.createFont();
        headfont3.setFontName("宋体");
        headfont3.setFontHeightInPoints((short) 9);// 字体大小
        HSSFCellStyle headstyle3 = workbook.createCellStyle();
        headstyle3.setFont(headfont3);
        headstyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        headstyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        headstyle3.setWrapText(true);// 自动换行
        headstyle3.setBorderTop((short) 1);//上边框 
        headstyle3.setBorderBottom((short) 1);//下边框
        headstyle3.setBorderLeft((short) 1);   //左边框
        headstyle3.setBorderRight((short) 1);  //右边框

        float beishu = 300.24154589371983f;  //缩放倍数
        HSSFRow row3 = sheet.createRow(currentRow++);
        row3.setHeight((short) (90 * 20));
        int cellIndex = 0;

        sheet.setColumnWidth(cellIndex++, (int) (4.14 * beishu));//a
        sheet.setColumnWidth(cellIndex++, (int) (6.57 * beishu));//b
        sheet.setColumnWidth(cellIndex++, (int) (6.29 * beishu));//c
        sheet.setColumnWidth(cellIndex++, (int) (3.43 * beishu));//d
        sheet.setColumnWidth(cellIndex++, (int) (5.14 * beishu));//e
        sheet.setColumnWidth(cellIndex++, (int) (4.14 * beishu));//f
        sheet.setColumnWidth(cellIndex++, (int) (3.5 * beishu));//g
        sheet.setColumnWidth(cellIndex++, (int) (7.71 * beishu));//h
        sheet.setColumnWidth(cellIndex++, (int) (8.57 * beishu));//i
        sheet.setColumnWidth(cellIndex++, (int) (6 * beishu));//j
        sheet.setColumnWidth(cellIndex++, (int) (5.71 * beishu));//k
        sheet.setColumnWidth(cellIndex++, (int) (5.71 * beishu));   //l
        sheet.setColumnWidth(cellIndex++, (int) (5.71 * beishu));//m
        sheet.setColumnWidth(cellIndex++, (int) (5.71 * beishu));//n
        sheet.setColumnWidth(cellIndex++, (int) (9.86 * beishu));//o
        sheet.setColumnWidth(cellIndex++, (int) (6.43 * beishu));//p
        sheet.setColumnWidth(cellIndex++, (int) (6 * beishu));//q
        sheet.setColumnWidth(cellIndex++, (int) (4.86 * beishu));//r
        sheet.setColumnWidth(cellIndex++, (int) (4.86 * beishu));//s
        sheet.setColumnWidth(cellIndex++, (int) (4.86 * beishu));//t
        sheet.setColumnWidth(cellIndex++, (int) (7.14 * beishu));//u
        sheet.setColumnWidth(cellIndex++, (int) (7.29 * beishu));//v
//        
//        sheet.setColumnWidth(cellIndex++, (int) (3.13 * beishu));//a
//        sheet.setColumnWidth(cellIndex++, (int) (4.5 * beishu));//b
//        sheet.setColumnWidth(cellIndex++, (int) (3.13 * beishu));//c
//        sheet.setColumnWidth(cellIndex++, (int) (4.38 * beishu));//d
//        sheet.setColumnWidth(cellIndex++, (int) (6.63 * beishu));//e
//        sheet.setColumnWidth(cellIndex++, (int) (3.13 * beishu));//f
//        sheet.setColumnWidth(cellIndex++, (int) (5.88 * beishu));//g
//        sheet.setColumnWidth(cellIndex++, (int) (6.75 * beishu));//h
//        sheet.setColumnWidth(cellIndex++, (int) (5.38 * beishu));//i
//        sheet.setColumnWidth(cellIndex++, (int) (4.75 * beishu));//j
//        sheet.setColumnWidth(cellIndex++, (int) (5.25 * beishu));//k
//        sheet.setColumnWidth(cellIndex++, (int) (5 * beishu));   //l
//        sheet.setColumnWidth(cellIndex++, (int) (4.25 * beishu));//m
//        sheet.setColumnWidth(cellIndex++, (int) (4.75 * beishu));//n
//        sheet.setColumnWidth(cellIndex++, (int) (4.38 * beishu));//o
//        sheet.setColumnWidth(cellIndex++, (int) (3.38 * beishu));//p
//        sheet.setColumnWidth(cellIndex++, (int) (6.13 * beishu));//q
//        sheet.setColumnWidth(cellIndex++, (int) (4 * beishu));//r
//        sheet.setColumnWidth(cellIndex++, (int) (4.88 * beishu));//s
//        sheet.setColumnWidth(cellIndex++, (int) (6.75 * beishu));//t
//        sheet.setColumnWidth(cellIndex++, (int) (6.25 * beishu));//u
//        sheet.setColumnWidth(cellIndex++, (int) (3.63 * beishu));//v
//        sheet.setColumnWidth(cellIndex++, (int) (3.13 * beishu));//w
//        sheet.setColumnWidth(cellIndex++, (int) (2.88 * beishu));//x
//        sheet.setColumnWidth(cellIndex++, (int) (3.63 * beishu));//y
//        sheet.setColumnWidth(cellIndex++, (int) (3.88 * beishu));//z

        cellIndex = 0;
        HSSFCell cell3_0 = row3.createCell(cellIndex++);
        cell3_0.setCellValue(new HSSFRichTextString("序号"));
        cell3_0.setCellStyle(headstyle3);
        HSSFCell cell3_0p = row3.createCell(cellIndex++);
        cell3_0p.setCellValue(new HSSFRichTextString("编号"));
        cell3_0p.setCellStyle(headstyle3);
        HSSFCell cell3_1 = row3.createCell(cellIndex++);
        cell3_1.setCellValue(new HSSFRichTextString("姓名"));
        cell3_1.setCellStyle(headstyle3);
        HSSFCell cell3_2 = row3.createCell(cellIndex++);
        cell3_2.setCellValue(new HSSFRichTextString("性别"));
        cell3_2.setCellStyle(headstyle3);
        HSSFCell cell3_3 = row3.createCell(cellIndex++);
        cell3_3.setCellValue(new HSSFRichTextString("国籍"));
        cell3_3.setCellStyle(headstyle3);
        HSSFCell cell3_4 = row3.createCell(cellIndex++);
        cell3_4.setCellValue(new HSSFRichTextString("出生日期"));
        cell3_4.setCellStyle(headstyle3);
        HSSFCell cell3_5 = row3.createCell(cellIndex++);
        cell3_5.setCellValue(new HSSFRichTextString("最高学历学位"));
        cell3_5.setCellStyle(headstyle3);
        HSSFCell cell3_6 = row3.createCell(cellIndex++);
        cell3_6.setCellValue(new HSSFRichTextString("毕业院校"));
        cell3_6.setCellStyle(headstyle3);
        HSSFCell cell3_7 = row3.createCell(cellIndex++);
        cell3_7.setCellValue(new HSSFRichTextString("用人(申报)单位/创办企业"));
        cell3_7.setCellStyle(headstyle3);
        HSSFCell cell3_8 = row3.createCell(cellIndex++);
        cell3_8.setCellValue(new HSSFRichTextString("职务/拟任职务或职称"));
        cell3_8.setCellStyle(headstyle3);
        HSSFCell cell3_9 = row3.createCell(cellIndex++);
        cell3_9.setCellValue(new HSSFRichTextString("专业领域"));
        cell3_9.setCellStyle(headstyle3);
        HSSFCell cell3_10 = row3.createCell(cellIndex++);
        cell3_10.setCellValue(new HSSFRichTextString("专业方向"));
        cell3_10.setCellStyle(headstyle3);
//        HSSFCell cell3_11 = row3.createCell(cellIndex++);
//        cell3_11.setCellValue(new HSSFRichTextString("专利授权或研发成果情况"));
//        cell3_11.setCellStyle(headstyle3);
        HSSFCell cell3_12 = row3.createCell(cellIndex++);
        cell3_12.setCellValue(new HSSFRichTextString("目前实际到位资金占比%"));
        cell3_12.setCellStyle(headstyle3);
        HSSFCell cell3_13 = row3.createCell(cellIndex++);
        cell3_13.setCellValue(new HSSFRichTextString("个人或风投的占股比例或资金额度"));
        cell3_13.setCellStyle(headstyle3);
//        HSSFCell cell3_14 = row3.createCell(cellIndex++);
//        cell3_14.setCellValue(new HSSFRichTextString("承诺每年国内工作时限（月/年）"));
//        cell3_14.setCellStyle(headstyle3);
//        HSSFCell cell3_15 = row3.createCell(cellIndex++);
//        cell3_15.setCellValue(new HSSFRichTextString("落地市"));
//        cell3_15.setCellStyle(headstyle3);
        HSSFCell cell3_16 = row3.createCell(cellIndex++);
        cell3_16.setCellValue(new HSSFRichTextString("到中国前单位及职务"));
        cell3_16.setCellStyle(headstyle3);
//        HSSFCell cell3_17 = row3.createCell(cellIndex++);
//        cell3_17.setCellValue(new HSSFRichTextString("职务"));
//        cell3_17.setCellStyle(headstyle3);
        HSSFCell cell3_18 = row3.createCell(cellIndex++);
        cell3_18.setCellValue(new HSSFRichTextString("（拟）到中国时间"));
        cell3_18.setCellStyle(headstyle3);
        HSSFCell cell3_19 = row3.createCell(cellIndex++);
        //
        //创新类签订合同时间/创业类企业完成工商注册时间
        cell3_19.setCellValue(new HSSFRichTextString("签订合同时间/工商注册时间"));
        cell3_19.setCellStyle(headstyle3);
//        HSSFCell cell3_20 = row3.createCell(cellIndex++);
//        cell3_20.setCellValue(new HSSFRichTextString("引进平台"));
//        cell3_20.setCellStyle(headstyle3);
        HSSFCell cell3_21 = row3.createCell(cellIndex++);
        cell3_21.setCellValue(new HSSFRichTextString("申报单位所属"));
        cell3_21.setCellStyle(headstyle3);
        HSSFCell cell3_22 = row3.createCell(cellIndex++);
        cell3_22.setCellValue(new HSSFRichTextString("人才类型"));
        cell3_22.setCellStyle(headstyle3);
        HSSFCell cell3_23 = row3.createCell(cellIndex++);
        cell3_23.setCellValue(new HSSFRichTextString("是否破格"));
        cell3_23.setCellStyle(headstyle3);
        HSSFCell cell3_24 = row3.createCell(cellIndex++);
        cell3_24.setCellValue(new HSSFRichTextString("审核状态"));
        cell3_24.setCellStyle(headstyle3);
        HSSFCell cell3_25 = row3.createCell(cellIndex++);
        cell3_25.setCellValue(new HSSFRichTextString("备注"));
        cell3_25.setCellStyle(headstyle3);

        // 设置字体
        HSSFFont headfont4 = workbook.createFont();
        headfont4.setFontName("宋体");
        headfont4.setFontHeightInPoints((short) 12);// 字体大小
        HSSFCellStyle headstyle4 = workbook.createCellStyle();
        headstyle4.setFont(headfont4);
        headstyle4.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 左右居右
        headstyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        headstyle4.setWrapText(true);// 自动换行

        query.setMaxResults(20);
        int firstResult = 0;
        int icount = 0;
        while (true) {
            query.setFirstResult(firstResult);
            List<Application> list = query.getResultList();
            if (list.isEmpty()) {
                break;
            }
            firstResult += list.size();
            for (Application app : list) {
                int cellInIndex = 0;
                HSSFRow rown = sheet.createRow(currentRow + (icount++));

                rown.setHeight((short) (63.75 * 20));  //设置行高

                HSSFCell celll_0p = rown.createCell(cellInIndex++);
                celll_0p.setCellValue(new HSSFRichTextString("" + (icount)));
                celll_0p.setCellStyle(headstyle3);
                HSSFCell celll_0 = rown.createCell(cellInIndex++);
                celll_0.setCellValue(new HSSFRichTextString(app.getNumber()));
                celll_0.setCellStyle(headstyle3);
                HSSFCell celll_1 = rown.createCell(cellInIndex++);
                celll_1.setCellValue(new HSSFRichTextString(app.getRealName()));
                celll_1.setCellStyle(headstyle3);
                HSSFCell celll_2 = rown.createCell(cellInIndex++);
                celll_2.setCellValue(new HSSFRichTextString(app.getSex() == 0 ? "男" : "女"));
                celll_2.setCellStyle(headstyle3);
                HSSFCell celll_3 = rown.createCell(cellInIndex++);
                celll_3.setCellValue(new HSSFRichTextString(app.getNationality()));
                celll_3.setCellStyle(headstyle3);
                HSSFCell celll_4 = rown.createCell(cellInIndex++);
                celll_4.setCellValue(new HSSFRichTextString(app.getBirthDate() != null ? fullFormat.format(app.getBirthDate()) : ""));
                celll_4.setCellStyle(headstyle3);
                HSSFCell celll_5 = rown.createCell(cellInIndex++);
                celll_5.setCellValue(new HSSFRichTextString(app.getMgChineseDegree()));
                celll_5.setCellStyle(headstyle3);
                HSSFCell celll_6 = rown.createCell(cellInIndex++);
                celll_6.setCellValue(new HSSFRichTextString(app.getMgChineseSchool()));
                celll_6.setCellStyle(headstyle3);
                HSSFCell celll_7 = rown.createCell(cellInIndex++);
                celll_7.setCellValue(new HSSFRichTextString(app.getAppOrgName()));
                celll_7.setCellStyle(headstyle3);
                HSSFCell celll_8 = rown.createCell(cellInIndex++);
                celll_8.setCellValue(new HSSFRichTextString(app.getPosition()));
                celll_8.setCellStyle(headstyle3);
                HSSFCell celll_9 = rown.createCell(cellInIndex++);
                celll_9.setCellValue(new HSSFRichTextString(app.getSpecialty()));
                celll_9.setCellStyle(headstyle3);
                HSSFCell celll_10 = rown.createCell(cellInIndex++);
                celll_10.setCellValue(new HSSFRichTextString(app.getProfession()));
                celll_10.setCellStyle(headstyle3);
//                HSSFCell celll_11 = rown.createCell(cellInIndex++);
//                celll_11.setCellValue(new HSSFRichTextString(app.getPatentDesc()));
//                celll_11.setCellStyle(headstyle3);
                HSSFCell celll_12 = rown.createCell(cellInIndex++);
                celll_12.setCellValue(new HSSFRichTextString(app.getActualCurrentFundsPer() + "%"));
                celll_12.setCellStyle(headstyle3);
                HSSFCell celll_13 = rown.createCell(cellInIndex++);
                celll_13.setCellValue(new HSSFRichTextString(app.getMyFundsPer() + "%"));
                celll_13.setCellStyle(headstyle3);
//                HSSFCell celll_14 = rown.createCell(cellInIndex++);
//                celll_14.setCellValue(new HSSFRichTextString(""));
//                celll_14.setCellStyle(headstyle3);
//            HSSFCell celll_15 = rown.createCell(cellInIndex++);
//            celll_15.setCellValue(new HSSFRichTextString(app.getCity()));
//            celll_15.setCellStyle(headstyle3);
                HSSFCell celll_16 = rown.createCell(cellInIndex++);
                celll_16.setCellValue(new HSSFRichTextString(app.getForeignJobChinese()));
                celll_16.setCellStyle(headstyle3);
//                HSSFCell celll_17 = rown.createCell(cellInIndex++);
//                celll_17.setCellValue(new HSSFRichTextString(""));
//                celll_17.setCellStyle(headstyle3);
                HSSFCell celll_18 = rown.createCell(cellInIndex++);
                celll_18.setCellValue(new HSSFRichTextString(app.getWdate()));
                celll_18.setCellStyle(headstyle3);
                HSSFCell celll_19 = rown.createCell(cellInIndex++);
                celll_19.setCellValue(new HSSFRichTextString(""));
                celll_19.setCellStyle(headstyle3);
//            HSSFCell celll_20 = rown.createCell(cellInIndex++);
//            celll_20.setCellValue(new HSSFRichTextString(app.getPlatform()));
//            celll_20.setCellStyle(headstyle3);
                HSSFCell celll_21 = rown.createCell(cellInIndex++);
                celll_21.setCellValue(new HSSFRichTextString(app.getOrgSubName()));
                celll_21.setCellStyle(headstyle3);
                HSSFCell celll_22 = rown.createCell(cellInIndex++);
                celll_22.setCellValue(new HSSFRichTextString(app.getType()));
                celll_22.setCellStyle(headstyle3);
                HSSFCell celll_23 = rown.createCell(cellInIndex++);
                celll_23.setCellValue(new HSSFRichTextString(app.isPoge() ? "是" : "否"));
                celll_23.setCellStyle(headstyle3);
                HSSFCell celll_24 = rown.createCell(cellInIndex++);
                celll_24.setCellValue(new HSSFRichTextString(app.getStatus()));
                celll_24.setCellStyle(headstyle3);
                HSSFCell celll_25 = rown.createCell(cellInIndex++);
                celll_25.setCellValue(new HSSFRichTextString(app.getComment()));
                celll_25.setCellStyle(headstyle3);
            }
            if (list.size() < 20) {
                break;
            }
        }

        //自动筛选
        CellRangeAddress c = CellRangeAddress.valueOf("A3:V3");
        sheet.setAutoFilter(c);
        //冻结行   (前一个参数代表列；后一个参数代表行。)
        sheet.createFreezePane(3, 3);

        double suofang = 0.3;
        HSSFPrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); // 设置默认打印纸张为A4
        printSetup.setLandscape(true);  //设置横向打印
        printSetup.setScale((short) 97);//缩放比例
        sheet.setMargin(HSSFSheet.TopMargin, 1.5 * suofang); // 上边距
        sheet.setMargin(HSSFSheet.BottomMargin, (double) 1 * suofang); // 下边距
        sheet.setMargin(HSSFSheet.LeftMargin, (double) 0.5 * suofang); // 左边距
        sheet.setMargin(HSSFSheet.RightMargin, (double) 0.5 * suofang); // 右边距

        workbook.write(out);
    }

    /**
     * @see #exportExcel(java.util.List, java.io.OutputStream)
     * @deprecated
     *
     */
    public InputStream exportExcel(List list) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            /**
             * 合并单元格 第一个参数：第一个单元格的行数（从0开始） 第二个参数：第二个单元格的行数（从0开始）
             * 第三个参数：第一个单元格的列数（从0开始） 第四个参数：第二个单元格的列数（从0开始）
             */
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 24));
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 16, 17));
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 18, 19));

            // 设置字体
            HSSFFont headfont = workbook.createFont();
            headfont.setFontName("方正小标宋简体");
            headfont.setFontHeightInPoints((short) 16);// 字体大小
            //headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
            HSSFCellStyle headstyle = workbook.createCellStyle();
            headstyle.setFont(headfont);
            headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
            headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
            //headstyle.setLocked(true);
            //headstyle.setWrapText(true);// 自动换行
            HSSFRow row1 = sheet.createRow(1);
            HSSFCell cell0 = row1.createCell(0);
            cell0.setCellValue(new HSSFRichTextString("温州市\"580海外精英引进计划\"申报人选情况汇总表"));
            cell0.setCellStyle(headstyle);

            // 设置字体
            HSSFFont headfont2 = workbook.createFont();
            headfont2.setFontName("宋体");
            headfont2.setFontHeightInPoints((short) 12);// 字体大小
            HSSFCellStyle headstyle2 = workbook.createCellStyle();
            headstyle2.setFont(headfont2);
            headstyle2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 左右居右
            headstyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

            HSSFRow row2 = sheet.createRow(2);
            HSSFCell cell16 = row2.createCell(16);
            cell16.setCellValue(new HSSFRichTextString("填表时间："));
            cell16.setCellStyle(headstyle2);

            HSSFCell cell18 = row2.createCell(18);
            cell18.setCellValue(new HSSFRichTextString("2013年1月1日"));
            cell18.setCellStyle(headstyle2);

            // 设置字体
            HSSFFont headfont3 = workbook.createFont();
            headfont3.setFontName("宋体");
            headfont3.setFontHeightInPoints((short) 9);// 字体大小
            HSSFCellStyle headstyle3 = workbook.createCellStyle();
            headstyle3.setFont(headfont3);
            headstyle3.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 左右居右
            headstyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
            headstyle3.setWrapText(true);// 自动换行
            HSSFRow row3 = sheet.createRow(3);

            HSSFCell cell3_0 = row3.createCell(0);
            cell3_0.setCellValue(new HSSFRichTextString("序号"));
            cell3_0.setCellStyle(headstyle3);
            HSSFCell cell3_1 = row3.createCell(1);
            cell3_1.setCellValue(new HSSFRichTextString("姓名"));
            cell3_1.setCellStyle(headstyle3);
            HSSFCell cell3_2 = row3.createCell(2);
            cell3_2.setCellValue(new HSSFRichTextString("性别"));
            cell3_2.setCellStyle(headstyle3);
            HSSFCell cell3_3 = row3.createCell(3);
            cell3_3.setCellValue(new HSSFRichTextString("国籍"));
            cell3_3.setCellStyle(headstyle3);
            HSSFCell cell3_4 = row3.createCell(4);
            cell3_4.setCellValue(new HSSFRichTextString("出生日期"));
            cell3_4.setCellStyle(headstyle3);
            HSSFCell cell3_5 = row3.createCell(5);
            cell3_5.setCellValue(new HSSFRichTextString("最高(海外)学历学位"));
            cell3_5.setCellStyle(headstyle3);
            HSSFCell cell3_6 = row3.createCell(6);
            cell3_6.setCellValue(new HSSFRichTextString("毕业院校"));
            cell3_6.setCellStyle(headstyle3);
            HSSFCell cell3_7 = row3.createCell(7);
            cell3_7.setCellValue(new HSSFRichTextString("用人(申报)单位/创办企业"));
            cell3_7.setCellStyle(headstyle3);
            HSSFCell cell3_8 = row3.createCell(8);
            cell3_8.setCellValue(new HSSFRichTextString("职务/拟任职务或职称"));
            cell3_8.setCellStyle(headstyle3);
            HSSFCell cell3_9 = row3.createCell(9);
            cell3_9.setCellValue(new HSSFRichTextString("专业领域"));
            cell3_9.setCellStyle(headstyle3);
            HSSFCell cell3_10 = row3.createCell(10);
            cell3_10.setCellValue(new HSSFRichTextString("专业方向"));
            cell3_10.setCellStyle(headstyle3);
            HSSFCell cell3_11 = row3.createCell(11);
            cell3_11.setCellValue(new HSSFRichTextString("专利授权或研发成果情况"));
            cell3_11.setCellStyle(headstyle3);
            HSSFCell cell3_12 = row3.createCell(12);
            cell3_12.setCellValue(new HSSFRichTextString("目前实际到位资金占比%"));
            cell3_12.setCellStyle(headstyle3);
            HSSFCell cell3_13 = row3.createCell(13);
            cell3_13.setCellValue(new HSSFRichTextString("个人或风投的占股比例或资金额度"));
            cell3_13.setCellStyle(headstyle3);
            HSSFCell cell3_14 = row3.createCell(14);
            cell3_14.setCellValue(new HSSFRichTextString("承诺每年国内工作时限（月/年）"));
            cell3_14.setCellStyle(headstyle3);
            HSSFCell cell3_15 = row3.createCell(15);
            cell3_15.setCellValue(new HSSFRichTextString("落地市"));
            cell3_15.setCellStyle(headstyle3);
            HSSFCell cell3_16 = row3.createCell(16);
            cell3_16.setCellValue(new HSSFRichTextString("到中国前单位"));
            cell3_16.setCellStyle(headstyle3);
            HSSFCell cell3_17 = row3.createCell(17);
            cell3_17.setCellValue(new HSSFRichTextString("职务"));
            cell3_17.setCellStyle(headstyle3);
            HSSFCell cell3_18 = row3.createCell(18);
            cell3_18.setCellValue(new HSSFRichTextString("（拟）到中国时间"));
            cell3_18.setCellStyle(headstyle3);
            HSSFCell cell3_19 = row3.createCell(19);
            cell3_19.setCellValue(new HSSFRichTextString("创新类签订合同时间/创业类企业完成工商注册时间"));
            cell3_19.setCellStyle(headstyle3);
            HSSFCell cell3_20 = row3.createCell(20);
            cell3_20.setCellValue(new HSSFRichTextString("引进平台"));
            cell3_20.setCellStyle(headstyle3);
            HSSFCell cell3_21 = row3.createCell(21);
            cell3_21.setCellValue(new HSSFRichTextString("申报单位所属"));
            cell3_21.setCellStyle(headstyle3);
            HSSFCell cell3_22 = row3.createCell(22);
            cell3_22.setCellValue(new HSSFRichTextString("人才类型"));
            cell3_22.setCellStyle(headstyle3);
            HSSFCell cell3_23 = row3.createCell(23);
            cell3_23.setCellValue(new HSSFRichTextString("是否破格"));
            cell3_23.setCellStyle(headstyle3);
            HSSFCell cell3_24 = row3.createCell(24);
            cell3_24.setCellValue(new HSSFRichTextString("牵头单位"));
            cell3_24.setCellStyle(headstyle3);
            HSSFCell cell3_25 = row3.createCell(25);
            cell3_25.setCellValue(new HSSFRichTextString("备注"));
            cell3_25.setCellStyle(headstyle3);

            // 设置字体
            HSSFFont headfont4 = workbook.createFont();
            headfont4.setFontName("宋体");
            headfont4.setFontHeightInPoints((short) 12);// 字体大小
            HSSFCellStyle headstyle4 = workbook.createCellStyle();
            headstyle4.setFont(headfont4);
            headstyle4.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 左右居右
            headstyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
            headstyle4.setWrapText(true);// 自动换行
            for (int i = 0; i < list.size(); i++) {
                HSSFRow rown = sheet.createRow(4 + i);

                HSSFCell celll_0 = rown.createCell(0);
                celll_0.setCellValue(new HSSFRichTextString("序号"));
                celll_0.setCellStyle(headstyle3);
                HSSFCell celll_1 = rown.createCell(1);
                celll_1.setCellValue(new HSSFRichTextString("姓名"));
                celll_1.setCellStyle(headstyle3);
                HSSFCell celll_2 = rown.createCell(2);
                celll_2.setCellValue(new HSSFRichTextString("性别"));
                celll_2.setCellStyle(headstyle3);
                HSSFCell celll_3 = rown.createCell(3);
                celll_3.setCellValue(new HSSFRichTextString("国籍"));
                celll_3.setCellStyle(headstyle3);
                HSSFCell celll_4 = rown.createCell(4);
                celll_4.setCellValue(new HSSFRichTextString("出生日期"));
                celll_4.setCellStyle(headstyle3);
                HSSFCell celll_5 = rown.createCell(5);
                celll_5.setCellValue(new HSSFRichTextString("最高(海外)学历学位"));
                celll_5.setCellStyle(headstyle3);
                HSSFCell celll_6 = rown.createCell(6);
                celll_6.setCellValue(new HSSFRichTextString("毕业院校"));
                celll_6.setCellStyle(headstyle3);
                HSSFCell celll_7 = rown.createCell(7);
                celll_7.setCellValue(new HSSFRichTextString("用人(申报)单位/创办企业"));
                celll_7.setCellStyle(headstyle3);
                HSSFCell celll_8 = rown.createCell(8);
                celll_8.setCellValue(new HSSFRichTextString("职务/拟任职务或职称"));
                celll_8.setCellStyle(headstyle3);
                HSSFCell celll_9 = rown.createCell(9);
                celll_9.setCellValue(new HSSFRichTextString("专业领域"));
                celll_9.setCellStyle(headstyle3);
                HSSFCell celll_10 = rown.createCell(10);
                celll_10.setCellValue(new HSSFRichTextString("专业方向"));
                celll_10.setCellStyle(headstyle3);
                HSSFCell celll_11 = rown.createCell(11);
                celll_11.setCellValue(new HSSFRichTextString("专利授权或研发成果情况"));
                celll_11.setCellStyle(headstyle3);
                HSSFCell celll_12 = rown.createCell(12);
                celll_12.setCellValue(new HSSFRichTextString("目前实际到位资金占比%"));
                celll_12.setCellStyle(headstyle3);
                HSSFCell celll_13 = rown.createCell(13);
                celll_13.setCellValue(new HSSFRichTextString("个人或风投的占股比例或资金额度"));
                celll_13.setCellStyle(headstyle3);
                HSSFCell celll_14 = rown.createCell(14);
                celll_14.setCellValue(new HSSFRichTextString("承诺每年国内工作时限（月/年）"));
                celll_14.setCellStyle(headstyle3);
                HSSFCell celll_15 = rown.createCell(15);
                celll_15.setCellValue(new HSSFRichTextString("落地市"));
                celll_15.setCellStyle(headstyle3);
                HSSFCell celll_16 = rown.createCell(16);
                celll_16.setCellValue(new HSSFRichTextString("到中国前单位"));
                celll_16.setCellStyle(headstyle3);
                HSSFCell celll_17 = rown.createCell(17);
                celll_17.setCellValue(new HSSFRichTextString("职务"));
                celll_17.setCellStyle(headstyle3);
                HSSFCell celll_18 = rown.createCell(18);
                celll_18.setCellValue(new HSSFRichTextString("（拟）到中国时间"));
                celll_18.setCellStyle(headstyle3);
                HSSFCell celll_19 = rown.createCell(19);
                celll_19.setCellValue(new HSSFRichTextString("创新类签订合同时间/创业类企业完成工商注册时间"));
                celll_19.setCellStyle(headstyle3);
                HSSFCell celll_20 = rown.createCell(20);
                celll_20.setCellValue(new HSSFRichTextString("引进平台"));
                celll_20.setCellStyle(headstyle3);
                HSSFCell celll_21 = rown.createCell(21);
                celll_21.setCellValue(new HSSFRichTextString("申报单位所属"));
                celll_21.setCellStyle(headstyle3);
                HSSFCell celll_22 = rown.createCell(22);
                celll_22.setCellValue(new HSSFRichTextString("人才类型"));
                celll_22.setCellStyle(headstyle3);
                HSSFCell celll_23 = rown.createCell(23);
                celll_23.setCellValue(new HSSFRichTextString("是否破格"));
                celll_23.setCellStyle(headstyle3);
                HSSFCell celll_24 = rown.createCell(24);
                celll_24.setCellValue(new HSSFRichTextString("牵头单位"));
                celll_24.setCellStyle(headstyle3);
                HSSFCell celll_25 = rown.createCell(25);
                celll_25.setCellValue(new HSSFRichTextString("备注"));
                celll_25.setCellStyle(headstyle3);
            }
            //自动筛选                        
            CellRangeAddress c = CellRangeAddress.valueOf("A4:Z4");
            sheet.setAutoFilter(c);
            //冻结行   (前一个参数代表列；后一个参数代表行。)
            sheet.createFreezePane(3, 4);

            workbook.write(os);

            byte[] b = os.toByteArray();
            ByteArrayInputStream in = new ByteArrayInputStream(b);
            os.close();
            return in;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    /**
     * @see #exportExcel(java.util.List, java.io.OutputStream)
     * @deprecated
     *
     */
    public void exportExcelOld(List<Application> list, OutputStream out) throws WriteException, IOException {
        // 创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(out);
        // 创建新的一页
        WritableSheet sheet = workbook.createSheet("第一页", 0);
        sheet.mergeCells(0, 1, 25, 1);
        sheet.mergeCells(16, 2, 17, 2);
        sheet.mergeCells(18, 2, 19, 2);

        //设置字体      
        WritableFont font1 = new WritableFont(WritableFont.createFont("方正小标宋简体"), 16, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
        WritableCellFormat cFormat1 = new WritableCellFormat(font1);
        cFormat1.setAlignment(Alignment.CENTRE);
        cFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);

        sheet.addCell(new Label(0, 1, "温州市\"580海外精英引进计划\"申报人选情况汇总表", cFormat1));

        WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
        WritableCellFormat cFormat2 = new WritableCellFormat(font2);
        cFormat2.setAlignment(Alignment.RIGHT);
        cFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);

        sheet.addCell(new Label(16, 2, "填表时间：", cFormat2));
        sheet.addCell(new Label(18, 2, fullFormat.format(new Date())));

        WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 9, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
        WritableCellFormat cFormat3 = new WritableCellFormat(font3);
        cFormat3.setAlignment(Alignment.CENTRE);
        cFormat3.setWrap(true);
        cFormat3.setVerticalAlignment(VerticalAlignment.CENTRE);

        sheet.addCell(new Label(0, 3, "序号", cFormat3));
        sheet.addCell(new Label(1, 3, "姓名", cFormat3));
        sheet.addCell(new Label(2, 3, "性别", cFormat3));
        sheet.addCell(new Label(3, 3, "国籍", cFormat3));
        sheet.addCell(new Label(4, 3, "出生日期", cFormat3));
        sheet.addCell(new Label(5, 3, "最高(海外)学历学位", cFormat3));
        sheet.addCell(new Label(6, 3, "毕业院校", cFormat3));
        sheet.addCell(new Label(7, 3, "用人(申报)单位/创办企业", cFormat3));
        sheet.addCell(new Label(8, 3, "职务/拟任职务或职称", cFormat3));
        sheet.addCell(new Label(9, 3, "专业领域", cFormat3));
        sheet.addCell(new Label(10, 3, "专业方向", cFormat3));
        sheet.addCell(new Label(11, 3, "专利授权或研发成果情况", cFormat3));
        sheet.addCell(new Label(12, 3, "目前实际到位资金占比%", cFormat3));
        sheet.addCell(new Label(13, 3, "个人或风投的占股比例或资金额度", cFormat3));
        sheet.addCell(new Label(14, 3, "承诺每年国内工作时限（月/年）", cFormat3));
        sheet.addCell(new Label(15, 3, "落地市", cFormat3));
        sheet.addCell(new Label(16, 3, "到中国前单位", cFormat3));
        sheet.addCell(new Label(17, 3, "职务", cFormat3));
        sheet.addCell(new Label(18, 3, "（拟）到中国时间", cFormat3));
        sheet.addCell(new Label(19, 3, "创新类签订合同时间/创业类企业完成工商注册时间", cFormat3));
        sheet.addCell(new Label(20, 3, "引进平台", cFormat3));
        sheet.addCell(new Label(21, 3, "申报单位所属", cFormat3));
        sheet.addCell(new Label(22, 3, "人才类型", cFormat3));
        sheet.addCell(new Label(23, 3, "是否破格", cFormat3));
        sheet.addCell(new Label(24, 3, "牵头单位", cFormat3));
        sheet.addCell(new Label(25, 3, "备注", cFormat3));

        WritableFont font4 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
        WritableCellFormat cFormat4 = new WritableCellFormat(font4);
        cFormat4.setAlignment(Alignment.CENTRE);
        cFormat4.setWrap(true);
        cFormat4.setVerticalAlignment(VerticalAlignment.CENTRE);

        NoNullLabel.setDefaultFormat(cFormat4);

        for (int i = 0; i < list.size(); i++) {
            Application app = list.get(i);
            sheet.addCell(new NoNullLabel(0, 4 + i, app.getNumber()));
            sheet.addCell(new NoNullLabel(1, 4 + i, app.getRealName()));
            sheet.addCell(new NoNullLabel(2, 4 + i, app.getSex() == 0 ? "男" : "女"));
            sheet.addCell(new NoNullLabel(3, 4 + i, app.getNationality()));
            sheet.addCell(new NoNullLabel(4, 4 + i, app.getBirthDate() != null ? fullFormat.format(app.getBirthDate()) : ""));
            sheet.addCell(new NoNullLabel(5, 4 + i, app.getMgChineseDegree()));
            sheet.addCell(new NoNullLabel(6, 4 + i, app.getMgChineseSchool()));
            sheet.addCell(new NoNullLabel(7, 4 + i, app.getAppOrgName()));
            sheet.addCell(new NoNullLabel(8, 4 + i, app.getPosition()));
            sheet.addCell(new NoNullLabel(9, 4 + i, app.getSpecialty()));
            sheet.addCell(new NoNullLabel(10, 4 + i, app.getProfession()));
            sheet.addCell(new NoNullLabel(11, 4 + i, app.getPatentDesc()));
            sheet.addCell(new NoNullLabel(12, 4 + i, app.getActualCurrentFundsPer() + "%"));
            sheet.addCell(new NoNullLabel(13, 4 + i, app.getMyFundsPer() + "%"));
            sheet.addCell(new NoNullLabel(14, 4 + i, ""));// 承诺每年国内工作时限（月/年）?
            sheet.addCell(new NoNullLabel(15, 4 + i, app.getCity()));
            sheet.addCell(new NoNullLabel(16, 4 + i, app.getForeignJobChinese()));
            sheet.addCell(new NoNullLabel(17, 4 + i, ""));//职务
            sheet.addCell(new NoNullLabel(18, 4 + i, app.getWdate()));
            sheet.addCell(new NoNullLabel(19, 4 + i, ""));//创新类签订合同时间/创业类企业完成工商注册时间
            sheet.addCell(new NoNullLabel(20, 4 + i, app.getPlatform()));
            sheet.addCell(new NoNullLabel(21, 4 + i, app.getOrgSubName()));
            sheet.addCell(new NoNullLabel(22, 4 + i, app.getType()));
            sheet.addCell(new NoNullLabel(23, 4 + i, app.isPoge() ? "是" : "女"));
            sheet.addCell(new NoNullLabel(24, 4 + i, app.getAppOrgName()));//牵头单位
            sheet.addCell(new NoNullLabel(25, 4 + i, app.getComment()));

        }

        workbook.write();
        workbook.close();
    }

    /**
     * 温州市“580海外精英引进计划”创新类申报书
     */
    public void export1(Application app, OutputStream out) throws IOException, TemplateException {

        // 要填入模本的数据文件
        Map dataMap = new HashMap();

        getData1(dataMap, app);

        // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
        // 这里我们的模板是放在com.havenliu.document.template包下面
        configuration.setServletContextForTemplateLoading(servletContext, "/WEB-INF/reports");
//        configuration.setClassForTemplateLoading(this.getClass(),
//                "/template");

        Template t = configuration.getTemplate("1.ftl");

        t.setEncoding("utf-8");

        t.process(dataMap, new OutputStreamWriter(out, "UTF-8"));
    }

    /**
     * 温州市“580海外精英引进计划”创业类申报书
     */
    public void export2(Application app, OutputStream out) throws IOException, TemplateException {

        // 要填入模本的数据文件
        Map dataMap = new HashMap();

        getData2(dataMap, app);

        // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
        // 这里我们的模板是放在com.havenliu.document.template包下面
        configuration.setServletContextForTemplateLoading(servletContext, "/WEB-INF/reports");
//        configuration.setClassForTemplateLoading(this.getClass(),
//                "/template");

        Template t = configuration.getTemplate("2.ftl");

        t.setEncoding("utf-8");

        t.process(dataMap, new OutputStreamWriter(out, "UTF-8"));
    }

    /**
     *
     * 注意dataMap里存放的数据Key值要与模板中的参数相对应
     *
     * 温州市“580海外精英引进计划”创新类申报书
     *
     * @param dataMap
     */
    @SuppressWarnings("unchecked")
    private void getData1(Map dataMap, Application app) {
        dataMap = new NoNullMap(dataMap);
        dataMap.put("str1_1", app.getNumber());// 编号        
        dataMap.put("str1_2", app.getRealName()); // 申报人
        dataMap.put("str1_3", app.getAppOrgName());// 申报单位
        dataMap.put("str1_4", app.getSpecialty());// 专业领域
        dataMap.put("str1_5", app.getProfession());// 专业方向
        dataMap.put("str1_6", app.getPeople());// 联系人
        dataMap.put("str1_7", app.getPhone());// 联系电话
        dataMap.put("str1_8", app.getMobile());// 联系手机

        //填表时间 如果没有的话 默认今天
        Date date = app.getComletionDate();
        if (date == null) {
            date = new Date();
        }

        dataMap.put("str1_9", yearFormat.format(date));// 年
        dataMap.put("str1_10", monthFormat.format(date));// 月
        dataMap.put("str1_11", dayFormat.format(date));// 日

        dataMap.put("str4_1", app.getRealName());// 中文名
        dataMap.put("str4_2", app.getSex() == 0 ? "男" : "女");// 性别
        dataMap.put("str4_3", app.getRealEnglishName());// 英文名

        date = app.getBirthDate();
        if (date == null) {
            dataMap.put("str4_4", "");// 出生年
            dataMap.put("str4_5", "");// 出生月
            dataMap.put("str4_6", "");// 出生日
        } else {
            dataMap.put("str4_4", yearFormat.format(date));// 出生年
            dataMap.put("str4_5", monthFormat.format(date));// 出生月
            dataMap.put("str4_6", dayFormat.format(date));// 出生日
        }
        dataMap.put("str4_7", app.getBirthPlace());// 出生地
        dataMap.put("str4_8", app.getNationality());// 国籍
        dataMap.put("str4_9", app.getMgChineseSchool());// 毕业学校中文
        dataMap.put("str4_10", app.getMgEnglishSchool());// 毕业学校英文
        dataMap.put("str4_11", app.getForeignJobChinese());// 工作单位及职务中文
        dataMap.put("str4_12", app.getForeignJobEnglish());// 工作单位及职务英文
        dataMap.put("str4_13", app.getEmployer());// 现任职单位
        dataMap.put("str4_14", app.getPosition());// 职务
        try {
            dataMap.put("str4_15", app.getMyorg().getContact().getAddress());// 单位地址
        } catch (NullPointerException ex) {
            dataMap.put("str4_15", app.getEmployerAddress());
        }
        dataMap.put("str4_16", app.getZip());// 邮编                
        dataMap.put("str4_17", beanHelper.toString(app, new String[]{
            "degree",
            "time",
            "country",
            "university",
            "major"
        }, " ", "\n"));// 教育经历
        dataMap.put("str4_18",
                beanHelper.toString(app, new String[]{
                    "jobPosition",
                    "jobTime",
                    "jobCountry",
                    "jobOrg"
                }, " ", "\n"));// 工作经历

        dataMap.put("str5_1", beanHelper.escapeToWord(app.getExpertTo()));// 个人专长
        List tables5_1 = new ArrayList();
        for (int i = 0; i < 20; i++) {
            if (i > 4 && beanHelper.toString(app, "projectTime", i, "").equals("")) {
                break;
            }
            Datadto t = new Datadto();
            t.setCol1(beanHelper.toString(app, "projectTime", i, ""));//起始时间
            t.setCol2(beanHelper.toString(app, "projectDesc", i, ""));//项目性质和来源
            t.setCol3(beanHelper.toString(app, "projectBudget", i, ""));//经费总额
            t.setCol4(beanHelper.toString(app, "projectPeoples", i, ""));//参与人数
            t.setCol5(beanHelper.toString(app, "projectResponsibility", i, ""));//申报人的具体职位和任务
            tables5_1.add(t);
        }

        //如果tables5_1的size小于4，请加上空的数据时它变成4  如：tables5_1.add( new Datadto());
        dataMap.put("tables5_1", tables5_1);  //领导（参与）过的主要项目

        List tables5_2 = new ArrayList();
        for (int i = 0; i < 20; i++) {
            if (i > 4 && beanHelper.toString(app, "paperTime", i, "").equals("")) {
                break;
            }
            Datadto t = new Datadto();
            t.setCol1(beanHelper.toString(app, "paperTime", i, ""));//发表时间
            t.setCol2(beanHelper.toString(app, "paperTitle", i, ""));//论著
            t.setCol3(beanHelper.toString(app, "paperMedia", i, ""));//发表载体
            t.setCol4(beanHelper.toString(app, "paperAuthor", i, ""));//论著（论文）坐着
            tables5_2.add(t);
        }

        //如果tables5_2的size小于4，请加上空的数据时它变成4  如：tables5_2.add( new Datadto());
        dataMap.put("tables5_2", tables5_2);  //代表性论著

        List tables5_3 = new ArrayList();
        for (int i = 0; i < 20; i++) {
            if (i > 4 && beanHelper.toString(app, "patentTime", i, "").equals("")) {
                break;
            }
            Datadto t = new Datadto();
            t.setCol1(beanHelper.toString(app, "patentTime", i, ""));//专利保护期
            t.setCol2(beanHelper.toString(app, "patentName", i, ""));//专利名称
            t.setCol3(beanHelper.toString(app, "patentCountry", i, ""));//授权国家
            t.setCol4(beanHelper.toString(app, "patentAuthor", i, ""));//专利所有者
            tables5_3.add(t);
        }

        //如果tables5_2的size小于4，请加上空的数据时它变成4  如：tables5_2.add( new Datadto());
        dataMap.put("tables5_3", tables5_3);  //专利

        dataMap.put("str5_2", beanHelper.escapeToWord(app.getProduct()));// 产品

        dataMap.put("str7_1", beanHelper.escapeToWord(app.getOtherProduct()));// 其他（包括获得的重要奖项、在国际学术组织兼职、在国际学术会议做重要报告等情况）

        dataMap.put("str8_1", beanHelper.escapeToWord(app.getObjectives()));// 工作设想
        dataMap.put("str8_2", beanHelper.escapeToWord(app.getAgreement()));// 您是否和其他任何单位签订过仍然有效的竞业禁止协议，如果有，请列出。
        dataMap.put("str8_3", app.getRealName());// 申请人签名
        date = new Date();
        dataMap.put("str8_4", yearFormat.format(date));// 年
        dataMap.put("str8_5", monthFormat.format(date));// 月
        dataMap.put("str8_6", dayFormat.format(date));// 日

        dataMap.put("str9_1", app.getSubmitReason());// 推荐理由
        dataMap.put("str9_2", app.getSubmitSupport());// 支持措施
        dataMap.put("str9_3", "");// 负责人签字
        date = app.getSubmitDate();
        if (date == null) {
            date = new Date();
        }
        dataMap.put("str9_4", yearFormat.format(date));// 年
        dataMap.put("str9_5", monthFormat.format(date));// 月
        dataMap.put("str9_6", dayFormat.format(date));// 日

        date = new Date();
        dataMap.put("str10_1", app.getUnitApproveReason());// 对申报材料的审核意见
        dataMap.put("str10_2", app.getPogeReason());// 推荐理由
        dataMap.put("str10_3", app.getUnitApproveSupport());// 支持措施
        dataMap.put("str10_4", "");// 主管部门主要负责人签字
        dataMap.put("str10_5", yearFormat.format(date));// 年
        dataMap.put("str10_6", monthFormat.format(date));// 月
        dataMap.put("str10_7", dayFormat.format(date));// 日
        dataMap.put("str10_8", "");// 省部属单位主要负责人签字
        dataMap.put("str10_9", yearFormat.format(date));// 年
        dataMap.put("str10_10", monthFormat.format(date));// 月
        dataMap.put("str10_11", dayFormat.format(date));// 日
    }

    /**
     * 温州市“580海外精英引进计划”创业类申报书
     *
     * @param dataMap
     * @param app
     */
    private void getData2(Map dataMap, Application app) {
        dataMap = new NoNullMap(dataMap);
//dataMap.put("str1_1", app.getId());// 编号
        dataMap.put("str1_1", app.getNumber());// 编号
        dataMap.put("str1_2", app.getRealName()); // 申报人
        dataMap.put("str1_3", app.getAppOrgName());// 申报单位
        dataMap.put("str1_4", app.getSpecialty());// 专业领域
        dataMap.put("str1_5", app.getPeople());// 联系人
        dataMap.put("str1_6", app.getPhone());// 联系电话
        dataMap.put("str1_7", app.getMobile());// 联系手机

        Date date = app.getComletionDate();
        if (date == null) {
            date = new Date();
        }
        dataMap.put("str1_8", yearFormat.format(date));// 年
        dataMap.put("str1_9", monthFormat.format(date));// 月
        dataMap.put("str1_10", dayFormat.format(date));// 日

        dataMap.put("str4_1", app.getRealName());// 中文名
        dataMap.put("str4_2", app.getSex() == 0 ? "男" : "女");// 性别
        dataMap.put("str4_3", app.getRealEnglishName());// 英文名
        date = app.getBirthDate();
        if (date == null) {
            dataMap.put("str4_4", "");// 出生年
            dataMap.put("str4_5", "");// 出生月
            dataMap.put("str4_6", "");// 出生日
        } else {
            dataMap.put("str4_4", yearFormat.format(date));// 出生年
            dataMap.put("str4_5", monthFormat.format(date));// 出生月
            dataMap.put("str4_6", dayFormat.format(date));// 出生日
        }

        dataMap.put("str4_7", app.getBirthPlace());// 出生地
        dataMap.put("str4_8", app.getNationality());// 国籍
        dataMap.put("str4_9", app.getMgChineseSchool());// 毕业学校中文
        dataMap.put("str4_10", app.getMgEnglishSchool());// 毕业学校英文
        dataMap.put("str4_11", app.getForeignJobChinese());// 工作单位及职务中文
        dataMap.put("str4_12", app.getForeignJobEnglish());// 工作单位及职务英文
        dataMap.put("str4_13", app.getEmployer());// 现任职单位
        dataMap.put("str4_14", app.getPosition());// 职务
        try {
            dataMap.put("str4_15", app.getMyorg().getContact().getAddress());// 单位地址
        } catch (NullPointerException ex) {
            dataMap.put("str4_15", app.getEmployerAddress());
        }
        dataMap.put("str4_16", app.getZip());// 邮编
        dataMap.put("str4_17", beanHelper.toString(app, new String[]{
            "degree",
            "time",
            "country",
            "university",
            "major"
        }, " ", "\n"));// 教育经历
        dataMap.put("str4_18", beanHelper.toString(app, new String[]{
            "jobPosition",
            "jobTime",
            "jobCountry",
            "jobOrg"
        }, " ", "\n"));// 工作经历

        dataMap.put("str5_1", beanHelper.escapeToWord(app.getEntInfo()));// 企业基本情况
        dataMap.put("str5_2", beanHelper.toString(app, new String[]{
            "partnerName",
            "partnerContent",
            "partnerType",
            "partnerPer",
            "partnerPosition"
        }, " ", "\n"));// 资本构成和股权结构
        dataMap.put("str5_3", beanHelper.escapeToWord(app.getEntTeam()));// 技术团队和管理团队
        dataMap.put("str5_4", beanHelper.escapeToWord(app.getEntProject()));// 创业项目
        dataMap.put("str5_5", beanHelper.escapeToWord(app.getEntPlan()));// 市场前景

        dataMap.put("str6_1", app.getSubmitReason());// 推荐理由
        dataMap.put("str6_2", app.getSubmitSupport());// 推荐理由支持措施（包括工作条件和生活待遇等方面的支持措施）

        dataMap.put("str7_1", app.getUnitApproveReason());// 对申报材料的审核意见
        dataMap.put("str7_2", app.getPogeReason());// 推荐理由
        dataMap.put("str7_3", app.getUnitApproveSupport());// 支持措施
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

}
