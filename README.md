# 作者简介: 
Adam Lu(刘亚壮)，高级软件架构师，Java编程专家，开源分布式消息引擎Mysum发起者、首席架构师及开发者，Android开源消息组件Android-MQ独立作者，Java代码生成神器mycode-generator独立作者，国内知名开源分布式数据库中间件Mycat核心架构师、开发者，精通Java, C, C++, Python, Hadoop大数据生态体系，熟悉MySQL、Redis内核，Android底层架构。多年来致力于分布式系统架构、微服务、分布式数据库、大数据技术的研究，曾主导过众多分布式系统、微服务及大数据项目的架构设计、研发和实施落地。在高并发、高可用、高可扩展性、高可维护性和大数据等领域拥有丰富的经验。对Hadoop、Spark、Storm等大数据框架源码进行过深度分析并具有丰富的实战经验。

# 作者联系方式
QQ：2711098650

# 项目简述
mycode-generator项目为一款通用的Java代码生成神器，目前已完全开源。

# 项目文档
在项目的webapp/docs目录下有详细的项目文档。

# mycode-generator项目版本经历
## 1.0.0
本版是mycode-generatorJava通用代码生成器功能增强版1.0.0\<br>
1)支持三个技术栈simple(jsp,clocksim	plejee或默认，s2sh\<br>
  (Struts2,Spring 4, Hibernate 4 hql)和s2shc(Struts2,\<br>
  Spring 4, Hibernate 4 Criteria)\<br>
2)推荐工业级的S2SHC技术栈\<br>
3)支持Eclipse JEE版完整的源码Zip包，可以直接导入Eclipse。\<br>

## 0.7.23
本版是mycode-generatorJava通用代码生成器功能增强版0.7.23\<br>
1)支持ActiveField的两种设置，凡是ActiveField名为deleted\<br>
  或delete(不区分大小写)单词的，和ActiveFiled是其他名字的自动反义。\<br>

## 0.7.22
本版是mycode-generatorJava通用代码生成器文档整理版0.7.22

## 0.7.21
本版是mycode-generatorJava通用代码生成器功能增强版0.7.21\<br>
1)支持DomainId格式分别为int和long

## 0.7.20
本版是mycode-generatorJava通用代码生成器功能增强版0.7.20\<br>
1)增加代码自动保存功能，在代码生成物中含有sgs文件夹，内有以 \<br>
  <项目名>_original.sgs命名的生成生成物的SGS源码。\<br>
2)增强SGS编译器，使其支持call magic魔法语句，自动生成棱柱列表，\<br>
    便利开发。\<br>
3)增强修复S2SH技术栈，不再使用openSession语句，而是使用\<br>
  getSessionFactory().getCurrentSession(),  \<br>
  Spring 3,Spring 4通用，同时web.xml里增加\<br>
  SpringOpenSessionInViewFilter\<br>

## 0.7.19.2
本版是mycode-generatorJava通用代码生成器修复版0.7.19.2\<br>
1)修复系统在Java7,Tomcat 8在64位windows上不能运行的故障。部署\<br>
    请保持一致。\<br>
2)修复Readme文档读取功能，并增补Readme文档\<br>

## 0.7.19
本版是mycode-generatorJava通用代码生成器文档与示例增强版0.7.19\<br>
1）新增手工代码生成技术视频\<br>
2）新增登录示例\<br>
3）新增代码生成原理杂项文档\<br>

## 0.7.18
本版是mycode-generatorJava通用代码生成器官方中文版0.7.18\<br>
1)中文界面\<br>
2）中文提示信息\<br>
3）中文编译错误信息\<br>
4）中文编译警告信息\<br>

## 0.7.17
本版是mycode-generatorJava通用代码生成器mycode-generator检查增强版0.7.17\<br>
1.增加了括号校验，不允许使用SGS语法中未定义的圆括号和方括号。\<br>
2.增强了域对象列表的校验和棱柱列表的校验。\<br>
3.实现了编译警告功能，若选择了IgnoreWarning选择框，编译警告即不影响代码生成。\<br>

## 0.7.16
本版是Java通用代码生成器mycode-generator代码精修版0.7.16\<br>
主要改进了代码的缩进和空格等方面的美化\<br>
改进了Simplejee技术栈的Add,Update,SoftDelete和Delete等动词的代码生成物\<br>
改进了理论PPT，进行了一些修正\<br>

## 0.7.15
本版本为mycode-generator的文档增强版本，支持在线Readme和文档下载\<br>

## 0.7.12
本版是Java通用代码生成器mycode-generator增强修复新版0.7.12\<br>
主要改进：\<br>
1)对所有java关键字和保留字进行保护，不可用作domain和其字段名。\<br>
2)重要的Bug修复，现在可以支持ID为id格式和DomainId格式\<br>
3)还有其他若干重要的Bug修复\<br>

## 0.7.11
本版是Java通用代码生成器mycode-generator增强修复新版0.7.11\<br>
主要改进：
1)对所有java关键字和保留字进行保护，不可用作domain和其字段名。\<br>
2)重要的Bug修复，现在可以支持ID为id格式和DomainId格式\<br>
3)还有其他若干重要的Bug修复\<br>

## 0.7.10
这是Java通用代码生成器mycode-generator新版0.7.10\<br>
从此,代码生成器进入了双时代\<br>
1)双引擎:支持simplejee和s2sh两个技术栈\<br>
2)双界面:支持Jsp Interface和JSON UI两个测试界面\<br>
3)双平台:部署平台可以是Windows也可以是Linux\<br>
还有其他亮点\<br>
-新增DeleteAll和SoftDeleteAll两个动词\<br>
-全面支持Json，自动生成所有动词的 JSON Facade\<br>
-全面测试与排错\<br>

## 0.7.9.2
1)本例为Java代码生成器mycode-generator0.79版,免积分下载\<br>
2)支持自动生成JSON Facade \<br>
3)请使用本代码生成器的DSL即SGS（标准生成器脚本）完成您的工作 \<br>
4)不吝反馈2711098650@qq.com\<br>
5)支持Java语言的两种技术组合：Simplejee和s2sh\<br>
6)生成相应对象的CRUDFLS等常用功能\<br>
7)生成完整的Eclipse JEE版兼容的zip格式的压缩文件\<br>
8)内置SGS脚本(标准生成器脚本)编译器\<br>
9)部署请使用Java7和Tomcat7环境\<br>
10)部署时请首先使用Dynamic Web项目的wizard生成同名项目再把解压后源码包拷入即可编译。\<br>
11)同时生成数据库建库脚本，保存在源码包sql子目录下。\<br>
12)代码生成器不需要数据库后端，只需要将war部署于tomcat的webapps文件夹即可\<br>

## 0.7.9
1)本例为Java代码生成器mycode-generator0.79版 \<br>
2)支持自动生成JSON Facade \<br>
3）请使用本代码生成器的DSL即SGS（标准生成器脚本）完成您的工作 \<br>
4）不吝反馈2711098650@qq.com\<br>

## 0.7.3
1.支持Java语言的两种技术组合：Simplejee和s2sh\<br>
2.生成相应对象的CRUDFLS等常用功能\<br>
3.生成完整的Eclipse JEE版兼容的zip格式的压缩文件\<br>
4.内置SGS脚本(标准生成器脚本)编译器\<br>
5.部署请使用Java7和Tomcat7环境\<br>
6.部署时请首先使用Dynamic Web项目的wizard生成同名项目再把解压后源码包拷入即可编译。
7.同时生成数据库建库脚本，保存在源码包sql子目录下。
8.代码生成器不需要数据库后端，只需要将war部署于tomcat的webapps文件夹即可






