package com.ruoyi.business.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 单据对象 ye_receipt
 * 
 * @author lwy
 * @date 2023-06-05
 */
public class Receipt extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 单据id */
    private Long receiptId;

    /** 送货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "送货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;

    /** 总笼数 */
    @Excel(name = "总笼数")
    private Long totalCagesNumber;

    /** 总皮重 */
    @Excel(name = "总皮重")
    private Double totalTareWeight;

    /** 总金额 */
    @Excel(name = "总金额")
    private Double totalAmount;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 单据明细信息 */
    private List<ReceiptDetails> receiptDetailsList;

    public void setReceiptId(Long receiptId) 
    {
        this.receiptId = receiptId;
    }

    public Long getReceiptId() 
    {
        return receiptId;
    }
    public void setDeliveryDate(Date deliveryDate) 
    {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate() 
    {
        return deliveryDate;
    }
    public void setTotalCagesNumber(Long totalCagesNumber) 
    {
        this.totalCagesNumber = totalCagesNumber;
    }

    public Long getTotalCagesNumber() 
    {
        return totalCagesNumber;
    }
    public void setTotalTareWeight(Double totalTareWeight) 
    {
        this.totalTareWeight = totalTareWeight;
    }

    public Double getTotalTareWeight() 
    {
        return totalTareWeight;
    }
    public void setTotalAmount(Double totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public Double getTotalAmount() 
    {
        return totalAmount;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public List<ReceiptDetails> getReceiptDetailsList()
    {
        return receiptDetailsList;
    }

    public void setReceiptDetailsList(List<ReceiptDetails> receiptDetailsList)
    {
        this.receiptDetailsList = receiptDetailsList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("receiptId", getReceiptId())
            .append("deliveryDate", getDeliveryDate())
            .append("totalCagesNumber", getTotalCagesNumber())
            .append("totalTareWeight", getTotalTareWeight())
            .append("totalAmount", getTotalAmount())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("receiptDetailsList", getReceiptDetailsList())
            .toString();
    }
}
