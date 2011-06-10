;; movie
;; http://www.vimeo.com/19404746

(ns robozzle.core
  (:use midje.sweet clojure.set))

(unfinished)

(defn snapshot [direction position] [direction position])
(defn position [snapshot] (second snapshot))
(defn direction [snapshot] (first snapshot))

(def displacement { :north [0 1], :east [1 0], :south [0 -1], :west [-1 0] })
(def right-rotation { :north :east, :east :south, :south :west, :west :north })
(def left-rotation { :north :west, :east :north, :south :east, :west :south})

(defn rotate [rotation-type current]
  (snapshot (rotation-type (direction current))
	    (position current)))
(def left (partial rotate left-rotation))
(def right (partial rotate right-rotation))

(fact "left changes the direction, but not position"
      (left (snapshot :north ...position...)) => (snapshot :west ...position...)
      (left (snapshot :east ...position...)) => (snapshot :north ...position...)
      (left (snapshot :south ...position...)) => (snapshot :east ...position...)
      (left (snapshot :west ...position...)) => (snapshot :south ...position...))

(fact "right changes the direction, but not position"
      (right (snapshot :north ...position...)) => (snapshot :east ...position...)
      (right (snapshot :east ...position...)) => (snapshot :south ...position...)
      (right (snapshot :south ...position...)) => (snapshot :west ...position...)
      (right (snapshot :west ...position...)) => (snapshot :north ...position...))

(defn forward [current]
  (snapshot (direction current)
	    (map +
		 (displacement (direction current))
		 (position current))))

(fact "forward changes the position, but not direction"
      (forward (snapshot :north [5 50])) => (snapshot :north [5 51])
      (forward (snapshot :south [5 50])) => (snapshot :south [5 49])
      (forward (snapshot :east [5 50])) => (snapshot :east [6 50])
      (forward (snapshot :west [5 50])) => (snapshot :west [4 50]))

(defn trail [voyage]
  (map position voyage))

(fact "a trail sequence of positions from a voyage"
      (trail []) => []
      (trail [ (snapshot .unimportant. ...first...)
	       (snapshot .unimportant. ...second...) ])
      => [ ...first... ...second... ])

(defn voyage [start moves]
  (reduce (fn [so-far move]
	    (conj so-far (move (last so-far))))
	  [start]
	  moves))

(fact "a voyage is created by successively applying move to snapshots"
      (voyage (snapshot :north [0 0]) [forward forward]) => [(snapshot :north [0 0])
							       (snapshot :north [0 1])
							       (snapshot :north [0 2])]
      (voyage ...start... []) => [...start...]
      (voyage ...start... [...move...]) => [...start... ...next...]
      (provided
       (...move... ...start...) => ...next...))


(defn win? [& {start :start
	       moves :moves
	       stars :goal-stars
	       move-count :within}]
  (subset? (set stars)
	   (set (take (inc move-count) (trail (voyage start moves))))))

;.;. There is an inevitable reward for good deeds. -- Ming Fu Wu
(fact "win"
      (let [start (snapshot :north [0 0])
	    winning-moves     [left forward right right forward forward]
	    moves-stop-short  [left forward right right forward]
	    misses-star-moves [left forward right right forward right]
	    goal [[-1 0] [1 0]]]
	(win? :start start
	      :moves winning-moves
	      :goal-stars goal
	      :within 500000) => truthy
	(win? :start start
	      :moves moves-stop-short
	      :goal-stars goal
	      :within 500000) => falsey
	(win? :start start
	      :moves misses-star-moves
	      :goal-stars goal
	      :within 500000) => falsey

        (win? :start start
	      :moves winning-moves
	      :goal-stars goal
	      :within 6) => truthy
        (win? :start start
	      :moves winning-moves
	      :goal-stars goal
	      :within 3) => falsey))
	

;; 실패 경우 - short or misses a star
;; number of moves
