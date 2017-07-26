 二、、修改代码流程，以master分支为例：

平常的工作中，一般只需要执行步骤：5、7、8、10、12、13

1、检查本地当前分支与远程分支的跟踪情况
git branch -a -v

2、如果本地没有建master分支，则新建，且跟踪到主分支/origin/master
git checkout -b master remotes/origin/master

如果已有master本地分支但是没有跟踪远程的master分支，则跟踪：
8V9:git reset --hard origin/master
8V9A:git reset --hard rk/ec6108v9a（在需要跟踪的目录下执行）

3、指定版本指定文件恢复
git checkout e43d1a8761a809ad23413d386c821049edd9ff73 .
注: git + checkout + 版本号 + .
版本号可以通过git log . 获取当前目录修改过的版本号( commit 值)
. 是指的当前目录,也可以改成指定的文件

4、本地当前分支下清除本地未被跟踪的所有文件、更新检测、跟踪远程的master分支
8V9:git clean -df && git fetch --all && git reset --hard origin/master

5、更新最新代码
git fetch --all && git pull origin master

/**
* 合入代码...
*/

6、暂存本地修改(正常开发过程中，非常适合临时插入的紧急bug修改)
git stash
注：此操作是将本地未提交的修改暂存起来，并将文件状态恢复到HEAD
git stash pop
注：恢复暂存的修改

7、查看与本地库的差异(修改情况)
git status 查看本地代码的变动情况

8、比对修改前后的差异
git diff 比较本地与本地库的差异
git diff OldcommitID NewcommitID(前6位) 比较文件/目录下所有文件两个特定的commit之间的差异
git diff commit_a commit_b --文件名 查看某个文件两个特定的commit之间的差异
git diff ––cached 比较已缓存(添加或移除)的，与本地库的差异

9、取消本地文件的修改，包括删除的文件
git checkout --文件名
注：此操作将取消本地文件（未提交）已做的但未提交的修改，如果需回退当前目录下的所有修改文件，则可以通过“git checkout .”命令来完成。
git clean -f
注：清除本地未被跟踪的所有文件，使用时需要注意是否确定需要清除。

10、添加变动的修改到缓存
git add 具体文件路径（新增）
或者
git rm 具体文件路径（删除）

注：以上命令均可增加“ -r 目录路径”，对指定的目录进行操作

git add --all 添加本地所有变动(不确定本地是否有跟所需无关的改动时，请慎用)

11、取消已缓存的修改（回退）
git rm –cached 文件名 
git reset HEAD 文件名

注：以上操作将修改的文件从缓冲区内删除，同样不影响本地内容。

12、提交修改到本地库

提交没单号的：
git commit -m "DTS:DTS US:SA/SR US-20140919115348-37609 
Description:【吉林联通8V9A】
零配置下发成功后会切换网络 打开开关控制不切换" 
git commit -m "DTS:DTS US:SA/SR US-20140919115348-37609 
Description:【福建电信8V9】
设置交接源码"

git commit -m "DTS:DTS US:SA/SR US-20140919115348-37609 
Description:【新疆联通8V9A】
屏蔽" 

提交有单号的：
git commit -m "DTS2016071509770 US:SA/SR 
Description:【福建电信8V9B】
单号：DTS2016071509770，新增开关控制：显示拉起BMC的入口" 

git commit -m "DTS2016082307033 US:SA/SR 
Description:【吉林联通8V9A】
更新数据库StbConfigProvider.apk" 

git commit --amend 修改本地库的commit 备注信息

13、将本地库的commit推送到远程中心代码库 
git push origin HEAD:master 推送到远程中心代码库

--------------------

三、git查看历史日志

8V9：

git log 查看本地库的提交记录
git log --stat 查看提交记录：显示文件修改概要
git log -p 查看提交记录：显示文件修改详情
git log -p filename 查看filename的log日志，-p的意思是展开每次的提交差异
git log -p -2 只显示最近的两次log
git log --author=luchunan 查看提交人的提交记录(提交人可自定义)
git log --grep=4k 过滤包含有字段“4k”的提交信息(字段可自定义)
git log --decorate --graph --all 查看分叉历史：提交历史、各个分支的指向以及项目的分支分叉情况。
git log --stat -p --since=2015-3-30.20:00:39 --before=2015-4-1.10:51:06 查看一个时间段的代码提交记录(从...到...)

8V9A：
repo/repo forall -c "git log --stat -p --since=2015-3-30.20:00:39 --before=2015-4-1.10:51:06" 查看一个时间段的代码提交记录(从...到...)
repo/repo forall -c "git reset --hard --stat -p --since=2015-3-30.20:00:39 --before=2015-4-1.10:51:06"(不可用)

--------------------
四、版本回退

先查看历史日志，获取需要回退到的，指定版本的commitID(每一次commit都会生成一个全球唯一的commitID）

git reset --hard "指定版本的commitID"

例：
git reset --hard bbf92a48d6459ec0c67af6042ec94a02eb

有时候本地库的修改、添加缓存、提交记录等比较混乱时，确保没有重要修改需要保留时，可以执行以下命令，强制更新版本到最新
git reset --hard origin/master

--------------------
五、分支操作与管理

1、查看
git branch 查看本地分支
git branch -r 查看远程分支
注：红色的代表远程分支
git branch -v 查看各个分支最后一个提交对象的信息
git branch -a 查看所有分支

2、创建
git branch 分支名 创建本地分支
git remote add -t BRANCH_NAME_HERE -f origin REMOTE_REPO_URL_PATH_HERE 克隆一个特定的远程分支到本地
git checkout -b newbranch origin/remote_branch 以某个远程branch最新的一个snapshot为基础创建一个新的branch
git checkout --track origin/dev 从远程拉取dev分支到本地，并切换到dev分支




通过vi 1、打开终端命令输入：vi 2、输入：i 3、输入要保存的内容 4、内容输入完毕后，按esc后输入 ：wq 文件名

＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊

设置环境变量

1、打开终端输入－－》vi .bash_profile －－》输入i进入编辑状态 2、设置对应的 e.g: ANDROID_HOME=/Users/zhiwei_zhu/Desktop/soft_android/android-sdk-macosx PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools:$ANDROID_HOME/build-tools/23.0.1 export ANDROID_HOME 3、设置好后 退出保存 －－》 按esc 输入 ：wq

＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊

更新代码

1\查看分支 git branch -a

2、需要切换到对应的分支 git checout 分支名

调试React native 可以试试 adb -s fa435f80 reverse tcp:8081 tcp:8081

git fetch 在新建的一个分支后要切换过去 先执行这一行命令

查看签名信息

keytool -list -v -keystore ./debug.keystore -storepass

https://registry.npm.taobao.org/ https://registry.npmjs.org/

react-native init Navtor --verbose //新建react native项目时,加上最后面的 --verbose 可以看到详情

https://jcenter.bintray.com 下载aar arr包






3、切换
git checkout 分支名 切换到指定的分支

4、合并
git merge 分支名 直接合并
注：此操作是将“分支名”指示的分支合并到当前所在的分支，所以合并前必须切换到目标分支。
git cherry-pick commitID 拣选合并
注：将某次特定提交合并到当前分支，首先合并前必须切换到目标分支。

检查分支是否合并到当前分支
git branch ––merged
注：查看已经合并到当前分支的所有分支。
git branch ––no-merged
注：查看还没有合并到当前分支的所有分支。

5、删除私有分支
git branch –d/-D 分支名 
注：此操作”-d”删除分支前会检查分支中的内容是否都已经合并到其他分支，如果没有，则命令不执行;
”-D”不进行检查，直接删除分支。

--------------------
六、跟远程库的交互操作

在Git中，HEAD是一个特别的指针，指向你正在工作的本地分支。一般来说当前分支就是master
origin 是默认的远程版本库的名字,一般大家都这么用,你也可以取其他名字比如thisIsNotOrigin什么的,在.git/config中有相应的设置

git remote -v 查看远程仓库
git remote add [name] [url] 添加远程仓库
git pull [remoteName] [localBranchName] 拉取远程仓库
例：git pull origin master 将远程origin仓库代码更新到本地的master分支
git push [remoteName] [localBranchName] 推送远程仓库：
例：git push origin HEAD:master 提交本地分支作为远程master分支

例子1：从远程仓库获取最新版本到本地
1、git remote -v 查看远程仓库
2、git fetch origin master:temp 从远程origin仓库的master分支下载代码到本地并新建一个分支temp
3、git diff temp 比较本地master分支与temp分支的不同
4、git merge temp 合并temp分支到本地master分支
5、git branch -d temp 或者 git branch -D temp 删除temp分支
6、git fetch -p 用来同步远端的分支信息，删除本地远端列表中远端服务器已经删除的分支

--------------------

df -lh 查看剩余空间

git show 615f37247cf821bf80c519d6b9b7423ef743ce19>~/ggg.txt 把提交的详细信息打印到ggg.tex文件上




















