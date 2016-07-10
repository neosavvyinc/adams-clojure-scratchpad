(ns sicp-examples.chapter1 
  (:gen-class))

(defn -main 
  "SICP Examples" 
  [& args])

(defn example1 
  []
  (println "Example 1"))

(def pi 3.14159)

(def radius 10.0)

(def circumference (* 2 pi radius))

(defn square [x] (* x x))

(defn sum-of-squares [x y] (+ (square x) (square y)))

(defn f [a] (sum-of-squares (+ a 1) (* a 2)))

(defn abs [x]
 (cond  (> x 0) x
        (= x 0) 0
        (< x 0) (- x))
)
(defn abs1 [x]
  (cond (< x 0) (- x) 
        :else x)
)

