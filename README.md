# javadev
## リポジトリの内容
- このリポジトリの内容はBYOD下を想定したポータブルなJava開発環境である
- 学生が解く課題を保存するフォルダ(vscode設定ファイル付き)，PortableGit内の独自コマンド，PortableGitのバイナリを実行するためのオプション付きexeを作成するフォルダの3つをこのリポジトリで管理する
- 想定するプログラミング演習の言語はJava
- 実行環境はAmazon Correto11＋PortableGit+Visual Studio Code．

## 演習環境の準備
### vscode, jdk, portablegitのインストール
- 別途oit-javaYYYYMMDD.exeの最新版をC:\oitにダウンロードし，実行（展開）する
  - 自己解凍ファイル展開後のフォルダ構成が以下のとおりになっていれば良い(X,Y,Zにはバージョン番号が入る)
 
```
C:\oit\javadevYY\vscode-portable-win64-1.XX.Y-Z\app
C:\oit\javadevYY\vscode-portable-win64-1.XX.Y-Z\data
C:\oit\javadevYY\vscode-portable-win64-1.XX.Y-Z\vscode-portable.exe
:
C:\oit\javadevYY\amazonjdk11.XXX\bin
C:\oit\javadevYY\amazonjdk11.XXX\include
C:\oit\javadevYY\amazonjdk11.XXX\jre
C:\oit\javadevYY\amazonjdk11.XXX\lib
:
C:\oit\javadevYY\PortableGit-2.XX.Y-64\bin
C:\oit\javadevYY\PortableGit-2.XX.Y-64\cmd
C:\oit\javadevYY\PortableGit-2.XX.Y-64\git-bash.exe
:
C:\oit\javadevYY\java-bash-2.XX.Y-64.exe
```

### 開発環境セットアップ
- `C:\oit\javaYY\javadev-bash-2.XX.Y-64.exe`を実行する．
  - `C:\Users\{ユーザ名}\oithomes\javadev\kadai\javadevYY\`フォルダが生成される（なお，javaYYのYYにはその年の末尾2桁が入る）．
- 下記に示すとおり，`getlocal`, `getenv` コマンドを順に実行する．
  - `更新は完了しました` と表示されればOK.失敗した等の表示の場合はネットワーク接続を確認の上，コマンドを再実行すること．
```bash
$ getlocal
開発環境データを取得します
ダウンロード中.........完了しました
各種コマンドを更新します．
各種設定を更新します．
更新は完了しました．
$ getenv
~/kadai/javadev20/を更新します．
更新は完了しました．
```
- エクスプローラーで `C:\Users\{ユーザ名}\oithomes\javadev\kadai\javadevYY\` を確認し，下記のようなフォルダやファイルが存在すればOK．

```
.settings
.vscode
.classpath
.project
```

## 開発(学生の立場から)の流れ
### vscodeの起動及びファイル編集
- `C:\oit\vscode-portable-win64-1.XX.Y-Z\vscode-portable.exe` をダブルクリックして起動する
  - ターミナル`C:\oit\javadev-bash-2.XX.Y-64.exe`を起動して，`code`コマンドを入力しても同じものが起動する．
- vscodeからファイル->フォルダを開く->`$HOME\kadai\javadevYY`フォルダを指定する（なお，javaYYのYYにはその年の末尾2桁が入る）．
  - ここで$HOMEは `C:\Users\{ユーザ名}\oithomes\javadev\` を表している．


### javaファイルのコンパイル・実行方法
- ターミナル`C:\oit\java-bash-2.XX.Y-64.exe`を起動する．
  - ターミナルはvscode内でも開ける．「表示->ターミナル」を選択する．
- $HOMEにいる状態でターミナルが開く（はず）ので，`cd kadai/javadevYY`を実行し，Hello.javaなどのJavaファイルが存在するディレクトリに移動し，`javac Hello.java`と実行する．
  - javadevYYのYYには年度の末尾2桁を入れる（2019の場合はjava19）
  - vscode内のターミナルで開いた場合はjavadevYYフォルダが直接開くので，そのままコンパイルを実行する．
- 正常にコンパイルができ，classファイルができたら，`java Work11`と実行すると結果が出力される．

### 開発終了時
- 以下のようにターミナルで`zipwork` コマンドを実行する

```bash
$ zipwork
課題ファイルをアーカイブします
/c/Users/??????/oithomes/javadev に javadev20_200523172917.7z が作成されました．
 javadev20_200523172917.7z ファイルを提出して下さい．
提出後は javadev20_200523172917.7zを削除しても構いません．
```

- 7zファイルが作成されたら，そのファイルを提出する．
  - なお，提出ファイルには開発したソースコード及び各種ログ（ターミナルで実行したコマンドやファイル編集履歴）が含まれている．
  - これらのソースコードやログは収集後，匿名化し，誰のものか分からなくした上でより良い開発環境の構築やプログラミング演習等の授業改善，研究等に利用される．
  