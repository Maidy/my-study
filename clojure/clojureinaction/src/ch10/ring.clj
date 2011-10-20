(ns ch10.ring
  (:use ring.adapter.jetty)
  (:use ring.middleware.params))

;; (use 'ring.util.serve)
;; (serve ch10.ring/handler)

(defn hello [req]
  {:body "<p>Hello World!</p>"})

(defn echo [req]
  (let [qs (:query-string req)
        params (apply hash-map (.split qs "="))]
    {:body (str "<pre>" req "</pre>")}))


(defn new-echo [req]
  (println "new")
  {:body (get-in req [:query-params "echo"])})

(def echo-app (wrap-params new-echo))
