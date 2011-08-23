(defparameter *titles*
  '(Mr Mrs Miss Ms Sir Madam Dr Admiral Major General))

(defun first-name (name)
  "Select the first name from a name represented as a list."
  (if (member (first name) *titles*)
      (first-name (rest name))
      (first name)))

;; ex 1.1
(defparameter *sub-titles*
  '(MD Jr))

(defun last-name (name)
  "Select the last name from a name represented as a list."
  (if (member (first (reverse name)) *sub-titles*)
      (last-name (reverse (rest (reverse name))))
      (first (last name))))

;; ex 1.2
;; (pow 3 2) = 3 * 3 = 9
(defun pow (v e)
  (if (= e 0)
      1
      (* v (pow v (- e 1)))))

;; ex 1.3
(defun count-atoms (e)
  '())
