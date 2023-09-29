# isdev
## リポジトリの内容
- このリポジトリの内容はBYOD下を想定したポータブルなSpring Boot開発環境である
- 学生が解く課題を保存するフォルダ(vscode設定ファイル付き)，PortableGit内の独自コマンド，PortableGitのバイナリを実行するためのオプション付きexeを作成するフォルダの3つをこのリポジトリで管理する
- 想定するプログラミング演習の言語はJava
- 実行環境はAmazon Correto11＋PortableGit+Visual Studio Code+Gradle




## 演習環境のセットアップ
### vscodeの拡張機能の追加
- Debugger for Java
- EvilInspector
- Gradle Language Support
- Japanese Language Pack
- Language Support for Java
- Spring Initializr Java Support
- Spring Boot Tools
- Local History

### 要変更(Javaから)
- (zipwork.sh)
- update_local_diff.sh
- nsswitch.conf
- デフォルトブランチはmaster (init git repo等複数のコマンドが影響を受けるため．要検討）
- .project
- settings.json(2つ)
- course*

## 課題
#### bashターミナルでjavacのエラーメッセージが文字化ける
- 下記修正
- javac↓を/usr/local/binに追加する
```
#!/bin/sh
( (/c/oit/${BYOD}/`/usr/local/bin/coursejdk`/bin/javac -encoding utf8 "$@" | nkf 1>&3) 2>&1 | nkf 1>&2) 3>&1
```
- export PATH=$PATH:/c/oit/${BYOD}/`/usr/local/bin/coursejdk`/bin/ にbash_profile.shのL22を修正する
- aliases.shのL13~19をコメントアウトする

#### git-bashでjavaのエラーメッセージが文字化ける
- `export GRADLE_OPTS="-Dfile.encoding=utf-8"` をbash_profileに追加して解決した
