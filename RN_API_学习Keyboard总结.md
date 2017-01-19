# RN API Keyboard简单总结

### Keyboard的作用:

* 对原生的键盘隐藏\显示进行监听,处理相应的逻辑.

1. 导入API
   import {
       AppRegistry,
       StyleSheet,
       Text,
       View,
       TextInput,
       Keyboard,
    } from 'react-native';  

2. 生命周期方法进行监听并回调私有方法:

        componentWillMount() {
            this.keyboardDidShowListener=Keyboard.addListener('keyboardDidShow',this._keyShow);
            this.keyboardDidHideListener=Keyboard.addListener('keyboardDidHide',this._keyHide);
        }

3. 移除监听:

        componentWillUnMount() {
           this.keyboardDidShowListener.remove();
           this.keyboardDidHideListener.remove();
        }

4. View部分一个简单Intput并监听是否点击了软键盘的确定或者提交按钮(onSubmitEditing):
   
                    <TextInput style={{width:W,height:45,justifyContent:'center',marginTop: 30}}
                               onSubmitEditing={Keyboard.dismiss}>

                    </TextInput>
		    
		    
   Keyboard.dismiss : 隐藏键盘. 测试了下 好像也没什么卵用.没有设置onSubmitEditing属性的Intput也一样正常回调

5. 回调的私有方法

       
        _keyShow() {
       
           ToastAndroid.show("键盘显示",ToastAndroid.SHORT);
	   
        }


        _keyHide() {
       
           ToastAndroid.show("键盘隐藏",ToastAndroid.SHORT);
	   
        }
       
       
       
6. 结束!!!!!!
