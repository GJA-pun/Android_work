package com.example.hegua.androidwork.object.hint_object;

/**
 * Created by hegua on 2018/7/24.
 */

public class GoodsDaoHint {
    private String nameHint = "";
    private String merchantHint = "";
    private String contentHint = "";
    private String sellingPriceHint = "";
    private String distributionPriceHint = "";
    private String typeHint = "";
    private String localPath = "";

    public String getLocalPath() {return localPath;}

    public void setLocalPath(String localPath) {this.localPath = localPath;}

    public String getNameHint() {
        return nameHint;
    }

    public void setNameHint(String nameHint) {
        this.nameHint = nameHint;
    }

    public String getMerchantHint() {
        return merchantHint;
    }

    public void setMerchantHint(String merchantHint) {
        this.merchantHint = merchantHint;
    }

    public String getContentHint() {
        return contentHint;
    }

    public void setContentHint(String content) {
        this.contentHint = content;
    }

    public String getSellingPriceHint() {
        return sellingPriceHint;
    }

    public void setSellingPriceHint(String sellingPriceHint) {
        this.sellingPriceHint = sellingPriceHint;
    }

    public String getDistributionPriceHint() {
        return distributionPriceHint;
    }

    public void setDistributionPriceHint(String distributionPriceHint) {
        this.distributionPriceHint = distributionPriceHint;
    }

    public String getTypeHint() {
        return typeHint;
    }

    public void setTypeHint(String typeHint) {
        this.typeHint = typeHint;
    }
}