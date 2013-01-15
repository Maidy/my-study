(define member?
  (lambda (a lat)
    (cond
     ((null? lat) #f)
     (else
      (or (eq? a (car lat))
          (member? a (cdr lat)))))))

(define two-in-a-row?
  (lambda (lat)
    (cond
     ((null? lat) #f)
     (else
      (or (is-first? (car lat) (cdr lat))
          (two-in-a-row? (cdr lat)))))))

(define is-first?
  (lambda (a lat)
    (cond
     ((null? lat) #f)
     (else
      (eq? a (car lat))))))

(define two-in-a-row-2?
  (lambda (lat)
    (cond
     ((null? lat) #f)
     (else
      (is-first-2? (car lat) (cdr lat))))))

(define is-first-2?
  (lambda (a lat)
    (cond
     ((null? lat) #f)
     (else
      (or (eq? a (car lat))
          (two-in-a-row-2? lat))))))

(define two-in-a-row-b?
  (lambda (preceding lat)
    (cond
     ((null? lat) #f)
     (else
      (or (eq? preceding (car lat))
          (two-in-a-row-b? (car lat) (cdr lat)))))))

(define two-in-a-row-3?
  (lambda (lat)
    (cond
     ((null? lat) #f)
     (else
      (two-in-a-row-b? (car lat) (cdr lat))))))

;; chapter 13. Hop, Skip, and Jump
(define intersect
  (lambda (set1 set2)
    (cond
     ((null? set1) '())
     ((member? (car set1) set2)
      (cons (car set1)
            (intersect (cdr set1) set2)))
     (else
      (intersect (cdr set1) set2)))))

(intersect '(tomatoes and macaroni)
           '(macaroni and cheese))

;; set2가 recursive내에서 계속 전달되지 않게 한다.
(define intersect-2
  (lambda (set1 set2)
    (letrec
        ((I (lambda (set)
              (cond
               ((null? set) '())
               ((member? (car set) set2)
                (cons (car set)
                      (intersect (cdr set) set2)))
               (else
                (intersect (cdr set) set2))))))
      (I set1))))

(intersect-2 '(tomatoes and macaroni)
             '(macaroni and cheese))

(define intersectall
  (lambda (lset)
    (cond
     ((null? (cdr lset)) (car lset))
     (else (intersect (car lset)
                      (intersectall (cdr lset)))))))

(intersectall '((a b c)
                (c d x)
                (d c 1)))

(define intersectall-2
  (lambda (lset)
    (cond
     ((null? lset) '())
     ((null? (cdr lset)) (car lset))
     (else (intersect (car lset)
                      (intersectall (cdr lset)))))))

(intersectall-2 '())
(intersectall-2 '((a b c)
                  (c d x)
                  (d c 1)))

(define intersectall-3
  (lambda (lset)
    (letrec
        ((A (lambda (lset)
              (cond
               ((null? (cdr lset)) (car lset))
               (else (intersect (car lset)
                                (A (cdr lset))))))))
      (cond
       ((null? lset) '())
       (else (A lset))))))

(intersectall-3 '())
(intersectall-3 '((a b c)
                  (c d x)
                  (d c 1)))

;; (define intersectall-4
;;   (lambda (lset)
;;     (letcc hop
;;            (letrec
;;                ((A (lambda (lset)
;;                      (cond
;;                       ((null? (car lset)) (hop '()))
;;                       ((null? (cdr lset)) (car lset))
;;                       (else (intersect (car lset)
;;                                        (A (cdr lset))))))))
;;              (cond
;;               ((null? lset) '())
;;               (else (A lset)))))))

;; mit scheme에서는 letcc 없다. 아래와 같이.
(define intersectall-4
  (lambda (lset)
    (call-with-current-continuation
     (lambda (hop)
       (letrec
           ((A (lambda (lset)
                 (cond
                  ((null? (car lset)) (hop '()))
                  ((null? (cdr lset)) (car lset))
                  (else (intersect (car lset)
                                   (A (cdr lset))))))))
         (cond
          ((null? lset) '())
          (else (A lset))))))))

;; lambda 내에서 무슨 일을 하던 (hop) 하면 인자를 반환하여 끝낸다.
;; 위에서처럼 recursion이 진행되는 동안 break 하기 위해 사용
(call-with-current-continuation (lambda (hop) (hop '(a))))
;; hop 없이 끝나면 해당 값을 반환한다.
(call-with-current-continuation (lambda (hop) '(a)))

(intersectall-4 '((a b c)
                  (c)
                  (d c 1)))

;; set2 empty면 바로 ()를 반환. 이제 set1이나 set2 중 하나라도 ()이면
;; 바로 종료된다.
(define intersect-3
  (lambda (set1 set2)
    (letrec
        ((I (lambda (set)
              (cond
               ((null? set) '())
               ((member? (car set) set2)
                (cons (car set)
                      (intersect (cdr set) set2)))
               (else
                (intersect (cdr set) set2))))))
      (cond
       ((null? set2) '())
       (else
        (I set1))))))
