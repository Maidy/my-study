(define member?
  (lambda (a lat)
    (cond
     ((null? lat) #f)
     ((eq? a (car lat)) #t)
     (else (member? a (cdr lat))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; CHAPTER 3. Cons the Magnificent
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define multirember
  (lambda (a lat)
    (cond
     ((null? lat) '())
     ((eq? a (car lat))
      (multirember a (cdr lat)))
     (else
      (cons (car lat) (multirember a (cdr lat)))))))

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
     ((eq? (car (cdr aexp)) (quote +)) (+ (value (car aexp)) (value (car (cdr (cdr aexp))))))
     ((eq? (car (cdr aexp)) (quote *)) (* (value (car aexp)) (value (car (cdr (cdr aexp))))))
     ((eq? (car (cdr aexp)) (quote ^)) (expt (value (car aexp)) (value (car (cdr (cdr aexp)))))))))

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

(define zero?
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
     ((zero? m) n)
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
