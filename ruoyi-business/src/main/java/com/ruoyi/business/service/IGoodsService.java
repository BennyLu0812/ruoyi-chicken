package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.Goods;

/**
 * 品种Service接口
 * 
 * @author lwy
 * @date 2023-06-01
 */
public interface IGoodsService 
{
    /**
     * 查询品种
     * 
     * @param goodsId 品种主键
     * @return 品种
     */
    public Goods selectGoodsByGoodsId(Long goodsId);

    /**
     * 查询品种列表
     * 
     * @param goods 品种
     * @return 品种集合
     */
    public List<Goods> selectGoodsList(Goods goods);

    /**
     * 新增品种
     * 
     * @param goods 品种
     * @return 结果
     */
    public int insertGoods(Goods goods);

    /**
     * 修改品种
     * 
     * @param goods 品种
     * @return 结果
     */
    public int updateGoods(Goods goods);

    /**
     * 批量删除品种
     * 
     * @param goodsIds 需要删除的品种主键集合
     * @return 结果
     */
    public int deleteGoodsByGoodsIds(Long[] goodsIds);

    /**
     * 删除品种信息
     * 
     * @param goodsId 品种主键
     * @return 结果
     */
    public int deleteGoodsByGoodsId(Long goodsId);
}
