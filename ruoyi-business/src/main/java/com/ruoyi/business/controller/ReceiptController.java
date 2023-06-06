package com.ruoyi.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.business.domain.ReceiptDetails;
import com.ruoyi.business.service.IReceiptDetailsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.business.domain.Receipt;
import com.ruoyi.business.service.IReceiptService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 单据Controller
 * 
 * @author lwy
 * @date 2023-06-05
 */
@RestController
@RequestMapping("/business/receipt")
public class ReceiptController extends BaseController
{
    @Autowired
    private IReceiptService receiptService;

    @Autowired
    private IReceiptDetailsService receiptDetailsService;

    /**
     * 查询单据列表
     */
    @PreAuthorize("@ss.hasPermi('business:receipt:list')")
    @GetMapping("/list")
    public TableDataInfo list(Receipt receipt)
    {
        startPage();
        List<Receipt> list = receiptService.selectReceiptList(receipt);
        return getDataTable(list);
    }

    /**
     * 导出单据列表
     */
    @PreAuthorize("@ss.hasPermi('business:receipt:export')")
    @Log(title = "单据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Receipt receipt)
    {
        List<Receipt> list = receiptService.selectReceiptList(receipt);
        ExcelUtil<Receipt> util = new ExcelUtil<Receipt>(Receipt.class);
        util.exportExcel(response, list, "单据数据");
    }

    /**
     * 获取单据详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:receipt:query')")
    @GetMapping(value = "/{receiptId}")
    public AjaxResult getInfo(@PathVariable("receiptId") Long receiptId)
    {
        return success(receiptService.selectReceiptByReceiptId(receiptId));
    }

    /**
     * 新增单据
     */
    @PreAuthorize("@ss.hasPermi('business:receipt:add')")
    @Log(title = "单据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Receipt receipt)
    {
        return toAjax(receiptService.insertReceipt(receipt));
    }

    /**
     * 修改单据
     */
    @PreAuthorize("@ss.hasPermi('business:receipt:edit')")
    @Log(title = "单据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Receipt receipt)
    {
        return toAjax(receiptService.updateReceipt(receipt));
    }

    /**
     * 删除单据
     */
    @PreAuthorize("@ss.hasPermi('business:receipt:remove')")
    @Log(title = "单据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{receiptIds}")
    public AjaxResult remove(@PathVariable Long[] receiptIds)
    {
        return toAjax(receiptService.deleteReceiptByReceiptIds(receiptIds));
    }

    /**
     * 查询单据明细列表
     */
    @PreAuthorize("@ss.hasPermi('business:receiptDetails:list')")
    @GetMapping("/customerReceiptList")
    public TableDataInfo customerReceiptList(ReceiptDetails receiptDetails) {

        List<ReceiptDetails> list = receiptDetailsService.selectReceiptDetailsList(receiptDetails);
        return getDataTable(list);
    }

    /**
     * 导出单据明细列表
     */
    @PreAuthorize("@ss.hasPermi('business:receiptDetails:export')")
    @Log(title = "单据明细", businessType = BusinessType.EXPORT)
    @PostMapping("/customerReceiptExport")
    public void customerReceiptExport(HttpServletResponse response, ReceiptDetails receiptDetails) {
        List<ReceiptDetails> list = receiptDetailsService.selectReceiptDetailsList(receiptDetails);
        ExcelUtil<ReceiptDetails> util = new ExcelUtil<ReceiptDetails>(ReceiptDetails.class);
        util.exportExcel(response, list, "单据明细数据");
    }

}
