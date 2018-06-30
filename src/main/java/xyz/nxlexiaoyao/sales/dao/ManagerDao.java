package xyz.nxlexiaoyao.sales.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.nxlexiaoyao.sales.bean.Manager;

@Repository
public interface ManagerDao {
    public void save(Manager manager);
    public List<Manager> selectAll();
    
    public int delById(@Param("managerId") String managerId);
    
    
    public Manager selectByAccount(@Param("account") String account);
}
