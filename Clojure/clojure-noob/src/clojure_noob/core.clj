(ns clojure-noob.core
  (:gen-class))

;; (use 'clojure-noob.core :reload)

(defn main
	"I don't do a whole lot ... yet."
	[& args]
	(println "I'm a little teapot again!")

	; IF-STATEMENT
	(if false
	(do (println "First string")
	"The flag was true")
	(do (println "second string")
	"The flag was false"))

	(when true
	(println "Success")
	true)

	; it returns first because it's the first true value
	(def testOr (or false nil :first :second :third))
	; it returns nil because it`s the first false value
	(def testAndWithNil (and :first nil :second))
	; it returns :second because it`s the last true value
	(def testAnd (and :first true :second))

	;Declaring something with def 
	(def names ["John", "Mary", "Gerald"])
 
    ; Increment the 1 value, returning 2
    (inc 1)
)

(defn error-message
  [severity]
  (str "Error message. The severity is "
  	(if (= severity :mild)
     	"Mild, but we are not sure"
     )
   )
)

(defn data-structures
  	[& args]
  	
  	(def number 10)
    (def numberFloat 1.2)
    (def ratioNumber 1/5)
    ;(println "The integer number values is" number)

    ; Different forms of declaring Maps
    (def emptyMap {})
    (def nameMap {:firstName "Luis", :lastName "Pampolini"})
    (def mapWithHash (hash-map :a 1 :b 2))
    
    ; Accessing dictionaties properties by keys
    (get nameMap :firstName)
    (get nameMap :lastName)
    (:lastName nameMap) ; This is the most common usage among clojure devs
    
    ; Accessing inner values of nested maps
    (def nestedMap {:country "USA", 
                    :languages {:native "English", 
                                :other "Spanish"}
                    })
    (get-in nestedMap [:languages :other])
    (:religion nestedMap "This country has no default religion")
    
    ; Working with vectors
    (get [3 2 1] 0)
    (get ["a" {:name "John"} "c"] 1)
    ; Another way to declare vectors
    (vector "creepy" "full" "moon")
    ; To add another element to the vector
    (conj [1 2 3] 4)
    
    ; Working with lists
    (def firstList '(1 2 3 4))
    ; Another way to declare list
    (list "John" "Mary" "Bob")
    ; To retrieve elements from list, use nth
    (nth firstList 1)
    ; We can use conj to include an item into the begginig of the list
    (conj firstList 5)
    
    ; IMPORTANT NOTES
    ; * nth for lists is much slower than using get for vectors
    ; * lists are used for macro and when we need to easily add items to the beggining of a sequence
    ; * For the rest vector would provide a better solution
    
    ; sets - Collections of unique values
    ; It has two kinds: hash set and sorted set (hash sets are used more often)
    #{"John Milles" 20 :icicle} ; literal notation for a hash set
    
    ; Regardless of having a couple of 1s and 2s,
    ; the final result will be #{1 2} because we only have unique values inside the hash
    (hash-set 1 1 2 2)
    
    (def hashMap (set [3 3 3 4 4])) ; Another way of declaring it
    
    ; Same happens if we try to add an item that already existis inside a hash
    (conj #{1 2 3} 1) ; Result in #{1 2 3}
    (def country #{:name :language :flagColor})
    (conj country :flagColor) ; The key is not included in case it already has
    
    ; We can check if a hash set contains a specific item
    (contains? #{:a :b} :a)
    (contains? #{:a :b} :c)
    ; Note: Whenever we are check for nil values inside the set, use contains?
    ; If we use get, it would bring nil regardless of having the element or not
)

; Examples of functions calls
(defn functions
    "Function to fool around with function calls"
	[]
  	
    ; A function return can be used as a function to another operation
    ((or + -) 3 4 2)
    
    ; Defining functions - Five main parts
    ; 1 - defn
    ; 2 - function name
    ; 3 - Optional docstring describing the function
    ; 4 - parameters described in brackets
    ; 5 - function body
    (defn myFunction
      "Just function declaration example"
      [parameter1 parameter2]
      (println (str parameter1 parameter2))
    )
    
    (defn do-something [p1 p2 p3] (println p1 p2 p3))
    (defn do-something-else [p1 p2] (println p1 p2))
    (defn do-nothing [p1] (p1))
    
    ; We can have arity overloading. When a function has different behaviors deppending on
    ; the amount of parameters provided
    (defn multi-arity
      ;; 3-arity arguments and body
      ([param1 param2 param3]
      (do-something param1 param2 param3))
      ;; 2-arity arguments and body
      ([param1 param2]
      (do-something-else param1 param2))
      ;; 1-arity arguments and body
      ([param1]
      (do-nothing param1))
    )
    
    ; Arity overloading can be used to defined default arugment values
    (defn chop
      "Describe chop type on someone"
      ([personName chopType]
       (str "I " chopType " chop " personName " in the head"))
      ([personName]
       (chop personName "karate"))
      )
    
    ; We can have a rest parameter as well (like "rest" of the arguments)
    ; the rest parameter is indicated with the & symbol
    (defn complain
      [kidName]
      (str "Hey you! " kidName))
    
    (defn complainMany
      [& kidsNames]
      (map complain kidsNames))
    (complainMany "John" "Mary" "Bob")
    
    (defn complainManyForReason
      [reason & names]
      (str "Hey! " (clojure.string/join ", " names) ". Stop " reason))
    ;(complainManyForReason "making loud noise" "John" "Mary" "Bob")
    
    ; Destructuring - More sophisticated way of declaring parameters
    (defn choose
      [[first-choice second-choice & other-choices]]
      (println "First: " first-choice)
      (println "Second: " second-choice)
      (println "Others: " (clojure.string/join "," other-choices)))
    ;(choose ["Yellow" "Blue" "Red" "Purple" "Grey"])
    
    (defn showLocation
      [{lat :lat lng :lng}]
      (println "My location - Latitude: " lat)
      (println "My location - Longitude: " lng))
    ;(showLocation {:lat 10 :lng 20})
    
    ;; Anonymous functions - we can declare functions without name
    (fn 
      [params]
      ; function body
      )
    
    (map (fn [name] (str "Hi, " name))
         ["John", "Mary"])
    (map #(str "Hi, " %) ["John", "Mary"])
    
    ((fn [x] (* x 3)) 8)
    
    (def myMupltiplicationFunc (fn [x] (* x 3)))
    (myMupltiplicationFunc 12)
    
    ; Another way of declaring anonymous functions
    (#(* % 3) 8) ; % is replaced by the paremeter
    
    ; We can also have multiple parameters
    (#(str "Hello friends " %1 " and " %2) "John" "Mary")
    
    ; We can use the rest parameter as well
    (#(identity %&) 1 "Hello" :param)
    
    ; Fuctions can return other functions, called closures 
    (defn inc-maker
      "Function to create a custom incrementor function"
      [inc-by]
      #(+ % inc-by))
    
    (def inc3 (inc-maker 3))
    (inc3 7)
)

(defn structures
  []
  ; let binds names to values
  ; let has two main uses. To provide clarity by letting you name things.
  ; and also to allow you evaluate an expression only once and reuse the result
  (let [x 3] x)
  (def dalmata-list ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
  (let [dalmata (take 2 dalmata-list)] dalmata)
  ; By using [pongo & dalmata], we are actually getting the head and tail from the list
  (let [[pongo & dalmata] dalmata-list] [pongo dalmata])
  
  ; Let creates a new scope. E.g.
  ; we can set x as 0
  (def x 0)
  ; But by using let, we can have a different values inside the new scope
  (let [x 1] x) ; I returns 1, which is the new value inside the let scope
  (let [x (inc x)] x) ; It`ll return 1, which is the inc of x on outer scope
  
  (into [] (set [:a :a]))
  
  ; loop
  (loop [iteration 0] ; Starts with a binding and initial value
    ;(println (str "Iteration " iteration))
    (if (> iteration 3)
      (println "Done!")
      (recur (inc iteration))))
  
  ; testing regular expressions
  (re-find #"^left-" "left-eye")
  (re-find #"^left-" "cleft-chin") ; this will return nil since it doesn`t start with left

  ; (fn [list] (nth list (- (count list) 1))) function to get the last list element
  
  ; The pattern of processing each element in a sequence and build a result is very common
  ; Clojure has a built-in function for it called reduce
  (reduce + [1 2 3 4])
  (reduce + 15 [1 2 3 4]) ; It can also take an initial value (e.g. 15)
)

(defn exercises
  "Suggested exercises from chapter 3 - Clojure for the brave and true"
  []
  (defn add-hundred
    "Just takes a number and add a 100 to it"
    [number]
    (+ number 100))
  
  ; A function that returns an Anonymous function
  (defn dec-maker
    "Function to decrement"
    [dec-by]
    #(- % dec-by))
  
  (def dec9 (dec-maker 9))
  
  (defn mapset
    "Function that works like a map, but returns the value as a set"
    [function values-vector]
    (reduce (fn [final-set value]
              (into final-set (set [(function value)]))) #{} values-vector))
  
  ; Macro sometimes might make your code more readable
  (-> [2 5 4 1 3 6] (reverse) (rest) (sort) (first)) ; We know the sequence of operations that will be applied and the order
  (first (sort (rest (reverse [2 5 4 1 3 6])))) ; Here, it might get a little harder to understand
  
  ; The macro ->> forces the data structure to be turned into a list
  (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (reduce +))
  (reduce + (map inc (take 3 (drop 2 [2 5 4 1 3 6])))) ; Another way of writing the expression above
)

(defn alien-exercise
  "Adapt body parts function to work for multiple parts"
  []
  
  (def alien-body-parts
  [{:name "head" :size 3}
   {:name "first-eye" :size 1}
   {:name "first-ear" :size 1}
   {:name "mouth" :size 1}
   {:name "nose" :size 1}
   {:name "neck" :size 2}
   {:name "first-shoulder" :size 3}
   {:name "first-upper-arm" :size 3}
   {:name "chest" :size 10}
   {:name "back" :size 10}
   {:name "first-forearm" :size 3}
   {:name "abdomen" :size 6}
   {:name "first-kidney" :size 1}
   {:name "first-hand" :size 2}
   {:name "first-knee" :size 2}
   {:name "first-thigh" :size 4}
   {:name "first-lower-leg" :size 2}
   {:name "first-achilles" :size 1}
   {:name "first-foot" :size 2}])
  
  (def names-ordinal ["second-" "third-" "fourth-" "fifth"])
  
  (defn matching-part-multiple
    "Create a new part map replacing left by right and setting the same size"
    [part]
    (if (clojure.string/starts-with? (:name part) "first")
      (loop [parts-counter 0 final-parts []]
        (if (> parts-counter 3)
          final-parts
          (recur (inc parts-counter)
                (conj final-parts
                      {:name (clojure.string/replace (:name part) #"^first-" (get names-ordinal parts-counter))
                       :size (:size part)}
                      ))))))
  
  (defn symmetrize-body-parts-reduce
    "Expect a seq of maps that have a :name and :size"
    [body-parts]
    ; Reduce will apply the anonymous function fn for every item in body-parts
    (reduce (fn [final-body-parts part]
        (into final-body-parts (set [part (matching-part-multiple part)])))
        [] body-parts))
  
  (symmetrize-body-parts-reduce alien-body-parts)
)

(defn seq-abstraction
  []
  ; If the core sequence functions first, rest and cons work on a data strcuture
  ; the data structure implements the sequence abstraction
  ; Notice that lists, vectors, sets and maps all implements the sequence 
  ; abstraction, so they work with map
  (defn applyTitle
    [topic]
    (str topic " for the Brave and True"))
  
  (map applyTitle ["Programmers" "Code"])
  (map applyTitle '("Language" "Programming"))
  (map applyTitle #{"Computer" "Logic"})
  (map #(applyTitle (second %)) {:issue "Glitch"})
  
  ; functions like 'first' work with multiple data structures because Clojure
  ; uses two forms of indirection (mechanism eployed by language so that one name
  ; can have multiple related meanings). 'first' has multiple, data structure-specific 
  ; meanings. Indirection is what makes abstraction possible.
  ; We have polymorphic functions dispatch to different function bodies based on the
  ; type of argument supplied (not so different from multiple-arity functions)
  ; When ir comes to sequence, Clojure does a lightweight conversion when a sequence
  ; is expected (e.g. map, first, rest or cons calls) - it calls the seq function first
  ; to obtain a data strcuture that allows first, rest and cons
  (seq '(1 2 3))
  (seq [1 2 3])
  (seq #{1 2 3})
  (seq {:name "John" :age 25})
  
  ; Working with Maps!
  
  ; Using map with multiple arguments
  ; The elements of the first collection will be passed as the first argument
  ; and the elements of the second collection will be passed as the second
  ; so the execution will be like (str "a" "A") (str "b" "B") (str "c" "C")
  (map str ["a" "b" "c"] ["A" "B" "C"]) ; result is ("aA" "bB" "cC")
  
  (def human-consumption [8.1 7.3 6.6 5.0])
  (def critter-consumption [0.0 0.2 0.3 1.1])
  
  (defn unify-diet
    [human critter]
    {:human human :critter critter})
  
  ; Same happens in here. unify-diet receives the nth element from each argument
  ; (unify-diet 8.1 0.0) (unify-diet 7.3 0.2) and so on
  (map unify-diet human-consumption critter-consumption)
  
  ; We can also pass map a collection of functions
  ; You can use it to perform a set of calculations on different collection numbers
  (def sum #(reduce + %))
  (def avg #(/ (sum %) (count %)))
  (defn stats
    [numbers]
    (map #(% numbers) [sum count avg])) ; In this cas % will receive the functions
  
  (stats [3 4 10]) ; We`ll get a list with the sum, count and average
  (stats [80 1 44 13 6])
  
  ; Clojurists often use map to retrieve the value associated to a keyword from a
  ; collection of maps
  (def identities 
    [{:name "Batman" :real "Bruce"}
     {:name "Spider-man" :real "Peter"}
     {:name "Santa" :real "Mom"}
     {:name "Easter-bunny" :real "Dad"}])
  (map :real identities)
  )