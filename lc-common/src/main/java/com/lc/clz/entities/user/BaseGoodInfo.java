package com.lc.clz.entities.user;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/5/11 13:00
 */
public class BaseGoodInfo {

    private int bgiId;

    private String bgiName;

    private Double bgiPrice;

    private String bgiUnit;


    public int getBgiId() {
        return bgiId;
    }

    public void setBgiId(int bgiId) {
        this.bgiId = bgiId;
    }

    public String getBgiName() {
        return bgiName;
    }

    public void setBgiName(String bgiName) {
        this.bgiName = bgiName;
    }

    public Double getBgiPrice() {
        return bgiPrice;
    }

    public void setBgiPrice(Double bgiPrice) {
        this.bgiPrice = bgiPrice;
    }

    public String getBgiUnit() {
        return bgiUnit;
    }

    public void setBgiUnit(String bgiUnit) {
        this.bgiUnit = bgiUnit;
    }
}
