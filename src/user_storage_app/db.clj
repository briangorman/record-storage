(ns user-storage-app.db
  (:require [clojure-csv.core :as csv])
  (:gen-class))

(def users (atom []))

(def user-keys [:last-name :first-name :gender :favorite-color :date-of-birth])

(defn read-in-users
  "Populates users collection from input CSV file
   must pass in delimiter as char"
  [filename delimiter]
  (doall
    (let [data (csv/parse-csv filename :delimiter delimiter)]
      (map #(swap! users conj %) (map #(zipmap user-keys %) data)))))


(defn read-in-single-user
  "Populates users collection from input CSV file
   must pass in delimiter as char"
  [input delimiter]
  (doall
   (let [data (csv/parse-csv input :delimiter delimiter)]
     (swap! users conj (zipmap user-keys (first data))))))

