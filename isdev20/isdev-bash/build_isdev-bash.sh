./rsrc.exe -ico isdev-bash.ico -o isdev-bash.syso
GOOS=windows GOARCH=amd64 go build -ldflags -H=windowsgui