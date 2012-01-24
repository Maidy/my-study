(ns clj.test.core
  (:use [clj.core])
  (:use [clojure.test])
  (:use midje.sweet))

(fact (+ 1 1) => 2)
