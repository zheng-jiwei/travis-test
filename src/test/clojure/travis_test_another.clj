(ns test.clojure.travis-test-another
    (:require
      [ecsite.storefront :as sf]
	  [clojure.test :refer :all]
	  [clojure.string :as str]
      )
    )

(deftest ^:test103 test-1
	(let [str_64 (sf/id-to-base64 "category" 1)]
 		(is (= "category" (-> (sf/base64-to-id str_64) (str/split #"\/") (get 3))))
	)
)

