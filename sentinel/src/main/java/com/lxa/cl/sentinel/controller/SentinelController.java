package com.lxa.cl.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.lxa.cl.sentinel.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SentinelController {
    private static final String RESOUR_NAME="user";

    @RequestMapping("/hello")
    public String hello(){
        Entry entry=null;
        try {
            entry = SphU.entry("hello");
            return "hello";
        } catch (BlockException e) {

            System.out.println("被流控了");
            return "xxx";
        }finally {
            if(entry!=null){
                entry.exit();
            }
        }
    }

    @PostConstruct
    public static void initRule(){
        //限流配置
        List<FlowRule> list=new ArrayList();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("hello");
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(1);
        list.add(flowRule);

        FlowRule flowRule1 = new FlowRule();
        flowRule1.setResource(RESOUR_NAME);
        flowRule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule1.setCount(1);
        list.add(flowRule1);

        FlowRuleManager.loadRules(list);

        //熔断降级配置
        List<DegradeRule> dRuleList=new ArrayList();
        DegradeRule degradeRule=new DegradeRule();
        degradeRule.setResource("grade");//资源名
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);//异常数量
        degradeRule.setCount(2);//异常超过2次就会进行熔断降级操作
        degradeRule.setMinRequestAmount(1);//最小调用此数，必须调用此数大于这个数才会触发
        degradeRule.setStatIntervalMs(60*1000);//在多长时间的异常数
        degradeRule.setTimeWindow(10);//熔断时间，这个时间调用都会到降级方法，当过了这个时间，处于半开状态，在次调用出现一次异常就会触发熔断降级
        dRuleList.add(degradeRule);
        DegradeRuleManager.loadRules(dRuleList);
    }
    @RequestMapping("/user")
    @SentinelResource(value = RESOUR_NAME,blockHandler = "blockHandlerUserInfo")
    public User userInfo(String id){
        return new User("zhangsan");
    }

    public User blockHandlerUserInfo(String id,BlockException e){
        e.printStackTrace();
        System.out.println("liujng");
        return new User("你被限制了");
    }


    @RequestMapping("/grade")
    @SentinelResource(value = "grade",blockHandler = "gradeBlockHandlerUserInfo")
    public User gradeInfo(String id){
        throw new RuntimeException();
        //return new User("zhangsan");
    }

    public User gradeBlockHandlerUserInfo(String id,BlockException e){
        e.printStackTrace();
        return new User("熔断");
    }

}
