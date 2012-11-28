(define member?
  (lambda (a lat)
    (cond
     ((null? lat) #f)
     ((eq? a (car lat)) #t)
     (else (member? a (cdr lat))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; CHAPTER 3. Cons the Magnificent
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define rember
  (lambda (a lat)
    (cond
     ((null? lat) '())
     ((eq? a (car lat)) (cdr lat))
     (else (cons (car lat)
                 (rember a (cdr lat)))))))

(rember 1 '(1 2 3))

(define firsts
  (lambda (l)
    (cond
     ((null? l) '())
     (else
      (cons (car (car l))
            (firsts (cdr l)))))))

(define insertR
  (lambda (new old lat)
    (cond
     ((null? lat) '())
     ((eq? old (car lat))
      (cons old (cons new (cdr lat))))
     (else
      (cons (car lat) (insertR new old (cdr lat)))))))

(insertR 'e 'd '(a b c d f g)) ;; > '(a b c d e f g)

;; > '(ice cream with topping for dessert)
(define subst
  (lambda (new old lat)
    (cond
     ((null? lat) '())
     ((eq? old (car lat))
      (cons new (cdr lat)))
     (else
      (cons (car lat) (subst new old (cdr lat)))))))

(subst 'topping 'fudge '(ice cream with fudge for dessert))

(define insertL
  (lambda (new old lat)
    (cond
     ((null? lat) '())
     ((eq? old (car lat))
      (cons new lat))
     (else
      (cons (car lat) (insertL new old (cdr lat)))))))

(insertL 'e 'd '(a b c d f g)) ;; > '(a b c d e f g)

(define multirember
  (lambda (a lat)
    (cond
     ((null? lat) '())
     ((eq? a (car lat))
      (multirember a (cdr lat)))
     (else
      (cons (car lat) (multirember a (cdr lat)))))))


(define multiinsertR
  (lambda (new old lat)
    (cond
     ((null? lat) '())
     ((eq? old (car lat))
      (cons (car lat) (cons new (multiinsertR new old (cdr lat)))))
     (else
      (cons (car lat) (multiinsertR new old (cdr lat)))))))

(multiinsertR 'a 'x '(x y z x y z))

(define multiinsertL
  (lambda (new old lat)
    (cond
     ((null? lat) '())
     ((eq? old (car lat))
      (cons new (cons (car lat) (multiinsertL new old (cdr lat)))))
     (else
      (cons (car lat) (multiinsertL new old (cdr lat)))))))

(multiinsertL 'a 'x '(x y z x y z))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; CHAPTER 5.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; (define a 'cup)
;; (define l '((coffee) cup ((tea) cup) (and (hick)) cup))

;; (rember* a l)
;; ;; > ((coffee ((tea) cup) (and (hick))))

(define a 'sauce)
(define l '(((tomato sauce))
            ((bean) sauce)
            (and ((flying)) sauce)))
;; (rember* a l)
;; ;; (((tomato))
;; ;;  ((bean))
;; ;;  (and ((flying))))

;; need for impl.
(define atom?
  (lambda (x)
    (and (not (pair? x)) (not (null? x)))))

(define lat?
  (lambda (l)
    (cond
     ((null? l) #t)
     ((atom? (car l)) (lat? (cdr l)))
     (else #f))))

(define rember*
  (lambda (a l)
    (cond
     ((null? l) '())
     ((atom? (car l))
      (cond
       ((eq? (car l) a) (rember* a (cdr l)))
       (else (cons (car l)
                   (rember* a (cdr l))))))
     (else (cons (rember* a (car l))
                 (rember* a (cdr l)))))))

(define insertR*
  (lambda (new old l)
    (cond
     ((null? l) '())
     ((atom? (car l))
      (cond
       ((eq? (car l) old)
        (cons old
              (cons new
                    (insertR* new old (cdr l)))))
       (else (cons (car l)
                   (insertR* new old (cdr l))))))
     (else (cons (insertR* new old (car l))
                 (insertR* new old (cdr l)))))))


(define l '((how much (wood))
            could
            ((a (wood) chuck))
            (((chuck)))
            (if (a) ((wood chuck)))
            could chuck wood))

(define a 'banana)
(define l '((banana)
            (split ((((banana ice)))
                    (cream (banana))
                    sherbet))
            (banana)
            (bread)
            (banana brandy)))

(define add1
  (lambda (n) (+ n 1)))
(define sub1
  (lambda (n) (- n 1)))

(define occur*
  (lambda (a l)
    (cond
     ((null? l) 0)
     ((atom? (car l))
      (cond
       ((eq? (car l) a) (add1 (occur* a (cdr l))))
       (else (occur* a (cdr l)))))
     (else (+ (occur* a (car l))
              (occur* a (cdr l)))))))

(define subst*
  (lambda (new old l)
    (cond
     ((null? l) '())
     ((atom? (car l))
      (cond
       ((eq? old (car l))
        (cons new (subst* new old (cdr l))))
       (else
        (cons (car l) (subst* new old (cdr l))))))
     (else (cons (subst* new old (car l))
                 (subst* new old (cdr l)))))))

(define l
  '((how much (wood))
    could
    ((a (wood) chuck))
    (((chuck)))
    (if (a) ((wood chuck)))
    could chuck wood))

(define insertL*
  (lambda (new old l)
    (cond
     ((null? l) '())
     ((atom? (car l))
      (cond
       ((eq? (car l) old)
        (cons new (cons (car l) (insertL* new old (cdr l)))))
       (else
        (cons (car l) (insertL* new old (cdr l))))))
     (else
      (cons (insertL* new old (car l))
            (insertL* new old (cdr l)))))))

(define l '((potato) (chips ((with) fish) (chips))))

(define member*?
  (lambda (a l)
    (cond
     ((null? l) #f)
     ((atom? (car l))
      (or
       (eq? (car l) a)
       (member*? a (cdr l))))
     (else
      (or
       (member*? a (car l))
       (member*? a (cdr l)))))))

;; leftmost - non-empty list에 대해서만 검사
(define leftmost
  (lambda (l)
    (cond
     ((atom? (car l)) (car l))
     (else (leftmost (car l))))))


;; eqlist? 1st
;; 책에서 eqan?을 사용하라고 했는데 뭐지? 일단 eq?로 변경
(define eqan? eq?)
(define eqlist?
  (lambda (l1 l2)
    (cond
     ((and (null? l1) (null? l2))
      #t)
     ((and (null? l1) (atom? (car l2)))
      #f)
     ((null? l1)
      #f)
     ((and (atom? (car l1)) (null? l2))
      #f)
     ((and (atom? (car l1)) (atom? (car l2)))
      (and (eqan? (car l1) (car l2)) (eqlist? (cdr l1) (cdr l2))))
     ((atom? (car l1))
      #f)
     ((null? l2)
      #f)
     ((atom? (car l2))
      #f)
     (else
      (and (eqlist? (car l1) (car l2)) (eqlist? (cdr l1) (cdr l2)))))))

(eqlist? '(strawberry ice cream) '(strawberry ice cream))
(eqlist? '(strawberry ice cream) '(strawberry cream ice))
(eqlist? '(banana ((split))) '((banana) (split)))
(eqlist? '(beef ((sausage)) (and (soda))) '(beef ((sausage)) (and (soda))))


(define eqlist?
  (lambda (l1 l2)
    (cond
     ((and (null? l1) (null? l2)) #t)
     ((or (null? li1) (null? l2)) #f)
     ((and (atom? (car l1)) (null? l2))
      #f)
     ((and (atom? (car l1)) (atom? (car l2)))
      (and (eqan? (car l1) (car l2)) (eqlist? (cdr l1) (cdr l2))))
     ((atom? (car l1))
      #f)
     ((null? l2)
      #f)
     ((atom? (car l2))
      #f)
     (else
      (and (eqlist? (car l1) (car l2)) (eqlist? (cdr l1) (cdr l2)))))))

;; S-expression equality test
(define equal?
  (lambda (s1 s2)
    (cond
     ((and (atom? s1) (atom? s2))
      (eqan? s1 s2))
     ((atom? s1)
      #f)
     ((atom? s2)
      #f)
     (else
      (eqlist? s1 s2)))))

(define equal?
  (lambda (s1 s2)
    (cond
     ((and (atom? s1) (atom? s2))
      (eqan? s1 s2))
     ((or (atom? s1) (atom? s2))
      #f)
     (else
      (eqlist? s1 s2)))))

(define eqlist?
  (lambda (l1 l2)
    (cond
     ((and (null? l1) (null? l2)) #t)
     ((or (null? l1) (null? l2)) #f)
     (else
      (and (equal? (car l1) (car l2))
           (equal? (cdr l1) (cdr l2)))))))

(define rember
  (lambda (s l)
    (cond
     ((null? l) '())
     ((equal? s (car l)) (cdr l))
     (else (cons (car l)
                 (rember s (cdr l)))))))

(rember '(1 2) '((a b (1 2)) (1 2) c (d (1 2))))
;; > ((a b (1 2)) c (d (1 2)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; CHAPTER 6. Shadow
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; arithmetic expression is
;; either an atom or two arithmetic expressions combined by +, *, ^

;; numbered?
;; determine whether a representation of an arithmetic expression
;; contains only numbers besides the +, *, ^
;; aexp는 atom 또는 +, *, ^ 로 조합된 aexp이다.
(define numbered?
  (lambda (aexp)
    (cond
     ((atom? aexp) (number? aexp))
     ((eq? (car (cdr aexp)) (quote +)) (and (numbered? (car aexp))
                                            (numbered? (car (cdr (cdr aexp))))))
     ((eq? (car (cdr aexp)) (quote *)) (and (numbered? (car aexp))
                                            (numbered? (car (cdr (cdr aexp))))))
     ((eq? (car (cdr aexp)) (quote ^)) (and (numbered? (car aexp))
                                            (numbered? (car (cdr (cdr aexp)))))))))

(define numbered?
  (lambda (aexp)
    (cond
     ((atom? aexp) (number? aexp))
     (else
      (and (numbered? (car aexp))
           (numbered? (car (cdr (cdr aexp)))))))))

(define u 13)
;(value u) ;; > 13

(define x '(1 + 3))
;(value x) ;; > 4

(define y '(1 + (3 ^ 4)))
;(value y) ;; > 82

(define y 'cookie)
;(value y) ;; > no answer

(define value
  (lambda (aexp)
    (cond
     ((atom? aexp) aexp)
     ((eq? (car (cdr aexp)) (quote +))
      (+ (value (car aexp)) (value (car (cdr (cdr aexp))))))
     ((eq? (car (cdr aexp)) (quote *))
      (* (value (car aexp)) (value (car (cdr (cdr aexp))))))
     ((eq? (car (cdr aexp)) (quote ^))
      (expt (value (car aexp)) (value (car (cdr (cdr aexp)))))))))

;; 앞의 value는 (1 + 2) 형태의 arithmetic expression에서 사용 가능한
;; 함수
;; (+ 1 2) 형태가 가능한 value 함수는?
(define value
  (lambda (aexp)
    (cond
     ((atom? aexp) aexp)
     ((eq? (car aexp) (quote +)) (+ (value (car (cdr aexp))) (value (car (cdr (cdr aexp))))))
     ((eq? (car aexp) (quote *)) (* (value (car (cdr aexp))) (value (car (cdr (cdr aexp))))))
     ((eq? (car aexp) (quote ^)) (expt (value (car (cdr aexp))) (value (car (cdr (cdr aexp)))))))))

;; + 와 첫번째 값, 두번째 값을 얻는 함수를 따로 만들면
(define operator
  (lambda (aexp)
    (car aexp)))
(define 1st-sub-exp
  (lambda (aexp)
    (car (cdr aexp))))
(define 2nd-sub-exp
  (lambda (aexp)
    (car (cdr (cdr aexp)))))

;; value를 다시 작성하면
(define value
  (lambda (aexp)
    (cond
     ((atom? aexp) aexp)
     ((eq? (operator aexp) (quote +))
      (+ (value (1st-sub-exp aexp))
         (value (2nd-sub-exp aexp))))
     ((eq? (operator aexp) (quote *))
      (* (value (1st-sub-exp aexp))
         (value (2nd-sub-exp aexp))))
     ((eq? (operator aexp) (quote ^))
      (expt (value (1st-sub-exp aexp))
            (value (2nd-sub-exp aexp)))))))

;; number : representation
;; 4라는 숫자를 4말고 다른 식으로 표현하면?
;; 숫자 넷은 (() () () ()) 로 표현할수도 있다.
;; 숫자 영은? () : null

(define nzero?
  (lambda (n)
    (null? n)))

;; +1
(define edd1
  (lambda (n)
    (cons '() n)))

;; -1
(define zub1
  (lambda (n)
    (cdr n)))

;; +
(define edd
  (lambda (n m)
    (cond
     ((nzero? m) n)
     (else
      (edd1 (edd n (zub1 m))))))) ;; (edd (edd1 n) (zub1 m)) 도 가능


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; CHAPTER 7. Friends and Relations
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; set
(define lat '(apples peaches pears plums))
;; (set? lat) ;; > #t
;; (set? '()) ;; > #t


(define set?
  (lambda (lat)
    (cond
     ((null? lat) #t)
     ((member? (car lat) (cdr lat)) #f)
     (else (set? (cdr lat))))))


;; eq? : 내장 함수
;; equal? : 구현한 함수, 두개의 s-expression이 동일한지 검사


(define lat '(apple peach pear peach
                    plum apple lemon peach))

;; (makeset lat) > '(apple peach pear plum lemon)
(define makeset
  (lambda (lat)
    (cond
     ((null? lat) '())
     ((member? (car lat) (cdr lat)) (makeset (cdr lat)))
     (else (cons (car lat) (makeset (cdr lat)))))))


(define makeset
  (lambda (lat)
    (cond
     ((null? lat) '())
     (else
      (cons (car lat) (makeset 
                       (multirember (car lat)
                                    (cdr lat))))))))

(define set1 '(5 chicken wings))
(define set2 '(5 hamburgers 2 pieces fried chicken and light duckling wings))

;; (subset? set1 set2) > #t

(define subset?
  (lambda (set1 set2)
    (cond
     ((null? set1) #t)
     ((member? (car set1) set2) (subset? (cdr set1) set2))
     (else #f))))

(define subset?
  (lambda (set1 set2)
    (cond
     ((null? set1) #t)
     (else
      (and (member? (car set1) set2)
           (subset? (cdr set1) set2))))))

(define set1 '(6 large chickens with wings))
(define set2 '(6 chickens with large wings))
;; (eqset? set1 set2) > #t

(define eqset?
  (lambda (set1 set2)
    (cond
     ((and (null? set1) (null? set2)) #t)
     ((null? set1) #f)
     ((null? set2) #f)
     (else
      (and (member? (car set1) set2)
           (eqset? (cdr set1) (rember* (car set1) set2)))))))

(define eqset?
  (lambda (set1 set2)
    (and (subset? set1 set2)
         (subset? set2 set1))))

(define set1 '(stewed tomatoes and macaroni))
(define set2 '(macaroni and cheese))

(define intersect?
  (lambda (set1 set2)
    (cond
     ((null? set1) #f)
     (else
      (or (member? (car set1) set2)
          (intersect? (cdr set1) set2))))))

;; (intersect '(stewed tomatoes and macaroni) '(macaroni and cheese))
;; > '(and macaroni)
(define intersect
  (lambda (set1 set2)
    (cond
     ((null? set1) '())
     ((member? (car set1) set2)
      (cons (car set1) (intersect (cdr set1) set2)))
     (else
      (intersect (cdr set1) set2)))))

(define union
  (lambda (set1 set2)
    (cond
     ((null? set1) set2)
     ((member? (car set1) set2)
      (union (cdr set1) set2))
     (else
      (cons (car set1)
            (union (cdr set1) set2))))))

(define difference
  (lambda (set1 set2)
    (cond
     ((null? set1) '())
     ((member? (car set1) set2)
      (difference (cdr set1) set2))
     (else
      (cons (car set1)
            (difference (cdr set1) set2))))))

;; (difference '(stewed tomatoes and macaroni) '(macaroni and cheese))
;; > '(stewed tomatoes)

(define intersect-all
  (lambda (l-set)
    (cond
     ((null? (cdr l-set)) (car l-set))
     (else
      (intersect-all (cons (intersect (car l-set) (car (cdr l-set)))
                           (cdr (cdr l-set))))))))

(define intersect-all
  (lambda (l-set)
    (cond
     ((null? (cdr l-set)) (car l-set))
     (else
      (intersect (car l-set)
                 (intersect-all (cdr l-set)))))))

(intersect-all '((6 pears and)
                 (3 peaches and 6 peppers)
                 (8 pears and 6 plums)
                 (and 6 prunes with some apples)))

(intersect-all '((6 pears and)
                 (3 peaches and 6 peppers)))

(intersect-all '((6 pears and)))

;; a-pair? 2개의 s-exp만 있는 list
(define a-pair?
  (lambda (x)
    (cond
     ((atom? x) #f)
     ((null? x) #f)
     ((null? (cdr x)) #f)
     ((null? (cdr (cdr x))) #t)
     (else #f))))

(define first
  (lambda (p)
    (car p)))

(define second
  (lambda (p)
    (car (cdr p))))

(define build
  (lambda (s1 s2)
    (cons s1 (cons s2 '()))))

(define third
  (lambda (l)
    (car (cdr (cdr l)))))

;; relation??? - pair의 set

;; fun? - firsts가 set인 리스트- finite function

(define fun?
  (lambda (rel)
    (set? (firsts rel))))

;; (revrel '((8 a) (pumpkin pie) (got sick)))
;; > ((a 8) (pie pumpkin) (sick got))
(define revrel
  (lambda (rel)
    (cond
     ((null? rel) '())
     (else
      (cons (build (second (car rel)) (first (car rel)))
            (revrel (cdr rel)))))))

(revrel '((8 a) (pumpkin pie) (got sick)))

(define revpair
  (lambda (p)
    (build (second p) (first p))))

(define revrel
  (lambda (rel)
    (cond
     ((null? rel) '())
     (else
      (cons (revpair (car rel))
            (revrel (cdr rel)))))))

(define seconds
  (lambda (l)
    (cond
     ((null? l) '())
     (else
      (cons (car (cdr (car l)))
            (seconds (cdr l)))))))

(define fullfun?
  (lambda (fun)
    (set? (seconds fun))))

(define one-to-one?
  (lambda (fun)
    (fun? (revrel fun))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; CHAPTER 8. Lambda the Ultimate
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(define test? =)
(define a 5)
(define l '(6 2 5 3))

;; (rember test? a l)
;; > (6 2 3)

(define rember-f
  (lambda (test? a l)
    (cond
     ((null? l) '())
     ((test? (car l) a) (cdr l))
     (else (cons (car l)
                 (rember-f test? a (cdr l)))))))
(rember-f = 5 '(6 2 5 3))

(define eq-c?
  (lambda (a)
    (lambda (x)
      (eq? x a))))

(define eq-salad? (eq-c? 'salad))

(define rember-f2
  (lambda (test?)
    (lambda (a l)
      (cond
       ((null? l) '())
       ((test? (car l) a) (cdr l))
       (else (cons (car l)
                   ((rember-f2 test?) a (cdr l))))))))

(define test? eq?)
(define rember-eq? (rember-f2 eq?))
(rember-eq? 5 '(6 2 5 3))

(define insertL-f
  (lambda (test?)
    (lambda (new old lat)
      (cond
       ((null? lat) '())
       ((test? old (car lat))
        (cons new lat))
       (else
        (cons (car lat) ((insertL-f test?) new old (cdr lat))))))))

((insertL-f eq?) 'e 'd '(a b c d f g))

(define insertR-f
  (lambda (test?)
    (lambda (new old lat)
      (cond
       ((null? lat) '())
       ((test? old (car lat))
        (cons (car lat) (cons new (cdr lat))))
       (else
        (cons (car lat) ((insertR-f test?) new old (cdr lat))))))))

((insertR-f eq?) 'e 'd '(a b c d f g))

(define seqL
  (lambda (new old l)
    (cons new (cons old l))))

(define seqR
  (lambda (new old l)
    (cons old (cons new l))))

;; seq가 seqL이면 insertL을, seq가 seqR이면 insertR을 반환하는 함수
(define insert-g
  (lambda (seq)
    (lambda (new old l)
      (cond
       ((null? l) '())
       ((eq? old (car l))
        (seq new old (cdr l)))
       (else
        (cons (car l) ((insert-g seq) new old (cdr l))))))))

((insert-g seqL) 'e 'd '(a b c d f g))
((insert-g seqR) 'e 'd '(a b c d f g))

(define insertL (insert-g seqL))
(define insertR (insert-g seqR))

(define insertL (insert-g
                 (lambda (new old l)
                   (cons new (cons old l)))))
(define insertR (insert-g
                   (lambda (new old l)
                     (cons old (cons new l)))))

(define seqS
  (lambda (new old l)
    (cons new l)))

(define subst (insert-g seqS))
(subst 'topping 'fudge '(ice cream with fudge for dessert))

(define atom-to-function
  (lambda (x)
    (cond
     ((eq? x '+) +)
     ((eq? x '*) *)
     ((eq? x '^) expt))))

;; (define operator
;;   (lambda (aexp)
;;     (car aexp)))
;; (define 1st-sub-exp
;;   (lambda (aexp)
;;     (car (cdr aexp))))
;; (define 2nd-sub-exp
;;   (lambda (aexp)
;;     (car (cdr (cdr aexp)))))

(define value
  (lambda (aexp)
    (cond
     ((atom? aexp) aexp)
     (else
      ((atom-to-function (operator aexp))
       (value (1st-sub-exp aexp))
       (value (2nd-sub-exp aexp)))))))

(value '(+ 3 (* 2 4)))

(define multirember-f
  (lambda (test?)
    (lambda (a lat)
      (cond
       ((null? lat) '())
       ((test? a (car lat))
        ((multirember-f test?) a (cdr lat)))
       (else
        (cons (car lat) ((multirember-f test?) a (cdr lat))))))))

(define multirember-eq? (multirember-f eq?))

(define multirember-T
  (lambda (test? lat)
    (cond
     ((null? lat) '())
     ((test? (car lat))
      (multirember-T test? (cdr lat)))
     (else
      (cons (car lat) (multirember-T test? (cdr lat)))))))

(define multiremberEco
  ;; col : collector or CONTINUATION
  (lambda (a lat col)
    (cond
     ((null? lat)
      (col '() '()))
     ((eq? (car lat) a)
      (multiremberEco a (cdr lat)
                      (lambda (newlat seen)
                        (col newlat
                             (cons (car lat) seen)))))
     (else
      (multiremberEco a (cdr lat)
                      (lambda (newlat seen)
                        (col (cons (car lat) newlat)
                             seen)))))))

;; multiremberEco - lat에 a가 없으면 첫번째, 있으면 두번째로 해서
;; col을 실행, (col '(있는거) '(없는거))

(define a-friend
  (lambda (x y)
    (null? y)))

(multiremberEco 'tuna '(strawberries tuna and swordfish) a-friend)
(multiremberEco 'tuna '() a-friend)
(multiremberEco 'tuna '(tuna) a-friend)

(define multiinsertLR
  (lambda (new oldL oldR lat)
    (cond
     ((null? lat) '())
     ((eq? oldL (car lat))
      (cons new (cons (car lat) (multiinsertLR new oldL oldR (cdr lat)))))
     ((eq? oldR (car lat))
      (cons (car lat) (cons new (multiinsertLR new oldL oldR (cdr lat)))))
     (else
      (cons (car lat) (multiinsertLR new oldL oldR (cdr lat)))))))
(multiinsertLR 'a 'x 'y '(x y z x y z))

(define multiinsertLREco
  (lambda (new oldL oldR lat col)
    (cond
     ((null? lat)
      (col '() 0 0))
     ((eq? oldL (car lat))
      (multiinsertLREco new oldL oldR (cdr lat)
                        (lambda (newlat L R)
                          (col (cons new (cons (car lat) newlat))
                               (add1 L) R))))
     ((eq? oldR (car lat))
      (multiinsertRLEco new oldL oldR (cdr lat)
                        (lambda (newlat L R)
                          (col (cons (car lat) (cons new newlat))
                               R (add1 R)))))
     (else
      (multiinsertLREco new oldL oldR (cdr lat)
                        (lambda (newlat L R)
                          (col (cons (car lat) newlat)
                               R L)))))))

;; (multiinsertLREco 'salty 'fish 'chips '(chips and fish or fish and
;;  chip) col)
;; > (col '(chips salty and salty fish or salty fish and chips salty) 2 2)

(define evens-only*
  (lambda (l)
    (cond
     ((null? l) '())
     ((atom? (car l))
      (cond
       ((even? (car l)) (cons (car l) (evens-only* (cdr l))))
       (else (evens-only* (cdr l)))))
     (else
      (cons (evens-only* (car l))
            (evens-only* (cdr l)))))))
;; (evens-only* '((9 1 2 8) 3 10 ((9 9) 7 6) 2))
;; => ((2 8) 10 (() 6) 2)

;; l : nested list
;; return 
;;  l : nested of even numbers
;;  m : multiplies the even numbers
;;  s : sums up the odd numbers
;;  (l m s)
;; (define evens-only*Eco
;;   (lambda (l col)
;;     (cond
;;      ((null? l) '(() 1 0))
;;      ((atom? (car l))
;;       (cond
;;        ((even? (car l))
;;         (evens-only*Eco (cdr l)
;;                         (lambda (newl m s)
;;                           (col (cons (car l) newl) (* m (car l)) s))))
;;        (else
;;         (evens-only*Eco (cdr l)
;;                        (lambda (newl m s)
;;                          (col (cons (car l) newl) m (+ s (car l))))))))
;;      (else
;;       (evens-only*Eco (car l) col)))))

;; multiremberEco 부터 잘 모르겠다... 복잡복잡!!!

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; CHAPTER 9. ...and Again, and Again, and Again,...
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; (looking 'caviar '(6 2 4 caviar 5 7 3))
;; > #t, 6 > 7 > 3 > 4 > caviar
;; (looking 'caviar '(6 2 grits caviar 5 7 3))
;; > #f, 6 > 7 > 3 > grits

(define looking
  (lambda (a lat)
    (keep-looking a (pick 1 lat) lat)))

;; sorn = symbol or number
(define keep-looking
  (lambda (a sorn lat)
    (cond
     ((number? sorn)
      (keep-looking a (pick sorn lat) lat))
     (else
      (eq? a sorn)))))

;; (keep-looking 'caviar 3 '(6 2 4 caviar 5 7 3))
;; > (keep-looing 'caviar 4 '(6 2 4 caviar 5 7 3))

;; looking 같은 함수를 partial function이라고 한다.
;; 어떤 의미? goal에 다가가지 않을수도 있는 함수?
;; 특정 인자에 대해서만 결과를 얻을 수 있는 함수?

;; most partial function
(define eternity
  (lambda (x)
    (eternity x)))

(define pick
  (lambda (n lat)
    (cond
     ((eq? n 1) (car lat))
     (else (pick (sub1 n) (cdr lat))))))

;; (pick 7 '(6 2 4 caviar 5 7 3))
;; > 3

;; looking : partial funciton ???
;; lat에 따라 무한루프 돌수 있다.

;; shift
;; pair를 인자로 받는데, 인자의 첫번째 값은 pair이다.
;; x = (a b) 일 때 (shift x) => ((first a) ((second a) c))
(define shift
  (lambda (x)
    (build (first (first x))
           (build (second (first x))
                  (second x)))))
;; (shift '((a b) c))
;; > (a (b c))
;; (shift '((a b) (c d)))
;; > (a (b (c d)))

;; pora > pair or atom

(define align
  (lambda (pora)
    (cond
     ((atom? pora) pora)
     ((a-pair? (first pora))
      (align (shift pora)))
     (else
      (build (first pora)
             (align (second pora)))))))

;; keep-looking과 유사점?
;; recursive 하면서 목표점? 에 도달하는걸 보장할 수 없다.
;; align에서는 atom? 이 탈출조건인데, pora가 atom이 되도록 변경하는
;; 코드가 없으므로

(define length*
  (lambda (pora)
    (cond
     ((atom? pora) 1)
     (else
      (+ (length* (first pora))
         (length* (second pora)))))))

(length* '((a b) c))

(define weight*
  (lambda (pora)
    (cond
     ((atom? pora) 1)
     (else
      (+ (* (weight* (first pora)) 2)
         (weight* (second pora)))))))

;; align은 partial function 이냐?
;; 아니다. ???

(define shuffle
  (lambda (pora)
    (cond
     ((atom? pora) pora)
     ((pair? (first pora))
      (shuffle (revpair pora)))
     (else
      (build (first pora)
             (shuffle (second pora)))))))

;; 어떤 함수가 있을때 이게 partial인지 full인지 확인하기 쉽지 않다.

(define zero?
  (lambda (n)
    (= n 0)))

(define A
  (lambda (n m)
    (cond
     ((zero? n) (add1 m))
     ((zero? m) (A (sub1 n) 1))
     (else
      (A (sub1 n)
         (A n (sub1 m)))))))

;; (A 4 3) ; => infinite loop, partial이라는 의미?

;; 함수가 partial인지 full 인지 검사하는 함수를 만들자.

;; (define will-stop?
;;   (lambda (f)
;;     ))

;; 먼저 ()에 대해서...

;; will-stop? 은 partial인가? 아니다. 이것은 항상 #t 또는 #f를
;; 반환한다.
;; (will-stop? length) ;; => #t

;; 그런데, will-stop? 이 검사할 함수가 will-stop?을 호출하는 경우는?
;; 자기 참조 -> 결론적으로 will-stop?은 정의할 수 없다.!

(define Y
  (lambda (le)
    ((lambda (f) (f f))
     (lambda (f)
       (le (lambda (x) ((f f) x)))))))

;; Ch 10. What is the value of all of this?
;; entry : pair of lists, first list is a set.

(define lookup-in-entry
  (lambda (name entry entry-f)
    (lookup-in-entry-help name
                          (first entry)
                          (second entry)
                          entry-f)))

(define lookup-in-entry-help
  (lambda (name names values entry-f)
    (cond
     ((null? names)
      (entry-f name))
     ((eq? name (car names))
      (car values))
     (else
      (lookup-in-entry-help
       name
       (cdr names)
       (cdr values)
       entry-f)))))

;; table : a list of entries

;; (define extend-table
;;   (lambda (entry table)
;;     (cons entry table)))

(define extend-table cons)

(define lookup-in-table
  (lambda (name table table-f)
    (cond
     ((null? table) (table-f name))
     (else
      (lookup-in-entry
       name
       (car table)
       (lambda (name)
         (lookup-in-table name (cdr table) table-f)))))))

(define rep-car 'car)
(define rep-quote 'quote)
(define rep-a 'a)
(define rep-b 'b)
(define rep-c 'c)
(cons rep-car
      (cons (cons rep-quote
                  (cons (cons rep-a
                              (cons rep-b
                                    (cons rep-c
                                          (quote ()))))
                        (quote ())))
            (quote ())))

;; type
;; *const
;; *quote
;; *identifier
;; *lambda
;; *cond
;; *application

(define expression-to-action
  (lambda (e)
    (cond
     ((atom? e)
      (atom-to-action e))
     (else
      (list-to-action e)))))

(define atom-to-expression
  (lambda (e)
    (cond
     ((number? e) *const)
     ((eq? e #t) *const)
     ((eq? e #f) *const)
     ((eq? e (quote cons)) *const)
     ((eq? e (quote car)) *const)
     ((eq? e (quote cdr)) *const)
     ((eq? e (quote null?)) *const)
     ((eq? e (quote eq?)) *const)
     ((eq? e (quote atom?)) *const)
     ((eq? e (quote zero?)) *const)
     ((eq? e (quote add1)) *const)
     ((eq? e (quote sub1)) *const)
     ((eq? e (quote number?)) *const)
     (else *identifier))))

(define list-to-expression
  (lambda (e)
    (cond
     ((atom? (car e))
      (cond
       ((eq? (car e) (quote quote)) *quote)
       ((eq? (car e) (quote lambda)) *lambda)
       ((eq? (car e) (quote cond)) *cond)
       (else *application))))))

(define value
  (lambda (e)
    (meaing e (quote ()))))

(define meaning
  (lambda (e table)
    ((expression-to-action e) e table)))

(define *const
  (lambda (e table)
    (cond
     ((number? e) e)
     ((eq? e #t) #t)
     ((eq? e #f) #f)
     (else
      (build (quote primitive) e)))))

(define *quote
  (lambda (e table)
    (text-of e)))

(define text-of second)

(define *identifier
  (lambda (e table)
    (lookup-in-table e table initial-table)))

(define initial-table
  (lambda (name)
    (car (quote ()))))

;; e : (lambda (args) (body))
(define *lambda
  (lambda (e table)
    (build (quote non-primitive)
           (cons table (cdr e)))))
;; > (non-primitive (table (args) (body)))

(define table-of first)
(define formals-of second)
(define body-of third)


(define question-of first)
(define answer-of second)
(define else?
  (lambda (x)
    (cond
     ((atom? x) (eq? x (quote else)))
     else #f)))

;; (cond
;;   (question answer) ;; line 1
;;   (question answer) ;; line 2
;;   (else answer))    ;; line n

(define evcon
  (lambda (lines table)
    ((else? (question-of (car lines)))
     (meaning (answer-of (car lines)) table))
    ((meaning (question-of (car lines)) table)
     (meaning (answer-of (car lines)) table))
    (else
     (evcon (cdr lines) table))))

;; lines가 null인 경우 처리가 없다.
;; question 중의 하나는 true 이어야 함.

(define cond-lines-of cdr)

(define *cond
  (lambda (e table)
    (evcon (cond-lines-of e) table)))

(define evlis
  (lambda (args table)
    (cond
     ((null? args) (quote ()))
     (else
      (cons (meaning (car args) table)
            (evlis (cdr args) table))))))

(define function-of car)
(define arguments-of cdr)

(define *application
  (lambda (e table)
    (apply
     (meaning (function-of e) table)
     (evlis (arguments-of e) table))))

