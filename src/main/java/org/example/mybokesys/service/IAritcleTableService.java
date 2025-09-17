package org.example.mybokesys.service;

import java.util.List;
import org.example.mybokesys.domain.AritcleTable;

/**
 * 文章格Service接口
 * 
 * @author lwj
 * @date 2025-09-14
 */
public interface IAritcleTableService 
{
    /**
     * 查询文章格
     * 
     * @param articleId 文章格主键
     * @return 文章格
     */
    public AritcleTable selectAritcleTableByArticleId(String articleId);

    /**
     * 查询文章格列表
     * 
     * @param aritcleTable 文章格
     * @return 文章格集合
     */
    public List<AritcleTable> selectAritcleTableList(AritcleTable aritcleTable);

    /**
     * 新增文章格
     * 
     * @param aritcleTable 文章格
     * @return 结果
     */
    public int insertAritcleTable(AritcleTable aritcleTable);

    /**
     * 修改文章格
     * 
     * @param aritcleTable 文章格
     * @return 结果
     */
    public int updateAritcleTable(AritcleTable aritcleTable);

    /**
     * 批量删除文章格
     * 
     * @param articleIds 需要删除的文章格主键集合
     * @return 结果
     */
    public int deleteAritcleTableByArticleIds(String[] articleIds);

    /**
     * 删除文章格信息
     * 
     * @param articleId 文章格主键
     * @return 结果
     */
    public int deleteAritcleTableByArticleId(String articleId);

}
