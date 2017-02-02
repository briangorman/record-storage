(ns user-storage-app.sorts
  (:require 
            [clojure.string :refer [lower-case]]
            [clj-time.format :as f]
            )
  (:gen-class))

(def american-format (f/formatter "MM/DD/YY"))

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


(defn gender-sort
  [x]
  (conj
    (filter #(= (compare "Male"  (:gender %)) 0 ) x)
    (filter #(= (compare "Female"  (:gender %)) 0 ) x)))
