package xyz.nxlexiaoyao.sales.controller.mobileApp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import xyz.nxlexiaoyao.sales.bean.BaseBean;
import xyz.nxlexiaoyao.sales.bean.Income;
import xyz.nxlexiaoyao.sales.bean.Member;
import xyz.nxlexiaoyao.sales.bean.SaleForm;
import xyz.nxlexiaoyao.sales.bean.Token;
import xyz.nxlexiaoyao.sales.common.MobileSession;
import xyz.nxlexiaoyao.sales.dao.IncomeDao;
import xyz.nxlexiaoyao.sales.dao.MemberDao;
import xyz.nxlexiaoyao.sales.dao.SaleFormDao;
import xyz.nxlexiaoyao.sales.service.SaleFormService;
import xyz.nxlexiaoyao.sales.util.PasswordUtil;
import xyz.nxlexiaoyao.sales.util.RandomStrUtil;
import xyz.nxlexiaoyao.sales.vo.PasswordVo;
import xyz.nxlexiaoyao.sales.vo.TeamVo;

import javax.ws.rs.GET;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/member")
public class MemberRest extends BaseRest {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private SaleFormDao saleFormDao;
    @Autowired
    private SaleFormService saleFormService;
    @Autowired
    private IncomeDao incomeDao;

    @PostMapping("/login.do")
    @ResponseStatus(HttpStatus.OK)
    public BaseBean<Map<String,String>> login(@RequestBody Member member){
        if(!StringUtils.isEmpty(member.getAccount()) && !StringUtils.isEmpty(member.getPassword())){
            Member mudule = memberDao.selectByAccount(member.getAccount());
            if(mudule!=null){
                String p = mudule.getPassword();
                String salt = mudule.getSalt();
                String password = member.getPassword();
                String realPassword = PasswordUtil.getMD5Password(password,salt);
                if(p.equals(realPassword)){
                    Token token = new Token();
                    token.setTimestamp(System.currentTimeMillis());
                    token.setToken(Base64.getEncoder().encodeToString(RandomStrUtil.getRandomStr(32).getBytes(Charset.forName("UTF-8"))));
                    token.setUserId(mudule.getId());

                    MobileSession.saveToken(token);
                    Map<String,String> res = new HashMap<String,String>();
                    res.put("userId",mudule.getId());
                    res.put("authorization",token.getToken());
                    

                    //隐藏银行卡和身份证号部分信息
                    return super.success(res);
                }
            }
        }
        return super.fail("登录失败");
    }



    @PostMapping("/modifyPwd.do")
    @ResponseStatus(HttpStatus.OK)
    public BaseBean<String> modifyPwd(@RequestBody PasswordVo passwordVo){
        String oldPwd = passwordVo.getOldPassword();
        String newPwd = passwordVo.getNewPassword();
        String userId = super.getUserId();
        Member member = memberDao.selectById(userId);
        String dataPwd = member.getPassword();
        String salt = member.getSalt();

        String oldPwdMd5 = PasswordUtil.getMD5Password(oldPwd,salt);
        if(oldPwdMd5.equals(dataPwd)){
            String newSalt = RandomStrUtil.getRandomStr(8);
            String newPwdMd5 = PasswordUtil.getMD5Password(newPwd,newSalt);
            member.setSalt(newSalt);
            member.setPassword(newPwdMd5);
            memberDao.updateFull(member);
            return success(null,"密码修改成功");
        }else {
            return fail("密码不正确");
        }
    }


    @GetMapping("/personalInfo.do")
    @ResponseStatus(HttpStatus.OK)
    public BaseBean<Member> myInfo(){
        String userId = super.getUserId();
        Member member = memberDao.selectById(userId);
        return success(member);
    }

    @GetMapping("/team.do")
    @ResponseStatus(HttpStatus.OK)
    public BaseBean<TeamVo> teamInfo(@RequestParam(value="memberId",required=false) String memberId,@RequestParam("ruleId") String ruleId){
    	TeamVo teamVo = new TeamVo();
        String userId = super.getUserId();
        SaleForm parent = null;
        if(memberId != null && !"".equals(memberId)) {
        	parent = saleFormDao.selectById(memberId);
        }else {
        	parent = saleFormDao.selectLastByRuleIdAndMemberId(ruleId, userId);
        }
        
        
        if(parent == null) {
        	return success(null);
        }
        
        List<SaleForm> saleFormChildren = saleFormDao.selectByRefereeIdPlus(parent.getName(),ruleId);
        
        
        List<TeamVo> children = new ArrayList<TeamVo>();
        saleFormChildren.forEach(child ->{
        	TeamVo tv = new TeamVo();
        	String childMemberId = child.getId();
        	String childName = child.getName();
        	tv.setMemberId(childMemberId);
        	tv.setMemberName(childName);
        	tv.setRefereeCount(child.getTotalRefereCount());
        	children.add(tv);
        });
        
        teamVo.setRefereeCount(parent.getTotalRefereCount());
        teamVo.setMemberId(parent.getId());
        teamVo.setMemberName(parent.getName());
        teamVo.setRefereeName(parent.getRefereeId());
        teamVo.setChildren(children);
        
        
      
        return success(teamVo);
    }
    
    
    

    @GetMapping("/association.do")
    @ResponseStatus(HttpStatus.OK)
    public BaseBean<List<SaleForm>> associationMember(@RequestParam("keyword") String keyword,@RequestParam("ruleId") String ruleId){
        return success(memberDao.selectLikeName(keyword,ruleId));
    }
    
    @GetMapping("/incomes.do")
    @ResponseStatus(HttpStatus.OK)
    public BaseBean<List<Income>> incomes(){
    	String memberId = getUserId();
    	List<Income> incomes = incomeDao.incomes(memberId);
    	if(incomes == null) {
    		incomes = new ArrayList<>();
    	}
    	return success(incomes);
    }
}


