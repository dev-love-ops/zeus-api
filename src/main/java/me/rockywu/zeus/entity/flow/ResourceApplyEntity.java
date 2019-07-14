package me.rockywu.zeus.entity.flow;

/**
 * 资源申请工单提交参数实体
 * @author RockyWu
 * @date 2019-01-22 23:02
 */
public class ResourceApplyEntity {
    private String token;
    private ServerDataEntity serverData;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ServerDataEntity getServerData() {
        return serverData;
    }

    public void setServerData(ServerDataEntity serverData) {
        this.serverData = serverData;
    }

    @Override
    public String toString() {
        return "ResourceApplyEntity{" +
                "token='" + token + '\'' +
                ", serverDateEntity=" + serverData +
                '}';
    }
}
