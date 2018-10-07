# English Version

## For data restorage:
1、Files(not include java files), pictures, data, other non-code files should be put in static folder and sorted in different package.

2、Environment parameters should be defined in static/project.properties, the format is simple: 

​	key=value (no need quotation mark).

3、The first line of the csv file should be the title of each column. Their names should be totally the same as the fields name defined in your entity class.  (normally this thing won't happen so we won't worry about it but if you manually change the file, please follow this rule or it might cause some problems)

## For coding:
1、Package name should be in this format: neu.edu.csye6200.team.YourPartName

​	For example: Student.java should be in neu.edu.csye6200.team.objects

2、Class name should be as simple as possible. However, don't make it abstract(ex. use S to represent Student), use at least one complete word.

3、The name of methods should be like this: 
​	notifyClassToday (first part should be the operation name-could be formed by more than one word, next part should be the target that you operate on, last part, if needed, should be the argument name or specific operation type)

4、The fields name should use the word that describe the usage of this field best. If needed, appends the constrain of this field.

5、Remember to use Interface if different type of classes are actually doing the similar operation. 

​	For example, data managing for Teacher and Student are all data managing, in which only their types of data are different. Then you should create a DataManaging interface(actually I've created) and create two different classes TeacherDataManaging and StudentDataManaging to accomplish the task. It seems more complicated, but less coupled.

6、If some fields and methods of a class will not be used by other classes, make them private. A utility class method should always be static. (Something more TBD)



# 中文版

英文为了presentation方便，中文版：

## 对于数据存储:

1、非java文件，图片，数据文件，任何非编码类文件都应当放置在static文件夹下并归类
2、环境参数（指的是程序运行之后就不会修改的参数）应当放置在static文件夹下的project.properties文件中格式非常简单，键=值，不用写引号
3、csv文件的第一行必须是列名，并且应当对应你定义的实体类中的属性名，一个字母都不要修改

## 对于编码：

1、包名应遵循如下格式：neu.edu.csye6200.team.你负责的模块名，具体用什么词汇可以自行斟酌

2、类名应当尽量精简。但是别太抽象，大家会看不懂。每个单词的首字母一定要大写（驼峰命名），属性名和方法名则除了第一个单词剩下的单词首字母也都应大写

3、方法名的命名方式：第一部分是具体的操作名，第二部分是被操作的对象名，第三部分是操作类型名或如有需要加上参数名。

4、属性名应当由具体属性描述的参数名命名，如有需要，加上参数的约束名

5、注意接口的使用。如果多个不同的类实际上在做类似的操作，那么他们应当共享同一个接口，这样会使得代码更加整洁。这也是策略模式的一种体现。虽然在初次编写代码时看起来复杂，但是可以很好的降低代码的耦合。

6、如果一个类中的一些方法和属性不会被其他类使用，那么请将他们的作用于调整至私有。工具类的方法一定是静态的。