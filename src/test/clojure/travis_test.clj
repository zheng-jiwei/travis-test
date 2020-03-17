(ns test.clojure.travis-test
    (:require
      [ecsite.storefront :as sf]
	  [ecsite.handler :as eh]
	  [clojure.data.json :as json]
	  [clojure.string :as str]
	  [clojure.test :refer :all]
      )
    )

; is を使って、テスト項目の中に１つが失敗するので、1 failures、test failed,falseを返す
;expected: (= "category-2" (-> (sf/base64-to-id str_64) (str/split #"\/") (get 3)))
;  actual: (not (= "category-2" "category"))
;false
;Ran 1 tests containing 2 assertions.
;1 failures, 0 errors.
;Tests failed.
(deftest ^:zheng test-1
	(let [str_64 (sf/id-to-base64 "category" 1)
       result (do (is (= "category" (-> (sf/base64-to-id str_64) (str/split #"\/") (get 3))))
       (is (= "category-2" (-> (sf/base64-to-id str_64) (str/split #"\/") (get 3)))))]
       (prn result)
       result
	)
)

; testingを使って、テスト項目の中に１つが失敗するので、0 failures, falseを返す
;    lein test test.clojure.travis-test
;    false
;    Ran 1 tests containing 0 assertions.
;    0 failures, 0 errors.
(deftest ^:zheng test-2
	(let [str_64 (sf/id-to-base64 "category" 1)
       result (testing "error" (= "category" (-> (sf/base64-to-id str_64) (str/split #"\/") (get 3)))
                   (= "category-2" (-> (sf/base64-to-id str_64) (str/split #"\/") (get 3))))]
      (prn result)
      result
	)
)


; testingを使って、テスト項目の中にすべて成功ので、0 failures、trueを返す
;    lein test test.clojure.travis-test
;    true
;    Ran 1 tests containing 0 assertions.
;    0 failures, 0 errors.
(deftest ^:zheng test-3
	(let [str_64 (sf/id-to-base64 "category" 1)
       result (testing "error-" (= "category" (-> (sf/base64-to-id str_64) (str/split #"\/") (get 3)))
                   (= 5 (+ 3 2)))]
    (prn result)
   result
	)
)

;do-report を使って、テスト項目の中に１つ失敗なので、結果に falseがあって、1 failures, test failed
;expected: (= "zheng.jiwei@yahoo.co.jp" (get result "email"))
;  actual: (not (= "zheng.jiwei@yahoo.co.jp" "zheng.jiwei+001@connecty.co.jp"))
;false
;"##" nil
;Ran 1 tests containing 2 assertions.
;1 failures, 0 errors.
;Tests failed.
(deftest ^{:test001 true} test-4
	(let [result (sf/parse-token-info "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJiaWdjb21tZXJjZSIsImV4cCI6MTU4NjM0NjUyMiwiY3VzdG9tZXJfaWQiOiIxIiwiZW1haWwiOiJ6aGVuZy5qaXdlaSswMDFAY29ubmVjdHkuY28uanAifQ.2ScGqKczUxKIFgnRW7_z_k-jn2aREMe7Lnaix0EX9tU" 1)]
      (when (is (= "1" (get result "customer_id")))
        (do-report {:type :error :message "error for "})
        )
	)
)

(deftest ^:test001 test-5
  (let [ret (successful? (sf/get-private-info {}))]
    (prn "### " ret)
    ret
    )
)

(deftest ^:test002 test-6
	(let [result (sf/parse-token-info "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJiaWdjb21tZXJjZSIsImV4cCI6MTU4NjM0NjUyMiwiY3VzdG9tZXJfaWQiOiIxIiwiZW1haWwiOiJ6aGVuZy5qaXdlaSswMDFAY29ubmVjdHkuY28uanAifQ.2ScGqKczUxKIFgnRW7_z_k-jn2aREMe7Lnaix0EX9tU" 1)]
		(testing "checking token"
			(is (= "1" (get result "customer_id")))
			(is (= "zheng.jiwei@connecty.co.jp" (get result "email")))
		)
	)
)
