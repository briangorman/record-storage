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
  (read-in-users pipe-file \|)
  (read-in-users comma-file \,)
  (read-in-users space-file \space)

  (println "Sortd by Gender, then Last name ascending")
  (println (sorts/gender-sort @users))
  (println "Sortd by birth date, ascending")
  (println (sorts/date-of-birth-sort @users))
  (println "Sortd by names, descending")
  (println (sorts/last-name-sort @users))
  (println "Launching websever")
  (jetty/run-jetty handler/app {:port 3000}))
