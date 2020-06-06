package com.cm.venuebooking.pojo.dtos.groundconfig;

import com.cm.venuebooking.pojo.vos.groundconfig.GroundInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @ClassName: GroundConfigDTO
 * @Description: 场地配置表
 * @Author: WenG
 * @Date: 2020-04-22 11:46
 * @Version: 1.0
 **/
@ApiModel
public class GroundConfigDTO extends GroundInfoDTO {

    @ApiModelProperty(name = "groundConfigId", value = "主键")
    private String groundConfigId;
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
    @ApiModelProperty(name = "singlePrice", value = "单场价格")
    private String singlePrice;

    public String getGroundConfigId() {
        return groundConfigId;
    }

    public void setGroundConfigId(String groundConfigId) {
        this.groundConfigId = groundConfigId;
    }

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

    public String getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(String singlePrice) {
        this.singlePrice = singlePrice;
    }
}
