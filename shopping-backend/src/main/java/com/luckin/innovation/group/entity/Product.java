package com.luckin.innovation.group.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 市场价
     */
    private Double marketPrice;
    /**
     * 商城价
     */
    private Double shopPrice;
    /**
     * 主图
     */
    private String masterImage;
    /**
     * 描述
     */
    private String productDesc;
    /**
     * 是否热门商品
     */
    private Integer isHot;
    /**
     * 二级分类Id
     */
    private Integer secondCategoryid;
    /**
     * 商品创建日期
     */
    private Date createTime;
    /**
     * 评论内容
     */
    private String commentContent;

    @Transient
    private ProductCategory categorySec;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(Double shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getMasterImage() {
        return masterImage;
    }

    public void setMasterImage(String masterImage) {
        this.masterImage = masterImage;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getSecondCategoryid() {
        return secondCategoryid;
    }

    public void setSecondCategoryid(Integer secondCategoryid) {
        this.secondCategoryid = secondCategoryid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public ProductCategory getCategorySec() {
        return categorySec;
    }

    public void setCategorySec(ProductCategory categorySec) {
        this.categorySec = categorySec;
    }
}
