package com.cm.venuebooking.pojo.dtos.groundconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @ClassName: GroundInfoDTO
 * @Description: 场地信息表
 * @Author: WenG
 * @Date: 2020-04-22 11:46
 * @Version: 1.0
 **/
@ApiModel
public class GroundInfoDTO {

    @ApiModelProperty(name = "venuesProjectId", value = "项目ID")
    private String venuesProjectId;
    @ApiModelProperty(name = "groundInfoId", value = "主键")
    private String groundInfoId;
    @ApiModelProperty(name = "groundName", value = "场地名称")
    private String groundName;
    @ApiModelProperty(name = "groundLocation", value = "场地具体位置")
    private String groundLocation;
    @ApiModelProperty(name = "openCloseType", value = "场地开闭状态")
    private String openCloseType;

    public String getVenuesProjectId() {
        return venuesProjectId;
    }

    public void setVenuesProjectId(String venuesProjectId) {
        this.venuesProjectId = venuesProjectId;
    }

    public String getGroundInfoId() {
        return groundInfoId;
    }

    public void setGroundInfoId(String groundInfoId) {
        this.groundInfoId = groundInfoId;
    }

    public String getGroundName() {
        return groundName;
    }

    public void setGroundName(String groundName) {
        this.groundName = groundName;
    }

    public String getGroundLocation() {
        return groundLocation;
    }

    public void setGroundLocation(String groundLocation) {
        this.groundLocation = groundLocation;
    }

    public String getOpenCloseType() {
        return openCloseType;
    }

    public void setOpenCloseType(String openCloseType) {
        this.openCloseType = openCloseType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"venuesProjectId\":\"")
                .append(venuesProjectId).append('\"');
        sb.append(",\"groundInfoId\":\"")
                .append(groundInfoId).append('\"');
        sb.append(",\"groundName\":\"")
                .append(groundName).append('\"');
        sb.append(",\"groundLocation\":\"")
                .append(groundLocation).append('\"');
        sb.append(",\"openCloseType\":\"")
                .append(openCloseType).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
