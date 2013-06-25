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
; (rember1*2 'salad '((Swedish rye) (French (mustard salad turkey))
; salad))

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

(define rm
  (lambda (a l oh)
    (cond
     ((null? l) (oh 'no))
     ((atom? (car l))
      (if (eq? (car l) a)
          (cdr l)
          (cons (car l)
                (rm a (cdr l) oh))))
     (else
      (let ((new-car
             (call-with-current-continuation
              (lambda (oh)
                (rm a (car l) oh)))))
        (if (atom? new-car)
            (cons (car l) (rm a (cdr l) oh))
            (cons new-car (cdr l))))))))

(define rember1*4
  (lambda (a l)
    (let ((new-l (call-with-current-continuation
                  (lambda (oh)
                    (rm a l oh)))))
      (if (atom? new-l)
          l
          new-l))))

;; chapter 15.

(define x
  (cons 'chicago (cons 'pizza '())))

(define food 'none)

(define gourmet
  (lambda (food)
    (cons food
          (cons x '()))))

(define gourmand
  (lambda (food)
    (set! x food)
    (cons food
          (cons x '()))))

(define dinner
  (lambda (food)
    (cons 'milkshake
          (cons food '()))))

(define dinnerR
  (lambda (food)
    (set! x food)
    (cons 'milkshake
          (cons food '()))))

(define omnivore
  (let ((x 'minestrone))
    (lambda (food)
      (set! x food)
      (cons food
            (cons x '())))))

(define gobbler
  (let ((x 'minestrone))
    (lambda (food)
      (set! x food)
      (cons food
            (cons x '())))))

(define glutton
  (lambda (x)
    (set! food x)
    (cons 'more
          (cons x
                (cons 'more
                      (cons x '()))))))

(define chez-nous
  (lambda ()
    (let ((a food))
      (set! food x)
      (set! x a))))

(define sweet-tooth
  (lambda (food)
    (cons food
          (cons (quote cake)
                (quote ())))))

(define last (quote angelfood))

(define sweet-toothL
  (lambda (food)
    (set! last food)
    (sweet-tooth food)))

(define ingredient (quote ()))
(define sweet-toothR
  (lambda (food)
    (set! ingredient (cons food ingredient))
    (sweet-tooth food)))

;; (define deep
;;   (lambda (m)
;;     (if (zero? m) 'pizza
;;         (cons (deep (- m 1))
;;               '()))))

;; (define Ns '())
;; (define Rs '())
;; (define deepR
;;   (lambda (m)
;;     (let ((result (deep m)))
;;       (set! Rs (cons result Rs))
;;       (set! Ns (cons m Ns))
;;       result)))

;; (define find
;;   (lambda (n Ns Rs)
;;     (if (= n (car Ns))
;;         (car Rs)
;;         (find n (cdr Ns) (cdr Rs)))))

;; (define find
;;   (lambda (n Ns Rs)
;;     (letrec
;;         ((A (lambda (ns rs)
;;               (if (eq? (car ns) n)
;;                   (car rs)
;;                   (A (cdr ns) (cdr rs))))))
;;       (A Ns Rs))))

;; (define deepM
;;   (lambda (m)
;;     (if (member? m Ns)
;;         (find m Ns Rs)
;;         (let ((result (deep m)))
;;           (set! Rs (cons result Rs))
;;           (set! Ns (cons m Ns))
;;           result))))

(define deep
  (lambda (m)
    (if (zero? m) 'pizza
        (cons (deepM (- m 1))
              '()))))

;; (define deepM
;;   (let ((Ns '())
;;         (Rs '()))
;;     (lambda (m)
;;       (if (member? m Ns)
;;           (find m Ns Rs)
;;           (let ((result (deep m)))
;;             (set! Rs (cons result Rs))
;;             (set! Ns (cons m Ns))
;;             result)))))

;; (define find
;;   (lambda (n Ns Rs)
;;     (letrec
;;         ((A (lambda (ns rs)
;;               (cond
;;                ((null? ns) #f)
;;                ((eq? (car ns) n) #t)
;;                (else
;;                 (A (cdr ns) (cdr rs)))))))
;;       (A Ns Rs))))

;; (define deepM
;;   (let ((Ns '())
;;         (Rs '()))
;;     (lambda (m)
;;       (let ((v (find m Ns Rs)))
;;         (if (atom? v)
;;             (let ((result (deep m)))
;;               (set! Rs (cons result Rs))
;;               (set! Ns (cons m Ns))
;;               result)
;;             v)))))

;; n이 Ns에 존재한다고 보장되는 경우
(define find
  (lambda (n Ns Rs)
    (letrec
        ((A (lambda (ns rs)
              (cond
               ((= (car ns) n) (car rs))
               (else (A (cdr ns) (cdr rs)))))))
      (A Ns Rs))))

;; ns가 null인 경우 고려
(define find
  (lambda (n Ns Rs)
    (letrec
        ((A (lambda (ns rs)
              (cond
               ((null? ns) #f)
               ((= (car ns) n) (car rs))
               (else (A (cdr ns) (cdr rs)))))))
      (A Ns Rs))))

(define deepM
  (let ((Rs '())
        (Ns '()))
    (lambda (n)
      (let ((exists (find n Ns Rs)))
        (if (atom? exists)
            (let ((result 
                   (if (zero? n)
                       'pizza
                       (cons (deepM (- n 1)) '()))))
              (set! Rs (cons result Rs))
              (set! Ns (cons n Ns))
              result)
            exists)))))

(define add1 (lambda (x) (+ x 1)))
(define sub1 (lambda (x) (- x 1)))

(define consC
  (let ((N 0))
    (lambda (x y)
      (set! N (add1 N))
      (cons x y))))

(define deep2
  (lambda (m)
    (if (zero? m) 'pizza
        (consC (deep2 (sub1 m))
               '()))))

(define counter)
(define set-counter)

(define consC3
  (let ((N 0))
    (set! counter (lambda () N))
    (set! set-counter (lambda (x) (set! N x)))
    (lambda (x y)
      (set! N (add1 N))
      (cons x y))))

(define deep3
  (lambda (m)
    (if (zero? m)
        'pizza
        (consC3 (deep3 (sub1 m))
                '()))))

(define supercounter
  (lambda (f)
    (letrec ((S (lambda (n)
                  (if (zero? n)
                      (f n)
                      (let ()
                        (f n)
                        (S (sub1 n)))))))
      (S 1000)
      (counter))))

(define deepM3
  (let ((Rs '())
        (Ns '()))
    (lambda (n)
      (let ((exists (find n Ns Rs)))
        (if (atom? exists)
            (let ((result 
                   (if (zero? n)
                       'pizza
                       (consC3 (deepM3 (- n 1)) '()))))
              (set! Rs (cons result Rs))
              (set! Ns (cons n Ns))
              result)
            exists)))))

(define rember*1
  (lambda (a l)
    (letrec
        ((R (lambda (l oh)
              (cond ((null? l)
                     (oh 'no))
                    ((atom? (car l))
                     (if (eq? (car l) a)
                         (cdr l)
                         (cons (car l)
                               (R (cdr l) oh))))
                    (else
                     (let ((new-car (call-with-current-continuation
                                     (lambda (oh)
                                       (R (car l) oh)))))
                       (if (atom? new-car)
                           (cons (car l)
                                 (R (cdr l) oh))
                           (cons new-car
                                 (cdr l)))))))))
      (let ((new-l (call-with-current-continuation
                    (lambda (oh) (R l oh)))))
        (if (atom? new-l)
            l
            new-l)))))

(define rember*1C
  (lambda (a l)
    (letrec
        ((R (lambda (l oh)
              (cond ((null? l)
                     (oh 'no))
                    ((atom? (car l))
                     (if (eq? (car l) a)
                         (cdr l)
                         (consC3 (car l)
                                 (R (cdr l) oh))))
                    (else
                     (let ((new-car (call-with-current-continuation
                                     (lambda (oh)
                                       (R (car l) oh)))))
                       (if (atom? new-car)
                           (consC3 (car l)
                                   (R (cdr l) oh))
                           (consC3 new-car
                                   (cdr l)))))))))
      (let ((new-l (call-with-current-continuation
                    (lambda (oh) (R l oh)))))
        (if (atom? new-l)
            l
            new-l)))))

(define lots
  (lambda (m)
    (cond
     ((zero? m) '())
     (else (kons 'egg (lots (- m 1)))))))

(define lenkth
  (lambda (l)
    (cond
     ((null? l) 0)
     (else (add1 (lenkth (kdr l)))))))

(define add-at-end
  (lambda (l)
    (cond
     ((null? (kdr l)) (konsC (kar l)
                             (kons 'egg '())))
     (else (konsC (kar l)
                  (add-at-end (kdr l)))))))

;; (add-at-end (lots 3))
;; (add-at-end (cons 'egg (cons 'egg (cons 'egg '()))))
;; (consC3 'egg (add-at-end (cons 'egg (cons 'egg '()))))
;; (consC3 'egg (consC3 'egg (add-at-end (cons 'egg '()))))
;; (consC3 'egg (consC3 'egg (consC3 'egg (cons 'egg '()))))

(define add-at-end-too
  (lambda (l)
    (letrec
        ((A (lambda (ls)
              (cond
               ((null? (kdr ls)) (set-kdr ls (kons 'egg '())))
               (else (A (kdr ls)))))))
      (A l)
      l)))

;(A (A (A '(egg))))
;(A (A '(egg egg)))
;(A '(egg egg egg))
;'(egg egg egg egg)

;; (define kons
;;   (lambda (kar kdr)
;;     (lambda (selector)
;;       (selector kar kdr))))

;; (kons 'egg '(egg egg))
;; (lambda (selector)
;;   (selector 'egg '(egg egg)))

;; (define kar
;;   (lambda (c)
;;     (c (lambda (a d) a))))

;; (define kdr
;;   (lambda (c)
;;     (c (lambda (a d) d))))

(define bons
  (lambda (kar)
    (let ((kdr '()))
      (lambda (selector)
        (selector
         (lambda (x) (set! kdr x))
         kar
         kdr)))))

(define kar
  (lambda (c)
    (c (lambda (s a d) a))))

(define kdr
  (lambda (c)
    (c (lambda (s a d) d))))

(define set-kdr
  (lambda (c x)
    ((c (lambda (s a d) s)) x)))

(define kons
  (lambda (a d)
    (let ((c (bons a)))
      (set-kdr c d)
      c)))

(define kounter)
(define set-kounter)

(define konsC
  (let ((N 0))
    (set! kounter (lambda () N))
    (set! set-kounter (lambda (x) (set! N x)))
    (lambda (x y)
      (set! N (add1 N))
      (kons x y))))
