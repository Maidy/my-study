;; 2.1 A grammar for subset of english

;; GRAMMAR
;; sentence    => noun-phrase + verb-phrase
;; noun-phrase => article + noun
;; verb-phrase => verb + noun-phrase
;; article     => the, a, ...
;; noun        => man, ball, woman, table, ...
;; verb        => hit, took, saw, liked, ...

(defun sentence ()    (append (noun-phrase) (verb-phrase)))
(defun noun-phrase () (append (Article) (Noun)))
(defun verb-Phrase () (append (Verb) (noun-phrase)))
(defun Article ()     (one-of '(the a)))
(defun Noun ()        (one-of '(man ball woman table)))
(defun Verb ()        (one-of '(hit took saw liked)))

(defun one-of (set)
  "Pick one element of set, and make a list of it."
  (list (random-elt set)))

(defun random-elt (choices)
  "Choose an element from a list at random"
  (elt choices (random (length choices))))

;; additional GRAMMAR
;; noun-phrase => article + adj* + noun + prep*
;; adj*        => 0, adj + adj*
;; prep*       => 0, prep + prep*
;; adj         => big, little, blue, green, ...
;; prep        => to, in, by, with, ...
(defun noun-phrase () (append (Article) (adj*) (Noun) (prep*)))
(defun adj* ()
  (if (= (random 2) 0)
      nil
      (append (adj) (adj*))))
(defun prep* ()
  (if (random-elt '(t nil))
      (append (prep) (prep*))
      nil))
(defun Adj  () (one-of '(big little blue green)))
(defun Prep () (one-of '(to in by with)))

;; 2.3 A rule-based solution

(defparameter *simple-grammar*
  '((sentence    -> (noun-phrase verb-phrase))
    (noun-phrase -> (article noun))
    (verb-phrase -> (verb noun-phrase))
    (article     -> the a)
    (noun        -> man balld woman table)
    (verb        -> hit took saw liked))
  "A grammar for a trivial subset of English")

(defvar *grammar* *simple-grammar*
  "The grammar used by generate. Initially, this is *simple-grammar*, but we can switch to other grammar.")

(defun rule-lhs (rule)
  "The left-hand side of a rule."
  (first rule))

(defun rule-rhs (rule)
  "The right-hand side of a rule."
  (rest (rest rule)))

(defun rewrite (category)
  "Return a list of the possible rewrites for this category."
  (rule-rhs (assoc category *grammar*)))

;; from p19
(defun mappend (fn the-list)
  "Apply fn to each element of list and append the result."
  (apply #'append (mapcar fn the-list)))

(defun generate (phrase)
  "Generate a random sentence or phrase"
  (cond ((listp phrase)
	 (mappend #'generate phrase))
	((rewrite phrase)
	 (generate (random-elt (rewrite phrase))))
	(t
	 (list phrase))))

(defun generate (phrase)
  "Generate a random snetence of phrase."
  (if (listp phrase)
      (mappend #'generate phrase)
      (let ((choices (rewrite phrase)))
	(if (null choices)
	    (list phrase)
	    (generate (random-elt choices))))))

;; ex 2.1
(defun generate (phrase)
  "Generate a random sentence or phrase."
  (let ((choices (rewrite phrase)))
    (cond ((listp phrase)
	   (mappend #'generate phrase))
	  ((null choices)
	   (list phrase))
	  (t
	   (generate (random-elt choices))))))

;; ex 2.2
;; differntiate terminal / non-terminal symbol ???

;; 2.4 Two paths to follow

;; 2.5 Changing the grammar without changing the program
(defparameter *bigger-grammar*
  '((sentence    -> (noun-phrase verb-phrase))
    (noun-phrase -> (Article Adj* Noun PP*) (Name) (Pronoun))
    (verb-phrase -> (Verb noun-phrase PP*))
    (PP*         -> () (PP PP*))
    (Adj*        -> () (Adj Adj*))
    (PP          -> (Prep noun-phrase))
    (Prep        -> to in by with on)
    (Adj         -> big little blue green adiabatic)
    (Article     -> the a)
    (Name        -> Pat Kim Lee Terry Robin)
    (Noun        -> man balld woman table)
    (Verb        -> hit took saw liked)
    (Pronoun     -> he she it these those that))
  "A grammar for a trivial subset of English")

;; (setf *grammar* *bigger-grammar*)
;; (generate 'sentence)

;; 2.6 Using the same data for several programs

(defun generate-tree (phrase)
  "Generate a random snetence tree of phrase."
  (if (listp phrase)
      (mapcar #'generate-tree phrase)
      (let ((choices (rewrite phrase)))
	(if (null choices)
	    (list phrase)
	    (cons phrase
		  (generate-tree (random-elt choices)))))))

(defun generate-all (phrase)
  "Generate a list of all possible expansions of this phrase."
  (cond ((null phrase) (list nil))
	((listp phrase)
	 (combine-all (generate-all (first phrase))
		      (generate-all (rest phrase))))
	((rewrite phrase)
	 (mappend #'generate-all (rewrite phrase)))
	(t (list (list phrase)))))

(defun combine-all (xlist ylist)
  "Return a list of lists formed by appending a x to and x.
E.g., (combine-all '((a) (b)) '((1) (2)))
-> ((A 1) (B 1) (A 2) (B 2))."
  (mappend #'(lambda (y)
	       (mapcar #'(lambda (x) (append x y)) xlist))
	   ylist))

;; *bigger-grammar* -> infinite recursion !!!
;; (setf *grammar* *simple-grammar*)

;; 2.7 Exercise

;; ex 2.3
