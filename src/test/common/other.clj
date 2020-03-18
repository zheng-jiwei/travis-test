(ns test.common.other
    (:require
	  [ecsite.common.other :as co]
	  [clojure.string :as str]
	  [clojure.test :refer :all]
      )
      (:import
        (java.util Date TimeZone Locale Calendar)
        (java.text DateFormat)
      )
    )

(deftest ^:user002 test-gtm-string
  (let [df (DateFormat/getDateInstance DateFormat/LONG, Locale/US)
        tm (.parse df "March 18, 2018")
        result (co/get-gmt-string tm "YYYY-MM-dd-HH")]
    (prn result)
    (testing "error"
        (= "2018" (get (str/split result #"\-") 0))
        (= "03" (get (str/split result #"\-") 1))
        (= "17" (get (str/split result #"\-") 2))
        (= "15" (get (str/split result #"\-") 3))
        )
    )
)
