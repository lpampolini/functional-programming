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
  ; uses two forms of indirection (mechanism employed by language so that one name
  ; can have multiple related meanings). 'first' has multiple data structure-specific
  ; meanings. Indirection is what makes abstraction possible.
  ; We have polymorphic functions dispatch to different function bodies based on the
  ; type of argument supplied (not so different from multiple-arity functions)
  ; When it comes to sequence, Clojure does a lightweight conversion when a sequence
  ; is expected (e.g. map, first, rest or cons calls) - it calls the seq function first
  ; to obtain a data strcuture that allows first, rest and cons
  (seq '(1 2 3))
  (seq [1 2 3])
  (seq #{1 2 3})
  (seq {:name "John" :age 25})
  
  ;;; Working with Maps! ;;;
  
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
    (map #(% numbers) [sum count avg])) ; In this case % will receive the functions
  
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

  ;;; Working with reduce! ;;;

  ; assoc syntax (assoc map key val) | (assoc map key val & kvs)
  ; e.g.
  (assoc {} :key1 "value" :key2 "another value") ; => {:key1 "value", :key2 "another value"}

  ; reduce treats {:max 30 :min 10 :avg 20} as a sequence of vectors ([:max 30] [:min 10] [:avg 20])
  (reduce (fn
            [new-map [key val]]
            (assoc new-map key (inc val))) {} {:max 30 :min 10 :avg 20})

  ; The function above could be trasnlated to
  (assoc (assoc (assoc {} :max (inc 30)) :min (inc 10)) :avg (inc 20))

  ; It can also be used to filter keys from map
  (reduce (fn [new-map [key val]]
            (if (> val 4)
              (assoc new-map key val)
              new-map)) {} {:human 4.1 :critter 3.9})

  ;;; Take, drop, take-while, and drop-while ;;;

  ; take and drop both take two arguments: a number and a sequence
  ; take - return the first n elements
  ; drop - return sequence with first n elements removed
  (take 3 [0 1 2 3 4 5 6 7 8 9 10]) ; (0 1 2)
  (drop 3 [0 1 2 3 4 5 6 7 8 9 10]) ; (3 4 5 6 7 8 9 10)

  ; take-while and drop-while takes a predicate function to determine when it should stop taking/droping
  (def food-journal
    [{:month 1 :day 1 :human 5.3 :critter 2.3}
     {:month 1 :day 2 :human 5.1 :critter 2.0}
     {:month 2 :day 1 :human 4.9 :critter 2.1}
     {:month 2 :day 2 :human 5.0 :critter 2.5}
     {:month 3 :day 1 :human 4.2 :critter 3.3}
     {:month 3 :day 2 :human 4.0 :critter 3.8}
     {:month 4 :day 1 :human 3.7 :critter 3.9}
     {:month 4 :day 2 :human 3.7 :critter 3.6}
     ])

  ; Retrieve just January and february data with take-while
  ; It only works for sorted data thou. In the first false result from the predicate function, it returns
  ; the results up to that point
  (take-while #(< (:month %) 3) food-journal)
  ; It keeps dropping elements until the predicate function tests true
  (drop-while #(< (:month %) 3) food-journal)

  ; We can combine take-while and drop-while to retrieve only data from February and March
  (take-while #(< (:month %) 4) (drop-while #(< (:month %) 2) food-journal))

  ;;; Filter and some ;;;

  ; We can use filter to return all the elements of a sequence that test true for the predicate function
  ; filter is very useful, but it can endup processing all your data when it isn't reslly necessary
  ; In this specific case take-while and drop-while work bettter because we already know the set
  ; of data is sorted by date, so it doesn't have to go through the whole set to bring the data we want
  (filter #(> (:human %) 5) food-journal)
  (filter #(< (:month %) 3) food-journal)

  ; In case you just want to test if a collection contains any values that test true for a predicate
  ; function, you can just use some. It'll return the first truthy value returned by the predicated function
  (some #(> (:critter %) 4) food-journal) ; It returns nil since we have no critter higher than 4
  (some #(< (:critter %) 2.5) food-journal) ; true

  ; In the tests above, we were just testing if the collection had a value that satisfied the condition
  ; In order return the first value that matches it, we should run
  (some #(and (> (:critter %) 3) %) food-journal) ; and returns the last true value

  ;;; Sort and sort-by ;;;

  ; We can sort things in asc
  (sort [3 1 2]) ; = (1 2 3)

  ; sort-by allows you to apply a function (often called key function) to the elements of a sequence
  ; and then uses the values it returns to determine the sort order
  (sort-by count ["aaa" "c" "bb"]) ; = ("c" "bb" "aaa") ; count is the key function here

  ;;; Concat ;;;
  ; It can be used to append members of one sequence to the end of another
  ; (concat A B) - We append the values from sequence B into sequence A
  (concat [1 2] [3 4]) ; = (1 2 3 4)

  ;;; Lazy seqs ;;;

  ; We saw that map first calls seq on the passed collection. It actually returns a lazy seq
  ; A lezy seq is a seq whose members are not computed unless we try to access them
  ; Computing the seq's members is called realizing the seq
  ; Deferring the computation until the moment this is really needed makes it more efficient
  (def source-database
    {0 {:suspect? false, :has_pulse? true :name "John"}
     1 {:suspect? false, :has_pulse? true :name "Peter"}
     2 {:suspect? true, :has_pulse? false :name "Nosferatu"}
     3 {:suspect? false, :has_pulse? true :name "Mary"}})

  (defn vampire-related-details
    [social-security-number]
    (Thread/sleep 1000)
    (get source-database social-security-number))

  (defn vampire?
    [record]
    (and (:suspect? record) (not (:has_pulse? record)) record))

  (defn identity-vampire
    [social-security-numbers]
    (first (filter vampire? (map vampire-related-details social-security-numbers))))

  ; Important: A non lazy implementation of the map would first apply vampire-related-details to every
  ; member of the source-database before passing it to the filter. It doesn't apply the vampire-related-details
  ; to the memebers of source-database until you try to access the mapped element.

  ; Map returns almost imeditially - around 0.017 ms
  (time (def mapped-details (map vampire-related-details (range 0 1000000))))

  ; You can think of lazy sequences consisting of two parts
  ; 1. The recipe function for how to realize the elements of a sequence
  ; 2. And the elements that have been realized so far

  ; mapped-details defined above is unrealized, but once you try yo access a member, it'll use the recie
  ; to generate the elemented you requested.
  ; The operation bellow takes around 32 seconds to be executed. We'd expect it to take only one seconds
  ; since we were trying to access the very first element.
  ; It happends because Clojure chunks its lazy sequences. Whenever Clojure has to realize one element
  ; it also realizes some of the next elements as well. We wanted the first element to be realized, but
  ; Clojure went ahead and realized the next 31 elements as well, and it does it that way because it
  ; usually results in better performance.
  (time (first mapped-details))

  ; Happly laz seq elements need to be realized a single time,
  ; and running accessing the first element again takes almost no time
  (time (first mapped-details)) ; It takes 0.0037 ms

  ; It`ll take around 32 seconds to find the suspect
  (time (identity-vampire (range 0 1000000)))

  ;;; Infinity sequences ;;;

  ; (repeat "na") is an infinity sequence whose every string is the string "na"
  (concat (take 8 (repeat "na")) ["Batman!"])

  ; We can do the same with repeatedly
  (take 3 (repeatedly (fn [] (rand-int 10))))

  ; The recipe function has no way to know will be returned by the lazy function, so they just take arguments
  ; and execute fo as long as they keep comning
  (defn even-numbers
    ([] (even-numbers 0))
    ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

  (take 10 (even-numbers))
  )

(defn collection-abstraction
  []

  ; into
  ; Seq functions return a seq instead of the original data structure
  ; We can use into to convert the return value it back to the original value
  ; "identity" return the argumnts of a given data
  ; It can be mostly used to take two collections and add all the elements from the second into the first
  (map identity {:name "John" :age 25}) ; returns ([:name "John"] [:age 25])
  ; into convert the list of arguments generated by the map back to a dictionary (map)
  (into {} (map identity {:name "John" :age 25}))

  ; E.g. 2
  (map identity [:garlic :sesame-oil :fried-eggs]) ; returns (:garlic :sesame-oil :fried-eggs)
  (into [] (map identity [:garlic :sesame-oil :fried-eggs])) ; returns [:garlic :sesame-oil :fried-eggs]

  ; E.g. 3
  (map identity [:garlic-clove :garlic-clove]) ; (:garlic-clove :garlic-clove)
  ; In this case we end up filtering repeated entries since sets only have unique values
  (into #{} (map identity [:garlic-clove :garlic-clove])) ; #{:garlic-clove}

  ; The first argument from into doesn't have to be empty
  (into {:emotion "fear"} [[:name "John"]]) ; {:emotion "fear", :name "John"}
  (into ["cherry"] '("pine" "spruce")) ; ["cherry" "pine" "spruce"]

  ; It can work with equal data types as well
  (into {:favorite-pet "dog"} {:least-favorite-pet "bird"})

  ;;; conj ;;;

  ; It also add elements to a collection, but in a different way
  (conj [0] [1]) ; it returns [0 [1]]
  (into [0] [1]) ; it retus [0 1]
  ; In order to get the same result as in into
  (conj [0] 1)

  ; E.g. 2 - We can pass multiple elements to it
  (conj [0] 1 2 3 4)

  (conj {:time "midnight"} [:place "cemetery"]) ; it returns {:time "midnight", :place "cemetery"}

  ; conj and into do pretty much the same thing, except that conj takes a rest parameter as argument,
  ; and into takes a seqable data structure
  ; We can even write conj in function of into
  (defn my-conj
    [target & additions]
    (into target additions))

  (my-conj [0] 1 2 3)

  ;;; apply ;;;

  ; Apply basically explodes a seqable data structure so it can be passed to a function that expects a rest
  ; parameter. We`ll use apply often to explode the elements of a collection so they get passed to a function
  ; as separated arguments
  (max 0 1 2) ; It returns 2 that is the biggest number
  ; If we wanted to know the biggest value of a vector, the command bellow won't work because max returns
  ; the greatest of all parameters passed to it
  (max [0 1 2])
  ; In order to make it work we can use apply to make the seaqable [0 1 2] into rest parameters
  (apply max [0 1 2])
  ; We can define now into in function of conj
  (defn my-into
    [target additions]
    (apply conj target additions))

  ;;; partial ;;;

  ; partial takes a function and any number of arguments. It then returns a new function.
  ; when we call the new function, it calls the original with the original arguments along with the new ones
  ; E.g.
  (def add10 (partial + 10))
  (add10 5 6) ; It returns 21

  ; The anonymous function returned by add10 would be something like
  ; (fn [& more-args]
  ;   (apply + (into [20] more-args)))

  (def add-missing-elements
    (partial conj ["water" "earth" "air"]))

  (add-missing-elements "fire" "metal")

  ; Here is how a formal definition of patial would look like
  (defn my-partial
    [partial-function & args]
    (fn [& more-args]
      (apply partial-function (into args more-args))))

  ; In general, you want to use partials when you find you're repeating the same combination of functions
  ; and arguments in many different contexts. E.g.
  (defn lousy-logger
    [log-level message]
    (condp = log-level
      :warn (clojure.string/lower-case message)
      :emergency (clojure.string/upper-case message)))

  (def warn (partial lousy-logger :warn))
  (warn "Red Light ahead") ; This would be the same as (lousy-logger :warn "Red light ahead")

  ;;; complement ;;;
  ; If we wanted to change our function that finds vampires to find humans, it would look like this
  (defn identity-humans
    [social-security-numbers]
    (filter #(not (vampire? %))
            (map vampire-related-details social-security-numbers)))

  ; Instead of negating the vampire? function, we could get its complement
  (def not-vampire?
    (complement vampire?))

  ; Then our function to find humans would be changed to
  (defn identify-humans
    [social-security-numbers]
    (filter not-vampire? (map vampire-related-details social-security-numbers)))
  )