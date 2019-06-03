package cn.pingan.springboottwo.controller;

import cn.pingan.springboottwo.common.ExcelCommonUtils;
import cn.pingan.springboottwo.model.po.Role;
import cn.pingan.springboottwo.model.vo.TestDemo;
import cn.pingan.springboottwo.service.inter.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private TestRepository testRepository;

    public static void main(String[] args) {

        //List<Map<String, String>> resList = ExcelCommon.getModel(Role.class);
        //resList=ExcelCommon.sortTitle(resList);
        //System.out.println(resList);
        try {
            Role role =new Role();
            role.setRoleName("sssssx");
            Class classz = Role.class;
            Field field = classz.getDeclaredField("roleName");
            //设置成可访问
            field.setAccessible(true);
            PropertyDescriptor pd= new PropertyDescriptor(field.getName(),classz);
            Method method = pd.getReadMethod();
            Object obj=method.invoke(role);
            System.out.println(obj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/exportExportUser"})
    public Map<String, Object> export(HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();

        try {

            List<Role> roleList = new ArrayList<>();
            Role role = new Role();
            role.setLevels("1");
            role.setUmNo("guodong536");
            role.setRoleName("超级管理员");
            role.setStatus("0");
            role.setRoleNo("001");
            roleList.add(role);

            Role role1 = new Role();
            role1.setLevels("1");
            role1.setUmNo("pengjie728");
            role1.setRoleName("估值评审员");
            role1.setStatus("3");
            role1.setRoleNo("003");
            roleList.add(role1);

            Role role2 = new Role();
            role2.setLevels("2");
            role2.setUmNo("zhangkang526");
            role2.setRoleName("风控审核员");
            role2.setStatus("0");
            role2.setRoleNo("002");
            roleList.add(role2);
            String name = "用户表" + System.currentTimeMillis();
            exportDeal(response, name);
            ExcelCommonUtils.ExportExcel(roleList, name, Role.class, response);
            result.put("ret", "0");
            result.put("resMsg", "sucess");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.put("ret", "-1");
            result.put("resMsg", e.getMessage());
        }
        return result;
    }


    @RequestMapping(value = "/test_mongodb",method = RequestMethod.GET)
    public TestDemo test_mongodb(){
        TestDemo test = testRepository.findByTitle("MongoDB");
        return test;
    }

    @RequestMapping(value = "/testList",method = RequestMethod.GET)
    public List<TestDemo> testList(){
        List<TestDemo> testList = testRepository.findAll();
        return testList;
    }
}
