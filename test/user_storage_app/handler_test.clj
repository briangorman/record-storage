(ns user-storage-app.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [user-storage-app.handler :refer :all]
            [user-storage-app.db :refer [users]]
            [cheshire.core :refer [parse-string]]))

(defn db-fixture 
  [f]
  (reset! users [])
  (swap! users conj {:last-name "last",
                     :first-name "first",
                     :date-of-birth "07/01/1978",
                     :favorite-color "Yellow",
                     :gender "Female"})
  (f)
  (reset! users []))

(use-fixtures :each db-fixture)

(deftest gender-handler-test
  (testing "gender"
    (let [response (app (mock/request :get "/records/gender"))]
      (is (= 0 (compare (:last-name (first (parse-string (:body response) true ))) "last")))
      (is (= (:status response) 200)))))
  
(deftest birth-date-handler-test
  (testing "birth date"
    (let [response (app (mock/request :get "/records/birthdate"))]
      (is (= 0 (compare (:last-name (first (parse-string (:body response) true ))) "last")))
      (is (= (:status response) 200)))))

(deftest name-handler-test
  (testing "name"
    (let [response (app (mock/request :get "/records/name"))]
      (is (= 0 (compare (:last-name (first (parse-string (:body response) true ))) "last")))
      (is (= (:status response) 200)))))
  
(deftest not-found-test
  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
  
(deftest record-insertion-handler-test
  (testing "records insert"
    (let [response (app (mock/request :post "/records" "abc,def,female,teal,\"07/02/1990\""))]
      (is (= (:status response) 200))
      (is (= (count @users) 2)))))
  
