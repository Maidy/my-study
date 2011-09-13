(ns programming-clojure.ch7)

(defn hello-world [] (println "Programming Clojure - Chapter 7. Macro"))

;; (defn unless [expr form]
;;   (if expr nil form))

;; (defn unless [expr form]
;;   (println "About to test ...")
;;   (if expr nil form))

(defmacro unless [expr form]
  (list 'if expr nil form))

(defmacro bad-unless [expr form]
  (list 'if 'expr nil form))

(defmacro chain
  ([x form] (list '. x form))
  ([x form & more] (concat (list 'chain (list '. x form)) more)))

;; expand process
;; (.. arm getHand)
;; >> (. arm getHand)

;; (.. arm getHand getFinger)
;; >> (.. (. arm getHand) getFinger)
;; >> (. (. arm getHand) getFinger)

;; (.. arm getHand getFinger getHead)
;; >> (.. (. arm getHand) getFinger getHead)
;; >> (.. (. (. arm getHand) getFinger) getHead)
;; >> (. (. (. arm getHand) getFinger) getHead)

(defmacro chain-1
  ([x form] `(. ~x ~form)) 
  ([x form & more] `(chain (. ~x ~form) ~@more)))

;; 아래 틀리다. 뭥미???
;; (macroexpand-1 '(chain arm getHand getFinger))
;; >> (chain (. arm getHand) getFinger)
;; (macroexpand-1 '(chain-1 arm getHand getFinger))
;; >> (programming-clojure.ch7/chain (. arm getHand) getFinger)

;; p.231 매크로 안에서 이름 만들기
;; (bench (str "a" "b"))
;; >> { :elapsed 61000 :result "ab" }

(defmacro bench [expr]
  `(let [start# (System/nanoTime)
         result# ~expr]
     { :elapsed (- (System/nanoTime) start#)
      :result  result# }))

(def slow-calc (delay (Thread/sleep 5000) "done!"))
