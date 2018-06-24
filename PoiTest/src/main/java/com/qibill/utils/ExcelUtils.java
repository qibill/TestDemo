package com.qibill.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    public static void main(String[] args) throws FileNotFoundException {
        List<Person> data= new ArrayList<>();
        
        for(int i=0;i<100;i++){
            Person p = new Person();
            p.setName(i+"名字");
            p.setAge(i);
            
            data.add(p);
        }
        //获得程序当前路径
        String path = System.getProperty("user.dir");
        System.out.println(path);
        FileOutputStream out = new FileOutputStream(new File(path+"/test.xlsx"));
        String[] headers = new String[]{"姓名","年龄"};
        String[] fiedlNames = new String[]{"name","age"};;
        
        export(Person.class, headers, fiedlNames, data, out);
    }
    
    
    public static <T> MsgEnum export(Class<T> clazz, String[] headers,String[] fiedlNames, List<T> data, OutputStream out) {
        if (headers.length == 0 || headers.length != fiedlNames.length) {
            // 表头和字段名有问题
            return MsgEnum.EXPORT_HEADER_ERRO;
        }/* else if (data == null || data.size() == 0) {
            // 数据为空
            return MsgEnum.EXPORT_DATA_EMPTY;
        }*/ else {
            Method[] methods = new Method[fiedlNames.length];
            try {
                for (int i = 0; i < fiedlNames.length; i++) {
                    //利用反射，调用getXX方法
                    methods[i] = clazz.getMethod("get"+StringUtils.upperFirstCase(fiedlNames[i]));
                }
            } catch (Exception e) {
                System.out.println("字段映射错误！");
                return MsgEnum.EXPORT_FIELD_ERRO;
            }

            try {
                XSSFWorkbook wb = new XSSFWorkbook();
                Sheet sh = wb.createSheet();
                Row headerRow = sh.createRow(0);
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    setValue(cell, headers[i]);
                }

                for (int i = 0; i < data.size(); i++) {
                    Object obj = data.get(i);
                    Row dataRow = sh.createRow(i + 1);
                    for (int j = 0; j < methods.length; j++) {
                        Cell cell = dataRow.createCell(j);
                        Method method = methods[j];
                        setValue(cell, method.invoke(obj));
                    }
                }
                for (int i = 0; i < headers.length; i++) {
                    sh.autoSizeColumn((short)i); //调整第一列宽度
                }
                wb.write(out);
                wb.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return MsgEnum.SUCCESS;
        }
    }
    
    public static void setValue(Cell cell, Object object) {
        cell.setCellValue(String.valueOf(object));
    }
    
}


enum MsgEnum {
    SUCCESS("000000","success"),
    ERROR("999999","系统异常！"),
    EXPORT_DATA_EMPTY("301","导出数据为空！"),
    EXPORT_HEADER_ERRO("302","导出表头错误！"),
    EXPORT_FIELD_ERRO("303","导出字段错误！"),
    EXPORT_SYSTEM_ERRO("303","导出系统异常！");
    
    private final String code;
    private final String msg;
    
    private MsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    
}


class StringUtils{
    /**
     * 对于指定属性的第一个字母大写
     * @param propName          属性名
     */
    public static String upperFirstCase(String propName) {
        if(isBlank(propName)){
            return null;
        }else 
        return String.valueOf(propName.charAt(0)).toUpperCase() + propName.substring(1);
    }
    
    public static boolean isBlank(String str) {
        int strLen;
        if (null == str || (strLen = str.length()) == 0 || "null".equals(str)) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
}

class Person{
    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
