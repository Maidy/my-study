(ns pcjvm.core)

(defn deposit [balance amount]
  (dosync
   )
  (alter balance + amount))

(defn withdraw [balance amount]
  (alter balance - amount))
