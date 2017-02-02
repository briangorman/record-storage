(ns user-storage-app.handler
  (:require [compojure.core :as compojure]
            [compojure.route :as route]
            [ring.util.http-response :as response]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :refer [wrap-json-response]]
            [user-storage-app.db :refer [users]]
            [user-storage-app.sorts :as sorts]))

(defn gender-handler [request]
  (response/ok
    (sorts/gender-sort @users)))

(compojure/defroutes app-routes
  ;(compojure/POST "/records" [] "Hello World")
  (compojure/GET "/records/gender" request gender-handler)
  (compojure/GET "/records/birthdate" [] "Hello World")
  (compojure/GET "/records/name" [] "Hello World")
  (route/not-found "Not Found"))

(def app
  (wrap-json-response app-routes))
