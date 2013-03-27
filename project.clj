(defproject json-converter "0.1.0"
  :description "A small command line tool to convert simple, flat, key/value json records into a single csv with shared structure"
  :url "https://gitub.com/thoughtmanifest/json-converter"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main json-converter.core
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.cli "0.2.2"]
                 [cheshire "5.0.2"]])
