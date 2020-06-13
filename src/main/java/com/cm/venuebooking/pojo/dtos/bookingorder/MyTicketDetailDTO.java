package com.cm.venuebooking.pojo.dtos.bookingorder;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单详情
 * @author xwangs
 * @create 2020-06-12 17:39
 * @description
 */
public class MyTicketDetailDTO {

    @ApiModelProperty(name = "bookingInfoId", value = "订单ID")
    private String bookingInfoId;
    @ApiModelProperty(name = "serial", value = "流水号")
    private String serial;
    @ApiModelProperty(name = "venuesName", value = "场馆名")
    private String venuesName;
    @ApiModelProperty(name = "projectName", value = "项目名")
    private String projectName;
    @ApiModelProperty(name = "itemDTOList", value = "预订项集合")
    private List<GroundBookingItemDTO> itemDTOList;

    public String getBookingInfoId() {
        return bookingInfoId == null ? "" : bookingInfoId;
    }

    public void setBookingInfoId(String bookingInfoId) {
        this.bookingInfoId = bookingInfoId;
    }

    public String getSerial() {
        return serial == null ? "" : serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getVenuesName() {
        return venuesName == null ? "" : venuesName;
    }

    public void setVenuesName(String venuesName) {
        this.venuesName = venuesName;
    }

    public String getProjectName() {
        return projectName == null ? "" : projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<GroundBookingItemDTO> getItemDTOList() {
        if (itemDTOList == null) {
            return new ArrayList<>();
        }
        return itemDTOList;
    }

    public void setItemDTOList(List<GroundBookingItemDTO> itemDTOList) {
        this.itemDTOList = itemDTOList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"bookingInfoId\":\"")
                .append(bookingInfoId).append('\"');
        sb.append(",\"serial\":\"")
                .append(serial).append('\"');
        sb.append(",\"venuesName\":\"")
                .append(venuesName).append('\"');
        sb.append(",\"projectName\":\"")
                .append(projectName).append('\"');
        sb.append(",\"itemDTOList\":")
                .append(itemDTOList);
        sb.append('}');
        return sb.toString();
    }
}
