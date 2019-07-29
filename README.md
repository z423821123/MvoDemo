# MvpDemo
分享自己一个使用的Mvp开发模式以及自己用Java写的Mvp类生成插件  
我理解的MVP有以下几个优点  
1.业务逻辑清晰,易于维护.将视图层与业务层剥离,也就是presenter控制view的所有业务逻辑,业务又由model里面的一个方法实现. 如果有复杂耗时业务逻辑的话可以在Model里面创建线程池进行统一管理.  
2.数据走统一流向,可以统一对数据进行操作.在平时开发中很有可能出现,当你进行网络请求时,这时候网络延迟,导致Activity关闭,服务器才返回数据,这时候有可能因为presenter持有activity的引用,使得Activity无法被回收导致内存泄漏(有兴趣的朋友可以了解下Java的垃圾回收机制)和程序崩溃.我们可以在baseActivity中声明一个basePresenter弱引用对象,这样在Activity没有强引用对象指向他时被GC回收.程序崩溃也可以通过在basepresenter进行统一判断activity是否存在,避免程序因控件销毁而导致的null报错.  
3.符合开闭原则,提供扩展,关闭修改.有一天产品经理会提出这样的需求,不将数据提交到服务器而是在本地保存,这时候你可以创建一个数据库Model实现类实现IModel接口,将数据保存方法切换成数据库存储,完全不影响到之前的代码.  
4.大大减少Actvity的代码,如果你经历过一个类里面有几千行代码你就能深深体会到这一点好处,这点没什么好说的,比较浅显.    
缺点也很明显  
1.增加代码量以及新建大量的接口,所以自己写了一个MVP类和接口生成插件分享给大家.  


再次用泛型封装了部分代码  
1.删除Activity中抽象方法createPresenter实例createPresenter  
在基类中用反射获取泛型实现类,构造一个presenter实例 同理Model也做了调整  
presenter实例通过Activity中的getPresenter()方法获得, model实例通过presenter中的getModel()方法获得  
protected T createPresenter(){  
        try {  
            Type superClass = getClass().getGenericSuperclass();  
            Type type = ((ParameterizedType) superClass).getActualTypeArguments()[1];  
            Class<?> clazz = getRawType(type);  
            return (T) clazz.newInstance();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
}  

2.数据回调类使用泛型声明,在实例回调接口时声明具体实体类即可,不用重复创建多个重复的回调类  
public interface callBackListener<T>{  
    void onSuccess(T data);  
    void onError(String msg);  
}  
