### 对Native 与 React 交互的学习 
1\ 新建 ZhuRnCallAndroid.class 继承  ReactContextBaseJavaModule  复写getName方法
并分别定义:React调用Native 函数 :rnCallAndroid() 
		  Native调用React代码  函数 :  androidToRn()





         public class ZhuRnCallAndroid extends ReactContextBaseJavaModule {

         private ReactApplicationContext mContext;

           public ZhuRnCallAndroid(ReactApplicationContext reactContext) {
              super(reactContext);
              mContext = reactContext;
           }

           @Override
           public String getName() {
              return "ZhuRnCallAndroid";
           }

           @ReactMethod
           public void rnCallAndroid(String msg, final Callback isOk, final Callback err){
             Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
             new Thread(new Runnable() {
                @Override
                 public void run() {
                    try{
                         Thread.sleep(3000);
                         String str = null;
                         str.toString();
                         isOk.invoke("回调成功");    //通过这种方式 已经实现了Android调用React端代码的通讯
                     }catch (Exception e){
                         err.invoke("回调失败");   //通过回调的方式是可控的
                     }
                  }
                }).start();
               //Intent  intent = new Intent(mContext,MyActivity.class);    //单纯的起一个Activity ,然后在Native中触发一个调用React事件
               //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               //mContext.startActivity(intent);
           }
    
    
        /**
        * 调用React 端
        * @param msg
        */
           public void androidToRn(String msg){ 
    	        //Native端主动发送事件,React端被动的接收
               mContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("zhuMonth",msg);
          }
         }  


2\ 新建类  ZhuMyReactPack.class 并 implements ReactPackage ,并注册ZhuRnCallAndroid接口.

    public class ZhuMyReactPack implements ReactPackage {
      public  ZhuRnCallAndroid mZhuRnCallAndroid;
      @Override
      public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
          List<NativeModule> list = new ArrayList<NativeModule>();
          mZhuRnCallAndroid = new ZhuRnCallAndroid(reactContext);
          list.add(mZhuRnCallAndroid);   //注册
          return list;
      }

      @Override
      public List<Class<? extends JavaScriptModule>> createJSModules() {
          return Collections.emptyList();
      }

      @Override
      public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
      }
    }


3\ RN部分代码 导入NativeModules React调用Native
             


    View部分
    	<Text onPress={this._onPress} style={{textAlign: 'center',backgroundColor:'red',fontSize:50}}>
            OnClickToAndroid
        </Text>


	_onPress() {
        NativeModules.ZhuRnCallAndroid.rnCallAndroid("啷个哩个啷",
            (isOk)=> {
                console.log(isOk);    //打印的信息从Android端返回
            },
            (err)=>{
                console.log(err);
             });
    }


4\  导入DeviceEventEmitter Native调用React    

	 componentWillMount() {
        DeviceEventEmitter.addListener("zhuMonth",this.handAndroidMan);
    }

    componentWillUnMount() {
        DeviceEventEmitter.remove("zhuMonth",this.handAndroidMan);
    }

    /**
     * Android端回调触发打印日志
     * @param msg
     */
    handAndroidMan(msg){
         console.log(msg);
    }

