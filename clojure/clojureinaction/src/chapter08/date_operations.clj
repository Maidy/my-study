(ns chapter08.date-operations
  (:import (java.text.SimpleDateFormat)
	   (java.util.GregorianCalendar)))
	   
(defn date [date-string]
  (let [f (java.text.SimpleDateFormat. "yyyy-MM-dd")
	d (.parse f date-string)]
    (doto (java.util.GregorianCalendar. )
      (.setTime d))))

(defn day-from [d]
  (.get d (java.util.Calendar/DAY_OF_MONTH)))

(defn month-from [d]
  (inc (.get d (java.util.Calendar/MONTH))))

(defn year-from [d]
  (.get d (java.util.Calendar/YEAR)))

(defn as-string [d]
  (let [y (year-from d)
	m (month-from d)
	d (day-from d)]
    (str y "-" (pad m) "-" (pad d))))

(defn pad [n]
  (if (< n 10) (str "0" n) n))