(ns json-converter.core
  (:require [cheshire.core :refer :all]
            [clojure.tools.cli :as tools :only [cli]]
            [clojure.java.io :as io :only [reader writer]])
  (:gen-class :main true))

(defn- get-headers [input]
  (sort (keys (into {} (map merge input)))))

(defn- get-rows [headers input]
  (map (fn [row] (map #(pr-str (get-in row [%])) headers)) input))

(defn- format-output [headers rows]
  (map #(str (apply str (interpose \, %)) "\n")
       (cons (map pr-str headers) rows)))

(defn- build-output [input-spec]
  (with-open [r (io/reader input-spec)]
    (let [input (parsed-seq r)
          headers (get-headers input)
          rows (get-rows headers input)]
      (format-output headers rows))))

(defn- write-csv [output output-spec]
  (with-open [w (io/writer output-spec)]
    (doseq [l output]
      (.write w l))))

(defn- json->csv [{:keys [input-spec output-spec]}]
  (-> (build-output input-spec)
      (write-csv ,,, output-spec)))

(defn -main [& args]
  (let [[options args banner]
        (tools/cli args
                   ["-i" "--input-spec" "Input Specification" :default "*.json"]
                   ["-o" "--output-spec" "Output Specification" :default "result.csv"]
                   ["-h" "--help" "Show help" :default false :flag true])]
  (when (:help options)
    (println banner)
    (System/exit 0))
  (json->csv options)))
