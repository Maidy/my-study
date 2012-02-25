(ns clj.core
  (:use midje.sweet))

(unfinished )

(defn divn? [m n]
  (zero? (rem n m)))
(def div5? (partial divn? 5))
(def div3? (partial divn? 3))

(fact "divide by 3?"
  (div3? 3) => true)

(fact "divide by 5?"
  5 => div5?
  10 => div5?)

(defn contain-m-in-n? [m n]
  (boolean (some (hash-set (first (str m))) (seq (str n)))))
(def contain3? (partial contain-m-in-n? 3))
(def contain5? (partial contain-m-in-n? 5))

(fact
 (contain3? 34) => true
 (contain3? 13) => true
 (contain5? 53) => true
 (contain5? 35) => true)

(defn fizzbuzz [n]
  (cond
   (and (div3? n) (div5? n)) "FizzBuzz"
   (or (div3? n) (contain3? n)) "Fizz"
   (or (div5? n) (contain5? n)) "Buzz"
   :else (str n)))

;.;. A clean boundary between useful abstractions and the grubby code that
;.;. touches the real world is always a good thing. -- Ron Jeffries
(fact
  (fizzbuzz 1)  => "1"
  (fizzbuzz 2)  => "2"
  (fizzbuzz 3)  => "Fizz"
  (fizzbuzz 13) => "Fizz"
  (fizzbuzz 32) => "Fizz"
  (fizzbuzz 5)  => "Buzz"
  (fizzbuzz 52) => "Buzz"
  (fizzbuzz 15) => "FizzBuzz"
  (fizzbuzz 35) => "Fizz")

(defn FizzBuzz [n]
  (map fizzbuzz (range 1 (inc n))))
