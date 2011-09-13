(ns pp.two-string-exer)

;; 1) Remove all duplicate characters from a string.
;; Thus, “aaabbb” becomes “ab” and “abcbd” becomes “abcd”.
;; 순서는 어떻게 되지? set으로 구현하니, 정렬된 형태로 나와버린다.
(defn remove-duplicate-char [s]
  (apply str (seq (set s))))

;; 2) Replace all runs of consecutive spaces with a single space.
;; Thus, “a.b” is unchanged and “a..b” becomes “a.b”, using a dot to make the space visible.
(defn remove-consecutive-space [s]
  (apply str
         (reduce
          (fn [r c] (if (and (= c (last r) \space)) r (conj r c)))
          [] (seq s))))
