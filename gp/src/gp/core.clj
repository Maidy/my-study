(ns gp.core)

(def *operators* '[+ - * /])

(defn random-elt [seq]
  (let [l (.length seq)]
    (when (> l 0)
      (nth seq (.nextInt (java.util.Random.) l)))))

;; (op n1 n2) form을 만드는데 conj 사용.
;; 실제 data structure가 무엇인지에 영향을 받는 프로시저라 적절하지 않다.
(defn random-form [operators & [max-depth]]
  (let [max-depth (if (nil? max-depth) 4 max-depth)
	random #(* (.nextFloat (java.util.Random.)) %)]
    (loop [result [(random-elt operators)]
	   x 2]
      (if (zero? x)
	(seq result)
	(recur (conj result
		     (let [rn (random 100)]
		       (if (> max-depth 0)
			 (cond (< rn 50) (random-form operators (- max-depth 1))
			       (< rn 75) (random 10.0)
			       true '=INPUT=)
			 (cond (< rn 50) (random 10.0)
			       true '=INPUT=))))
	       (- x 1))))))
;; 이건 grow method. full method로 만드는건 숙제!!!

;; 정말 이상하다.
(defn run-form [form input]
  (try
    ((eval (cons 'fn (cons '[=INPUT=] (list form)))) input)
    (catch ArithmeticException _ nil)))

(defn create-initial-population [operators & [size]]
  (loop [result []
	 count (if (nil? size) 100 size)]
    (if (> count 0)
      (recur (conj result (random-form operators)) (- count 1))
      result)))

;; form : random-form으로 만들어진 function
;; fitness-fn : 정확한 값을 반환하는 function
;; test-input : 입력값, sequence
(defn fitness [form fitness-fn test-inputs]
  (let [items (loop [inputs (seq test-inputs)
		     results []]
		(if (nil? inputs)
		  results
		  (let [output (run-form form (first inputs))]
		    (if (nil? output)
		      nil
		      (let [target (fitness-fn (first inputs))
			    difference (. Math abs (- output target))
			    fitness (/ 1.0 (+ 1 difference))]
			(recur (next inputs) (conj results fitness)))))))]
    (if-not (nil? items) (reduce * items))))
;; 이것도 코드가 구리다... 쩝. 이게 최선인가?
;; fitness는 차이를 0~1 값 사이로 바꾸는 일인듯.

;; circle area
(defn area [r] (* (. Math PI) r r))

