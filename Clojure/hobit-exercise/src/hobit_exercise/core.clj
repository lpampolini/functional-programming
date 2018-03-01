(ns hobit-exercise.core
  (:gen-class))

;; (use 'hobit-exercise.core :reload)

; Create the hobit list of body parts
(def hobit-body-parts
  [{:name "head" :size 3}
   {:name "left-eye" :size 1}
   {:name "left-ear" :size 1}
   {:name "mouth" :size 1}
   {:name "nose" :size 1}
   {:name "neck" :size 2}
   {:name "left-shoulder" :size 3}
   {:name "left-upper-arm" :size 3}
   {:name "chest" :size 10}
   {:name "back" :size 10}
   {:name "left-forearm" :size 3}
   {:name "abdomen" :size 6}
   {:name "left-kidney" :size 1}
   {:name "left-hand" :size 2}
   {:name "left-knee" :size 2}
   {:name "left-thigh" :size 4}
   {:name "left-lower-leg" :size 2}
   {:name "left-achilles" :size 1}
   {:name "lef-foot" :size 2}])

(defn matching-part
  "Create a new part map replacing left by right and setting the same size"
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-") 
   :size (:size part)})

; This function is using a very common strategy in FP
; It keeps spliting a list of items in head and tail, then it process the head
; adds it to some result and uses recursion to keep processing the tail
(defn symmetrize-body-parts
  "Expect a seq of maps that have a :name and :size"
  [body-parts]
  ; We initiate the looping. The tail of the sequence will be bound to "remaining-parts"
  ; that initially has the full sequence in "body-parts"
  ; We also create a result sequence, "final-body-parts" that is initially []
  (loop [remaining-parts body-parts final-body-parts []]
    ; If the remaining-parts is empty, means we have already processed the list
    (if (empty? remaining-parts)
      final-body-parts ; return final-body-parts
      
      ; Otherwise, we split the list into head ("part") and tail ("remaining")
      ; "let" is telling to clojure to create a new scope and associate "part" with the first
      ; element of "remaining-parts" and associate "remaining" with the rest of elements in
      ; "remainging-parts"
      (let [[part & remaining] remaining-parts]
        ; we recur with remaining that gets shorter every iteration
        (recur remaining
               ; "set" tells clojure to create a set that has "part" and the matching part
               ; of "part" by colling the matchin-part function passing part as paremeter
               ; finally "into" will add the elements of set into the vector final-body-parts
               (into final-body-parts (set [part (matching-part part)])))))))


; Using reduce to process a list is much better than using a loop since it's a structure
; that is usually used to process a list. Using loop, other people would have to read the
; loop boddy first to identify what you're doing
(defn symmetrize-body-parts-reduce
  "Expect a seq of maps that have a :name and :size"
  [body-parts]
  ; Reduce will apply the anonymous function fn for every item in body-parts
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          [] body-parts))

(defn hit
  [body-parts]
  (let [sym-parts (symmetrize-body-parts-reduce body-parts) 
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
      	accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))