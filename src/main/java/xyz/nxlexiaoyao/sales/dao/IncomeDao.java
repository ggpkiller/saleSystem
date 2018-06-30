package xyz.nxlexiaoyao.sales.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import xyz.nxlexiaoyao.sales.bean.Income;
@Repository
public interface IncomeDao {
	public void save(Income income);
	public List<Income> incomes(@Param("memberId") String memberId);
	
	
}
