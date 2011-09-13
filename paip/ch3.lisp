;; ch3. Overview of lisp

;; p55.

;; ex3.1
;; (let ((var value) ... ) body) == ((lambda (vars...) body) value...)
;; (let* ((var value) ...) body) ==> ???
((lambda (x y)
   (+ x y))
 40
 (+ 1 1))

(let ((x 40)
      (y (+ 1 1)))
  (+ x y))

;; (let ((x 40)
;;       (y (* x 10)))
;;   (+ x y)) ;; => ERROR!

(let* ((x 6)
       (y (* x x)))
  (+ x y)) ;; => 6 + 6*6 = 42

((lambda (x)
   ((lambda (y)
      (+ x y))
    (* x x)))
 6)

;; p57. Functions and Special Forms for Repetition
(defun length1 (list)
  (let ((len 0))
    (dolist (element list)
      (incf len))
    len))

(defun length1-1 (list)
  (let ((len 0))
    (dolist (element list len)
      (incf len))))

(mapcar #'(lambda (x) (+ x 1)) '(1 2 3 4 5))
(mapc #'(lambda (x) (+ x 1)) '(1 2 3 4 5)) ;; mapc => side-effect

(defun length2 (list)
  (let ((len 0))
    (mapc #'(lambda (element) (incf len)) list)
    len)) ;; compilation warning ???

(defun length3 (list)
  (do ((len 0 (+ len 1))
       (l list (rest l)))
      ((null l) len)
    ;; (print (first l))
    ))

;; p60 보는중
