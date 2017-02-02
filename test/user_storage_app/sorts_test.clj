(ns user-storage-app.sorts-test
  (:require [clojure.test :refer :all]
            [user-storage-app.sorts :refer :all]))

(def test-data  
  (vector {:gender "Male", :last-name "Zulu", :date-of-birth "01/01/1992", :favorite-color "Blue", :first-name "Tony"}
        {:gender "Male", :last-name "Abraham", :date-of-birth "02/02/1993", :favorite-color "Black", :first-name "Antonio"}
        {:gender "female", :last-name "vick", :date-of-birth "03/11/1989", :favorite-color "Brown", :first-name "Mindy"}
        {:gender "Female", :last-name "Smith", :date-of-birth "10/12/2007", :favorite-color "White", :first-name "Karen"}))


(deftest test-gender-sort
  (testing "gender sorting"
    (let [data (gender-sort test-data)]
      (is (= 0 (compare (:last-name (nth data 0)) "Smith" )))
      (is (= 0 (compare (:last-name (nth data 1 )) "vick" )))
      (is (= 0 (compare (:last-name (nth data 2 )) "Abraham" )))
      (is (= 0 (compare (:last-name (nth data 3)) "Zulu" ))))))


(deftest test-date-of-birth-sort
  (testing "testing data of birth sort"
    (let [data (date-of-birth-sort test-data)]
      ;(is (= 0 (compare (:last-name (nth data 0)) "vick" )))
      ;(is (= 0 (compare (:last-name (nth data 1 )) "Zulu" )))
      ;(is (= 0 (compare (:last-name (nth data 2 )) "Abraham" )))
      (is (= 0 (compare (:last-name (nth data 3)) "Smith" ))))))

(deftest test-last-name-sort
  (testing "last name sort"
    (let [data (last-name-sort test-data)]
      (is (= 0 (compare (:last-name (nth data 0)) "Zulu")))
      (is (= 0 (compare (:last-name (nth data 1 )) "vick")))
      (is (= 0 (compare (:last-name (nth data 2 )) "Smith")))
      (is (= 0 (compare (:last-name (nth data 3)) "Abraham"))))))


