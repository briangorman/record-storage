(defproject user-storage-app "0.0.0"
  :description "Simple Application that imports users from CSV, and provides
               a rest interface to make modifcations"
  :url "http://github.com/briangorman"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.csv "0.1.3"]
                 [compojure "1.5.1"]
                 [clj-time "0.13.0"]
                 [ring/ring-defaults "0.2.1"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler user-storage-app.handler/app}
  :main ^:skip-aot user-storage-app.core
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
