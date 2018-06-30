package xyz.nxlexiaoyao.sales.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.nxlexiaoyao.sales.bean.SaleRule;
import xyz.nxlexiaoyao.sales.vo.QueueVo;

import java.util.List;

@Repository
public interface SaleRuleDao {
    public void save(SaleRule saleRule);

    public List<SaleRule> selectAll();

    public SaleRule selectById(@Param("id") String id);

    public int del(@Param("id") String id);


    public List<QueueVo> queryByMemberId(@Param("memberId") String memberId);
    
    public int updateAll(SaleRule saleRule);
}
