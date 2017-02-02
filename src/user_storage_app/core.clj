(ns user-storage-app.core
  (:require 
            [clojure-csv.core :as csv]
            [clojure.string :refer [lower-case]]
            [clj-time.format :as f]
            [user-storage-app.handler :as handler]
            [user-storage-app.db :refer [users]]
            [user-storage-app.sorts :as sorts]
            [ring.adapter.jetty :as jetty]
            )
  (:gen-class))

(def american-format (f/formatter "MM/DD/YY"))

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


(defn -main
  "pipe comma space"
  [pipe-file comma-file space-file & args]
  (read-in-users pipe-file \|)
  (read-in-users comma-file \,)
  (read-in-users space-file \space)
  (println (sorts/third-sort @users))
  (jetty/run-jetty handler/app {:port 3000})
  )
