package xyz.nxlexiaoyao.sales.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.nxlexiaoyao.sales.bean.BaseBean;
import xyz.nxlexiaoyao.sales.bean.Member;
import xyz.nxlexiaoyao.sales.bean.SaleForm;
import xyz.nxlexiaoyao.sales.controller.mobileApp.BaseRest;
import xyz.nxlexiaoyao.sales.dao.MemberDao;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class SystemRestController extends BaseRest {
    @Autowired
    private MemberDao memberDao;

    @GetMapping("/associationMember")
    @ResponseStatus(HttpStatus.OK)
    public BaseBean<List<SaleForm>> associationMember(@RequestParam("keyword") String keyword,
    		@RequestParam("ruleId")String ruleId){
        return success(memberDao.selectLikeName(keyword,ruleId));
    }
}
