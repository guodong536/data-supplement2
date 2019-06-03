package cn.pingan.springboottwo.common;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelCommonUtils {

    public static void ExportExcel(List list, String sheetName, Class<?> classz, HttpServletResponse response) throws IOException {

        Workbook wb= new HSSFWorkbook();
        Sheet sheet=wb.createSheet(sheetName);

        //创建一个标题
        Row row=sheet.createRow(0);
        List<Map<String,String>> oList=ExcelCommon.sortTitle(ExcelCommon.getModel(classz));

        //填充标题
        if(!CollectionUtils.isEmpty(oList)){
            for (int a=0 ;a <oList.size();a++){
                Cell cell=row.createCell(a);
                cell.setCellValue(oList.get(a).get("value"));
            }
        }

        //数据填充内容
        if(!CollectionUtils.isEmpty(list)){
            for (int i=0 ;i<list.size();i++){
               Row rows= sheet.createRow(i+1);
               Object obj= list.get(i);

               for (int j=0; j<oList.size(); j++){
                   //属性名称
                   String key=oList.get(j).get("key");
                   //属性值
                   Object object=ExcelCommon.getObjByName(key,classz,obj);
                   Cell cell=rows.createCell(j);
                   cell.setCellValue(object+"");
               }
            }
        }

        //响应数据
        wb.write(response.getOutputStream());
        wb.close();
    }
}
