package com.ruoyi.business.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.ReceiptDetailsMapper;
import com.ruoyi.business.domain.ReceiptDetails;
import com.ruoyi.business.service.IReceiptDetailsService;

/**
 * 单据明细Service业务层处理
 * 
 * @author lwy
 * @date 2023-06-05
 */
@Service
public class ReceiptDetailsServiceImpl implements IReceiptDetailsService {
    @Autowired
    private ReceiptDetailsMapper receiptDetailsMapper;

    /**
     * 查询单据明细列表
     * 
     * @param receiptDetails 单据明细
     * @return 单据明细
     */
    @Override
    public List<ReceiptDetails> selectReceiptDetailsList(ReceiptDetails receiptDetails)
    {
        return receiptDetailsMapper.selectReceiptDetailsList(receiptDetails);
    }

}
