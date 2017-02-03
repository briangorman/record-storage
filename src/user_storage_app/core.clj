(ns user-storage-app.core
  (:require
   [clojure.string :refer [lower-case]]
   [clj-time.format :as f]
   [user-storage-app.handler :as handler]
   [user-storage-app.db :refer [users read-in-users]]
   [user-storage-app.sorts :as sorts]
   [ring.adapter.jetty :as jetty])
  (:gen-class))

(defn -main
  "pipe comma space"
  [pipe-file comma-file space-file & args]
  (with-open [pipe-reader  (clojure.java.io/reader pipe-file)
              comma-reader (clojure.java.io/reader comma-file)
              space-reader  (clojure.java.io/reader space-file)]
    (read-in-users pipe-reader \|)
    (read-in-users comma-reader \,)
    (read-in-users space-reader \space))

  (println "Sortd by Gender, then Last name ascending")
  (println (sorts/gender-sort @users))
  (println "Sortd by birth date, ascending")
  (println (sorts/date-of-birth-sort @users))
  (println "Sortd by names, descending")
  (println (sorts/last-name-sort @users))
  (println "Launching websever")
  (jetty/run-jetty handler/app {:port 3000}))
