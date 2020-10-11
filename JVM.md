### JVM



jdk结构

![image-20201005083053033](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201005083053033.png)





编译原理：

词法分析---> 语法分析--->语法树--->字节码生成器--->Person.class



class文件结构

```
ClassFile {
    u4             magic;
    u2             minor_version;
    u2             major_version;
    u2             constant_pool_count;
    cp_info        constant_pool[constant_pool_count-1];
    u2             access_flags;
    u2             this_class;
    u2             super_class;
    u2             interfaces_count;
    u2             interfaces[interfaces_count];
    u2             fields_count;
    field_info     fields[fields_count];
    u2             methods_count;
    method_info    methods[methods_count];
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
```



类加载机制

（1）装载

- 先找到类文件的位置
- 类文件信息交给jvm
- 类文件所队友的对象class

（2）链接

- 验证

​          保证被加载的类的正确性

- 准备

​          要为类的静态变量分配内存空间，并将其的值初始化成默认值

​          static int a=10;

- 解析

​           将类中的符号引用转换成直接引用：符号引用，直接引用

​           地址：String str = 地址是什么

（3）初始化

​          为静态变量赋值为真正的值

