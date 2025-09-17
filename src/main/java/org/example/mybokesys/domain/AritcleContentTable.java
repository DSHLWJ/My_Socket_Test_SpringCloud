package org.example.mybokesys.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 文章内容分片对象 aritcle_content_table
 * 
 * @author lwj
 * @date 2025-09-14
 */
public class AritcleContentTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章ID */
    private String articleId;

    /** 文章分片索引，从0开始 */
    @Excel(name = "文章分片索引，从0开始")
    private Long partIndex;

    /** 文章分片内容 */
    @Excel(name = "文章分片内容")
    private String partContent;

    public void setArticleId(String articleId) 
    {
        this.articleId = articleId;
    }

    public String getArticleId() 
    {
        return articleId;
    }
    public void setPartIndex(Long partIndex) 
    {
        this.partIndex = partIndex;
    }

    public Long getPartIndex() 
    {
        return partIndex;
    }
    public void setPartContent(String partContent) 
    {
        this.partContent = partContent;
    }

    public String getPartContent() 
    {
        return partContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("articleId", getArticleId())
            .append("partIndex", getPartIndex())
            .append("partContent", getPartContent())
            .toString();
    }
}
