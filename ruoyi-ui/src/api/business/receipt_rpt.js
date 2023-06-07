import request from '@/utils/request'

// 查询客户列表
export function customerReceiptList(query) {
  return request({
    url: '/business/receipt/customerReceiptList',
    method: 'get',
    params: query
  })
}

export function customerReceiptDetailList(query) {
  return request({
    url: '/business/receipt/customerReceiptDetailList',
    method: 'get',
    params: query
  })
}
