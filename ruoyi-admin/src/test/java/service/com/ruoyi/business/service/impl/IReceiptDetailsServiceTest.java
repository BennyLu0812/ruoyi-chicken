package service.com.ruoyi.business.service.impl;

import com.ruoyi.RuoYiApplication;
import com.ruoyi.business.domain.ReceiptDetails;
import com.ruoyi.business.service.IReceiptDetailsService;
import com.ruoyi.business.service.impl.ReceiptServiceImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Benny
 * @description:
 * @date 2023/6/6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RuoYiApplication.class)
@Transactional
public class IReceiptDetailsServiceTest {

    @Autowired
    private IReceiptDetailsService receiptDetailsService;

    @Test
    public void testSelectReceiptDetailsList() {
        ReceiptDetails receiptDetails = new ReceiptDetails();
        receiptDetails.setCustomerId(1L);

        List<ReceiptDetails> list =  receiptDetailsService.selectReceiptDetailsList(receiptDetails);
        Assert.assertTrue(org.apache.commons.collections.CollectionUtils.isNotEmpty(list));
    }



}
