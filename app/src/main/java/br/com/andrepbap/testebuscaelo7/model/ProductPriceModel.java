package br.com.andrepbap.testebuscaelo7.model;

import java.io.Serializable;

public class ProductPriceModel implements Serializable {

    private String current;
    private String nonPromotional;
    private String installment;

    public String getCurrent() {
        return current;
    }

    public String getNonPromotional() {
        return nonPromotional;
    }

    public String getInstallment() {
        return installment;
    }
}
