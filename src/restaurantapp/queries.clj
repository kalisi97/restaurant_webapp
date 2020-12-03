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
  (def existingCustomer (get-customer CustomerId))
  (if existingCustomer (delete customer (where {:CustomerId CustomerId}))
                       "Customer does not exist!"))

;;items

(defn get-items []
  (select item))

(defn get-item [ItemId]
  (first (select item (where {:ItemId ItemId}))))

(defn get-item-by-name [Name]
  (first (select item (where {:Name Name}))))

(defn add-item [Name Price]
  (def existingItem (get-item-by-name Name))
  (if existingItem
    "Item already exist!"
    (if (> Price 0)
    ((def insertItem
       (insert item (values {:Name Name :Price Price})))
     (def insertedItem (get insertItem :generated_key))
     (get-item insertedItem))
    "Price cannot be 0 or less!")
    ))

(defn delete-item [ItemId]
  (def existingItem (get-item ItemId))
  (if existingItem
    (delete item (where {:ItemId ItemId}))
    "Item does not exist!"))

(defn update-item [ItemId Name Price]
  (def existingItem (get-item ItemId))
  (if existingItem
    (if (> Price 0)
    (update item (set-fields {
                                  :Name Name
                                  :Price Price
                                  }) (where {:ItemId ItemId}))
    "Price cannot be 0 or less!")
  "Customer does not exist!"))