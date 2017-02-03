(ns user-storage-app.db-test
  (:require [clojure.test :refer :all]
            [user-storage-app.sorts :refer :all]
            [user-storage-app.db :refer :all]))

(defn db-fixture
  [f]
  (reset! users [])
  (f))

(use-fixtures :each db-fixture)

(def test-data "abc,cde,male,blue,11/11/1111\nxyz,wvu,female,red,01/01/2000")

(deftest test-read-in-single-user
  (testing "Test reading in users"
    (read-in-single-user test-data \,)
    (is (= (count @users) 1))))

(deftest test-read-in-multiple-users
  (testing "Test reading in users"
    (read-in-users test-data \,)
    (is (= (count @users) 2))))

