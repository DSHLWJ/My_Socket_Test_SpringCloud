package org.example.mybokesys.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import org.example.mybokesys.domain.AritcleTable;
import org.example.mybokesys.service.IAritcleTableService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 文章格Controller
 * 
 * @author lwj
 * @date 2025-09-14
 */
@RestController
@RequestMapping("/table")
public class AritcleTableController extends BaseController
{
    @Autowired
    private IAritcleTableService aritcleTableService;

    /**
     * 查询文章格列表
     */
    @RequiresPermissions("mybokesys:table:list")
    @GetMapping("/list")
    public TableDataInfo list(AritcleTable aritcleTable)
    {
        startPage();
        List<AritcleTable> list = aritcleTableService.selectAritcleTableList(aritcleTable);
        return getDataTable(list);
    }

    /**
     * 导出文章格列表
     */
    @RequiresPermissions("mybokesys:table:export")
    @Log(title = "文章格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AritcleTable aritcleTable)
    {
        List<AritcleTable> list = aritcleTableService.selectAritcleTableList(aritcleTable);
        ExcelUtil<AritcleTable> util = new ExcelUtil<AritcleTable>(AritcleTable.class);
        util.exportExcel(response, list, "文章格数据");
    }

    /**
     * 获取文章格详细信息
     */
    @RequiresPermissions("mybokesys:table:query")
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(@PathVariable("articleId") String articleId)
    {
        return success(aritcleTableService.selectAritcleTableByArticleId(articleId));
    }

    /**
     * 新增文章格
     */
    @RequiresPermissions("mybokesys:table:add")
    @Log(title = "文章格", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AritcleTable aritcleTable)
    {
        return toAjax(aritcleTableService.insertAritcleTable(aritcleTable));
    }

    /**
     * 修改文章格
     */
    @RequiresPermissions("mybokesys:table:edit")
    @Log(title = "文章格", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AritcleTable aritcleTable)
    {
        return toAjax(aritcleTableService.updateAritcleTable(aritcleTable));
    }

    /**
     * 删除文章格
     */
    @RequiresPermissions("mybokesys:table:remove")
    @Log(title = "文章格", businessType = BusinessType.DELETE)
	@DeleteMapping("/{articleIds}")
    public AjaxResult remove(@PathVariable String[] articleIds)
    {
        return toAjax(aritcleTableService.deleteAritcleTableByArticleIds(articleIds));
    }
}
