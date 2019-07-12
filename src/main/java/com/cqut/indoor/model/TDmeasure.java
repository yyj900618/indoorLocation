package com.cqut.indoor.model;

import java.math.BigDecimal;

public class TDmeasure {
    private String rpid;

    private String apid;

    private BigDecimal rrsi;

    public String getRpid() {
        return rpid;
    }

    public void setRpid(String rpid) {
        this.rpid = rpid == null ? null : rpid.trim();
    }

    public String getApid() {
        return apid;
    }

    public void setApid(String apid) {
        this.apid = apid == null ? null : apid.trim();
    }

    public BigDecimal getRrsi() {
        return rrsi;
    }

    public void setRrsi(BigDecimal rrsi) {
        this.rrsi = rrsi;
    }
}