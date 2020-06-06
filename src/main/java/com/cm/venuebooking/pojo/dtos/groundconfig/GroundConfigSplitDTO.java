package com.cm.venuebooking.pojo.dtos.groundconfig;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author xwangs
 * @create 2020-05-11 16:02
 * @description
 */
public class GroundConfigSplitDTO{

    @ApiModelProperty(name = "timeStr", value = "开始时间")
    private String timeStr;
    @ApiModelProperty(name = "timeEnd", value = "开始时间")
    private String timeEnd;
    @ApiModelProperty(name = "singlePrice", value = "价格")
    private String singlePrice;
    @ApiModelProperty(name = "bookingType", value = "是否被预约true|false")
    private String bookingType;
    @ApiModelProperty(name = "status", value = "状态")
    private String status;

    public String getTimeStr() {
        return timeStr == null ? "" : timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getTimeEnd() {
        return timeEnd == null ? "" : timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getSinglePrice() {
        return singlePrice == null ? "" : singlePrice;
    }

    public void setSinglePrice(String singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getBookingType() {
        return bookingType == null ? "" : bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"timeStr\":\"")
                .append(timeStr).append('\"');
        sb.append(",\"timeEnd\":\"")
                .append(timeEnd).append('\"');
        sb.append(",\"singlePrice\":\"")
                .append(singlePrice).append('\"');
        sb.append(",\"bookingType\":\"")
                .append(bookingType).append('\"');
        sb.append(",\"status\":\"")
                .append(status).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
