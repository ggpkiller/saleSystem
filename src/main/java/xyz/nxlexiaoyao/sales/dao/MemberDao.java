package xyz.nxlexiaoyao.sales.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import xyz.nxlexiaoyao.sales.bean.Member;
import xyz.nxlexiaoyao.sales.bean.SaleForm;
import xyz.nxlexiaoyao.sales.vo.TeamVo;

@Repository
public interface MemberDao {
    public void save(Member member);

    public List<Member> selectByIndex(@Param("limit") Integer limit,@Param("offset") Integer offset);

    public Integer counter();

    public Member selectByAccount(@Param("account") String account);


    public Member selectById(@Param("id") String id);

    public int updateFull(Member member);

    public List<TeamVo> queryTeam(@Param("memberId") String memberId);

    public List<SaleForm> selectLikeName(@Param("name") String name,@Param("ruleId") String ruleId);
}
