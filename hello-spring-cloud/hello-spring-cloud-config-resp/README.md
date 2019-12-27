# 命令行指令

## Git 全局设置

git config --global user.name "jzh"

git config --global user.email "813997065@qq.com"

## 创建新版本库

git clone ssh://git@192.168.56.101:2222/spring-cloud/spring-cloud-config.git

cd spring-cloud-config

touch README.md

git add README.md

git commit -m "add README"

git push -u origin master

## 已存在的文件夹

cd existing_folder

git init

git remote add origin ssh://git@192.168.56.101:2222/spring-cloud/spring-cloud-config.git

git add .

git commit -m "Initial commit"

git push -u origin master

## 已存在的 Git 版本库

cd existing_repo

git remote add origin ssh://git@192.168.56.101:2222/spring-cloud/spring-cloud-config.git

git push -u origin --all

git push -u origin --tags