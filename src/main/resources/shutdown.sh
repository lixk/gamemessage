#!/bin/sh
gamemessagepid=`ps aux|grep jar|grep gamemessage.jar|awk '{print $2}'`
echo "正在停止gamemessage $gamemessagepid......"
kill -9 "$gamemessagepid"
echo "服务已停止......"
