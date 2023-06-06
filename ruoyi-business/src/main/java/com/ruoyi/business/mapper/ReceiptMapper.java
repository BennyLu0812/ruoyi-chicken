package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.Receipt;
import com.ruoyi.business.domain.ReceiptDetails;

/**
 * 单据Mapper接口
 * 
 * @author lwy
 * @date 2023-06-05
 */
public interface ReceiptMapper 
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
     * 删除单据
     * 
     * @param receiptId 单据主键
     * @return 结果
     */
    public int deleteReceiptByReceiptId(Long receiptId);

    /**
     * 批量删除单据
     * 
     * @param receiptIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReceiptByReceiptIds(Long[] receiptIds);

    /**
     * 批量删除单据明细
     * 
     * @param receiptIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReceiptDetailsByReceiptIds(Long[] receiptIds);
    
    /**
     * 批量新增单据明细
     * 
     * @param receiptDetailsList 单据明细列表
     * @return 结果
     */
    public int batchReceiptDetails(List<ReceiptDetails> receiptDetailsList);
    

    /**
     * 通过单据主键删除单据明细信息
     * 
     * @param receiptId 单据ID
     * @return 结果
     */
    public int deleteReceiptDetailsByReceiptId(Long receiptId);


}
