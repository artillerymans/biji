map的操作 获得全部key 或者全部value 

var mMap = new Map();

mMap.set('key1',66)

mMap.set('key2',77)

mMap.get('key2)

77

//获得全部key名
for(let [key] of mMap){
	console.log(key)
}
key1
key2

//获得全部value的值
for(let [,value] of mMap){
	console.log(value)
}
66
77

***
 
 判断对象的API   Math

1、	Math.trunc(8.9); //8  把数字的小数部分去掉，返回一个整数。

2、 	Math.sign(-8);   //-1  判断一个数到底是正数、负数、零、其他
	Math.sign(8);   //1
	Math.sign(0);    //0
	Math.sign(-0);   //-0
	Math.sign();   //NaN  

3、	Math.cbrt(8);  //2  计算一个数的立方根

4、	Math.clz32();   //整数使用32位二进制形式表示，对于小数只考虑整数部分，对于其他类型的数据，先转换成数值让后计算。

5、	Math.hypot(3,4);  //5    返回所有参数的平方和的平方根  eg： 3^2 + 4^2 = 25   最后根号25  等于 5

***

数组扩展API  Array

1\	Array.from('yyyy') // [ 'y', 'y', 'y', 'y' ]    把对象转换成数组  如果对象本身是一个数组,则原样返回该对象.

2\	Array.of(67,98,76); //[67,98,76]   和上一个类似,只不过参数目测必须要是Number类型的才可以.

3\	Array.copyWithin(target,start,end);   // target 要替换的位置   start 从哪里开始读取数据   end到哪里结束(可选) 读到哪里结束 

4\	Array.find();    //查找数组中第一个找到的数据,  find第一个参数是function ,node上测试失败了 

5\	Array.fill(9,2,7);    //填充数组,一般初始化的时候用下,  第一个参数:要填充的数据,第二个参数:开始位置,第三个参数:结束位置,其中第二和第三个参数可以忽略,既全部填充为9

6\	 Array的API有些是会出来数组的空位的,尽量避免空位.

***

函数的默认值

1\	function log(x,y="IPTV"){   //在没有传入y参数是 函数会默认是用IPTV ,传入了则使用调用者传入的值.
		console.log(x,y);
	}  

	log('zhu'); //zhu IPTV
	log('zhu','hi');  //zhu hi

2\	var Persion {
		let 
	}

3\  尾调用或者尾递归对性能都可以起到比较好的优化,循环都是可以用递归替代的,而一旦使用到了递归最好使用尾递归.尾递归不用担心栈溢出.
	
	 function myFor(x,y = 1){
		if(x === 1){ 
			return y;
		}
		return myFor(x -1,x * y);
	}

	上面为简单的尾调用递归,求x的阶乘;   测试速度\效率都很高.

4\	Object.is(x,y);   比较x和y两个值是否严格相等,和 === 类似, 不同的地方再  -0 === +0 和 NaN === NaN 的比对结果是相反的

5\	-javaagent:JetbrainsCrack.jar 



6\ 变量的提升      JavaScript 中，函数及变量的声明都将被提升到函数的最顶部。
				  JavaScript 中，变量可以在使用后声明，也就是变量可以先使用再声明。


				  变量提升：函数声明和变量声明总是会被解释器悄悄地被"提升"到方法体的最顶部。


			初始化不会被提升,   
			        var x = 5; // 初始化 x

					elem = document.getElementById("demo"); // 查找元素 
					elem.innerHTML = x + " " + y;           // 显示 x 和 y

					var y = 7; // 初始化 y				  


						  最后y 是undefined
  


7\ switch 语句会使用恒等计算符(===)进行比较


8\ 在 JavaScript 中, null 用于对象, undefined 用于变量，属性和方法。
   对象只有被定义才有可能为 null，否则为 undefined。

   if (typeof myObj !== "undefined" && myObj !== null) 


****************************************************************************************************************************

8、组件的生命周期
componentWillMount：组件创建之前
getInitialState：初始化状态
render：渲染视图
componentDidMount：渲染视图完成后
componentWillUnmount：组件被卸载之前

***

































