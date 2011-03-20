(ns chapter08.date-operations-spec
  (:use chapter08.date-operations)
  (:use clojure.test))

(deftest test-simple-data-parsing
  (let [d (date "2009-1-22")
	d1 (date "2011-12-31")]
    (is (= (day-from d) 22))
    (is (= (month-from d) 1))
    (is (= (year-from d) 2009))
    (is (= (month-from d1) 12))
    (is (= (year-from d1) 2011))))

(deftest test-as-string
  (let [d1 (date "2009-01-22")
	d2 (date "2011-03-15")]
    (is (= (as-string d1) "2009-01-22"))
    (is (= (as-string d2) "2011-03-15"))))

(deftest test-incrementing
  (let [d (date "2011-03-15")
	n-day (increment-day d)
	n-month (increment-month d)
	n-year (increment-year d)]
    (is (= (as-string n-day) "2011-03-16"))
    (is (= (as-string n-month) "2011-04-15"))
    (is (= (as-string n-year) "2012-03-15"))))

(deftest test-decrementing
  (let [d (date "2011-03-15")
	n-day (decrement-day d)
	n-month (decrement-month d)
	n-year (decrement-year d)]
    (is (= (as-string n-day) "2011-03-14"))
    (is (= (as-string n-month) "2011-02-15"))
    (is (= (as-string n-year) "2010-03-15"))))
