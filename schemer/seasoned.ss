(define atom?
  (lambda (x)
    (and (not (pair? x)) (not (null? x)))))

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
                      (I (cdr set))))
               (else
                (I (cdr set)))))))
      (cond
       ((null? set2) '())
       (else
        (I set1))))))

(define intersectall-5
  (lambda (lset)
    (call-with-current-continuation
     (lambda (hop)
       (letrec
           ((A (lambda (lset)
                 (cond
                  ((null? (car lset)) (hop '()))
                  ((null? (cdr lset)) (car lset))
                  (else
                   (I (car lset) (A (cdr lset)))))))
            (I (lambda (s1 s2)
                 (letrec
                     ((J (lambda (s)
                           (cond
                            ((null? s) '())
                            ((member? (car s) s2)
                             (cons (car s) (J (cdr s))))
                            (else (J (cdr s)))))))
                   (cond
                    ((null? s2) '())
                    (else
                     (J s1)))))))
         (cond
          ((null? lset) (hop '()))
          (else (A lset))))))))

(define rember
  (lambda (a lat)
    (letrec
        ((R (lambda (lat)
              (cond
               ((null? lat) '())
               ((eq? a (car lat))
                (R (cdr lat)))
               (else
                (cons (car lat) (R (cdr lat))))))))
      (R lat))))

(define rember-beyond-first
  (lambda (a lat)
    (letrec
        ((R (lambda (lat)
              (cond
               ((null? lat) '())
               ((eq? a (car lat)) '())
               (else
                (cons (car lat) (R (cdr lat))))))))
      (R lat))))

(define rember-upto-last
  (lambda (a lat)
    (call-with-current-continuation
     (lambda (skip)
       (letrec
           ((R (lambda (lat)
                 (cond
                  ((null? lat) '())
                  ((eq? (car lat) a) (skip (R (cdr lat))))
                  (else (cons (car lat)
                              (R (cdr lat))))))))
         (R lat))))))

(rember-upto-last 'cookies
                  '(cookies
                    chocolate mints caramel delight ginger snaps
                    desserts
                    chocolate mousse
                    vanilla ice cream
                    German chocolate cake
                    more cookies
                    gingerbreadman chocolate chip brownies))

(define leftmost
  (lambda (l)
    (cond
     ((atom? (car l)) (car l))
     (else
      (leftmost (car l))))))

(leftmost '((a b) c))
(leftmost '(((a) b) (c d)))
(leftmost '(((a) ()) () (e)))
;; (leftmost '(((() a) ())))
;; > error

(define leftmost-2
  (lambda (l)
    (cond
     ((null? l) '())
     ((atom? (car l)) (car l))
     (else
      (cond
       ((atom? (leftmost-2 (car l))) (leftmost-2 (car l)))
       (else (leftmost-2 (cdr l))))))))

(leftmost-2 '(((() a) ())))

(define leftmost-3
  (lambda (l)
    (cond
     ((null? l) '())
     ((atom? (car l)) (car l))
     (else
      (let
          ((a (leftmost-3 (car l))))
        (cond
         ((atom? a) a)
         (else (leftmost-3 (cdr l)))))))))
(leftmost-3 '(((() a) ())))
;; let과 letcc의 차이점은?
(let
    ((x 10))
  (let
      ((x 20)
       (y x))
    (cons x (cons y '()))))
;; > (20 10), y는 동일한 레벨의 let에서가
;; 아닌 밖에 있는 let에서 가져온다.
;; letrec는???
