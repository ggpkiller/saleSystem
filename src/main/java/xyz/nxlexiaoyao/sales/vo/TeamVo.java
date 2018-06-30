package xyz.nxlexiaoyao.sales.vo;

import java.util.List;

public class TeamVo {
    private String memberId;
    private String memberName;
    private Integer refereeCount;
    private String refereeName;
   
    
    private List<TeamVo> children;
    
    
    
    
    
    
    /**
	 * @return the refereeName
	 */
	public String getRefereeName() {
		return refereeName;
	}

	/**
	 * @param refereeName the refereeName to set
	 */
	public void setRefereeName(String refereeName) {
		this.refereeName = refereeName;
	}

	/**
	 * @return the refereeCount
	 */
	public Integer getRefereeCount() {
		return refereeCount;
	}

	/**
	 * @param refereeCount the refereeCount to set
	 */
	public void setRefereeCount(Integer refereeCount) {
		this.refereeCount = refereeCount;
	}

	

	/**
	 * @return the children
	 */
	public List<TeamVo> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<TeamVo> children) {
		this.children = children;
	}

	public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
