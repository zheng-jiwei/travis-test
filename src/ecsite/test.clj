(ns ecsite.test
    (:require [compojure.core :refer :all]
      [compojure.route :as route]
      [clojure.data.json :as json]
      [clojure.edn :as edn]
      [ecsite.handler :as eh]
      [ecsite.storefront :as sf]
      [clojure.java.io :as io]
      )
    )

(defn test-one[]
  (prn "### test-one" (sf/server-query "/v2/shipping/zones"))
)

(defn test-two[]
  (prn "### test-two" (sf/parse-token-info "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJiaWdjb21tZXJjZSIsImV4cCI6MTU4NjM0NjUyMiwiY3VzdG9tZXJfaWQiOiIxIiwiZW1haWwiOiJ6aGVuZy5qaXdlaSswMDFAY29ubmVjdHkuY28uanAifQ.2ScGqKczUxKIFgnRW7_z_k-jn2aREMe7Lnaix0EX9tU" 1))
)

(defn -main[]
  ((eh/get-store-config (fn[in] (test-one) (test-two))) {})
 )

