(ns joc.ch8.macro)

(defn hello-world [] (println "Hello World"))

(defmacro do-until [& clauses]
  (when clauses
    (list `when (first clauses)
          (if (next clauses)
            (second clauses)
            (throw (IllegalArgumentException.
                    "do-until requires an even number of formss")))
          (cons 'do-until (nnext clauses)))))

(do-until
 (even? 2) (println "Even")
 (odd? 3)  (println "Odd")
 (zero? 1) (println "You never see me")
 :lollipop (println "Tuthy thing"))

(defmacro unless [condition & body]
  `(if (not ~condition)
     (do ~@body)))

(defn from-end [s n]
  (let [delta (dec (- (count s) n))]
    (unless (neg? delta)
            (nth s delta))))

(defmacro domain [name & body]
  `{:tag :domain,
    :attrs {:name (str '~name)},
    :content [~@body]})

