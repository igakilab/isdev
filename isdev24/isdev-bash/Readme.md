# bash.exeを呼び出すgoプログラム
## ビルド環境設定
- msys2 portableをダウンロードして展開
  - https://sourceforge.net/projects/mwayne/files/MSYS2Portable/
- golang環境を導入
  - 参考：http://takaya030.hatenablog.com/entry/2018/01/18/230105
```
$ pacman -S mingw-w64-x86_64-go
$ pacman -S git
```
- 以下を`C:\byod\MSYS2Portable\App\msys32\etc\bash.bashrc` の末尾に追加
```
export GOROOT=/mingw64/lib/go
export GOPATH=/mingw64
export PATH=/mingw64/bin:$PATH
```
- icon作成環境の導入
  - .icoファイルを用意してrsrcをbuild
    - 参考：http://blog.y-yuki.net/entry/2017/04/22/000000

```
$ go get -v github.com/akavel/rsrc
$ cd %GOPATH%/src/github.com/akavel/rsrc
$ go build
```

## ICOファイルの作成
- http://flat-icon-design.com/
  - このあたりで透過PNG画像をDLし，↓のサイトでICOファイルに変換する
- https://service.tree-web.net/icon_converter/


## ビルド
- ↑でビルドしたrsrc.exeを*.goファイルと同じディレクトリに置き，msys2の環境で `build_isdev-bash.sh`を実行する
  - `sh build_isdev-bash.sh`
- このとき，`isdev-bash.go` のL15をPortableGitのバージョンに合わせて修正すること
