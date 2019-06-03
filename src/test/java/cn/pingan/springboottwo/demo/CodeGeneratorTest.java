package cn.pingan.springboottwo.demo;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成代码
 * @author Administrator
 *
 */
public class CodeGeneratorTest {

	@Test
	public void generateCode() {
		//基础包定义
		String packageName="cn.java.code";
		//需要生成代码的表，空则生成所有
		String[] tableNames= {
			"qrtz_triggers"
		};
		//接口以I开始，如user-->UserService,设置为true,user-->IUserService
		boolean serviceNameStartWithI=false;
		String url="jdbc:mysql:///mysql?serverTimezone=UTC";
		String userName="root";
		String password="root";
		String driverName="com.mysql.cj.jdbc.Driver";
		generateByTables(url,userName,password, driverName,serviceNameStartWithI,packageName,tableNames);
	}
	
	/**
	 * 需要自动填充的字段，
	 * 创建时间，修改人，修改时间
	 */
	private List<TableFill> getTableFill(){
		List<TableFill> tableFields=new ArrayList<>();
		TableFill tf1=new TableFill("CREATE_BY",FieldFill.INSERT);
		TableFill tf2=new TableFill("CREATE_DATE",FieldFill.INSERT);
		TableFill tf3=new TableFill("UPDATE_BY",FieldFill.UPDATE);
		TableFill tf4=new TableFill("UPDATE_DATE",FieldFill.UPDATE);
		TableFill tf5=new TableFill("object_version",FieldFill.DEFAULT);
		tableFields.add(tf1);
		tableFields.add(tf2);
		tableFields.add(tf3);
		tableFields.add(tf4);
		tableFields.add(tf5);
		return tableFields;
	}
	
	/**
	 * 自动生成表结构
	 */
	private void generateByTables(String url,String userName,String password,String driverName,boolean serviceNameStartWithI,String packageName,String[] tableNames) {
		//链接配置
		DataSourceConfig dataSourceConfig=new DataSourceConfig();
		dataSourceConfig.setDbType(DbType.MYSQL)
		.setUrl(url)
		.setUsername(userName)
		.setPassword(password)
		.setDriverName(driverName);
		
		//策略配置
		StrategyConfig strategyConfig=new StrategyConfig();
		strategyConfig.setCapitalMode(true)
		.setEntityLombokModel(false)
		.setDbColumnUnderline(true)//表名、字段名、是否使用下划线命名（默认 false）
		.setNaming(NamingStrategy.underline_to_camel)
		.setTablePrefix("cmts_")
		.setEntityLombokModel(true)
		.setTableFillList(getTableFill())
		.setSuperEntityClass("cn.java.code.entity.BaseVo")
		.setSuperControllerClass("cn.java.code.controller.BaseController")
		.setVersionFieldName("object_version")
		.setInclude(tableNames);//需要生成的的表名，多个表名传数组
		
		//全局配置
		GlobalConfig config=new GlobalConfig();
		config.setActiveRecord(false)
		.setAuthor("GUODONG536")
		.setOutputDir("d:\\CenCode")
		//.setOutputDir("F:\\springbootwork\\cmtspingan\\src\\main\\java")
		.setIdType(IdType.INPUT)
		.setFileOverride(false);
		
		if(!serviceNameStartWithI) {
			config.setServiceName("%sService");//设置service名
		}
		
		new AutoGenerator().setGlobalConfig(config)
		.setDataSource(dataSourceConfig)
		.setStrategy(strategyConfig)
		.setPackageInfo(
		new PackageConfig().setParent(packageName)
		.setController("controller")
		.setMapper("dao")
		.setEntity("model.po")
		).execute();
	}
}
