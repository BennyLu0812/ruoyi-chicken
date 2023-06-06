package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.ReceiptDetails;

/**
 * 单据明细Service接口
 * 
 * @author lwy
 * @date 2023-06-05
 */
public interface IReceiptDetailsService 
{

    /**
     * 查询单据明细列表
     * 
     * @param receiptDetails 单据明细
     * @return 单据明细集合
     */
    public List<ReceiptDetails> selectReceiptDetailsList(ReceiptDetails receiptDetails);


}
