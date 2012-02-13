(ns ch9.mysql.model.user
  (:require clj-record.boot))

(defn hello-user []
  (println " Hello user"))

(def db
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "mysql"
   :user ""
   :password ""
   :subname "//localhost/test"})

(clj-record.core/init-model
 (:associations
  (has-many charges)))
