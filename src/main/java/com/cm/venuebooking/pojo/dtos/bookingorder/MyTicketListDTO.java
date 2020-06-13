package com.cm.venuebooking.pojo.dtos.bookingorder;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 预订订单信息
 * @author xwangs
 * @create 2020-06-08 16:44
 * @description
 */
public class MyTicketListDTO {

    @ApiModelProperty(name = "groundBookingId", value = "订单ID")
    private String groundBookingId;
    @ApiModelProperty(name = "serial", value = "流水号")
    private String serial;
    @ApiModelProperty(name = "venuesName", value = "场馆")
    private String venuesName;
    @ApiModelProperty(name = "venuePanorama", value = "场馆图片")
    private String venuePanorama;
    @ApiModelProperty(name = "projectName", value = "项目")
    private String projectName;
    @ApiModelProperty(name = "gmtCreate", value = "预订时间")
    private String gmtCreate;
    @ApiModelProperty(name = "orderType", value = "订单状态0正常1取消2过期")
    private String orderType;
    @ApiModelProperty(name = "price", value = "总金额")
    private String price;
    @ApiModelProperty(name = "itemCount", value = "共预订场次数")
    private String itemCount;
    @ApiModelProperty(name = "itemCount", value = "共预订场次数")
    private List<GroundBookingItemDTO> itemDTOList;

    public String getGroundBookingId() {
        return groundBookingId == null ? "" : groundBookingId;
    }

    public void setGroundBookingId(String groundBookingId) {
        this.groundBookingId = groundBookingId;
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

    public String getVenuePanorama() {
        return venuePanorama == null ? "" : venuePanorama;
    }

    public void setVenuePanorama(String venuePanorama) {
        this.venuePanorama = venuePanorama;
    }

    public String getProjectName() {
        return projectName == null ? "" : projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getGmtCreate() {
        return gmtCreate == null ? "" : gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getOrderType() {
        return orderType == null ? "" : orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPrice() {
        return price == null ? "" : price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItemCount() {
        return itemCount == null ? "" : itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
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
        sb.append("\"groundBookingId\":\"")
                .append(groundBookingId).append('\"');
        sb.append(",\"serial\":\"")
                .append(serial).append('\"');
        sb.append(",\"venuesName\":\"")
                .append(venuesName).append('\"');
        sb.append(",\"venuePanorama\":\"")
                .append(venuePanorama).append('\"');
        sb.append(",\"projectName\":\"")
                .append(projectName).append('\"');
        sb.append(",\"gmtCreate\":\"")
                .append(gmtCreate).append('\"');
        sb.append(",\"orderType\":\"")
                .append(orderType).append('\"');
        sb.append(",\"price\":\"")
                .append(price).append('\"');
        sb.append(",\"itemCount\":\"")
                .append(itemCount).append('\"');
        sb.append(",\"itemDTOList\":")
                .append(itemDTOList);
        sb.append('}');
        return sb.toString();
    }
}
