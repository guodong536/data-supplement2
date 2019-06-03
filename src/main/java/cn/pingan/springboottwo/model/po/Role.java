package cn.pingan.springboottwo.model.po;

import cn.pingan.springboottwo.common.ModelExcel;
import lombok.Data;

@Data
public class Role {

    private String roleNo;

    @ModelExcel(name = "角色名",sortNum = 0)
    private String roleName;

    @ModelExcel(name="账号",sortNum = 3)
    private String umNo;

    @ModelExcel(name="状态",sortNum = 2)
    private String status;

    @ModelExcel(name="级别",sortNum = 1)
    private String levels;

    private int sortNum;

    private boolean deleteFlag;
}
