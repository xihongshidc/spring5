package com.dc.springTest.demo;

import java.lang.reflect.Method;

/**
 * description: 桥接方法. spring mvc 调用的是handler 对象的桥接方法目的是为了实现泛型信息的传递.
 * (够正确处理控制器类中的泛型类型，确保在处理请求时能够正确调用相应的控制器方法，而不受泛型类型擦除的影响)
 */
// 定义一个范型接口
public interface Parent<T> {
    T bridgeMethod(T param); //{1}由于 泛型擦除(为了兼容 jdk 1.5 之前的java), class文件中的泛型在编译期都被object 替代了.
	//public abstract Object bridgeMethod(Object param)
}

// 定义一个类实现范型接口
class Child implements Parent<String> {
    public String bridgeMethod(String param) {
        return param;
    }
    //public String bridgeMethod(String param)

    //{2} 在子类 中由于编译的时候 产生的方法,类型和返回值类型和父类不一样, 这在java 语义上是不对的, 因此为了达到java 语义的一致, 生成一个桥接方法.

	//public synthetic bridge Object bridgeMethod(Object param)


}

// 测试方法
class BridgeMethodTest {
    public static void main(String[] args) throws Exception {
       // 使用java的多态
        Parent parent = new Child();
        System.out.println(parent.bridgeMethod("abc123"));// 调用的是实际的方法
        Class<? extends Parent> clz = parent.getClass();
        Method method = clz.getMethod("bridgeMethod", Object.class); // 获取桥接方法
        System.out.println(method.isBridge()); // true
        System.out.println(method.invoke(parent, "hello")); // 调用的是桥接方法
        System.out.println(parent.bridgeMethod(new String("111")));// 调用的是桥接方法, 会报ClassCastException: java.lang.Object cannot be cast to java.lang.String`错误`
    }
}