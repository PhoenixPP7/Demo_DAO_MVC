
public class Emp {

}

/*
==========05_《Java核心设计模式  —— DAO设计模式》
源代码="src/dao/..."目录中的所有程序;

====================DAO设计模式(Data Access Object)---又称业务层设计/业务数据层设计
==========程序设计分层思想;=前台(显示层 + 控制层) + 后台(业务层BO(Business Object) + 数据层DAO) + 数据库;
==========DAO设计模式的组成及各部分开发
=====说明
1.数据层DAO专门进行数据库的原子性操作,通过控制JDBC中的PreparedStatement接口实现;	2.业务层BO调用多个数据层的操作已完成项目的整体业务设计;
3.不同层之间的子类是不见的,访问靠工厂类和接口进行操作,例=业务层->数据层,靠[DAOFactory]、[IEmpDAO];
4.控制层完全看不到任何数据库操作(指JDBC代码);

====================DAO实例=Emp
==========业务要求
=====说明=涉及[emp]表中的[empno、ename、job、hiredate、sal、comm]字段,实现如下操作=
1.雇员添加,且雇员编号不重复;
2.雇员数据修改;
3.多个雇员数据的删除;
4.根据雇员编号查找某个雇员;显示所有雇员;
5.实现数据分页显示(模糊查询),查询所有雇员数量;
==========准备工作=
1.导入Oracle数据库驱动程序;
2.打开数据库的监听与实例服务;
==========开发步骤=
=====开发JDBC连接类=[DatabaseConnection.java];
=====开发VO(Value Object)-简单Java类=[Emp.java];
=====开发数据层-接口=[IEmpDAO];
=====开发数据层-实现类=[EmpDAOImpl];
=====开发数据层-工厂类=[DAOFactory];
=====开发业务层-接口=[IEmpService];
=====开发业务层-实现类=[EmpServiceImpl];
=====开发业务层-工厂类=[ServiceFactory];
=====代码测试=;
=====junit测试;
==========简单Java类=[Emp.java]
=====设计要求=
1.考虑到分布式应用问题,简单Java类需要实现[java.io.Serializable]接口;
2.简单Java类的名称必须与表名称保持一致,例=表名称[emp],则类名称[Emp.java];此类负责数据库的查询设置值及取值;
3.类中属性不允许使用基本数据类型,必须用基本数据类型的包装类;因为基本数据类型默认值=0,而包装类默认值=null;
4.类中属性必须使用private封装,封装后的属性必须提供setter、getter方法;
5.类中必须定义一个无参构造方法;
6.一个实体表对应一个简单Java类;
==========开发数据层-接口=[IEmpDAO]
=====说明=业务层需要调用数据层,故需制定业务层和数据层操作标准,需定义数据层接口;[IEmpDAO];
=====设计要求=
1.数据层进行数据[CRUD]操作,保存在dao包中;
2.数据层接口-命名规则=一个数据表对应一个接口,[接口标记]+[表名]+[DAO]=[IEmpDAO];
3.更新操作-命名规则=[doXxx()]=[doCreate()]、[doUpdate()]、[doRemove()];
4.查询操作-命名规则=[findXxx()]=[findById()]、[findByName()]、[findAll()];
5.统计操作-命名规则=[getXxx()]=[getAllCount];
==========开发数据层-实现类=[EmpDAOImpl]
=====说明=实际开发中,一个业务层操作会执行多个数据层的调用,故数据库的连接和关闭操作由业务层控制比较合理,故数据层;[EmpDAOImpl]
=====设计要求=
1.保存在dao.impl子包中;
2.实现类中的构造方法需要接收一个[Connnection]接口对象;
==========开发数据层-工厂类=[DAOFactory]
=====说明=业务层需要取得数据层接口[IEmpDAO]对象实现对数据层的调用,为方便接口调用,数据层需设计数据层工厂类[DAOFactory]供业务层使用;
=====设计要求=
1.保存在[factory]包中;
==========开发业务层-接口=[IEmpService]
=====说明=业务层可能会被不同层调用,故需制定业务层操作标准,需定义业务层接口;[IEmpService]
=====设计要求=
1.保存在[service]包下;
2.命名规则=参考"开发数据层-接口";
3.业务层类中属性使用基本数据类型;因为业务层会被web调用,数据层使用包装类;
==========开发业务层-实现类=[EmpServiceImpl]
=====说明=业务层对象的目的是控制数据层操作数据库,负责数据库连接和关闭;根据[DAOFactory]工厂类调用[getIEmpDAOInstance()]方法取得[IEmpDAO]接口对象;
=====设计要求=
1.保存在[service.impl]子包中;
==========开发业务层-工厂类=[ServiceFactory]
=====说明=业务层需要被控制层调用,故需设计业务层工厂类[ServiceFactory];
=====设计要求=
1.保存在[factory]包中;
2.前台业务逻辑的工厂类[ServiceFrontFactory]保存在[service.front]包中;本实例不涉及;
3.后台业务逻辑的工厂类[ServiceBackFactory]保存在[service.back]包中;本实例不涉及;
==========代码测试
=====调用测试
1.测试增加操作;
==========JUnit测试
=====说明=选择测试的类或接口,本项目选择[IEmpService]接口,建立[JUnit Test Case],保存在[test.junit]包中,命名为[IEmpServiceTest],

====================DAO实例2=dept
==========业务要求
=====说明=涉及[dept]表中的[deptno、dname、loc]字段,实现如下操作=
1.雇员添加,且雇员编号不重复;---部门添加,且部门编号不重复；
2.雇员数据修改;---部门数据修改;
3.多个雇员数据的删除;---多个部门数据的删除;
4.根据雇员编号查找某个雇员;显示所有雇员;---根据部门编号查找某个雇员;显示所有部门;
5.实现数据分页显示(模糊查询),查询所有雇员数量;
==========开发步骤=
=====使用JDBC连接类=[DatabaseConnection.java];
=====开发VO(Value Object)-简单Java类=[Dept.java];---此类负责数据库的查询设置值及取值;
=====开发数据层-接口=[IDeptDAO];---因为与[IEmpDAO]功能类似,都是[CRUD]操作,为减少重复代码,故合并改为[IDAO];
=====开发数据层-实现类=[DeptDAOImpl];
=====开发数据层-工厂类=[DAOFactory];---使用DAOFactory,取得IDeptDAO接口的实现类对象;
=====开发业务层-接口=[IDeptService];---IEmpService
=====开发业务层-实现类=[DeptServiceImpl];---EmpServiceImpl
=====开发业务层-工厂类=[ServiceFactory];---使用ServiceFactory.java工厂类,取得IDeptService接口的实现类对象;
=====代码测试=;
=====junit测试；

====================DAO-总结
==========开发步骤=
=====开发JDBC连接类=[DatabaseConnection.java];---负责数据库的连接和关闭;
=====开发VO类(Value Object)(简单Java类)=[Emp.java]/[Dept.java];---负责数据库原子性操作,取得数值/设置数值;
=====开发数据层-接口=[IEmpDAO];---提供子类的标准操作;
=====开发数据层-实现类=[EmpDAOImpl];---负责具体操作;
=====开发数据层-工厂类=[DAOFactory];---负责根据要求的接口提供对应的实现类;
=====开发业务层-接口=[IEmpService];---同上;
=====开发业务层-实现类=[EmpServiceImpl];---同上;负责数据库的连接和关闭操作;
=====开发业务层-工厂类=[ServiceFactory];---同上;
=====代码测试=;
=====junit测试;
==========总结
DAO接口定义过程中,不同的表主要区别在于VO类、主键类型,所以VO类需要分别定义;
数据层操作功能大多类似,都是[CRUD]操作,故数据层接口可以统一定义[IDAO];
业务层因为是用户直接调用的方法,故不建议定义类似[IDAO]的抽象类,建议分开定义[IEmpService]和[IDeptService];
==========自我总结-数据增加操作时=
1.前台通过表单输入[数据表]各个列字段信息,生成一个[数据表对象];数据库连接对象类似;
2.通过控制层-业务层-数据层-将[数据表对象]一层层传递下去,直到最后执行原子性数据库操作,增加一行[数据表]数据;

*/