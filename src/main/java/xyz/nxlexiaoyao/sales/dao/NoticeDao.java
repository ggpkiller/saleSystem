package xyz.nxlexiaoyao.sales.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import xyz.nxlexiaoyao.sales.bean.Notice;

@Repository
public interface NoticeDao {
	public void save(Notice notice);
	public List<Notice> selectAll();
	public List<Notice> selectByPage(@Param("limit") Integer limit,@Param("offset") Integer offset);
	
	public Notice selectById(@Param("noticeId") String noticeId);
	
	public int delById(@Param("noticeId") String noticeId);
}
