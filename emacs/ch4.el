(defun insert-current-time ()
  "Insert the current time"
  (interactive "*")
  (insert (current-time-string)))

(defvar insert-time-format "%X"
  "*Format for \\[insert-time] (c.f. 'format-time-string').")

(defvar insert-date-format "%x"
  "*Format for \\[insert-date] (c.f. 'format-date-string').")

(defun insert-time ()
  "Insert the current time according to insert-time-format."
  (interactive "*")
  (insert (format-time-string insert-time-format
                              (current-time))))

(defun insert-date ()
  "Insert the current date according to insert-date-format."
  (interactive "*")
  (insert (format-time-string insert-date-format
                              (current-time))))

(add-hook 'local-write-file-hooks
          'update-writestamps)

;; (defun update-writestamps ()
;;   "Find writestamps and replace them with the current time"
;;   (interactive "*")
;;   (save-excursion
;;     (save-restriction
;;       (save-match-data
;;         (widen)
;;         (goto-char (point-min))
;;         (while (search-forward "WS((" nil t)
;;           (let ((start (point)))
;;             (search-forward "))")
;;             (delete-region start (- (point) 2))
;;             (goto-char start)
;;             (insert-date)))))))

(defvar writestamp-format "%Y%M%D"
  "*Format for writestamps (c.f. 'format-time-string').")

(defvar writestamp-prefix "WRITESTAMP(("
  "*Unique string identifying start of writestamp.")

(defvar writestamp-suffix "))"
  "*String that terminates a writestamp.")

(defun update-writestamps ()
  "Find writestamps and replace them with the current time"
  (save-excursion
    (save-restriction
      (save-match-data
        (widen)
        (goto-char (point-min))
        (while (search-forward writestamp-prefix nil t)
          (let ((start (point)))
            (search-forward writestamp-suffix)
            (delete-region start
                           (match-beginning 0))
            (goto-char start)
            (insert (format-time-string writestamp-format
                                        (current-time)))))))))

