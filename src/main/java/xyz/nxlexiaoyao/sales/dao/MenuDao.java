package xyz.nxlexiaoyao.sales.dao;

import org.springframework.stereotype.Repository;
import xyz.nxlexiaoyao.sales.bean.Menu;

import java.util.List;

@Repository
public interface MenuDao {
    public List<Menu> selectAll();
}
