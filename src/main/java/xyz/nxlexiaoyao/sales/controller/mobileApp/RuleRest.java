package xyz.nxlexiaoyao.sales.controller.mobileApp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.nxlexiaoyao.sales.bean.BaseBean;
import xyz.nxlexiaoyao.sales.bean.SaleForm;
import xyz.nxlexiaoyao.sales.bean.SaleRule;
import xyz.nxlexiaoyao.sales.dao.SaleFormDao;
import xyz.nxlexiaoyao.sales.dao.SaleRuleDao;
import xyz.nxlexiaoyao.sales.util.MathUtil;
import xyz.nxlexiaoyao.sales.vo.QueueVo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/rule")
public class RuleRest extends BaseRest{
    @Autowired
    private SaleRuleDao saleRuleDao;
    @Autowired
    private SaleFormDao saleFormDao;
    
    @GetMapping("/list.do")
    public BaseBean<List<SaleRule>> list(){
        List<SaleRule> saleRules = saleRuleDao.selectAll();
        return super.success(saleRules);
    }
    

    @GetMapping("/myQueue.do")
    public BaseBean<List<QueueVo>> myQueue(){
        String memberId = getUserId();
        
        List<QueueVo> queueVos = new ArrayList<QueueVo>();
        List<SaleRule> rules = saleRuleDao.selectAll();
        if(rules!= null && rules.size()>0) {
        	rules.forEach(rule->{
            	SaleForm member = saleFormDao.selectLastByRuleIdAndMemberId(rule.getId(), memberId);
            	if(member != null) {
//            		SaleForm out = saleFormDao.selectOutForm(rule.getId());
                	List<SaleForm> tree = saleFormDao.selectAllTree(rule.getId());
                	//树状总单数
                    int treeCount = tree.size();
                	//规则定义的满足层数
                    int maxLayer = rule.getOutNumber();
                    //公排要求的人数
                	int subordinate = MathUtil.multiplyAndAdd(3, 0, maxLayer);
                	//原始排名
                    int insertNo = member.getInsertNo();
                    
                	
                	Set<Integer> myTreeInsertNo = getMyTreeInsertNo(insertNo, treeCount);
                    AtomicInteger rank = new AtomicInteger(1);//每有一个下级，则增加1,就是他自己的排名
                    SaleForm condition = new SaleForm();
                    condition.setRuleId(rule.getId());
                    condition.setVerifyStatus("Y");
                    condition.setIsOut("N");
                    List<SaleForm> allFormTree = saleFormDao.selectLineList(condition);
                    
                    if(!myTreeInsertNo.isEmpty()) {
                    	Iterator<Integer> ite = myTreeInsertNo.iterator();
                        while(ite.hasNext()) {
                        	int childInsertNo = ite.next();
                        	Iterator<SaleForm> saleFormIte = allFormTree.iterator();
                        	while(saleFormIte.hasNext()) {
                        		SaleForm thisForm = saleFormIte.next();
                        		if(!member.getId().equals(thisForm.getId()) && childInsertNo == thisForm.getInsertNo().intValue()) {
                        			rank.incrementAndGet();
                        		}
                        	}
                        }
                        ite = null;
                    }
                	
                	
                	QueueVo queueVo = new QueueVo();
                	queueVo.setCapacity(subordinate);
                	queueVo.setQueueName(rule.getRuleName());
                	queueVo.setHas(rank.get());
                	queueVo.setRank(rank.get());
                	queueVo.setRuleId(rule.getId());
                	queueVos.add(queueVo);
            	}
            	
            });
        }
        
        return success(queueVos);
    }
    
    private static Set<Integer> getMyTreeInsertNo(int insertNo,int treeCount){
    	Set<Integer> myTreeInsertNo = new HashSet<Integer>();
    	insertNo *= 3;
        for(int i=0;i<3;i++) {
        	insertNo++;
        	if(insertNo >= treeCount) {
        		break;
        	}
        	Integer thisVal = new Integer(insertNo);
        	myTreeInsertNo.add(thisVal);
        	Set<Integer> children = getMyTreeInsertNo(thisVal.intValue(),treeCount);
        	if(!children.isEmpty()) {
        		myTreeInsertNo.addAll(children);
        	}
        }
        return myTreeInsertNo;
    }
}
