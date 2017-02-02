(ns user-storage-app.core
  (:require 
            [clojure-csv.core :as csv]
            [clojure.string :refer [lower-case]]
            [clj-time.format :as f]
            )
  (:gen-class))

(def american-format (f/formatter "MM/DD/YY"))

(def users (atom []))

(def my-keys [:last :first :gender :favorite-color :date-of-birth])

;can this be def'd better...
;Thought about breaking this apart, but - program requirements
;Explicitly require adding only one line in a post
(defn read-in-users
  "Populates users collection from input CSV file
   must pass in delimiter as char"
  [filename delimiter]
  (with-open [input-file (clojure.java.io/reader filename)]
    (doall
      (let [data (csv/parse-csv input-file :delimiter delimiter)]
        (map #(swap! users conj %) (map #(zipmap my-keys %) data))))))

;todo: force lower case for comparisons

(defn first-sort
  "returns users sorted by females first, then males. Each gender is further sorted
   by ascending last names"
  [x]
  (conj
    (sort-by #(str (:last %)) (filter #(= (compare "Male"  (:gender %)) 0 ) x))
    (sort-by #(str (:last %)) (filter #(= (compare "Female"  (:gender %)) 0 ) x))))

(defn second-sort
  "returns users sorted by date of birth in ascending order"
  [x]
    (sort-by #(f/parse american-format (:date-of-birth %)) x))

(defn third-sort
  "sort by last name, descending order"
  [x]
    (reverse (sort-by #(str (:last %)) x)))


(defn -main
  "pipe comma space"
  [pipe-file comma-file space-file & args]
  (read-in-users pipe-file \|)
  (read-in-users comma-file \,)
  (read-in-users space-file \space)
  (println (third-sort @users)))
