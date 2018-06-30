package xyz.nxlexiaoyao.sales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.nxlexiaoyao.sales.bean.Member;
import xyz.nxlexiaoyao.sales.bean.SaleForm;
import xyz.nxlexiaoyao.sales.dao.MemberDao;

@Service
public class MemberService {
    @Autowired
    private MemberDao memberDao;

    /**
     * 保存报单
     * @param memberId
     * @return
     */
    public Member getMember(String memberId){
        Member member = memberDao.selectById(memberId);


        return member;
    }


}
