(ns test.storefront
    (:require
	  [ecsite.common.other :as co]
	  [clojure.string :as str]
	  [clojure.test :refer :all]
    [ecsite.storefront :as es]
      )
      (:import
        (java.util Date TimeZone Locale Calendar)
      )
    )

(def store_setting (ref {
	"shop_name" "store-354",
	"shop_cache" "7xuqbyzkli",
	"access_token" "7szy414j7vqgcloog7qq15idhfc7ut9",
	"client_id"  "9woozsildiyjkk9x3inyrc4r0nki3cu",
	"client_secret" "968f49b8a492a50104e19ecd373a2952e7e1ab86f1d905158b3da1fb589b796c"
}))

(deftest ^:user002 test-id-to-base64
  (let [str64 (es/id-to-base64 "type2" 1)
        result (es/base64-to-id str64)
        result (str/split result #"\/")]
    (testing
      (= "type2" (get result 3))
      (= "1" (get result 4))
     )
    )
)

(deftest ^:user002 test-verify-token-over-expires
  (binding [es/store_setting store_setting]
      (let [token (es/create-jwt {:a 1 :b 2} (Date. (- (.getTime (Date.)) (* 24 3600 1000))))
            result (es/verify-jwt token)]
        (is (:error result))
        )
    )
  )

(deftest ^:user002 test-verify-token-by-value
  (binding [es/store_setting store_setting]
      (let [token (es/create-jwt {:a 1 :b 2} (Date. (+ (.getTime (Date.)) (* 24 3600 1000))))
            result (es/verify-jwt token)]
        (testing
          (= 1 (:a result))
          (= 2 (:b result))
         )
        )
    )
  )
