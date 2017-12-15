#!/bin/bash
NginxServer='http://127.0.0.1'
Check_Nginx_Server()
{

    http_status_code=$(curl -m 5 -s -i  -w %{http_code} -o /tmp/nginxlog.log $NginxServer)
    if [ $http_status_code -eq 000 -o $http_status_code -ge 500 ];then
    	dat= date +"%Y-%m-%d %p %T"
        echo "check http server error \nhttp_status_code is"  $http_status_code "时间 : " $dat >> /tmp/nginxlog.log
    else
		http_content=$(curl -s ${NginxServer}) 
		echo "service status ok\n"$http_content
    fi
}

Check_Nginx_Server