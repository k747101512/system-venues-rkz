package com.cm.venuebooking.pojo.vos.groundconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @ClassName: GroundConfigVO
 * @Description: 场地配置表
 * @Author: WenG
 * @Date: 2020-04-22 11:46
 * @Version: 1.0
 **/
@ApiModel
public class GroundConfigVO extends GroundInfoVO {

    @ApiModelProperty(name = "week", value = "星期")
    private String week;
    @ApiModelProperty(name = "orderIndex", value = "排序")
    private String orderIndex;
    @ApiModelProperty(name = "timeAMStr", value = "上午营业时间始")
    private String timeAMStr;
    @ApiModelProperty(name = "timeAMEnd", value = "下午营业时间止")
    private String timeAMEnd;
    @ApiModelProperty(name = "timePMStr", value = "下午营业时间始")
    private String timePMStr;
    @ApiModelProperty(name = "timePMEnd", value = "下午营业时间止")
    private String timePMEnd;
    @ApiModelProperty(name = "lengthTime", value = "单场时限")
    private String lengthTime;
    @ApiModelProperty(name = "restTime", value = "场间间隔时间")
    private String restTime;
    @ApiModelProperty(name = "singlPrice", value = "单场价格")
    private String singlPrice;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(String orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getTimeAMStr() {
        return timeAMStr;
    }

    public void setTimeAMStr(String timeAMStr) {
        this.timeAMStr = timeAMStr;
    }

    public String getTimeAMEnd() {
        return timeAMEnd;
    }

    public void setTimeAMEnd(String timeAMEnd) {
        this.timeAMEnd = timeAMEnd;
    }

    public String getTimePMStr() {
        return timePMStr;
    }

    public void setTimePMStr(String timePMStr) {
        this.timePMStr = timePMStr;
    }

    public String getTimePMEnd() {
        return timePMEnd;
    }

    public void setTimePMEnd(String timePMEnd) {
        this.timePMEnd = timePMEnd;
    }

    public String getLengthTime() {
        return lengthTime;
    }

    public void setLengthTime(String lengthTime) {
        this.lengthTime = lengthTime;
    }

    public String getRestTime() {
        return restTime;
    }

    public void setRestTime(String restTime) {
        this.restTime = restTime;
    }

    public String getSinglPrice() {
        return singlPrice;
    }

    public void setSinglPrice(String singlPrice) {
        this.singlPrice = singlPrice;
    }
}
