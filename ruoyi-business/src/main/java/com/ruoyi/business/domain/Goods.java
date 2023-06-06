package com.ruoyi.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 品种对象 ye_goods
 * 
 * @author lwy
 * @date 2023-06-01
 */
public class Goods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 品种id */
    private Long goodsId;

    /** 品种名称 */
    @Excel(name = "品种名称")
    private String goodsName;

    /** 备注 */
    @Excel(name = "备注")
    private String goodsRemark;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setGoodsRemark(String goodsRemark) 
    {
        this.goodsRemark = goodsRemark;
    }

    public String getGoodsRemark() 
    {
        return goodsRemark;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsId", getGoodsId())
            .append("goodsName", getGoodsName())
            .append("goodsRemark", getGoodsRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
