package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.Receipt;

/**
 * 单据Service接口
 * 
 * @author lwy
 * @date 2023-06-05
 */
public interface IReceiptService 
{
    /**
     * 查询单据
     * 
     * @param receiptId 单据主键
     * @return 单据
     */
    public Receipt selectReceiptByReceiptId(Long receiptId);

    /**
     * 查询单据列表
     * 
     * @param receipt 单据
     * @return 单据集合
     */
    public List<Receipt> selectReceiptList(Receipt receipt);

    /**
     * 新增单据
     * 
     * @param receipt 单据
     * @return 结果
     */
    public int insertReceipt(Receipt receipt);

    /**
     * 修改单据
     * 
     * @param receipt 单据
     * @return 结果
     */
    public int updateReceipt(Receipt receipt);

    /**
     * 批量删除单据
     * 
     * @param receiptIds 需要删除的单据主键集合
     * @return 结果
     */
    public int deleteReceiptByReceiptIds(Long[] receiptIds);

    /**
     * 删除单据信息
     * 
     * @param receiptId 单据主键
     * @return 结果
     */
    public int deleteReceiptByReceiptId(Long receiptId);
}
