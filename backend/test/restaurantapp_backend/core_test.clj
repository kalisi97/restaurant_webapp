(ns restaurantapp-backend.core-test
  (:require [clojure.test :refer :all]
            [restaurantapp.queries :as q]))


;The correct behaviour in IntelliJ.
;The console is displaying the output of each test as it goes,
; the message Process finished with exit code 0 states that the test run was successful.
; If a test fails this will say something else

(deftest add-nums
  (testing "Positive cases"
    (is (= 3 (+ 2 1)))
    ))

;; this test should pass

(deftest create-customer
(testing "Retrieve customer"
  (let [customer (q/add-customer "Petar Peric" "0654565898")
     found-customer (q/get-customer (customer :CustomerId))]
 (is (= "Petar Peric" (found-customer :Name)))
  )))

(deftest multiple-customers-operations
  (testing "Find all customers"
    (def customerCount (count (q/get-customers)))
    (q/add-customer "Name2" "Contact2")
    (is (= (inc customerCount) (count (q/get-customers))))))

;;delete existing customer
(deftest delete-customer
  (testing "Delete customer"
    (let [customer (q/add-customer "Testing name" "Testing contact")]
    (q/delete-customer (customer :CustomerId))
      (is (= nil (q/get-customer (customer :CustomerId))))
    )))

;;delete non-existing customer
(deftest delete-non-existing-customer
  (testing "Delete customer"
      (is (= "Customer does not exist!" (q/delete-customer 100)))
      ))


(deftest update-customer
  (testing "Modifies existing customer"
    (let [customer-orig (q/add-customer "Customer1" "Contact1")
          customer-id (customer-orig :CustomerId)]
      (q/update-customer customer-id "Updated Customer" "Contact1")
      (is (= "Updated Customer" (:Name (q/get-customer customer-id)))))))