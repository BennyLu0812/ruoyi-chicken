package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.ReceiptDetails;

/**
 * 单据明细Mapper接口
 * 
 * @author lwy
 * @date 2023-06-05
 */
public interface ReceiptDetailsMapper 
{
    /**
     * 查询单据明细
     * 
     * @param receiptDetailsId 单据明细主键
     * @return 单据明细
     */
    public ReceiptDetails selectReceiptDetailsByReceiptDetailsId(Long receiptDetailsId);

    /**
     * 查询单据明细列表
     * 
     * @param receiptDetails 单据明细
     * @return 单据明细集合
     */
    public List<ReceiptDetails> selectReceiptDetailsList(ReceiptDetails receiptDetails);

    /**
     * 新增单据明细
     * 
     * @param receiptDetails 单据明细
     * @return 结果
     */
    public int insertReceiptDetails(ReceiptDetails receiptDetails);

    /**
     * 修改单据明细
     * 
     * @param receiptDetails 单据明细
     * @return 结果
     */
    public int updateReceiptDetails(ReceiptDetails receiptDetails);

    /**
     * 删除单据明细
     * 
     * @param receiptDetailsId 单据明细主键
     * @return 结果
     */
    public int deleteReceiptDetailsByReceiptDetailsId(Long receiptDetailsId);

    /**
     * 批量删除单据明细
     * 
     * @param receiptDetailsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReceiptDetailsByReceiptDetailsIds(Long[] receiptDetailsIds);

    /**
     * 查询单据報表
     *
     * @param receiptDetails 单据明细
     * @return 单据報表集合
     */
    public List<ReceiptDetails> selectCustomerReceiptList(ReceiptDetails receiptDetails);
}
