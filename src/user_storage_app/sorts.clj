(ns user-storage-app.sorts
  (:require
   [clojure.string :refer [lower-case]]
   [clj-time.format :as f])
  (:gen-class))

(def american-format (f/formatter "MM/DD/YY"))

;todo: force lower case for comparisons

(defn gender-sort
  "returns users sorted by females first, then males. Each gender is further sorted
   by ascending last names"
  [x]
  (concat
   (sort-by #(str (lower-case (:last-name %))) (filter #(= (compare "female" (lower-case (:gender %))) 0) x))
   (sort-by #(str (lower-case (:last-name %))) (filter #(= (compare "male"  (lower-case (:gender %))) 0) x))))

(defn date-of-birth-sort
  "returns users sorted by date of birth in ascending order"
  [x]
  (sort-by #(f/parse american-format (:date-of-birth %)) x))

(defn last-name-sort
  "sort by last name, descending order"
  [x]
  (reverse (sort-by #(str (lower-case (:last-name %))) x)))
