# Introduction To [Clojure](https://github.com/clojure/clojure)
```clojure
(with (focus-on (data-analytics log_data))) ; ~Lisp on the JVM
```



## Clojure Presentation Topics

* Why Clojure?
* Basic Terms & Primitives
* Basic Functions & Java Interoperability (demo 1)
* Data Structures & Common Functions (demo 2)
* Clash & Laziness (demo 3)
* todo: Customized Demo
* Summary
* Resources



## Why Clojure?

Clojure was started by Rich Hickey in late 2007 and designed to be a functional language, 
modeled after Lisp (est 1958), that compiles to and runs on the JVM.  Benefits include:
* dynamic typing
* functional structure and immutable
* powerful interactive shell (REPL)
* broad open source community support ([Leiningen](http://leiningen.org/))
* based on the popular Lisp programming language



## Basic Terms & Keywords
* primitives - (think Java... briefly)
```clojure
1        ; a java.lang.Long
2.0      ; a java.lang.Double
"foobar" ; a java.lang.String
true     ; a java.lang.Boolean
```
* **keyword** - a unique identifier within a namespace
```clojure
:foo ; => ':foo', use as a key for key-value stores
```
* **symbol** - treated as a literal with no evaluation
```clojure
(+ 1 1)   ; => 2
'(+ 1 1)  ; => (+ 1 1), not 2
```
* **namespace** - groups symbols, variables, and functions
```clojure
(ns clojure.demo) ; at the top of a file
```



## Functions
Clojure functions, like Lisp, uses prefix notation for function execution. The function name
precedes its arguments.
* simple addition
```clojure
(+ 1 1)  ; instead of (1 + 1) 
```
* basic function
```clojure
(defn "Square a number" square-it 
  [x] (* x x))
(square-it 42)  ; => 1764, Chief Pontiac surrenders
```
* function documentation
```clojure
(doc square-it)
user/sq
([x])
  Square a number.
```


## Variables & Anonymous Functions
* a simple ~variable
```clojure
(def foo "A simple string object.") ; A namespace variable
```
* anonymous functions
```clojure
#(* 2 %)          ; shortcut 
(fn [x] (* 2 x))  ; => a function (see below)
; #object[user$eval1221$fn__1222 0x6ec522f8 ...]
```
* applied anonymous functions
```clojure
(def double-it "Double a number." #(* 2 %))
(double-it 2)  ; => 4
(#(* 2 %) 2)   ; => 4
```


## Java Interoperability & Using Namespaces
* java.lang.* does not require namespace import
```clojure
(.contains "FooBar" "B")  ; => true
(System/nanoTime)  ; => 2775688924064
(System/setProperty "foo.bar" "test")
;; Clojure contains? is not the same as Java String contains 
```
* **use** and **require** namespaces
```clojure
(use 'clojure.string)
(split "There.Can.Be.Only.One!" #"\.")
; => ["There" "Can" "Be" "Only" "One!"]
;
(require '[clojure.string :as s])
(s/split "All|work|and|no|play|sounds|like|Fortran?" #"\|") 
; => ["All" "work" "and" "no" "play" "sounds" "like" "Fortran?"]
```


## Simple Functions REPL Demonstration



## Common Data Structures
* **list** - a data collection (~LinkList)
```clojure
'(1 2 3)     ; => (1 2 3)
(list 1 2 3) ; => (1 2 3)
```
* **vector** - a data collection (~ArrayList)
```clojure
[1 2 3]        ; => [1 2 3]
(vector 1 2 3) ; => [1 2 3]
```
* Maps - key value stores (~HashMap)
```clojure
{:a 1 :b 2}               ; => {:a 1, :b 2}
(hash-map [:a 1 :b 2])    ; => {:a 1, :b 2}
(zip-map [:a :b] '(1 2))  ; => {:a 1, :b 2}
```
* Sets - unique value collection (~HashSet)
```clojure
#{1 2 3 2}          ; => #{1 2 3}
(hash-set 1 2 3 2)  ; => #{1 2 3}
```


## Collections of Data Structures
* fictional list of vectors for data aggregations
```clojure
(def results
  "A collection of data points per result"
  ; min, max, average, count
  '([1.0, 24.0, 18.5, 23]
    [1.1, 21.2, 17.6, 23]
    [0.85, 22.3, 19, 23])
```
* vector of maps for major Clojure contributors
```clojure
(def people ; (~ a pojo collection)
  "A collection of Clojure open source contributors."
  [{:first "Rich" :last "Hickey" :notable ["Clojure"]}
   {:first "Alex" :last "Miller" :notable ["Strange Loop"]}
   {:first "Kyle" :last "Kingsbury" :notable ["Riemann"]}   
   {:first "David" :last "Nolan" :notable ["ClojureScript", "Om"]}
   {:first "David" :last "Millett" :notable ["shameless plug"]} ]
```


## Common Data Functions
* collection access
```clojure
(count [1 2 3 4]     ; => 4
(first [1 2 3 4]     ; => 1
(rest [1 2 3 4]      ; => (2 3 4)
(last [1 2 3 4]      ; => 4
;
(nth [1 2 3 4] 1)    ; => 2
(take 2 [1 2 3 4])   ; => (1 2)
(filter even? [1 2 3 4]  ; => (2 4)
```
* hashmaps
```clojure
(count {:k1 1 :k2 2})        ; 2
(keys {:k1 1 :k2 2})         ; (:k1 :k2)
(vals {:k1 1 :k2 2})         ; (1 2)
;
(:k1 {:k2 1, :k3 2})         ; => 1
(-> {:k1 {:k2 2}} :k1 :k2)   ; => 2
(select-keys {:k1 1 :k2 2} [:k2])   ; => {:k2 2}
(get-in {"foo" {:k2 3}} ["foo" :k2]) ; => 3
```


## Mapping & Reducing Functions
* **map** - apply a function across a collection
```clojure
(map even? (range 0 4))  ; => (true false true false)
```
* **reduce** - the result from applying a function
```clojure
(reduce + (range 0 4))  ; => 6 or (+ 0 1 2 3)
(reduce #(+ %1 %2) (range 0 4))  ; => 6
```
* **let** - binding
```clojure
(defn let-example [x] (let [v (+ 3 x)] (* 3 v)))
(foolet 11) ; => 42 or (* 3 (+ 3 11))
```
* **for** - binding and iteration
```clojure
(for [[k v] {:a 2, :b 1}] (* 2 v))  ; => (4 2)
```


## Mapping & Reducing REPL Demonstration



## Clash
Known as Clojure Log Analysis Shell, this library is useful for quickly applying 
arbitrary structure(s) on log data to memory. It also provides a number of utility 
functions for building result sets, determine value frequencies, and measuring 
performance.

https://github.com/dmillett/clash.git


## Clash & Lazy Sequences
* laziness - delays execution of a Seqable function/object
```clojure
; Create a lazy sequence
(def million (range 0 1000000))
(require '[clash.tools :as ct])
;
; Lazily builds a seqable function/object (~70 ns)
(ct/perf (def evens (ct/collect-with million even?)))
; Builds and counts collection (~90 ms)
(ct/perf (count evens))
```
* Clash functions
```clojure
((ct/all? number? even?) 4)  ; => true, see: all? any? none? 
(ct/count-with million (ct/all? number? even?))  ; => 500,000
(ct/collect-value-frequencies [{:a "a" :b "b"} {a "a" :b "c"}])  ; frequency of values 
; => {:a {"a" 2} :b {"b" 1, "c" 1}}
```


## Clash Pivots & Higher Order Functions
* higher order functions
```clojure
(defn divide-by?
  "Divide a number by another number"
  [d]
  (fn [n] (zero? (mod n d))) )
;    
; Creates (divide-by? 2) (divide-by? 3) (divide-by? 4)
(def divvys (map #(divide-by? %) [2 3 4])) 
(map #(% 8) divfxs) ; => (true false true)
```
* Clash **pivot**
```clojure
(def wild_bunch (ct/pivot (range 0 1776) "DOI" :p divide-by? :v (range 1 7)) )
; => {DOI_[1] 1776, DOI_[2] 888, DOI_[3] 592, DOI_[4] 444, DOI_[5] 356, DOI_[6] 296}
```


## Clash Demonstration
todo: more detailed example



## Summary
Clojure is versatile language that runs on the JVM. Recently, it is gaining traction
 in other areas:

* Web - ClojureScript is Clojure that compiles to Javascript and runs in the web
* Mobile - React Native for Javascript apps for iOS and Android



## Resources
There are numerous resources available for Clojure development. Here are a few:

* Emacs
* Intellij Idea
* http://clojure.org
* http://learn-clojure.com
* http://www.4clojure.com
* https://www.manning.com/books/clojure-in-action
* https://pragprog.com/book/shcloj2/programming-clojure
