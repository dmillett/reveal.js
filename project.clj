(defproject clojure-intro "1.0"
  :description "An introductory tutorial for Clojure, using Clash, that is paired with the reveal.js presentation framework."
  :url "https://github.com/dmillett/reveal.js"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT" }
  :scm {:name "git" :url "https://github.com/dmillett/reveal.js" }
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clash "1.1.1"]]
  :jvm-opts ["-Xms256m" "-Xmx256m"]
  :repl-options {:init (do
                         (load-file "src/clojure_intro/core.clj")
                         (use 'clojure_intro.core)
                         (use 'clash.tools)
                         (use 'clash.core)
                         (use 'clash.pivot)
                         (use 'clash.text_tools)
                         (defn load-local-resource
                           [logfile]
                           (str (System/getProperty "user.dir") logfile))
                         (println "Loaded 'clash' resources")
                         )} )
