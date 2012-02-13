(ns ch9.mysql.model.charge
  (:require clj-record.boot))

;; id             INT
;; user_id        INT
;; amount_dollars INT
;; amount_cents   INT
;; category       VARCHAR
;; vendor_name    VARCHAR
;; date           DATETIME

(defn hello-charge []
  (println " Hello charge"))

(def db
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "mysql"
   :user ""
   :password ""
   :subname "//localhost/test"})

(clj-record.core/init-model
 (:associations
  (belongs-to user))
 (:validation
  (:amount_dollars "Must be positive!" #(>= % 0))
  (:amount_cents "Must be positive!" #(>= % 0)))
 (:callbacks
  (:before-save (fn [record]
                  (if-not (:category record)
                    (assoc record :category "uncategorized")
                    record)))))


