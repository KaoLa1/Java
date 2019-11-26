package com.watchme.dspcoresupport.utils;

import com.watchme.dspcoresupport.bases.exc.IndustryException;
import com.watchme.dspcoresupport.dcs.entity.DayTime;


/**
 * cron表达式解析
 */
public class CronUtils {

    /**
     * performCycle 执行周期 、每周、每月
     * performTime 执行时间
     *  DAY 对于每天的直接为具体时间（例如：11:30:00）
     *      例如 11:30:00 表示每天的十一点半执行
     *  WEEK 对于每周使用下划线（_）分隔为两部分 前半部分为周几 数字1-7（1 ＝ 星期日）
     *      例如 4_11:30:00 表示每周3十一点半执行一次 对应的cron表达式为 0 30 11 ? * 4
     *  MONTH 每月使用下划线(_)分割为两部分 前半部分为每月的几号
     *      例如 20_11:30:00 表示每月的20号的十一点半执行一次 对应cron表达式 0 30 11 20 * ?
     */


    public static void main(String[] args){
        String s = weekCron("4_11:30:00");
        System.out.println(s);
    }

    public static final String DAY = "day";
    public static final String WEEK = "week";
    public static final String MONTH = "month";

    /**
     * 获取cron表达式
     * @param performCycle 执行周期
     * @param performTime 执行时间
     * @return cron
     */
    public static String cronExpressionBuilder(String performCycle, String performTime){
        switch (performCycle){
            case DAY: return dayCron(performTime);
            case WEEK: return weekCron(performTime);
            case MONTH: return monthCron(performTime);
            default: throw new IndustryException("暂不支持的周期类型");
        }
    }

    /**
     * 日 cron表达式
     * DAY hh:mm:ss 每天的什么时间执行
     * @param performTime 执行时间
     * @return cron
     */
    private static String dayCron(String performTime){
        String[] cronArray = {"*","*","*","*","*","?"};
        DayTime detailTime = getDetailTime(performTime);
        cronArray[0] = detailTime.getSecond();
        cronArray[1] = detailTime.getMinute();
        cronArray[2] = detailTime.getHour();
        return cronArrayToString(cronArray);
    }

    /**
     * 周 cron表达式
     * WEEK week_hh:mm:ss 每周几的什么时间执行
     * @param performTime 执行时间
     * @return cron
     */
    private static String weekCron(String performTime){
        String[] cronArray = {"*","*","*","?","*","*"};
        if(StringUtil.isEmpty(performTime)) {
            throw new IndustryException("执行时间 为空");
        }
        String[] weekArray = performTime.split("_");
        if(weekArray.length != 2){
            throw new IndustryException("执行时间格式错误"+performTime);
        }
        cronArray[5] = weekArray[0];
        DayTime detailTime = getDetailTime(weekArray[1]);
        cronArray[0] = detailTime.getSecond();
        cronArray[1] = detailTime.getMinute();
        cronArray[2] = detailTime.getHour();
        return cronArrayToString(cronArray);
    }

    /**
     * 月 cron表达式
     * MONTH month_hh:mm:ss 每月几号的什么时间执行
     * @param performTime
     * @return
     */
    private static String monthCron( String performTime){
        String[] cronArray = {"*","*","*","*","*","?"};
        if(StringUtil.isEmpty(performTime)) {
            throw new IndustryException("执行时间 为空");
        }
        String[] weekArray = performTime.split("_");
        if(weekArray.length != 2) {
            throw new IndustryException("执行时间格式错误"+performTime);
        }
        cronArray[3] = weekArray[0];
        DayTime detailTime = getDetailTime(weekArray[1]);
        cronArray[0] = detailTime.getSecond();
        cronArray[1] = detailTime.getMinute();
        cronArray[2] = detailTime.getHour();
        return cronArrayToString(cronArray);
    }

    /**
     * 获取执行时间- 时分秒
     * @param performTime 执行时间为空
     * @return cron
     */
    private static DayTime getDetailTime(String performTime){
        if(StringUtil.isEmpty(performTime)) {
            throw new IndustryException("执行时间为空");
        }
        String[] timeArray = performTime.split(":");
        if(timeArray.length != 3){
            throw new IndustryException("执行时间格式错误"+performTime);
        }
        return DayTime.builder()
                .hour(timeArray[0])
                .minute(timeArray[1])
                .second(timeArray[2])
                .build();
    }

    private static String cronArrayToString(String[] cronArray){
        String cron = "";
        for(int i = 0; i < cronArray.length; i++){
            cron += cronArray[i] + " ";
        }
        return cron.trim();
    }

    public static String weekName(String performCycle){
        switch (performCycle){
            case "1": return "日";
            case "2": return "一";
            case "3": return "二";
            case "4": return "三";
            case "5": return "四";
            case "6": return "五";
            case "7": return "六";
            default: throw new IndustryException("暂不支持的周期类型");
        }
    }
}
