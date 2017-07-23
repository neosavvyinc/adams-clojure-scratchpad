(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little Teapot"))
(println "Cleanliness is next to godliness")


(defn train
  []
  (println "Choo Choo!"))


(+ 1 (* 2 3) 4)

(if false
  (do (println "Success") "By Zeus's hammer!") 
  (do (println "Failure")  "By Aquaman's trident"))

(when true (println "Success!") "abra cadabra")

(nil? nil)

(if "Bears eat Beets" "bears beets Battlestar Galactica")
(if nil "This won't be the result because nil is falsey" "nil is falsey")

(= 1 1)
(= nil nil)
(= 1 2)

(or true false)
(or false true)
(and false true)
(and false false)
(and true true)
(and false true)

(and :free_wifi :hot_coffee)
(and :feelin_super_cool nil false)

(def failed-protagonist-names ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])

(defn error-message 
  [severity] 
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOOMED!")))

;; Maps
(def adam  {:firstName "Adam" :lastName "Parrish"})

(def nested {:name {:first "Adam" :last "Parrish" :middle "William"}})

(def hashMap (hash-map :a 1 :b 2))

;; find a value by key in map
(get hashMap :b)

(println  (get hashMap :c "Unicorns!"))

(println (get-in nested  [:name :last]))

(println (nested :name))

(println (:firstName adam)) ;; access a map similar to get above

(println (:ssn nested "Dude that ain't public")) ;; default when a failed lookup 

;; Vectors
(def numVector [3 2 1])

(println (get numVector 0)) ;; access the 0th element in a vector

(def numVector2 (vector "creepy" "full" "moon"))

(println (conj numVector2 "more" "strings"))


;; Lists
(def basicNumberList '(1 2 3 4))

;; get the nth element in the list
(println (nth basicNumberList 3))

(def anotherList (list 1 "two" {3 4}))

(println (nth anotherList 2))

;; elements are added to beginning of a list vs end of a vector

(println (conj anotherList 5))

;; Sets // Hash Sets and Sorted Sets
(def randoSet #{"kurt vonnegut" 20 :icicle})

(println randoSet)

(def randoSet2 (hash-set 1 1 2 2))

(println randoSet2)

(println (conj #{:a :b} :b))

;; contains to find set membership
(def pl println)
(pl (contains? randoSet2 1))
(pl (contains? randoSet2 100))

(pl (:a #{:a :b :c}))

;; FUNCTIONS

;; returns first truthy value + and uses it as operator for 1 2 3
(pl ((or + -) 1 2 3))

(map inc [1 2 3])

(defn too-enthusiastic
  "Return a cheer that might be a bit overly enthusiastic"
  [name]
  (str "OH. MY GOD! " name " YOU ARE THE COOLEST EVER"))

(too-enthusiastic "Dana")

(defn no-params [] "I take no params")
(defn one-param [one] (str "I take one param " one))
(defn two-param [one two] (str "Param one: " one " with " two))

(no-params)
(one-param "ARG")
(two-param "ARG" "ANON")

;; multi-arity functions
(defn x-chop
  "Describe the kind of chop your Karate will bring"
  ([name chop-type] 
   (str "I " chop-type " chop " name "! take that!"))
  ([name]
   (str (x-chop name "karate")))
)

(x-chop "Dana" "Tiger")
(x-chop "Dana")

(defn codger-communication
  [whippersnapper] 
  (str "Get off my lawn " whippersnapper "!!!"))
(codger-communication "Dana")

;; using the ...rest params
(defn codger
  [& whippersnappers]
(map codger-communication whippersnappers))

(codger "Dana" "Adam" "Curtis")

;; mixed use named params with ....rest which must come last
(defn favorite-things
  [name & things]
  (str "Hi my name is " name " and here are my favorite things: "
       (clojure.string/join ", " things)))

(favorite-things "Adam" "Gum" "Shows" "Programming" "Landing Safely after a long flight")

;; Destructuring
(defn my-first
  [[first-thing]] ; in a vector
  first-thing
)

(my-first ["oven" "bike" "war axe"])

(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (pl (str "Your first choice was: " first-choice))
  (pl (str "Your second choice was: " second-choice))
  (pl (str "We're ignoring the rest of your choices. "
           "Here they are so you can cry over them. " 
           (clojure.string/join ", " unimportant-choices))))

(chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])

(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (pl (str "Treasure Latitude: " lat))
  (pl (str "Treasure Longitude: " lng))
)

;;(announce-treasure-location {:lat 10.4 :lng 87.3})
(announce-treasure-location {:lng 10.4 :lat 87.3})

(defn steer-ship! [location] (pl "Heading there now!"))
(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}] ;; saves original map for use later
  (pl (str "Treasure latitude: " lat))
  (pl (str "Treasure longitude: " lng))
  (steer-ship! treasure-location)
)
(receive-treasure-location {:lat 99 :lng 64})

(defn illustrative-function 
  []
  (+ 1 304)
  30
  "joe")

(illustrative-function)
  
(defn number-comment [x] 
  (if (> x 6)
    "Oh my gosh! what a big number!"
    "Oh that's cute"))

(number-comment 4)

;; Anonymous Functions
(map (fn [name] (str "Hi " name)) ["Darth Vader" "Mr Magoo"])

((fn [x] (* x 3)) 8)
(#(* % 3) 8)

(#(str %1 " and " %2) "cornbread" "butter beans")

(defn inc-maker
  "Create a custom incrementor"
  [inc-by] 
  #(+ % inc-by))

(def inc3 (inc-maker 3))
(inc3 7)

(def asym-hobbit-body-parts [{:name "head" :size 3}
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
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

(symmetrize-body-parts asym-hobbit-body-parts)

(let [x 3] x)
(def dalmation-list ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
(let [dalmations (take 2 dalmation-list)] dalmations)

;; resume at loop 
(loop [iteration 0]
  (println (str "Iteration" iteration))
  (if (> iteration 3) 
    (println "Goodbye!")
    (recur (inc iteration))))

(defn recursive-printer
  ([]
   (recursive-printer 0))
  ([iteration]
   (println iteration)
   (if (> iteration 3)
     (println "Goodbye!")
     (recursive-printer (inc iteration)))))

(recursive-printer)

;; regular expressions
(re-find #"^left-" "left-eye")
(re-find #"^left-" "cleft-chin")
(re-find #"^left-" "wonglebart")

(matching-part {:name "left-eye" :size 1})
(matching-part {:name "head" :size 1})


;; reduce
(reduce + [1 2 3 4]) ;; with no initial value ie 0
(reduce + 15 [1 2 3 4]) ;; with initial value 15

(defn my-reduce
  ([f initial coll]
   (loop [result initial remaining coll]
     (if (empty? remaining )
       result
       (recur (f result (first remaining)) (rest remaining)))))
  ([f [head & tail]]
   (my-reduce f head tail)))

(my-reduce + 15 [1 2 3 4])

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts
          ))
(better-symmetrize-body-parts asym-hobbit-body-parts)

;; Hobbit Violence
(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts 
           accumulated-size (:size part)]
      (if (> accumulated-size target) 
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))


(hit asym-hobbit-body-parts)
(hit asym-hobbit-body-parts)
(hit asym-hobbit-body-parts)
(hit asym-hobbit-body-parts)




    
