(ns joc.ch6.mylazy)

(defn xconj [t v]
  (cond (nil? t) { :val v :L nil :R nil }
        (< v (:val t)) {:val (:val t)
                        :L (xconj (:L t) v)
                        :R (:R t)}
        :else {:val (:val t)
               :L (:L t)
               :R (xconj (:R t) v)}))

(def t1 (xconj nil 5))
(def t1 (xconj t1 3))
(def t1 (xconj t1 2))

(defn xseq [t]
  (when t
    (concat (xseq (:L t)) [(:val t)] (xseq (:R t)))))


(println "start")

(def very-lazy (-> (iterate #(do (print \.) (inc %)) 1)
                   rest rest rest))
(println "---")
(def less-lazy (-> (iterate #(do (print \.) (inc %)) 1)
                   next next next))
(println "end")


;; (step [1 2 3 4]) ;; > [1 [2 [3 [4 []]]]]

(defn rec-step [[x & xs]]
  (if x
    [x (step xs)]
    []))
