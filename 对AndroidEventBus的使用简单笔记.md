#### 对AndroidEventBus的使用简单笔记
1. 
>build.gradle上添加依赖  
 compile 'org.simple:androideventbus:1.0.5.1'

2. 完整的源码部分


``` 
package com.example.zhiwei_zhu.androideventbustest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private Button a,b,c,d,e,f,j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView();

    }

    private void initView() {
        a = (Button) findViewById(R.id.a);
        b = (Button) findViewById(R.id.b);
        c = (Button) findViewById(R.id.c);
        d = (Button) findViewById(R.id.d);
        e = (Button) findViewById(R.id.e);
        f = (Button) findViewById(R.id.f);
        j = (Button) findViewById(R.id.j);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.a:
                EventBus.getDefault().post(new User("a"),"a");
                break;
            case R.id.b:
                EventBus.getDefault().post(new User("b"),"b");
                break;
            case R.id.c:
                EventBus.getDefault().post(new User("c"),"c");
                break;
            case R.id.d:
                break;
            case R.id.e:
                break;
            case R.id.f:
                break;
            case R.id.j:
                break;
            default:
                Toast.makeText(this,"this is nothings",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Subscriber(mode = ThreadMode.POST,tag = "a")
    private void a(User user){
        Toast.makeText(this,user.getName(),Toast.LENGTH_SHORT).show();
    }

    @Subscriber(mode = ThreadMode.POST,tag = "b")
    private void b(User user){
        Toast.makeText(this,user.getName(),Toast.LENGTH_SHORT).show();
        Log.i("zhu", "### update user async , name = " + user.name + ", thread name = " + Thread.currentThread().getName());

    }

    @Subscriber(mode = ThreadMode.ASYNC,tag = "c")
    private void c(User user){
        Toast.makeText(this,user.getName(),Toast.LENGTH_SHORT).show();
        Log.i("zhu", "### update user async , name = " + user.name + ", thread name = " + Thread.currentThread().getName());

    }

    public class User{
        String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
```


### 分步解析代码

1. 注册监听

	```
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);  //注册监听
        initView();
    }
    ```
2. 注销监听

	 ```
		@Override
   	 	protected void onDestroy() {
        	super.onDestroy();
        	EventBus.getDefault().unregister(this); //注销监听
      	}
	 ```
3. 点击事件触发相应的Post请求,传入的参数第一个为object类型,第二个为String类型的标记tag. 因为要一个Object所以上面定义了一个内部类User,通过这个内部类来传递参数,而第二个参数可以不传,有默认值,如果传了则在后面的方法体里面要指定一个相对应的tag才能执行.
	
	```
	public void onClick(View view){
        switch (view.getId()){
            case R.id.a:
                EventBus.getDefault().post(new User("a"),"a");
                break;
            case R.id.b:
                EventBus.getDefault().post(new User("b"),"b");
                break;

	``` 
	
4. 触发的相应事件,通过@Subscriber 标注,如果没有这个标注会无法执行到该方法,后面括号里面的带有连个参数,第一个参数有三个值 ThreadMode.POST | ThreadMode.MAIN | ThreadMode.ASYNC 分别代表意思运行再发送事件的线程里,运行在主线程里,运行在单独的线程里面,但是测试了一下,再单独线程里面的可以进行UI的更新,不会出现Crash的现象,通过日志可以看到有异常抛出,UI更新正常.第二个参数 自带默认值,表示只有再post的时候指定的tag,当有指定tag的方法体才会执行.
   
   ```
   @Subscriber(mode = ThreadMode.POST,tag = "a")
    private void a(User user){
        Toast.makeText(this,user.getName(),Toast.LENGTH_SHORT).show();
    }

    @Subscriber(mode = ThreadMode.POST,tag = "b")
    private void b(User user){
        Toast.makeText(this,user.getName(),Toast.LENGTH_SHORT).show();
        Log.i("zhu", "### update user async , name = " + user.name + ", thread name = " + Thread.currentThread().getName());

    }
   ```
   
5. 以上为使用AndroidEventBus简单应用的Demo,该框架是为了解决内部组件之间的通信问题,如Server和广播这种高成本的通讯解决方案,或者Activity之间通讯也可以.

	