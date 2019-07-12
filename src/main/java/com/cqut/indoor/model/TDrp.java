package com.cqut.indoor.model;

import java.math.BigDecimal;

public class TDrp {
    private String rpid;

    private BigDecimal x;

    private BigDecimal y;

    public String getRpid() {
        return rpid;
    }

    public void setRpid(String rpid) {
        this.rpid = rpid == null ? null : rpid.trim();
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }
}