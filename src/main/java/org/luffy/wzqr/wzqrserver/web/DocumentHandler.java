package org.luffy.wzqr.wzqrserver.web;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
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

    public void exportExcel(List<Application> list, OutputStream out) throws WriteException, IOException {
        // 创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(out);
        // 创建新的一页
        WritableSheet sheet = workbook.createSheet("第一页", 0);
        sheet.mergeCells(0, 1, 25, 1);
        sheet.addCell(new Label(0, 1, "温州市\"580海外精英引进计划\"申报人选情况汇总表"));
        sheet.addCell(new Label(0, 2, "填表时间："+fullFormat.format(new Date())));

        sheet.addCell(new Label(0, 3, "序号"));
        sheet.addCell(new Label(1, 3, "姓名"));
        sheet.addCell(new Label(2, 3, "性别"));
        sheet.addCell(new Label(3, 3, "国籍"));
        sheet.addCell(new Label(4, 3, "出生日期"));
        sheet.addCell(new Label(5, 3, "最高(海外)学历学位"));
        sheet.addCell(new Label(6, 3, "毕业院校"));
        sheet.addCell(new Label(7, 3, "用人(申报)单位/创办企业"));
        sheet.addCell(new Label(8, 3, "职务/拟任职务或职称"));
        sheet.addCell(new Label(9, 3, "专业领域"));
        sheet.addCell(new Label(10, 3, "专业方向"));
        sheet.addCell(new Label(11, 3, "专利授权或研发成果情况"));
        sheet.addCell(new Label(12, 3, "目前实际到位资金占比%"));
        sheet.addCell(new Label(13, 3, "个人或风投的占股比例或资金额度"));
        sheet.addCell(new Label(14, 3, "承诺每年国内工作时限（月/年）"));
        sheet.addCell(new Label(15, 3, "落地市"));
        sheet.addCell(new Label(16, 3, "到中国前单位"));
        sheet.addCell(new Label(17, 3, "职务"));
        sheet.addCell(new Label(18, 3, "（拟）到中国时间"));
        sheet.addCell(new Label(19, 3, "创新类签订合同时间/创业类企业完成工商注册时间"));
        sheet.addCell(new Label(20, 3, "引进平台"));
        sheet.addCell(new Label(21, 3, "申报单位所属"));
        sheet.addCell(new Label(22, 3, "人才类型"));
        sheet.addCell(new Label(23, 3, "是否破格"));
        sheet.addCell(new Label(24, 3, "牵头单位"));
        sheet.addCell(new Label(25, 3, "备注"));

        for (int i = 0; i < list.size(); i++) {
            Application app = list.get(i);
            sheet.addCell(new NoNullLabel(0, 4 + i, app.getNumber()));
            sheet.addCell(new NoNullLabel(1, 4 + i, app.getRealName()));
            sheet.addCell(new NoNullLabel(2, 4 + i, app.getSex()==0?"男":"女"));
            sheet.addCell(new NoNullLabel(3, 4 + i, app.getNationality()));
            sheet.addCell(new NoNullLabel(4, 4 + i, app.getBirthDate()!=null?fullFormat.format(app.getBirthDate()):""));
            sheet.addCell(new NoNullLabel(5, 4 + i, app.getMgChineseDegree()));
            sheet.addCell(new NoNullLabel(6, 4 + i, app.getMgChineseSchool()));
            sheet.addCell(new NoNullLabel(7, 4 + i, app.getAppOrgName()));
            sheet.addCell(new NoNullLabel(8, 4 + i, app.getPosition()));
            sheet.addCell(new NoNullLabel(9, 4 + i, app.getSpecialty()));
            sheet.addCell(new NoNullLabel(10, 4 + i, app.getProfession()));
            sheet.addCell(new NoNullLabel(11, 4 + i, app.getPatentDesc()));
            sheet.addCell(new NoNullLabel(12, 4 + i, app.getActualCurrentFundsPer()+"%"));
            sheet.addCell(new NoNullLabel(13, 4 + i, app.getMyFundsPer()+"%"));
            sheet.addCell(new NoNullLabel(14, 4 + i, ""));// 承诺每年国内工作时限（月/年）?
            sheet.addCell(new NoNullLabel(15, 4 + i, app.getCity()));
            sheet.addCell(new NoNullLabel(16, 4 + i, app.getForeignJobChinese()));
            sheet.addCell(new NoNullLabel(17, 4 + i, ""));//职务
            sheet.addCell(new NoNullLabel(18, 4 + i, app.getWdate()));
            sheet.addCell(new NoNullLabel(19, 4 + i, ""));//创新类签订合同时间/创业类企业完成工商注册时间
            sheet.addCell(new NoNullLabel(20, 4 + i, app.getPlatform()));
            sheet.addCell(new NoNullLabel(21, 4 + i, app.getOrgSubName()));
            sheet.addCell(new NoNullLabel(22, 4 + i, app.getType()));
            sheet.addCell(new NoNullLabel(23, 4 + i, app.isPoge()?"是":"女"));
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
        try{
            dataMap.put("str4_15", app.getMyorg().getContact().getAddress());// 单位地址
        }catch(NullPointerException ex){
            dataMap.put("str4_15",app.getEmployerAddress());
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
        for (int i = 0; i < 5; i++) {
            //TODO...
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
        for (int i = 0; i < 5; i++) {
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
        for (int i = 0; i < 5; i++) {
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
        try{
            dataMap.put("str4_15", app.getMyorg().getContact().getAddress());// 单位地址
        }catch(NullPointerException ex){
            dataMap.put("str4_15",app.getEmployerAddress());
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
