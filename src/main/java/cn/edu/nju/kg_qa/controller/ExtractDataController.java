package cn.edu.nju.kg_qa.controller;

import cn.edu.nju.kg_qa.common.CommonResult;
import cn.edu.nju.kg_qa.config.Config;
import cn.edu.nju.kg_qa.service.extractService.*;
import com.csvreader.CsvReader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import static cn.edu.nju.kg_qa.config.Config.EXTRACT_NUM;

/**
 * Description: <br/>
 * date: 2021/1/12 21:31<br/>
 *
 * @author HaoNanWang<br />
 * @since JDK 11
 */
@Api(tags = "提取数据接口")
@RestController
@RequestMapping("/extractData")
public class ExtractDataController {

    private Logger logger = LoggerFactory.getLogger(ExtractDataController.class);

    @Autowired
    HandleAuthorService handleAuthorService;
    @Autowired
    HandleBookSeriesService handleBookSeriesService;
    @Autowired
    HandleBookService handleBookService;
    @Autowired
    HandleCityService handleCityService;
    @Autowired
    HandleConceptService handleConceptService;
    @Autowired
    HandleDateService handleDateService;
    @Autowired
    HandleInstituteService handleInstituteService;

    @ApiOperation(value = "实体：作者 国籍\n" + "关系：assist write humanOf ")
    @PostMapping(value = "/importAuthor")
    public CommonResult<Boolean> importAuthor() {
        File file = null;
        try {
            file = ResourceUtils.getFile(Config.IN_CSV_PATH);
        } catch (FileNotFoundException e) {
            logger.error("未找到文件");
            e.printStackTrace();
            return CommonResult.failed("提取失败，未找到文件");
        }
        try {
            int count = EXTRACT_NUM;
            int i = 0;
            CsvReader csvReader = new CsvReader(file.getAbsolutePath(), ',', Charset.forName("UTF-8"));
            while (i++ < count) {
                try {
                    if (!csvReader.readRecord()) {
                        break;
                    }

                } catch (IOException e) {
                    logger.error("e:文件IO读取错误");
                    e.printStackTrace();
                    return CommonResult.failed("提取失败，文件IO读取错误");
                }
                handleAuthorService.extractAuthor(csvReader);
            }
            handleAuthorService.writeNationEntity();
            handleAuthorService.writeAuthorsEntity();
            handleAuthorService.writeAssistRelation();
            handleAuthorService.writeHumanOfRelation();
            handleAuthorService.writeWriteRelation();
            handleAuthorService.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return CommonResult.success(true,"提取成功");
    }

    @ApiOperation(value = "实体：书籍\n")
    @PostMapping(value = "/importBook")
    public CommonResult<Boolean> importBook() {
        File file = null;
        try {
            file = ResourceUtils.getFile(Config.IN_CSV_PATH);
        } catch (FileNotFoundException e) {
            logger.error("未找到输入文件");
            e.printStackTrace();
            return CommonResult.failed("提取失败，未找到文件");
        }
        try {
            int count = EXTRACT_NUM;
            int i = 0;
            CsvReader csvReader = new CsvReader(file.getAbsolutePath(), ',', Charset.forName("UTF-8"));
            while (i++ < count) {
                try {
                    if (!csvReader.readRecord()) {
                        break;
                    }

                } catch (IOException e) {
                    logger.error("e:文件IO读取错误");
                    e.printStackTrace();
                    return CommonResult.failed("提取失败，文件IO读取错误");
                }
                handleBookService.extractBook(csvReader);
            }
            handleBookService.writeBookEntity();
            handleBookService.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return CommonResult.success(true,"提取成功");
    }

    @ApiOperation(value = "实体：系列丛书 关系：subBook\n")
    @PostMapping(value = "/importBookSeries")
    public CommonResult<Boolean> importBookSeries() {
        File file = null;
        try {
            file = ResourceUtils.getFile(Config.IN_CSV_PATH);
        } catch (FileNotFoundException e) {
            logger.error("未找到输入文件");
            e.printStackTrace();
        }
        try {
            int count = EXTRACT_NUM;
            int i = 0;
            CsvReader csvReader = new CsvReader(file.getAbsolutePath(), ',', Charset.forName("UTF-8"));
            while (i++ < count) {
                try {
                    if (!csvReader.readRecord()) {
                        break;
                    }

                } catch (IOException e) {
                    logger.error("e:文件IO读取错误");
                    e.printStackTrace();
                }
                handleBookSeriesService.extractBookSeries(csvReader);
            }
            handleBookSeriesService.writeBookSeriesEntity();
            handleBookSeriesService.writeSubBookOfRelation();
            handleBookSeriesService.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return CommonResult.success(true,"提取成功");
    }

    @ApiOperation(value = "实体：城市 关系：locateIn\n")
    @PostMapping(value = "/importCity")
    public CommonResult<Boolean> importCity() {
        File file = null;
        try {
            file = ResourceUtils.getFile(Config.IN_CSV_PATH);
        } catch (FileNotFoundException e) {
            logger.error("未找到输入文件");
            e.printStackTrace();
        }
        try {
            int count = EXTRACT_NUM;
            int i = 0;
            CsvReader csvReader = new CsvReader(file.getAbsolutePath(), ',', Charset.forName("UTF-8"));
            while (i++ < count) {
                try {
                    if (!csvReader.readRecord()) {
                        break;
                    }

                } catch (IOException e) {
                    logger.error("e:文件IO读取错误");
                    e.printStackTrace();
                }
                handleCityService.extractCity(csvReader);
            }
            handleCityService.writeCityEntity();
            handleCityService.writeLocateInRelation();
            handleCityService.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return CommonResult.success(true,"提取成功");
    }

    @ApiOperation(value = "实体：概念 关系：belongTo\n")
    @PostMapping(value = "/importConcept")
    public CommonResult<Boolean> importConcept() {
        File file = null;
        try {
            file = ResourceUtils.getFile(Config.IN_CSV_PATH);
        } catch (FileNotFoundException e) {
            logger.error("未找到输入文件");
            e.printStackTrace();
        }
        try {
            int count = EXTRACT_NUM;
            int i = 0;
            CsvReader csvReader = new CsvReader(file.getAbsolutePath(), ',', Charset.forName("UTF-8"));
            while (i++ < count) {
                try {
                    if (!csvReader.readRecord()) {
                        break;
                    }

                } catch (IOException e) {
                    logger.error("e:文件IO读取错误");
                    e.printStackTrace();
                }
                handleConceptService.extractConcepts(csvReader);
            }
            handleConceptService.writeConceptsEntity();
            handleConceptService.writeConceptsRelation();
            handleConceptService.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return CommonResult.success(true,"提取成功");
    }

    @ApiOperation(value = "实体：年 月 关系：publishMonth publishYear\n")
    @PostMapping(value = "/importDate")
    public CommonResult<Boolean> importDate() {
        File file = null;
        try {
            file = ResourceUtils.getFile(Config.IN_CSV_PATH);
        } catch (FileNotFoundException e) {
            logger.error("未找到输入文件");
            e.printStackTrace();
        }
        try {
            int count = EXTRACT_NUM;
            int i = 0;
            CsvReader csvReader = new CsvReader(file.getAbsolutePath(), ',', Charset.forName("UTF-8"));
            while (i++ < count) {
                try {
                    if (!csvReader.readRecord()) {
                        break;
                    }

                } catch (IOException e) {
                    logger.error("e:文件IO读取错误");
                    e.printStackTrace();
                }
                handleDateService.extractDate(csvReader);
            }
            handleDateService.writeMonthEntity();
            handleDateService.writeYearEntity();
            handleDateService.writePublishMonthRelation();
            handleDateService.writePublishYearRelation();
            handleDateService.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return CommonResult.success(true,"提取成功");
    }

    @ApiOperation(value = "实体：机构：publish\n")
    @PostMapping(value = "/importInstitute")
    public CommonResult<Boolean> importInstitute() {
        File file = null;
        try {
            file = ResourceUtils.getFile(Config.IN_CSV_PATH);
        } catch (FileNotFoundException e) {
            logger.error("未找到输入文件");
            e.printStackTrace();
        }
        try {
            int count = EXTRACT_NUM;
            int i = 0;
            CsvReader csvReader = new CsvReader(file.getAbsolutePath(), ',', Charset.forName("UTF-8"));
            while (i++ < count) {
                try {
                    if (!csvReader.readRecord()) {
                        break;
                    }

                } catch (IOException e) {
                    logger.error("e:文件IO读取错误");
                    e.printStackTrace();
                }
                handleInstituteService.extractInstitute(csvReader);
            }
            handleInstituteService.writeInstituteEntity();
            handleInstituteService.writePublishRelation();
            handleInstituteService.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return CommonResult.success(true,"提取成功");
    }
}
