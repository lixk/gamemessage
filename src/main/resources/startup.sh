#!/bin/sh
logfile=./logs

if [ ! -d "$logfile" ]; then
        mkdir "$logfile"
fi
echo "正在启动服务......"
nohup /usr/local/jdk1.8.0_73/bin/java -jar ./gamemessage.jar >> "$logfile"/catalina.out 2>&1 &
echo "服务启动完成......"
tail -f "$logfile"/catalina.out