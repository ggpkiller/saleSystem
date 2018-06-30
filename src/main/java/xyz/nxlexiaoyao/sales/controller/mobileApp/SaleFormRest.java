package xyz.nxlexiaoyao.sales.controller.mobileApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



import xyz.nxlexiaoyao.sales.bean.BaseBean;
import xyz.nxlexiaoyao.sales.bean.Member;
import xyz.nxlexiaoyao.sales.bean.SaleForm;
import xyz.nxlexiaoyao.sales.bean.SaleRule;
import xyz.nxlexiaoyao.sales.dao.MemberDao;
import xyz.nxlexiaoyao.sales.dao.SaleFormDao;
import xyz.nxlexiaoyao.sales.dao.SaleRuleDao;
import xyz.nxlexiaoyao.sales.util.PasswordUtil;
import xyz.nxlexiaoyao.sales.util.RandomStrUtil;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/saleForm")
public class SaleFormRest extends BaseRest {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private SaleRuleDao saleRuleDao;
    @Autowired
    private SaleFormDao saleFormDao;

    @PostMapping("/add.do")
    @ResponseStatus(HttpStatus.OK)
    public BaseBean<String> addSaleForm(@RequestBody SaleForm saleForm, HttpServletRequest request){
    	System.out.println(saleForm.getRefereeId());
        String userId = request.getHeader("userId");
        String msg = "报单成功！等待工作人员审核。";
        saleForm.setFormType(userId);
        saleFormDao.save(saleForm);
        
        return super.success(null,msg);
    }
    
    @GetMapping("/dingbo.do")
    @ResponseStatus(HttpStatus.OK)
    public BaseBean<String> dingbo(@RequestParam("ruleId") String ruleId){
    	String content = "";
    	List<SaleForm> tree = saleFormDao.selectAllTree(ruleId);
    	int treeCount = tree.size();
    	String memberId = getUserId();
    	SaleForm saleForm = saleFormDao.selectLastByRuleIdAndMemberId(ruleId, memberId);
    	String formId = saleForm.getId();
    	
    	SaleForm condition = new SaleForm();
    	condition.setVerifyStatus("Y");
    	condition.setIsOut("N");
    	condition.setRuleId(ruleId);
    	List<SaleForm> allFormTree = saleFormDao.selectLineList(condition);
    	condition.setId(formId);
    	saleForm = saleFormDao.selectLineList(condition).get(0);
    	int totalReferrCount = saleForm.getTotalRefereCount();
    	int insertNo = saleForm.getInsertNo();
    	Set<Integer> myTreeInsertNo = getMyTreeInsertNo(insertNo, treeCount);
    	AtomicInteger rank = new AtomicInteger(1);//每有一个下级，则增加1,就是他自己的排名
        if(!myTreeInsertNo.isEmpty()) {
        	Iterator<Integer> ite = myTreeInsertNo.iterator();
            while(ite.hasNext()) {
            	int childInsertNo = ite.next();
            	Iterator<SaleForm> saleFormIte = allFormTree.iterator();
            	while(saleFormIte.hasNext()) {
            		SaleForm thisForm = saleFormIte.next();
            		if(!formId.equals(thisForm.getId()) && childInsertNo == thisForm.getInsertNo().intValue()) {
            			rank.incrementAndGet();
            		}
            	}
            }
        }
        int refereeCount = saleForm.getRefereeCount();//直推人数
        
        boolean isLeader = false;
        String findDingboCondition = "N";
        
        
        SaleRule rule = saleRuleDao.selectById(ruleId);
        
        int leaderFirstRank = rule.getLeaderFirstRank().intValue();
        int leaderSecondRank = rule.getLeaderSecondRank().intValue();
        int leaderThirdRank = rule.getLeaderThirdRank().intValue();
        
        int leaderReferee = rule.getLeaderReferee().intValue();
        int leaderUnderNumber = rule.getLeaderUnderNumber().intValue();
        
        /*int commonFirstRank = rule.getCommonFirstRank().intValue();
        int commonFirstReferee = rule.getCommonFirstReferee().intValue();
        
        
        int commonSecondRank = rule.getCommonSecondRank().intValue();
        int commonSecondReferee = rule.getCommonSecondReferee().intValue();
        
        int commonThirdRank = rule.getCommonThirdRank().intValue();
        int commonThirdReferee = rule.getCommonThirdReferee().intValue();*/
        
        
        int noshareFirstRank = rule.getNoshareFirstRank().intValue();
        int noshareSecondRank = rule.getNoshareSecondRank().intValue();
        int noshareThirdRank = rule.getNoshareThirdRank().intValue();
        
        //领导奖励判断
        if(refereeCount >= leaderReferee && totalReferrCount >= leaderUnderNumber) {
        	isLeader = true;
        }
        
        if(isLeader) {
        	if(saleForm.getLeaderFirst().equals("N")) {
        		if(rank.intValue() >= leaderFirstRank) {
        			content = "已达成 领导定拨奖励-"+rule.getRuleName()+"-"+rule.getLeaderFirstRank()+"人 "+rule.getLeaderFirstRebate()+"元";;
        			findDingboCondition = "Y";
        			saleForm.setLeaderFirst("Y");
                    saleForm.setLeaderSecond("N");
                    saleForm.setLeaderThird("N");
                    saleForm.setCommonFirst("N");
                    saleForm.setCommonSecond("N");
                    saleForm.setCommonThird("N");
                    saleForm.setNoshareFirst("N");
                    saleForm.setNoshareSecond("N");
                    saleForm.setNoshareThird("N");
        		}
        	}
        	
        	if(saleForm.getLeaderSecond().equals("N") && !"Y".equals(findDingboCondition)) {
        		if(rank.intValue() >= leaderSecondRank) {
        			content = "已达成 领导定拨奖励-"+rule.getRuleName()+"-"+rule.getLeaderSecondRank()+"人 "+rule.getLeaderSecondRebate()+"元";;
        			findDingboCondition = "Y";
        			saleForm.setLeaderSecond("Y");
        			saleForm.setLeaderFirst("N");
                    saleForm.setLeaderThird("N");
                    saleForm.setCommonFirst("N");
                    saleForm.setCommonSecond("N");
                    saleForm.setCommonThird("N");
                    saleForm.setNoshareFirst("N");
                    saleForm.setNoshareSecond("N");
                    saleForm.setNoshareThird("N");
        		}
        	}
        	
        	if(saleForm.getLeaderThird().equals("N")  && !"Y".equals(findDingboCondition)) {
        		if(rank.intValue() >= leaderThirdRank) {
        			content = "已达成 领导定拨奖励-"+rule.getRuleName()+"-"+rule.getLeaderThirdRank()+"人 "+rule.getLeaderThirdRebate()+"元";;
        			findDingboCondition = "Y";
        			saleForm.setLeaderThird("Y");
        			saleForm.setLeaderFirst("N");
                    saleForm.setLeaderSecond("N");
                    saleForm.setCommonFirst("N");
                    saleForm.setCommonSecond("N");
                    saleForm.setCommonThird("N");
                    saleForm.setNoshareFirst("N");
                    saleForm.setNoshareSecond("N");
                    saleForm.setNoshareThird("N");
        		}
        	}
        }
        
        //普通分享奖励判断
        
        /*if(saleForm.getCommonFirst().equals("N")  && !"Y".equals(findDingboCondition) && saleForm.getNoshareFirst().equals("N")) {
        	if(rank.intValue() >= commonFirstRank && refereeCount >= commonFirstReferee) {
        		content = "已达成 普通分享定拨奖励-"+rule.getRuleName()+"-"+rule.getCommonFirstRank()+"人 "+rule.getCommonFirstRebate()+"元";;
        		findDingboCondition = "Y";
        		saleForm.setCommonFirst("Y");
        		saleForm.setLeaderFirst("N");
                saleForm.setLeaderSecond("N");
                saleForm.setLeaderThird("N");
                saleForm.setCommonSecond("N");
                saleForm.setCommonThird("N");
                saleForm.setNoshareFirst("N");
                saleForm.setNoshareSecond("N");
                saleForm.setNoshareThird("N");
        	}
        }
        
        if(saleForm.getCommonSecond().equals("N")  && !"Y".equals(findDingboCondition)  && saleForm.getNoshareSecond().equals("N")) {
        	if(rank.intValue() >= commonSecondRank && refereeCount >= commonSecondReferee) {
        		content = "已达成 普通分享定拨奖励-"+rule.getRuleName()+"-"+rule.getCommonSecondRank()+"人 "+rule.getCommonSecondRebate()+"元";;
        		findDingboCondition = "Y";
        		saleForm.setCommonSecond("Y");
        		saleForm.setLeaderFirst("N");
                saleForm.setLeaderSecond("N");
                saleForm.setLeaderThird("N");
                saleForm.setCommonFirst("N");
                saleForm.setCommonThird("N");
                saleForm.setNoshareFirst("N");
                saleForm.setNoshareSecond("N");
                saleForm.setNoshareThird("N");
        	}
        }
        
        if(saleForm.getCommonThird().equals("N")  && !"Y".equals(findDingboCondition)  && saleForm.getNoshareThird().equals("N")) {
        	if(rank.intValue() >= commonThirdRank && refereeCount >= commonThirdReferee) {
        		content = "已达成 普通分享定拨奖励-"+rule.getRuleName()+"-"+rule.getCommonThirdRank()+"人 "+rule.getCommonThirdRebate()+"元";;
        		findDingboCondition = "Y";
        		saleForm.setCommonThird("Y");
        		saleForm.setLeaderFirst("N");
                saleForm.setLeaderSecond("N");
                saleForm.setLeaderThird("N");
                saleForm.setCommonFirst("N");
                saleForm.setCommonSecond("N");
                saleForm.setNoshareFirst("N");
                saleForm.setNoshareSecond("N");
                saleForm.setNoshareThird("N");
        	}
        }*/
        
        
        //判断无分享
//        if(refereeCount == 0) {
        	if(saleForm.getNoshareFirst().equals("N") && saleForm.getCommonFirst().equals("N") && rank.intValue() >= noshareFirstRank  && !"Y".equals(findDingboCondition)) {
        		content = "已达成 无分享定拨奖励-"+rule.getRuleName()+"-"+rule.getNoshareFirstRank()+"人 "+rule.getNoshareFirstRebate()+"元";
        		findDingboCondition = "Y";
        		saleForm.setNoshareFirst("Y");
        		saleForm.setLeaderFirst("N");
                saleForm.setLeaderSecond("N");
                saleForm.setLeaderThird("N");
                saleForm.setCommonFirst("N");
                saleForm.setCommonSecond("N");
                saleForm.setCommonThird("N");
                saleForm.setNoshareSecond("N");
                saleForm.setNoshareThird("N");
        	}
        	if(saleForm.getNoshareSecond().equals("N")  && saleForm.getCommonSecond().equals("N") && rank.intValue() >= noshareSecondRank  && !"Y".equals(findDingboCondition)) {
        		content = "已达成 无分享定拨奖励-"+rule.getRuleName()+"-"+rule.getNoshareSecondRank()+"人 "+rule.getNoshareSecondRebate()+"元";;
        		findDingboCondition = "Y";
        		saleForm.setNoshareSecond("Y");
        		saleForm.setLeaderFirst("N");
                saleForm.setLeaderSecond("N");
                saleForm.setLeaderThird("N");
                saleForm.setCommonFirst("N");
                saleForm.setCommonSecond("N");
                saleForm.setCommonThird("N");
                saleForm.setNoshareFirst("N");
                saleForm.setNoshareThird("N");
        	}
        	if(saleForm.getNoshareThird().equals("N") && saleForm.getCommonThird().equals("N") && rank.intValue() >= noshareThirdRank  && !"Y".equals(findDingboCondition)) {
        		content = "已达成 无分享定拨奖励-"+rule.getRuleName()+"-"+rule.getNoshareThirdRank()+"人 "+rule.getNoshareThirdRebate()+"元";;
        		findDingboCondition = "Y";
        		saleForm.setNoshareThird("Y");
        		saleForm.setLeaderFirst("N");
                saleForm.setLeaderSecond("N");
                saleForm.setLeaderThird("N");
                saleForm.setCommonFirst("N");
                saleForm.setCommonSecond("N");
                saleForm.setCommonThird("N");
                saleForm.setNoshareFirst("N");
                saleForm.setNoshareSecond("N");
        	}
//        }
        
        
    	return success(content);
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
