import request from '@/utils/request'

// 查询单据头列表
export function listReceipt(query) {
  return request({
    url: '/business/receipt/list',
    method: 'get',
    params: query
  })
}

// 查询单据头详细
export function getReceipt(receiptId) {
  return request({
    url: '/business/receipt/' + receiptId,
    method: 'get'
  })
}

// 新增单据头
export function addReceipt(data) {
  return request({
    url: '/business/receipt',
    method: 'post',
    data: data
  })
}

// 修改单据头
export function updateReceipt(data) {
  return request({
    url: '/business/receipt',
    method: 'put',
    data: data
  })
}

// 删除单据头
export function delReceipt(receiptId) {
  return request({
    url: '/business/receipt/' + receiptId,
    method: 'delete'
  })
}
