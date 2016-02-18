(ns clojure_demo.core
  (:require [clojure.string :as s]))

;; ************* Demo 1 ***************

;; Simple function definitions
(defn square-it
  "Square a number"
  [x]
  (* x x))

;; Anonymous functions
(def double-it-a
  "Double a number (using anonymous shortcut)"
  #(* 2 %))

(def double-it-b
  "Double a number (using 'fn')"
  (fn [x] (* 2 x)))

;; Java Interoperability, namespaces
(def text1 "There.Can.Be.Only.One!")
(def text2 "All|work|and|no|play|sounds|like|Fortran?")


;;*********** Demo 2 *******************
(def results
  "A collection of data points per result"
  ; min, max, average, count
  '([1.0, 24.0, 18.5, 23]
    [1.1, 21.2, 17.6, 23]
    [0.85, 22.3, 19, 23]))

(def people ; (~ a pojo collection)
  "A collection of Clojure open source contributors."
  [{:first "Rich" :last "Hickey" :notable ["Clojure"]}
   {:first "Alex" :last "Miller" :notable ["Strange Loop"]}
   {:first "Kyle" :last "Kingsbury" :notable ["Riemann"]}   
   {:first "David" :last "Nolan" :notable ["ClojureScript", "Om"]}
   {:first "David" :last "Millett" :notable ["shameless plug"]} ])
