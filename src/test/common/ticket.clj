(ns test.common.ticket
    (:require
    [ecsite.common.ticket :as ct]
	  [clojure.test :refer :all]
	  [clojure.string :as str]
      )
    )

(deftest ^:user001 test-ticket-discount-65-years-old-weekday
  ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
  (testing
    (= 0.5 (ct/get-ticket-discount-by-condition 65 1 false true))
    (= 0.5 (ct/get-ticket-discount-by-condition 65 1 false false))
       )
  )

(deftest ^:user001 test-ticket-discount-65-years-old-holiday-with-coupon
  ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
  (is (= 0.1 (ct/get-ticket-discount-by-condition 65 1 true true)))
  )

(deftest ^:user001 test-ticket-discount-65-years-old-holiday-without-coupon
  ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
  (is (= 0 (ct/get-ticket-discount-by-condition 65 1 true false)))
  )

(deftest ^:user001 test-ticket-discount-65-years-old-weekend-with-coupon
  ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
  (is (= 0.1 (ct/get-ticket-discount-by-condition 65 6 false true)))
  )

(deftest ^:user001 test-ticket-discount-65-years-old-weekend-without-coupon
  ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
  (is (= 0 (ct/get-ticket-discount-by-condition 65 6 false false)))
  )

(deftest ^:user001 test-ticket-discount-14-years-old-weekend
  ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
  (testing
    (= 0.2 (ct/get-ticket-discount-by-condition 14 6 false true))
    (= 0.2 (ct/get-ticket-discount-by-condition 14 6 false false))
    (= 0.2 (ct/get-ticket-discount-by-condition 14 6 false true))
    (= 0.2 (ct/get-ticket-discount-by-condition 14 6 false false))
     )
)

(deftest ^:user001 test-ticket-discount-14-years-old-holiday
  ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
  (testing
    (= 0.2 (ct/get-ticket-discount-by-condition 14 1 true true))
    (= 0.2 (ct/get-ticket-discount-by-condition 14 1 true false))
    (= 0.2 (ct/get-ticket-discount-by-condition 14 6 true true))
    (= 0.2 (ct/get-ticket-discount-by-condition 14 6 true false))
     )
  )

(deftest ^:user001 test-ticket-discount-14-years-old-weekday
  ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
  (testing
    (= 0.3 (ct/get-ticket-discount-by-condition 14 1 false true))
    (= 0.3 (ct/get-ticket-discount-by-condition 14 1 false false))
    (= 0.3 (ct/get-ticket-discount-by-condition 14 1 true true))
    (= 0.3 (ct/get-ticket-discount-by-condition 14 1 true false))
    )
  )

(deftest ^:user001 test-ticket-discount-15-years-old-weekday
  ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
  (testing
    (= 0.3 (ct/get-ticket-discount-by-condition 15 1 false true))
    (= 0.3 (ct/get-ticket-discount-by-condition 15 1 false false))
    (= 0.3 (ct/get-ticket-discount-by-condition 15 1 true true))
    (= 0.3 (ct/get-ticket-discount-by-condition 15 1 true false))
    )
  )

(deftest ^:user001 test-ticket-discount-15-years-old-weekend-with-coupon
  ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
	(is (= 0.1 (ct/get-ticket-discount-by-condition 15 6 false true)))
)

(deftest ^:user001 test-ticket-discount-15-years-old-weekend-without-coupon
  ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
	(is (= 0 (ct/get-ticket-discount-by-condition 15 6 false false)))
)

(deftest ^:user001 test-ticket-discount-15-years-old-holiday-with-coupon
  ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
	(is (= 0.1 (ct/get-ticket-discount-by-condition 15 1 true true)))
)

(deftest ^:user001 test-ticket-discount-15-years-old-holiday-without-coupon
  ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
	(is (= 0 (ct/get-ticket-discount-by-condition 15 1 true false)))
)

(deftest ^:user002 test-ticket-discount-15-years-old-weekday
  ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
	(testing
    (= 0.3 (ct/get-ticket-discount-by-condition 15 1 true true))
    (= 0.3 (ct/get-ticket-discount-by-condition 15 1 true false))
    (= 0.3 (ct/get-ticket-discount-by-condition 15 1 false false))
    (= 0.3 (ct/get-ticket-discount-by-condition 15 1 false true))
    )
)
