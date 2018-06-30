package xyz.nxlexiaoyao.sales.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.nxlexiaoyao.sales.bean.Member;
import xyz.nxlexiaoyao.sales.bean.SaleForm;
import xyz.nxlexiaoyao.sales.vo.LineMenuVo;
import xyz.nxlexiaoyao.sales.vo.RebateMenuVo;

import java.util.List;

@Repository
public interface SaleFormDao {
    public void save(SaleForm saleForm);

    public List<SaleForm> select(SaleForm saleForm);

    public void verifyForm(SaleForm saleForm);

    public List<SaleForm> selectAll(@Param("ruleId") String ruleId);

    public Integer selectMaxInsertNo();

    public Integer selectMaxCurrentNo();

    public List<LineMenuVo> selectLineMenu();

    public List<SaleForm> selectLineList(SaleForm saleForm);

    public List<SaleForm> selectByRefereeId(@Param("refereeId") String refereeId,@Param("ruleId") String ruleId);


    public List<SaleForm> selectAllTree(@Param("ruleId") String ruleId);

    public SaleForm selectOutForm(@Param("ruleId") String ruleId);
    
    
    public SaleForm selectLastByRuleIdAndMemberId(@Param("ruleId") String ruleId,@Param("memberId") String memberId);
    
    
    public List<SaleForm> selectOnlineAll(@Param("ruleId") String ruleId);
    
    public void updateTotalRefereeCount(@Param("list") List<SaleForm> list);
    
    public void regDingbo(SaleForm saleForm);
    
    public SaleForm selectById(@Param("id") String id);
    
    public void out(SaleForm saleForm);
    
    public List<SaleForm> selectByRefereeIdPlus(@Param("refereeId") String refereeId,@Param("ruleId") String ruleId);
    
    public List<SaleForm> selectByIds(@Param("ids") List<String> ids);
    
    public void delById(@Param("id") String id);
    
    public void updateInsertNoByNo(SaleForm saleForm);
    
    public void increaseRefereeTotal(@Param("list") List<SaleForm> list);
    
    public void increaseRefereeTotalSelf(SaleForm saleForm);
    
    public void decreaseRefereeTotal(@Param("list") List<SaleForm> list);
    
    public List<SaleForm> relationsUp(SaleForm saleForm);
    
    public List<SaleForm> relationsDown(SaleForm saleForm);
}
