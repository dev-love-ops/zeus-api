package me.rockywu.zeus.controller.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import me.rockywu.zeus.common.JsonResult;
import me.rockywu.zeus.entity.flow.ResourceApplyEntity;
import me.rockywu.zeus.entity.flow.ServerDataEntity;
import me.rockywu.zeus.enums.ErrorCode;
import me.rockywu.zeus.service.flow.ResourceApplyFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author rocky
 */
@RestController
@RequestMapping("/v1/flow/resource")
public class ResourceApplyFlowController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResourceApplyFlowService resourceApplyFlowService;

    /**
     * 申请工单接口
     * @param resourceApplyEntity
     * @return
     */
    @PostMapping("/apply")
    public JsonResult applyFlow(@RequestBody ResourceApplyEntity resourceApplyEntity){
        System.out.println(resourceApplyEntity);
        try {
            resourceApplyFlowService.insertOne(resourceApplyEntity);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return JsonResult.error(ErrorCode.RESOURCE_APPLY_FLOW_CREATED_FAILED.getCode(), ErrorCode.RESOURCE_APPLY_FLOW_CREATED_FAILED.getDesc());
        }
        return JsonResult.success("工单创建成功!");
    }

    @GetMapping("/resourceList")
    public JsonResult getFlowList(){
        ServerDataEntity serverDateEntity = new ServerDataEntity();
        serverDateEntity.setComment("不好吃");
        serverDateEntity.setCount(12);
        serverDateEntity.setService(1);
        ResourceApplyEntity resourceApplyEntity = new ResourceApplyEntity();
        resourceApplyEntity.setToken("123abc");
        return JsonResult.success("查询成功", "OK");
    }

}
