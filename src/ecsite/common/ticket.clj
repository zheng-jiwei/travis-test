(ns ecsite.common.ticket
  (:gen-class)
  (:require
    [clojure.string :as str]
    )
  (:import
    (java.util Date TimeZone Locale Calendar)
    (org.joda.time DateTimeZone DateTime)
	 )
  )

;割引計算ルール
;クーポンをもって 10% OFF
;平日割り引け; 30% OFF
;平日65才以上の割引: 50%OFF
;土日祝日15才未満の割引： 20%OFF
;二つ割引重ねる場合、高い割引を適用する
(defn get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
    (cond
      (< age 15)
           (cond
            (and  (<= weekday 5) (not isHoliday)) 0.3
             :else 0.2
             )
      (>= age 65)
           (cond
             (and  (<= weekday 5) (not isHoliday)) 0.5
             :else (if hasCoupon 0.1 0)
             )
      :else
           (cond
             (and  (<= weekday 5) (not isHoliday)) 0.3
             :else (if hasCoupon 0.1 0)
             )
      )
    )
