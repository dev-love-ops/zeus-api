package me.rockywu.zeus.mapper.flow;

import me.rockywu.zeus.domain.flow.ResourceApplyFlow;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author RockyWu
 * @date 2019-01-21 21:42
 */
@Mapper
public interface ResourceApplyFlowMapper {
    @Results(id = "resourceApplyFlowResultMap", value = {
            @Result(column = "flow_id", property = "flowId"),
            @Result(column = "apply_user", property = "applyUser"),
            @Result(column = "verify_user", property = "verifyUser"),
            @Result(column = "operate_user", property = "operateUser"),
            @Result(column = "apply_time", property = "applyTime"),
            @Result(column = "verify_time", property = "verifyTime"),
            @Result(column = "close_time", property = "closeTime"),
            @Result(column = "failed_reason", property = "failedReason"),
            @Result(column = "status", property = "status"),
            @Result(column = "server_data", property = "serverData"),
    })
    @Select("select * from resource_apply_flow")
    List<ResourceApplyFlow> getAllFlows();

    @ResultMap("resourceApplyFlowResultMap")
    @Select("SELECT * from resource_apply_flow where flow_id=#{flow_id}")
    ResourceApplyFlow getFlowById(@Param("flow_id") String flowId);

    @Insert("INSERT INTO resource_apply_flow(flow_id, apply_user, apply_time, status, server_data) VALUES(#{flowId}, #{applyUser}, #{applyTime}, #{status}, #{serverData})")
    int insertOne(ResourceApplyFlow resourceApplyFlow);
}
