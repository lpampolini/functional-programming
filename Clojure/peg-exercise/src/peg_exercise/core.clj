(ns peg-exercise.core
  (require [clojure.set :as set])
  (:gen-class))

;; (use 'clojure-noob.core :reload)

(declare successfull-move prompt-move game-over query-rows)

; We`ll first define a function that can calculate the triangular number, that can be used to know
; the las number in each row
(defn tri*
  "Generates a lazy sequence of triangular numbers"
  ([] (tri* 0 1))
  ([sum n]
   (let [new-sum (+ sum n)]
     (cons new-sum (lazy-seq (tri* new-sum (inc n))))))
  )

; We are implementing the tri with the default value
(def tri (tri*))

(defn triangular?
  "Check if a number is a triangular number"
  [n]
  (= n (last (take-while #(>= n %) tri))))

(defn row-tri
  "It takes the row number, and returns its triangular number"
  [row-number]
  (last (take row-number tri)))

(defn my-row-num
  "It takes a board position and returns the row it belongs to"
  [board-position]
  (loop [row-counter 1]
    (if (<= board-position (row-tri row-counter))
      row-counter
      (recur (inc row-counter)))))

; Much better solution because we already have the list of triangular numbers
(defn row-num
  "Another solution for the row number function"
  [pos]
  (inc (count (take-while #(> pos %) tri))))

(defn connect
  "Functio to form a mutual connection between two positions"
  [board max-pos pos neighbor destination]
  (if (<= destination max-pos)
    (reduce (fn [new-board [p1 p2]]
              (assoc-in new-board [p1 :connections p2] neighbor))
            board [[pos destination] [destination pos]])
    board))

;; Note: assoc-in lets you return a new map with the given value at the specified nesting
; It results in {:cookie {:monster {:vocals "Finnt roll"}}}
(assoc-in {} [:cookie :monster :vocals] "Finnt roll")
; It results in {1 {:connections {4 2}}}
(assoc-in {} [1 :connections 4] 2)
;; get-in lets you look up values in nested maps
(get-in {:cookie {:monster {:vocals "Finnt roll"}}} [:cookie :monster])