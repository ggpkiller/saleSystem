package xyz.nxlexiaoyao.sales.vo;

public class RebateListVo {
    private Integer currentNumber;
    private String name;
    private Integer refereeCount;
    private String condition;

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRefereeCount() {
        return refereeCount;
    }

    public void setRefereeCount(Integer refereeCount) {
        this.refereeCount = refereeCount;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
