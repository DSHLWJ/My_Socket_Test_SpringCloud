package org.example.mybokesys.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 文章格对象 aritcle_table
 * 
 * @author lwj
 * @date 2025-09-14
 */
public class AritcleTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章ID */
    private String articleId;

    /** 文章标题 */
    @Excel(name = "文章标题")
    private String userArticleTitile;

    /** 文章摘要 */
    @Excel(name = "文章摘要")
    private String userArticleDesc;

    /** 文章封面链接 */
    @Excel(name = "文章封面链接")
    private String userArticleCover;

    /** 文章发布状态 */
    @Excel(name = "文章发布状态")
    private String userArticleStatus;

    /** 乐观锁 */
    private Long REVISION;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    /** 文章内容分片信息 */
    private List<AritcleContentTable> aritcleContentTableList;

    public void setArticleId(String articleId) 
    {
        this.articleId = articleId;
    }

    public String getArticleId() 
    {
        return articleId;
    }

    public void setUserArticleTitile(String userArticleTitile) 
    {
        this.userArticleTitile = userArticleTitile;
    }

    public String getUserArticleTitile() 
    {
        return userArticleTitile;
    }

    public void setUserArticleDesc(String userArticleDesc) 
    {
        this.userArticleDesc = userArticleDesc;
    }

    public String getUserArticleDesc() 
    {
        return userArticleDesc;
    }

    public void setUserArticleCover(String userArticleCover) 
    {
        this.userArticleCover = userArticleCover;
    }

    public String getUserArticleCover() 
    {
        return userArticleCover;
    }

    public void setUserArticleStatus(String userArticleStatus) 
    {
        this.userArticleStatus = userArticleStatus;
    }

    public String getUserArticleStatus() 
    {
        return userArticleStatus;
    }

    public void setREVISION(Long REVISION) 
    {
        this.REVISION = REVISION;
    }

    public Long getREVISION() 
    {
        return REVISION;
    }

    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }

    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }

    public void setUpdatedBy(String updatedBy) 
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() 
    {
        return updatedBy;
    }

    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }

    public List<AritcleContentTable> getAritcleContentTableList()
    {
        return aritcleContentTableList;
    }

    public void setAritcleContentTableList(List<AritcleContentTable> aritcleContentTableList)
    {
        this.aritcleContentTableList = aritcleContentTableList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("articleId", getArticleId())
            .append("userArticleTitile", getUserArticleTitile())
            .append("userArticleDesc", getUserArticleDesc())
            .append("userArticleCover", getUserArticleCover())
            .append("userArticleStatus", getUserArticleStatus())
            .append("REVISION", getREVISION())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .append("aritcleContentTableList", getAritcleContentTableList())
            .toString();
    }
}
