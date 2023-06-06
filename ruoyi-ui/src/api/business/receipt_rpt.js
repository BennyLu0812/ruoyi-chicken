import request from '@/utils/request'

// 查询客户列表
export function listCustomerReceipt(query) {
  return request({
    url: '/business/receipt/customerReceiptList',
    method: 'get',
    params: query
  })
}
