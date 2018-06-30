package xyz.nxlexiaoyao.sales.vo;

public class LineMenuVo {
    private String ruleId;
    private String ruleName;
    private Integer totalForm;
    private Integer totalAmount;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getTotalForm() {
        return totalForm;
    }

    public void setTotalForm(Integer totalForm) {
        this.totalForm = totalForm;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
}
