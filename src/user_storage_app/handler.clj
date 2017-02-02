(ns user-storage-app.handler
  (:require [compojure.core :as compojure]
            [compojure.route :as route]
            [ring.util.http-response :as response]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(compojure/defroutes app-routes
  (compojure/GET "/" [] "Hello World")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
