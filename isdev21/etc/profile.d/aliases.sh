# Some good standards, which are not used if the user
# creates his/her own .bashrc/.bash_profile

# --show-control-chars: help showing Korean or accented characters
alias ls='ls -F --color'
alias ll='ls -l'
alias la='ll -a'
alias rm='rm -i'
alias vi='vim'
alias code='code.cmd'
#alias javac='javac -J-Dfile.encoding=UTF-8'
#alias java='java -Dfile.encoding=UTF-8'
if [ $BASH = "/usr/bin/bash" ]; then
    alias javac='javac -encoding UTF-8'
    alias java='java -Dfile.encoding=SHIFT-JIS'
else
    alias javac='javac -J-Dfile.encoding=UTF-8'
    alias java='java -Dfile.encoding=UTF-8'
fi


case "$TERM" in
xterm*)
	# The following programs are known to require a Win32 Console
	# for interactive usage, therefore let's launch them through winpty
	# when run inside `mintty`.
	for name in node ipython php php5 psql python2.7
	do
		case "$(type -p "$name".exe 2>/dev/null)" in
		''|/usr/bin/*) continue;;
		esac
		alias $name="winpty $name.exe"
	done
	;;
esac
