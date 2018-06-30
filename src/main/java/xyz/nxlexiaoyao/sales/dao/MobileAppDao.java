package xyz.nxlexiaoyao.sales.dao;

import org.springframework.stereotype.Repository;
import xyz.nxlexiaoyao.sales.bean.MobileApp;

@Repository
public interface MobileAppDao {
    public void save(MobileApp mobileApp);

    public MobileApp getLastMobileApp();
    
    public int delAll();
}
