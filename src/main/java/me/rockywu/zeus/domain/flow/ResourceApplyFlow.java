package me.rockywu.zeus.domain.flow;

/**
 * @author RockyWu
 * @date 2019-01-21 21:29
 */
public class ResourceApplyFlow extends BaseFlow {
    /**
     * 申请人提交的服务器申请相关信息
     */
    private String serverData;

    public String getServerData() {
        return serverData;
    }

    public void setServerData(String serverData) {
        this.serverData = serverData;
    }
}
