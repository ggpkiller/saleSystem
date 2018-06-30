package xyz.nxlexiaoyao.sales.bean;

import java.util.Date;

public class Manager {
    private String id;
    private String account;
    private String password;
    private String salt;
    private String name;
    private Date insertTime;
    private String rights;

    private String[] rightArry;
    
    
    
    
    
    
    
    
    /**
	 * @return the rightArry
	 */
	public String[] getRightArry() {
		return rightArry;
	}

	/**
	 * @param rightArry the rightArry to set
	 */
	public void setRightArry(String[] rightArry) {
		this.rightArry = rightArry;
	}

	/**
	 * @return the rights
	 */
	public String getRights() {
		return rights;
	}

	/**
	 * @param rights the rights to set
	 */
	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
