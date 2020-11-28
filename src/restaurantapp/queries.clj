(ns restaurantapp.queries
  (:require [korma.core :refer :all]
            [korma.db :refer :all]
            [restaurantapp.database :refer [db]]
            [restaurantapp.domain :refer :all]))


;; customers

(defn get-customers []
  (select customer))

(defn get-customer [CustomerId]
  (first (select customer (where {:CustomerId CustomerId}))))

(defn add-customer [Name Contact]
  (insert customer (values {:Name Name :Contact Contact})))

(defn update-customer [CustomerId Name Contact]
  (update customer (set-fields {
                                       :Name Name
                                       :Contact Contact
                                       }) (where {:CustomerId CustomerId})))

(defn delete-customer [CustomerId]
  (delete customer (where {:CustomerId CustomerId})))

