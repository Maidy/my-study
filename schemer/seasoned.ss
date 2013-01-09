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
