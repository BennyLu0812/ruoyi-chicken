package com.ruoyi.business.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.business.domain.ReceiptDetails;
import com.ruoyi.business.mapper.ReceiptMapper;
import com.ruoyi.business.domain.Receipt;
import com.ruoyi.business.service.IReceiptService;

/**
 * 单据Service业务层处理
 * 
 * @author lwy
 * @date 2023-06-05
 */
@Service
public class ReceiptServiceImpl implements IReceiptService 
{
    @Autowired
    private ReceiptMapper receiptMapper;

    /**
     * 查询单据
     * 
     * @param receiptId 单据主键
     * @return 单据
     */
    @Override
    public Receipt selectReceiptByReceiptId(Long receiptId)
    {
        return receiptMapper.selectReceiptByReceiptId(receiptId);
    }

    /**
     * 查询单据列表
     * 
     * @param receipt 单据
     * @return 单据
     */
    @Override
    public List<Receipt> selectReceiptList(Receipt receipt)
    {
        return receiptMapper.selectReceiptList(receipt);
    }

    /**
     * 新增单据
     * 
     * @param receipt 单据
     * @return 结果
     */
    @Transactional
    @Override
    public int insertReceipt(Receipt receipt)
    {
        receipt.setCreateTime(DateUtils.getNowDate());
        int rows = receiptMapper.insertReceipt(receipt);
        insertReceiptDetails(receipt);
        return rows;
    }

    /**
     * 修改单据
     * 
     * @param receipt 单据
     * @return 结果
     */
    @Transactional
    @Override
    public int updateReceipt(Receipt receipt)
    {
        receipt.setUpdateTime(DateUtils.getNowDate());
        receiptMapper.deleteReceiptDetailsByReceiptId(receipt.getReceiptId());
        insertReceiptDetails(receipt);
        return receiptMapper.updateReceipt(receipt);
    }

    /**
     * 批量删除单据
     * 
     * @param receiptIds 需要删除的单据主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteReceiptByReceiptIds(Long[] receiptIds)
    {
        receiptMapper.deleteReceiptDetailsByReceiptIds(receiptIds);
        return receiptMapper.deleteReceiptByReceiptIds(receiptIds);
    }

    /**
     * 删除单据信息
     * 
     * @param receiptId 单据主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteReceiptByReceiptId(Long receiptId)
    {
        receiptMapper.deleteReceiptDetailsByReceiptId(receiptId);
        return receiptMapper.deleteReceiptByReceiptId(receiptId);
    }

    /**
     * 新增单据明细信息
     * 
     * @param receipt 单据对象
     */
    public void insertReceiptDetails(Receipt receipt)
    {
        List<ReceiptDetails> receiptDetailsList = receipt.getReceiptDetailsList();
        Long receiptId = receipt.getReceiptId();
        if (StringUtils.isNotNull(receiptDetailsList))
        {
            List<ReceiptDetails> list = new ArrayList<ReceiptDetails>();
            for (ReceiptDetails receiptDetails : receiptDetailsList)
            {
                receiptDetails.setReceiptId(receiptId);
                list.add(receiptDetails);
            }
            if (list.size() > 0)
            {
                receiptMapper.batchReceiptDetails(list);
            }
        }
    }
}
