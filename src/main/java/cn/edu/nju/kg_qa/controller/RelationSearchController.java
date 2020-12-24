package cn.edu.nju.kg_qa.controller;

import cn.edu.nju.kg_qa.domain.base.BaseRelation;
import cn.edu.nju.kg_qa.domain.relation.BelongRelation;
import cn.edu.nju.kg_qa.domain.relation.PublishRelation;
import cn.edu.nju.kg_qa.domain.relation.WriteRelation;
import cn.edu.nju.kg_qa.service.BelongToService;
import cn.edu.nju.kg_qa.service.PublishService;
import cn.edu.nju.kg_qa.service.WriteService;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description: <br/>
 * date: 2020/12/24 17:54<br/>
 *
 * @author HaoNanWang<br />
 * @since JDK 11
 */
@Api(tags = "关系查询接口")
@RestController
@RequestMapping("/relationSearch")
public class RelationSearchController {
    @Autowired
    BelongToService belongToService;

    @Autowired
    PublishService publishService;

    @Autowired
    WriteService writeService;

    @ApiOperation("根据概念名 查询belongTo概念关系")
    @GetMapping("/findBelongToRelationByConceptName/{conceptName}")
    String findBelongToRelationByConceptName(@PathVariable String conceptName) {
        List<BelongRelation> list = belongToService.findBelongToRelationByConceptName(conceptName);
        return JSON.toJSONString(list);
    }

    @ApiOperation("根据书籍名 查询belongTo概念关系")
    @GetMapping("/findBelongToRelationByBookName/{bookName}")
    String findBelongToRelationByBookName(@PathVariable String bookName) {
        List<BelongRelation> list = belongToService.findBelongToRelationByBookName(bookName);
        return JSON.toJSONString(list);
    }


    @ApiOperation("根据机构名查询publish关系路径节点")
    @GetMapping("/findPublishRelationByAuthorName/{instituteName}")
    public String findPublishRelationByAuthorName(@PathVariable String instituteName) {
        List<PublishRelation> list = publishService.findPublishRelationByInstituteName(instituteName);
        return JSON.toJSONString(list);
    }


    @ApiOperation("根据书籍名查询publish关系路径节点")
    @GetMapping("/findPublishRelationByBookName/{bookName}")
    public String findPublishRelationByBookName(@PathVariable String bookName) {
        List<PublishRelation> list = publishService.findPublishRelationByBookName(bookName);
        return JSON.toJSONString(list);
    }

    @ApiOperation("根据作者名查询write关系路径节点")
    @GetMapping("/findWriteRelationByAuthorName/{authorName}")
    public String findWriteRelationByAuthorName(@PathVariable String authorName) {
        List<WriteRelation> list = writeService.findWriteRelationByAuthorName(authorName);
        return JSON.toJSONString(list);
    }


    @ApiOperation("根据书籍名查询write关系路径节点")
    @GetMapping("/findWriteRelationByBookName/{bookName}")
    public String findWriteRelationByBookName(@PathVariable String bookName) {
        List<WriteRelation> list = writeService.findWriteRelationByBookName(bookName);
        return JSON.toJSONString(list);
    }


}
