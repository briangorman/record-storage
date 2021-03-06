(ns user-storage-app.handler
  (:require [compojure.core :as compojure]
            [compojure.route :as route]
            [clojure-csv.core :as csv]
            [ring.util.http-response :as response]
            [ring.middleware.json :refer [wrap-json-response]]
            [user-storage-app.db :refer [users read-in-single-user]]
            [user-storage-app.sorts :as sorts]))

(defn- gender-handler [request]
  (response/ok
   (sorts/gender-sort @users)))

(defn- birth-date-handler [request]
  (response/ok
   (sorts/date-of-birth-sort @users)))

(defn- name-handler [request]
  (response/ok
   (sorts/last-name-sort @users)))

; Ideally, we would enforce a data schema here
; For now, I just limit insertions to one entity as requested
; This handler only deals with raw responses
(defn- new-record-handler [request]
  (if (some? request)
    (read-in-single-user
     (slurp (:body request)) \,))
  (response/ok))

(compojure/defroutes app-routes
  (compojure/POST "/records" request new-record-handler)
  (compojure/GET "/records/gender" [] gender-handler)
  (compojure/GET "/records/birthdate" [] birth-date-handler)
  (compojure/GET "/records/name" [] name-handler)
  (route/not-found "Not Found"))

(def app
  (wrap-json-response app-routes))
