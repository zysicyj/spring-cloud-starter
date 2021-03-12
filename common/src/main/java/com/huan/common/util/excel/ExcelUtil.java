package com.huan.common.util.excel;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.exception.excel.ExcelImportException;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import com.huan.common.basic.BasicDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 表格工具
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/3/4
 */
@SuppressWarnings("ALL")
@Slf4j
public class ExcelUtil {

    private ExcelImportResult result;

    private BasicDao dao;


    public ExcelUtil(BasicDao dao) {
        this.dao = dao;
    }

    public ExcelImportResult importExcel(String filePath, Class<?> pojoClass, Class<?> destClass) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(new File(filePath));
            ImportParams params = new ImportParams();
            params.setNeedVerify(true);
            result = new CommonExcelImportService().importExcelByIs(in, pojoClass, params, false);
            if (result != null && CollUtil.isNotEmpty(result.getFailList())) {
                List<ExcelImportError> list = new ArrayList<>();
                result.getFailList().forEach(x -> {
                    ExcelImportError error = new ExcelImportError();
                    error.setCellNumber(((XSSFRow) x).getRowNum() + 1);
                    error.setMessage(((XSSFRow) x).getCell(((XSSFRow) x).getLastCellNum() - 1).getStringCellValue());
                    list.add(error);
                });
                result.setFailList(list);
                result.setVerifyFail(false);
            } else {
                saveAll(result.getList(), destClass);
                result.setVerifyFail(true);
                result.setList(null);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcelImportException(e.getMessage(), e);
        } finally {
            IoUtil.close(in);
        }
    }


    private void saveAll(List resultList, Class<?> destClass) {
        List list = new ArrayList();
        if (CollUtil.isEmpty(resultList)) {
            return;
        }
        int bound = resultList.size() - 1;
        for (int i = 0; i < bound; i++) {
            Object o = null;
            try {
                o = destClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtil.copyProperties(resultList.get(i), o, false);
            resultList.remove(i);
            bound--;
            list.add(o);
        }
        resultList.clear();
        if (CollUtil.isNotEmpty(list)) {
            dao.saveAll(list);
        }
    }
}
