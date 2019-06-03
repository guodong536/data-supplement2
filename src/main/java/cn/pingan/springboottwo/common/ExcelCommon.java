package cn.pingan.springboottwo.common;

import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ExcelCommon {

    public static List<Map<String,String>> getModel(Class<?> classz){

        //结果集合
        List<Map<String,String>> resList= new ArrayList<>();
        Field[] fields = classz.getDeclaredFields();

        if (fields !=null && fields.length>0){
            for (Field field :fields) {
                if(field.isAnnotationPresent(ModelExcel.class)){
                    Map<String,String> resMap= new HashMap<>();
                    //字段属性名称
                    String attrName= field.getName();
                    System.out.println(attrName);

                    ModelExcel annotation = field.getAnnotation(ModelExcel.class);

                    String name=annotation.name();

                    String sex=annotation.sex();//不取用

                    int age=annotation.age();//不取用

                    int sortNum =annotation.sortNum();

                    resMap.put("key",attrName);
                    resMap.put("value",name);
                    resMap.put("num",sortNum+"");
                    resList.add(resMap);
                }else{
                    System.out.printf("未进入");
                }
            }
        }
        return resList;
    }

    //排序表头
    public static List<Map<String,String>> sortTitle(List<Map<String,String>> list){

        if(!CollectionUtils.isEmpty(list)){
            Collections.sort(list, new Comparator<Map<String, String>>() {
                public int compare(Map<String, String> o1, Map<String, String> o2) {
                    Integer name1 = Integer.valueOf(o1.get("num")) ;//name1是从你list里面拿出来的一个
                    Integer name2 = Integer.valueOf(o2.get("num")) ; //name1是从你list里面拿出来的第二个name
                    return name1.compareTo(name2);
                }
            });
        }
        return list;
    }

    //通过属性名获取属性值
    public static Object getObjByName(String key,Class classz,Object o){
        Object obj =null;
        try {
            //根据某一属性名称获取域
            Field field=classz.getDeclaredField(key);
            //设置可访问
            field.setAccessible(true);
            PropertyDescriptor propertyDescriptor =new PropertyDescriptor(field.getName(),classz);
            //获取属性名对应的方法名称
            Method method =propertyDescriptor.getReadMethod();
            //获取属性值
            obj =method.invoke(o);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return obj;
    }
}
