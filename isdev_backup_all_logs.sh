#!/bin/bash
# source ./backup_all_logs.sh
# assign meibo data to var line, step by step
# ファイル末尾に改行がなければ追加して結合
COURSE_NAME="isdev20"
mkdir -p ~/students/${COURSE_NAME}/
MEIBO=`awk 1 ./students_list/IS.tsv`
while read line
do
  echo $line | grep -v '^#.*' > /dev/null
  if [ $? -eq 0 ];then
    row=(`echo ${line}`)
    #rowの先頭が小文字のtの場合は教員
    if [[ $row =~ ^(t) ]];then
        sid=${row[0]}
        rsync -av /home/teachers/$sid/kadai/${COURSE_NAME}/ ~/students/${COURSE_NAME}/$sid --max-size=1M
    else
        sid=`echo e1${row[0],,}`
        rsync -av /home/students/$sid/kadai/${COURSE_NAME}/ ~/students/${COURSE_NAME}/$sid --max-size=1M
    fi
  fi
done <<END
$MEIBO
END
cd ~/students/${COURSE_NAME}/;tar cjvf - ./ | ssh java_staff@150.89.223.126 "cat > /home/java_staff/backup/logs_`whoami`_`date +%y%m%d%H%M`.tar.bz"