(ns clj-adv.core
  (:require [clojure.string :as str]))

(def nodes {:living-room "You are in the living-room. A wizard is snoring loudly on the couch."
            :garden      "You are in a beautiful garden. There is a well in front of you."
            :attic       "You are in the attic. There is a giant welding torch in the corner."})

(def edges {:living-room [[:garden :west :door]
                          [:attic :upstairs :ladder]]
            :garden      [[:living-room :east :door]]
            :attic       [[:living-room :downstairs :ladder]]})

(def objects [:whiskey :bucket :frog :chain])

(def object-locations (atom {:living-room [:whiskey :bucket]
                             :garden      [:chain :frog]}))

;; (def object-locations (atom {:whiskey :living-room
;;                              :bucket  :living-room
;;                              :chain   :garden
;;                              :frog    :garden}))

(def location (atom :living-room))

(defn describe-location [loc nodes]
  (get nodes loc))

(defn describe-path [edge]
  (str "There is a " (name (nth edge 2)) " going " (name (nth edge 1)) " from here."))

(defn describe-paths [location edges]
  (map describe-path (get edges location)))

(defn objects-at [loc obj-locs]
  (get obj-locs loc))

;; (defn objects-at [loc objs obj-locs]
;;   (let [at-loc? (fn [o] (= (get obj-locs o) loc))]
;;     (filter at-loc? objs)))

(defn describe-objects [loc obj-locs]
  (map #(str "You see a " (name %) " on the floor.") (objects-at loc obj-locs)))

;; (defn describe-objects [loc objs obj-locs]
;;   (let [desc-obj (fn [o] (str "You see a " (name o) " on the floor."))]
;;     (map desc-obj (objects-at loc objs obj-locs))))

(defn look []
  (concat [(describe-location @location nodes)]
          (describe-paths @location edges)
          (describe-objects @location @object-locations)))

(defn walk [dir]
  (let [next (first (filter #(= (second %) dir) (get edges @location)))]
    (if (seq  next)
      (dosync (reset! location (first next))
              (look))
      ["You cannot go that way"])))

(defn pickup [object]
  (if (some #(= object %) (objects-at @location @object-locations))
    (dosync (swap! object-locations
                   (fn [ols]
                     (assoc ols :body (conj (:body ols []) object))))
            (str "You are now carring the " (name object) "."))
    "You cannot get that."))

(defn inventory []
  (str "Items - "  (str/join ", " (map name (objects-at :body @object-locations)))))
