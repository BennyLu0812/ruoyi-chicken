import request from '@/utils/request'

// 查询品种列表
export function listGoods(query) {
  return request({
    url: '/business/goods/list',
    method: 'get',
    params: query
  })
}

// 查询品种详细
export function getGoods(goodsId) {
  return request({
    url: '/business/goods/' + goodsId,
    method: 'get'
  })
}

// 新增品种
export function addGoods(data) {
  return request({
    url: '/business/goods',
    method: 'post',
    data: data
  })
}

// 修改品种
export function updateGoods(data) {
  return request({
    url: '/business/goods',
    method: 'put',
    data: data
  })
}

// 删除品种
export function delGoods(goodsId) {
  return request({
    url: '/business/goods/' + goodsId,
    method: 'delete'
  })
}

// 获取品种选择框列表
export function optionselect() {
  return request({
    url: '/business/goods/optionselect',
    method: 'get'
  })
}
