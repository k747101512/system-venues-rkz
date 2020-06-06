package com.cm.venuebooking.pojo.dtos.groundbooking;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xwangs
 * @create 2020-05-13 10:30
 * @description
 */
@ApiModel
public class GroundBookingInfoListDTO extends GroundBookingInfoDTO{

    @ApiModelProperty(name = "groundName", value = "场地名称")
    private String groundName;
    @ApiModelProperty(name = "projectCategory", value = "项目名称")
    private String projectCategory;
    @ApiModelProperty(name = "venueName", value = "场馆名称")
    private String venueName;

    public String getGroundName() {
        return groundName == null ? "" : groundName;
    }

    public void setGroundName(String groundName) {
        this.groundName = groundName;
    }

    public String getProjectCategory() {
        return projectCategory == null ? "" : projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }

    public String getVenueName() {
        return venueName == null ? "" : venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"groundName\":\"")
                .append(groundName).append('\"');
        sb.append(",\"projectCategory\":\"")
                .append(projectCategory).append('\"');
        sb.append(",\"venueName\":\"")
                .append(venueName).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
