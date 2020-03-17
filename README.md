### ファイル構成の説明

- project.cljの変更
下記のコードをproject.cljに追加します。（:test-1やtest-2を任意のkeywordへ変更できます）
```
  :test-paths ["src"]
  :test-selectors {:default (complement :all)
   　　　　　    　:test-1 :test-1
  　　　　　    　 :test-2 :test-2
　　　　　　　　:all (fn[_] true)}
```

- .travis.yml の追加
scriptのコマンドはlein test $T_KEYで環境変更を貰って lein test で実施します。

- travis-ci.comの管理画面に変数の追加　
  - 管理画面右上のiconをクリック　＞　settings　＞　travis-test（branch）のsettings　＞　Environment Variables
  - NAME = T_KEY　　BRANCH=travis-test（選択しなければ全部branchでもよいです）       
  - VALUE=:zheng （「:」が付いてます。このkeywordは project.cljに定義したtest-selectorsの一つです）
  または VALUE=test.clojure.travis-test （namespaceを設定する場合、travis_test.cljにあるdeftestを全部実施されます）


### テストコードの説明

- テスト実施できる粒度下記のように分けられています
  - lein test
    - parameterなしでdefaultを実施したら、:test-pathsで指定したパス配下にすべてcljファイルにある deftest ファンクションをテストします。
  - lein test test.clojure.travis-test
    - テストファイルのnamespaceを指定したら、該当namespaceに定義した deftestをすべてテストします。
  - lein test :test001
    - keywordを指定したら、deftest ^:test001 のような該当keywordを明確標記したファンクションをテストします。
  - lein test :only test.clojure.travis-test/function-name 　
    - namespaceとfunction-nameを指定したら、指定したnamespaceにある function-nameのみテストします。
  - 組み合わせテストも可能です
```
   lein test :test001 :test002
   //:test001 と :test002 を標記した deftestを実施する

   lein test :test103 test.clojure.travis-test
   //:test103を標記したdeftestとnamespace test.clojure.travis-testにあるすべてdeftestを実施する

   lein test test.clojure.travis-test test.clojure.travis-test-another
   //namespace test.clojure.travis-testとtest.clojure.travis-test-anotherにあるすべてdeftestを実施する
```

- テスト実施を分けるため、project.cljに下記のようなコードを追加します
```
  :test-paths
  :test-selectors {:default (complement :all)
  　　　　　    　 :test001 :test001
  　　　　　    　 :test002 :test002
  　　　　　    　 :test003 :test003
　　　　　　　　:all (fn[_] true)}
```

- テストファンクションの構成

  ```
  　(deftest ^keyword function-name)
   例：
  　(deftest ^:test001 function-A
      (is (= 2 1))
     )
  ```
  - keyword は test-selectorsに定義したkeywordの一つを選べる
　- function-nameは任意
