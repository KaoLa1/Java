package com.watchme.dspcoresupport.utils;


import com.github.pagehelper.PageInfo;
import com.watchme.dspcoresupport.dcs.entity.PageResultEntity;
import com.watchme.dspcoresupport.dcs.enums.GlobalEnum;

/**
 * 返回结果（含分页信息）封装工具类
 *
 * @author weiQiang
 */
public class PageResultUtil {

    /**
     * 失败方法
     *
     * @param globalEnum
     * @return
     */
    public static PageResultEntity error(GlobalEnum globalEnum) {
        return error(globalEnum.getMessage());
    }


    /**
     * 失败方法
     *
     * @param msg
     * @return
     */
    public static PageResultEntity error(String msg) {
        return error(msg, null);
    }


    /**
     * 失败方法
     *
     * @param msg
     * @param data
     * @return
     */
    public static PageResultEntity error(String msg, Object data) {
        return PageResultEntity.builder().success(false).msg(msg).data(data).build();
    }


    /**
     * 操作成功
     *
     * @return PageResultEntity
     */
    public static PageResultEntity msg() {
        return PageResultEntity.builder().success(true).msg(GlobalEnum.MsgOperationSuccess.getMessage()).build();
    }

    /**
     * 操作成功、失败
     *
     * @param total 数据
     * @param count 数量
     * @return PageResultEntity
     */
    public static PageResultEntity msg(Object[] total, int count) {
        if (total.length == count) {
            return PageResultEntity.builder().success(true).msg(GlobalEnum.MsgOperationSuccess.getMessage()).build();
        } else {
            return msg(count);
        }
    }

    /**
     * 操作成功、失败
     *
     * @param count 数量
     * @return PageResultEntity
     */
    public static PageResultEntity msg(Integer count) {
        if (isIntThanZero(count)) {
            return PageResultEntity.builder().success(true).msg(GlobalEnum.MsgOperationSuccess.getMessage()).build();
        }
        return PageResultEntity.builder().success(false).msg(GlobalEnum.MsgOperationFailed.getMessage()).build();
    }

    /**
     * 判断整数是否大于零
     *
     * @param number
     * @return
     */
    public static boolean isIntThanZero(int number) {
        if (number > 0) {
            return true;
        }
        return false;
    }


    /**
     * 成功返回分页数据和总页数
     *
     * @param querySuccess
     * @param resultList
     * @return
     */
    public static PageResultEntity success(GlobalEnum querySuccess, Object resultList) {
        return PageResultEntity.builder().msg(querySuccess.getMessage()).success(true).data(resultList).build();
    }


    /**
     * 成功方法
     *
     * @param globalEnum
     * @return
     */
    public static PageResultEntity success(GlobalEnum globalEnum) {
        return success(globalEnum.getMessage(), null, null);
    }


    /**
     * 成功方法
     *
     * @param msg
     * @param data
     * @return
     */
    public static PageResultEntity success(String msg, Object data, PageInfo pageInfo) {
        return success(msg, data, pageInfo.getTotal(), pageInfo.getPageNum(),
                pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getSize());
    }

    /**
     * 成功方法
     *
     * @param globalEnum
     * @param data
     * @return
     */
    public static PageResultEntity success(GlobalEnum globalEnum, Object data, Long total,
                                           int pageNum, int pageSize, int pages, int size) {
        return success(globalEnum.getMessage(), data, total, pageNum, pageSize, pages, size);
    }

    /**
     * 成功方法
     *
     * @param globalEnum 请求提示
     * @param esPage     分页数据
     * @return PageResultEntity
     */
    /*public static PageResultEntity success(GlobalEnum globalEnum, EsPage esPage) {
        int pageNum = (esPage.getCurrentPage() + 1) / esPage.getPageSize();
        return PageResultEntity.builder().success(true).msg(globalEnum.getMessage())
                .data(esPage.getRecordList())
                .total(Long.valueOf(esPage.getCount()))
                .pageNum(pageNum)
                .pageSize(esPage.getPageSize())
                .pages(esPage.getPageCount())
                .size(esPage.getRecordList().size())
                .build();
    }*/

    /**
     * 成功方法
     *
     * @param msg
     * @param data
     * @return
     */
    public static PageResultEntity success(String msg, Object data, long total,
                                           int pageNum, int pageSize, int pages, int size) {
        return PageResultEntity.builder().success(true).msg(msg)
                .data(data)
                .total(total)
                .pageNum(pageNum)
                .pageSize(pageSize)
                .pages(pages)
                .size(size)
                .build();
    }

    /**
     * 成功方法
     *
     * @param globalEnum 枚举信息
     * @param data       数据
     * @param total      总数量
     * @param pageNum    页码
     * @param pageSize   每页数量
     * @return 分页信息
     */
    public static PageResultEntity success(GlobalEnum globalEnum, Object data,
                                           long total, int pageNum, int pageSize) {
        //计算总页数
        int pages = total % pageSize == 0 ? (int) total / pageSize : (int) total / pageSize +1;
        //计算当前页数量
        int size = pageNum == pages ? (int) (total - (pageNum-1) * pageSize) : pageSize;


        return success(globalEnum, data, total, pageNum, pageSize, pages, size);
    }
    /**
     * 自定义信息
     *
     * @return PageResultEntity
     */
    public static PageResultEntity msg(String msg) {
        return PageResultEntity.builder().success(true).msg(msg).build();
    }
}
