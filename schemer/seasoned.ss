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

(define eqlist?
  (lambda (l1 l2)
    (cond
     ((and (null? l1) (null? l2)) #t)
     ((or (null? l1) (null? l2)) #f)
     (else
      (and (equal? (car l1) (car l2))
           (equal? (cdr l1) (cdr l2)))))))

(define rember1*
  (lambda (a l)
    (cond
     ((null? l) '())
     ((atom? (car l))
      (cond
       ((eq? a (car l)) (cdr l))
       (else (cons (car l) (rember1* a (cdr l))))))
     (else
      (cond
       ((eqlist? (rember1* a (car l)) (car l))
        (cons (car l) (rember1* a (cdr l))))
       (else (cons (rember1* a (car l)) (cdr l))))))))
;; (rember1* 'salad '((Swedish rye) (French (mustard salad turkey))
;; salad))

(define rember1*2
  (lambda (a l)
    (letrec
        ((R (lambda (l)
              (cond
               ((null? l) '())
               ((atom? (car l))
                (cond
                 ((eq? a (car l)) (cdr l))
                 (else (cons (car l) (R (cdr l))))))
               (else
                (cond
                 ((eqlist? (R (car l)) (car l))
                  (cons (car l) (R (cdr l))))
                 (else (cons (R (car l)) (cdr l)))))))))
      (R l))))
;; (rember1*2 'salad '((Swedish rye) (French (mustard salad turkey))
;; salad))

;; (R (car l)) 반복 제거
(define rember1*3
  (lambda (a l)
    (letrec
        ((R (lambda (l)
              (cond
               ((null? l) '())
               ((atom? (car l))
                (cond
                 ((eq? a (car l)) (cdr l))
                 (else (cons (car l) (R (cdr l))))))
               (else
                (let
                    ((av (R (car l))))
                  (cond
                   ((eqlist? av (car l))
                    (cons (car l) (R (cdr l))))
                   (else (cons av (cdr l))))))))))
      (R l))))

(define addone
  (lambda (n) (+ n 1)))

(define depth*
  (lambda (l)
    (cond
     ((null? l) 1)
     ((atom? (car l)) (depth* (cdr l)))
     (else
      (cond
       ((> (depth* (cdr l))
           (addone (depth* (car l))))
        (depth* (cdr l)))
       (else
        (addone (depth* (car l)))))))))

;; best code
(define depth2*
  (lambda (l)
    (cond
     ((null? l) 1)
     ((atom? (car l))
      (depth2* (cdr l)))
     (else
      (let
          ((a (addone (depth2* (car l))))
           (d (depth2* (cdr l))))
        (cond
         ((> d a) d)
         (else a)))))))

(define depth3*
  (lambda (l)
    (cond
     ((null? l) 1)
     (else
      (let ((d (depth3* (cdr l))))
        (cond
         ((atom? (car l)) d)
         (else
          (let ((a (addone (depth3* (car l)))))
            (cond
             ((> d a) d)
             (else a))))))))))


;; depth* 코드들 검토 - 계산 방법 관점에서...
;; (car l)이 atom이면 (cdr l)의 depth를 계산하고 종료
;; (car l)이 atom이 아니면 (car l)의 depth는 두 번 (cdr l)의 depth는
;; 한 번 계산,  결국 let을 어떻게 쓰던 depth2*나 depth3*나 (car l)과
;; (cdr l)의 depth의 계산횟수는 동일. 그러므로 보기 좋은 depth2*가 더
;; 좋은 코드이다.

;; depth2*와 동일, 마지막 cond를 >함수의 결과로 처리.
(define depth4*
  (lambda (l)
    (cond
     ((null? l) 1)
     ((atom? (car l))
      (depth4* (cdr l)))
     (else
      (let ((a (addone (depth4* (car l))))
            (d (depth4* (cdr l))))
        (if (> d a) d a))))))

(define max (lambda (a b) (if (> a b) a b)))
;; 두값을 비교해서 큰 값을 반환하는 함수가 있다면...
;; 아래처럼 좀 더 간결하게... let이 필요 없어졌다.
(define depth5*
  (lambda (l)
    (cond
     ((null? l) 1)
     ((atom? (car l)) (depth5* (cdr l)))
     (else
      (max (addone (depth5* (car l)))
           (depth5* (cdr l)))))))

(define pick
  (lambda (n lat)
    (cond
     ((eq? n 1) (car lat))
     (else (pick (sub1 n) (cdr lat))))))

(define scramble
  (lambda (tup)
    (letrec
        ((P (lambda (tup rp)
              (cond
               ((null? tup) (quote ()))
               (else
                (cons (pick (car tup)
                            (cons (car tup) rp))
                      (P (cdr tup)
                         (cons (caar tup) rp))))))))
      (P tup (quote ())))))


(define leftmost-4
  (lambda (l)
    (call-with-current-continuation
     (lambda (skip)
       (lm l skip)))))

(define lm
  (lambda (l out)
    (cond
     ((null? l) '())
     ((atom? (car l)) (out (car l)))
     (else
      (let ()
        (lm (car l) out)
        (lm (cdr l) out))))))

(define leftmost-5
  (lambda (l)
    (call-with-current-continuation
     (lambda (skip)
       (letrec ((lm (lambda (l)
                      (cond
                       ((null? l) '())
                       ((atom? (car l)) (skip (car l)))
                       (else
                        (let ()
                          (lm (car l))
                          (lm (cdr l))))))))
         (lm l))))))
