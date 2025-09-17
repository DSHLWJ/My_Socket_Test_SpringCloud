package org.example.mybokesys.mapper;

import java.util.List;
import org.example.mybokesys.domain.AritcleTable;
import org.example.mybokesys.domain.AritcleContentTable;

/**
 * 文章格Mapper接口
 * 
 * @author lwj
 * @date 2025-09-14
 */
public interface AritcleTableMapper 
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
     * 删除文章格
     * 
     * @param articleId 文章格主键
     * @return 结果
     */
    public int deleteAritcleTableByArticleId(String articleId);

    /**
     * 批量删除文章格
     * 
     * @param articleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAritcleTableByArticleIds(String[] articleIds);

    /**
     * 批量删除文章内容分片
     * 
     * @param articleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAritcleContentTableByArticleIds(String[] articleIds);
    
    /**
     * 批量新增文章内容分片
     * 
     * @param aritcleContentTableList 文章内容分片列表
     * @return 结果
     */
    public int batchAritcleContentTable(List<AritcleContentTable> aritcleContentTableList);
    

    /**
     * 通过文章格主键删除文章内容分片信息
     * 
     * @param articleId 文章格ID
     * @return 结果
     */
    public int deleteAritcleContentTableByArticleId(String articleId);

    /**
     * 通过文章id获取文章内容分片信息
     *
     * @param articleId 文章格ID
     * @return 结果
     */
    List<AritcleContentTable> selectAritcleContentTableList(String articleId);

}
