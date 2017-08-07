;; Chapter 2
(defn titleize [title] (str title " for the Brave and True"))

(map titleize ["Hamsters", "Ragnorok"])   ;; vector
(map titleize '("Empathy", "Decorating")) ;; list
(map titleize #{"Elbows" , "Soap Carving"}) ;; unsorted set
(map #(titleize (second %)) {:uncomfortable-thing "Winking"}) ;; anonymous function

(seq '(1 2 3)) ;; list
(seq [1 2 3]) ;; vector
(seq #{1 2 3}) ;; set
(seq {:name "Bill Compton" :occupation "Dead mopey guy"})

;; put the result of the seq back into an object
(into {}  (seq {:name "Bill Compton" :occupation "Dead mopey guy"}))

(map str ["a" "b" "c"] ["A" "B" "C"])

;; vampire example
(def human-consumption [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human :critter critter})

(map unify-diet-data human-consumption critter-consumption)

;; map with collection of functions
(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  "Applies the functions sum count and avg to the numbers passed as params (via anon func passed to map)"
  [numbers] 
  (map #(% numbers) [sum count avg]))

(stats [3 4 10])
(stats [80 1 44 13 6])


(def identities 
  [
   {:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-man" :real "Peter Parker"}
   {:alias "Santa" :real "your mom"}
   {:alias "The Easter Bunny" :real "your dad"}   
   ])

(map :real identities)


;; transform values to new values with reduce
(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {}
        {:max 30 :min 10})


;; above is the same as 
(assoc (assoc {} :max (inc 30)) :min (inc 10))

;; filter example with reduce
(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
        {}
        {:human 4.1
         :critter 3.9})

(take 3 [1 2 3 4 5 6 7 8 9 10])
(drop 3 [1 2 3 4 5 6 7 8 9 10])

(def food-journal
  [
   {:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}
   ]
)

;; show only january/february entries
(take-while #(< (:month %) 3) food-journal)
(drop-while #(< (:month %) 3) food-journal)
(take-while #(< (:month %) 4) (drop-while #(< (:month %) 2) food-journal)) ;; chained example

(filter #(< (:human %) 5) food-journal)
(filter #(< (:month %) 3) food-journal)

(some #(> (:critter %) 5) food-journal) ;; ask if the collection has any months were more than 5 critters consumed
(some #(> (:critter %) 3) food-journal) ;; ask if more than 3

(some #(and (> (:critter %) 3) %) food-journal) ;; return the result from the row that returns a truthy value

(sort [3 1 2])
(sort-by count ["aaa" "c" "bb"])
(sort ["aaa" "c" "bb"])
(concat [1 2] [3 4])

;;; vampire search tool
(def vampire-database
  {
   0 {:makes-blood-puns? false, :has-pulse? true :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true :name "McMackson"}
   2 {:makes-blood-puns? true, :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true, :has-pulse? true :name "Mickey Mouse"}
   }
)

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 10)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record) (not (:has-pulse? record)) record))

(defn identify-vampire
  [social-security-numbers]
(first (filter vampire? (map vampire-related-details social-security-numbers))))

(time (vampire-related-details 0))
(def mapped-details (map vampire-related-details (range 0 1000000)))
;;(time (first mapped-details))

;;(time (identify-vampire (range 0 1000000)))

;; infinite sequences
(concat (take 8 (repeat "na")) ["Batman!"])
(take 3 (repeatedly (fn [] (rand-int 10))))

;; custom lazy-sequence
(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2)))))
)
(take 10 (even-numbers))
(cons 0 '(2 4 6))

;; collections and what not
(empty? [])
(empty? ["no"])

(map identity {:sunlight-reaction "Glitter!"})

;; into
(into {} (map identity {:sunlight-reaction "Glitter!"}))

(def ingredients (map identity [:garlic :sesame-oil :fried-eggs]))
ingredients
(into [] ingredients)

(def garlic (map identity [:garlic-clove :garlic-clove]))
garlic
(into #{} garlic)

(into {:favorite-emotiion "gloomy"} [[:sunlight-reaction "Glitter!"]])
(into ["cherry"] '("pine" "spruce"))

(into {:favorite-animal "kitty"} {:least-favorite-smell "dog" :relationship-with-teenager "creepy"} )

;; conj
(conj [0] [1]) 

;; above is not equal to the below
(into [0] [1])

;; to get the equivalent
(conj [0] 1)
(conj [0] 1 2 3 4 5)
(conj {:time "midnight"} [:place "ye olde cemetarium"])

;; conj in terms of into
(defn my-conj
  [target & additions] 
  (into target additions))

(my-conj [1] 1 2 3)

(max 0 1 2)
(apply max [1 2 3])

;; into in terms of conj
(defn my-into
  [target & additions]
  apply conj target additions)

(my-into [0] [1 2 3])

(def add10 (partial + 10))
(add10 3)
(add10 5)

(def add-missing-elements
  (partial conj ["water" "earth" "air"]))
(add-missing-elements "unobtainium" "adamatium")

;; example of a toy partial for logging
(defn lousy-logger
  [log-level message] 
  (condp = log-level
    :warn (clojure.string/lower-case message) 
    :emergency (clojure.string/upper-case message)))

(def warn (partial lousy-logger :warn))
(def fail (partial lousy-logger :emergency))
(warn "Red light ahead")
(fail "stop")

(defn identify-humans
  [social-security-numbers]
  (filter #(not (vampire? %))
          (map vampire-related-details social-security-numbers)))



                      
