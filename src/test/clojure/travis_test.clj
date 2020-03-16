(ns test.clojure.travis-test
    (:require
      [ecsite.storefront :as sf]
	  [clojure.test :refer :all]
      )
    )

(deftest ^:zheng test-1
	(prn "### 1")
)

(deftest ^{:zheng true} test-2 
	(let [result (sf/parse-token-info "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJiaWdjb21tZXJjZSIsImV4cCI6MTU4NjM0NjUyMiwiY3VzdG9tZXJfaWQiOiIxIiwiZW1haWwiOiJ6aGVuZy5qaXdlaSswMDFAY29ubmVjdHkuY28uanAifQ.2ScGqKczUxKIFgnRW7_z_k-jn2aREMe7Lnaix0EX9tU" 1)]
		(prn "#### 2 " result)
		)
)

(deftest ^:test test-3
	(prn "### 3")
)
