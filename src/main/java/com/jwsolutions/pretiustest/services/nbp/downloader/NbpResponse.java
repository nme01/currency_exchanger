package com.jwsolutions.pretiustest.services.nbp.downloader;

import java.util.Collection;

public class NbpResponse {
    private String table;
    private String no;
    private String effectiveDate;
    private Collection<NbpRate> rates;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Collection<NbpRate> getRates() {
        return rates;
    }

    public void setRates(Collection<NbpRate> rates) {
        this.rates = rates;
    }
}
