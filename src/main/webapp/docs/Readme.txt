
本文档是所有mycode-generator所有版本的Readme合集

===============1.0.0=============================
本版是mycode-generatorJava通用代码生成器功能增强版1.0.0
1)支持三个技术栈simple(jsp,clocksim	plejee或默认，s2sh
  (Struts2,Spring 4, Hibernate 4 hql)和s2shc(Struts2,
  Spring 4, Hibernate 4 Criteria)
2)推荐工业级的S2SHC技术栈
3)支持Eclipse JEE版完整的源码Zip包，可以直接导入Eclipse。

===============0.7.23===========================
本版是mycode-generatorJava通用代码生成器功能增强版0.7.23
1)支持ActiveField的两种设置，凡是ActiveField名为deleted
  或delete(不区分大小写)单词的，和ActiveFiled是其他名字的自动反义。

===============0.7.22===========================
本版是mycode-generatorJava通用代码生成器文档整理版0.7.22

===============0.7.21===========================
本版是mycode-generatorJava通用代码生成器功能增强版0.7.21
1)支持DomainId格式分别为int和long

===============0.7.20===========================
本版是mycode-generatorJava通用代码生成器功能增强版0.7.20
1)增加代码自动保存功能，在代码生成物中含有sgs文件夹，内有以 
  <项目名>_original.sgs命名的生成生成物的SGS源码。
2)增强SGS编译器，使其支持call magic魔法语句，自动生成棱柱列表，
    便利开发。
3)增强修复S2SH技术栈，不再使用openSession语句，而是使用
  getSessionFactory().getCurrentSession(),  
  Spring 3,Spring 4通用，同时web.xml里增加
  SpringOpenSessionInViewFilter

===============0.7.19.2===========================
本版是mycode-generatorJava通用代码生成器修复版0.7.19.2
1)修复系统在Java7,Tomcat 8在64位windows上不能运行的故障。部署
    请保持一致。
2)修复Readme文档读取功能，并增补Readme文档

===============0.7.19===========================
本版是mycode-generatorJava通用代码生成器文档与示例增强版0.7.19
1）新增手工代码生成技术视频
2）新增登录示例
3）新增代码生成原理杂项文档

===============0.7.18===========================
本版是mycode-generatorJava通用代码生成器官方中文版0.7.18
1)中文界面
2）中文提示信息
3）中文编译错误信息
4）中文编译警告信息

===============0.7.17===========================
本版是mycode-generatorJava通用代码生成器mycode-generator检查增强版0.7.17
1.增加了括号校验，不允许使用SGS语法中未定义的圆括号和方括号。
2.增强了域对象列表的校验和棱柱列表的校验。
3.实现了编译警告功能，若选择了IgnoreWarning选择框，编译警告即不影响代码生成。

===============0.7.16===========================
本版是Java通用代码生成器mycode-generator代码精修版0.7.16
主要改进了代码的缩进和空格等方面的美化
改进了Simplejee技术栈的Add,Update,SoftDelete和Delete等动词的代码生成物
改进了理论PPT，进行了一些修正

===============0.7.15===========================
本版本为mycode-generator的文档增强版本，支持在线Readme和文档下载

===============0.7.12===========================
本版是Java通用代码生成器mycode-generator增强修复新版0.7.12
主要改进：
1)对所有java关键字和保留字进行保护，不可用作domain和其字段名。
2)重要的Bug修复，现在可以支持ID为id格式和DomainId格式
3)还有其他若干重要的Bug修复

===============0.7.11===========================
本版是Java通用代码生成器mycode-generator增强修复新版0.7.11
主要改进：
1)对所有java关键字和保留字进行保护，不可用作domain和其字段名。
2)重要的Bug修复，现在可以支持ID为id格式和DomainId格式
3)还有其他若干重要的Bug修复

===============0.7.10===========================
这是Java通用代码生成器mycode-generator新版0.7.10
从此,代码生成器进入了双时代
1)双引擎:支持simplejee和s2sh两个技术栈
2)双界面:支持Jsp Interface和JSON UI两个测试界面
3)双平台:部署平台可以是Windows也可以是Linux
还有其他亮点
-新增DeleteAll和SoftDeleteAll两个动词
-全面支持Json，自动生成所有动词的 JSON Facade
-全面测试与排错

===============0.7.9.2===========================
1)本例为Java代码生成器mycode-generator0.79版,免积分下载
2)支持自动生成JSON Facade 
3)请使用本代码生成器的DSL即SGS（标准生成器脚本）完成您的工作 
4)不吝反馈2711098650@qq.com
5)支持Java语言的两种技术组合：Simplejee和s2sh
6)生成相应对象的CRUDFLS等常用功能
7)生成完整的Eclipse JEE版兼容的zip格式的压缩文件
8)内置SGS脚本(标准生成器脚本)编译器
9)部署请使用Java7和Tomcat7环境
10)部署时请首先使用Dynamic Web项目的wizard生成同名项目再把解压后源码包拷入即可编译。
11)同时生成数据库建库脚本，保存在源码包sql子目录下。
12)代码生成器不需要数据库后端，只需要将war部署于tomcat的webapps文件夹即可

===============0.7.9===========================
1)本例为Java代码生成器mycode-generator0.79版 
2)支持自动生成JSON Facade 
3）请使用本代码生成器的DSL即SGS（标准生成器脚本）完成您的工作 
4）不吝反馈2711098650@qq.com

===============0.7.3===========================
1.支持Java语言的两种技术组合：Simplejee和s2sh
2.生成相应对象的CRUDFLS等常用功能
3.生成完整的Eclipse JEE版兼容的zip格式的压缩文件
4.内置SGS脚本(标准生成器脚本)编译器
5.部署请使用Java7和Tomcat7环境
6.部署时请首先使用Dynamic Web项目的wizard生成同名项目再把解压后源码包拷入即可编译。
7.同时生成数据库建库脚本，保存在源码包sql子目录下。
8.代码生成器不需要数据库后端，只需要将war部署于tomcat的webapps文件夹即可






















