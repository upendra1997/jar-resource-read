(ns jar-resource-read.core
  (:require [clojure.java.io :refer [resource file]])
  (:gen-class))

(defn read-all-txt-files [] 
  (let [txt-files (->> (resource "hello")
                       (file)
                       (file-seq)
                       (filter
                        #(-> %1
                             (.getName)
                             (.endsWith ".txt"))))]
    (for [f txt-files] 
      {:name (.getName f) 
       :content (slurp f)})))

(defmacro all-txt-files []
  (into [] (read-all-txt-files)))

(defn -main
  [& args]
  (doseq [{file-name :name content :content} (all-txt-files)]
    (println (format "%s:" file-name))
    (println content)
    (println "---------------")))
