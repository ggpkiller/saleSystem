package xyz.nxlexiaoyao.sales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.nxlexiaoyao.sales.bean.SaleForm;
import xyz.nxlexiaoyao.sales.dao.SaleFormDao;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SaleFormService {
    @Autowired
    private SaleFormDao saleFormDao;


    /**
     * 计算某个报单的推荐个数
     * @param name
     * @return
     */
    public int countReferee(String name,String ruleId){
    	
        //查找自己推荐的单子
        List<SaleForm> saleForms = saleFormDao.selectByRefereeId(name,ruleId);
        return saleForms.size();
    }
}
