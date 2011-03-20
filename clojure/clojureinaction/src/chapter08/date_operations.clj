(ns chapter08.date-operations
  (:import (java.text.SimpleDateFormat)
	   (java.util.GregorianCalendar)))
	   
(defn date [date-string]
  (let [f (java.text.SimpleDateFormat. "yyyy-MM-dd")
	d (.parse f date-string)]
    (doto (java.util.GregorianCalendar. )
      (.setTime d))))

(defn date-from [field]
  (fn [d]
    (let [v (.get d field)] 
      (if (= field java.util.Calendar/MONTH)
	(inc v)
	v))))
(def day-from (date-from java.util.Calendar/DAY_OF_MONTH))
(def month-from (date-from java.util.Calendar/MONTH))
(def year-from (date-from java.util.Calendar/YEAR))

(defn pad [n]
  (if (< n 10) (str "0" n) n))

(defn as-string [d]
  (let [y (year-from d)
	m (month-from d)
	d (day-from d)]
    (str y "-" (pad m) "-" (pad d))))

(defn date-operator [operation field]
  (fn [d]
    (doto (.clone d)
      (.add field (operation 1)))))
(def increment-day (date-operator + java.util.Calendar/DAY_OF_MONTH))
(def increment-month (date-operator + java.util.Calendar/MONTH))
(def increment-year (date-operator + java.util.Calendar/YEAR))
(def decrement-day (date-operator - java.util.Calendar/DAY_OF_MONTH))
(def decrement-month (date-operator - java.util.Calendar/MONTH))
(def decrement-year (date-operator - java.util.Calendar/YEAR))
