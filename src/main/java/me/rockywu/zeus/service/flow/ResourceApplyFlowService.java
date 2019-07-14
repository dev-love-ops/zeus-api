package me.rockywu.zeus.service.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.rockywu.zeus.common.FlowStatusEnum;
import me.rockywu.zeus.domain.flow.ResourceApplyFlow;
import me.rockywu.zeus.entity.flow.ResourceApplyEntity;
import me.rockywu.zeus.mapper.flow.ResourceApplyFlowMapper;
import me.rockywu.zeus.util.JwtTokenUtil;
import me.rockywu.zeus.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author RockyWu
 * @date 2019-01-21 22:15
 */
@Service
public class ResourceApplyFlowService {
    @Autowired
    private ResourceApplyFlowMapper resourceApplyFlowMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired


    public List<ResourceApplyFlow> getAllFlows(){
        return resourceApplyFlowMapper.getAllFlows();
    }

    public int insertOne(ResourceApplyEntity resourceApplyEntity) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String username = jwtTokenUtil.getUsernameFromToken(resourceApplyEntity.getToken());

        ResourceApplyFlow resourceApplyFlow = new ResourceApplyFlow();

        resourceApplyFlow.setFlowId(generateFlowId("flow", username));
        resourceApplyFlow.setApplyUser(username);
        resourceApplyFlow.setApplyTime(TimeUtil.getTimestamp());
        resourceApplyFlow.setStatus(FlowStatusEnum.TO_BE_VERIFY.getCode());
        resourceApplyFlow.setServerData(mapper.writeValueAsString(resourceApplyEntity.getServerData()));
        return resourceApplyFlowMapper.insertOne(resourceApplyFlow);
    }

    /**
     * 生成工单ID, 一般不用太复杂, 因为工单的申请频率不是很高
     * 工单类型-申请人-时间戳
     * @param username 用户名
     * @return
     */
    private String generateFlowId(String type, String username){
        return MessageFormat.format("{0}-{1}-{2}", type, username, TimeUtil.getTimeStamps());
    }
}
