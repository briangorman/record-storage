(ns user-storage-app.db
  (:require [clojure-csv.core :as csv])
  (:gen-class))

(def users (atom []))

(def user-keys [:last :first :gender :favorite-color :date-of-birth])

(defn read-in-users
  "Populates users collection from input CSV file
   must pass in delimiter as char"
  [filename delimiter]
  (with-open [input-file (clojure.java.io/reader filename)]
    (doall
      (let [data (csv/parse-csv input-file :delimiter delimiter)]
        (map #(swap! users conj %) (map #(zipmap user-keys %) data))))))

(defn read-in-single-user
  "Populates users collection from input CSV file
   must pass in delimiter as char"
  [input delimiter]
    (doall
      (let [data (csv/parse-csv input :delimiter delimiter)]
        (map #(swap! users conj %) (map #(zipmap user-keys %) data)))))


