package me.rockywu.zeus.entity.flow;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户申请服务器信息实体
 * @author RockyWu
 * @date 2019-01-22 22:58
 */
public class ServerDataEntity {
    /**
     * 服务ID
     */
    private int service;
    /**
     * 申请的服务器类型
     */
    private String type;
    /**
     * 申请数量
     */

    private Integer count;
    /**
     * 备注
     */
    private String comment;

    /**
     * 这里是为了说明@JsonIgnore这个注解的作用, 序列化和反序列的时候忽略该属性
     * 磁盘大小(G)
     */
    @JsonIgnore
    private Integer diskSize;

    public Integer getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(Integer diskSize) {
        this.diskSize = diskSize;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ServerDataEntity{" +
                "service=" + service +
                ", type='" + type + '\'' +
                ", count=" + count +
                ", comment='" + comment + '\'' +
                '}';
    }
}
