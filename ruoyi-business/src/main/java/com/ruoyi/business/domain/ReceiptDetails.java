package com.ruoyi.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 单据明细对象 ye_receipt_details
 * 
 * @author lwy
 * @date 2023-06-05
 */
public class ReceiptDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 单据明细id */
    private Long receiptDetailsId;

    /** 单据id */
    private Long receiptId;

    /** 品种 */
    @Excel(name = "品种")
    private String goodsName;
    private Long goodsId;

    /** 客户id */
    @Excel(name = "客户")
    private String customerName;
    private Long customerId;



    /** 毛重 */
    @Excel(name = "毛重")
    private Double grossWeight;

    /** 笼数 */
    @Excel(name = "笼数")
    private Long cagesNumber;

    /** 单价 */
    @Excel(name = "单价")
    private Double price;

    /** 皮重 */
    @Excel(name = "皮重")
    private Double tareWeight;

    /** 磅位 */
    @Excel(name = "磅位")
    private Double poundPosition;

    /** 金额 */
    @Excel(name = "金额")
    private Double amount;

    /** 状态 */
    private String status;

    private String deliveryDateBeginTime;
    private String deliveryDateEndTime;

    /** 送货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "送货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;


    public void setReceiptDetailsId(Long receiptDetailsId) 
    {
        this.receiptDetailsId = receiptDetailsId;
    }

    public Long getReceiptDetailsId() 
    {
        return receiptDetailsId;
    }
    public void setReceiptId(Long receiptId) 
    {
        this.receiptId = receiptId;
    }

    public Long getReceiptId() 
    {
        return receiptId;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setGrossWeight(Double grossWeight) 
    {
        this.grossWeight = grossWeight;
    }

    public Double getGrossWeight() 
    {
        return grossWeight;
    }
    public void setCagesNumber(Long cagesNumber) 
    {
        this.cagesNumber = cagesNumber;
    }

    public Long getCagesNumber() 
    {
        return cagesNumber;
    }
    public void setPrice(Double price) 
    {
        this.price = price;
    }

    public Double getPrice() 
    {
        return price;
    }
    public void setTareWeight(Double tareWeight) 
    {
        this.tareWeight = tareWeight;
    }

    public Double getTareWeight() 
    {
        return tareWeight;
    }
    public void setPoundPosition(Double poundPosition) 
    {
        this.poundPosition = poundPosition;
    }

    public Double getPoundPosition() 
    {
        return poundPosition;
    }
    public void setAmount(Double amount) 
    {
        this.amount = amount;
    }

    public Double getAmount() 
    {
        return amount;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDeliveryDateBeginTime() {
        return deliveryDateBeginTime;
    }

    public void setDeliveryDateBeginTime(String deliveryDateBeginTime) {
        this.deliveryDateBeginTime = deliveryDateBeginTime;
    }

    public String getDeliveryDateEndTime() {
        return deliveryDateEndTime;
    }

    public void setDeliveryDateEndTime(String deliveryDateEndTime) {
        this.deliveryDateEndTime = deliveryDateEndTime;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("receiptDetailsId", getReceiptDetailsId())
            .append("receiptId", getReceiptId())
            .append("goodsId", getGoodsId())
            .append("goodsName", getGoodsName())
            .append("customerId", getCustomerId())
            .append("customerName", getCustomerName())
            .append("grossWeight", getGrossWeight())
            .append("cagesNumber", getCagesNumber())
            .append("price", getPrice())
            .append("tareWeight", getTareWeight())
            .append("poundPosition", getPoundPosition())
            .append("amount", getAmount())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deliveryDateBeginTime", getDeliveryDateBeginTime())
            .append("deliveryDateEndTime", getDeliveryDateEndTime())
            .append("deliveryDate", getDeliveryDate())
            .toString();
    }
}
