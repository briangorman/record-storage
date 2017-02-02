(ns user-storage-app.core
  (:require
   [clojure.string :refer [lower-case]]
   [clj-time.format :as f]
   [user-storage-app.handler :as handler]
   [user-storage-app.db :refer [users read-in-users]]
   [user-storage-app.sorts :as sorts]
   [ring.adapter.jetty :as jetty])
  (:gen-class))

(def american-format (f/formatter "MM/DD/YY"))

(defn -main
  "pipe comma space"
  [pipe-file comma-file space-file & args]
  (read-in-users pipe-file \|)
  (read-in-users comma-file \,)
  (read-in-users space-file \space)
  (println @users)
  ;(println (sorts/age-sort @users))
  ;(println (sorts/date-of-birth-sort @users))
  (jetty/run-jetty handler/app {:port 3000}))
