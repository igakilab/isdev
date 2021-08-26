# add ~/.bash_profile if needed for executing ~/.bashrc
if [ -e ~/.bashrc -a ! -e ~/.bash_profile -a ! -e ~/.bash_login -a ! -e ~/.profile ]; then
  printf "\n\033[31mWARNING: Found ~/.bashrc but no ~/.bash_profile, ~/.bash_login or ~/.profile.\033[m\n\n"
  echo "This looks like an incorrect setup."
  echo "A ~/.bash_profile that loads ~/.bashrc will be created for you."
  cat >~/.bash_profile <<-\EOF
	# generated by Git for Windows
	test -f ~/.profile && . ~/.profile
	test -f ~/.bashrc && . ~/.bashrc
	EOF
fi

export CLASSNAME=`/usr/local/bin/course -v`

PATH=/mingw64/bin:/usr/local/bin:/usr/bin:/bin:/usr/bin/core_perl:/usr/bin/vendor_perl
export LANG="ja_JP.UTF-8"
export COURSEYEAR=`/usr/local/bin/course`

mkdir -p "$USERPROFILE/oithomes/$CLASSNAME/kadai/$COURSEYEAR/.log/"

export PATH=/c/oit/$COURSEYEAR/`/usr/local/bin/coursejdk`/bin/:$PATH
export PATH=/c/oit/$COURSEYEAR/`/usr/local/bin/coursevscode`/:$PATH
export PATH=/c/oit/$COURSEYEAR/`/usr/local/bin/coursegradle`/bin/:$PATH
export HOME=$(cd "$USERPROFILE\oithomes\\${CLASSNAME}" && pwd)

# Hidden setting
/c/Windows/System32/attrib.exe +H ~/kadai/${COURSEYEAR}/.log 1>> "$HISTFILE" 2>&1

export HISTDATE=`date +'%Y%m'`
export HISTFILE="$HOME/kadai/$COURSEYEAR/.log/.${CLASSNAME}_bash_history.$HISTDATE"
HISTTIMEFORMAT="%Y/%m/%dT%T%z "
export PROMPT_COMMAND='echo -e "res:$?\tpwd:$PWD" >> ~/kadai/$COURSEYEAR/.log/.${CLASSNAME}_bash_history.$HISTDATE;history -a'

# 古いjava_bash_historyはローカルから削除（6ヶ月以上前）
this_month=`date +'%Y%m'`
last_month=`date -d "${this_month}01 5 month ago" +'%Y%m'`
find ~/kadai/`/usr/local/bin/course`/ -name ".${CLASSNAME}_bash_history*" -not -name ".${CLASSNAME}_bash_history.${this_month}" -not -name ".${CLASSNAME}_bash_history.${last_month}" -exec rm {} \;

# /homeの作成
export MSYS=winsymlinks:lnk
if [ ! -e "/home/${USERNAME}" ]; then
  mkdir -p /home
  ln -s "${HOME}" "/home/${USERNAME}"
fi

export JAVA_HOME=/c/oit/$COURSEYEAR/`/usr/local/bin/coursejdk`/
export GRADLE_OPTS="-Dfile.encoding=utf-8"
