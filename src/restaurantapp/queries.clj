(ns restaurantapp.queries
  (:require [korma.core :refer :all]
            [korma.db :refer :all]
            [restaurantapp.database :refer [db]]
            [restaurantapp.domain :refer :all]))


;; customers

(defn get-customers []
  (select customer))

(defn get-customer-by-contact [Contact]
 (first (select customer (where {:Contact Contact}))))

(defn get-customer [CustomerId]
  (first (select customer (where {:CustomerId CustomerId}))))

(defn add-customer [Name Contact]
  (def existingCustomer (get-customer-by-contact Contact))
  (if existingCustomer
    "Customer already exist!"
  ((def insertCustomer
    (insert customer (values {:Name Name :Contact Contact})))
    (def insertedCustomer (get insertCustomer :generated_key))
    (get-customer insertedCustomer))
    ))

(defn update-customer [CustomerId Name Contact]
  (def existingCustomer (get-customer CustomerId))
  (if existingCustomer
  (update customer (set-fields {
                                       :Name Name
                                       :Contact Contact
                                       }) (where {:CustomerId CustomerId})))
  "Customer does not exist!")

(defn delete-customer [CustomerId]
  (delete customer (where {:CustomerId CustomerId})))

;;items

(defn get-items []
  (select item))

(defn get-item [ItemId]
  (first (select item (where {:ItemId ItemId}))))


