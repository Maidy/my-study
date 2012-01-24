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

;; (step [1 2 3 4]) ;; > [1 [2 [3 [4 []]]]]

(defn rec-step [[x & xs]]
  (if x
    [x (rec-step xs)]
    []))


;; lazy-seq
;; rest / next
;; high-order function
;; don't hold head

(defn lz-rec-step [s]
  (lazy-seq
   (if (seq s)
     [(first s) (lz-rec-step (rest s))]
     [])))

; (def very-lazy (-> (iterate #(do (print \.) (inc %)) 1) rest rest rest))
; (def less-lazy (-> (iterate #(do (print \.) (inc %)) 1) next next next))



