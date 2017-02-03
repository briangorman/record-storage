(ns user-storage-app.core
  (:require
   [clojure.string :refer [lower-case]]
   [clj-time.format :as f]
   [user-storage-app.handler :as handler]
   [user-storage-app.db :refer [users read-in-users]]
   [user-storage-app.sorts :as sorts]
   [ring.adapter.jetty :as jetty])
  (:gen-class))

(defn- display-data
  [x]
  (doall (map #(println (:last-name %) (:first-name %) (:gender %) (:favorite-color %) (:date-of-birth %)) x)))

(defn -main
  "pipe comma space"
  [pipe-file comma-file space-file & args]
  (with-open [pipe-reader  (clojure.java.io/reader pipe-file)
              comma-reader (clojure.java.io/reader comma-file)
              space-reader  (clojure.java.io/reader space-file)]
    (read-in-users pipe-reader \|)
    (read-in-users comma-reader \,)
    (read-in-users space-reader \space))

  (println "Output data sorted by gender, then last name ascending:\n" )
  (display-data (sorts/gender-sort @users))
  (println "Output data sorted by date of birth ascending:\n" )
  (display-data (sorts/date-of-birth-sort @users))
  (println "Output data sorted by gender, then last name descending:\n" )
  (display-data (sorts/last-name-sort @users))
  (println "Launching websever")
  (flush)
  (jetty/run-jetty handler/app {:port 3000}))
