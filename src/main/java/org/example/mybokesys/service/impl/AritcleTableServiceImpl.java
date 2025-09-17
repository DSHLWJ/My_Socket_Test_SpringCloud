package org.example.mybokesys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.core.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.example.mybokesys.domain.AritcleContentTable;
import org.example.mybokesys.mapper.AritcleTableMapper;
import org.example.mybokesys.domain.AritcleTable;
import org.example.mybokesys.service.IAritcleTableService;

/**
 * 文章格Service业务层处理
 * 
 * @author lwj
 * @date 2025-09-14
 */
@Service
public class AritcleTableServiceImpl implements IAritcleTableService 
{
    @Autowired
    private AritcleTableMapper aritcleTableMapper;

    /**
     * 查询文章格
     * 
     * @param articleId 文章格主键
     * @return 文章格
     */
    @Override
    public AritcleTable selectAritcleTableByArticleId(String articleId)
    {
        List<AritcleContentTable> aritcleContentTableList =  aritcleTableMapper.selectAritcleContentTableList(articleId);
        AritcleTable aritcleTable = aritcleTableMapper.selectAritcleTableByArticleId(articleId);

        if(aritcleContentTableList.isEmpty()){
            aritcleTable.setAritcleContentTableList(new ArrayList<>());
        }else{
            aritcleTable.setAritcleContentTableList(aritcleContentTableList);
        }
        return aritcleTable;
    }

    /**
     * 查询文章格列表
     * 
     * @param aritcleTable 文章格
     * @return 文章格
     */
    @Override
    public List<AritcleTable> selectAritcleTableList(AritcleTable aritcleTable)
    {
        return aritcleTableMapper.selectAritcleTableList(aritcleTable);
    }

    /**
     * 新增文章格
     * 
     * @param aritcleTable 文章格
     * @return 结果
     */
    @Transactional
    @Override
    public int insertAritcleTable(AritcleTable aritcleTable)
    {
        int rows = aritcleTableMapper.insertAritcleTable(aritcleTable);
        insertAritcleContentTable(aritcleTable);
        return rows;
    }

    /**
     * 修改文章格
     * 
     * @param aritcleTable 文章格
     * @return 结果
     */
    @Transactional
    @Override
    public int updateAritcleTable(AritcleTable aritcleTable)
    {
        aritcleTableMapper.deleteAritcleContentTableByArticleId(aritcleTable.getArticleId());
        insertAritcleContentTable(aritcleTable);
        return aritcleTableMapper.updateAritcleTable(aritcleTable);
    }

    /**
     * 批量删除文章格
     * 
     * @param articleIds 需要删除的文章格主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAritcleTableByArticleIds(String[] articleIds)
    {
        aritcleTableMapper.deleteAritcleContentTableByArticleIds(articleIds);
        return aritcleTableMapper.deleteAritcleTableByArticleIds(articleIds);
    }

    /**
     * 删除文章格信息
     * 
     * @param articleId 文章格主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAritcleTableByArticleId(String articleId)
    {
        aritcleTableMapper.deleteAritcleContentTableByArticleId(articleId);
        return aritcleTableMapper.deleteAritcleTableByArticleId(articleId);
    }

    /**
     * 新增文章内容分片信息
     * 
     * @param aritcleTable 文章格对象
     */
    public void insertAritcleContentTable(AritcleTable aritcleTable)
    {
        List<AritcleContentTable> aritcleContentTableList = aritcleTable.getAritcleContentTableList();
        String articleId = aritcleTable.getArticleId();
        if (StringUtils.isNotNull(aritcleContentTableList))
        {
            List<AritcleContentTable> list = new ArrayList<AritcleContentTable>();
            for (AritcleContentTable aritcleContentTable : aritcleContentTableList)
            {
                aritcleContentTable.setArticleId(articleId);
                list.add(aritcleContentTable);
            }
            if (list.size() > 0)
            {
                aritcleTableMapper.batchAritcleContentTable(list);
            }
        }
    }
}
