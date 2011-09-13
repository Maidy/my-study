(ns pp.hammingnumber
  [:use clojure.contrib.math])

(def integers (iterate inc 1))
(defn divide? [num div] (zero? (rem num div)))
(def hamming-numbers  (cons 1
                            (filter #(or (divide? % 2)
                                         (divide? % 3)
                                         (divide? % 5)) integers)))

;; sicp 3.56
(defn my-merge [s1 s2]
  (lazy-seq
   (cond (not (seq s1)) s2
         (not (seq s2)) s1
         :else (let [s1i (first s1)
                     s2i (first s2)]
                 (cond (< s1i s2i)
                       (cons s1i (my-merge (rest s1) s2))
                       (> s1i s2i)
                       (cons s2i (my-merge s1 (rest s2)))
                       :else
                       (cons s1i (my-merge (rest s1) (rest s2))))))))

;; 삑 이건 scheme이 아니자나.
;; (def hn (cons 1 (my-merge (map #(* 2 %) hn)
;;                           (my-merge (map #(* 3 %) hn)
;;                                     (map #(* 5 %) hn)))))
