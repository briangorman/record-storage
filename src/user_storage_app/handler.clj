(ns user-storage-app.handler
  (:require [compojure.core :as compojure]
            [compojure.route :as route]
            [ring.util.http-response :as response]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :refer [wrap-json-response]]
            [user-storage-app.db :refer [users]]
            [user-storage-app.sorts :as sorts]))

(defn- gender-handler [request]
  (response/ok
    (sorts/gender-sort @users)))

(defn- birth-date-handler [request]
  (response/ok
    (sorts/date-of-birth-sort @users)))

(defn- name-handler [request]
  (response/ok
    (sorts/age-sort @users)))

(compojure/defroutes app-routes
  (compojure/GET "/records/gender" [] gender-handler)
  (compojure/GET "/records/birthdate" [] birth-date-handler)
  (compojure/GET "/records/name" [] name-handler)
  (route/not-found "Not Found"))

(def app
  (wrap-json-response app-routes))
